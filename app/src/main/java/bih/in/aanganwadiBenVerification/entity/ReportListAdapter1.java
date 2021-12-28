package bih.in.aanganwadiBenVerification.entity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import bih.in.aanganwadiBenVerification.database.DataBaseHelper;

public class ReportListAdapter1 extends ArrayAdapter<ReportEntity> {

	Context context;
	int layoutResourceId;
	String fileName;
	// For Image loading via cache memory
	//public ImageLoader imageLoader;


	ArrayList<ReportEntity> data = new ArrayList<ReportEntity>();

	public ReportListAdapter1(Context context, int layoutResourceId,
                              ArrayList<ReportEntity> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ImageHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ImageHolder();

//			holder.txtId = (TextView) row.findViewById(R.id.report_id_no);
//			holder.txtDate = (TextView) row.findViewById(R.id.report_Date);
//			holder.txtName = (TextView) row.findViewById(R.id.report_Name);
//			holder.txtRemarks = (TextView) row.findViewById(R.id.report_Remarks);

			row.setTag(holder);
		} else {
			holder = (ImageHolder) row.getTag();
		}

		ReportEntity report = data.get(position);
		holder.txtId.setText(report.getAWCGOICode());

		String edate=report.getUpload_Date();

		StringTokenizer st1=new StringTokenizer(edate," ");
		edate=st1.nextToken();



		holder.txtDate.setText(edate);

		DataBaseHelper helper=new DataBaseHelper(context);
		//holder.txtName.setText(helper.getPanchayatName(report.getPanchayatCode(),context));

		holder.txtName.setText(report.getAWCName());

		holder.txtRemarks.setText(report.getRemarks());
		holder = (ImageHolder) row.getTag();
		return row;
	}




	static class ImageHolder {
		TextView txtId,txtDate,txtName,txtRemarks;
	}

}




