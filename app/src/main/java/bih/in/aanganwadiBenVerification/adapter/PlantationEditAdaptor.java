package bih.in.aanganwadiBenVerification.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.activity.PlantationInspectionActivity;

import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.utility.Utiilties;

public class PlantationEditAdaptor extends BaseAdapter {

    public DataBaseHelper dataBaseHelper;

    Activity activity;
    LayoutInflater mInflater;

    ArrayList<PlantationDetail> ThrList=new ArrayList<>();
    String version;



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
            convertView = mInflater.inflate(R.layout.adaptor_plantation_edit, null);

            holder = new ViewHolder();
        holder.tv_work_code=(TextView)convertView.findViewById(R.id.tv_work_code);
        holder.tv_work_name=(TextView)convertView.findViewById(R.id.tv_work_name);
        holder.tv_work_type=(TextView)convertView.findViewById(R.id.tv_work_type);
        holder.tv_agency_name=(TextView)convertView.findViewById(R.id.tv_agency_name);

        holder.btn_remove=(Button) convertView.findViewById(R.id.btn_remove);
        holder.btn_edit=(Button)convertView.findViewById(R.id.btn_edit);
        holder.btn_upload=(Button)convertView.findViewById(R.id.btn_upload);

        convertView.setTag(holder);

        holder.tv_work_code.setText(ThrList.get(position).getWorkCode());
        holder.tv_work_name.setText(ThrList.get(position).getWorkName());
        holder.tv_work_type.setText(ThrList.get(position).getWorktype());
        holder.tv_agency_name.setText(ThrList.get(position).getAgencyName());

            holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(activity)
                            .setIcon(R.drawable.logo1)
                            .setTitle(R.string.confirmation)
                            .setMessage("क्या आप डाटा हटाना चाहते है?")
                            .setCancelable(false)
                            .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dataBaseHelper = new DataBaseHelper(activity);
                                    String inspectionid = String.valueOf(ThrList.get(position).getInspectionID());
                                    long c = dataBaseHelper.resetPlantationInspectionUpdatedData(inspectionid);

                                    if(c>0)
                                    {
                                        Toast.makeText(activity, "Deleted Successfully",Toast.LENGTH_SHORT).show();
                                        ThrList.remove(position);
                                        notifyDataSetChanged();
                                    }
                                    else
                                    {
                                        Toast.makeText(activity, "Failed to delete",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            })
                            .setNegativeButton("नहीं", null)
                            .show();


                }
            });

            holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlantationDetail detail = ThrList.get(position);
                    Intent i=new Intent(activity, PlantationInspectionActivity.class);
                    i.putExtra("data", detail);
                    activity.startActivity(i);
                }
            });

            holder.btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(activity, "Upload", Toast.LENGTH_SHORT).show();

                    if(ThrList.get(position).getPhoto() != null && ThrList.get(position).getPhoto1() != null){
                        new AlertDialog.Builder(activity)
                                .setIcon(R.drawable.logo1)
                                .setTitle(R.string.confirmation)
                                .setMessage("क्या आप डाटा अपलोड करना चाहते है?")
                                .setCancelable(false)
                                .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (Utiilties.isOnline(activity)) {
                                            PlantationDetail info = ThrList.get(position);
                                           // new UploadPlantationDetail(info, position, version).execute();
                                        }
                                        else {

                                            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("नहीं", null)
                                .show();
                    }else{
                        Toast.makeText(activity, "इस डेटा का फोटो नहीं लिया गया है, कृपया फोटो लें और फिर अपलोड करें", Toast.LENGTH_SHORT).show();
                    }
                }
            });

//        }
//        else {
//            holder = (PlantationEditAdaptor.ViewHolder) convertView.getTag();
//        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_work_code,tv_work_name,tv_work_type,tv_agency_name;
        Button btn_remove,btn_edit,btn_upload;

    }

   /* private class UploadPlantationDetail extends AsyncTask<String, Void, String> {
        PlantationDetail data;
        String version;
        int position;
        private final ProgressDialog dialog = new ProgressDialog(activity);

        UploadPlantationDetail(PlantationDetail data, int position, String version) {
            this.data = data;
            this.position = position;
            this.version = version;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("अपलोड हो राहा है...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


            String res = WebServiceHelper.uploadPlantationDate(data);
            return res;

        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue",""+result);

            if (result != null) {
                if (result.equalsIgnoreCase("1")) {
                    DataBaseHelper localDBHelper = new DataBaseHelper(activity);
                    // long c = placeData.deleterowconLab(userid);
                    long isDel = localDBHelper.resetPlantationInspectionUpdatedData(String.valueOf(this.data.getInspectionID()));
                    if (isDel > 0) {
                        Log.e("messagdelete", "Data deleted !!");
                        ThrList.remove(position);
                        notifyDataSetChanged();
                    } else {
                        Log.e("message", "data is uploaded but not deleted !!");
                    }

                    Toast.makeText(activity, "प्रेषण हो गया", Toast.LENGTH_SHORT).show();
                    chk_msg("डेटा अपलोड हो गया", "डेटा अपलोड हो गया");
                }
                else  if (result.equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "प्रेषण फेल !", Toast.LENGTH_SHORT).show();
                }

            }
            else {

                Toast.makeText(activity, "null record", Toast.LENGTH_SHORT).show();
            }



        }
    }*/

    public void refresh(ArrayList<PlantationDetail> events) {
        this.ThrList.clear();
        this.ThrList.addAll(events);
        notifyDataSetChanged();
    }

    public void chk_msg(String title, String msg) {
        // final String wantToUpdate;
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setCancelable(false);
        ab.setIcon(R.drawable.headerbrds);
        ab.setTitle(title);
        //ab.setMessage(msg);
        Dialog dialog = new Dialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();

            }
        });

        // ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
        ab.show();
    }

}