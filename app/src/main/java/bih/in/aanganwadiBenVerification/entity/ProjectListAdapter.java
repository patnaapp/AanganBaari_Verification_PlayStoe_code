package bih.in.aanganwadiBenVerification.entity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectListAdapter extends ArrayAdapter<ProjectListClass> {
		Context context;
		int layoutResourceId;
		String fileName;

		ArrayList<ProjectListClass> data = new ArrayList<ProjectListClass>();

		public ProjectListAdapter(Context context, int layoutResourceId, ArrayList<ProjectListClass> data) {
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

//				holder.projectName = (TextView) row.findViewById(R.id.projectName);
//				holder.countText = (TextView) row.findViewById(R.id.count);

				row.setTag(holder);
			} else {
				holder = (ImageHolder) row.getTag();
			}

			ProjectListClass projectListClass = data.get(position);

			holder.projectName.setText(projectListClass.getProjectName());
			holder.countText.setText(projectListClass.getCount());

			holder = (ImageHolder) row.getTag();
			return row;
		}

		static class ImageHolder {
			TextView projectName,countText;
		}
		
		
		

	}




