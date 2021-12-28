package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.utility.Utiilties;

public class MultipulPhotoActivity2 extends Activity implements View.OnClickListener
{
    private final static int CAMERA_PIC = 99;
    ImageView img1, img2, img3, img4,img5;
    Button btnOk;

    int ThumbnailSize = 0;
    int exitCount = 1;
    String PID = "0";
    String AREA = "";
    //ArrayList<Intent> imageData;
    Intent imageData1,imageData2,imageData3,imageData4,imageData5;
    String isOpen = "",purpose="";
    TextView text_p1;
    LinearLayout linearLayout2;
    Boolean hasImg1=false, hasImg2=false;
    RelativeLayout re_p2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiple_photo);
        // Utiilties.setActionBarBackground(this);
        // Utiilties.setStatusBarColor(this);

        PID = String.valueOf(getIntent().getIntExtra("KEY_PID", 0));
        isOpen = getIntent().getStringExtra("isOpen");
        purpose = getIntent().getStringExtra("pupose");

        if (PID == null)
        {
            PID = "0";
        }

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        img1 = (ImageView) findViewById(R.id.imageButton1);
        img2 = (ImageView) findViewById(R.id.imageButton2);
        img3 = (ImageView) findViewById(R.id.imageButton3);
        img4 = (ImageView) findViewById(R.id.imageButton4);
        img5 = (ImageView) findViewById(R.id.imageButton5);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        re_p2 = (RelativeLayout) findViewById(R.id.re_p2);
        text_p1 = (TextView) findViewById(R.id.text_p1);

        btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnClickListener(this);
        if (!purpose.equalsIgnoreCase("edit"))
        {
            btnOk.setEnabled(true);
            btnOk.setBackgroundResource(R.drawable.buttonbackshape1);
        }

        if (displaymetrics.widthPixels < displaymetrics.heightPixels)
        {
            ThumbnailSize = displaymetrics.widthPixels / 2;
            //img1.getLayoutParams().height = ThumbnailSize;
            //img3.getLayoutParams().height = ThumbnailSize;
        }
        else
        {

            ThumbnailSize = displaymetrics.widthPixels / 4;
            img1.getLayoutParams().height = img2.getLayoutParams().height = img3.getLayoutParams().height = img4.getLayoutParams().height = img5.getLayoutParams().height = ThumbnailSize;
        }
        if (isOpen.equals("2"))
        {
            text_p1.setText("बंद केंद्र की फोटो");
            linearLayout2.setVisibility(View.GONE);
            re_p2.setVisibility(View.GONE);
        }

        DataBaseHelper placeData = new DataBaseHelper(this);
        SQLiteDatabase db = placeData.getReadableDatabase();
        //int HIDID = getIntent().getIntExtra("KEY_HIDID", 0);
        Cursor cursor = db.rawQuery("SELECT Photo1,Photo2,Photo3,Photo4 FROM WellInspectionDetail where id=?",new String[]{String.valueOf(PID)});

        Log.e("PID  ", PID);
        if (cursor.moveToNext())
        {

            if (!cursor.isNull(0))
            {

                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("Photo1"));
                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length);
                img1.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                        ThumbnailSize, ThumbnailSize));
                img1.setOnClickListener(this);
                hasImg1 = true;

            }
            else
            {
                img1.setOnClickListener(this);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
                img5.setEnabled(false);

            }

            if (!cursor.isNull(1))
            {
                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("Photo2"));

                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
                img2.setImageBitmap(Utiilties.GenerateThumbnail(bmp,ThumbnailSize, ThumbnailSize));
                img2.setOnClickListener(this);
                hasImg2 = true;
                btnOk.setEnabled(true);
                btnOk.setBackgroundResource(R.drawable.buttonbackshape);
            }
            else
            {
                img2.setOnClickListener(this);
                img5.setOnClickListener(this);
                img3.setEnabled(true);
                img4.setEnabled(true);

            }
//            if (!cursor.isNull(2)) {
//                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("Photo3"));
//
//                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
//                        imgData.length);
//                img3.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
//                        ThumbnailSize, ThumbnailSize));
//                img3.setOnClickListener(this);
//            } else {
//                img3.setOnClickListener(this);
//                img4.setEnabled(false);
//            }
//            if (!cursor.isNull(3)) {
//                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("Photo4"));
//
//                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
//                        imgData.length);
//                img4.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
//                        ThumbnailSize, ThumbnailSize));
//                img4.setOnClickListener(this);
//            } else {
//                img4.setOnClickListener(this);
//
//            }

        }
        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_multiple_photo, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnOk)) {

            if (!hasImg1 || !hasImg2 /*&& imageData3==null&& imageData4==null&& imageData5==null&& purpose.equals("edit")*/){
                Toast.makeText(this, "कृपया दोनों फोटो लें", Toast.LENGTH_SHORT).show();
                //finish();
            }else {
                saveImage();
                Intent iUserHome = new Intent(getApplicationContext(),
                        DashboardActivity.class);
                iUserHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(iUserHome);
            }
        } else {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                buildAlertMessageNoGps();
            }
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {

                Intent iCamera = new Intent(getApplicationContext(),
                        CameraActivity.class);
                if (view.equals(img1))
                    iCamera.putExtra("KEY_PIC", "1");
                else if (view.equals(img2))
                    iCamera.putExtra("KEY_PIC", "2");
                else if (view.equals(img3))
                    iCamera.putExtra("KEY_PIC", "3");
                else if (view.equals(img4))
                    iCamera.putExtra("KEY_PIC", "4");
                else if (view.equals(img5))
                    iCamera.putExtra("KEY_PIC", "5");
                startActivityForResult(iCamera, CAMERA_PIC);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK)
                {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");


                    //imageData.add(data);
                    if (isOpen.equals("2")) btnOk.setEnabled(true);

                    switch (data.getIntExtra("KEY_PIC", 0))
                    {
                        case 1:
                            imageData1=data;
                            Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                                    imgData.length);
                            img1.setScaleType(ImageView.ScaleType.FIT_XY);
                            img1.setImageBitmap(Utiilties.GenerateThumbnail(bmp,500, 500));
                            //img1.setOnClickListener(null);
                            hasImg1 = true;
                            //btnOk.setEnabled(true);
                            //btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            if (isOpen.equals("2"))
                            {
                                btnOk.setEnabled(true);
                                btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            }
                            else img2.setEnabled(true);
                            break;

                        case 2:
                            imageData2=data;
                            img2.setScaleType(ImageView.ScaleType.FIT_XY);
                            img2.setImageBitmap(Utiilties.GenerateThumbnail(BitmapFactory.decodeByteArray(imgData, 0,imgData.length), 500, 500));
                            //img2.setOnClickListener(null);
                            hasImg2 = true;
                            img3.setEnabled(true);
                            btnOk.setEnabled(true);
                            btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            break;

                        case 3:
                            imageData3=data;
                            img3.setScaleType(ImageView.ScaleType.FIT_XY);
                            img3.setImageBitmap(Utiilties.GenerateThumbnail(BitmapFactory.decodeByteArray(imgData, 0,imgData.length), 500, 500));
                            //img3.setOnClickListener(null);
                            img4.setEnabled(true);
                            btnOk.setEnabled(true);
                            btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            break;

                        case 4:
                            imageData4=data;
                            img4.setScaleType(ImageView.ScaleType.FIT_XY);
                            img4.setImageBitmap(Utiilties.GenerateThumbnail(BitmapFactory.decodeByteArray(imgData, 0,imgData.length), 500,500));
                            img5.setEnabled(true);
                            btnOk.setEnabled(true);
                            btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            break;
                        case 5:
                            imageData5=data;
                            img5.setScaleType(ImageView.ScaleType.FIT_XY);
                            img5.setImageBitmap(Utiilties.GenerateThumbnail(BitmapFactory.decodeByteArray(imgData, 0,imgData.length), 500,500));
                            //img4.setOnClickListener(null);
                            break;

                    }


                }

        }

    }


    private void saveImage()
    {

        int i = 0;
        DataBaseHelper placeData = new DataBaseHelper(MultipulPhotoActivity2.this);
        SQLiteDatabase db = placeData.getWritableDatabase();
        for (i = 0; i < 5; i++)
        {
            ContentValues values = new ContentValues();
            String[] whereArgs;
            byte[] imgData;
            switch (i)
            {
                case 0:
                    //GPSTime
                    if (imageData1!=null)
                    {
                        imgData = imageData1.getByteArrayExtra("CapturedImage");

                        values.put("Photo1", imgData);
                        /*values.put("EntryDate",
                                imageData1.getStringExtra("CapturedTime"));*/
                        values.put("Latitude_Mob",String.valueOf(imageData1.getStringExtra("Lat")));
                        values.put("Longitude_Mob",String.valueOf(imageData1.getStringExtra("Lng")));
                      /*  values.put("UploadDate",
                                String.valueOf(imageData1.getStringExtra("GPSTime")));*/
                        whereArgs = new String[]{String.valueOf(PID)};
                        long c= db.update("WellInspectionDetail", values, "id=?", whereArgs);
                        btnOk.setEnabled(true);
                        if(c>0)
                        {
                            Log.e("IMAGE","Updated");
                        }
                        else
                        {
                            Log.e("IMAGE","Not Updated");
                        }
                    }
                    break;
                case 1:
                    if (imageData2!=null)
                    {
                        imgData = imageData2.getByteArrayExtra("CapturedImage");

                        values.put("Photo2", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        db.update("WellInspectionDetail", values, "id=?", whereArgs);
                    }
                    break;
                case 2:
                    if (imageData3!=null)
                    {
                        imgData = imageData3.getByteArrayExtra("CapturedImage");
                        values.put("Photo3", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        long c1= db.update("WellInspectionDetail", values, "id=?", whereArgs);
                        if(c1>0)
                        {
                            Log.e("IMAGEE","Updated");
                        }
                        else
                        {
                            Log.e("IMAGEE","not updttad");
                        }
                    }
                    break;
                case 3:
                    if (imageData4!=null)
                    {
                        imgData=imageData4.getByteArrayExtra("CapturedImage");

                        values.put("Photo4", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        db.update("WellInspectionDetail", values, "id=?", whereArgs);
                    }
                    break;
            }
        }

        db.close();
    }

    private void buildAlertMessageNoGps()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GPS");
        builder.setMessage("GPS is turned OFF...\nDo U Want Turn On GPS..")
//		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Turn on GPS", new DialogInterface.OnClickListener()
                {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                    {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                    {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed()
    {
        if (purpose.equalsIgnoreCase("new"))
        {
            if (exitCount < 4)
            {
                Toast.makeText(this, "कृपया दोनों फोटो लें अन्यथा सत्यापन/सर्वेक्षण पूर्ण नहीं होगा ("+exitCount+")", Toast.LENGTH_SHORT).show();
                exitCount++;
            }
            else
                {
                DataBaseHelper db = new DataBaseHelper(this);
                long isDel = db.resetWellInspectionUpdatedData(String.valueOf(PID));
                finish();
            }
        }
        else
            {
            finish();
        }
    }
}

