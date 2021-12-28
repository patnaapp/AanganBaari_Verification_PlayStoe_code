package bih.in.aanganwadiBenVerification;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.activity.WdcDashBoardActivity;
import bih.in.aanganwadiBenVerification.adapter.WdcShowAdaptor;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import bih.in.aanganwadiBenVerification.utility.Utiilties;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;

public class WdcShowDataActivity extends Activity
{
    ArrayList<String> data=new ArrayList<>();
    WdcShowAdaptor adaptor_showedit_listDetail;
    ListView listView;
    Spinner spn_labhartiType,spn_AngnwadiList;
    ArrayAdapter vanPoshakhoAdaptor;
    String SectorCode="",ProjectCode="",AWCID="",AWCIDName="",URole="";
    DataBaseHelper dataBaseHelper;

    ArrayList<AWC_Model> AwcList = new ArrayList<AWC_Model>();
    ArrayList<String> AWC_ModelArray;
    ArrayAdapter<String> AWCadapter;
    ArrayList<wdcBenList>BenDataList=new ArrayList<>();
    PopupWindow popupWindow;
    String BenType="",version="";
    TextView upd_status,tv_Norecord;
    EditText et_account;


    Button buton;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdc_data);

        listView=(ListView)findViewById(R.id.listview_showdata);
        spn_labhartiType=(Spinner)findViewById(R.id.spin_labharthitype) ;
        spn_AngnwadiList=(Spinner)findViewById(R.id.spin_forest_poshak_pant_no) ;
        buton=(Button )findViewById(R.id.buton) ;
        upd_status=(TextView)findViewById(R.id.upd_status);
        tv_Norecord=(TextView)findViewById(R.id.tv_Norecord);
        et_account=(EditText) findViewById(R.id.et_account);
        et_account.addTextChangedListener(inputTextWatcher1);
        try
        {
            SectorCode=getIntent().getStringExtra("SectorCode");
            ProjectCode=getIntent().getStringExtra("projectCode");
            URole=getIntent().getStringExtra("Role");
            if(URole.equalsIgnoreCase("AWC"))
            {
                AWCID = getIntent().getStringExtra("AWCID");
                AWCIDName=getIntent().getStringExtra("AWCIDName");

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        loadDistrictSpinner();
        data.add("--Select--");
        data.add("गर्भवती महिला");
        data.add("स्तनपान कराने वाली महिला");
        data.add("आंगनबाड़ी केंद्र के बच्चे");
        vanPoshakhoAdaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
        vanPoshakhoAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_labhartiType.setAdapter(vanPoshakhoAdaptor);


        if(!AWCID.equalsIgnoreCase(""))
        {
            if (URole.equalsIgnoreCase("AWC"))
            {

                BenDataList.clear();
                BenDataList = dataBaseHelper.getWdcBenList( AWCID);
                if (BenDataList.size() != 0)
                {
                    adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "3", "", AWCID);
                    listView.setAdapter(adaptor_showedit_listDetail);
                    adaptor_showedit_listDetail.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                }

            }
        }


        spn_AngnwadiList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {

                if (i > 0)
                {
                    AWCID = AwcList.get(i - 1).getAWC_CODE();
                    AWCIDName = AwcList.get(i - 1).getAWC_NAME();
                    BenDataList = dataBaseHelper.getWdcBenListforshowlist(AWCID);
                    if (BenDataList.size() == 0)
                    {
                        new LoadBeneficiary().execute();
                    }
                    else
                    {
                        if(BenDataList != null && BenDataList.size()> 0)
                        {
                            tv_Norecord.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "3", "", AWCID);
                            listView.setAdapter(adaptor_showedit_listDetail);
                            adaptor_showedit_listDetail.notifyDataSetChanged();
                        }
                        else
                        {
                            listView.setVisibility(View.GONE);
                            tv_Norecord.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else
                {
                    BenDataList.clear();
                    adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "3","","");
                    listView.setAdapter(adaptor_showedit_listDetail);
                    adaptor_showedit_listDetail.notifyDataSetChanged();

                    // spn_labhartiType.setSelection(0);
                }

               /* BenDataList.clear();
                adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "1","","");
                listView.setAdapter(adaptor_showedit_listDetail);
                adaptor_showedit_listDetail.notifyDataSetChanged();
                spn_labhartiType.setSelection(0);  */

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        if((!AWCID.equalsIgnoreCase(""))&&(URole.equalsIgnoreCase("AWC")))
        {
            Log.d("TAGOPTION1",AWCID);
            BenDataList=dataBaseHelper.getWdcBenListforshowlist(AWCID);
            if(BenDataList.size()==0)
                new LoadBeneficiary().execute();
        }
        buton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                uploadToServer();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                showPopUp(BenDataList.get(i));
            }
        });

    }


    public void loadDistrictSpinner()
    {
        dataBaseHelper=new DataBaseHelper(WdcShowDataActivity.this);
        AwcList = dataBaseHelper.getAwcLiost(SectorCode);
        AWC_ModelArray = new ArrayList<String>();
        AWC_ModelArray.add("-select-");
        int i = 0;
        for (AWC_Model district_list : AwcList)
        {
            AWC_ModelArray.add(district_list.getAWC_NAME());
            i++;
        }
        AWCadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AWC_ModelArray);
        AWCadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_AngnwadiList.setAdapter(AWCadapter);
        if((!AWCID.equalsIgnoreCase(""))&&(URole.equalsIgnoreCase("AWC")))
        {
            spn_AngnwadiList.setEnabled(false);
            spn_AngnwadiList.setClickable(false);
            spn_AngnwadiList.setSelection(((ArrayAdapter<String>)spn_AngnwadiList.getAdapter()).getPosition(AWCIDName));
        }


    }

    private class LoadBeneficiary extends AsyncTask<String, Void, ArrayList<wdcBenList>>
    {
        private final ProgressDialog dialog = new ProgressDialog(WdcShowDataActivity.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<wdcBenList> doInBackground(String...arg)
        {
            //  Log.d("TAGDATA3",AWCID);
            return WebServiceHelper.getWdcBenificiaryList(AWCID);

        }

        @Override
        protected void onPostExecute(ArrayList<wdcBenList> result)
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

                    long i = helper.setWDCBenLocal(result, AWCID);
                    BenDataList = result;

                    if (URole.equalsIgnoreCase("Ls"))
                    {
                        BenDataList = dataBaseHelper.getWdcBenList(AWCID);
                        if(BenDataList != null && BenDataList.size()> 0){
                            tv_Norecord.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                            adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, result, "3",BenType,AWCID);
                            listView.setAdapter(adaptor_showedit_listDetail);
                            adaptor_showedit_listDetail.notifyDataSetChanged();
                        }
                        else {
                            listView.setVisibility(View.GONE);
                            tv_Norecord.setVisibility(View.VISIBLE);
                        }


                    }

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
                    listView.setVisibility(View.GONE);
                    tv_Norecord.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"इस आंगनवाड़ी में कोई डेटा नहीं है |",Toast.LENGTH_LONG).show();
                }
            }


        }
    }


    public void uploadToServer()
    {

        if (Utiilties.isOnline(this))
        {

            android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(this);
            ab.setTitle("Upload");
            ab.setMessage("Do You Want to Upload Data");
            ab.setPositiveButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int whichButton)
                {

                    dialog.dismiss();

                }
            });

            ab.setNegativeButton("Yes", new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface dialog, int whichButton)
                {
                    dialog.dismiss();
                    DataBaseHelper dbHelper = new DataBaseHelper(WdcShowDataActivity.this);

                    ArrayList<wdcBenList> dataProgress = dbHelper.getWdcBenList_toupload(AWCID);

                    if (dataProgress.size() > 0)
                    {
                        new UploadTeacherDetails(dataProgress).execute();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No Data to Upload", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            ab.show();

        }
    }

    private class UploadTeacherDetails extends AsyncTask<String, Void, String>
    {

        ArrayList<wdcBenList> data;
        //SchoolFacultyDetailEntity data;
        String _id;
        private final ProgressDialog dialog = new ProgressDialog(WdcShowDataActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(WdcShowDataActivity.this).create();


        UploadTeacherDetails(ArrayList<wdcBenList> data)
        {
            this.data = data;
            //  this._id = data.getTeacher_Number();
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

            String devicename = getDeviceName();
            String app_version = getAppVersion();
            boolean isTablet = isTablet(WdcShowDataActivity.this);
            if (isTablet) {
                devicename = "Tablet::" + devicename;
                Log.e("DEVICE_TYPE", "Tablet");
            } else {
                devicename = "Mobile::" + devicename;
                Log.e("DEVICE_TYPE", "Mobile");
            }

            String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            String res = WebServiceHelper.UploadwdcTeacherDetails(getApplicationContext(),data, app_version, "",username);
            return res;

        }

        @Override
        protected void onPostExecute(String result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null)
            {
                String string = result;


                if (result.equals("Success"))
                {

                   /* long c = databaseHelper.deletePrincipal(diseCode);
                    long c1;
                    if (c>0){
                        c1=databaseHelper.deleteTeachersl(diseCode);
                        if (c1>0){
                         updatePrincipalDetail(diseCode,"Y");*/

                    //alterVerifiedData("N");
                    deletebendata();
                    AlertDialog.Builder builder = new AlertDialog.Builder(WdcShowDataActivity.this);
                    builder.setIcon(R.drawable.icdslogo);
                    builder.setTitle("Success!!");
                    builder.setMessage("Verified Records Uploded Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent i=new Intent(WdcShowDataActivity.this, WdcDashBoardActivity.class);
                            startActivity(i);
                            dialog.dismiss();

                        }
                    });
                    AlertDialog dialog = builder.create();
                    if (!WdcShowDataActivity.this.isFinishing())
                    {
                        dialog.show();
                    }

                }

                else if (result.equals("0"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WdcShowDataActivity.this);

                    builder.setMessage(result);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    if (!WdcShowDataActivity.this.isFinishing())
                    {
                        dialog.show();
                    }
                }


                else
                {
                    Toast.makeText(getApplicationContext(), "Uploading data failed ", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                //chk_msg_OK_networkdata("Result:null..Uploading failed.Please Try Again Later");
                Toast.makeText(getApplicationContext(), "Result Null:Uploading failed..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void showPopUp(wdcBenList benDataList)
    {
        LayoutInflater layoutInflater = (LayoutInflater) WdcShowDataActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.activity_wdc_bendetail, null);
        TextView txt_husname = (TextView) customView.findViewById(R.id.txt_husname);
        TextView txt_wifname = (TextView) customView.findViewById(R.id.txt_wifname);
        TextView txt_childname = (TextView) customView.findViewById(R.id.txt_childname);
        TextView txt_childage = (TextView) customView.findViewById(R.id.txt_childage);
        TextView txt_aadhar = (TextView) customView.findViewById(R.id.txt_aadhar);
        TextView txt_acount = (TextView) customView.findViewById(R.id.txt_acount);
        TextView txt_ifsc = (TextView) customView.findViewById(R.id.txt_ifsc);
        TextView txt_benType = (TextView) customView.findViewById(R.id.txt_benType);
        LinearLayout lin_childname = (LinearLayout) customView.findViewById(R.id.lin_childname);
        LinearLayout lin_childage = (LinearLayout) customView.findViewById(R.id.lin_childage);
        Button button = (Button) customView.findViewById(R.id.button);

        txt_husname.setText(benDataList.getBenFH_Name());
        txt_wifname.setText(benDataList.getBen_Name());
        if (!benDataList.getBen_Gender() .equalsIgnoreCase("NA"))
        {
            txt_childname.setText(benDataList.getBen_Gender());
        }
        else
        {
            lin_childname.setVisibility(View.GONE);
        }

        if(!benDataList.getBen_AadharCardNo().equalsIgnoreCase("anyType{}"))
        {
            txt_aadhar.setText(benDataList.getBen_AadharCardNo());
        }
        else
        {
            txt_aadhar.setText("NA");
        }
        txt_acount.setText(benDataList.getBen_AccountNo());

        txt_ifsc.setText(benDataList.getBen_IFSCCode());
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);

        //display the popup window
        popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popupWindow.dismiss();
            }
        });
    }

    public  void alterVerifiedData(String status)
    {
        DataBaseHelper localDB=new DataBaseHelper(getApplicationContext());
        ContentValues values = new ContentValues();
        // values.put("CreatedBy", diseCode);
        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");
        //  values.put("CreatedDate", cdate);
        values.put("IsBenUpdated","N");
        // String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("wdcBenDetails", values,null,null);
        Log.e("ISUPDATED",""+c);
    }


    public  void deletebendata()
    {
        DataBaseHelper localDB=new DataBaseHelper(getApplicationContext());
        ContentValues values = new ContentValues();
        // values.put("CreatedBy", diseCode);
        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");
        String[] whereArgsss = new String[]{"Y"};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.delete("wdcBenDetails","IsBenUpdated=?",whereArgsss);
        Log.e("ISUPDATED",""+c);
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {

            return model.toUpperCase();
        } else {

            return manufacturer.toUpperCase() + " " + model;
        }
    }
    public String getAppVersion(){
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                TextView tv = (TextView)getActivity().findViewById(R.id.txtVersion_1);
//                tv.setText(getActivity().getString(R.string.app_version) + version + " ");
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return version;
    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private TextWatcher inputTextWatcher1 = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (et_account.getText().length()==4) {
                populateLocalData();
            }
            else {
                BenDataList = dataBaseHelper.getWdcBenListforshowlist(AWCID);

                if(BenDataList != null && BenDataList.size()> 0)
                {
                    tv_Norecord.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "3", "", AWCID);
                    listView.setAdapter(adaptor_showedit_listDetail);
                    adaptor_showedit_listDetail.notifyDataSetChanged();
                }
                else
                {
                    listView.setVisibility(View.GONE);
                    tv_Norecord.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };


    public void populateLocalData()
    {
        BenDataList = dataBaseHelper.getWdcBenListforshowlistByFilter(AWCID,et_account.getText().toString());

        if(BenDataList != null && BenDataList.size()> 0)
        {
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adaptor_showedit_listDetail = new WdcShowAdaptor(WdcShowDataActivity.this, BenDataList, "3", "", AWCID);
            listView.setAdapter(adaptor_showedit_listDetail);
            adaptor_showedit_listDetail.notifyDataSetChanged();
        }
        else
        {
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }
}


