package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.GetLocation;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.entity.PlantationSiteEntity;
import bih.in.aanganwadiBenVerification.entity.PondLakeDepartmentEntity;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.entity.VillageListEntity;


public class PlantationInspectionActivity extends Activity implements AdapterView.OnItemSelectedListener {

    GetLocation gps;
    DataBaseHelper dataBaseHelper;
    LinearLayout ll_land_type,ll_plant_scheme_no;

    TextView tv_work_code,tv_work_name,tv_work_type,tv_agency_name,tv_fyear;
    TextView tv_plantation_site,tv_year,tv_forest_poshak_pant_no,tv_forest_poshak_bhugtan_pant_no;
    EditText et_planted_no,et_live_pant_no,et_live_pant_percent,et_above_90_per,et_75_90_per,et_50_75_per,et_below_25_per,et_remark,et_average_height_of_plant,et_gawyan_per;

    Spinner spin_plantation_site,spin_year,spin_forest_poshak_pant_no,spin_bhugtan_plant_month,spin_bhugtan_plant_year;
    Button btn_reg;

    String inspectionID,blockID,blockName,rajswaThanaNumber,villageID="",villageName,distID,distName,latitude,longitude,panchayatCode,panchayatName="", isUpdated = "0";
    String landType, landTypeValue="", yearId, yearValue="";


    ArrayList<PondLakeDepartmentEntity> deptList = new ArrayList<PondLakeDepartmentEntity>();
    ArrayList<PlantationSiteEntity> plantationSiteList = new ArrayList<PlantationSiteEntity>();
    ArrayList<VillageListEntity> villageList = new ArrayList<VillageListEntity>();

    ArrayList<String> plantatiionNameArray, yearArray;

    String landTypeOption[] = {"-चयन करे-","सरकारी", "निजी"};
    String yearOption[] = {"-चयन करे-","2014-2015", "2015-2016", "2016-2017","2017-2018","2018-2019"};
    String vanPoshakhoOption[] = {"-चयन करे-","1","2","3","4","5","6","7","8","9","10"};
    String bhugtanMonthOption[] = {"-चयन करे-","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug","Sep","Oct","Nov","Dec"};
    String bhugtanYearOption[] = {"-चयन करे-","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024"};

    int id;

    ArrayAdapter plantationAdapter;
    ArrayAdapter vanPoshakhoAdaptor;
    ArrayAdapter bhugtanMonthAdaptor;
    ArrayAdapter bhugtanYearAdaptor;
    String blockCode,s1_data = "";

    PlantationDetail plantationInfo;
    UserDetails userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantation_inspection);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        Initialization();

        vanPoshakhoAdaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vanPoshakhoOption);
        vanPoshakhoAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_forest_poshak_pant_no.setAdapter(vanPoshakhoAdaptor);

        bhugtanMonthAdaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bhugtanMonthOption);
        bhugtanMonthAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bhugtan_plant_month.setAdapter(bhugtanMonthAdaptor);

        bhugtanYearAdaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bhugtanYearOption);
        bhugtanYearAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bhugtan_plant_year.setAdapter(bhugtanYearAdaptor);

        btn_reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (validateData()) {
                            UpdateInspectionDetail();
                        }else{
                            Toast.makeText(PlantationInspectionActivity.this, "Please filled all required field", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

        et_planted_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    float totalPlant =  Float.parseFloat(s.toString().trim());
                    String livePlantNo = et_live_pant_no.getText().toString().trim();
                    if(!livePlantNo.isEmpty()){
                        try{
                            float livePlant =  Float.parseFloat(livePlantNo);
                            float livePlantPercent = (livePlant*100)/totalPlant;
                            et_live_pant_percent.setText(String.format("%.2f", livePlantPercent));
                            et_live_pant_percent.setError(null);
                        }catch (Exception e){
                            Log.e("exception", e.getLocalizedMessage());
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_live_pant_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    float livePlant =  Float.parseFloat(s.toString().trim());
                    String totalPlantNo = et_planted_no.getText().toString().trim();
                    if(!totalPlantNo.isEmpty()){
                        try {
                            float totalPlant = Float.parseFloat(totalPlantNo);
                            float livePlantPercent = (livePlant * 100) / totalPlant;
                            et_live_pant_percent.setText(String.format("%.2f", livePlantPercent));
                            et_live_pant_percent.setError(null);
                        }catch (Exception e){
                        Log.e("exception", e.getLocalizedMessage());
                    }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getUserDetail();
        loadPlantationSiteSpinner();
        extractDataFromIntent();

    }

    private void Initialization()
    {
        tv_work_code = (TextView) findViewById(R.id.tv_work_code);
        tv_work_name = (TextView) findViewById(R.id.tv_work_name);
        tv_work_type = (TextView) findViewById(R.id.tv_work_type);
        tv_agency_name = (TextView) findViewById(R.id.tv_agency_name);
        tv_fyear = (TextView) findViewById(R.id.tv_fyear);
        tv_forest_poshak_pant_no = (TextView) findViewById(R.id.tv_forest_poshak_pant_no);
        tv_forest_poshak_bhugtan_pant_no = (TextView) findViewById(R.id.tv_forest_poshak_bhugtan_pant_no);

        tv_plantation_site = (TextView) findViewById(R.id.tv_plantation_site);
        tv_year = (TextView) findViewById(R.id.tv_year);

        ll_land_type = (LinearLayout) findViewById(R.id.ll_land_type);
        ll_plant_scheme_no = (LinearLayout) findViewById(R.id.ll_plant_scheme_no);

        et_planted_no = (EditText)findViewById(R.id.et_planted_no);
        et_live_pant_no = (EditText)findViewById(R.id.et_live_pant_no);
        et_live_pant_percent =(EditText)findViewById(R.id.et_live_pant_percent);
        et_above_90_per = (EditText)findViewById(R.id.et_above_90_per);
        et_75_90_per = (EditText)findViewById(R.id.et_75_90_per);
        et_50_75_per = (EditText)findViewById(R.id.et_50_75_per);
        et_below_25_per = (EditText)findViewById(R.id.et_below_25_per);
        et_gawyan_per = (EditText)findViewById(R.id.et_gawyan_per);
        et_average_height_of_plant = (EditText)findViewById(R.id.et_average_height_of_plant);
        et_remark = (EditText)findViewById(R.id.et_remark);

        spin_plantation_site = (Spinner)findViewById(R.id.spin_plantation_site);
        spin_forest_poshak_pant_no = (Spinner)findViewById(R.id.spin_forest_poshak_pant_no);
        spin_bhugtan_plant_month =(Spinner)findViewById(R.id.spin_bhugtan_plant_month);
        spin_bhugtan_plant_year =(Spinner)findViewById(R.id.spin_bhugtan_plant_year);

        spin_plantation_site.setOnItemSelectedListener(this);
        spin_forest_poshak_pant_no.setOnItemSelectedListener(this);
        spin_bhugtan_plant_month.setOnItemSelectedListener(this);
        spin_bhugtan_plant_year.setOnItemSelectedListener(this);

        btn_reg = (Button) findViewById(R.id.btn_reg);
    }

    private boolean validateData() {
        View focusView = null;
        boolean validate = true;
        if (plantationInfo.getPlantation_Site_Id() == null) {
            tv_plantation_site.setError(getString(R.string.fieldRequired));
            focusView = tv_plantation_site;
            validate = false;
        }


        if (et_planted_no.getText().toString().equals("")) {
            et_planted_no.setError(getString(R.string.fieldRequired));
            focusView = et_planted_no;
            validate = false;
        }

        if (et_live_pant_no.getText().toString().equals("")) {
            et_live_pant_no.setError(getString(R.string.fieldRequired));
            focusView = et_live_pant_no;
            validate = false;
        }else if(!et_planted_no.getText().toString().equals("") && !et_live_pant_no.getText().toString().equals("")){
            try{
                if(Double.parseDouble(et_live_pant_no.getText().toString()) > Double.parseDouble(et_planted_no.getText().toString())){
                    et_live_pant_no.setError("उत्तरजीवित पौधों की संख्या रोपित पौधों की संख्या से कम होना चाहिए!");
                    focusView = et_live_pant_no;
                    validate = false;
                }
            }catch (Exception e){
                Log.e("verification", e.getLocalizedMessage());
            }
        }

        if (et_live_pant_percent.getText().toString().equals("")) {
            et_live_pant_percent.setError(getString(R.string.fieldRequired));
            focusView = et_live_pant_percent;
            validate = false;
        }

        if (plantationInfo.getVan_Posako_No() == null) {
            tv_forest_poshak_pant_no.setError(getString(R.string.fieldRequired));
            focusView = tv_forest_poshak_pant_no;
            validate = false;
        }

        if (plantationInfo.getPosak_bhugtaanMonth() == null) {
            tv_forest_poshak_bhugtan_pant_no.setError(getString(R.string.fieldRequired));
            focusView = tv_forest_poshak_bhugtan_pant_no;
            validate = false;
        }

        if (plantationInfo.getPosak_bhugtaanYear() == null) {
            tv_forest_poshak_bhugtan_pant_no.setError(getString(R.string.fieldRequired));
            focusView = tv_forest_poshak_bhugtan_pant_no;
            validate = false;
        }

        if (et_gawyan_per.getText().toString().equals("")) {
            et_gawyan_per.setError(getString(R.string.fieldRequired));
            focusView = et_gawyan_per;
            validate = false;
        }
//        else if(Float.parseFloat(et_gawyan_per.getText().toString()) > 100.00){
//            et_gawyan_per.setError("प्रतिशत 100 से कम होना चाहिए");
//            focusView = et_gawyan_per;
//            validate = false;
//        }

        if (et_average_height_of_plant.getText().toString().equals("")) {
            et_average_height_of_plant.setError(getString(R.string.fieldRequired));
            focusView = et_average_height_of_plant;
            validate = false;
        }

        if (et_remark.getText().toString().equals("")) {
            et_remark.setError(getString(R.string.fieldRequired));
            focusView = et_remark;
            validate = false;
        }

        if (!validate) focusView.requestFocus();

        return validate;
    }

    private void getUserDetail(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("uid", "user");
        String password = prefs.getString("pass", "password");
        userInfo = dataBaseHelper.getUserDetails(username.toLowerCase(), password);
    }

    public String getappversion() {
        String versionCode = null;
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String packageName = info.packageName;
            versionCode = String.valueOf(info.versionCode);
            String versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
        }
        return versionCode;
    }

    public void loadPlantationSiteSpinner() {
        plantationSiteList = dataBaseHelper.getPlantationSiteDetail();
        plantatiionNameArray = new ArrayList<String>();
        plantatiionNameArray.add("-चयन करे-");
        int i = 0;
        for (PlantationSiteEntity plantation_list : plantationSiteList) {
            plantatiionNameArray.add(plantation_list.getSite_Name());
            i++;
        }
        plantationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plantatiionNameArray);
        plantationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_plantation_site.setAdapter(plantationAdapter);
    }

    private void UpdateInspectionDetail(){
        long result = 0;
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

//        plantationInfo.setBhumiType(landTypeValue);
//        plantationInfo.setFyear(yearValue);

        plantationInfo.setRopit_PlantNo(et_planted_no.getText().toString().trim());
        plantationInfo.setUtarjibit_PlantNo(et_live_pant_no.getText().toString().trim());
        plantationInfo.setUtarjibitaPercent(et_live_pant_percent.getText().toString().trim());
        plantationInfo.setGavyan_percentage(et_gawyan_per.getText().toString().trim());
        plantationInfo.setAverage_height_Plant(et_average_height_of_plant.getText().toString().trim());
        plantationInfo.setRemarks(et_remark.getText().toString());

        plantationInfo.setIsUpdated("1");
        plantationInfo.setAppVersion(getappversion());
        plantationInfo.setVerifiedBy(userInfo.getUserID());
        plantationInfo.setVerifiedDate(currentDate);
        plantationInfo.setUserRole(userInfo.getUserrole());

        result = new DataBaseHelper(this).UpdatePlantationInspectionDetail(plantationInfo);

            String type = "edit";

            if (result > 0) {
                Toast.makeText(getApplicationContext(),"डाटा सफलतापूर्वक सेव हो गया",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MultiplePhotoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Log.d("ggeuhshd",""+id);
                intent.putExtra("KEY_PID", plantationInfo.getInspectionID());
                intent.putExtra("pupose", type);
                intent.putExtra("isOpen", s1_data);
                startActivity(intent);
                  finish();
            }else {
                Toast.makeText(getApplicationContext(),"डाटा सेव नहीं हुआ, कृपया पुन: प्रयास करें", Toast.LENGTH_SHORT).show();
            }

    }

    public void extractDataFromIntent(){
        Intent intent = getIntent();
        plantationInfo = (PlantationDetail) intent.getSerializableExtra("data");
        Log.e("InspectId", plantationInfo.getInspectionID());

        tv_work_code.setText(plantationInfo.getWorkCode());
        tv_work_name.setText(plantationInfo.getWorkName());
        tv_work_type.setText(plantationInfo.getWorktype());
        tv_agency_name.setText(plantationInfo.getAgencyName());
        tv_fyear.setText(plantationInfo.getWorkStateFyear());

        if (plantationInfo.getIsUpdated() != null && plantationInfo.getIsUpdated().contains("1")){

            et_planted_no.setText(plantationInfo.getRopit_PlantNo());
            et_live_pant_no.setText(plantationInfo.getUtarjibit_PlantNo());
            et_live_pant_percent.setText(plantationInfo.getUtarjibitaPercent());
            et_gawyan_per.setText(plantationInfo.getGavyan_percentage());
            et_average_height_of_plant.setText(plantationInfo.getAverage_height_Plant());
            et_remark.setText(plantationInfo.getRemarks());

            spin_plantation_site.setSelection((((ArrayAdapter<String>) spin_plantation_site.getAdapter()).getPosition(plantationInfo.getPlantation_Site_Name())));
            spin_forest_poshak_pant_no.setSelection((((ArrayAdapter<String>) spin_forest_poshak_pant_no.getAdapter()).getPosition(plantationInfo.getVan_Posako_No())));
            spin_bhugtan_plant_month.setSelection((((ArrayAdapter<String>) spin_bhugtan_plant_month.getAdapter()).getPosition(plantationInfo.getPosak_bhugtaanMonth())));
            spin_bhugtan_plant_year.setSelection((((ArrayAdapter<String>) spin_bhugtan_plant_year.getAdapter()).getPosition(plantationInfo.getPosak_bhugtaanYear())));

            landTypeValue = plantationInfo.getBhumiType();
            yearValue = plantationInfo.getFyear();
            //Log.e("year",yearValue);

            landType = landTypeValue == "1" ? "सरकारी" : "निजी";
            //spin_plantation_site.setSelection(landType.contains("1") ? 1 : 2);

            if(yearValue != null){
                if (yearValue.contains("2014-2015")){
                    spin_year.setSelection(1);
                }else if(yearValue.contains("2015-2016")){
                    spin_year.setSelection(2);
                }else if(yearValue.contains("2016-2017")){
                    spin_year.setSelection(3);
                }else if(yearValue.contains("2017-2018")){
                    spin_year.setSelection(4);
                }else if(yearValue.contains("2018-2019")){
                    spin_year.setSelection(5);
                }
            }

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_plantation_site:
                if (position > 0) {
                    tv_plantation_site.setError(null);
                    String siteId = plantationSiteList.get(position - 1).getId().trim();
                    plantationInfo.setPlantation_Site_Id(siteId);
                    plantationInfo.setPlantation_Site_Name(plantationSiteList.get(position - 1).getSite_Name());
                }
                break;
            case R.id.spin_forest_poshak_pant_no:
                if(position > 0){
                    tv_forest_poshak_pant_no.setError(null);
                    plantationInfo.setVan_Posako_No(vanPoshakhoOption[position].toString());
                }
                break;
            case R.id.spin_bhugtan_plant_month:
                if(position > 0){
                    tv_forest_poshak_bhugtan_pant_no.setError(null);
                    plantationInfo.setPosak_bhugtaanMonth(bhugtanMonthOption[position].toString());
                }
                break;
            case R.id.spin_bhugtan_plant_year:
                if(position > 0){
                    tv_forest_poshak_bhugtan_pant_no.setError(null);
                    plantationInfo.setPosak_bhugtaanYear(bhugtanYearOption[position].toString());
                }
                break;
//            case R.id.spin_year:
//                if (position > 0) {
//                    tv_year.setError(null);
//                    yearValue=yearOption[position].toString();
//
//                    switch (yearValue){
//                        case "2014-2015":
//                            yearId = "1";
//                            break;
//                        case "2015-2016":
//                            yearId = "2";
//                            break;
//                        case "2016-2017":
//                            yearId = "3";
//                            break;
//                        case "2017-2018":
//                            yearId = "4";
//                            break;
//                        case "2018-2019":
//                            yearId = "5";
//                            break;
//                    }
//                }
//                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
