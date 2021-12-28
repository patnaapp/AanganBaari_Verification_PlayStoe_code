package bih.in.aanganwadiBenVerification.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.adapter.BeneficiaryAdaptor;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.Block;
import bih.in.aanganwadiBenVerification.entity.CatWiseBenStatus;
import bih.in.aanganwadiBenVerification.entity.District;
import bih.in.aanganwadiBenVerification.web_services.WebServiceHelper;


public class BeneficiaryStatusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listView;
    BeneficiaryAdaptor adaptor_showedit_listDetail;
    Spinner spin_school,spin_block,spin_district;

    ProgressDialog dialog;
    DataBaseHelper dataBaseHelper;
    ArrayList<CatWiseBenStatus> data = new ArrayList<CatWiseBenStatus>();
    ArrayList<CatWiseBenStatus> schoolList = new ArrayList<CatWiseBenStatus>();
    String listid;
    TextView tv_Norecord;
    Button btn_add_pond,btn_view_pond_map;
    LinearLayout ll_district_section,ll_block_section,ll_school_section;

    ArrayList<Block> blockList = new ArrayList<Block>();
    ArrayList<District> districtList = new ArrayList<District>();

    ArrayList<String> blockNameArray;
    ArrayList<String> districtNameArray;
    ArrayList<String> schoolNameArray;
    ArrayAdapter<String> blockadapter;
    ArrayAdapter<String> districtadapter;
    ArrayAdapter<String> schooladapter;
    EditText edt_school_dise;
    String disecode="";



    //UserDetails userInfo;

    String blockCode,blockName,districtCode,districtName,schoolCode,schoolName="", userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_status);

        listView=(ListView)findViewById(R.id.listviewshow);
        tv_Norecord=(TextView) findViewById(R.id.tv_Norecord);

        spin_block=(Spinner) findViewById(R.id.spin_block);
        spin_school=(Spinner) findViewById(R.id.spin_school);
        spin_district=(Spinner) findViewById(R.id.spin_district);

        ll_district_section=(LinearLayout) findViewById(R.id.ll_district_section);
        ll_block_section=(LinearLayout) findViewById(R.id.ll_block_section);
        ll_school_section=(LinearLayout) findViewById(R.id.ll_school_section);
        edt_school_dise= findViewById(R.id.edt_school_dise);
        edt_school_dise.addTextChangedListener(inputTextWatcher);

        ll_school_section.setVisibility(View.GONE);

        spin_block.setOnItemSelectedListener(this);
        spin_school.setOnItemSelectedListener(this);
        spin_district.setOnItemSelectedListener(this);

        dataBaseHelper = new DataBaseHelper(this);

        loadDistrictSpinner();

    }

    public void populateLocalData(){
        //Call api or fetch data from load database
//        data.clear();
//        schoolList.clear();

        //schoolList.add(new CatWiseBenStatus("1", "230", "Patna", "1234", "Patna", "A.M.S KAKURWA", "1", "10071205901", "674", "351", "423", "674", "351", "423"));
        //schoolList.add(new CatWiseBenStatus("2", "230", "Patna", "1234", "Patna", "AL SHAMS MILLIA COLLEGE ARARIA", "1", "10071205901", "874", "541", "231", "432", "234", "123"));
        data = new ArrayList<CatWiseBenStatus>(schoolList);

        if(data != null && data.size()> 0){

            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.invalidate();
            //((DraftAdapter)dataListView.getAdapter()).notifyDataSetChanged();
            adaptor_showedit_listDetail = new BeneficiaryAdaptor(BeneficiaryStatusActivity.this, data);
            listView.setAdapter(adaptor_showedit_listDetail);
        }else{
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }

    public void loadSchoolSpinner()
    {
        // panchayatList = dataBaseHelper.getPanchayt(blockCode);
        schoolNameArray = new ArrayList<String>();
        schoolNameArray.add("-select-");
        int i = 0;
        for (CatWiseBenStatus list : schoolList)
        {
            schoolNameArray.add(list.getSchoolName());
            i++;
        }
        schooladapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schoolNameArray);
        schooladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_school.setAdapter(schooladapter);
    }

    public void loadBlockSpinner()
    {
        blockList = dataBaseHelper.getBlock(districtCode);
        blockNameArray = new ArrayList<String>();
        blockNameArray.add("-select-");
        int i = 0;
        for (Block block_list : blockList)
        {
            blockNameArray.add(block_list.getBlockName());
            i++;
        }
        blockadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, blockNameArray);
        blockadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_block.setAdapter(blockadapter);
    }

    public void loadDistrictSpinner()
    {
        districtList = dataBaseHelper.getDistrict();
        districtNameArray = new ArrayList<String>();
        districtNameArray.add("-select-");
        int i = 0;
        for (District district_list : districtList)
        {
            districtNameArray.add(district_list.get_DistName());
            i++;
        }
        districtadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districtNameArray);
        districtadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_district.setAdapter(districtadapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(blockCode != null)
        {
            if(data != null && data.size()> 0)
            {
                tv_Norecord.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.invalidate();
                //((DraftAdapter)dataListView.getAdapter()).notifyDataSetChanged();
                adaptor_showedit_listDetail = new BeneficiaryAdaptor(this, data);
                listView.setAdapter(adaptor_showedit_listDetail);
             }
            else
            {
                listView.setVisibility(View.GONE);
                tv_Norecord.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_district:
                int pos2 = position;
                if (pos2 > 0)
                {
                    districtCode = districtList.get(pos2 - 1).get_DistCode().trim();
                    districtName = districtList.get(pos2 - 1).get_DistName().trim();
                    Log.d("district", "District Code: "+districtCode);
                    loadBlockSpinner();
                }
                break;

            case R.id.spin_block:
                int pos1 = position;
                if (pos1 > 0)
                {
                    blockCode = blockList.get(pos1 - 1).getBlockCode().trim();
                    blockName = blockList.get(pos1 - 1).getBlockName().trim();
                    Log.d("block", "Block Code: "+blockCode);

                    //populateLocalData();
                    //loadSchoolSpinner();
//                    new SyncBenStatusdata(districtCode, blockCode).execute("");
//                    ll_school_section.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.spin_school:
                int pos = position;
                if (pos > 0) {
                    // schoolCode = schoolList.get(pos - 1).getSchoolId().trim();
                    schoolName = schoolList.get(pos - 1).getSchoolName().trim();
                    CatWiseBenStatus school = schoolList.get(pos - 1);
                    data.clear();
                    data.add(school);
                    adaptor_showedit_listDetail.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick_Finish(View v)
    {
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private class SyncBenStatusdata extends AsyncTask<String, Void, ArrayList<CatWiseBenStatus>> {
        private final ProgressDialog dialog = new ProgressDialog(BeneficiaryStatusActivity.this);
        String districtCode;
        String blockCode;
        String dise;

        public SyncBenStatusdata(String districtCode, String blockCode,String dise){
            this.districtCode = districtCode;
            this.blockCode = blockCode;
            this.dise = dise;
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<CatWiseBenStatus> doInBackground(String...arg) {
            //String blockId = userInfo.getBlockCode();

            // return WebServiceHelper.getCategoryWiseBenData(districtCode, blockCode, "0");
            return WebServiceHelper.getCategoryWiseBenData(districtCode, blockCode, dise);

        }

        @Override
        protected void onPostExecute(ArrayList<CatWiseBenStatus> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if(result.size()>0)
            {
                data.clear();
                schoolList.clear();
                schoolList = result;
                populateLocalData();
                loadSchoolSpinner();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No Record Found",Toast.LENGTH_SHORT).show();
                tv_Norecord.setVisibility(View.VISIBLE);
            }
        }
    }


    private TextWatcher inputTextWatcher = new TextWatcher()
    {

        public void beforeTextChanged(CharSequence s, int start, int count,int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (edt_school_dise.getText().toString().length() == 11)
            {
                disecode = edt_school_dise.getText().toString();
                if (districtCode!=null && blockCode!=null)
                {
                    new SyncBenStatusdata(districtCode, blockCode,disecode).execute();
                }
                // new SyncBenStatusdata(districtCode, blockCode,disecode).execute("");
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    };

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i=new Intent(BeneficiaryStatusActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}
