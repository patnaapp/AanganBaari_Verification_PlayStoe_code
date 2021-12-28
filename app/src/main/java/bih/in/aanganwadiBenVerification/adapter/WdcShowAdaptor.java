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
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import bih.in.aanganwadiBenVerification.utility.GlobalVariables;
import bih.in.aanganwadiBenVerification.utility.Utiilties;

public class WdcShowAdaptor extends BaseAdapter
{
    LayoutInflater mInflater;
    ArrayList<String>datastatus=new ArrayList<>();
    Context context;
    String Totclasses;
    int countChecked=0;
    ArrayAdapter<String>Status;
    ArrayList<wdcBenList> data = new ArrayList<>();
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



    public WdcShowAdaptor(Context activity, ArrayList<wdcBenList> contact,String type,String dataType,String awcid)
    {
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

        int pos1 = i+1;
        //if (view == null) {
        mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.adaptor_list_wdc_checkdata, null);

        holder = new ViewHolder();
        holder.lin_ben_child = (LinearLayout) view.findViewById(R.id.lin_ben_child);
        //  holder.lin_ben_women = (LinearLayout) view.findViewById(R.id.lin_ben_women);
        holder.lockstatus1 = (TextView) view.findViewById(R.id.lockstatus1);
        holder.lockstatus = (TextView) view.findViewById(R.id.lockstatus);

        holder.submit = (Button) view.findViewById(R.id.submit);

        if (type.contains("3"))
        {
            holder.txt_f_and_mName = (TextView) view.findViewById(R.id.txt_f_and_mName);

            holder.txt_yr_of_birth = (TextView) view.findViewById(R.id.txt_yr_of_birth);
            holder.tv_slno = (TextView) view.findViewById(R.id.tv_slno);
            holder.txt_sex = (TextView) view.findViewById(R.id.txt_sex);
            holder.txt_childNmae = (TextView) view.findViewById(R.id.txt_childNmae);
            holder.radioattn = (RadioGroup) view.findViewById(R.id.radioattn);
            holder.radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn);
            holder.radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn);
            // holder.spn_status=(Spinner)view.findViewById(R.id.spn_status_child);
            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month_child) ;
            holder.tv_schemid=(TextView) view.findViewById(R.id.tv_schemid) ;
            holder.txt_f_and_mName.setText(data.get(i).getBenFH_Name());
            String  _masked_acc = data.get(i).getBen_AccountNo().trim().replaceAll("\\w(?=\\w{4})", "*");
            holder.txt_yr_of_birth.setText(_masked_acc);
            holder.txt_childNmae.setText(data.get(i).getBen_Name());
            holder.txt_sex.setText(data.get(i).getBen_Gender());
            holder.tv_schemid.setText(data.get(i).getScheme_Id());
            holder.editnoofmonth.setVisibility(View.GONE);
            //  holder.spn_status.setVisibility(View.GONE);
            holder.submit.setVisibility(View.GONE);

        }

        countChecked=0;

        holder.radioattn.setFocusable(false);
        holder.radio_Y_attn.setFocusable(false);
        holder.radio_N_attn.setFocusable(false);
        final int pos = i;

        if(data!=null)

        {
            if(!(data.get(i).getIsBenPer().equalsIgnoreCase("anyType{}")))

            {
                if(data.get(i).getIsBenPer().equalsIgnoreCase("R")) // y changed to N
                {

                    holder.radio_N_attn.setChecked(true);

                    holder.radioattn.setTag(data.get(i));

                    if(type.equalsIgnoreCase("3"))
                    {
                        holder.editnoofmonth.setVisibility(View.VISIBLE);
                        //   holder.spn_status.setVisibility(View.GONE);
                        holder.submit.setVisibility(View.VISIBLE);

                        if(data.get(i).getRejectReason()!=null)
                        {
                            if((!data.get(i).getRejectReason().equalsIgnoreCase(""))||(!(data.get(i).getRejectReason().equalsIgnoreCase("NA"))))
                            {
                                holder.editnoofmonth.setText(data.get(i).getRejectReason());
                            }

                        }
                    }

                }
                else
                {

                    holder.radio_Y_attn.setChecked(true);

                    holder.radioattn.setTag(data.get(i));

                    if(type.equalsIgnoreCase("3"))
                    {
                        holder.editnoofmonth.setVisibility(View.GONE);
                        //    holder.spn_status.setVisibility(View.GONE);
                        holder.submit.setVisibility(View.GONE);
                       /* if(data.get(i).getRejectReason()!=null){
                            if((!data.get(i).getRejectReason().equalsIgnoreCase(""))||(!(data.get(i).getRejectReason().equalsIgnoreCase("NA")))){
                                holder.editnoofmonth.setText(data.get(i).getRejectReason());
                            }
                        }*/
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
            }

            else if(data.get(i).getBenPerDate()==null)

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

        holder.radioattn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Radio button
                if ((type.equalsIgnoreCase("1"))||(type.equalsIgnoreCase("2")))
                {

                    wdcBenList country = data.get(pos);

                    if (finalHolder.radio_Y_attn.isChecked())
                    {

                        if ((country.getId() != null) && (!country.getId().equalsIgnoreCase("")))
                        {
                            Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                            country.setSelected(true);
                            cn.setBenID(country.getId());  //------------
                            //cn.setIsChecked("0");   //----------------
                            // cn.setIsChecked("N");   //----------------
                            cn.setIsChecked("Y");   //----------------

                            Log.e("BenID ", country.getId() + " Status " + "Y");
                            String no_noof_month = finalHolder.editnoofmonth.getText().toString();
                            updateAttendance(country.getId(), "Y", no_noof_month, str_NutritionId, str_NutritionValue);
                        }


                    }
                    else if (finalHolder.radio_N_attn.isChecked())
                    {
                        Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                        if (country != null)
                        {
                            if ((country.getId() != null) && (!country.getId().equalsIgnoreCase("")))
                            {
                                country.setSelected(false);
                                cn.setBenID(country.getId()); //----------y
                                // cn.setIsChecked("1");  //------------
                                //cn.setIsChecked("Y");  //------------
                                cn.setIsChecked("R");

                                Log.e("BenID ", country.getId() + " Status R");
                                String no_noof_month = finalHolder.editnoofmonth.getText().toString();
                                //  updateAttendance(country.getId(), "N", no_noof_month, str_NutritionId, str_NutritionValue);

                                // data = dataBaseHelper.getBenList(DataType,Awcid);
                                // refresh(data);
                            }

                        }

                    }


                    benchek.add(cn);
                }
                else
                {
                    if (type.equalsIgnoreCase("3") )
                    {

                        wdcBenList country =data.get(pos);

                        if (finalHolder.radio_Y_attn.isChecked())
                        {

                            if ((country.getId() != null))
                            {
                                if(!country.getId().equalsIgnoreCase(""))
                                {

                                    country.setSelected(true);
                                    cn.setBenID(country.getId());  //------------
                                    cn.setIsChecked("Y");   //----------------

                                    finalHolder1.editnoofmonth.setVisibility(View.GONE);
                                    //  finalHolder1.spn_status.setVisibility(View.GONE);
                                    finalHolder1.submit.setVisibility(View.GONE);
                                    A_Id = country.getId();
                                    updateAttendance(country.getId(), "Y", no_noof_month, str_NutritionId, str_NutritionValue);
                                }


                            }


                        }
                        else if (finalHolder.radio_N_attn.isChecked())
                        {
                            Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                            if (country != null)
                            {
                                if ((country.getId() != null) && (!country.getId().equalsIgnoreCase("")))
                                {
                                    country.setSelected(false);
                                    cn.setBenID(country.getId()); //----------y

                                    cn.setIsChecked("R");
                                    Log.e("BenID ", country.getId() + " Status " + "R");
                                    String no_noof_month = "";
                                    finalHolder1.editnoofmonth.setVisibility(View.VISIBLE);
                                    //   finalHolder1.spn_status.setVisibility(View.VISIBLE);
                                    finalHolder1.submit.setVisibility(View.VISIBLE);
                                    A_Id = country.getId();
                                    //   updateAttendance(country.getId(), "N", no_noof_month, str_NutritionId, str_NutritionValue);

                                }

                            }

                        }


                        benchek.add(cn);
                    }
                }
            }
        });
        final ViewHolder finalHolder2 = holder;
        finalHolder1.submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                no_noof_month = finalHolder2.editnoofmonth.getText().toString();
                if (no_noof_month.equalsIgnoreCase(""))
                {
                    Toast.makeText(context,    "कृपया पहले महीने की संख्या दर्ज करें" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    long c= updateAttendance1(A_Id, "R", no_noof_month, str_NutritionId, str_NutritionValue);
                    dataBaseHelper=new DataBaseHelper(context);
                    Log.d("vbhbjnjn","^^^^^^^^^^^"+Awcid);
                    ArrayList<wdcBenList> data1 = dataBaseHelper.getWdcBenListforadaptor(Awcid);

                    data = dataBaseHelper.getWdcBenListforadaptor(Awcid);

                    if(c>0)
                    {
                        Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                    }
                    refresh(data1);

                }

            }
        });


        GlobalVariables.benIDArrayList=benchek;
        wdcBenList country = data.get(i);

        if(benchek.size()>0)
        {
            for(int x=0;x<benchek.size();x++)
            {

                try {
                    if (country.getId().equalsIgnoreCase(benchek.get(x).getBenID()))
                    {
                        //  if (benchek.get(x).getIsChecked().equalsIgnoreCase("1")) {
                        if (benchek.get(x).getIsChecked().equalsIgnoreCase("Y"))
                        {
                            //holder.checkBox.setChecked(true);
                            holder.radio_Y_attn.setChecked(true);
                        }
                        else if (benchek.get(x).getIsChecked().equalsIgnoreCase("R"))
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
        holder.tv_slno.setText(Integer.toString(pos1)+").");
        pos1++;

        notifyDataSetChanged();
        return view;

    }

    public  void updateAttendance(String benid,String status,String noMonth,String NutritionId,String NutritionValue)
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
        // values.put("NutritionId",NutritionId);
        //  values.put("NutritionName",NutritionValue);
        // values.put("NoOfMonth",noMonth);
        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("wdcBenDetails", values, "a_Id=?", whereArgsss);
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
        //  values.put("NutritionId",NutritionId);
        //   values.put("NutritionName",NutritionValue);
        values.put("ben_rej_reason",noMonth);

        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("wdcBenDetails", values, "a_Id=?", whereArgsss);

        Log.e("ISUPDATED",""+c);
        return c;

    }

    private class ViewHolder
    {
        TextView txt_f_and_mName,txt_sex,txt_yr_of_birth,txt_childNmae,txt_husName,txt_wifeName,txt_cat,textViewSlNo,tv_slno;
        TextView tv_schemid;
        CheckBox checkBox;
        LinearLayout lin_detail,lin_ben_child,lin_ben_women;

            RadioGroup radioattn;
        Button submit;
        RadioButton radio_Y_attn,radio_N_attn;
        Spinner spn_status_child,spn_status;
        EditText editnoofmonth;
        TextView lockstatus1,lockstatus;
    }

    public void refresh(ArrayList<wdcBenList> events)
    {
        this.data.clear();
        this.data.addAll(events);
        notifyDataSetChanged();
    }
}

