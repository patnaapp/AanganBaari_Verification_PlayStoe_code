package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.utility.CommonPref;
import bih.in.aanganwadiBenVerification.utility.GlobalVariables;
import bih.in.aanganwadiBenVerification.utility.Utiilties;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class LoginActivity extends Activity {

    ConnectivityManager cm;
    public static String UserPhoto;
    String version;
    TelephonyManager tm;
    private static String imei;
    //TODO setup Database
    //DatabaseHelper1 localDBHelper;
    Context context;
    String uid = "";
    String mob = "";
    String otp = "";
    String pass = "";
    EditText userName,et_otp;
    EditText et_Mobile_Num;
    String[] param;
    DataBaseHelper localDBHelper;

    UserDetails userInfo;
    Spinner spn_chooseDept;
    ArrayList<String>chhosePost=new ArrayList<>();
    ArrayAdapter<String>choosepostAdaptor;
    TextView appversion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chhosePost.add("Lady Supervisor");

        chhosePost.add("Anganwadi");
        userName = (EditText) findViewById(R.id.et_username);

        et_Mobile_Num = (EditText) findViewById(R.id.et_Mobile_Num);
        appversion = (TextView) findViewById(R.id.appversion);
        et_otp = (EditText) findViewById(R.id.et_otp);
        Button loginBtn = (Button) findViewById(R.id.btn_login);
        TextView signUpBtn = (TextView) findViewById(R.id.tv_signup);
        spn_chooseDept=(Spinner)findViewById(R.id.spn_chooseDept);
        localDBHelper=new DataBaseHelper(getApplicationContext());

        choosepostAdaptor = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_item, chhosePost);
        choosepostAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_chooseDept.setAdapter(choosepostAdaptor);
        String version = null;
        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(),
                    0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        appversion.setText("App Version :- "+ " "+ version);

        spn_chooseDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(chhosePost.get(i).equalsIgnoreCase("Anganwadi"))
                {
                    et_Mobile_Num.setHint("ओ.टी.पी.");
                }
                else
                {
                    et_Mobile_Num.setHint("पासवर्ड");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                param = new String[2];
                param[0] = userName.getText().toString();
                param[1] = et_Mobile_Num.getText().toString();
                //  param[2] = et_otp.getText().toString();
                if (param[0].length() < 1)
                {
                    Toast.makeText(LoginActivity.this, "कृपया सही यूजर का नाम दर्ज करे", Toast.LENGTH_SHORT).show();
                }
                else if (param[1].length() < 1)
                {
                    Toast.makeText(LoginActivity.this, "कृपया सही पासवर्ड  दर्ज करे", Toast.LENGTH_SHORT).show();
                }/*else if (param[2].length() < 1){
                    Toast.makeText(LoginActivity.this, "कृपया सही ओ.टी.पी. दर्ज करे", Toast.LENGTH_SHORT).show();
                }*/
                else
                {
                    if(Utiilties.isOnline(LoginActivity.this))
                    {
                        new LoginTask(param[0], param[1], "").execute();
                    }
                    else
                    {
                        userInfo=localDBHelper.getUserDetails(param[0].toLowerCase(),param[1]);
                        if(userInfo!=null)
                        {
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("uid", param[0]).commit();
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("pass", param[1]).commit();
                            start();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please Turn On Internet Connection For First Time",Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent singUpInt = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(singUpInt);
            }
        });

        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void getUserDetail()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("uid", "user");
        String password = prefs.getString("pass", "password");
        userInfo = localDBHelper.getUserDetails(username.toLowerCase(), password);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //getIMEI();
    }


    private class LoginTask extends AsyncTask<String, Void, UserDetails>
    {
        String username,mobile_num,otp;

        LoginTask(String username, String Mobile_Num,String Otp)
        {
            this.username = username;
            this.mobile_num = Mobile_Num;
            //this.otp = Otp;
        }
        private  ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        private final AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Authenticating...");
            this.dialog.show();
        }
        @Override
        protected UserDetails doInBackground(String... param)
        {
            if (!Utiilties.isOnline(LoginActivity.this))
            {
                UserDetails userDetails = new UserDetails();
                userDetails.setAuthenticated(true);
                return userDetails;
            }
            else
            {
                return WebServiceHelper.Login(username,mobile_num,"");
            }
        }
        @Override
        protected void onPostExecute(final UserDetails result)
        {
            Log.d("vhvchvdch",result.getUserID());

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if (result != null && result.isAuthenticated() == false)
            {
                alertDialog.setTitle(getResources().getString(R.string.failed));
                alertDialog.setMessage(getResources().getString(R.string.authentication_failed));
                alertDialog.show();
            }
            else if (!(result != null))
            {
                AlertDialog.Builder ab = new AlertDialog.Builder(context);
                ab.setTitle(getResources().getString(R.string.server_down_title));
                ab.setMessage(getResources().getString(R.string.server_down_text));
                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        dialog.dismiss();
                    }
                });
                ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                ab.show();
            }
            else
            {
             //-----------------------------------------Online-------------------------------------
                if (Utiilties.isOnline(LoginActivity.this))
                {
                    Log.d("vhvchvdch11",result.getUserID());
                    if (result!= null && result.isAuthenticated()==true)
                    {
                        uid=result.getUserID();
                        mob = result.getMobileNo();
                        otp = result.getOtp();
                        pass = param[1];
                        try
                        {
                            GlobalVariables.LoggedUser = result;
                            GlobalVariables.LoggedUser.setUserID(userName.getText().toString().trim().toLowerCase());
                            GlobalVariables.LoggedUser.setPassword(pass);
                            GlobalVariables.LoggedUser.setOtp(et_otp.getText().toString().trim());
                            CommonPref.setUserDetails(LoginActivity.this,GlobalVariables.LoggedUser);
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("distcode", result.getDistrictCode()).commit();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("pancode", result.getPanchayatCode()).commit();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("pan_name", result.getPanchayatName()).commit();
                            long c = setLoginStatus(GlobalVariables.LoggedUser,param[1]);
                            if (c > 0)
                            {
                                start();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, getResources().getString(R.string.authentication_failed),Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.authentication_failed),Toast.LENGTH_SHORT).show();
                        }

                    }
                    // offline -------------------------------------------------------------------------
                }
                /* else {

                    if (localDBHelper.getUserCount() > 0) {

                        GlobalVariables.LoggedUser = localDBHelper
                                .getUserDetails_new(userName.getText()
                                                .toString().trim().toLowerCase(),
                                        et_Mobile_Num.getText().toString(),et_otp.getText().toString());

                        if (GlobalVariables.LoggedUser != null) {

                            CommonPref.setUserDetails(
                                    getApplicationContext(),
                                    GlobalVariables.LoggedUser);

                            SharedPreferences.Editor editor = SplashActivity.prefs.edit();
                            editor.putBoolean("username", true);
                            editor.putBoolean("mob", true);
                            editor.putBoolean("otp", true);
                            editor.putString("uid", uid);
                            editor.putString("mob", mob);
                            editor.putString("otp", otp);
                            editor.commit();
                            start();

                        } else {

                            Toast.makeText(
                                    getApplicationContext(),
                                    getResources().getString(R.string.username_password_notmatched),
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                getResources().getString(R.string.enable_internet_for_firsttime),
                                Toast.LENGTH_LONG).show();
                    }
                }*/
            }

        }
    }

    private long setLoginStatus(UserDetails details,String pass)
    {
        details.setMobileNo(mob);
        details.setOtp(otp);
        SharedPreferences.Editor editor = SplashActivity.prefs.edit();
        editor.putBoolean("username", true);
        editor.putBoolean("mob", true);
        editor.putBoolean("otp", true);
        editor.putString("uid", uid.toLowerCase());
        editor.putString("mob", mob);
        editor.putString("otp", otp);
        editor.putString("role", details.getUserrole());
        editor.commit();
        //PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("USER_ID", uid).commit();
        PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("uid", uid).commit();
        PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("pass", pass).commit();
        PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("mobile", mob).commit();
        PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("otp", otp).commit();


        localDBHelper = new DataBaseHelper(LoginActivity.this);

        long c = localDBHelper.insertUserDetails(details,pass);

        return c;
    }

    public void start()
    {
        //getUserDetail();
        //new SyncPanchayatData().execute("");
        Intent iUserHome = new Intent(LoginActivity.this,ChooseSchemeActivity.class);
        startActivity(iUserHome);
        finish();
    }

}
