package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.WdcShowDataActivity;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.entity.ward;
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;

public class WdcDashBoardActivity extends Activity implements View.OnClickListener {

    TextView tv_userName,pending_sudhar_pond,pending_sudhar_well,pending_upload_well,pending_upload_pond,tv_district,tv_block,tv_panchayat,tv_role,pending_manrega,pending_upload_manrega,pending_other,pending_upload_other;
    Button btn_sync_pond,btn_sync_well,btn_sync_panchayat;
    TextView tv_panchayat11,tv_aanganwadi;

    LinearLayout ll_plantation_click,ll_plantatoin_menu,plantatoin_list_layout,ll_plantation_upload,ll_plantation_report_data;
    RelativeLayout rl_sync_data,rl_sync_bendata;
    TextView pending_sudhar_plantation;
    ImageView iv_plantation_drpdwn,btn_logout;

    DataBaseHelper dataBaseHelper;

    Boolean isShowPlantation = false;
    ArrayList<AWC_Model> AwcList = new ArrayList<AWC_Model>();

    UserDetails userInfo;
    LinearLayout plantatoin_list_layout1,ll_plantation_upload1;
    TextView pending_sudhar_plantation1;
    ArrayList<wdcBenList>BenDataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdc_dashboard);

        ll_plantation_click = (LinearLayout) findViewById(R.id.ll_plantation_click);
        ll_plantatoin_menu = (LinearLayout) findViewById(R.id.ll_plantatoin_menu);
        plantatoin_list_layout = (LinearLayout) findViewById(R.id.plantatoin_list_layout);
        ll_plantation_upload = (LinearLayout) findViewById(R.id.ll_plantation_upload);
        ll_plantation_report_data = (LinearLayout) findViewById(R.id.ll_plantation_report_data);
        plantatoin_list_layout1 = (LinearLayout) findViewById(R.id.plantatoin_list_layout1);
        ll_plantation_upload1 = (LinearLayout) findViewById(R.id.ll_plantation_upload1);

        rl_sync_data = (RelativeLayout) findViewById(R.id.rl_sync_data);

        btn_logout = (ImageView) findViewById(R.id.btn_logout);

        tv_userName = (TextView) findViewById(R.id.tv_username);
        pending_sudhar_plantation = (TextView) findViewById(R.id.pending_sudhar_plantation);
        pending_sudhar_plantation1 = (TextView) findViewById(R.id.pending_sudhar_plantation1);
        pending_upload_pond = (TextView) findViewById(R.id.pending_upload_pond);
        rl_sync_bendata = (RelativeLayout) findViewById(R.id.rl_sync_bendata);
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

        rl_sync_data.setOnClickListener(this);

        dataBaseHelper=new DataBaseHelper(this);

        getUserDetail();
        rl_sync_bendata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BenDataList = dataBaseHelper.getWdcBenList_tosyncNew( );
                if (BenDataList.size()>0){
                    new AlertDialog.Builder(WdcDashBoardActivity.this)
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
                    deleteStudentdetails();
                    Toast.makeText(getApplicationContext(),"सत्यापित करे बटन पे क्लिक करके आंगनबाड़ी का चयन करे |",Toast.LENGTH_LONG).show();
                }

            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // showPending();
        // handlePlantationMenu();
    }

    private void showPending(){
        String plantaionCount = dataBaseHelper.getPlantationUpdatedDataCount();

        pending_sudhar_plantation1.setText(plantaionCount);

    }

    private void confirmLogout(){
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
        Intent intent = new Intent(this,
                LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void logout() {
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

    private void getUserDetail(){

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

    private String capitalizeFirstCharacter(String name){
        String[] nameList = name.trim().split(" ");
        String orderName = "";

        for(String item: nameList){
            item = item.toLowerCase();
        }

        return orderName;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        switch (id){
            case R.id.plantatoin_list_layout1:

                if(userInfo.getUserrole().equalsIgnoreCase("AWC")) {
                    Intent intent = new Intent(WdcDashBoardActivity.this, WdcShowDataActivity.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("AWCID", userInfo.getAwc_Code());
                    intent.putExtra("AWCIDName", userInfo.getAwc_Name());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WdcDashBoardActivity.this, WdcShowDataActivity.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }
                break;
            case R.id.ll_plantation_upload1:
                if(userInfo.getUserrole().equalsIgnoreCase("AWC")) {
                    Intent intent = new Intent(WdcDashBoardActivity.this, ShowUploadData.class);
                    intent.putExtra("SectorCode", userInfo.getSector_Code());
                    intent.putExtra("AWCID", userInfo.getAwc_Code());
                    intent.putExtra("AWCIDName", userInfo.getAwc_Name());
                    intent.putExtra("Role", userInfo.getUserrole());

                    intent.putExtra("projectCode", userInfo.getProject_Code());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WdcDashBoardActivity.this, ShowUploadData.class);
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

    private void handlePlantationMenu(){
        if(isShowPlantation){
            iv_plantation_drpdwn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp));
            ll_plantatoin_menu.setVisibility(View.VISIBLE);
        }else{
            iv_plantation_drpdwn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp));
            ll_plantatoin_menu.setVisibility(View.GONE);
        }
    }

    private class SyncPlantationData extends AsyncTask<String, Void, ArrayList<PlantationDetail>> {
        private final ProgressDialog dialog = new ProgressDialog(WdcDashBoardActivity.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<PlantationDetail> doInBackground(String...arg)
        {
            return WebServiceHelper.getPlantationData(userInfo.getDistrictCode(), userInfo.getUserrole());
        }

        @Override
        protected void onPostExecute(ArrayList<PlantationDetail> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

            long i= helper.setPlantationDataToLocal(result);
            //showPending();
            if(i>0)
            {
                Toast.makeText(getApplicationContext(), "Plantation Data Synced Successfully",Toast.LENGTH_SHORT).show();
                //new SyncWardData().execute("");
            }
            else
            {
                Toast.makeText(getApplicationContext(), "कोई डेटा नहीं मिला!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SyncAWC extends AsyncTask<String, Void, ArrayList<AWC_Model>>
    {
        private final ProgressDialog dialog = new ProgressDialog(WdcDashBoardActivity.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<AWC_Model> doInBackground(String...arg)
        {
            Log.d("dfcgg",userInfo.getSector_Code()+"   "+userInfo.getProject_Code());
            return WebServiceHelper.getAWCList(userInfo.getSector_Code(),userInfo.getProject_Code());
        }

        @Override
        protected void onPostExecute(ArrayList<AWC_Model> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            Log.d("dgvgfcv",result.toString());

            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

            long i= helper.setAWCDataToLocal(result,userInfo.getSector_Code());

            if(i>0)
            {
                Toast.makeText(getApplicationContext(), " Awc Data Synced Successfully",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to update Awc",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class SyncWardData extends AsyncTask<String, Void, ArrayList<ward>> {
        private final ProgressDialog dialog = new ProgressDialog(WdcDashBoardActivity.this);

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


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.icds_logo)
                .setTitle(R.string.appTitle)
                .setMessage("Are you sure you want to exit the app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i=new Intent(WdcDashBoardActivity.this,ChooseSchemeActivity.class);
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
        showPending();

    }

    public  void deleteStudentdetails()
    {

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        long c = db.delete("wdcBenDetails", null, null);
        if(c>0)
        {
            Log.e("wdcBenDetails","success");
        }
        else
        {
            Log.e("wdcBenDetails","failed");
        }

    }

}
