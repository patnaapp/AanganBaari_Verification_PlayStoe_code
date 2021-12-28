package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.MarshmallowPermission;
import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.adapter.OtpVerifyAdaptor;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.Financial_Month;
import bih.in.aanganwadiBenVerification.entity.SevikaAttendance_Entity;
import bih.in.aanganwadiBenVerification.entity.SevikaSahaikaEntity;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.utility.Utiilties;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;

public class AttendanceActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rl_adhar_image;
    EditText edt_no_of_ben;
    TextView tv_username,tv_role,tv_district,tv_block,tv_panchayat11,tv_aanganwadi;
    ImageView take_photo;
    Button btn_submit;
    DataBaseHelper localDB ;
    String SectorCode="",ProjectCode="",AWCID="",AWCIDName="",URole="",AWCGOI_CODE="";
    UserDetails userInfo;
    ArrayList<Financial_Month> FinMnthList = new ArrayList<Financial_Month>();
    String monthlist;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter<String> monthlistadapter;
    Spinner spin_month;
    SevikaAttendance_Entity info;
    TelephonyManager tm;
    private static String imei;

    private final static int CAMERA_PIC = 99;
    Bitmap bmp;
    double take_latitude=0.00;
    double take_longitude=0.00;
    Intent imageData1;
    String str_imagcap1="",str_lat="",str_long="",TODO="",username="",var_month_id="",var_month_name="",var_year="",img_taken="",distcode="",pancode="",panname="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        localDB = new DataBaseHelper(this);

        initialise();


        if (FinMnthList .size() <= 0) {
            new GET_FinancialMonth().execute();
        } else {
            load_month_list();
        }

        try
        {
            SectorCode=getIntent().getStringExtra("SectorCode");
            ProjectCode=getIntent().getStringExtra("projectCode");
            ///URole=getIntent().getStringExtra("Role");
             username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            distcode =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("distcode", "");
            pancode =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pancode", "");
            panname =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pan_name", "");
            String password = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", "");
            Log.d("hcghgdhcg",username+password);
            userInfo = localDB.getUserDetails(username.toLowerCase(), password);
            URole=userInfo.getUserrole();

            tv_username.setText(userInfo.getUserName());
            tv_role.setText(userInfo.getUserrole());
            tv_district.setText(userInfo.getDistName());
            tv_block.setText(userInfo.getProject_Name());
            tv_aanganwadi.setText(userInfo.getAwc_Name()+""+userInfo.getAwc_Code());
            tv_panchayat11.setText(panname);
            if(URole.equalsIgnoreCase("AWC"))
            {

                //   AWCID = getIntent().getStringExtra("AWCID");
                AWCID = userInfo.getAwc_Code();
                // AWCIDName=getIntent().getStringExtra("AWCIDName");
                AWCIDName=userInfo.getAwc_Name();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        spin_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (arg2 > 0) {
                    //spn_dialogward.setSelection(0);

                    Financial_Month block = FinMnthList.get(arg2 - 1);
                    var_month_id = block.getMonthID();
                    var_month_name = block.getMonthName();
                    var_year = block.getYear();

                } else {
                    var_month_id = "";
                    var_month_name = "";
                    var_year = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setvalue();
                if (img_taken.equals("Y") && edt_no_of_ben.getText().toString().length()>0)
                {
                    new UploadAttendanceData(info).execute();
                }
            }
        });
    }

    public void setvalue()
    {
        info=new SevikaAttendance_Entity();
        info.set_FyearID(var_year);
        info.set_Month_ID(var_month_id);
        info.set_Distcode(userInfo.getDistrictCode());
        info.set_projectCode(userInfo.getProject_Code());
        info.set_PanchayatCode(pancode);
        info.set_AWCID(AWCID);
        info.set_EntryBy(username);
        info.set_UploadDate(Utiilties.getCurrentDate());
        info.set_AttaendanceDate(Utiilties.getCurrentDate());
        info.set_StudentCount(edt_no_of_ben.getText().toString());
        info.set_StudentPhoto(str_imagcap1);
        info.set_Lat(str_lat);
        info.set_Long(str_long);
        info.set_AppVersion(Utiilties.getAppVersion(AttendanceActivity.this));
        info.set_DeviceType(Utiilties.getDeviceName());
        info.set_Imei(getIMEIDeviceId(AttendanceActivity.this));

    }

    public void initialise()
    {
        rl_adhar_image=findViewById(R.id.rl_adhar_image);
        edt_no_of_ben=findViewById(R.id.edt_no_of_ben);
        tv_username=findViewById(R.id.tv_username);
        tv_role=findViewById(R.id.tv_role);
        tv_district=findViewById(R.id.tv_district);
        tv_block=findViewById(R.id.tv_block);
        tv_panchayat11=findViewById(R.id.tv_panchayat11);
        tv_aanganwadi=findViewById(R.id.tv_aanganwadi);
        take_photo=findViewById(R.id.take_photo);
        btn_submit=findViewById(R.id.btn_submit);
        spin_month=findViewById(R.id.spin_month);

        take_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.take_photo)
        {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                buildAlertMessageNoGps();
            }
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true)
            {
                Intent iCamera = new Intent(getApplicationContext(), CameraActivity.class);
                iCamera.putExtra("KEY_PIC", "1");
                startActivityForResult(iCamera, CAMERA_PIC);
            }
        }
    }

    private void buildAlertMessageNoGps()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setIcon(R.drawable.bihgov);
        builder.setTitle("GPS ?");
        builder.setMessage("(GPS)जीपीएस बंद है\nस्थान के अक्षांश और देशांतर प्राप्त करने के लिए कृपया जीपीएस चालू करें")
//		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("[(GPS) जीपीएस चालू करे ]", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("[ बंद करें ]", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK)
                {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    // byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    DataBaseHelper placeData = new DataBaseHelper(getApplicationContext());

                    switch (data.getIntExtra("KEY_PIC", 0))
                    {
                        case 1:
                            imageData1=data;
                            bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
                            take_photo.setScaleType(ImageView.ScaleType.FIT_XY);
                            take_photo.setImageBitmap(Utiilties.GenerateThumbnail(bmp,500, 500));
                            str_imagcap1 = org.kobjects.base64.Base64.encode(imgData);
                            str_lat = data.getStringExtra("Lat");
                            str_long = data.getStringExtra("Lng");
                            img_taken="Y";
                            break;

                    }
                }
        }
    }


    private class GET_FinancialMonth extends AsyncTask<String, Void, ArrayList<Financial_Month>> {

        private final ProgressDialog dialog = new ProgressDialog(AttendanceActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AttendanceActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Panchayat...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Financial_Month> doInBackground(String... param) {

            return WebServiceHelper.getFinancialMonth();

        }

        @Override
        protected void onPostExecute(ArrayList<Financial_Month> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(AttendanceActivity.this);


                long i = helper.setFinancialMonthLocal(result);
                if (i > 0) {
                    load_month_list();

                    // Toast.makeText(BasicDetails.this, "Success", Toast.LENGTH_SHORT).show();

                } else {
                    //Toast.makeText(BasicDetails.this, "Fail", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(AttendanceActivity.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void load_month_list() {

        FinMnthList = localDB.getFinancialMonth();
        String[] divNameArray = new String[FinMnthList.size() + 1];
        divNameArray[0] = "-select-";
        int i = 1;
        int setID = 0;
        for (Financial_Month gen : FinMnthList) {
            divNameArray[i] = gen.getMonthName();
            if (monthlist == gen.getMonthID()) {
                setID = i;
            }
            i++;

        }
        monthlistadapter = new ArrayAdapter<String>(this, R.layout.dropdowlist, divNameArray);
        monthlistadapter.setDropDownViewResource(R.layout.dropdowlist);
        spin_month.setAdapter(monthlistadapter);


    }

    public static String getIMEIDeviceId(Context context) {

        String deviceId;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "";
                }
            }
            assert mTelephony != null;
            if (mTelephony.getDeviceId() != null)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    deviceId = mTelephony.getImei();
                }else {
                    deviceId = mTelephony.getDeviceId();
                }
            } else {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
        Log.d("deviceId", deviceId);
        return deviceId;
    }

    private class UploadAttendanceData extends AsyncTask<String, Void, String>
    {
        SevikaAttendance_Entity data;
        private final ProgressDialog dialog = new ProgressDialog(AttendanceActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AttendanceActivity.this).create();

        UploadAttendanceData(SevikaAttendance_Entity data)
        {
            this.data = data;
        }

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param)
        {

            String res = WebServiceHelper.UploadAttendanceDate(data);
            return res;

        }

        @Override
        protected void onPostExecute(String result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (result != null)
            {
                if (result.equals("1"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);

                    builder.setTitle("सफलतापूर्वक");
                    // Ask the final question
                    builder.setMessage("उपस्थिति सफलतापूर्वक अपलोड की गई");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent i =new Intent(AttendanceActivity.this,ChooseSchemeActivity.class);
                            startActivity(i);
                            finish();

                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
                else if (result.equals("0"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
                    builder.setTitle("Alert!!");
                    builder.setMessage("Failed To Add Compliance");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    Toast.makeText(AttendanceActivity.this, "Your data is not uploaded Successfully ! ", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(AttendanceActivity.this, "Result:null ..Uploading failed...Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

}