package bih.in.aanganwadiBenVerification.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import bih.in.aanganwadiBenVerification.R;
import bih.in.aanganwadiBenVerification.database.DataBaseHelper;
import bih.in.aanganwadiBenVerification.entity.UserDetails;

public class ChooseSchemeActivity extends Activity
{
    LinearLayout lin1,lin2,lin3,lin4;
    DataBaseHelper localDB ;
    SQLiteDatabase db;
    DataBaseHelper databaseHelper;
    TextView tv_version;
    UserDetails userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scheme);
        localDB = new DataBaseHelper(this);
        databaseHelper=new DataBaseHelper(this);

        db = localDB.getWritableDatabase();


        try
        {
            localDB.createDataBase();
        }
        catch (IOException ioe)
        {
            throw new Error("Unable to create database");
        }

        try
        {
            localDB.openDataBase();
        }
        catch (SQLException sqle)
        {
            throw sqle;
        }

        String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
        String password = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", "");

        userInfo = databaseHelper.getUserDetails(username.toLowerCase(), password);


        lin1=findViewById(R.id.lin1);
        lin2=findViewById(R.id.lin2);
        lin3=findViewById(R.id.lin3);
        lin4=findViewById(R.id.lin4);
        tv_version=findViewById(R.id.tv_version);
        if(userInfo.getUserrole().equalsIgnoreCase("AWC"))
        {
            lin3.setVisibility(View.VISIBLE);
            lin4.setVisibility(View.VISIBLE);
        }
        else
        {
            lin3.setVisibility(View.GONE);
            lin4.setVisibility(View.GONE);
        }
        String version = null;

        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        tv_version.setText("App Version :- "+ " "+ version);
        CREATETABLEIFNOTEXIST1();
        CREATETABLEIFNOTEXIST2();

        lin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                intent.putExtra("ForActivity","1");
                startActivity(intent);
                finish();
            }
        });

        lin2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(),WdcDashBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lin3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                intent.putExtra("ForActivity","2");
                startActivity(intent);
                finish();
            }
        });

        lin4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(getApplicationContext(),AttendanceActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    public void CREATETABLEIFNOTEXIST1()
    {
        db = localDB.getWritableDatabase();
        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS wdcBenDetails (a_Id TEXT, Dist_Name TEXT, Block_Name TEXT, Ben_Id TEXT, Ben_Name TEXT, BenFh_Name TEXT, Ben_Gender TEXT, Ben_MobNo TEXT, Ben_AadhaarNo TEXT, Ben_AcHName TEXT, Ben_AcNo TEXT, Ben_Ifsc TEXT, Bank_Name TEXT, IsBenPer TEXT, BenPerDate TEXT, IsRecordUpdated TEXT, IsBenUpdated TEXT, AwcId TEXT, ben_rej_reason TEXT )");
            localDB.getWritableDatabase().close();
            Log.e("CREATE SUCCESS ", "wdcBenDetails");
            localDB.getWritableDatabase().close();
            //db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("CREATE Failed ", "wdcBenDetails");
        }
    }

    public void CREATETABLEIFNOTEXIST2()
    {
        db = localDB.getWritableDatabase();
        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS OtpDetails (a_Id TEXT, Dist_Name TEXT, Block_Name TEXT, Ben_Id TEXT, Ben_Name TEXT, BenFh_Name TEXT, Ben_Gender TEXT, Ben_MobNo TEXT, Ben_AadhaarNo TEXT, Ben_AcHName TEXT, Ben_AcNo TEXT, Ben_Ifsc TEXT, Bank_Name TEXT, IsBenPer TEXT, BenPerDate TEXT, IsRecordUpdated TEXT, IsBenUpdated TEXT, AwcId TEXT, ben_rej_reason TEXT, ben_otp TEXT, Ben_Mname TEXT, Ben_Categ TEXT, otp_valid_from TEXT, otp_valid_to TEXT, Is_Verify TEXT, Yob TEXT, BenId TEXT, NoofMonth TEXT, nutrition TEXT, bentype TEXT, gender TEXT )");
            localDB.getWritableDatabase().close();
            Log.e("CREATE SUCCESS ", "OtpDetails");
            localDB.getWritableDatabase().close();
            //db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("CREATE Failed ", "OtpDetails");
        }
    }

}
