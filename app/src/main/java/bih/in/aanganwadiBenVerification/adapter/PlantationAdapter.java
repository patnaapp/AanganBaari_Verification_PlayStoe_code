package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.activity.PlantationListActivity;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;

public class PlantationAdapter extends BaseAdapter
{

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<PlantationDetail> ThrList=new ArrayList<>();
    String panchayatCode,panchayatName="";

    public PlantationAdapter(PlantationListActivity listViewshowedit, ArrayList<PlantationDetail> rlist)
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
        convertView = mInflater.inflate(R.layout.adaptor_plantation, null);

        holder = new ViewHolder();
        holder.tv_work_code=(TextView)convertView.findViewById(R.id.tv_work_code);
        holder.tv_work_name=(TextView)convertView.findViewById(R.id.tv_work_name);
        holder.tv_work_type=(TextView)convertView.findViewById(R.id.tv_work_type);
        holder.tv_agency_name=(TextView)convertView.findViewById(R.id.tv_agency_name);
        holder.tv_inspected_by=(TextView)convertView.findViewById(R.id.tv_inspected_by);

        convertView.setTag(holder);

        String inspectedBy = "";
        if(ThrList.get(position).getIsInspectedByDSTAE().equals("Y"))
        {
            inspectedBy += "DSTAE";
        }
        if(ThrList.get(position).getIsInspectedByDSTEE().equals("Y")){
            if(!inspectedBy.equals("")){
                inspectedBy += ", ";
            }
            inspectedBy += "DSTEE";
        }
        if(ThrList.get(position).getIsInspectedByDSTDRDA().equals("Y")){
            if(!inspectedBy.equals("")){
                inspectedBy += ", ";
            }
            inspectedBy += "DSTDRDA";
        }
        if(ThrList.get(position).getIsInspectedByDSTDDC().equals("Y")){
            if(!inspectedBy.equals("")){
                inspectedBy += ", ";
            }
            inspectedBy += "DSTDDC";
        }

        if(inspectedBy.equals("")){
            inspectedBy = "none";
        }

        holder.tv_work_code.setText(ThrList.get(position).getWorkCode());
        holder.tv_work_name.setText(ThrList.get(position).getWorkName());
        holder.tv_work_type.setText(ThrList.get(position).getWorkStateFyear());
        holder.tv_agency_name.setText(ThrList.get(position).getAgencyName());
        holder.tv_inspected_by.setText(inspectedBy.trim());
        return convertView;
    }


    private class ViewHolder {
        TextView tv_work_code,tv_work_name,tv_work_type,tv_agency_name,tv_inspected_by;
        LinearLayout ll_inspected_by;
        Button btn_map,btn_inspect;

    }



}
