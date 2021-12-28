package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.adapter.OtpVerifyAdaptor;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.Financial_Month;
import bih.in.aanganwadiBenVerification.entity.Financial_Year;
import bih.in.aanganwadiBenVerification.entity.OtpVerifyModel;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.entity.ward;
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;

public class DashboardActivity extends Activity implements View.OnClickListener
{

    Dialog dialogselectdata;
    TextView tv_userName,pending_sudhar_pond,pending_sudhar_well,pending_upload_well,pending_upload_pond,tv_district,tv_block,tv_panchayat,tv_role,pending_manrega,pending_upload_manrega,pending_other,pending_upload_other;
    Button btn_sync_pond,btn_sync_well,btn_sync_panchayat;
    TextView tv_panchayat11,tv_aanganwadi;

    LinearLayout ll_plantation_click,ll_plantatoin_menu,plantatoin_list_layout,ll_plantation_upload,ll_plantation_report_data;
    RelativeLayout rl_sync_data,rl_sync_bendata_0tp;
    TextView pending_sudhar_plantation;
    ImageView iv_plantation_drpdwn,btn_logout;

    DataBaseHelper dataBaseHelper;

    Boolean isShowPlantation = false;
    ArrayList<AWC_Model> AwcList = new ArrayList<AWC_Model>();

    UserDetails userInfo;
    LinearLayout plantatoin_list_layout1,ll_plantation_upload1,otpverifyactivity;
    TextView pending_sudhar_plantation1;
    String ForActivity="";
    ArrayList<OtpVerifyModel>BenDataList=new ArrayList<>();
    String SectorCode="",ProjectCode="",AWCID="",AWCIDName="",URole="",AWCGOI_CODE="";
    SQLiteDatabase db;
    DataBaseHelper helper;
    Button dialog_btnOk,btn_reject;
    Spinner Spn_finyr,Spn_fin_month;
    ArrayList<Financial_Year> FinYearList = new ArrayList<Financial_Year>();
    ArrayList<Financial_Month> FinMnthList = new ArrayList<Financial_Month>();
    String monthlist;
    ArrayAdapter<String> monthlistadapter;
    String var_month_id="",var_month_name="",var_year="";
    String finYR[] = {"2021-2022"};
    DataBaseHelper localDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        helper = new DataBaseHelper(this);
        dataBaseHelper=new DataBaseHelper(this);
        localDB = new DataBaseHelper(this);
        ForActivity=getIntent().getStringExtra("ForActivity");
        ModifyTable();
        CREATETABLEIFNOTEXIST();
        try
        {
            SectorCode=getIntent().getStringExtra("SectorCode");
            ProjectCode=getIntent().getStringExtra("projectCode");
            ///URole=getIntent().getStringExtra("Role");
            String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            String password = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", "");
            Log.d("hcghgdhcg",username+password);
            userInfo = dataBaseHelper.getUserDetails(username.toLowerCase(), password);
            URole=userInfo.getUserrole();
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


        ll_plantation_click = (LinearLayout) findViewById(R.id.ll_plantation_click);
        ll_plantatoin_menu = (LinearLayout) findViewById(R.id.ll_plantatoin_menu);
        plantatoin_list_layout = (LinearLayout) findViewById(R.id.plantatoin_list_layout);
        ll_plantation_upload = (LinearLayout) findViewById(R.id.ll_plantation_upload);
        ll_plantation_report_data = (LinearLayout) findViewById(R.id.ll_plantation_report_data);
        plantatoin_list_layout1 = (LinearLayout) findViewById(R.id.plantatoin_list_layout1);
        ll_plantation_upload1 = (LinearLayout) findViewById(R.id.ll_plantation_upload1);
        otpverifyactivity = (LinearLayout) findViewById(R.id.otpverifyactivity);
        rl_sync_bendata_0tp = (RelativeLayout) findViewById(R.id.rl_sync_bendata_0tp);

        rl_sync_data = (RelativeLayout) findViewById(R.id.rl_sync_data);

        btn_logout = (ImageView) findViewById(R.id.btn_logout);

        tv_userName = (TextView) findViewById(R.id.tv_username);
        pending_sudhar_plantation = (TextView) findViewById(R.id.pending_sudhar_plantation);
        pending_sudhar_plantation1 = (TextView) findViewById(R.id.pending_sudhar_plantation1);
        pending_upload_pond = (TextView) findViewById(R.id.pending_upload_pond);

        iv_plantation_drpdwn = (ImageView) findViewById(R.id.iv_plantation_drpdwn);

        tv_district = (TextView) findViewById(R.id.tv_district);
        tv_block = (TextView) findViewById(R.id.tv_block);
        tv_panchayat = (TextView) findViewById(R.id.tv_panchayat);
        tv_role = (TextView) findViewById(R.id.tv_role);
        tv_panchayat11 = (TextView) findViewById(R.id.tv_panchayat11);
        tv_aanganwadi = (TextView) findViewById(R.id.tv_aanganwadi);

        ll_plantation_click.setOnClickListener(this);
        plantatoin_list_layout.setOnClickListener(this);
        ll_plantation_upload.setOnClickListener(this);
        ll_plantation_report_data.setOnClickListener(this);
        plantatoin_list_layout1.setOnClickListener(this);
        ll_plantation_upload1.setOnClickListener(this);
        otpverifyactivity.setOnClickListener(this);

        rl_sync_data.setOnClickListener(this);

        FinMnthList = dataBaseHelper.getFinancialMonth();

        if (FinMnthList .size() <= 0) {
            new GET_FinancialMonth().execute();
        }


        getUserDetail();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        if (ForActivity.equalsIgnoreCase("1"))
        {
            otpverifyactivity.setVisibility(View.GONE);
            rl_sync_bendata_0tp.setVisibility(View.GONE);
            plantatoin_list_layout1.setVisibility(View.VISIBLE);
        }
        else if (ForActivity.equalsIgnoreCase("2"))
        {
            otpverifyactivity.setVisibility(View.VISIBLE);
            rl_sync_bendata_0tp.setVisibility(View.VISIBLE);
            plantatoin_list_layout1.setVisibility(View.GONE);
        }

        rl_sync_bendata_0tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BenDataList = dataBaseHelper.getOtpVerifyList_toSyncNew(AWCID);
                if (BenDataList.size()>0){
                    new AlertDialog.Builder(DashboardActivity.this)
                            .setTitle("बेनिफिसिअरी सूचि")
                            .setIcon(R.drawable.icds_logo)
                            .setMessage("नया बेनिफिसिअरी सूचि लोड करने के लिए कृपया पहले से वेरीफाई किये हुए डाटा को सर्वर पे अपलोड करे |")
                            .setCancelable(false)
                            .setPositiveButton("ठीक है", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })

                            .show();
                }
                else {

                    if(!AWCID.equalsIgnoreCase(""))
                    {
                        if (URole.equalsIgnoreCase("AWC"))
                        {

                            //  BenDataList.clear();
//                            BenDataList = dataBaseHelper.getOtpDetailsBenListforshowlist(AWCID,var_month_id,var_year);
//                            if (BenDataList.size() == 0)
//                            {
                               // new LoadBeneficiary().execute();
                                ShowPanchayatDialogue();
//                            }
//                            else
//                            {
//
//                                // Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
//                            }

                        }
                    }


                   // Toast.makeText(getApplicationContext(),"सत्यापित करे बटन पे क्लिक करके आंगनबाड़ी का चयन करे |",Toast.LENGTH_LONG).show();


                }

            }
        });
    }

    private void showPending()
    {
        String plantaionCount = dataBaseHelper.getPlantationUpdatedDataCount();

        pending_sudhar_plantation1.setText(plantaionCount);

    }

    private void confirmLogout()
    {
        SplashActivity.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = SplashActivity.prefs.edit();
        editor.putBoolean("username", false);
        editor.putBoolean("password", false);
        editor.commit();
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("uid", "").commit();
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("pass", "").commit();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.deleteAllPlantationRecord();
        //  dataBaseHelper.deleteCredential(getActivity(), "1");
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void logout()
    {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setIcon(R.drawable.icds_logo)
                .setMessage("Are you sure you want to logout from app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmLogout();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void getUserDetail()
    {

        String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
        String password = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", "");
        Log.d("hcghgdhcg",username+password);
        userInfo = dataBaseHelper.getUserDetails(username.toLowerCase(), password);

        tv_userName.setText(userInfo.getUserName());
        tv_role.setText(userInfo.getUserrole());
        tv_district.setText(userInfo.getDistName());
        tv_block.setText(userInfo.getProject_Name());
        tv_aanganwadi.setText(userInfo.getAwc_Name()+""+userInfo.getAwc_Code());
        tv_panchayat11.setText(userInfo.getSector_Name()+" "+userInfo.getSector_Code());
        AwcList = dataBaseHelper.getAwcLiost(userInfo.getSector_Code());
        if(AwcList.size()==0)
            new SyncAWC().execute();


        Log.e("Role", userInfo.getUserrole());
        Log.e("District", userInfo.getDistrictCode());
        //Log.e("Block", userInfo.getBlockCode());
    }

    private String capitalizeFirstCharacter(String name)
    {
        String[] nameList = name.trim().split(" ");
        String orderName = "";

        for(String item: nameList)
        {
            item = item.toLowerCase();
        }

        return orderName;
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        switch (id)
        {
            case R.id.plantatoin_list_layout1:

                if(userInfo.getUserrole().equalsIgnoreCase("AWC"))
                {
                    Intent intent = new Intent(DashboardActivity.this, showdata.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("AWCID", userInfo.getAwc_Code());
                    intent.putExtra("AWCIDName", userInfo.getAwc_Name());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }
                else
                    {
                    Intent intent = new Intent(DashboardActivity.this, showdata.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }
                break;

            case R.id.otpverifyactivity:
                if(userInfo.getUserrole().equalsIgnoreCase("AWC")) {
                    Intent intent = new Intent(DashboardActivity.this, OtpVerifyDataActivity.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("AWCID", userInfo.getAwc_Code());
                    intent.putExtra("AWCIDName", userInfo.getAwc_Name());
                    intent.putExtra("Role", userInfo.getUserrole());
                    intent.putExtra("ForActivity", ForActivity);

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(DashboardActivity.this, OtpVerifyDataActivity.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }
                break;

            case R.id.ll_plantation_upload1:
                if(userInfo.getUserrole().equalsIgnoreCase("AWC")) {
                    Intent intent = new Intent(DashboardActivity.this, ShowUploadData.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("AWCID", userInfo.getAwc_Code());
                    intent.putExtra("AWCIDName", userInfo.getAwc_Name());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(DashboardActivity.this, ShowUploadData.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }
                break;
            case R.id.rl_sync_data:

                new SyncAWC().execute();
                dataBaseHelper.deleteBendataData();
                showPending();
        }
    }


    private class SyncAWC extends AsyncTask<String, Void, ArrayList<AWC_Model>> {
        private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<AWC_Model> doInBackground(String...arg) {

            Log.d("dfcgg",userInfo.getSector_Code()+"   "+userInfo.getProject_Code());
            return WebServiceHelper.getAWCList(userInfo.getSector_Code(),userInfo.getProject_Code());

        }

        @Override
        protected void onPostExecute(ArrayList<AWC_Model> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("dgvgfcv",result.toString());
            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

            long i= helper.setAWCDataToLocal(result,userInfo.getSector_Code());

            if(i>0)
            {
                new GET_FinancialMonth().execute();
                Toast.makeText(getApplicationContext(), " Awc Data Synced Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to update Awc",Toast.LENGTH_SHORT).show();

            }


        }
    }

    private class SyncWardData extends AsyncTask<String, Void, ArrayList<ward>> {
        private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<ward> doInBackground(String...arg) {


            return WebServiceHelper.getWardListData(userInfo.getBlockCode());

        }

        @Override
        protected void onPostExecute(ArrayList<ward> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

            //   new SyncVillageData().execute("");
            long i= helper.setWardDataToLocal(result);

            if(i>0)
            {
                Toast.makeText(getApplicationContext(), "Ward Data Synced Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to update ward",Toast.LENGTH_SHORT).show();

            }


        }
    }

    /* private class SyncVillageData extends AsyncTask<String, Void, ArrayList<VillageListEntity>> {
         private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

         @Override
         protected void onPreExecute() {
             this.dialog.setCanceledOnTouchOutside(false);
             this.dialog.setMessage("Loading...");
             this.dialog.show();
         }

         @Override
         protected ArrayList<VillageListEntity> doInBackground(String...arg) {


             return WebServiceHelper.getVillageListData(userInfo.getBlockCode());

         }

         @Override
         protected void onPostExecute(ArrayList<VillageListEntity> result) {
             if (this.dialog.isShowing()) {
                 this.dialog.dismiss();
             }
             DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

             long i= helper.setVillageDataToLocal(result);

             if(i>0)
             {
                 Toast.makeText(getApplicationContext(), "Village Data Synced Successfully",Toast.LENGTH_SHORT).show();
             }
             else
             {
                 Toast.makeText(getApplicationContext(), "Failed to update village",Toast.LENGTH_SHORT).show();

             }
         }
     }

     private class SyncPlantationSiteData extends AsyncTask<String, Void, ArrayList<PlantationSiteEntity>> {
         private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

         @Override
         protected void onPreExecute() {
             this.dialog.setCanceledOnTouchOutside(false);
             this.dialog.setMessage("Loading...");
             this.dialog.show();
         }

         @Override
         protected ArrayList<PlantationSiteEntity> doInBackground(String...arg) {


             return WebServiceHelper.getPlantationSiteData();

         }

         @Override
         protected void onPostExecute(ArrayList<PlantationSiteEntity> result) {
             if (this.dialog.isShowing()) {
                 this.dialog.dismiss();
             }
             DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

             long i= helper.setPlantationSiteDataToLocal(result);

             if(i>0)
             {
                 Toast.makeText(getApplicationContext(), "Plantation Site Data Synced Successfully",Toast.LENGTH_SHORT).show();
             }
             else
             {
                 Toast.makeText(getApplicationContext(), "Failed to update Plantation Site Data",Toast.LENGTH_SHORT).show();

             }
         }
     }
 */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.icds_logo)
                .setTitle(R.string.appTitle)
                .setMessage("Are you sure you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i=new Intent(DashboardActivity.this,ChooseSchemeActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try
        {
            SectorCode=getIntent().getStringExtra("SectorCode");
            ProjectCode=getIntent().getStringExtra("projectCode");
            ///URole=getIntent().getStringExtra("Role");
            String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            String password = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", "");
            Log.d("hcghgdhcg",username+password);
            userInfo = dataBaseHelper.getUserDetails(username.toLowerCase(), password);
            URole=userInfo.getUserrole();
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

        showPending();

    }

    public  void deleteBenToSyncData(String awcid,String month,String year)
    {

        String[] params = new String[] {awcid,month,year};
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        long c = db.delete("OtpDetails", "AwcId=? and month_id=? and year_id=?", params);
        if(c>0)
        {
            Log.e("OtpDetails","success");
        }
        else
        {
            Log.e("OtpDetails","failed");
        }

    }


    public void ModifyTable()
    {

        if (isColumnExists("OtpDetails", "otp_valid_from") == false)
        {
            AlterTable("OtpDetails", "otp_valid_from");
        }
        if (isColumnExists("OtpDetails", "otp_valid_to") == false)
        {
            AlterTable("OtpDetails", "otp_valid_to");
        }

        if (isColumnExists("OtpDetails", "Is_Verify") == false)
        {
            AlterTable("OtpDetails", "Is_Verify");
        }

        //------------
        if (isColumnExists("OtpDetails", "Yob") == false)
        {
            AlterTable("OtpDetails", "Yob");
        }
        if (isColumnExists("OtpDetails", "BenId") == false)
        {
            AlterTable("OtpDetails", "BenId");
        }
        if (isColumnExists("OtpDetails", "NoofMonth") == false)
        {
            AlterTable("OtpDetails", "NoofMonth");
        }
        if (isColumnExists("OtpDetails", "nutrition") == false)
        {
            AlterTable("OtpDetails", "nutrition");
        }
        if (isColumnExists("OtpDetails", "bentype") == false)
        {
            AlterTable("OtpDetails", "bentype");
        }
        if (isColumnExists("OtpDetails", "gender") == false)
        {
            AlterTable("OtpDetails", "gender");
        }

        if (isColumnExists("OtpDetails", "month_id") == false)
        {
            AlterTable("OtpDetails", "month_id");
        }
        if (isColumnExists("OtpDetails", "year_id") == false)
        {
            AlterTable("OtpDetails", "year_id");
        }
        if (isColumnExists("OtpDetails", "isDataReady") == false)
        {
            AlterTable("OtpDetails", "isDataReady");
        }
        //--------

        if (isColumnExists("BenDetails", "Delete_benif") == false)
        {
            AlterTable("BenDetails", "Delete_benif");
        }

    }

    public boolean isColumnExists(String table, String column)
    {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + table + ")", null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if (column.equalsIgnoreCase(name))
                {
                    return true;
                }
            }
        }
        helper.getReadableDatabase().close();
        return false;
    }

    public void AlterTable(String tableName, String columnName)
    {
        try
        {
            db = helper.getWritableDatabase();
            db.execSQL("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " TEXT");
            Log.e("ALTER Done", tableName + "-" + columnName);
            helper.getWritableDatabase().close();
        }
        catch (Exception e)
        {
            Log.e("ALTER Failed", tableName + "-" + columnName);
        }
    }

    private class LoadBeneficiary extends AsyncTask<String, Void, ArrayList<OtpVerifyModel>>
    {
        private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<OtpVerifyModel> doInBackground(String...arg)
        {
            Log.d("TAGDATA3",AWCID);
            return WebServiceHelper.getOtpBenificiaryList(AWCID,var_month_id,var_year);
        }

        @Override
        protected void onPostExecute(ArrayList<OtpVerifyModel> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            Log.d("dgvgfcvqqq", result.toString());

            if(result!=null)
            {
                if (result.size() > 0)
                {
                    DataBaseHelper helper = new DataBaseHelper(getApplicationContext());

                    long i = helper.setOtpBenLocal(result,AWCID,var_month_id,var_year);
                    //BenDataList = result;
                    BenDataList=dataBaseHelper.getOtpDetailsBenListforshowlist(AWCID,var_month_id,var_year);


                    if (i > 0)
                    {
                        Toast.makeText(getApplicationContext(), " लाभार्थी डेटा सफलतापूर्वक सिंक किया गया ", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed to update Beneficiary", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {

                    Toast.makeText(getApplicationContext(),"इस आंगनवाड़ी में कोई डेटा नहीं है |",Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void ShowPanchayatDialogue() {
        dialogselectdata = new Dialog(DashboardActivity.this);
        dialogselectdata.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogselectdata.setContentView(R.layout.selectvaluedialog);
        dialog_btnOk = (Button) dialogselectdata.findViewById(R.id.btn_ok);
        btn_reject = (Button) dialogselectdata.findViewById(R.id.btn_reject);
        Spn_finyr = (Spinner) dialogselectdata.findViewById(R.id.Spn_finyr);
        Spn_fin_month = (Spinner) dialogselectdata.findViewById(R.id.Spn_fin_month);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, finYR);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spn_finyr.setAdapter(adapter);

        Spn_finyr.setEnabled(false);
        load_month_list();


        //  FinYearList = dataBaseHelper.getFinancialYr();

//        Spn_finyr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int arg2, long arg3) {
//                if (arg2 > 0) {
//
//                    Panchayat_List district = PanchayatPhase1List.get(arg2 - 1);
//                    var_spn_panchayat_code_phase1 = district.getPanchayat_code();
//                    var_spn_panchayat_name_phase1 = district.getPanchayat_Name();
//                    //loadBlockSpinnerData();
//
//                } else {
//                    var_spn_panchayat_code_phase1 = "";
//                    var_spn_panchayat_name_phase1 = "";
//
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });
        Spn_fin_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        dialog_btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteBenToSyncData(AWCID,var_month_id,var_year);
                if (!var_month_id.equals(""))
                {
                    new LoadBeneficiary().execute();
                }



            }

        });
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogselectdata.dismiss();
            }
        });

        if (dialogselectdata.isShowing()) dialogselectdata.dismiss();
        dialogselectdata.show();
        //panchayatadapter.setNotifyOnChange(true);
    }

    private class GET_FinancialMonth extends AsyncTask<String, Void, ArrayList<Financial_Month>> {

        private final ProgressDialog dialog = new ProgressDialog(DashboardActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(DashboardActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Active Month List...");
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

                DataBaseHelper helper = new DataBaseHelper(DashboardActivity.this);


                long i = helper.setFinancialMonthLocal(result);
                if (i > 0)
                {
                    //load_month_list();

                     Toast.makeText(DashboardActivity.this, "Month List Loaded", Toast.LENGTH_SHORT).show();

                }
                else
                    {
                    //Toast.makeText(BasicDetails.this, "Fail", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(DashboardActivity.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void load_month_list() {

        FinMnthList = dataBaseHelper.getFinancialMonth();
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
        Spn_fin_month.setAdapter(monthlistadapter);

       /* if (getIntent().hasExtra("KeyId")) {
            Spn_fin_month.setSelection(((ArrayAdapter<String>) Spn_fin_month.getAdapter()).getPosition(_spin_chng_khesra));
        }
*/

    }

    public void CREATETABLEIFNOTEXIST() {

        db = localDB.getWritableDatabase();
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS FinMonth (Month_Name TEXT, Month_Id TEXT, Year TEXT, Active TEXT)");
            localDB.getWritableDatabase().close();
            Log.e("CREATE SUCCESS ", "FinMonth");
            localDB.getWritableDatabase().close();
            //db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CREATE Failed ", "FinMonth");
        }
    }
}
