package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.utility.CommonPref;

public class HomeActivity extends AppCompatActivity {
    LinearLayout ll_verification,ll_edit,ll_upload;
    TextView tv_username,tv_role,tv_district,tv_block,tv_panchayat_new,tv_aanganwadi;
    DataBaseHelper dataBaseHelper;
    String Str_userid,Str_username,Str_role,Str_distcode,Str_distname,Str_projectCode,Str_projectName,Str_panchayatcode,
           Str_PanchayatName,Str_awcCode,Str_awcName;
    ImageView btn_logout;
    public static ProgressDialog progressDialog;
    ProgressBar profressBar1;
    ProgressDialog pd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBaseHelper=new DataBaseHelper(HomeActivity.this);
        pd1= new ProgressDialog(HomeActivity.this);
        pd1.setTitle("Data is Uploading Wait");
        pd1.setCancelable(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        Str_userid= CommonPref.getUserDetails(HomeActivity.this).getUserID();
        Str_username= CommonPref.getUserDetails(HomeActivity.this).getName();
        Str_role= CommonPref.getUserDetails(HomeActivity.this).getUserrole();
        Str_distcode= CommonPref.getUserDetails(HomeActivity.this).getDist_Code();
        Str_distname= CommonPref.getUserDetails(HomeActivity.this).getDist_Name();
        Str_projectCode= CommonPref.getUserDetails(HomeActivity.this).getProject_Code();
        Str_projectName= CommonPref.getUserDetails(HomeActivity.this).getProject_Name();
        Str_panchayatcode= CommonPref.getUserDetails(HomeActivity.this).getPanchayat_Code();
        Str_PanchayatName= CommonPref.getUserDetails(HomeActivity.this).getPanchayat_Name();
        Str_awcCode= CommonPref.getUserDetails(HomeActivity.this).getAwc_Code();
        Str_awcName= CommonPref.getUserDetails(HomeActivity.this).getAwc_Name();

        inilization();
    }

    public void inilization(){
        ll_verification=(LinearLayout)findViewById(R.id.ll_verification);
        ll_edit=(LinearLayout)findViewById(R.id.ll_edit);
        ll_upload=(LinearLayout)findViewById(R.id.ll_upload);

        tv_username=(TextView) findViewById(R.id.tv_username);
        tv_role=(TextView) findViewById(R.id.tv_role);
        tv_district=(TextView) findViewById(R.id.tv_district);
        tv_block=(TextView) findViewById(R.id.tv_block);
        tv_panchayat_new=(TextView) findViewById(R.id.tv_panchayat_new);
        tv_aanganwadi=(TextView) findViewById(R.id.tv_aanganwadi);
        btn_logout=(ImageView) findViewById(R.id.btn_logout);

        ll_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,showdata.class);
                startActivity(intent);
            }
        });
        ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ll_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle("पुष्टीकरण")
                .setIcon(R.drawable.logo1)
                .setMessage("क्या आप वाकई ऐप से लॉगआउट होना चाहते हैं?")
                .setCancelable(false)
                .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmLogout();
                    }
                })
                .setNegativeButton("नहीं", null)
                .show();
    }
    private void confirmLogout(){
        SplashActivity.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SplashActivity.prefs.edit();
        editor.putBoolean("username", false);
        editor.putBoolean("mob", false);
        editor.putBoolean("otp", false);
        editor.commit();

//        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
//        dataBaseHelper.deleteAllPlantationRecord();
        //  dataBaseHelper.deleteCredential(getActivity(), "1");
        Intent intent = new Intent(this,
                LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        showPending();

    }
    private void showPending(){

        tv_username.setText(Str_username);
        tv_role.setText(Str_role);
        tv_district.setText(Str_distname);
        tv_block.setText(Str_projectName);
        tv_panchayat_new.setText(Str_PanchayatName);
        tv_aanganwadi.setText(Str_awcName);

//        String plantaionCount = dataBaseHelper.getPlantationUpdatedDataCount();
//
//        pending_sudhar_plantation.setText(plantaionCount);
//
//        if(Integer.parseInt(plantaionCount) > 0){
//            isShowPlantation = true;
//            handlePlantationMenu();
//        }else{
//            pending_sudhar_plantation.setVisibility(View.GONE);
//        }
    }

   /* private class LoadBenList extends AsyncTask<String, Void, ArrayList<BenList>> {

        String _dcode;

        LoadBenList(String dcode)
        {
            this._dcode=dcode;
        }
        @Override
        protected void onPreExecute() {
            try {


                progressDialog.setMessage("कृपया प्रतीक्षा कीजिये...\nलाभार्थी का विवरण डाउनलोड हो रहा है");

                progressDialog.show();
            }
            catch (Exception e)
            {

            }
        }

        @Override
        protected ArrayList<BenList> doInBackground(String... param) {

            return WebServiceHelper.GetStudentAttendanceList(_dcode);
        }

        @Override
        protected void onPostExecute(ArrayList<BenList> result) {


            if (result != null) {
                if (result.size() > 0) {

                    Log.d("result.size",""+result.size());
                    final String totalres=""+result.size();
                    DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

                    long i= helper.setBenLocal(result);

                    if(i>0){
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
                        ab.setCancelable(false);
                        ab.setIcon(R.drawable.download);

                        ab.setTitle("डाउनलोड सफल,हुआ");
                        ab.setMessage(result.size() +" लाभार्थी का विवरण सफलतापूर्वक डाउनलोड हुआ.");
                        Dialog dialog = new Dialog(HomeActivity.this);
                        dialog.setCanceledOnTouchOutside(false);
                        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
//                                    txtTStd.setText(totalres);
//                                    txtTotalStudents.setText("Total Student : " + totalres);
                                //resetValueS();
                                dialog.dismiss();

                            }
                        });

                        ab.show();


                    }
                    else{
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
                        ab.setCancelable(false);
                        ab.setIcon(R.drawable.download);

                        ab.setTitle("DOWNLOAD FAIL");
                        ab.setMessage(" कोई भी लाभार्थी इस आंगनवाड़ी केंद्र में डाउनलोड के लिए नहीं है: " + Str_awcName);
                        Dialog dialog = new Dialog(HomeActivity.this);
                        dialog.setCanceledOnTouchOutside(false);
                        ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {

                                dialog.dismiss();

                            }
                        });

                        ab.show();


                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
                    ab.setCancelable(false);
                    ab.setIcon(R.drawable.download);

                    ab.setTitle("DOWNLOAD FAIL");
                    ab.setMessage(" कोई भी लाभार्थी इस आंगनवाड़ी केंद्र में डाउनलोड के लिए नहीं है: " + Str_awcName);
                    Dialog dialog = new Dialog(HomeActivity.this);
                    dialog.setCanceledOnTouchOutside(false);
                    ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {

                            dialog.dismiss();

                        }
                    });

                    ab.show();


                }
            }
            else
            {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                Toast.makeText(getApplicationContext(), "Response NULL.", Toast.LENGTH_SHORT).show();


            }
            // btnSync.clearAnimation();
            //profressBar1.setVisibility(View.GONE);
        }
    }*/
}
