package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.Manifest;
import bih.in.aanganwadiBenVerification.MarshmallowPermission;
import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.Block;
import bih.in.aanganwadiBenVerification.entity.District;
import bih.in.aanganwadiBenVerification.entity.SignUp;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;

public class SignUpActivity extends Activity implements AdapterView.OnItemSelectedListener {

    DataBaseHelper dataBaseHelper;

    LinearLayout ll_reg_type_below_list,ll_organisation,ll_et_org,ll_level;
    EditText et_organisation,et_name,et_mobile_no,et_designation;
    Spinner spn_register_as,spn_level,spn_dist,spn_block,spn_panchayat,spn_organisation;

    Button btn_signUp;
    ImageView btn_cancel;
    String st_et_name,st_et_mobile,st_Desig;

    String districtCode="",districtName="",blockCode="",blockName="",panchayatCode="",panchayatName="", registerType, registerTypeName, level, levelName, organisationId, organisationName;

    TelephonyManager tm;
    public Context context;


    ArrayList<District> distList = new ArrayList<District>();
    ArrayList<Block> blockList = new ArrayList<Block>();
    String regTypeList[] = {"-Select-","Individual", "Government", "Non Govt. Org."};
    String levelList[] = {"-Select-","Level 1", "Level 2", "Level 3"};
    String organisationList[] = {"-Select-","Org 1", "Org 2", "Org 3"};

    ArrayList<String> districtNameArray;
    ArrayList<String> blockNameArray;

    private static String imei;
    String version="";

    ArrayAdapter<String> districtadapter;
    ArrayAdapter<String> blockadapter;
    ArrayAdapter<String> regTypeAdapter;
    ArrayAdapter<String> levelAdapter;
    ArrayAdapter<String> orgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Initialization();

        dataBaseHelper = new DataBaseHelper(SignUpActivity.this);
        dataBaseHelper = new DataBaseHelper(this);

        try {
            dataBaseHelper.createDataBase();

        } catch (IOException ioe) {throw new Error("Unable to create database");}

        try {
            dataBaseHelper.openDataBase();
        } catch (SQLException sqle) {throw sqle;}


        loadSpinner();
        loadDistrictSpinner();

        btn_signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SignUpEntry();
                    }

                });

        btn_cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }

                });
    }
    public void Initialization()
    {
        spn_register_as = (Spinner)findViewById(R.id.spn_register_as);
        spn_level =(Spinner)findViewById(R.id.spn_level);
        spn_dist =(Spinner)findViewById(R.id.spn_dist);
        spn_block =(Spinner)findViewById(R.id.spn_block);
        spn_panchayat =(Spinner)findViewById(R.id.spn_panchayat);
        spn_organisation =(Spinner)findViewById(R.id.spn_organisation);

        spn_register_as.setOnItemSelectedListener(this);
        spn_level.setOnItemSelectedListener(this);
        spn_dist.setOnItemSelectedListener(this);
        spn_block.setOnItemSelectedListener(this);
        spn_panchayat.setOnItemSelectedListener(this);
        spn_organisation.setOnItemSelectedListener(this);

        et_name = (EditText)findViewById(R.id.et_name);
        et_organisation = (EditText)findViewById(R.id.et_organisation);
        et_mobile_no = (EditText)findViewById(R.id.et_mobile_no);
        et_designation = (EditText)findViewById(R.id.et_designation);

        btn_signUp = (Button)findViewById(R.id.btn_signUp);
        btn_cancel = (ImageView)findViewById(R.id.reg_cancel);

        ll_reg_type_below_list = (LinearLayout) findViewById(R.id.ll_reg_type_below_list);
        ll_organisation = (LinearLayout) findViewById(R.id.ll_organisation);
        ll_et_org = (LinearLayout) findViewById(R.id.ll_et_org);
        ll_level = (LinearLayout) findViewById(R.id.ll_level);
        ll_reg_type_below_list.setVisibility(View.GONE);
    }

    public void loadSpinner(){

        regTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, regTypeList);
        regTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_register_as.setAdapter(regTypeAdapter);

        levelAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levelList);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_level.setAdapter(levelAdapter);

        orgAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, organisationList);
        orgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_organisation.setAdapter(orgAdapter);
    }

    public void loadDistrictSpinner() {
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        distList = dataBaseHelper.getDistrict();
        districtNameArray = new ArrayList<String>();
        districtNameArray.add("-Select-");
        int i = 0;
        for (District district_list : distList) {
            districtNameArray.add(district_list.get_DistName());
            i++;
        }
        districtadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districtNameArray);
        districtadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_dist.setAdapter(districtadapter);
    }

    public void loadBlockSpinner() {
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        blockList = dataBaseHelper.getBlock(districtCode);
        blockNameArray = new ArrayList<String>();
        blockNameArray.add("-Select-");
        int i = 0;
        for (Block block_list : blockList) {
            blockNameArray.add(block_list.getBlockName());
            i++;
        }
        blockadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, blockNameArray);
        blockadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_block.setAdapter(blockadapter);
    }


    public void SignUpEntry() {

        Intent intent = new Intent(this, SignupAuthActivity.class);
        startActivity(intent);
        //long c = 0;
//        setValue();
//        SignUp signUp = new SignUp();
//        if (Validate()) {
//            signUp.setDist_code(districtCode);
//            signUp.setBlock_code(blockCode);
//            signUp.setName(st_et_name);
//            signUp.setDesignation(st_Desig);
//            //signUp.setFname(st_et_fname);
//            signUp.setMobile(st_et_mobile);
//            //signUp.setPassword(st_et_password);
//            //setConfirm_password(st_et_confirm_password);
//
//            new UPLOADDATA(signUp).execute();
//
//        }

    }
    public void setValue()
    {
        st_et_name=et_name.getText().toString();
        //st_et_address=et_address.getText().toString();
        //st_et_fname=et_fname.getText().toString();
        st_et_mobile=et_mobile_no.getText().toString();
        st_Desig = et_designation.getText().toString();
        //st_et_password=et_password.getText().toString();
        //st_et_confirm_password=et_confirm_password.getText().toString();

    }

    private boolean Validate()
    {
        View focusview=null;
        boolean validate=true;
        if(TextUtils.isEmpty(st_et_name))
        {
            focusview=et_name;
            validate=false;
            et_name.setError("please enter name");

        }
        if(TextUtils.isEmpty(st_et_mobile))
        {
            focusview=et_mobile_no;
            validate=false;
            et_mobile_no.setError("please enter mobile number");

        }

        else if (st_et_mobile.length() != 10) {
            et_mobile_no.setError(getString(R.string.Invalid_Number));
            focusview = et_mobile_no;
            validate = false;
        }
        if(TextUtils.isEmpty(st_Desig))
        {
            focusview=et_designation;
            validate=false;
            et_designation.setError("please enter password");

        }

        if(districtCode.equals(""))
        {
            focusview=spn_dist;
            validate=false;
            Toast.makeText(getApplicationContext(),"please select district", Toast.LENGTH_LONG).show();
        }
        if(blockCode.equals(""))
        {
            focusview=spn_block;
            validate=false;
            Toast.makeText(getApplicationContext(),"please select Block", Toast.LENGTH_LONG).show();
        }

        return validate;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spn_register_as:
                if (position > 0)
                {
                    registerTypeName = regTypeList[position];
                    registerType = ""+position;
                    ll_reg_type_below_list.setVisibility(View.VISIBLE);
                    if(position == 1)
                    {
                        ll_organisation.setVisibility(View.GONE);
                        ll_et_org.setVisibility(View.GONE);
                        ll_level.setVisibility(View.GONE);
                        et_designation.setVisibility(View.GONE);
                    }
                    else if(position == 2)
                    {
                        ll_et_org.setVisibility(View.GONE);
                        ll_level.setVisibility(View.VISIBLE);
                        et_designation.setVisibility(View.VISIBLE);
                        ll_organisation.setVisibility(View.VISIBLE);
                    }
                    else if(position == 3)
                    {
                        ll_organisation.setVisibility(View.GONE);
                        ll_level.setVisibility(View.VISIBLE);
                        et_designation.setVisibility(View.VISIBLE);
                        ll_et_org.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    ll_reg_type_below_list.setVisibility(View.GONE);
                }
                break;
            case R.id.spn_level:
                if (position > 0)
                {
                    levelName = levelList[position];
                }
                break;
            case R.id.spn_dist:
                if (position > 0)
                {
                    districtCode = distList.get(position - 1).get_DistCode();
                    districtName = distList.get(position - 1).get_DistName();

                    loadBlockSpinner();
                }
                break;
            case R.id.spn_block:
                if(position > 0){
                    blockCode = blockList.get(position - 1).getBlockCode();
                    blockName = blockList.get(position - 1).getBlockName();
                }
                break;
            case R.id.spn_panchayat:
                if(position > 0){
                    panchayatCode = blockList.get(position - 1).getBlockCode();
                    panchayatName = blockList.get(position - 1).getBlockName();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*   private class DISTRICTDATA extends AsyncTask<String, Void, ArrayList<District_list>> {
 
         private final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
 
         private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(SignUpActivity.this).create();
 
         @Override
         protected void onPreExecute() {
 
             this.dialog.setCanceledOnTouchOutside(false);
             this.dialog.setMessage("Loading...");
             this.dialog.show();
         }
 
         @Override
         protected ArrayList<District_list> doInBackground(String... param) {
 
 
             return WebServiceHelper.getDistrictData();
 
         }
 
         @Override
         protected void onPostExecute(ArrayList<District_list> result) {
             if (this.dialog.isShowing()) {
                 this.dialog.dismiss();
             }
 
             if (result != null) {
                 Log.d("Resultgfg", "" + result);
 
                 DataBaseHelper helper = new DataBaseHelper(getApplicationContext());
 
 
                 long i = helper.setDistrictName(result);
                 if (i > 0) {
 
                     loadDistrictSpinner();
                     // Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
 
                 }
 
                 else
                     {
                     Toast.makeText(getApplicationContext(), "Server is Slow.Please Try later", Toast.LENGTH_SHORT).show();
                 }
 
             }
         }
     }*/
//    private class loadBlockDatanew extends AsyncTask<String, Void, ArrayList<BlockWeb>> {
//
//
//        String distCode = "";
//        ArrayList<BlockWeb> blocklist = new ArrayList<BlockWeb>();
//        private final ProgressDialog dialog = new ProgressDialog(
//                SignUpActivity.this);
//
//
//
//        @Override
//        protected void onPreExecute() {
//
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setMessage("Loading...");
//            this.dialog.show();
//        }
//
// //       @Override
////        protected ArrayList<BlockWeb> doInBackground(String... param) {
////
////            this.blocklist = WebServiceHelper.getBlockData(st_spn_dist_code);
////
////            return this.blocklist;
////        }
//
//        @Override
//        protected void onPostExecute(ArrayList<BlockWeb> result) {
//            if (this.dialog.isShowing()) {
//                this.dialog.dismiss();
//
//            }
//
//            if (result != null) {
//                if (result.size() > 0) {
//
//                    DataBaseHelper placeData = new DataBaseHelper(SignUpActivity.this);
//                    long i = placeData.setBlockDataLocal(result, this.distCode);
//                    if (i > 0) {
//                        setBlockData();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loading_fail),
//                            Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//        }
//
//    }



    private class UPLOADDATA extends AsyncTask<String, Void, String> {
        SignUp data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(SignUpActivity.this).create();


        UPLOADDATA(SignUp data) {
            this.data = data;

        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


            String res = WebServiceHelper.completeSignup(this.data,imei,version);
            return res;

        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue",""+result);
            if (result != null)
            {
                String string = result;
                String[] parts = string.split(",");
                String part1 = parts[0]; // 004-


                if(part1.equals("1"))
                {

                    Toast.makeText(getApplicationContext(), "सफलतापूर्वक पंजीकरण हो गया, कृपया अनुमोदन की प्रतीक्षा करें", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "पंजीकरण विफल हुआ, कृपया दूसरे नंबर से प्रयत्न करें", Toast.LENGTH_SHORT).show();
                }
            } }
    }
    private void getIMEI()
    {
        context = this;
        //Database Opening
        dataBaseHelper = new DataBaseHelper(SignUpActivity.this);
        dataBaseHelper = new DataBaseHelper(this);

        try
        {
            dataBaseHelper.createDataBase();
        }
        catch (IOException ioe)
        {
            throw new Error("Unable to create database");
        }
        try
        {
            dataBaseHelper.openDataBase();

        }
        catch (SQLException sqle)
        {
            throw sqle;
        }

        MarshmallowPermission permission = new MarshmallowPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permission.result == -1 || permission.result == 0)
        {
            try
            {
                tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                if (tm != null)
                    imei = tm.getDeviceId();
            }
            catch (Exception e)
            {

            }
        }
        else if (permission.result == 1)
        {
            tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) imei = tm.getDeviceId();
                    /* Intent i=new Intent(this,LoginActivity.class);
                     startActivity(i);
           	finish();*/
        }

        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {

        }

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getIMEI();
    }

}
