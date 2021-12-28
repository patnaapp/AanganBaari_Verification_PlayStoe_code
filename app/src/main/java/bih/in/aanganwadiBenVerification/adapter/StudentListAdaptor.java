package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.entity.CheckUnCheck;
import bih.in.aanganwadiBenVerification.entity.Nutrition;
import bih.in.aanganwadiBenVerification.utility.GlobalVariables;
import bih.in.aanganwadiBenVerification.utility.Utiilties;



public class StudentListAdaptor extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String>datastatus=new ArrayList<>();
    Context context;
    String Totclasses;
    int countChecked=0;
    ArrayAdapter<String>Status;
    ArrayList<BenList> data = new ArrayList<>();
    ArrayList<CheckUnCheck> benchek=new ArrayList<>();
    String type="";

    DataBaseHelper dataBaseHelper;
    ArrayList<Nutrition>NutritionList=new ArrayList<>();
    ArrayList<String>NutritionString=new ArrayList<>();
    String str_NutritionId="",str_NutritionValue;
    private HashMap<String, String> textValues = new HashMap<String, String>();
    Button submit;
    String A_Id="";
    String no_noof_month="";
    String DataType="",Awcid="";


    public StudentListAdaptor(Context activity, ArrayList<BenList> contact,String type,String dataType,String awcid) {
        this.context = activity;
        this.data = contact;
        this.type=type;
        this.DataType=dataType;
        this.Awcid=awcid;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder = null;

        //if (view == null) {
        mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.adaotor_list_for_check, null);

        holder = new ViewHolder();
        holder.lin_ben_child = (LinearLayout) view.findViewById(R.id.lin_ben_child);
        holder.lin_ben_women = (LinearLayout) view.findViewById(R.id.lin_ben_women);
        holder.lockstatus1=(TextView)view.findViewById(R.id.lockstatus1);
        holder.lockstatus=(TextView)view.findViewById(R.id.lockstatus);

        holder.submit=(Button)view.findViewById(R.id.submit) ;

        if(type.contains("1"))
        {
            holder.lin_ben_child.setVisibility(View.GONE);
            holder.txt_husName = (TextView) view.findViewById(R.id.txt_husName);
            holder.txt_wifeName = (TextView) view.findViewById(R.id.txt_wifeName);
            holder.txt_cat = (TextView) view.findViewById(R.id.txt_cat);
            holder.radioattn = (RadioGroup) view.findViewById(R.id.radioattn1);
            holder.radio_group_remove = (RadioGroup) view.findViewById(R.id.radio_group_remove1);
            holder.radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn1);
            holder.radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn1);
            holder.radio_remove = (RadioButton)view.findViewById(R.id.radio_remove_attn1);
            holder.spn_status=(Spinner)view.findViewById(R.id.spn_status);
            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month) ;
            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month) ;
            holder.txt_husName.setText(data.get(i).getHusband_Name());
            holder.txt_wifeName.setText(data.get(i).getWife_Name());
            holder.txt_cat.setText(data.get(i).getCategory());
            holder.editnoofmonth.setVisibility(View.GONE);
            holder.spn_status.setVisibility(View.GONE);
            if(data.get(i).getIsDataReady().equalsIgnoreCase("Y"))
            {
                holder.lin_ben_women.setEnabled(false);
                holder.lin_ben_women.setClickable(false);
                holder.radio_N_attn.setEnabled(false);
                holder.radio_Y_attn.setEnabled(false);
                holder.radio_remove.setEnabled(false);
                holder.lockstatus.setText("Locked");
                holder.lockstatus.setTextColor(Color.parseColor("#ffcc0000"));
            }

        }
        else if(type.equalsIgnoreCase("2"))
        {

            holder.lin_ben_child.setVisibility(View.GONE);
            holder.txt_husName = (TextView) view.findViewById(R.id.txt_husName);
            holder.txt_wifeName = (TextView) view.findViewById(R.id.txt_wifeName);
            holder.txt_cat = (TextView) view.findViewById(R.id.txt_cat);
            holder.radioattn = (RadioGroup) view.findViewById(R.id.radioattn1);
            holder.radio_group_remove = (RadioGroup) view.findViewById(R.id.radio_group_remove1);
            holder.radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn1);
            holder.radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn1);
            holder.radio_remove = (RadioButton)view.findViewById(R.id.radio_remove_attn1);
            holder.spn_status=(Spinner)view.findViewById(R.id.spn_status);
            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month) ;
            holder.txt_husName.setText(data.get(i).getHusband_Name());
            holder.txt_wifeName.setText(data.get(i).getWife_Name());
            holder.txt_cat.setText(data.get(i).getCategory());
            holder.editnoofmonth.setVisibility(View.GONE);
            holder.spn_status.setVisibility(View.GONE);
            if(data.get(i).getIsDataReady().equalsIgnoreCase("Y"))
            {
                holder.lin_ben_women.setEnabled(false);
                holder.lin_ben_women.setClickable(false);
                holder.radio_N_attn.setEnabled(false);
                holder.radio_Y_attn.setEnabled(false);
                holder.radio_remove.setEnabled(false);
                holder.lockstatus.setText("Locked");
                holder.lockstatus.setTextColor(Color.parseColor("#ffcc0000"));
            }

        }
        else if(type.equalsIgnoreCase("3"))
        {
            holder.lin_ben_women.setVisibility(View.GONE);
            holder.txt_f_and_mName = (TextView) view.findViewById(R.id.txt_f_and_mName);
            holder.txt_yr_of_birth = (TextView) view.findViewById(R.id.txt_yr_of_birth);
            holder.txt_sex = (TextView) view.findViewById(R.id.txt_sex);
            holder.txt_childNmae = (TextView) view.findViewById(R.id.txt_childNmae);
            holder.radioattn = (RadioGroup) view.findViewById(R.id.radioattn);
            holder.radio_group_remove = (RadioGroup) view.findViewById(R.id.radio_group_remove);
            holder.radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn);
            holder.radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn);
            holder.radio_remove = (RadioButton)view.findViewById(R.id.radio_remove_attn);
            holder.spn_status=(Spinner)view.findViewById(R.id.spn_status_child);
            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month_child) ;
            holder.txt_f_and_mName.setText(data.get(i).getHusband_Name()+"\n"+data.get(i).getWife_Name());
            holder.txt_yr_of_birth.setText(data.get(i).getAge());
            holder.txt_childNmae.setText(data.get(i).getChild_Name());
            holder.txt_sex.setText(data.get(i).getGender());
            holder.editnoofmonth.setVisibility(View.GONE);
            holder.spn_status.setVisibility(View.GONE);
            holder.submit.setVisibility(View.GONE);
            if(data.get(i).getIsDataReady().equalsIgnoreCase("Y"))
            {
                Log.d("dataview",data.get(i).getIsDataReady());
                holder.lin_ben_child.setEnabled(false);
                holder.lin_ben_child.setClickable(false);
                holder.radio_N_attn.setEnabled(false);
                holder.radio_Y_attn.setEnabled(false);
                holder.radio_Y_attn.setClickable(false);
                holder.radio_remove.setEnabled(false);
                holder.radio_N_attn.setClickable(false);
                holder.lockstatus1.setText("Locked");
                holder.spn_status.setEnabled(false);
                holder.spn_status.setClickable(false);
                holder.editnoofmonth.setEnabled(false);
                holder.editnoofmonth.setClickable(false);
                holder.submit.setClickable(false);
                holder.submit.setEnabled(false);
                holder.lockstatus1.setTextColor(Color.parseColor("#ffcc0000"));

            }

        }

        dataBaseHelper = new DataBaseHelper(context);

        NutritionList = dataBaseHelper.getNuterition();
        NutritionString = new ArrayList<String>();
        NutritionString.add("कृपया पोषण प्रकार चुनें");
        int p = 0;
        for (Nutrition block_list : NutritionList)
        {
            NutritionString.add(block_list.getNutritionName());
            p++;
        }
        Status = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, NutritionString);
        Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(type.equalsIgnoreCase("3")) {
            holder.spn_status.setAdapter(Status);
        }
        else {
            holder.spn_status.setAdapter(Status);
        }

        holder.spn_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                  //  Log.d("TAGData","erhh"+NutritionList.get(i-1).getNutritionId());
                    str_NutritionId = NutritionList.get(i-1).getNutritionId();
                    str_NutritionValue = NutritionList.get(i-1).getNutritionName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                str_NutritionId="";
                str_NutritionValue="";
            }
        });

        view.setTag(holder);
        countChecked=0;

        holder.radioattn.setFocusable(false);
        holder.radio_Y_attn.setFocusable(false);
        holder.radio_N_attn.setFocusable(false);
        holder.radio_remove.setFocusable(false);
        holder.radio_group_remove.setFocusable(false);
        final int pos = i;

        if(data!=null)

        {

            if(!(data.get(i).getIsBenPer().equalsIgnoreCase("anyType{}")))

            {

                if(data.get(i).getIsBenPer().equalsIgnoreCase("N")) // y changed to N
                {

                    holder.radio_N_attn.setChecked(true);

                    holder.radioattn.setTag(data.get(i));

                    if(type.equalsIgnoreCase("3")){
                        holder.editnoofmonth.setVisibility(View.GONE);
                        holder.spn_status.setVisibility(View.GONE);
                        holder.submit.setVisibility(View.GONE);
                    }


                }
                else if(data.get(i).getIsBenPer().equalsIgnoreCase("Y"))
                {

                    holder.radio_Y_attn.setChecked(true);

                    holder.radioattn.setTag(data.get(i));


                    if(type.equalsIgnoreCase("3")){
                        holder.editnoofmonth.setVisibility(View.VISIBLE);
                        holder.spn_status.setVisibility(View.VISIBLE);
                        holder.submit.setVisibility(View.VISIBLE);

                        if(data.get(i).getNoOfMonth()!=null){
                            if((!data.get(i).getNoOfMonth().equalsIgnoreCase(""))||(!(data.get(i).getNoOfMonth().equalsIgnoreCase("NA")))){
                                holder.editnoofmonth.setText(data.get(i).getNoOfMonth());
                            }

                        } if(data.get(i).getNutritionName()!=null) {
                            if ((!data.get(i).getNutritionName().equalsIgnoreCase(""))||(!(data.get(i).getNutritionName().equalsIgnoreCase("NA")))) {
                                holder.spn_status.setSelection(((ArrayAdapter<String>) holder.spn_status.getAdapter()).getPosition(data.get(i).getNutritionName()));
                            }
                        }
                    }

                }
                else if(data.get(i).getIsBenPer().equalsIgnoreCase("D"))
                {

                    holder.radio_remove.setChecked(true);

                    holder.radio_group_remove.setTag(data.get(i));


                    if(type.equalsIgnoreCase("3")){
                        holder.editnoofmonth.setVisibility(View.GONE);
                        holder.spn_status.setVisibility(View.GONE);
                        holder.submit.setVisibility(View.GONE);

                        if(data.get(i).getNoOfMonth()!=null){
                            if((!data.get(i).getNoOfMonth().equalsIgnoreCase(""))||(!(data.get(i).getNoOfMonth().equalsIgnoreCase("NA")))){
                                holder.editnoofmonth.setText(data.get(i).getNoOfMonth());
                            }

                        } if(data.get(i).getNutritionName()!=null) {
                            if ((!data.get(i).getNutritionName().equalsIgnoreCase(""))||(!(data.get(i).getNutritionName().equalsIgnoreCase("NA")))) {
                                holder.spn_status.setSelection(((ArrayAdapter<String>) holder.spn_status.getAdapter()).getPosition(data.get(i).getNutritionName()));
                            }
                        }
                    }

                }
            }
            else if(data.get(i).getBenPerDate().equalsIgnoreCase("anyType{}"))

            {
                //  holder.checkBox.setChecked(false);
                holder.radio_Y_attn.setChecked(false);
                holder.radio_N_attn.setChecked(false);

                holder.radioattn.setTag(data.get(i));
                // holder.radio_Y_attn.setTag(data.get(i));

            }else if(data.get(i).getBenPerDate()==null)

            {
                //  holder.checkBox.setChecked(false);
                holder.radio_Y_attn.setChecked(false);
                holder.radio_N_attn.setChecked(false);

                holder.radioattn.setTag(data.get(i));

            }
        }


        final CheckUnCheck cn=new CheckUnCheck(); //--

        final ViewHolder finalHolder = holder;
        final ViewHolder finalHolder1 = holder;

        holder.radioattn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Radio button
                if ((type.equalsIgnoreCase("1"))||(type.equalsIgnoreCase("2"))) {

                    BenList country = data.get(pos);

                    if (finalHolder.radio_Y_attn.isChecked()) {

                        if ((country.getA_id() != null) && (!country.getA_id().equalsIgnoreCase(""))) {
                            Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                            country.setSelected(true);
                            cn.setBenID(country.getA_id());  //------------
                            country.setDelete_Ben("N");
                            //cn.setIsChecked("0");   //----------------
                            // cn.setIsChecked("N");   //----------------
                            cn.setIsChecked("Y");   //----------------


                            Log.e("BenID ", country.getA_id() + " Status " + "Y");
                            String no_noof_month = finalHolder.editnoofmonth.getText().toString();
                            updateAttendance(country.getA_id(), "Y", no_noof_month, str_NutritionId, str_NutritionValue,country.getDelete_Ben());
                        }


                    } else if (finalHolder.radio_N_attn.isChecked()) {
                        Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                        if (country != null) {
                            if ((country.getA_id() != null) && (!country.getA_id().equalsIgnoreCase(""))) {
                                country.setSelected(false);
                                cn.setBenID(country.getA_id()); //----------y
                                country.setDelete_Ben("N");
                                // cn.setIsChecked("1");  //------------
                                //cn.setIsChecked("Y");  //------------
                                cn.setIsChecked("N");

                                Log.e("BenID ", country.getA_id() + " Status " + "N");
                                String no_noof_month = finalHolder.editnoofmonth.getText().toString();
                                updateAttendance(country.getA_id(), "N", no_noof_month, str_NutritionId, str_NutritionValue,country.getDelete_Ben());

                               // data = dataBaseHelper.getBenList(DataType,Awcid);
                               // refresh(data);
                            }

                        }

                    }


                    benchek.add(cn);


            }else {
                    if (type.equalsIgnoreCase("3") ) {


                        BenList country =data.get(pos);

                        if (finalHolder.radio_Y_attn.isChecked()) {

                            if ((country.getA_id() != null)) {
                                if(!country.getA_id().equalsIgnoreCase("")) {

                                    country.setSelected(true);
                                    cn.setBenID(country.getA_id());  //------------
                                    cn.setIsChecked("Y");   //----------------

                                    finalHolder1.editnoofmonth.setVisibility(View.VISIBLE);
                                    finalHolder1.spn_status.setVisibility(View.VISIBLE);
                                    finalHolder1.submit.setVisibility(View.VISIBLE);

                                    Log.e("BenID ", country.getA_id() + " Status " + "Y"+country.getChild_Name());

                                    A_Id = country.getA_id();
                                }


                            }


                        } else if (finalHolder.radio_N_attn.isChecked()) {
                            Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                            if (country != null) {
                                if ((country.getA_id() != null) && (!country.getA_id().equalsIgnoreCase(""))) {
                                    country.setSelected(false);
                                    cn.setBenID(country.getA_id()); //----------y
                                    country.setDelete_Ben("N");
                                    cn.setIsChecked("N");


                                    Log.e("BenID ", country.getA_id() + " Status " + "N");
                                    String no_noof_month = "";
                                    finalHolder1.editnoofmonth.setVisibility(View.GONE);
                                    finalHolder1.spn_status.setVisibility(View.GONE);
                                    finalHolder1.submit.setVisibility(View.GONE);
                                    updateAttendance(country.getA_id(), "N", no_noof_month, str_NutritionId, str_NutritionValue,country.getDelete_Ben());

                                }

                            }

                        }


                        benchek.add(cn);
                    }
                }
            }
        });
        //delete

        holder.radio_group_remove.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Radio button
                if ((type.equalsIgnoreCase("1"))||(type.equalsIgnoreCase("2"))) {

                    BenList country = data.get(pos);

                    if (finalHolder.radio_remove.isChecked())
                    {
                        finalHolder.radioattn.setVisibility(View.GONE);
                        finalHolder.radio_N_attn.setSelected(false);
                        finalHolder.radio_Y_attn.setSelected(false);
                        if ((country.getA_id() != null) && (!country.getA_id().equalsIgnoreCase(""))) {
                            Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                           // country.setSelected(true);
                            country.setDelete_Ben("Y");
                            cn.setBenID(country.getA_id());  //------------
                            //cn.setIsChecked("0");   //----------------
                            // cn.setIsChecked("N");   //----------------
                            cn.setIsChecked("Y");   //----------------


                            Log.e("BenID ", country.getA_id() + " Status " + "Y");
                            String no_noof_month = finalHolder.editnoofmonth.getText().toString();
                            updateAttendance(country.getA_id(), "", no_noof_month, str_NutritionId, str_NutritionValue,country.getDelete_Ben());
                        }


                    }


                    benchek.add(cn);


                }else {
                    if (type.equalsIgnoreCase("3") ) {


                        BenList country =data.get(pos);

                        if (finalHolder.radio_remove.isChecked()) {

                            if ((country.getA_id() != null)) {
                                if(!country.getA_id().equalsIgnoreCase("")) {

                                    //country.setSelected(true);
                                    country.setDelete_Ben("Y");
                                    cn.setBenID(country.getA_id());  //------------
                                    cn.setIsChecked("Y");   //----------------

                                    finalHolder1.editnoofmonth.setVisibility(View.VISIBLE);
                                    finalHolder1.spn_status.setVisibility(View.VISIBLE);
                                    finalHolder1.submit.setVisibility(View.VISIBLE);

                                    Log.e("BenID ", country.getA_id() + " Status " + ""+country.getChild_Name());

                                    A_Id = country.getA_id();
                                }


                            }


                        }


                        benchek.add(cn);
                    }
                }
            }
        });

        //-----------


        final ViewHolder finalHolder2 = holder;
        finalHolder1.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_noof_month = finalHolder2.editnoofmonth.getText().toString();
                if (str_NutritionId.equalsIgnoreCase("")) {

                    Toast.makeText(context,
                            "कृपया पहले पोषण प्रकार दर्ज करें", Toast.LENGTH_SHORT).show();
                } else if (no_noof_month.equalsIgnoreCase("")) {
                    Toast.makeText(context,    "कृपया पहले महीने की संख्या दर्ज करें" , Toast.LENGTH_SHORT).show();
                } else {
                   long c= updateAttendance1(A_Id, "Y", no_noof_month, str_NutritionId, str_NutritionValue);
                    ArrayList<BenList> data1 = dataBaseHelper.getBenList(DataType,Awcid);

                    data = dataBaseHelper.getBenList(DataType,Awcid);

                    if(c>0){
                        Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                    }
                    refresh(data1);

                }

            }
        });


        GlobalVariables.benIDArrayList=benchek;


        BenList country = data.get(i);

        if(benchek.size()>0)
        {
            for(int x=0;x<benchek.size();x++)
            {

                try {
                    if (country.getA_id().equalsIgnoreCase(benchek.get(x).getBenID()))
                    {
                        //  if (benchek.get(x).getIsChecked().equalsIgnoreCase("1")) {
                        if (benchek.get(x).getIsChecked().equalsIgnoreCase("Y"))
                        {
                            //holder.checkBox.setChecked(true);
                            holder.radio_Y_attn.setChecked(true);

                        }
                        else if (benchek.get(x).getIsChecked().equalsIgnoreCase("N"))
                        {
                            // holder.checkBox.setChecked(false);
                            holder.radio_N_attn.setChecked(true);
                        }
                    }
                }
                catch(Exception e)
                {
                    //some error
                }
            }

        }




        notifyDataSetChanged();
        return view;

    }

    public  void updateAttendance(String benid,String status,String noMonth,String NutritionId,String NutritionValue,String IsBenDeleted)
    {
        DataBaseHelper localDB=new DataBaseHelper(context);
        ContentValues values = new ContentValues();
        // values.put("CreatedBy", diseCode);
        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");
        //  values.put("CreatedDate", cdate);
        values.put("BenPerDate", cdate);
        values.put("IsBenPer", status);
        values.put("IsBenUpdated","Y");
        values.put("NutritionId",NutritionId);
        values.put("NutritionName",NutritionValue);
        values.put("NoOfMonth",noMonth);
        values.put("Delete_benif",IsBenDeleted);

        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("BenDetails", values, "a_ID=?", whereArgsss);


    }
    public  long  updateAttendance1(String benid,String status,String noMonth,String NutritionId,String NutritionValue)
    {
        DataBaseHelper localDB=new DataBaseHelper(context);
        ContentValues values = new ContentValues();
        // values.put("CreatedBy", diseCode);
        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");
        //  values.put("CreatedDate", cdate);
        values.put("BenPerDate", cdate);
        values.put("IsBenPer", status);
        values.put("IsBenUpdated","Y");
        values.put("NutritionId",NutritionId);
        values.put("NutritionName",NutritionValue);
        values.put("NoOfMonth",noMonth);

        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("BenDetails", values, "a_ID=?", whereArgsss);


        Log.e("ISUPDATED",""+c);
        return c;

    }


    private class ViewHolder
    {
         TextView txt_f_and_mName,txt_sex,txt_yr_of_birth,txt_childNmae,txt_husName,txt_wifeName,txt_cat,textViewSlNo;
         CheckBox checkBox;
         LinearLayout lin_detail,lin_ben_child,lin_ben_women;

         RadioGroup radioattn,radio_group_remove;
         Button submit;
         RadioButton radio_Y_attn,radio_N_attn,radio_remove;
         Spinner spn_status_child,spn_status;
         EditText editnoofmonth;
         TextView lockstatus1,lockstatus;
    }

    public void refresh(ArrayList<BenList> events) {
        this.data.clear();
        this.data.addAll(events);
        notifyDataSetChanged();
    }


}
