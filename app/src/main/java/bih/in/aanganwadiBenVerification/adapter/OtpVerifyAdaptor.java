package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.ShowPopupListener;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.CheckUnCheck;
import bih.in.aanganwadiBenVerification.entity.Nutrition;
import bih.in.aanganwadiBenVerification.entity.OtpVerifyModel;
import bih.in.aanganwadiBenVerification.utility.CommonPref;
import bih.in.aanganwadiBenVerification.utility.Encriptor;
import bih.in.aanganwadiBenVerification.utility.GlobalVariables;
import bih.in.aanganwadiBenVerification.utility.Utiilties;

public class OtpVerifyAdaptor extends BaseAdapter
{
    LayoutInflater mInflater;
    ArrayList<String>datastatus=new ArrayList<>();
    Context context;
    String Totclasses;
    int countChecked=0;
    ArrayAdapter<String>Status;
    ArrayList<OtpVerifyModel> data = new ArrayList<>();
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
    Encriptor _encrptor;
    String otp="",accno="";
    ArrayList<OtpVerifyModel> data2;
    ShowPopupListener showPopupListener;
    String month_id="",year_id="";

    public OtpVerifyAdaptor(Context activity, ArrayList<OtpVerifyModel> contact, String type, String dataType, String awcid,String acc,ShowPopupListener showpopupListener,String monthid,String year)
    {
        this.context = activity;
        this.data = contact;
        this.type=type;
        this.DataType=dataType;
        this.Awcid=awcid;
        this.accno=acc;
        this.showPopupListener=showpopupListener;
        this.month_id=monthid;
        this.year_id=year;
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
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder = null;
        _encrptor=new Encriptor();
        int pos1 = i+1;
        //if (view == null) {
        mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.adaptor_list_verifyotp, null);

        holder = new ViewHolder();
        holder.lin_ben_child = (LinearLayout) view.findViewById(R.id.lin_ben_child);
        holder.lockstatus1 = (TextView) view.findViewById(R.id.lockstatus1);
        holder.lockstatus = (TextView) view.findViewById(R.id.lockstatus);

        holder.submit = (Button) view.findViewById(R.id.submit);

        if(type.contains("3"))
        {
            holder.txt_f_and_mName = (TextView) view.findViewById(R.id.txt_f_and_mName);
            holder.ll_otp = (LinearLayout) view.findViewById(R.id.ll_otp);
            holder.lin_ben_child = (LinearLayout) view.findViewById(R.id.lin_ben_child);
            holder.tv_verified = (TextView) view.findViewById(R.id.tv_verified);
            //   holder.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            holder.tv_motherNAme = (TextView) view.findViewById(R.id.tv_motherNAme);
            holder.txt_aadhar = (TextView) view.findViewById(R.id.txt_aadhar);
            holder.txt_yr_of_birth = (TextView) view.findViewById(R.id.txt_yr_of_birth);
            holder.tv_slno = (TextView) view.findViewById(R.id.tv_slno);
            holder.txt_benif_Id = (TextView) view.findViewById(R.id.txt_benif_Id);
            holder.txt_Name = (TextView) view.findViewById(R.id.txt_Name);
            holder.txt_NutritionType = (TextView) view.findViewById(R.id.txt_NutritionType);
            holder.txt_benifType = (TextView) view.findViewById(R.id.txt_benifType);

            holder.radioattn = (RadioGroup) view.findViewById(R.id.radioattn);
            holder.radio_Y_attn = (RadioButton)view.findViewById(R.id.radio_Y_attn);
            holder.radio_N_attn = (RadioButton)view.findViewById(R.id.radio_N_attn);

            holder.editnoofmonth=(EditText)view.findViewById(R.id.edt_no_month_child) ;

            holder.txt_f_and_mName.setText(data.get(i).getBenFH_Name());
            String  _masked_acc = data.get(i).getBen_AccountNo().trim().replaceAll("\\w(?=\\w{4})", "*");
            holder.txt_yr_of_birth.setText(_masked_acc);

            holder.tv_motherNAme.setText(data.get(i).getBenM_Name());
            String  _masked_aadhar = data.get(i).getBen_AadharCardNo().trim().replaceAll("\\w(?=\\w{4})", "*");
            holder.txt_aadhar.setText(data.get(i).getBen_cat());
            holder.txt_Name.setText(data.get(i).getBen_Name());
            holder.txt_NutritionType.setText(data.get(i).getNutrition_type());
            holder.txt_benif_Id.setText(data.get(i).getBen_Id());
            holder.txt_benifType.setText(data.get(i).getBen_type());

            holder.lin_ben_child.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    showPopupListener.onShowpopup(i);
                }
            });


        }

        final int pos = i;


        if(data!=null)
        {
            if(!(data.get(i).getIsBenPer().equalsIgnoreCase("anyType{}")) || !(data.get(i).getIsBenPer().equalsIgnoreCase("")))
            {
                //if(data.get(i).getIsBenPer().equalsIgnoreCase("R")) // y changed to N
                if(data.get(i).getIsBenPer().equalsIgnoreCase("anyType{}")) // y changed to N
                {
                    if(type.equalsIgnoreCase("3"))
                    {
                        holder.tv_verified.setVisibility(View.GONE);
                        holder.ll_otp.setVisibility(View.VISIBLE);

                        holder.submit.setVisibility(View.VISIBLE);
                    }
                }

                else if(data.get(i).getIsBenPer().equalsIgnoreCase("Y"))
                {
                    if(type.equalsIgnoreCase("3"))
                    {
                        holder.tv_verified.setVisibility(View.VISIBLE);
                        holder.ll_otp.setVisibility(View.GONE);

                        holder.submit.setVisibility(View.GONE);
                    }
                }
            }

            else if(data.get(i).getBenPerDate().equalsIgnoreCase("anyType{}") || (data.get(i).getIsBenPer().equalsIgnoreCase("")))
            {
                holder.tv_verified.setVisibility(View.GONE);
                holder.ll_otp.setVisibility(View.VISIBLE);
            }

            else if(data.get(i).getBenPerDate()==null)
            {
                holder.tv_verified.setVisibility(View.GONE);
                holder.ll_otp.setVisibility(View.VISIBLE);
            }
        }

        final CheckUnCheck cn=new CheckUnCheck();

        final ViewHolder finalHolder = holder;
        final ViewHolder finalHolder1 = holder;

        final ViewHolder finalHolder2 = holder;
        finalHolder1.submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final OtpVerifyModel country = data.get(pos);
                no_noof_month = finalHolder2.editnoofmonth.getText().toString();
                if (no_noof_month.length()<5)
                {
                    Toast.makeText(context,"कृपया  OTP दर्ज करें" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try
                    {
                        otp=_encrptor.Decrypt(country.getOtp(), CommonPref.CIPER_KEY);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    //if (country.getOtp().equals(no_noof_month))
                    if (otp.equals(no_noof_month))
                    {

                        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        if (isTimeAutomatic(context))
                        {

                            if (checkBetween(currentDate, country.getOtp_valid_from(), country.getOtp_valid_to()))
                            // if (checkBetween("2020-11-02",country.getOtp_valid_from(),country.getOtp_valid_to()))
                            {
                                long c = updateAttendance1(country.getId(), "Y", no_noof_month, str_NutritionId, str_NutritionValue,country.getMonth(),country.getYear());
                                dataBaseHelper = new DataBaseHelper(context);
                                Log.d("vbhbjnjn  ", "^^^^^^^^^^^" + Awcid);
                                ArrayList<OtpVerifyModel> data1 = dataBaseHelper.getOtpListforadaptor(Awcid,month_id,year_id);

                                if (accno.length()>0){
                                    data2 = dataBaseHelper.getOTPBenListforshowlistByFilter(Awcid,accno,month_id,year_id);
                                }


                                if (c > 0) {
                                    Toast.makeText(context, "OTP Verified successfully", Toast.LENGTH_SHORT).show();
                                    finalHolder2.tv_verified.setVisibility(View.VISIBLE);
                                    finalHolder2.ll_otp.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show();
                                    finalHolder2.tv_verified.setVisibility(View.GONE);
                                    finalHolder2.ll_otp.setVisibility(View.VISIBLE);
                                }
                                if (accno.length()>0)
                                {
                                    refresh(data2);
                                }
                                else
                                    {
                                    refresh(data1);
                                }

                            }
                            else
                                {
                                Toast.makeText(context, "OTP expired please fetch current record", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            {
                            Toast.makeText(context, "Please use correct system date", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(context,    "OTP मैच नहीं हुआ, कृपया मेसेज द्वारा प्राप्त OTP डाले" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        GlobalVariables.benIDArrayList=benchek;
        OtpVerifyModel country = data.get(i);

        holder.tv_slno.setText(Integer.toString(pos1)+").");

        pos1++;

        notifyDataSetChanged();
        return view;
    }

    public  void updateAttendance(String benid,String status,String noMonth,String NutritionId,String NutritionValue)
    {
        DataBaseHelper localDB=new DataBaseHelper(context);
        ContentValues values = new ContentValues();
        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");
        values.put("BenPerDate", cdate);
        values.put("IsBenPer", status);

        String[] whereArgsss = new String[]{benid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("wdcBenDetails", values, "a_Id=?", whereArgsss);
    }

    public  long  updateAttendance1(String benid,String status,String noMonth,String NutritionId,String NutritionValue,String month,String yearid)
    {
        DataBaseHelper localDB=new DataBaseHelper(context);
        ContentValues values = new ContentValues();

        String cdate= Utiilties.getDateString("yyyy-MM-dd");
        cdate=cdate.replace("-","");

        values.put("BenPerDate", cdate);
        values.put("IsBenPer", status);
        values.put("IsBenUpdated","Y");

        values.put("ben_rej_reason",noMonth);

        String[] whereArgsss = new String[]{benid,month,yearid};
        SQLiteDatabase db = localDB.getWritableDatabase();
        long  c = db.update("OtpDetails", values, "a_Id=? and month_id=? and year_id=?", whereArgsss);

        Log.e("ISUPDATED",""+c);
        return c;
    }

    private class ViewHolder
    {
        TextView txt_f_and_mName,txt_benifType,txt_sex,txt_yr_of_birth,txt_childNmae,txt_husName,txt_wifeName,txt_cat,textViewSlNo,tv_slno,tv_motherNAme,txt_aadhar,tv_ben_name,tv_verified,txt_benif_Id,txt_Name,txt_NutritionType;
        CheckBox checkBox;
        LinearLayout lin_detail,lin_ben_child,lin_ben_women,ll_otp;

        RadioGroup radioattn;
        Button submit;
        RadioButton radio_Y_attn,radio_N_attn;
        Spinner spn_status_child,spn_status;
        EditText editnoofmonth;
        TextView lockstatus1,lockstatus;
    }

    public void refresh(ArrayList<OtpVerifyModel> events)
    {
        this.data.clear();
        this.data.addAll(events);
        notifyDataSetChanged();
    }



    public static boolean checkBetween(String dateToCheck, String startDate, String endDate)
    {
        boolean res = false;
        // SimpleDateFormat fmt1 = new SimpleDateFormat("dd-MMM-yyyy"); //22-05-2013
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd"); //22-05-2013
        // SimpleDateFormat fmt2 = new SimpleDateFormat("dd/MM/yyyy"); //22-05-2013
        SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd"); //22-05-2013
        try
        {
            Date requestDate = fmt2.parse(dateToCheck);
            //  Date requestDate = fmt2.parse("2020-11-01");
            Date fromDate = fmt1.parse(startDate);
            Date toDate = fmt1.parse(endDate);
            res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <=0;
        }
        catch(ParseException pex)
        {
            pex.printStackTrace();
        }
        return res;
    }

    public static boolean isTimeAutomatic(Context c)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        }
        else
        {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }

}

