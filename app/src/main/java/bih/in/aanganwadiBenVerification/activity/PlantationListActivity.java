package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.adapter.PlantationAdapter;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.Block;
import bih.in.aanganwadiBenVerification.entity.District;
import bih.in.aanganwadiBenVerification.entity.PanchayatData;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;

public class PlantationListActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ListView listView;
    PlantationAdapter adaptor_showedit_listDetail;
    Spinner spin_panchayat,spin_block,spin_district;

    ProgressDialog dialog;
    DataBaseHelper dataBaseHelper;
    ArrayList<PlantationDetail> data;
    String listid;
    TextView tv_Norecord;
    Button btn_add_pond,btn_view_pond_map;
    LinearLayout ll_btn,ll_district_section,ll_block_section,ll_panchayat_section;

    ArrayList<Block> blockList = new ArrayList<Block>();
    ArrayList<District> districtList = new ArrayList<District>();
    ArrayList<String> blockNameArray;
    ArrayList<String> districtNameArray;
    ArrayAdapter<String> blockadapter;
    ArrayAdapter<String> districtadapter;

    ArrayList<PanchayatData> panchayatList = new ArrayList<PanchayatData>();
    ArrayList<String> panchayatNameArray;
    ArrayAdapter<String> panchayatadapter;


    //UserDetails userInfo;

    String blockCode,blockName,districtCode,districtName,panchayatCode,panchayatName="", userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantation_list);
        listView=(ListView)findViewById(R.id.listviewshow);
        tv_Norecord=(TextView) findViewById(R.id.tv_Norecord);

        spin_block=(Spinner) findViewById(R.id.spin_block);
        spin_panchayat=(Spinner) findViewById(R.id.spin_panchayat);
        spin_district=(Spinner) findViewById(R.id.spin_district);

        ll_district_section=(LinearLayout) findViewById(R.id.ll_district_section);
        ll_block_section=(LinearLayout) findViewById(R.id.ll_block_section);
        ll_panchayat_section=(LinearLayout) findViewById(R.id.ll_panchayat_section);

        spin_block.setOnItemSelectedListener(this);
        spin_panchayat.setOnItemSelectedListener(this);
        spin_district.setOnItemSelectedListener(this);

        dataBaseHelper = new DataBaseHelper(this);
        Intent intent = getIntent();
        blockCode = intent.getStringExtra("blockCode");
        districtCode = intent.getStringExtra("DistrictCode");
        panchayatCode = intent.getStringExtra("panchayatCode");
        userRole = intent.getStringExtra("userRole");
        Log.e("blockCode", blockCode);

        if(userRole.contains("STATE")){
            loadDistrictSpinner();
            ll_district_section.setVisibility(View.VISIBLE);
            ll_block_section.setVisibility(View.VISIBLE);
            ll_panchayat_section.setVisibility(View.VISIBLE);
        }else if(userRole.contains("DST")){
            loadBlockSpinner();
            ll_district_section.setVisibility(View.GONE);
            ll_block_section.setVisibility(View.VISIBLE);
            ll_panchayat_section.setVisibility(View.VISIBLE);
        }else if(userRole.contains("BLK")){
            loadPanchayatSpinner();
            ll_district_section.setVisibility(View.GONE);
            ll_block_section.setVisibility(View.GONE);
            ll_panchayat_section.setVisibility(View.VISIBLE);
        }else if(userRole.contains("PANADM")){
            populateLocalData();
            ll_district_section.setVisibility(View.GONE);
            ll_block_section.setVisibility(View.GONE);
            ll_panchayat_section.setVisibility(View.GONE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(), PlantationInspectionActivity.class);
                PlantationDetail detail = data.get(position);
                i.putExtra("data", detail);
                startActivity(i);
            }

        });
    }

    public void populateLocalData(){
        data=dataBaseHelper.getPlantationDetail(panchayatCode);

        if(data != null && data.size()> 0){
            Log.e("data", ""+data.size());
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adaptor_showedit_listDetail = new PlantationAdapter(PlantationListActivity.this, data);
            listView.setAdapter(adaptor_showedit_listDetail);
            adaptor_showedit_listDetail.notifyDataSetChanged();
        }else{
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }

    public void loadPanchayatSpinner() {
        panchayatList = dataBaseHelper.getPanchayt(blockCode);
        panchayatNameArray = new ArrayList<String>();
        panchayatNameArray.add("-select-");
        int i = 0;
        for (PanchayatData panchayat_list : panchayatList) {
            panchayatNameArray.add(panchayat_list.getPname());
            i++;
        }
        panchayatadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, panchayatNameArray);
        panchayatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_panchayat.setAdapter(panchayatadapter);
    }

    public void loadBlockSpinner() {
        blockList = dataBaseHelper.getBlock(districtCode);
        blockNameArray = new ArrayList<String>();
        blockNameArray.add("-select-");
        int i = 0;
        for (Block block_list : blockList) {
            blockNameArray.add(block_list.getBlockName());
            i++;
        }
        blockadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, blockNameArray);
        blockadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_block.setAdapter(blockadapter);
    }

    public void loadDistrictSpinner() {
        districtList = dataBaseHelper.getDistrict();
        districtNameArray = new ArrayList<String>();
        districtNameArray.add("-select-");
        int i = 0;
        for (District district_list : districtList) {
            districtNameArray.add(district_list.get_DistName());
            i++;
        }
        districtadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districtNameArray);
        districtadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_district.setAdapter(districtadapter);
    }

    private void setReportListViewData()
    {
        data=dataBaseHelper.getPlantationDetail(panchayatCode);
        //Toast.makeText(this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
        adaptor_showedit_listDetail =new PlantationAdapter(PlantationListActivity.this,data);
        listView.setAdapter(adaptor_showedit_listDetail);
        adaptor_showedit_listDetail.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(blockCode != null){
            if(data != null && data.size()> 0){
                tv_Norecord.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.invalidate();
                //((DraftAdapter)dataListView.getAdapter()).notifyDataSetChanged();
                adaptor_showedit_listDetail = new PlantationAdapter(PlantationListActivity.this, data);
                listView.setAdapter(adaptor_showedit_listDetail);
            }else{
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
                if (pos2 > 0) {
                    districtCode = districtList.get(pos2 - 1).get_DistCode().trim();
                    districtName = districtList.get(pos2 - 1).get_DistName().trim();
                    Log.d("district", "District Code: "+districtCode);
                    loadBlockSpinner();
                }
                break;

            case R.id.spin_block:
                int pos1 = position;
                if (pos1 > 0) {
                    blockCode = blockList.get(pos1 - 1).getBlockCode().trim();
                    blockName = blockList.get(pos1 - 1).getBlockName().trim();
                    Log.d("block", "Block Code: "+blockCode);
                    loadPanchayatSpinner();
                }
                break;

            case R.id.spin_panchayat:
                int pos = position;
                if (pos > 0) {
                    panchayatCode = panchayatList.get(pos - 1).getPcode().trim();
                    panchayatName = panchayatList.get(pos - 1).getPname().trim();
                    Log.d("pond", "Panchayat Code: "+panchayatCode);
                    populateLocalData();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

