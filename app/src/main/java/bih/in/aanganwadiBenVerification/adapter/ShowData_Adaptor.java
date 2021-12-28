package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;

public class ShowData_Adaptor extends BaseAdapter {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<String> ThrList = new ArrayList<>();
    String panchayatCode, panchayatName = "";
    String Type="";

    public ShowData_Adaptor(Activity listViewshowedit, ArrayList<String> rlist,String LType) {
        this.activity = listViewshowedit;
        this.ThrList = rlist;
        mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
        //if (convertView == null) {
        convertView = mInflater.inflate(R.layout.adaotor_list_for_check, null);

        holder = new ViewHolder();
       // holder.tv_work_code = (TextView) convertView.findViewById(R.id.tv_work_code);

        convertView.setTag(holder);



       // holder.tv_work_code.setText(ThrList.get(position).getWorkCode());

        return convertView;
    }


    private class ViewHolder {


    }
}
