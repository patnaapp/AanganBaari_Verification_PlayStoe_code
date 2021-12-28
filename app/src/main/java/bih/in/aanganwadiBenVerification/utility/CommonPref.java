package bih.in.aanganwadiBenVerification.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import bih.in.aanganwadiBenVerification.entity.UserDetails;

public class CommonPref {

    static Context context;
    public static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static final String CIPER_KEY = "DGRC@NIC2020";

    CommonPref()
    {

    }

    CommonPref(Context context)
    {
        CommonPref.context = context;
    }

    public static void setUserDetails(Context context, UserDetails userInfo)
    {

        String key = "_USER_DETAILS";

        SharedPreferences prefs = context.getSharedPreferences(key,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("UserId", userInfo.getUserID());
        editor.putString("pass", userInfo.getPassword());
        editor.putString("UserName", userInfo.getName());
        editor.putString("Dist_Code", userInfo.getDist_Code());
        editor.putString("Dist_Name", userInfo.getDist_Name());
        editor.putString("Project_Code", userInfo.getProject_Code());
        editor.putString("Project_Name", userInfo.getProject_Name());
        editor.putString("Panchayat_Code", userInfo.getPanchayat_Code());
        editor.putString("Panchayat_Name", userInfo.getPanchayat_Name());
        editor.putString("Awc_Code", userInfo.getAwc_Code());
        editor.putString("Awc_Name", userInfo.getAwc_Name());
        editor.putString("Role", userInfo.getUserrole());
        //editor.putString("Degignation", userInfo.getDegignation());
        editor.putString("MobileNo", userInfo.getMobileNo());
        editor.putString("OTP", userInfo.getOtp());


        editor.commit();

    }

    public static UserDetails getUserDetails(Context context)
    {
        String key = "_USER_DETAILS";
        UserDetails userInfo = new UserDetails();
        SharedPreferences prefs = context.getSharedPreferences(key,Context.MODE_PRIVATE);

        userInfo.setUserID(prefs.getString("UserId", ""));
        userInfo.setName(prefs.getString("UserName", ""));
        userInfo.setDist_Code(prefs.getString("Dist_Code", ""));
        userInfo.setDist_Name(prefs.getString("Dist_Name", ""));
        userInfo.setBlockCode(prefs.getString("Project_Code", ""));
        userInfo.setBlockName(prefs.getString("Project_Name", ""));
        userInfo.setPanchayat_Code(prefs.getString("Panchayat_Code", ""));
        userInfo.setPanchayat_Name(prefs.getString("Panchayat_Name", ""));
        userInfo.setAwc_Code(prefs.getString("Awc_Code", ""));
        userInfo.setAwc_Name(prefs.getString("Awc_Name", ""));
        userInfo.setUserrole(prefs.getString("Role", ""));
        userInfo.setMobileNo(prefs.getString("MobileNo", ""));
        userInfo.setOtp(prefs.getString("OTP", ""));

        return userInfo;
    }

    public static void setCheckUpdate(Context context, long dateTime)
    {

        String key = "_CheckUpdate";

        SharedPreferences prefs = context.getSharedPreferences(key,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();


        dateTime=dateTime+1*3600000;
        editor.putLong("LastVisitedDate", dateTime);

        editor.commit();

    }

    public static int getCheckUpdate(Context context)
    {

        String key = "_CheckUpdate";

        SharedPreferences prefs = context.getSharedPreferences(key,Context.MODE_PRIVATE);

        long a = prefs.getLong("LastVisitedDate", 0);

        if(System.currentTimeMillis()>a)
            return 1;
        else
            return 0;
    }

    public static void setAwcId(Activity activity, String awcid)
    {
        String key = "_Awcid";
        SharedPreferences prefs = activity.getSharedPreferences(key,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("code2", awcid);
        editor.commit();
    }

    public static String getAwcId(Activity activity)
    {
        String key = "_Awcid";
        UserDetails userInfo = new UserDetails();
        SharedPreferences prefs = activity.getSharedPreferences(key,Context.MODE_PRIVATE);
        String code2=prefs.getString("code2","");
        return code2;
    }
}
