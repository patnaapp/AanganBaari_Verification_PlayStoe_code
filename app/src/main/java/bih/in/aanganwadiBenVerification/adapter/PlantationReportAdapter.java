package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.activity.PlantationReportActivity;
import bih.in.aanganwadiBenVerification.entity.PlantationReportEntity;

public class PlantationReportAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<PlantationReportEntity> ThrList=new ArrayList<>();

    public PlantationReportAdapter(PlantationReportActivity listViewshowedit, ArrayList<PlantationReportEntity> rlist) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ThrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        //if (convertView == null) {
        convertView = mInflater.inflate(R.layout.adaptor_plantation_report, null);holder = new PlantationReportAdapter.ViewHolder();

        holder.tv_work_code=(TextView)convertView.findViewById(R.id.tv_work_code);
        holder.tv_work_name=(TextView)convertView.findViewById(R.id.tv_work_name);
        holder.tv_work_type=(TextView)convertView.findViewById(R.id.tv_work_type);
        holder.tv_agency_name=(TextView)convertView.findViewById(R.id.tv_agency_name);
        holder.tv_inspected_date=(TextView)convertView.findViewById(R.id.tv_inspected_date);

        convertView.setTag(holder);

        holder.tv_work_code.setText(ThrList.get(position).getWorkCode());
        holder.tv_work_name.setText(ThrList.get(position).getWorkName());
        holder.tv_work_type.setText(ThrList.get(position).getWorktype());
        holder.tv_agency_name.setText(ThrList.get(position).getAgencyName());
        holder.tv_inspected_date.setText(ThrList.get(position).getWorkStateFyear());
//        }
//        else {
//            holder = (PlantationReportAdapter.ViewHolder) convertView.getTag();
//        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_work_code,tv_work_name,tv_work_type,tv_agency_name,tv_inspected_date;


    }



}
