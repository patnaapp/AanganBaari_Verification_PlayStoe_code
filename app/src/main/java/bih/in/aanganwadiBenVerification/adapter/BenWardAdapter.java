package bih.in.aanganwadiBenVerification.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.entity.CheckUnCheck;
import bih.in.aanganwadiBenVerification.entity.ward;
import bih.in.aanganwadiBenVerification.utility.GlobalVariables;
import bih.in.aanganwadiBenVerification.utility.Utiilties;

public class BenWardAdapter extends RecyclerView.Adapter<BenWardAdapter.ViewHolder>
{
    ArrayList<ward> WardList;// = new ArrayList<ward>();
    ArrayAdapter<String> wardadapter;

    String _varwardName="",_varwardID="0",_Mobile="";

    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    ArrayList<BenList> data = new ArrayList<>();
    ArrayList<String>datastatus=new ArrayList<>();
    ArrayList<CheckUnCheck> benchek=new ArrayList<>();    private PopupWindow mPopupWindow;
    DataBaseHelper databaseHelper1;
    String type="";
    ArrayAdapter<String>Status;
    int countChecked=0;
    public BenWardAdapter(Context context1, ArrayList<BenList> SubjectValues1,String type)
    {

        this.context = context1;
        this.data = SubjectValues1;
        datastatus.add("--Select--");
        datastatus.add("गर्भवती महिला");
        datastatus.add("स्तनपान ");
        //this.Totclasses=totnnoClasses;
        this.type=type;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txt_f_and_mName,txt_sex,txt_yr_of_birth,txt_childNmae,txt_husName,txt_wifeName,txt_cat,textViewSlNo;
        CheckBox checkBox;
        EditText edt_no_month_child,edt_no_month;
        LinearLayout lin_detail,lin_ben_child,lin_ben_women;

        RadioGroup radioattn;
        RadioButton radio_Y_attn,radio_N_attn;
        Spinner spn_status_child,spn_status;
        //ImageView imgbasic,imgedit;
        public ViewHolder(View view,String type)
        {

            super(view);
            Log.d("hfhbvhb","777"+type);
            if(type.contains("1"))
            {
                radioattn = (RadioGroup) view.findViewById(R.id.radioattn1);
                radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn1);
                radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn1);
            }
            else if(type.contains("2"))
            {
                radioattn = (RadioGroup) view.findViewById(R.id.radioattn1);
                radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn1);
                radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn1);
            }
            else if(type.contains("3"))
            {
                radioattn = (RadioGroup) view.findViewById(R.id.radioattn);
                radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn);
                radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn);
            }
            edt_no_month_child=(EditText)view.findViewById(R.id.edt_no_month_child);
            edt_no_month=(EditText)view.findViewById(R.id.edt_no_month);
            lin_ben_child = (LinearLayout) view.findViewById(R.id.lin_ben_child);
            lin_ben_women = (LinearLayout) view.findViewById(R.id.lin_ben_women);
            txt_husName = (TextView) view.findViewById(R.id.txt_husName);
            txt_wifeName = (TextView) view.findViewById(R.id.txt_wifeName);
            txt_cat = (TextView) view.findViewById(R.id.txt_cat);
            //checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            spn_status=(Spinner)view.findViewById(R.id.spn_status);

            txt_f_and_mName = (TextView) view.findViewById(R.id.txt_f_and_mName);
            txt_yr_of_birth = (TextView) view.findViewById(R.id.txt_yr_of_birth);
            txt_sex = (TextView) view.findViewById(R.id.txt_sex);
            txt_childNmae = (TextView) view.findViewById(R.id.txt_childNmae);

            spn_status_child=(Spinner)view.findViewById(R.id.spn_status_child);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        view1 = LayoutInflater.from(context).inflate(R.layout.adaotor_list_for_check,parent,false);
        Log.d("hfhbvhb","77"+type);

        viewHolder1 = new ViewHolder(view1,type);

        //WardList = new ArrayList<>();
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int i)
    {
        if(type.contains("1"))
        {
            holder.lin_ben_child.setVisibility(View.GONE);

            holder.txt_husName.setText(data.get(i).getHusband_Name());
            holder.txt_wifeName.setText(data.get(i).getWife_Name());
            holder.txt_cat.setText(data.get(i).getCategory());
            Status = new ArrayAdapter(context, android.R.layout.simple_spinner_item, datastatus);
            Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spn_status.setAdapter(Status);

        }
        else if(type.equalsIgnoreCase("2"))
        {
            holder.lin_ben_child.setVisibility(View.GONE);

            holder.txt_husName.setText(data.get(i).getHusband_Name());
            holder.txt_wifeName.setText(data.get(i).getWife_Name());
            holder.txt_cat.setText(data.get(i).getCategory());

            Status = new ArrayAdapter(context, android.R.layout.simple_spinner_item, datastatus);
            Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spn_status.setAdapter(Status);
        }
        else if(type.equalsIgnoreCase("3"))
        {
            holder.lin_ben_women.setVisibility(View.GONE);

            holder.txt_f_and_mName.setText(data.get(i).getHusband_Name()+"\n"+data.get(i).getWife_Name());
            holder.txt_yr_of_birth.setText(data.get(i).getAge());
            holder.txt_childNmae.setText(data.get(i).getChild_Name());
            holder.txt_sex.setText(data.get(i).getGender());

            Status = new ArrayAdapter(context, android.R.layout.simple_spinner_item, datastatus);
            Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spn_status_child.setAdapter(Status);
        }

        // view1.setTag(holder);
        countChecked=0;
//            final CheckUnCheck cn=new CheckUnCheck(); //------------------
//        holder.checkBox.setFocusable(false);
        holder.radioattn.setFocusable(false);
        holder.radio_Y_attn.setFocusable(false);
        holder.radio_N_attn.setFocusable(false);
        int pos = i+1;

        if(data!=null)

        {

            if(!(data.get(i).getIsBenPer().equalsIgnoreCase("anyType{}")))

            {
//                // if(data.get(i).getStdattseventyfiveper().equalsIgnoreCase("N")) // y changed to N
                if(data.get(i).getIsBenPer().equalsIgnoreCase("N")) // y changed to N
                {
                    //  holder.checkBox.setChecked(true);
                    holder.radio_N_attn.setChecked(true);

                    // holder.checkBox.setChecked(false);
                    //    holder.checkBox.setTag(data.get(i));
                    holder.radioattn.setTag(data.get(i));

                    //country.setSelected(true);
                }
                else
                {
                    //  holder.checkBox.setChecked(true);
                    holder.radio_Y_attn.setChecked(true);
                    //   holder.checkBox.setTag(data.get(i));
                    holder.radioattn.setTag(data.get(i));
                    //country.setSelected(false);

                }
            }
            else if(data.get(i).getBenPerDate().equalsIgnoreCase("anyType{}"))
            // else if(data.get(i).getStdattseventyfiveper().equalsIgnoreCase("anyType{}"))
            //  else if(data.get(i).getAttendancePerDate().equalsIgnoreCase("anyType{}"))
            {
                //  holder.checkBox.setChecked(false);
                holder.radio_Y_attn.setChecked(false);
                holder.radio_N_attn.setChecked(false);

                holder.radioattn.setTag(data.get(i));
                // holder.radio_Y_attn.setTag(data.get(i));

            }
            else if(data.get(i).getBenPerDate()==null)
            // else if(data.get(i).getStdattseventyfiveper().equalsIgnoreCase("anyType{}"))
            //  else if(data.get(i).getAttendancePerDate().equalsIgnoreCase("anyType{}"))
            {
                //  holder.checkBox.setChecked(false);
                holder.radio_Y_attn.setChecked(false);
                holder.radio_N_attn.setChecked(false);

                holder.radioattn.setTag(data.get(i));
                // holder.radio_Y_attn.setTag(data.get(i));

            }
        }

        //  final CheckUnCheck cn=new CheckUnCheck(); //--
        final CheckUnCheck cn=new CheckUnCheck(); //--
        //final ViewHolder finalHolder1 = holder;
        final ViewHolder finalHolder = holder;
        holder.radioattn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Radio button
                //   int pos = (int) finalHolder.radioattn.getTag();
                BenList country = (BenList) finalHolder.radioattn.getTag();


                if (finalHolder.radio_Y_attn.isChecked())
                {
                    Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                    //  updateMismatchStatus(country.getStdbenid(),country.getaID(),country.getCUBy(),"Y");
                    country.setSelected(true);
                    cn.setBenID(country.getA_id());  //------------
                    //cn.setIsChecked("0");   //----------------
                    // cn.setIsChecked("N");   //----------------
                    cn.setIsChecked("Y");   //----------------
                    //Log.e("BenID ",country.getStdbenid()+" Status " + "0");


                    Log.e("BenID ",country.getA_id()+" Status " + "Y");
                    updateAttendance(country.getA_id(),"Y");
                    // Toast.makeText(context, country.getHusband_Name()+" का रिकॉर्ड अपडेट हो  गया है", Toast.LENGTH_SHORT).show();

                }
                else if (finalHolder.radio_N_attn.isChecked())
                {
                    Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                    if (country!=null){
                        country.setSelected(false);
                        cn.setBenID(country.getA_id()); //----------y
                        // cn.setIsChecked("1");  //------------
                        //cn.setIsChecked("Y");  //------------
                        cn.setIsChecked("N");
                        // finalHolder.txtname.setTextColor(context.getResources().getColor(R.color.holo_red_dark));//------------
                        //    Log.e("BenID ",country.getStdbenid()+" Status " + "1");

                        Log.e("BenID ",country.getA_id()+" Status " + "N");
                        updateAttendance(country.getA_id(),"N");
                        // Toast.makeText(context, country.getHusband_Name()+" का रिकॉर्ड अपडेट हो  गया है", Toast.LENGTH_SHORT).show();
                    }

                }

                benchek.add(cn);
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
                            // } else i  f (benchek.get(x).getIsChecked().equalsIgnoreCase("0")) {
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

    }

    public  void updateAttendance(String benid,String status)
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

        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("BenDetails", values, "a_ID=?", whereArgsss);
        Log.e("ISUPDATED",""+c);
    }


    @Override
    public int getItemCount()
    {
        return data.size();
    }


}
