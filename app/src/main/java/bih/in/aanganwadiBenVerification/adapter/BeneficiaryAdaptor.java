package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.activity.BeneficiaryStatusActivity;
import bih.in.aanganwadiBenVerification.entity.CatWiseBenStatus;



public class BeneficiaryAdaptor extends BaseAdapter
{
    Activity activity;
    LayoutInflater mInflater;
    ArrayList<CatWiseBenStatus> ThrList=new ArrayList<>();

    public BeneficiaryAdaptor(BeneficiaryStatusActivity listViewshowedit, ArrayList<CatWiseBenStatus> rlist)
    {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return ThrList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        //if (convertView == null) {
        convertView = mInflater.inflate(R.layout.adaptor_benediciary, null);

        holder = new ViewHolder();
        holder.tv_school=(TextView)convertView.findViewById(R.id.tv_school);
        holder.tv_dise_code=(TextView)convertView.findViewById(R.id.tv_dise_code);
        holder.tv_total=(TextView)convertView.findViewById(R.id.tv_total);
        holder.tv_male=(TextView)convertView.findViewById(R.id.tv_male);

        holder.tv_total_gn=(TextView)convertView.findViewById(R.id.tv_total_gn);
        holder.tv_male_gn=(TextView)convertView.findViewById(R.id.tv_male_gn);

        holder.tv_total_obc=(TextView)convertView.findViewById(R.id.tv_total_obc);
        holder.tv_male_obc=(TextView)convertView.findViewById(R.id.tv_male_obc);

        holder.tv_total_ebc=(TextView)convertView.findViewById(R.id.tv_total_ebc);
        holder.tv_male_ebc=(TextView)convertView.findViewById(R.id.tv_male_ebc);

        convertView.setTag(holder);

        String inspectedBy = "";

        holder.tv_school.setText(ThrList.get(position).getSchoolName());
        holder.tv_dise_code.setText(ThrList.get(position).getDiseCode());
        holder.tv_total.setText(ThrList.get(position).getTotalBen());
        holder.tv_male.setText(ThrList.get(position).getTotalBenMale());

        holder.tv_total_gn.setText(ThrList.get(position).getTotalBenGen());
        holder.tv_male_gn.setText(ThrList.get(position).getTotalBenMaleGen());

        holder.tv_total_obc.setText(ThrList.get(position).getTOBC());
        holder.tv_male_obc.setText(ThrList.get(position).getOBCMale());

        holder.tv_total_ebc.setText(ThrList.get(position).getTEBC());
        holder.tv_male_ebc.setText(ThrList.get(position).getEBCMale());

        return convertView;
    }

    private class ViewHolder
    {
        TextView tv_school,tv_dise_code,tv_total,tv_male,tv_total_gn,tv_male_gn;
        TextView tv_total_obc,tv_male_obc,tv_total_ebc,tv_male_ebc;
    }
}
