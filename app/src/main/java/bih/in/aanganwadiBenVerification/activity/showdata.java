
package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.adapter.BenWardAdapter;
import bih.in.aanganwadiBenVerification.adapter.StudentListAdaptor;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.utility.Utiilties;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;
import android.view.ViewGroup.LayoutParams;

public class showdata extends Activity {
    ArrayList<String>data=new ArrayList<>();
    StudentListAdaptor adaptor_showedit_listDetail;
    ListView listView;
    Spinner spn_labhartiType,spn_AngnwadiList;
    ArrayAdapter vanPoshakhoAdaptor;
    String SectorCode="",ProjectCode="",AWCID="",AWCIDName="",URole="";
    DataBaseHelper dataBaseHelper;

    ArrayList<AWC_Model> AwcList = new ArrayList<AWC_Model>();
    ArrayList<String> AWC_ModelArray;
    ArrayAdapter<String> AWCadapter;
    ArrayList<BenList>BenDataList=new ArrayList<>();
    PopupWindow popupWindow;
    String BenType="";
    TextView upd_status,tv_Norecord;

    Button buton;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);

        listView=(ListView)findViewById(R.id.listview_showdata);
        tv_Norecord=(TextView) findViewById(R.id.tv_Norecord);
        spn_labhartiType=(Spinner)findViewById(R.id.spin_labharthitype) ;
        spn_AngnwadiList=(Spinner)findViewById(R.id.spin_forest_poshak_pant_no) ;
        buton=(Button )findViewById(R.id.buton) ;
        upd_status=(TextView)findViewById(R.id.upd_status);
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

        spn_labhartiType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i>0) {
                    BenType = data.get(i);
                    // Log.d("TAGDATA",BenType);
                    if(!AWCID.equalsIgnoreCase(""))
                    {
                        if (URole.equalsIgnoreCase("AWC"))
                        {
                            if (data.get(i).equalsIgnoreCase("गर्भवती महिला"))
                            {
                                BenDataList.clear();
                                BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                if (BenDataList.size() != 0)
                                {
                                    tv_Norecord.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "1",data.get(i),AWCID);
                                    listView.setAdapter(adaptor_showedit_listDetail);
                                    adaptor_showedit_listDetail.notifyDataSetChanged();

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                }
                            }
                            else if (data.get(i).equalsIgnoreCase("स्तनपान कराने वाली महिला"))
                            {
                                BenDataList.clear();
                                BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                if (BenDataList.size() != 0)
                                {
                                    tv_Norecord.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);

                                    adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "2",data.get(i),AWCID);
                                    listView.setAdapter(adaptor_showedit_listDetail);
                                    adaptor_showedit_listDetail.notifyDataSetChanged();

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                }
                            }
                            else if (data.get(i).equalsIgnoreCase("आंगनबाड़ी केंद्र के बच्चे"))
                            {
                                BenDataList.clear();
                                BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                if (BenDataList.size() != 0)
                                {

                                    tv_Norecord.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3",data.get(i),AWCID);
                                    listView.setAdapter(adaptor_showedit_listDetail);
                                    adaptor_showedit_listDetail.notifyDataSetChanged();

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                }
                            }
                            else if (BenType.equalsIgnoreCase("--select--"))
                            {
                                BenDataList.clear();
                                tv_Norecord.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3","","");
                                listView.setAdapter(adaptor_showedit_listDetail);
                                adaptor_showedit_listDetail.notifyDataSetChanged();

                                Toast.makeText(getApplicationContext(), "Please Choose One Type", Toast.LENGTH_LONG).show();

                            }
                        }
                        else if (URole.equalsIgnoreCase("LS"))
                        {
                            BenDataList.clear();
                            BenDataList = dataBaseHelper.getBenListByAwc(AWCID);
                            if (BenDataList.size() != 0)
                            {
                                if (data.get(i).equalsIgnoreCase("गर्भवती महिला"))
                                {
                                    BenDataList.clear();

                                    BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                    if (BenDataList.size() != 0)
                                    {
                                        tv_Norecord.setVisibility(View.GONE);
                                        listView.setVisibility(View.VISIBLE);
                                        adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "1",data.get(i),AWCID);
                                        listView.setAdapter(adaptor_showedit_listDetail);
                                        adaptor_showedit_listDetail.notifyDataSetChanged();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), " इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else if (data.get(i).equalsIgnoreCase("स्तनपान कराने वाली महिला"))
                                {
                                    BenDataList.clear();
                                    BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                    if (BenDataList.size() != 0)
                                    {

                                        tv_Norecord.setVisibility(View.GONE);
                                        listView.setVisibility(View.VISIBLE);
                                        adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "2",data.get(i),AWCID);
                                        listView.setAdapter(adaptor_showedit_listDetail);
                                        adaptor_showedit_listDetail.notifyDataSetChanged();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                    }
                                } else if (data.get(i).equalsIgnoreCase("आंगनबाड़ी केंद्र के बच्चे")) {
                                    BenDataList.clear();
                                    BenDataList = dataBaseHelper.getBenList(data.get(i), AWCID);
                                    if (BenDataList.size() != 0) {
                                        tv_Norecord.setVisibility(View.GONE);
                                        listView.setVisibility(View.VISIBLE);

                                        adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3",data.get(i),AWCID);
                                        listView.setAdapter(adaptor_showedit_listDetail);
                                        adaptor_showedit_listDetail.notifyDataSetChanged();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "इस आंगनवाड़ी और लाभारथी प्रकार के लिए कोई डेटा नहीं है ", Toast.LENGTH_LONG).show();
                                    }
                                } else if (BenType.equalsIgnoreCase("--select--")) {
                                    BenDataList.clear();

                                    tv_Norecord.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    //  adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3");
                                    listView.setAdapter(adaptor_showedit_listDetail);
                                    adaptor_showedit_listDetail.notifyDataSetChanged();

                                    Toast.makeText(getApplicationContext(), "Please Choose One Type", Toast.LENGTH_LONG).show();

                                }
                            }else {
                                new LoadBeneficiary().execute();
                            }
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"कृपया आंगनवाड़ी केंद्र का चयन करें",Toast.LENGTH_LONG).show();
                    }
                }else {
                    BenDataList.clear();
                    tv_Norecord.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    // adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3");
                    listView.setAdapter(adaptor_showedit_listDetail);
                    adaptor_showedit_listDetail.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spn_AngnwadiList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BenDataList.clear();
                adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "1","","");
                listView.setAdapter(adaptor_showedit_listDetail);
                adaptor_showedit_listDetail.notifyDataSetChanged();
                spn_labhartiType.setSelection(0);
                if (i > 0) {
                    AWCID = AwcList.get(i - 1).getAWC_CODE();
                    AWCIDName = AwcList.get(i - 1).getAWC_NAME();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        if((!AWCID.equalsIgnoreCase(""))&&(URole.equalsIgnoreCase("AWC"))){
            Log.d("TAGOPTION1",AWCID);
            BenDataList=dataBaseHelper.getBenListByAwc(AWCID);
            if(BenDataList.size()==0)
                new LoadBeneficiary().execute();
        }
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToServer();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopUp(BenDataList.get(i));
            }
        });

    }


    public void loadDistrictSpinner() {
        dataBaseHelper=new DataBaseHelper(showdata.this);
        AwcList = dataBaseHelper.getAwcLiost(SectorCode);
        AWC_ModelArray = new ArrayList<String>();
        AWC_ModelArray.add("-select-");
        int i = 0;
        for (AWC_Model district_list : AwcList) {
            AWC_ModelArray.add(district_list.getAWC_NAME());
            i++;
        }
        AWCadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AWC_ModelArray);
        AWCadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_AngnwadiList.setAdapter(AWCadapter);
        if((!AWCID.equalsIgnoreCase(""))&&(URole.equalsIgnoreCase("AWC"))){
            spn_AngnwadiList.setEnabled(false);
            spn_AngnwadiList.setClickable(false);
            spn_AngnwadiList.setSelection(((ArrayAdapter<String>)spn_AngnwadiList.getAdapter()).getPosition(AWCIDName));
        }


    }

    private class LoadBeneficiary extends AsyncTask<String, Void, ArrayList<BenList>> {
        private final ProgressDialog dialog = new ProgressDialog(showdata.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<BenList> doInBackground(String...arg) {

            Log.d("TAGDATA3",AWCID);
            return WebServiceHelper.getBenificiaryList(AWCID);

        }

        @Override
        protected void onPostExecute(ArrayList<BenList> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            //  Log.d("dgvgfcvqqq", result);
            if(result!=null) {
                if (result.size() > 0) {

                    DataBaseHelper helper = new DataBaseHelper(getApplicationContext());

                    long i = helper.setBenLocal(result, AWCID);
                    BenDataList = result;
                    if (URole.equalsIgnoreCase("Ls")) {

                        if (BenType.equalsIgnoreCase("गर्भवती महिला")) {
                            tv_Norecord.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            BenDataList = dataBaseHelper.getBenList(BenType, AWCID);
                            adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "1",BenType,AWCID);
                            listView.setAdapter(adaptor_showedit_listDetail);
                            adaptor_showedit_listDetail.notifyDataSetChanged();

                        } else if (BenType.equalsIgnoreCase("स्तनपान कराने वाली महिला")) {
                            tv_Norecord.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            BenDataList = dataBaseHelper.getBenList(BenType, AWCID);
                            adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "2",BenType,AWCID);
                            listView.setAdapter(adaptor_showedit_listDetail);
                            adaptor_showedit_listDetail.notifyDataSetChanged();

                        } else if (BenType.equalsIgnoreCase("आंगनबाड़ी केंद्र के बच्चे")) {
                            tv_Norecord.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            BenDataList = dataBaseHelper.getBenList(BenType, AWCID);
                            adaptor_showedit_listDetail = new StudentListAdaptor(showdata.this, BenDataList, "3",BenType,AWCID);
                            listView.setAdapter(adaptor_showedit_listDetail);
                            adaptor_showedit_listDetail.notifyDataSetChanged();


                        } else if (BenType.equalsIgnoreCase("--select--")) {
                            Toast.makeText(getApplicationContext(), "Please Choose One Type", Toast.LENGTH_LONG).show();

                        }
                    }

                    if (i > 0) {
                        Toast.makeText(getApplicationContext(), " लाभार्थी डेटा सफलतापूर्वक सिंक किया गया ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to update Beneficiary", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),"इस आंगनवाड़ी में कोई डेटा नहीं है |",Toast.LENGTH_LONG).show();
                }
            }


        }
    }


    public void uploadToServer(){

        if (Utiilties.isOnline(this)) {

            android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(
                    this);
            ab.setTitle("Upload");
            ab.setMessage("Do You Want to Upload Data");
            ab.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {

                    dialog.dismiss();

                }
            });

            ab.setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                    DataBaseHelper dbHelper = new DataBaseHelper(showdata.this);

                    ArrayList<BenList> dataProgress = dbHelper.getBenList(AWCID);

                    if (dataProgress.size() > 0) {


                        new UploadTeacherDetails(dataProgress).execute();



                    } else {
                        Toast.makeText(getApplicationContext(), "No Data to Upload", Toast.LENGTH_SHORT).show();

                    }


                }
            });

            ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            ab.show();

        }
    }

    private class UploadTeacherDetails extends AsyncTask<String, Void, String> {

        ArrayList<BenList> data;
        //SchoolFacultyDetailEntity data;
        String _id;
        private final ProgressDialog dialog = new ProgressDialog(showdata.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(showdata.this).create();


        UploadTeacherDetails(ArrayList<BenList> data) {
            this.data = data;
            //  this._id = data.getTeacher_Number();

        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");

            this.dialog.show();

        }

        @Override
        protected String doInBackground(String... param) {
           /* String devicename = getDeviceName();
            String app_version = getAppVersion();
            boolean isTablet = isTablet(showdata.this);
            if (isTablet) {
                devicename = "Tablet::" + devicename;
                Log.e("DEVICE_TYPE", "Tablet");
            } else {
                devicename = "Mobile::" + devicename;
                Log.e("DEVICE_TYPE", "Mobile");
            }*/

            String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            String res = WebServiceHelper.UploadTeacherDetails(getApplicationContext(),data, "", "",username);
            return res;

        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {
                String string = result;


                if (result.equals("1"))
                {
                   /* long c = databaseHelper.deletePrincipal(diseCode);
                    long c1;
                    if (c>0){
                        c1=databaseHelper.deleteTeachersl(diseCode);
                        if (c1>0){

                            updatePrincipalDetail(diseCode,"Y");*/
                    alterVerifiedData("N");
                    AlertDialog.Builder builder = new AlertDialog.Builder(showdata.this);
                    builder.setIcon(R.drawable.aanganlogo);
                    builder.setTitle("Success!!");
                    builder.setMessage("Verified Records Uploded Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()

                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            dialog.dismiss();

                        }
                    });
                    AlertDialog dialog = builder.create();
                    if (!showdata.this.isFinishing())
                    {
                        dialog.show();
                    }


                }
                else if (result.equals("0"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(showdata.this);

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
                    if (!showdata.this.isFinishing())
                    {
                        dialog.show();
                    }
                }


                else {
                    Toast.makeText(getApplicationContext(), "Uploading data failed ", Toast.LENGTH_SHORT).show();
                }

            } else {
                //chk_msg_OK_networkdata("Result:null..Uploading failed.Please Try Again Later");
                Toast.makeText(getApplicationContext(), "Result Null:Uploading failed..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void showPopUp(BenList benDataList) {

        LayoutInflater layoutInflater = (LayoutInflater) showdata.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.activity_bendetail, null);
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


        txt_husname.setText(benDataList.getHusband_Name());
        txt_wifname.setText(benDataList.getWife_Name());
        if (!benDataList.getChild_Name() .equalsIgnoreCase("NA")){
            txt_childname.setText(benDataList.getChild_Name());
        }else {
            lin_childname.setVisibility(View.GONE);
        }
        if(!benDataList.getAge().equalsIgnoreCase("NA")) {
            txt_childage.setText(benDataList.getAge());
        }else {
            lin_childage.setVisibility(View.GONE);
        }
        if(!benDataList.getAadhar_Num().equalsIgnoreCase("anyType{}")) {
            txt_aadhar.setText(benDataList.getAadhar_Num());
        }else {
            txt_aadhar.setText("NA");
        }
        txt_acount.setText(benDataList.getAccount_Num());

        txt_ifsc.setText(benDataList.getIFSC());
        txt_benType.setText(benDataList.getLbharthi_Type());
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);


        //display the popup window
        popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow .dismiss();
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
        long  c = db.update("BenDetails", values,null,null);
        Log.e("ISUPDATED",""+c);
    }


}
