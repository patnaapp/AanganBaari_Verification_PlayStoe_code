package bih.in.aanganwadiBenVerification.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.entity.Block;
import bih.in.aanganwadiBenVerification.entity.DataProgress;
import bih.in.aanganwadiBenVerification.entity.District;
import bih.in.aanganwadiBenVerification.entity.Financial_Month;
import bih.in.aanganwadiBenVerification.entity.Financial_Year;
import bih.in.aanganwadiBenVerification.entity.LocalFieldInfo;
import bih.in.aanganwadiBenVerification.entity.LocalSpinnerData;
import bih.in.aanganwadiBenVerification.entity.Nutrition;
import bih.in.aanganwadiBenVerification.entity.OtpVerifyModel;
import bih.in.aanganwadiBenVerification.entity.PanchayatData;
import bih.in.aanganwadiBenVerification.entity.PanchayatEntity;
import bih.in.aanganwadiBenVerification.entity.PanchayatWeb;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.entity.PlantationSiteEntity;
import bih.in.aanganwadiBenVerification.entity.PondEncroachmentEntity;
import bih.in.aanganwadiBenVerification.entity.PondEntity;
import bih.in.aanganwadiBenVerification.entity.PondInspectionDetail;
import bih.in.aanganwadiBenVerification.entity.PondLakeDepartmentEntity;
import bih.in.aanganwadiBenVerification.entity.SanrachnaTypeEntity;
import bih.in.aanganwadiBenVerification.entity.SectorWeb;
import bih.in.aanganwadiBenVerification.entity.SevikaSahaikaEntity;
import bih.in.aanganwadiBenVerification.entity.SpinnerMaster;
import bih.in.aanganwadiBenVerification.entity.Sub_abyb;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.entity.VillageListEntity;
import bih.in.aanganwadiBenVerification.entity.VoutcherEntity;
import bih.in.aanganwadiBenVerification.entity.WellInspectionEntity;
import bih.in.aanganwadiBenVerification.entity.ward;
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import bih.in.aanganwadiBenVerification.utility.CommonPref;
import bih.in.aanganwadiBenVerification.utility.Encriptor;
import bih.in.aanganwadiBenVerification.utility.Utiilties;
import io.michaelrocks.paranoid.Obfuscate;

//import bih.in.jaljeevanharyali.activity.NewEnrollmentActivity;


/**
 * Helper to the database, manages versions and creation
 */
@Obfuscate
public class DataBaseHelper extends SQLiteOpenHelper {
    //private static String DB_PATH = "";
    private static String DB_PATH = "/data/data/app.bih.in.nic.epacsmgmt/databases/";
    //private static String DB_NAME = "eCountingAC.sqlite";
    private static String DB_NAME = "PACSDB1";

    Encriptor _encrptor;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    SQLiteDatabase db;

    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {


            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;


    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */


    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist


        } else {
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            //this.getReadableDatabase();

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;

    }

    public boolean databaseExist() {


        File dbFile = new File(DB_PATH + DB_NAME);

        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        this.getReadableDatabase().close();
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }

    public void openDataBase() throws SQLException {

        // Open the database
        this.getReadableDatabase();
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public long insertUserDetails(UserDetails result, String pass) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("UserId", result.getUserID().toLowerCase());
            values.put("UserName", result.getUserName());
            values.put("UserPassword", pass);
            values.put("Dist_Code", result.getDistrictCode());
            values.put("Dist_Name", result.getDistName());
            values.put("Project_Code", result.getProject_Code());
            values.put("Project_Name", result.getProject_Name());
            values.put("SectorCode", result.getSector_Code());
            values.put("SectorName", result.getSector_Name());
            values.put("Awc_Code", result.getAwc_Code());
            values.put("Awc_Name", result.getAwc_Name());
            values.put("Role", result.getUserrole());
            values.put("MobileNo", result.getMobileNo());
            values.put("OTP", result.getOtp());

            String[] whereArgs = new String[]{result.getUserID()};

            c = db.update("UserLogin", values, "UserId=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insert("UserLogin", null, values);
                //c = db.insertWithOnConflict("UserLogin", null, values,SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }

    public UserDetails getUserDetails(String userId, String pass) {

        UserDetails userInfo = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId.trim(), pass};

            Cursor cur = db.rawQuery("Select * from UserLogin WHERE UserId=? and UserPassword=?",params);

            int x = cur.getCount();

            while (cur.moveToNext())
            {
                userInfo = new UserDetails();
                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserId")));
                userInfo.setUserName(cur.getString(cur.getColumnIndex("UserName")));
                userInfo.setPassword(cur.getString(cur.getColumnIndex("UserPassword")));

                userInfo.setUserrole(cur.getString(cur.getColumnIndex("Role")));
                userInfo.setDistrictCode(cur.getString(cur.getColumnIndex("Dist_Code")));
                userInfo.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                userInfo.setPanchayatCode(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                userInfo.setPanchayatName(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                userInfo.setProject_Code(cur.getString(cur.getColumnIndex("Project_Code")));
                userInfo.setProject_Name(cur.getString(cur.getColumnIndex("Project_Name")));
                userInfo.setSector_Code(cur.getString(cur.getColumnIndex("SectorCode")));
                userInfo.setSector_Name(cur.getString(cur.getColumnIndex("SectorName")));

                userInfo.setAwc_Code(cur.getString(cur.getColumnIndex("Awc_Code")));
                userInfo.setAwc_Name(cur.getString(cur.getColumnIndex("Awc_Name")));
                userInfo.setMobileNo(cur.getString(cur.getColumnIndex("MobileNo")));
                userInfo.setOtp(cur.getString(cur.getColumnIndex("OTP")));

            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }

    public UserDetails getUserDetails_new(String userId, String mob, String otp)
    {

        UserDetails userInfo = null;

        try
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId.trim(),mob,otp};

            Cursor cur = db.rawQuery("Select * from UserLogin WHERE UserId=? and MobileNo=? and OTP=?", params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                userInfo = new UserDetails();
                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserId")));
                userInfo.setName(cur.getString(cur.getColumnIndex("UserName")));
                userInfo.setDist_Code(cur.getString(cur.getColumnIndex("Dist_Code")));
                userInfo.setDist_Name(cur.getString(cur.getColumnIndex("Dist_Name")));
                userInfo.setAuthenticated(true);
                userInfo.setBlockCode(cur.getString(cur.getColumnIndex("Project_Code")));
                userInfo.setBlockName(cur.getString(cur.getColumnIndex("Project_Name")));
                userInfo.setPanchayatCode(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                userInfo.setPanchayatName(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                userInfo.setAwc_Code(cur.getString(cur.getColumnIndex("Awc_Code")));
                userInfo.setAwc_Name(cur.getString(cur.getColumnIndex("Awc_Name")));
                userInfo.setUserrole(cur.getString(cur.getColumnIndex("Role")));
                userInfo.setMobileNo(cur.getString( cur.getColumnIndex("MobileNo")));
                userInfo.setOtp(cur.getString(cur.getColumnIndex("OTP")));
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }

//    public long UpdateInspectionCount(String inspectionCount, String UserId) {
//
//        long c = 0;
//        try {
//
//            SQLiteDatabase db = this.getReadableDatabase();
//
//            ContentValues values = new ContentValues();
//            values.put("TotInspection", inspectionCount);
//
//            String[] whereArgs = new String[]{UserId};
//
//            c = db.update("UserLogin", values, "UserID=? ", whereArgs);
//
//            db.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            // TODO: handle exception
//            return 0;
//        }
//        return c;
//
//    }

    public WellInspectionEntity getWellInspectionDetails(String inspId)
    {

        WellInspectionEntity info = null;

        try
        {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{inspId};

            Cursor cur = db.rawQuery("Select * from WellInspectionDetail WHERE id=?",params);
            int x = cur.getCount();

            while (cur.moveToNext()) {


                info = new WellInspectionEntity();

                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistrictCode")));
                info.setBlockID(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));

                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                //info.setAuthenticated(true);
                info.setVillageID(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setVillageName(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setPanchayat_Code(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                info.setPanchayat_Name(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                info.setKhata_Khesra_No(cur.getString(cur.getColumnIndex("Khata_Khesra_No")));
                info.setPrivate_or_Public(cur.getString(cur.getColumnIndex("Private_or_Public")));
                info.setOutside_Diamter(cur.getString(cur.getColumnIndex("Outside_Diamter")));

                info.setName_of_undertaken(cur.getString(cur.getColumnIndex("Name_Of_Undertaken_Dept")));
                info.setRequirement_Of_Flyer(cur.getString(cur.getColumnIndex("Requirement_Of_Flyer")));
                info.setStatus_of_Encroachment(cur.getString(cur.getColumnIndex("Status_Of_Encroachment")));
                info.setTypes_of_Encroachment(cur.getString(cur.getColumnIndex("Types_Of_Encroachment")));
                info.setRequirement_of_Renovation(cur.getString(cur.getColumnIndex("Requirement_Of_Renovation")));
                //info.setre(cur.getString(cur.getColumnIndex("Recommended_Execution_Dept")));
                info.setRequirement_of_machine(cur.getString(cur.getColumnIndex("Requirement_of_Machine")));
                //info.set(cur.getString(cur.getColumnIndex("Name_of_undertaken")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistrictCode")));

                info.setRemarks(cur.getString(cur.getColumnIndex("Remarks")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                //info.setVi(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));
                info.setLatitude_Mob(cur.getString(cur.getColumnIndex("Latitude_Mob")));
                info.setLongitude_Mob(cur.getString(cur.getColumnIndex("Longitude_Mob")));
                info.setWellAvblValue(cur.getString(cur.getColumnIndex("WellAvblValue")));
                info.setWellOwnerOtherDeptName(cur.getString(cur.getColumnIndex("Photo3")));

                if (!cur.isNull(cur.getColumnIndex("Photo1")))
                {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto1(encodedImg1);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo2")))
                {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo2"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg2 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto2(encodedImg2);
                }

//                if (!cur.isNull(cur.getColumnIndex("Photo3"))) {
//
//                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo3"));
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    String encodedImg3 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//                    info.setPhoto3(encodedImg3);
//                }
//
//                if (!cur.isNull(cur.getColumnIndex("Photo4"))) {
//
//                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo4"));
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    String encodedImg4 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//                    info.setPhoto4(encodedImg4);
//                }
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            info = null;
        }
        return info;
    }

    public PondEncroachmentEntity getPondEncrhmntDetails(String inspId, String type)
    {

        String tablename = type.equals("pond") ? "CoPondEncroachmentReport" : "CoWellEncroachmentReport";
        PondEncroachmentEntity info = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{inspId};

            Cursor cur = db.rawQuery("Select * from "+tablename+" WHERE id=?",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                info = new PondEncroachmentEntity();

                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistID")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("BlockID")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setPanchayatID(cur.getString(cur.getColumnIndex("PanchayatID")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));

                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("RajswaThanaNumber")));
                info.setKhaata_Kheshara_Number(cur.getString(cur.getColumnIndex("Khaata_Kheshara_Number")));

                info.setVillageID(cur.getString(cur.getColumnIndex("VillageID")));
                info.setVILLNAME(cur.getString(cur.getColumnIndex("VILLNAME")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setStatus_Of_Encroachment(cur.getString(cur.getColumnIndex("Status_Of_Encroachment")));
                info.setType_Of_Encroachment(cur.getString(cur.getColumnIndex("Type_Of_Encroachment")));

                info.setVerified_By(cur.getString(cur.getColumnIndex("Verified_By")));
                info.setIsInspected(cur.getString(cur.getColumnIndex("IsInspected")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));

                info.setEnch_Verified_By(cur.getString(cur.getColumnIndex("Ench_Verified_By")));
                info.setEStatus(cur.getString(cur.getColumnIndex("EStatus")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setEnchrochmentStartDate(cur.getString(cur.getColumnIndex("EnchrochmentStartDate")));
                info.setEnchrochmentEndDate(cur.getString(cur.getColumnIndex("EnchrochmentEndDate")));
                info.setNoticeDate(cur.getString(cur.getColumnIndex("NoticeDate")));
                info.setNoticeNo(cur.getString(cur.getColumnIndex("NoticeNo")));
                info.setAppVersion(cur.getString(cur.getColumnIndex("AppVersion")));
                info.setUploadType(cur.getString(cur.getColumnIndex("UploadType")));

            }
            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            info = null;
        }
        return info;
    }


    public PondInspectionDetail getPondInspectionDetails(String inspId) {

        PondInspectionDetail info = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{inspId};

            Cursor cur = db.rawQuery("Select * from PondInspectionDetail WHERE id=?",params);
            int x = cur.getCount();

            while (cur.moveToNext()) {


                info = new PondInspectionDetail();

                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistrictCode(cur.getString(cur.getColumnIndex("DistrictCode")));
                info.setBlockCode(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));

                info.setRajswaThanaNo(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                //info.setAuthenticated(true);
                info.setVillage(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setVillageName(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setPanchayatCode(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                info.setKhataKhesraNo(cur.getString(cur.getColumnIndex("Khata_Khesra_No")));
                info.setPrivateOrPublic(cur.getString(cur.getColumnIndex("Private_or_Public")));
                info.setAreaByGovtRecord(cur.getString(cur.getColumnIndex("Area_by_Govt_Record")));

                info.setConnectedWithPine(cur.getString(cur.getColumnIndex("Connected_with_Pine")));
                info.setAvailiablityOfWater(cur.getString(cur.getColumnIndex("Availability_Of_Water")));
                info.setStatusOfEncroachment(cur.getString(cur.getColumnIndex("Status_of_Encroachment")));
                info.setTypesOfEncroachment(cur.getString(cur.getColumnIndex("Types_of_Encroachment")));
                info.setRequirementOfRenovation(cur.getString(cur.getColumnIndex("Requirement_of_Renovation")));
                info.setRecommendedExecutionDept(cur.getString(cur.getColumnIndex("Recommended_Execution_Dept")));
                info.setRequirementOfMachine(cur.getString(cur.getColumnIndex("Requirement_of_machine")));
                info.setOwnerName(cur.getString(cur.getColumnIndex("Name_of_undertaken")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));

                info.setRemarks(cur.getString(cur.getColumnIndex("Remarks")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                //info.setVi(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));
                info.setLatitude_Mob(cur.getString(cur.getColumnIndex("Latitude_Mob")));
                info.setLongitude_Mob(cur.getString(cur.getColumnIndex("Longitude_Mob")));
                info.setPondAvblValue(cur.getString(cur.getColumnIndex("PondAvblValue")));
                info.setPondCatValue(cur.getString(cur.getColumnIndex("PondCatValue")));

                info.setPondCatName(cur.getString(cur.getColumnIndex("Photo3")));
                info.setPondOwnerOtherDeptName(cur.getString(cur.getColumnIndex("Photo4")));

                if (!cur.isNull(cur.getColumnIndex("Photo1")))
                {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto1(encodedImg1);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo2")))
                {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo2"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg2 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto2(encodedImg2);
                }

//                if (!cur.isNull(cur.getColumnIndex("Photo3"))) {
//
//                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo3"));
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    String encodedImg3 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//                    info.setPhoto3(encodedImg3);
//                }
//
//                if (!cur.isNull(cur.getColumnIndex("Photo4"))) {
//
//                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photor4"));
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    String encodedImg4 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//                    info.setPhoto4(encodedImg4);
//                }


            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            info = null;
        }
        return info;
    }

    public long UpdateWellInspectionDetail(WellInspectionEntity entity) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("Block_Code", entity.getBlockID());
            values.put("Block_Name", entity.getBlockName());
            values.put("DistrictCode", entity.getDistID());
            values.put("DistName", entity.getDistName());

            values.put("Panchayat_Code", entity.getPanchayat_Code());
            values.put("Panchayat_Name", entity.getPanchayat_Name());
            values.put("Village_Id", entity.getVillageID());
            values.put("Village_Name", entity.getVillageName());

            values.put("Khata_Khesra_No", entity.getKhata_Khesra_No());
            values.put("Private_or_Public", entity.getPrivate_or_Public());
            values.put("Outside_Diamter", entity.getOutside_Diamter());
            values.put("Name_Of_Undertaken_Dept", entity.getName_of_undertaken());
            values.put("Requirement_Of_Flyer", entity.getRequirement_Of_Flyer());
            values.put("WellAvblValue", entity.getWellAvblValue());
            values.put("Status_of_Encroachment", entity.getStatus_of_Encroachment());
            values.put("Types_of_Encroachment", entity.getTypes_of_Encroachment());
            values.put("Requirement_of_Renovation", entity.getRequirement_of_Renovation());
            values.put("WellAvblValue", entity.getWellAvblValue());
            values.put("Requirement_of_machine", entity.getRequirement_of_machine());
            values.put("Remarks", entity.getRemarks());
            values.put("isUpdated", entity.getIsUpdated());
            values.put("DistrictCode", entity.getDistID());
            values.put("Photo3", entity.getWellOwnerOtherDeptName());
            values.put("Verified_Date", entity.getVerified_Date());

            String[] whereArgs = new String[]{String.valueOf(entity.getId())};

            if (entity.getId() == 0){
                c = db.insert("WellInspectionDetail", null, values);
            }else{
                c = db.update("WellInspectionDetail", values, "id=? ", whereArgs);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return 0;
        }
        return c;

    }

    public long UpdateEncrhmntDetail(PondEncroachmentEntity entity, String type) {
        String tableName = type.equals("pond") ? "CoPondEncroachmentReport" : "CoWellEncroachmentReport";
        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();

            if(entity.getUploadType().equals("R")){
                values.put("EnchrochmentStartDate", entity.getEnchrochmentStartDate());
                values.put("EnchrochmentEndDate", entity.getEnchrochmentEndDate());
                values.put("NoticeDate", entity.getNoticeDate());
                values.put("NoticeNo", entity.getNoticeNo());
            }else{
                values.put("Status_Of_Encroachment", entity.getStatus_Of_Encroachment());
                values.put("Type_Of_Encroachment", entity.getType_Of_Encroachment());
            }

            values.put("Verified_By", entity.getVerified_By());
            values.put("Verified_Date", entity.getVerified_Date());
            values.put("AppVersion", entity.getAppVersion());
            values.put("UploadType", entity.getUploadType());
            values.put("isUpdated", entity.getIsUpdated());

            String[] whereArgs = new String[]{String.valueOf(entity.getId())};

            c = db.update(tableName, values, "id=? ", whereArgs);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return 0;
        }
        return c;

    }

    public long UpdatePondMarkedEncrhmntDetail(PondEncroachmentEntity entity) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("Status_Of_Encroachment", entity.getStatus_Of_Encroachment());
            values.put("Type_Of_Encroachment", entity.getType_Of_Encroachment());
            values.put("Verified_By", entity.getVerified_By());
            values.put("Verified_Date", entity.getVerified_Date());
            values.put("AppVersion", entity.getAppVersion());
            values.put("UploadType", entity.getUploadType());
            values.put("isUpdated", entity.getIsUpdated());

            String[] whereArgs = new String[]{String.valueOf(entity.getId())};

            c = db.update("CoPondEncroachmentReport", values, "id=? ", whereArgs);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return 0;
        }
        return c;

    }

    public long UpdatePlantationInspectionDetail(PlantationDetail entity) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("isUpdated", entity.getIsUpdated());
            values.put("AppVersion", entity.getAppVersion());
            values.put("verifiedBy", entity.getVerifiedBy());
            values.put("verifiedDate", entity.getVerifiedDate());
            values.put("userRole", entity.getUserRole());
//            values.put("Fyear", entity.getFyear());
//            values.put("BhumiType", entity.getBhumiType());
            values.put("Ropit_PlantNo", entity.getRopit_PlantNo());
            values.put("Utarjibit_PlantNo", entity.getUtarjibit_PlantNo());
            values.put("UtarjibitaPercent", entity.getUtarjibitaPercent());
            values.put("Plantation_Site_Id", entity.getPlantation_Site_Id());
            values.put("Plantation_Site_Name", entity.getPlantation_Site_Name());
            values.put("Van_Posako_No", entity.getVan_Posako_No());
            values.put("Posak_bhugtaanMonth", entity.getPosak_bhugtaanMonth());
            values.put("Posak_bhugtaanYear", entity.getPosak_bhugtaanYear());
            values.put("gavyan_percentage", entity.getGavyan_percentage());
            values.put("Average_height_Plant", entity.getAverage_height_Plant());
            values.put("Remarks", entity.getRemarks());

            String[] whereArgs = new String[]{String.valueOf(entity.getInspectionID())};

            c = db.update("PlantationDetail", values, "InspectionID=? ", whereArgs);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return 0;
        }
        return c;

    }


    public long UpdateGPSCount(String gpsCount, String UserId) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("TotBuilPic", gpsCount);

            String[] whereArgs = new String[]{UserId};

            c = db.update("UserLogin", values, "UserID=? ", whereArgs);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return 0;
        }
        return c;

    }

    public long resetWellInspectionUpdatedData(String insepectionId)
    {

        long c = -1;

        try
        {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(insepectionId)};
            c = db.delete("WellInspectionDetail", "id=?", DeleteWhere);

            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deleteBendataData()
    {

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            c = db.delete("BenDetails", null,null);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long resetEncrhmntUpdatedData(String id, String type){
        String tableName = type.equals("pond") ? "CoPondEncroachmentReport" : "CoWellEncroachmentReport";
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(id)};
            c = db.delete(tableName, "id=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deleteAllPlantationRecord(){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            //String[] DeleteWhere = {String.valueOf(insepectionId)};
            //c = db.delete("PlantationDetail", "InspectionID=?", DeleteWhere);
            db.execSQL("delete from PlantationDetail");

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long resetPlantationInspectionUpdatedData(String insepectionId){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(insepectionId)};
            c = db.delete("PlantationDetail", "InspectionID=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deleteManregadData(String insepectionId, int phaseType){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("isUpdated", "0");
            switch (phaseType){
                case 1:
                    values.put("IsPhase1Inspected", "N");
                    break;
                case 2:
                    values.put("IsPhase2Inspected", "N");
                    break;
                case 3:
                    values.put("IsPhase3Inspected", "N");
                    break;
            }

            String[] whereArgs = {String.valueOf(insepectionId)};

            //c = db.update("Menrega_Rural_Dev_Dept", values, "id=?", whereArgs);

            c = db.delete("Menrega_Rural_Dev_Dept", "id=?", whereArgs);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long resetManregadData(String insepectionId, int phaseType){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("isUpdated", "0");

            String[] whereArgs = {String.valueOf(insepectionId)};

            if (phaseType == 3){
                c = db.delete("Menrega_Rural_Dev_Dept", "id=?", whereArgs);
            }else{
                c = db.update("Menrega_Rural_Dev_Dept", values, "id=?", whereArgs);
            }


            //c = db.delete("Menrega_Rural_Dev_Dept", "id=?", whereArgs);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deleteOtherSchemeData(String insepectionId, int phaseType){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("isUpdated", "0");
            switch (phaseType){
                case 1:
                    values.put("IsPhase1Inspected", "N");
                    break;
                case 2:
                    values.put("IsPhase2Inspected", "N");
                    break;
                case 3:
                    values.put("IsPhase3Inspected", "N");
                    break;
            }

            String[] whereArgs = {String.valueOf(insepectionId)};
            c = db.delete("OtherDept_Of_Rural_Dev_Dept", "id=?", whereArgs);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long resetOtherDeptData(String insepectionId, int phaseType){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("isUpdated", "0");

            String[] whereArgs = {String.valueOf(insepectionId)};

            if (phaseType == 3){
                c = db.delete("OtherDept_Of_Rural_Dev_Dept", "id=?", whereArgs);
            }else{
                c = db.update("OtherDept_Of_Rural_Dev_Dept", values, "id=?", whereArgs);
            }


            //c = db.delete("Menrega_Rural_Dev_Dept", "id=?", whereArgs);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }


    public long setPlantationDataToLocal(ArrayList<PlantationDetail> list) {
        String tableName = "PlantationDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<PlantationDetail> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("DistID", info.get(i).getDistID());
                    values.put("DistName", info.get(i).getDistName());
                    values.put("BlockID", info.get(i).getBlockID());
                    values.put("BlockName", info.get(i).getBlockName());
                    values.put("PanchayatID", info.get(i).getPanchayatID());
                    values.put("PanchayatName", info.get(i).getPanchayatName());
                    values.put("WorkStateFyear", info.get(i).getWorkStateFyear());
                    values.put("WorkName", info.get(i).getWorkName());
                    values.put("WorkCode", info.get(i).getWorkCode());
                    values.put("Worktype", info.get(i).getWorktype());
                    values.put("AgencyName", info.get(i).getAgencyName());
                    values.put("SanctionAmtLabourCom", info.get(i).getSanctionAmtLabourCom());
                    values.put("SanctionAmtMaterialCom", info.get(i).getSanctionAmtMaterialCom());
                    values.put("InspectionID", info.get(i).getInspectionID());
                    values.put("IsInspectedDate", info.get(i).getIsInspectedDate());
                    values.put("IsInspectedBy", info.get(i).getIsInspectedBy());
                    values.put("IsInspectedByDSTAE", info.get(i).getIsInspectedByDSTAE());
                    values.put("IsInspectedByDSTEE", info.get(i).getIsInspectedByDSTEE());
                    values.put("IsInspectedByDSTDRDA", info.get(i).getIsInspectedByDSTDRDA());
                    values.put("IsInspectedByDSTDDC", info.get(i).getIsInspectedByDSTDDC());

                    values.put("isUpdated", "0");

                    values.put("AppVersion", info.get(i).getAppVersion());
                    values.put("verifiedBy", info.get(i).getVerifiedBy());
                    values.put("verifiedDate", info.get(i).getVerifiedDate());
                    values.put("userRole", info.get(i).getUserRole());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getInspectionID())};

                    c = db.update(tableName, values, "InspectionID=?", whereArgs);

                    if(c < 1){
                        c = db.insert(tableName, null, values);
                    }
                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setWardDataToLocal(ArrayList<ward> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<ward> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("WardName", info.get(i).getWardname());
                    values.put("WardCode", info.get(i).getWardCode());
                    values.put("PanchayatCode", info.get(i).getPanchayatCode());
                    values.put("AreaType", info.get(i).getAreaType());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getWardCode())};

                    c = db.update("Ward", values, "WardCode=?", whereArgs);

                    if(c != 1){
                        c = db.insert("Ward", null, values);
                    }
                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setPlantationSiteDataToLocal(ArrayList<PlantationSiteEntity> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<PlantationSiteEntity> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("id", info.get(i).getId());
                    values.put("Site_Name", info.get(i).getSite_Name());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getId())};

                    c = db.update("PlantationSiteDetail", values, "id=?", whereArgs);

                    if(c != 1){
                        c = db.insert("PlantationSiteDetail", null, values);
                    }
                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setVillageDataToLocal(ArrayList<VillageListEntity> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<VillageListEntity> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("VillageCode", info.get(i).getVillCode());
                    values.put("VillageName", info.get(i).getVillName());
                    values.put("PanchayatCode", info.get(i).getPanchayatCode());
                    values.put("BLOCKCODE", info.get(i).getBlockCode());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getVillCode()),String.valueOf(info.get(i).getPanchayatCode())};

                    c = db.update("VillageList", values, "VillageCode=? AND PanchayatCode=?", whereArgs);

                    if(c != 1){
                        c = db.insert("VillageList", null, values);
                    }

                    Log.e("Panchayat", info.get(i).getPanchayatCode());
                    Log.e("VillageCode", info.get(i).getVillCode());
                    Log.e("C", String.valueOf(c));

                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setSanrachnaTypeDataToLocal(ArrayList<SanrachnaTypeEntity> list) {

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<SanrachnaTypeEntity> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("PacsID", info.get(i).getSancrachnaId());
                    values.put("PacsName", info.get(i).getSancrachnaName());
                    values.put("BlockCode", info.get(i).getSub_Execution_DeptID());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getSancrachnaId())};

                    c = db.update("PACS", values, "PacsID=?", whereArgs);

                    if(c < 1){
                        c = db.insert("PACS", null, values);
                    }



                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }


    public long setPanchayatDataToLocal(UserDetails userInfo, ArrayList<PanchayatEntity> list) {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<PanchayatEntity> info = list;


        if (info != null) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++) {

                    values.put("PanchayatCode", info.get(i).getPcode());
                    values.put("PanchayatName", info.get(i).getPname());
                    values.put("PACName", info.get(i).getAreaType());

                    values.put("BlockCode", userInfo.getBlockCode());
                    //values.put("Block Name", userInfo.getBlockName());
                    values.put("DistrictCode", userInfo.getDistrictCode());
                    values.put("DistrictName", userInfo.getDistName());
                    values.put("PartNo", "2");

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getPcode())};

                    c = db.update("Panchayat", values, "PanchayatCode=?", whereArgs);

                    if(c != 1){
                        c = db.insert("Panchayat", null, values);
                    }



                }
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }
    public long setAWCDataToLocal(ArrayList<AWC_Model> list, String SectorCode)
    {
        // String tableName = type == "pond" ? "PondInspectionDetail" : "WellInspectionDetail";

        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try
        {
            dh.createDataBase();
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<AWC_Model> info = list;

        if (info != null)
        {
            try
            {
                SQLiteDatabase db = this.getReadableDatabase();

                ContentValues values = new ContentValues();

                for (int i = 0; i < info.size(); i++)
                {

                    values.put("AWC_CODE", info.get(i).getAWC_CODE());
                    values.put("AWC_NAME", info.get(i).getAWC_NAME());
                    values.put("AWCGOICode", info.get(i).getAWCGOICode());
                    values.put("SectorId", SectorCode);

                    values.put("AWC_PANCHAYATCODE", info.get(i).getAWC_PANCHAYATCODE());

                    String[] whereArgs = new String[]{String.valueOf(info.get(i).getAWC_CODE())};

                    c = db.update("AwcTable", values, "AWC_CODE=?", whereArgs);

                    if(c != 1)
                    {
                        c = db.insert("AwcTable", null, values);
                    }

                }
                db.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public String getWellEncrhmntUpdatedDataCount()
    {
        String pondCount = "0", wellCount = "0";
        String[] params = new String[]{"1"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from CoWellEncroachmentReport WHERE isUpdated=?", params);

            pondCount = String.valueOf(curPond.getCount());
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getPondEncrhmntUpdatedDataCount()
    {
        String pondCount = "0", wellCount = "0";
        String[] params = new String[]{"1"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from CoPondEncroachmentReport WHERE isUpdated=?", params);
            pondCount = String.valueOf(curPond.getCount());
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getPlantationUpdatedDataCount()
    {
        //ArrayList<String> List = new ArrayList<String>();
        String pondCount = "0";
        String[] params = new String[]{"Y"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor curPond = db.rawQuery("Select * from BenDetails WHERE IsBenUpdated=?", params);

            pondCount = String.valueOf(curPond.getCount());
            //wellCount = String.valueOf(curWell.getCount());
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return pondCount;
    }

    public String getWellUpdatedDataCount()
    {
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from WellInspectionDetail WHERE IsBenUpdated=?", params);

            wellCount = String.valueOf(curWell.getCount());

        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }

    public String getManregadDataCount()
    {
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from Menrega_Rural_Dev_Dept WHERE isUpdated=?", params);

            wellCount = String.valueOf(curWell.getCount());

        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }

    public String getOtherSchemeDataCount()
    {
        String wellCount = "0";
        String[] params = new String[]{"1"};
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor curPond = db.rawQuery("Select * from PondInspectionDetail WHERE isUpdated=?", params);

            Cursor curWell = db.rawQuery("Select * from OtherDept_Of_Rural_Dev_Dept WHERE isUpdated=?", params);
            wellCount = String.valueOf(curWell.getCount());

        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return wellCount;
    }


    public ArrayList<LocalSpinnerData> getAwcList(String sectorCode)
    {

        // PlaceDataSQL placeData = new PlaceDataSQL(MainActivity.this);
        ArrayList<LocalSpinnerData> awcList = new ArrayList<LocalSpinnerData>();
        Cursor cur;
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            if(sectorCode == null || sectorCode.equalsIgnoreCase("")|| sectorCode.equalsIgnoreCase("NA") || sectorCode.equalsIgnoreCase("0")){
                cur = db.rawQuery("Select * from Panchayat", null);
            }else {
                String[] params = new String[]{sectorCode};
                cur = db.rawQuery("Select * from Panchayat WHERE Sector_Code=?", params);
            }

            int x = cur.getCount();

            while (cur.moveToNext()) {

                LocalSpinnerData localSpinnerData = new LocalSpinnerData();
                localSpinnerData.setCode((cur.getString(cur.getColumnIndex("slno"))));
                localSpinnerData.setValue(cur.getString(cur.getColumnIndex("Name")));

                awcList.add(localSpinnerData);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return awcList;

    }


    public ArrayList<LocalSpinnerData> getSectorList(String projectCode) {

        // PlaceDataSQL placeData = new PlaceDataSQL(MainActivity.this);
        ArrayList<LocalSpinnerData> sectorList = new ArrayList<LocalSpinnerData>();
        Cursor cur;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[]{projectCode};
            cur = db.rawQuery("Select * from Sector WHERE Block_Code=?", params);


            int x = cur.getCount();

            while (cur.moveToNext()) {

                LocalSpinnerData localSpinnerData = new LocalSpinnerData();
                localSpinnerData.setCode((cur.getString(cur.getColumnIndex("Code"))));
                localSpinnerData.setValue(cur.getString(cur.getColumnIndex("Name")));

                sectorList.add(localSpinnerData);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return sectorList;

    }


    public long setSpinnerMasterDataLocal(ArrayList<SpinnerMaster> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<SpinnerMaster> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (info != null) {
            try {


                for (int i = 0; i < info.size(); i++) {

                    values.put("Code", info.get(i).getCode());
                    values.put("Value", info.get(i).getValue());
                    values.put("Field", info.get(i).getField());

                    c = db.insert("SpinnerMaster", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public ArrayList<PondEntity> getWellsInspectionUpdatedDetail(){
        //PondInspectionDetail info = null;

        ArrayList<PondEntity> infoList = new ArrayList<PondEntity>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from WellInspectionDetail WHERE isUpdated=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PondEntity info = new PondEntity();

                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));

                info.setDistID(cur.getString(cur.getColumnIndex("DistrictCode")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));
                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                info.setVillageID(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setVillageName(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PondEntity> getWellsInspectionDetail(String panchayatId){
        //PondInspectionDetail info = null;

        ArrayList<PondEntity> infoList = new ArrayList<PondEntity>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"0",panchayatId};

            Cursor cur = db.rawQuery(
                    "Select * from WellInspectionDetail WHERE isUpdated=? AND Panchayat_Code=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PondEntity info = new PondEntity();
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));

                info.setDistID(cur.getString(cur.getColumnIndex("DistrictCode")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));
                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                info.setVillageID(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setVillageName(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PlantationDetail> getPlantationInspectionUpdatedDetail(){
        //PondInspectionDetail info = null;

        ArrayList<PlantationDetail> infoList = new ArrayList<PlantationDetail>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from PlantationDetail WHERE isUpdated=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PlantationDetail info = new PlantationDetail();

                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistID")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("BlockID")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setPanchayatID(cur.getString(cur.getColumnIndex("PanchayatID")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                info.setWorkStateFyear(cur.getString(cur.getColumnIndex("WorkStateFyear")));
                info.setWorkName(cur.getString(cur.getColumnIndex("WorkName")));
                info.setWorkCode(cur.getString(cur.getColumnIndex("WorkCode")));
                info.setWorktype(cur.getString(cur.getColumnIndex("Worktype")));
                info.setAgencyName(cur.getString(cur.getColumnIndex("AgencyName")));
                info.setSanctionAmtLabourCom(cur.getString(cur.getColumnIndex("SanctionAmtLabourCom")));
                info.setSanctionAmtMaterialCom(cur.getString(cur.getColumnIndex("SanctionAmtMaterialCom")));
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setIsInspectedDate(cur.getString(cur.getColumnIndex("IsInspectedDate")));
                info.setIsInspectedBy(cur.getString(cur.getColumnIndex("IsInspectedBy")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setAppVersion(cur.getString(cur.getColumnIndex("AppVersion")));
                info.setVerifiedBy(cur.getString(cur.getColumnIndex("verifiedBy")));
                info.setVerifiedDate(cur.getString(cur.getColumnIndex("verifiedDate")));
                info.setUserRole(cur.getString(cur.getColumnIndex("userRole")));
                info.setFyear(cur.getString(cur.getColumnIndex("Fyear")));
                info.setBhumiType(cur.getString(cur.getColumnIndex("BhumiType")));
                info.setRopit_PlantNo(cur.getString(cur.getColumnIndex("Ropit_PlantNo")));
                info.setUtarjibit_PlantNo(cur.getString(cur.getColumnIndex("Utarjibit_PlantNo")));
                info.setUtarjibitaPercent(cur.getString(cur.getColumnIndex("UtarjibitaPercent")));
                info.setUtarjibit_90PercentMore(cur.getString(cur.getColumnIndex("Utarjibit_90PercentMore")));
                info.setUtarjibit_75_90Percent(cur.getString(cur.getColumnIndex("Utarjibit_75_90Percent")));
                info.setUtarjibit_50_75Percent(cur.getString(cur.getColumnIndex("Utarjibit_50_75Percent")));
                info.setUtarjibit_25PercentLess(cur.getString(cur.getColumnIndex("Utarjibit_25PercentLess")));
                info.setRemarks(cur.getString(cur.getColumnIndex("Remarks")));
                info.setLatitude_Mob(cur.getString(cur.getColumnIndex("Latitude_Mob")));
                info.setLongitude_Mob(cur.getString(cur.getColumnIndex("Longitude_Mob")));

                info.setPlantation_Site_Id(cur.getString(cur.getColumnIndex("Plantation_Site_Id")));
                info.setPlantation_Site_Name(cur.getString(cur.getColumnIndex("Plantation_Site_Name")));
                info.setVan_Posako_No(cur.getString(cur.getColumnIndex("Van_Posako_No")));
                info.setPosak_bhugtaanMonth(cur.getString(cur.getColumnIndex("Posak_bhugtaanMonth")));
                info.setPosak_bhugtaanYear(cur.getString(cur.getColumnIndex("Posak_bhugtaanYear")));
                info.setGavyan_percentage(cur.getString(cur.getColumnIndex("gavyan_percentage")));
                info.setAverage_height_Plant(cur.getString(cur.getColumnIndex("Average_height_Plant")));


                if (!cur.isNull(cur.getColumnIndex("photo"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("photo"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto(encodedImg1);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo1"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto1(encodedImg1);
                }

                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PondEncroachmentEntity> getPondsEncrhmntDetail(String panchayatId, String type){
        //PondInspectionDetail info = null;
        String tableName = type.equals("pond") ? "CoPondEncroachmentReport" : "CoWellEncroachmentReport";

        ArrayList<PondEncroachmentEntity> infoList = new ArrayList<PondEncroachmentEntity>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"0",panchayatId};

            Cursor cur = db.rawQuery(
                    "Select * from "+ tableName +" WHERE isUpdated=? AND PanchayatID=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PondEncroachmentEntity info = new PondEncroachmentEntity();
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistID")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("BlockID")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setPanchayatID(cur.getString(cur.getColumnIndex("PanchayatID")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("RajswaThanaNumber")));
                info.setKhaata_Kheshara_Number(cur.getString(cur.getColumnIndex("Khaata_Kheshara_Number")));
                info.setVillageID(cur.getString(cur.getColumnIndex("VillageID")));
                info.setVILLNAME(cur.getString(cur.getColumnIndex("VILLNAME")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setStatus_Of_Encroachment(cur.getString(cur.getColumnIndex("Status_Of_Encroachment")));
                info.setType_Of_Encroachment(cur.getString(cur.getColumnIndex("Type_Of_Encroachment")));
                info.setVerified_By(cur.getString(cur.getColumnIndex("Verified_By")));
                info.setIsInspected(cur.getString(cur.getColumnIndex("IsInspected")));

                infoList.add(info);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PondEncroachmentEntity> getPondsEncrhmntUpdatedDetail(String type){
        //PondInspectionDetail info = null;

        String tableName = type.equals("pond") ? "CoPondEncroachmentReport" : "CoWellEncroachmentReport";
        ArrayList<PondEncroachmentEntity> infoList = new ArrayList<PondEncroachmentEntity>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from "+ tableName +" WHERE isUpdated=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PondEncroachmentEntity info = new PondEncroachmentEntity();
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistID")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("BlockID")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setPanchayatID(cur.getString(cur.getColumnIndex("PanchayatID")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("RajswaThanaNumber")));
                info.setKhaata_Kheshara_Number(cur.getString(cur.getColumnIndex("Khaata_Kheshara_Number")));
                info.setVillageID(cur.getString(cur.getColumnIndex("VillageID")));
                info.setVILLNAME(cur.getString(cur.getColumnIndex("VILLNAME")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setStatus_Of_Encroachment(cur.getString(cur.getColumnIndex("Status_Of_Encroachment")));
                info.setType_Of_Encroachment(cur.getString(cur.getColumnIndex("Type_Of_Encroachment")));
                info.setVerified_By(cur.getString(cur.getColumnIndex("Verified_By")));
                info.setIsInspected(cur.getString(cur.getColumnIndex("IsInspected")));

                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));
                info.setEnch_Verified_By(cur.getString(cur.getColumnIndex("Ench_Verified_By")));
                info.setEStatus(cur.getString(cur.getColumnIndex("EStatus")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setEnchrochmentStartDate(cur.getString(cur.getColumnIndex("EnchrochmentStartDate")));
                info.setEnchrochmentEndDate(cur.getString(cur.getColumnIndex("EnchrochmentEndDate")));
                info.setNoticeDate(cur.getString(cur.getColumnIndex("NoticeDate")));
                info.setNoticeNo(cur.getString(cur.getColumnIndex("NoticeNo")));
                info.setAppVersion(cur.getString(cur.getColumnIndex("AppVersion")));
                info.setUploadType(cur.getString(cur.getColumnIndex("UploadType")));

                infoList.add(info);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PlantationDetail> getPlantationDetail(String panchayatId){
        //PondInspectionDetail info = null;

        ArrayList<PlantationDetail> infoList = new ArrayList<PlantationDetail>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"0",panchayatId};

            Cursor cur = db.rawQuery(
                    "Select * from PlantationDetail WHERE isUpdated=? AND PanchayatID=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PlantationDetail info = new PlantationDetail();
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistID")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                info.setBlockID(cur.getString(cur.getColumnIndex("BlockID")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setPanchayatID(cur.getString(cur.getColumnIndex("PanchayatID")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                info.setWorkStateFyear(cur.getString(cur.getColumnIndex("WorkStateFyear")));
                info.setWorkName(cur.getString(cur.getColumnIndex("WorkName")));
                info.setWorkCode(cur.getString(cur.getColumnIndex("WorkCode")));
                info.setWorktype(cur.getString(cur.getColumnIndex("Worktype")));
                info.setAgencyName(cur.getString(cur.getColumnIndex("AgencyName")));
                info.setSanctionAmtLabourCom(cur.getString(cur.getColumnIndex("SanctionAmtLabourCom")));
                info.setSanctionAmtMaterialCom(cur.getString(cur.getColumnIndex("SanctionAmtMaterialCom")));
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setIsInspectedDate(cur.getString(cur.getColumnIndex("IsInspectedDate")));
                info.setIsInspectedBy(cur.getString(cur.getColumnIndex("IsInspectedBy")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setAppVersion(cur.getString(cur.getColumnIndex("AppVersion")));
                info.setVerifiedBy(cur.getString(cur.getColumnIndex("verifiedBy")));
                info.setVerifiedDate(cur.getString(cur.getColumnIndex("verifiedDate")));
                info.setUserRole(cur.getString(cur.getColumnIndex("userRole")));
                info.setIsInspectedByDSTAE(cur.getString(cur.getColumnIndex("IsInspectedByDSTAE")));
                info.setIsInspectedByDSTEE(cur.getString(cur.getColumnIndex("IsInspectedByDSTEE")));
                info.setIsInspectedByDSTDRDA(cur.getString(cur.getColumnIndex("IsInspectedByDSTDRDA")));
                info.setIsInspectedByDSTDDC(cur.getString(cur.getColumnIndex("IsInspectedByDSTDDC")));

                info.setPlantation_Site_Id(cur.getString(cur.getColumnIndex("Plantation_Site_Id")));
                info.setPlantation_Site_Name(cur.getString(cur.getColumnIndex("Plantation_Site_Name")));
                info.setVan_Posako_No(cur.getString(cur.getColumnIndex("Van_Posako_No")));
                info.setPosak_bhugtaanMonth(cur.getString(cur.getColumnIndex("Posak_bhugtaanMonth")));
                info.setPosak_bhugtaanYear(cur.getString(cur.getColumnIndex("Posak_bhugtaanYear")));
                info.setGavyan_percentage(cur.getString(cur.getColumnIndex("gavyan_percentage")));
                info.setAverage_height_Plant(cur.getString(cur.getColumnIndex("Average_height_Plant")));

                infoList.add(info);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

    public ArrayList<PondInspectionDetail> getInspectedPondList(){
        //PondInspectionDetail info = null;

        ArrayList<PondInspectionDetail> infoList = new ArrayList<PondInspectionDetail>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from PondInspectionDetail WHERE isUpdated=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                PondInspectionDetail info = new PondInspectionDetail();
                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setBlockCode(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));

                info.setRajswaThanaNo(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                //info.setAuthenticated(true);
                info.setVillage(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setPanchayatCode(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                info.setKhataKhesraNo(cur.getString(cur.getColumnIndex("Khata_Khesra_No")));
                info.setPrivateOrPublic(cur.getString(cur.getColumnIndex("Private_or_Public")));
                info.setAreaByGovtRecord(cur.getString(cur.getColumnIndex("Area_by_Govt_Record")));

                info.setConnectedWithPine(cur.getString(cur.getColumnIndex("Connected_with_Pine")));
                info.setAvailiablityOfWater(cur.getString(cur.getColumnIndex("Availability_Of_Water")));
                info.setStatusOfEncroachment(cur.getString(cur.getColumnIndex("Status_of_Encroachment")));
                info.setTypesOfEncroachment(cur.getString(cur.getColumnIndex("Types_of_Encroachment")));
                info.setRequirementOfRenovation(cur.getString(cur.getColumnIndex("Requirement_of_Renovation")));
                info.setRecommendedExecutionDept(cur.getString(cur.getColumnIndex("Recommended_Execution_Dept")));
                info.setRequirementOfMachine(cur.getString(cur.getColumnIndex("Requirement_of_machine")));
                info.setOwnerName(cur.getString(cur.getColumnIndex("Name_of_undertaken")));
                info.setDistName(cur.getString(cur.getColumnIndex("DistName")));

                info.setRemarks(cur.getString(cur.getColumnIndex("Remarks")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                //info.setVi(cur.getString(cur.getColumnIndex("Village_Name")));
                //info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));
                info.setLatitude_Mob(cur.getString(cur.getColumnIndex("Latitude_Mob")));
                info.setLongitude_Mob(cur.getString(cur.getColumnIndex("Longitude_Mob")));

                if (!cur.isNull(cur.getColumnIndex("Photo1"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto1(encodedImg1);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo2"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg2 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto2(encodedImg2);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo3"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg3 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto3(encodedImg3);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo4"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg4 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto4(encodedImg4);
                }


                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            //info = null;
        }
        return infoList;
    }

    public ArrayList<WellInspectionEntity> getInspectedWellList(){
        //PondInspectionDetail info = null;

        ArrayList<WellInspectionEntity> infoList = new ArrayList<WellInspectionEntity>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from WellInspectionDetail WHERE isUpdated=?",
                    params);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                WellInspectionEntity info = new WellInspectionEntity();

                info.setInspectionID(cur.getString(cur.getColumnIndex("InspectionID")));
                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setBlockID(cur.getString(cur.getColumnIndex("Block_Code")));
                info.setBlockName(cur.getString(cur.getColumnIndex("Block_Name")));

                info.setRajswaThanaNumber(cur.getString(cur.getColumnIndex("Rajswa_Thana_No")));
                //info.setAuthenticated(true);
                info.setVillageID(cur.getString(cur.getColumnIndex("Village_Id")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setPanchayat_Code(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                info.setPanchayat_Name(cur.getString(cur.getColumnIndex("Panchayat_Name")));
                info.setKhata_Khesra_No(cur.getString(cur.getColumnIndex("Khata_Khesra_No")));
                info.setPrivate_or_Public(cur.getString(cur.getColumnIndex("Private_or_Public")));
                info.setOutside_Diamter(cur.getString(cur.getColumnIndex("Outside_Diamter")));

                info.setName_of_undertaken(cur.getString(cur.getColumnIndex("Name_Of_Undertaken_Dept")));
                info.setRequirement_Of_Flyer(cur.getString(cur.getColumnIndex("Requirement_Of_Flyer")));
                info.setStatus_of_Encroachment(cur.getString(cur.getColumnIndex("Status_Of_Encroachment")));
                info.setTypes_of_Encroachment(cur.getString(cur.getColumnIndex("Types_Of_Encroachment")));
                info.setRequirement_of_Renovation(cur.getString(cur.getColumnIndex("Requirement_Of_Renovation")));
                //info.setre(cur.getString(cur.getColumnIndex("Recommended_Execution_Dept")));
                info.setRequirement_of_machine(cur.getString(cur.getColumnIndex("Requirement_of_Machine")));
                //info.set(cur.getString(cur.getColumnIndex("Name_of_undertaken")));
                //info.setDi(cur.getString(cur.getColumnIndex("DistName")));
                info.setDistID(cur.getString(cur.getColumnIndex("DistrictCode")));

                info.setRemarks(cur.getString(cur.getColumnIndex("Remarks")));
                info.setIsUpdated(cur.getString(cur.getColumnIndex("isUpdated")));
                //info.setVi(cur.getString(cur.getColumnIndex("Village_Name")));
                info.setVerified_Date(cur.getString(cur.getColumnIndex("Verified_Date")));
                info.setLatitude_Mob(cur.getString(cur.getColumnIndex("Latitude_Mob")));
                info.setLongitude_Mob(cur.getString(cur.getColumnIndex("Longitude_Mob")));

                if (!cur.isNull(cur.getColumnIndex("Photo1"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto1(encodedImg1);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo2"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg2 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto2(encodedImg2);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo3"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg3 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto3(encodedImg3);
                }

                if (!cur.isNull(cur.getColumnIndex("Photo4"))) {

                    byte[] imgData = cur.getBlob(cur.getColumnIndex("Photo1"));
                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    String encodedImg4 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                    info.setPhoto4(encodedImg4);
                }

                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }

//
//    public UserDetails getUserDetails(String userId, String pass) {
//
//        UserDetails userInfo = null;
//
//        try {
//
//            SQLiteDatabase db = this.getReadableDatabase();
//
//            String[] params = new String[]{userId.trim(), pass};
//
//            Cursor cur = db.rawQuery(
//                    "Select * from UserLogin WHERE UserID=? and Password=?",
//                    params);
//            int x = cur.getCount();
//            // db1.execSQL("Delete from UserDetail");
//
//            while (cur.moveToNext()) {
//
//
//                userInfo = new UserDetails();
//                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserID")));
//                userInfo.setName(cur.getString(cur.getColumnIndex("UserName")));
//                userInfo.setPassword(cur.getString(cur.getColumnIndex("Password")));
//               // userInfo.setMobile(cur.getString(cur.getColumnIndex("ContactNo")));
//                userInfo.setUserrole(cur.getString(cur.getColumnIndex("Userrole")));
//                //userInfo.setIMEI(cur.getString(cur.getColumnIndex("IMEI")));
//                userInfo.setAuthenticated(true);
//                userInfo.setDistrictCode(cur.getString(cur.getColumnIndex("DistCode")));
//                userInfo.setDistName(cur.getString(cur.getColumnIndex("DistName")));
//                userInfo.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
//                userInfo.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
//                userInfo.setDegignation(cur.getString(cur.getColumnIndex("Degignation")));
//                userInfo.setMobileNo(cur.getString(cur.getColumnIndex("MobileNo")));
//            }
//
//            cur.close();
//            db.close();
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            userInfo = null;
//        }
//        return userInfo;
//    }


    public ArrayList<LocalFieldInfo> getLocalFieldInfo() {


        ArrayList<LocalFieldInfo> fieldList = new ArrayList<LocalFieldInfo>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"Y"};
            Cursor cur = db.rawQuery("Select * from FieldMaster where isActive=? order by Sequence", params);


            while (cur.moveToNext()) {

                String slno = cur.getString(0);
                String Field = cur.getString(1);
                String Label = cur.getString(3);
                String Type = cur.getString(4);
                String Sequence = String.valueOf(cur.getInt(5));


                fieldList.add(new LocalFieldInfo(slno, Field, Label, "Y", Type, Sequence));


            }

            cur.close();
            db.close();


        } catch (Exception e) {
            // TODO: handle exception
            return fieldList;

        }

        return fieldList;


    }


    public ArrayList<LocalSpinnerData> getLocalSpinnerData(String F) {


        ArrayList<LocalSpinnerData> fieldList = new ArrayList<LocalSpinnerData>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{F};
            Cursor cur = db.rawQuery("Select * from SpinnerMaster where Field=? order by Code ", params);


            while (cur.moveToNext()) {


                String Code = cur.getString(1);
                String Value = cur.getString(2);
                String Field = cur.getString(3);


                fieldList.add(new LocalSpinnerData(Code, Value, Field, ""));


            }

            cur.close();
            db.close();


        } catch (Exception e) {
// TODO: handle exception
            return fieldList;

        }

        return fieldList;


    }


    public long insertVoucher(VoutcherEntity voutcherEntity) {

        long c = -1;
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("chaval_detail", voutcherEntity.getChaval_detail());
            values.put("masur_daal_detail", voutcherEntity.getMasur_daal_detail());
            values.put("chana_detail", voutcherEntity.getChana_detail());
            values.put("mungfali_detail", voutcherEntity.getMungfali_detail());
            values.put("gur_detail", voutcherEntity.getGur_detail());
            values.put("suji_detail", voutcherEntity.getSuji_detail());
            values.put("chini_detail", voutcherEntity.getChini_detail());
            values.put("refine_tel_detail", voutcherEntity.getRefine_tel_detail());
            values.put("iyodin_namak_detail", voutcherEntity.getIyodin_namak_detail());
            values.put("masala_detail", voutcherEntity.getMasala_detail());
            values.put("nasta_detail", voutcherEntity.getNasta_detail());
            values.put("hari_sabji_detail", voutcherEntity.getHari_sabji_detail());
            values.put("jalavan_detail", voutcherEntity.getJalavan_detail());
            values.put("anda_detail", voutcherEntity.getAnda_detail());
            values.put("parivahan_detail", voutcherEntity.getParivahan_detail());
            values.put("total_detail", voutcherEntity.getTotal_detail());
            values.put("ls_id", voutcherEntity.getLs_id());
            values.put("voucher_no", voutcherEntity.getVoucher_no());
            values.put("vouter_img", voutcherEntity.getVouter_img());

            c = db.insert("myVoutcher", null, values);
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }

        return c;

    }

    public ArrayList<VoutcherEntity> getAllVoucher() {

        ArrayList<VoutcherEntity> progressList = new ArrayList<VoutcherEntity>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db
                    .rawQuery(
                            "select * from myVoutcher",
                            null);

            int count = cur.getCount();

            while (cur.moveToNext()) {

                VoutcherEntity progress = new VoutcherEntity();
                progress.setSlno(cur.getString(cur.getColumnIndex("slno")));
                progress.setChana_detail(cur.isNull(cur.getColumnIndex("chaval_detail")) == false ? cur.getString(cur.getColumnIndex("chaval_detail")) : "");
                progress.setMasur_daal_detail(cur.isNull(cur.getColumnIndex("masur_daal_detail")) == false ? cur.getString(cur.getColumnIndex("masur_daal_detail")) : "");
                progress.setChana_detail(cur.isNull(cur.getColumnIndex("chana_detail")) == false ? cur.getString(cur.getColumnIndex("chana_detail")) : "");
                progress.setMungfali_detail(cur.isNull(cur.getColumnIndex("mungfali_detail")) == false ? cur.getString(cur.getColumnIndex("mungfali_detail")) : "");
                progress.setGur_detail(cur.isNull(cur.getColumnIndex("gur_detail")) == false ? cur.getString(cur.getColumnIndex("gur_detail")) : "");
                progress.setSuji_detail(cur.isNull(cur.getColumnIndex("suji_detail")) == false ? cur.getString(cur.getColumnIndex("suji_detail")) : "");
                progress.setChini_detail(cur.isNull(cur.getColumnIndex("chini_detail")) == false ? cur.getString(cur.getColumnIndex("chini_detail")) : "");
                progress.setRefine_tel_detail(cur.isNull(cur.getColumnIndex("refine_tel_detail")) == false ? cur.getString(cur.getColumnIndex("refine_tel_detail")) : "");
                progress.setIyodin_namak_detail(cur.isNull(cur.getColumnIndex("iyodin_namak_detail")) == false ? cur.getString(cur.getColumnIndex("iyodin_namak_detail")) : "");
                progress.setMasala_detail(cur.isNull(cur.getColumnIndex("masala_detail")) == false ? cur.getString(cur.getColumnIndex("masala_detail")) : "");
                progress.setNasta_detail(cur.isNull(cur.getColumnIndex("nasta_detail")) == false ? cur.getString(cur.getColumnIndex("nasta_detail")) : "");
                progress.setHari_sabji_detail(cur.isNull(cur.getColumnIndex("hari_sabji_detail")) == false ? cur.getString(cur.getColumnIndex("hari_sabji_detail")) : "");
                progress.setJalavan_detail(cur.isNull(cur.getColumnIndex("jalavan_detail")) == false ? cur.getString(cur.getColumnIndex("jalavan_detail")) : "");
                progress.setAnda_detail(cur.isNull(cur.getColumnIndex("anda_detail")) == false ? cur.getString(cur.getColumnIndex("anda_detail")) : "");
                progress.setParivahan_detail(cur.isNull(cur.getColumnIndex("parivahan_detail")) == false ? cur.getString(cur.getColumnIndex("parivahan_detail")) : "");
                progress.setTotal_detail(cur.isNull(cur.getColumnIndex("total_detail")) == false ? cur.getString(cur.getColumnIndex("total_detail")) : "");
                progress.setLs_id(cur.isNull(cur.getColumnIndex("ls_id")) == false ? cur.getString(cur.getColumnIndex("ls_id")) : "");
                progress.setVoucher_no(cur.isNull(cur.getColumnIndex("voucher_no")) == false ? cur.getString(cur.getColumnIndex("voucher_no")) : "");
                progress.setVouter_img(cur.isNull(cur.getColumnIndex("vouter_img")) == false ?
                        cur.getBlob(cur.getColumnIndex("vouter_img")) : null);


                progressList.add(progress);
            }
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return progressList;

    }


    public static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int h = cal.get(Calendar.HOUR);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);

        String date = day + "/" + month + "/" + year + "  " + h + ":" + m + ":" + s;
        return date;

    }


    public ArrayList<DataProgress> getAllProgressList(String userId) {

        ArrayList<DataProgress> progressList = new ArrayList<DataProgress>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = {userId};
        Cursor cur = db.rawQuery("select slno,District_Code,Block_Code,Sector_Code,Panchayat_Code,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,Fyear,Remarks,EntryDate,Latitude,Longitude,UploadDate,EntryBy from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? LIMIT 3", param);

        try {

            while (cur.moveToNext()) {

                DataProgress progress = new DataProgress();

                progress.setSlno(cur.getString(cur.getColumnIndex("slno")));


                progress.setDistrict_code(cur.isNull(cur.getColumnIndex("District_Code")) == false ? cur.getString(cur.getColumnIndex("District_Code")) : "");
                progress.setBlock_code(cur.isNull(cur.getColumnIndex("Block_Code")) == false ? cur.getString(cur.getColumnIndex("Block_Code")) : "");
                progress.setSector_code(cur.isNull(cur.getColumnIndex("Sector_Code")) == false ? cur.getString(cur.getColumnIndex("Sector_Code")) : "");
                progress.setPanchayat_code(cur.isNull(cur.getColumnIndex("Panchayat_Code")) == false ? cur.getString(cur.getColumnIndex("Panchayat_Code")) : "");


                progress.setF11(cur.isNull(cur.getColumnIndex("F11")) == false ? cur.getString(cur.getColumnIndex("F11")) : "");
                progress.setF12(cur.isNull(cur.getColumnIndex("F12")) == false ? cur.getString(cur.getColumnIndex("F12")) : "");
                progress.setSpMakeFoodData(cur.isNull(cur.getColumnIndex("F13")) == false ? cur.getString(cur.getColumnIndex("F13")) : "");
                progress.setRegisteredChildren(cur.isNull(cur.getColumnIndex("F14")) == false ? cur.getString(cur.getColumnIndex("F14")) : "");
                progress.setFyear(cur.isNull(cur.getColumnIndex("Fyear")) == false ? cur.getString(cur.getColumnIndex("Fyear")) : "");

                progress.setSpinner1_Data(cur.isNull(cur.getColumnIndex("F1")) == false ? cur.getString(cur.getColumnIndex("F1")) : "");
                progress.setSpinner2_Data(cur.isNull(cur.getColumnIndex("F2")) == false ? cur.getString(cur.getColumnIndex("F2")) : "");
                progress.setSpinner3_Data(cur.isNull(cur.getColumnIndex("F3")) == false ? cur.getString(cur.getColumnIndex("F3")) : "");
                progress.setSpinner4_Data(cur.isNull(cur.getColumnIndex("F4")) == false ? cur.getString(cur.getColumnIndex("F4")) : "");
                progress.setSpinner5_Data(cur.isNull(cur.getColumnIndex("F5")) == false ? cur.getString(cur.getColumnIndex("F5")) : "");

                progress.setText1_Data(cur.isNull(cur.getColumnIndex("F6")) == false ? cur.getString(cur.getColumnIndex("F6")) : "");
                progress.setText2_Data(cur.isNull(cur.getColumnIndex("F7")) == false ? cur.getString(cur.getColumnIndex("F7")) : "");
                progress.setText3_Data(cur.isNull(cur.getColumnIndex("F8")) == false ? cur.getString(cur.getColumnIndex("F8")) : "");
                progress.setText4_Data(cur.isNull(cur.getColumnIndex("F9")) == false ? cur.getString(cur.getColumnIndex("F9")) : "");
                progress.setText5_Data(cur.isNull(cur.getColumnIndex("F10")) == false ? cur.getString(cur.getColumnIndex("F10")) : "");
                progress.setRemark_Data(cur.isNull(cur.getColumnIndex("Remarks")) == false ? cur.getString(cur.getColumnIndex("Remarks")) : "");


                progress.setEntry_Date(cur.isNull(cur.getColumnIndex("EntryDate")) == false ? cur.getString(cur.getColumnIndex("EntryDate")) : "");
                progress.setLatitude(cur.isNull(cur.getColumnIndex("Latitude")) == false ? cur.getString(cur.getColumnIndex("Latitude")) : "");
                progress.setLongitude(cur.isNull(cur.getColumnIndex("Longitude")) == false ? cur.getString(cur.getColumnIndex("Longitude")) : "");
                progress.setGps_date(cur.isNull(cur.getColumnIndex("UploadDate")) == false ? cur.getString(cur.getColumnIndex("UploadDate")) : "");


                progress.setUpload_by(cur.getString(cur.getColumnIndex("EntryBy")));

                String[] param2 = {userId, cur.getString(cur.getColumnIndex("slno"))};
                Cursor cur1 = db.rawQuery("select Photo1 from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? and slno = ? ", param2);
                Cursor cur2 = db.rawQuery("select Photo2 from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? and slno = ? ", param2);
                Cursor cur3 = db.rawQuery("select Photo3 from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? and slno = ? ", param2);
                Cursor cur4 = db.rawQuery("select Photo4 from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? and slno = ? ", param2);
                Cursor cur5 = db.rawQuery("select Photo5 from UploadData where Latitude IS NOT NULL and Longitude IS NOT NULL and EntryBy = ? and slno = ? ", param2);
                try {

                    while (cur1.moveToNext()) {
                        progress.setPhoto1(cur1.isNull(cur1.getColumnIndex("Photo1")) == false ? Base64
                                .encodeToString(
                                        cur1.getBlob(cur1.getColumnIndex("Photo1")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur1.close();
                }
                try {
                    while (cur2.moveToNext()) {
                        progress.setPhoto2(cur2.isNull(cur2.getColumnIndex("Photo2")) == false ? Base64
                                .encodeToString(cur2.getBlob(cur2.getColumnIndex("Photo2")), Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur2.close();
                }
                try {
                    while (cur3.moveToNext()) {
                        progress.setPhoto3(cur3.isNull(cur3.getColumnIndex("Photo3")) == false ? Base64
                                .encodeToString(
                                        cur3.getBlob(cur3.getColumnIndex("Photo3")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur3.close();
                }
                try {
                    while (cur4.moveToNext()) {
                        progress.setPhoto4(cur4.isNull(cur4.getColumnIndex("Photo4")) == false ? Base64
                                .encodeToString(
                                        cur4.getBlob(cur4.getColumnIndex("Photo4")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur4.close();
                }
                try {
                    while (cur5.moveToNext()) {
                        progress.setPhoto5(cur5.isNull(cur5.getColumnIndex("Photo5")) == false ? Base64
                                .encodeToString(
                                        cur5.getBlob(cur5.getColumnIndex("Photo5")),
                                        Base64.NO_WRAP) : "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cur5.close();
                }
                progressList.add(progress);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        } finally {
            cur.close();
            db.close();
        }
        return progressList;

    }

    public long deletePendingUpload(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("UploadData", "slno=? and EntryBy =?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deletePendingUpload2(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("UploadDataforGps", "sl_no=? and userId=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }

    public long deletePendingUpload3(String pid, String userId) {
        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(pid), userId};
            c = db.delete("SevikaSahaika", "slno=? and userId=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }


    public int getNumberOfPendingData(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select slno from Inspection where Latitude IS NOT NULL and Longitude IS NOT NULL and UploadBy =?", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberTotalOfPendingData(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select slno from UploadData where EntryBy =?", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberOfPendingData2(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select sl_no from UploadDataforGps where Latitude IS NOT NULL and Longitude IS NOT NULL and userId=? ", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }
    public int getNumberOfPendingData2GPS(String userId) {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};
            Cursor cur = db.rawQuery("Select sl_no from UploadDataforGps where userId=? ", whereArgs);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }

    public int getNumberOfPendingData3() {

        int x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {null, null};
            Cursor cur = db.rawQuery("Select * from myVoutcher", null);
            x = cur.getCount();
            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return x;

    }


    public ArrayList<DataProgress> getProgressList(String userId) {


        ArrayList<DataProgress> progressList = new ArrayList<DataProgress>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] whereArgs = {userId};


            Cursor cur = db
                    .rawQuery("Select * from UploadData where EntryBy=?", whereArgs);

            while (cur.moveToNext()) {

                DataProgress progress = new DataProgress();
                progress.setSlno(cur.getString(cur.getColumnIndex("slno")));
                progress.setLatitude(cur.isNull(cur.getColumnIndex("Latitude")) == false ? cur.getString(cur.getColumnIndex("Latitude")) : "NULL");
                progress.setLongitude(cur.isNull(cur.getColumnIndex("Longitude")) == false ? cur.getString(cur.getColumnIndex("Longitude")) : "NULL");

                progress.setText1_Data(cur.isNull(cur.getColumnIndex("F6")) == false ? cur.getString(cur.getColumnIndex("F6")) : "");
                progress.setText5_Data(cur.isNull(cur.getColumnIndex("F10")) == false ? cur.getString(cur.getColumnIndex("F10")) : "");
                progress.setRemark_Data(cur.isNull(cur.getColumnIndex("Remarks")) == false ? cur.getString(cur.getColumnIndex("Remarks")) : "");
                String pcode = (cur.isNull(cur.getColumnIndex("Panchayat_Code")) == false ? cur.getString(cur.getColumnIndex("Panchayat_Code")) : "");
                String kname = "";

                progress.setEntry_Date(cur.isNull(cur.getColumnIndex("EntryDate")) == false ? cur.getString(cur.getColumnIndex("EntryDate")) : "");


                whereArgs = new String[]{pcode};
                Cursor c = db.rawQuery(
                        "select * from Panchayat where Code=?",
                        whereArgs);

                if (c.getCount() > 0) {
                    c.moveToNext();
                    kname = c.getString(c.getColumnIndex("Name"));

                }
                c.close();
                progress.setPanchayatName(kname);
                progressList.add(progress);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception

        }
        return progressList;

    }

    public boolean deleteFromDB(String slno) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] sl = {slno};


        long c = db.delete("UploadData", "slno=?", sl);
        if (c > 0) return true;
        else return false;
    }


    public ArrayList<LocalSpinnerData> getLocalDependentSpinnerData(String tableName, String dependentField, String param) {


        ArrayList<LocalSpinnerData> fieldList = new ArrayList<LocalSpinnerData>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = null;
            String depField = dependentField;
            if ((dependentField != null) && (param != null) && (tableName != null)) {

                String[] params = new String[]{param};


                cur = db.rawQuery("Select * from " + tableName + " where " + depField + "=?", params);


            } else if (tableName != null) {
                cur = db.rawQuery("Select * from " + tableName, null);
            }


            if (cur != null) {
                while (cur.moveToNext()) {
                    String Code = cur.getString(cur.getColumnIndex("Code"));
                    String Name = cur.getString(cur.getColumnIndex("Name"));
                    String AwcCode = "";
                    if (tableName.equalsIgnoreCase("Panchayat")) {
                        AwcCode = cur.getString(cur.getColumnIndex("slno"));
                    }


                    fieldList.add(new LocalSpinnerData(Code, Name, "", AwcCode));
                }
            }

            cur.close();
            db.close();


        } catch (Exception e) {
            // TODO: handle exception
            return fieldList;

        }

        return fieldList;


    }

    public long setDistrictDataLocalUserWise(ArrayList<District> distlist, String userid) {

        long c = -1;
        ArrayList<District> dist = distlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (dist != null) {
            try {
                for (int i = 0; i < dist.size(); i++) {
                    values.put("Code", dist.get(i).get_DistCode());
                    values.put("Name", dist.get(i).get_DistName());
                    values.put("userid", userid);
                    String[] param = {dist.get(i).get_DistCode()};
                    //long update = db.update("DistDetail", values, "Code = ?", param);
                    //  if (!(update > 0))
                    c = db.insert("DistDetailUserBy", null, values);
                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setBlockDataForDist(ArrayList<Block> blocklist, String distCode) {

        long c = -1;
        ArrayList<Block> block = blocklist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Block");
        if (block.size() > 0) {
            try {


                for (int i = 0; i < block.size(); i++) {

                    values.put("Code", block.get(i).getBlockCode());
                    values.put("Name", block.get(i).getBlockName());
                    values.put("District_Code", distCode);
                  /*  String[] param={block.get(i).getCode()};
                    long update = db.update("Block", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Block", null, values);
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }
    public long setSectorDataLocal(ArrayList<SectorWeb> sectorWebArrayList, String blockCode) {

        long c = -1;
        ArrayList<SectorWeb> sectorWebs = sectorWebArrayList;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Sector");
        if (sectorWebs.size() > 0) {
            try {


                for (int i = 0; i < sectorWebs.size(); i++) {
                    values.put("Code", sectorWebs.get(i).getCode());
                    values.put("Name", sectorWebs.get(i).getValue());
                    values.put("Block_Code", blockCode);
                    /*String[] param={sectorWebs.get(i).getCode()};
                    long update = db.update("Sector", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Sector", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public long setPanchayatDataLocal(ArrayList<PanchayatWeb> panchayatlist, String sectorCode) {

        long c = -1;
        ArrayList<PanchayatWeb> panchayat = panchayatlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Panchayat");
        if (panchayat.size() > 0) {
            try {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("Code", panchayat.get(i).getCode());
                    values.put("slno", panchayat.get(i).getAWC_Code());
                    values.put("Name", panchayat.get(i).getValue());
                    values.put("Sector_Code", panchayat.get(i).getSectorCode());
                   /* String[] param={panchayat.get(i).getCode()};
                    long update = db.update("Panchayat", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Panchayat", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }
    public long setPanchayatDataForDistBlock(ArrayList<PanchayatData> panchayatlist, String distcode, String blkcode) {

        long c = -1;
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<PanchayatData> panchayat = panchayatlist;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL("Delete from Panchayat1");
        if (panchayat.size() > 0) {
            try {


                for (int i = 0; i < panchayat.size(); i++) {

                    values.put("PanchayatCode", panchayat.get(i).getPcode());
                    values.put("PanchayatName", panchayat.get(i).getPname());
                    values.put("DistCode", distcode);
                    values.put("BlockCode", blkcode);
                    // values.put("Sector_Code", panchayat.get(i).getSectorCode());
                   /* String[] param={panchayat.get(i).getCode()};
                    long update = db.update("Panchayat", values, "Code = ?", param);
                    if (!(update>0))*/
                    c = db.insert("Panchayat1", null, values);

                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;
    }

    public DataProgress getCredentialData() {
        DataProgress progress = new DataProgress();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db
                    .rawQuery(
                            "select * from Credential where slno = '1'",
                            null);
            if (cur.getCount() > 0) {

                while (cur.moveToNext()) {
                    progress.setDistrict_code(null != (cur.getString(cur.getColumnIndex("District_Code"))) ? cur.getString(cur.getColumnIndex("District_Code")) : "");
                    progress.setBlock_code(null != (cur.getString(cur.getColumnIndex("Block_Code"))) ? cur.getString(cur.getColumnIndex("Block_Code")) : "");
                    progress.setPanchayat_code(null != (cur.getString(cur.getColumnIndex("Panchayat_Code"))) ? cur.getString(cur.getColumnIndex("Panchayat_Code")) : "");
                    progress.setSector_code(null != (cur.getString(cur.getColumnIndex("Ward_Code"))) ? cur.getString(cur.getColumnIndex("Ward_Code")) : "");

                    String fyearcode = (null != cur.getString(cur.getColumnIndex("Fyear")) ? cur.getString(cur.getColumnIndex("Fyear")) : "");

                    progress.setFyear(fyearcode);

                    progress.setF11(null != cur.getString(cur.getColumnIndex("F11")) ? cur.getString(cur.getColumnIndex("F11")) : "");
                    progress.setF12(null != cur.getString(cur.getColumnIndex("F12")) ? cur.getString(cur.getColumnIndex("F12")) : "");


                    progress.setDistrictName(null != cur.getString(cur.getColumnIndex("DistrictName")) ? cur.getString(cur.getColumnIndex("DistrictName")) : "");

                    progress.setBlockName(null != cur.getString(cur.getColumnIndex("BlockName")) ? cur.getString(cur.getColumnIndex("BlockName")) : "");
                    progress.setSectorName(null != cur.getString(cur.getColumnIndex("WardName")) ? cur.getString(cur.getColumnIndex("WardName")) : "");

                    progress.setPanchayatName(null != cur.getString(cur.getColumnIndex("PanchayatName")) ? cur.getString(cur.getColumnIndex("PanchayatName")) : "");

                    progress.setFyearName(null != cur.getString(cur.getColumnIndex("FyearName")) ? cur.getString(cur.getColumnIndex("FyearName")) : "");


                    String[] whereArgs = new String[]{fyearcode};
                    cur = db.rawQuery(
                            "select * from Fyear where slno=?",
                            whereArgs);

                    if (cur.getCount() > 0) {
                        cur.moveToNext();

                        progress.setFyearName(cur.getString(1));
                        progress.setFyear(cur.getString(3));
                    } else progress.setFyear("");
                    cur.close();

                }
            } else {
                progress = null;
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;

        }
        return progress;
    }


    public String getPanchayatName(String pcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String kname = "";

        String[] whereArgs = new String[]{pcode};
        Cursor c = db.rawQuery(
                "select * from Panchayat where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            kname = c.getString(c.getColumnIndex("Name"));

        }
        c.close();


        return kname;

    }


    public String getDistrictName(String dcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{dcode};
        Cursor c = db.rawQuery(
                "select * from District where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Name"));

        }
        c.close();
        return name;

    }


    public String getBlockName(String bcode, Context context) {
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{bcode};
        Cursor c = db.rawQuery(
                "select * from Block where Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Name"));

        }
        c.close();
        return name;

    }


    public String getSpinnerValue(Context context, String fieldName, String code) {

        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String name = "";

        String[] whereArgs = new String[]{fieldName, code};
        Cursor c = db.rawQuery(
                "select Value from SpinnerMaster where Field=? and Code=?",
                whereArgs);

        if (c.getCount() > 0) {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("Value"));

        }
        c.close();
        return name;
    }


    public long deleteCredential(Context context, String pid) {

        DataBaseHelper placeData = new DataBaseHelper(context);
        SQLiteDatabase db = placeData.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Block_Code", "");
        values.put("BlockName", "");
        values.put("District_Code", "");
        values.put("DistrictName", "");
        values.put("Panchayat_Code", "");
        values.put("PanchayatName", "");
        values.put("WardName", "");
        values.put("Ward_Code", "");
        values.put("Fyear", "");
        values.put("FyearName", "");
        String[] param = {"1"};

        long update = db.update("Credential", values, "slno = ?", param);
        if (!(update > 0)) {
            update = db.insert("Credential", null, values);
        }
        db.close();
        return update;
    }


    public long insertSevikaSahaikaImage(Activity activity, SevikaSahaikaEntity sevikaSahaikaEntity) {
        long c=-1;
        try {
            DataBaseHelper placeData = new DataBaseHelper(activity);
            SQLiteDatabase db = placeData.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("UserId",sevikaSahaikaEntity.getUserId());
            contentValues.put("DistrictCode",sevikaSahaikaEntity.getDistrictCode());
            contentValues.put("ProjectCode",sevikaSahaikaEntity.getProjectCode());
            contentValues.put("SectorCode",sevikaSahaikaEntity.getSectorCode());
            contentValues.put("aanganvariCode",sevikaSahaikaEntity.getAanganvariCode());
            if (sevikaSahaikaEntity.getSevikaPhoto()!=null)contentValues.put("SevikaPhoto", Utiilties.bitmaptoByte(Utiilties.StringToBitMap(sevikaSahaikaEntity.getSevikaPhoto())));
            if (sevikaSahaikaEntity.getSahaikaPhoto()!=null)contentValues.put("SahaikaPhoto", Utiilties.bitmaptoByte(Utiilties.StringToBitMap(sevikaSahaikaEntity.getSahaikaPhoto())));
            c = db.insert("SevikaSahaika", null, contentValues);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return  c;
    }
    public ArrayList<SevikaSahaikaEntity> getAllSevikaSahaikaDetails(String userid){
        ArrayList<SevikaSahaikaEntity> sevikaSahaikaEntities=new ArrayList<SevikaSahaikaEntity>();
        try {
            DataBaseHelper dataBaseHelper=new DataBaseHelper(myContext);
            SQLiteDatabase sqLiteDatabase=dataBaseHelper.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from SevikaSahaika where UserId=?",new String[]{userid});
            while (cursor.moveToNext()){
                SevikaSahaikaEntity sevikaSahaikaEntity=new SevikaSahaikaEntity();
                sevikaSahaikaEntity.setId(Integer.toString(cursor.getInt(cursor.getColumnIndex("slno"))));
                sevikaSahaikaEntity.setUserId(cursor.getString(cursor.getColumnIndex("UserId")));
                sevikaSahaikaEntity.setDistrictCode(cursor.getString(cursor.getColumnIndex("DistrictCode")));
                sevikaSahaikaEntity.setProjectCode(cursor.getString(cursor.getColumnIndex("ProjectCode")));
                sevikaSahaikaEntity.setSectorCode(cursor.getString(cursor.getColumnIndex("SectorCode")));
                sevikaSahaikaEntity.setAanganvariCode(cursor.getString(cursor.getColumnIndex("aanganvariCode")));
                sevikaSahaikaEntity.setSevikaPhoto(cursor.isNull(cursor.getColumnIndex("SevikaPhoto")) == false ? Base64
                        .encodeToString(cursor.getBlob(cursor.getColumnIndex("SevikaPhoto")), Base64.NO_WRAP) : "");
                sevikaSahaikaEntity.setSahaikaPhoto(cursor.isNull(cursor.getColumnIndex("SahaikaPhoto")) == false ? Base64
                        .encodeToString(cursor.getBlob(cursor.getColumnIndex("SahaikaPhoto")), Base64.NO_WRAP) : "");
                sevikaSahaikaEntities.add(sevikaSahaikaEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sevikaSahaikaEntities;
    }
    public  int countSevikaSahaikaDetail(Activity mContext, String userid){
        Cursor cursor;
        try {
            DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
            SQLiteDatabase sqLiteDatabase=dataBaseHelper.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("select slno from SevikaSahaika where UserId=?",new String[]{userid});
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return cursor.getCount();
    }

    public long checkIfExistAwc(String panchayatcode) {
        long c = -1;
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor=db.rawQuery("select sl_no from UploadDataforGps where AWC_Code=?",new String[]{panchayatcode});
            c=cursor.getCount();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return c;
        }

        return c;
    }

    public boolean deleteBenFromMasterWardRec(String benid)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] sl = {benid};

        long c = db.delete("BeneficiaryForWard", "BenfiId=?", sl);
        if (c > 0) return true;
        else return false;
    }
    public boolean deleteBenWardRec(String sid)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] sl = {sid};

        long c = db.delete("Ben_Ward_Data", "id=?", sl);
        if (c > 0) return true;
        else return false;
    }
    public boolean deleteBenWardRecByBenID(String benid)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] sl = {benid};

        long c = db.delete("Ben_Ward_Data", "BenID=?", sl);
        if (c > 0) return true;
        else return false;
    }

    public String getBenID(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String name = "";

        String[] whereArgs = new String[]{id};
        Cursor c = db.rawQuery("select * from Ben_Ward_Data where id=?",whereArgs);

        if (c.getCount() > 0)
        {
            c.moveToNext();
            name = c.getString(c.getColumnIndex("BenID"));

        }
        c.close();
        return name;

    }

    public  int countBenWardData(Activity mContext, String userid){
        Cursor cursor;
        try
        {
            DataBaseHelper dataBaseHelper=new DataBaseHelper(mContext);
            SQLiteDatabase sqLiteDatabase=dataBaseHelper.getReadableDatabase();
            cursor=sqLiteDatabase.rawQuery("select * from Ben_Ward_Data where UserID=?",new String[]{userid});
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return cursor.getCount();
    }

    public ArrayList<PondLakeDepartmentEntity> getPondLakeDeptList()
    {
        ArrayList<PondLakeDepartmentEntity> deptList = new ArrayList<PondLakeDepartmentEntity>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            //String[] params = new String[] { blockCode };

            Cursor cur = db
                    .rawQuery(
                            "SELECT * from PondLakeDept",
                            null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                PondLakeDepartmentEntity dept = new PondLakeDepartmentEntity();
                dept.set_DepatmentName(cur.getString(cur.getColumnIndex("_DepatmentName")));
                dept.set_DepatmentHNd(cur.getString(cur.getColumnIndex("_DepatmentHNd")));
                dept.setId(cur.getInt(cur.getColumnIndex("id")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }

    public ArrayList<SanrachnaTypeEntity> getSanrachnaTypeList(String subExecID)
    {
        ArrayList<SanrachnaTypeEntity> deptList = new ArrayList<SanrachnaTypeEntity>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { subExecID };

            Cursor cur = db.rawQuery("SELECT * from PACS WHERE BlockCode= ?", params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                SanrachnaTypeEntity dept = new SanrachnaTypeEntity();
                dept.setSancrachnaId(cur.getString(cur.getColumnIndex("PacsID")));
                dept.setSancrachnaName(cur.getString(cur.getColumnIndex("PacsName")));
                dept.setSub_Execution_DeptID(cur.getString(cur.getColumnIndex("BlockCode")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }


    public ArrayList<ward> getWardList(String Pan_Code) {
        ArrayList<ward> deptList = new ArrayList<ward>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { Pan_Code };

            Cursor cur = db.rawQuery("SELECT * from Ward WHERE PanchayatCode= ?", params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                ward dept = new ward();
                dept.setWardCode(cur.getString(cur.getColumnIndex("WardCode")));
                dept.setWardname(cur.getString(cur.getColumnIndex("WardName")));
                dept.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                dept.setAreaType(cur.getString(cur.getColumnIndex("AreaType")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }

    public ArrayList<VillageListEntity> getVillageList(String Pan_Code)
    {
        ArrayList<VillageListEntity> deptList = new ArrayList<VillageListEntity>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { Pan_Code };

            Cursor cur = db.rawQuery("SELECT * from VillageList WHERE PanchayatCode= ?", params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                VillageListEntity dept = new VillageListEntity();
                dept.setVillCode(cur.getString(cur.getColumnIndex("VillageCode")));
                dept.setVillName(cur.getString(cur.getColumnIndex("VillageName")));
                dept.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                dept.setBlockCode(cur.getString(cur.getColumnIndex("BLOCKCODE")));

                deptList.add(dept);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return deptList;
    }

    public ArrayList<PanchayatData> getPanchayt(String blockCode)
    {
        ArrayList<PanchayatData> panchayatList = new ArrayList<PanchayatData>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { blockCode };

            Cursor cur = db
                    .rawQuery(
                            "SELECT PanchayatCode,PanchayatName,DistrictCode,BlockCode,PACName from Panchayat WHERE BlockCode = ? ORDER BY PanchayatName",
                            params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname(cur.getString(cur.getColumnIndex("PanchayatName")));
                panchayat.setBcode(cur.getString(cur.getColumnIndex("BlockCode")));
                panchayat.setDcode(cur.getString(cur.getColumnIndex("DistrictCode")));
                panchayat.setAreaType(cur.getString(cur.getColumnIndex("PACName")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<BenList> getBenList(String bentype, String awcid)
    {
        ArrayList<BenList> panchayatList = new ArrayList<BenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { bentype ,awcid};

            Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                BenList panchayat = new BenList();
                panchayat.setA_id(cur.getString(cur.getColumnIndex("a_ID")));
                panchayat.setHusband_Name(cur.getString(cur.getColumnIndex("Husband_Name")));
                panchayat.setWife_Name(cur.getString(cur.getColumnIndex("Wife_Name")));
                panchayat.setChild_Name(cur.getString(cur.getColumnIndex("Child_Name")));
                panchayat.setCategory(cur.getString(cur.getColumnIndex("Category")));
                panchayat.setAge(cur.getString(cur.getColumnIndex("Age")));
                panchayat.setGender(cur.getString(cur.getColumnIndex("Gender")));
                panchayat.setAadhar_Num(cur.getString(cur.getColumnIndex("Aadhar_Num")));
                panchayat.setAccount_Num(cur.getString(cur.getColumnIndex("Account_Num")));
                panchayat.setIFSC(cur.getString(cur.getColumnIndex("IFSCCode")));
                panchayat.setAwcId(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setLbharthi_Type(cur.getString(cur.getColumnIndex("BenType")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setNutritionId(cur.getString(cur.getColumnIndex("NutritionId")));
                panchayat.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));
                panchayat.setNoOfMonth(cur.getString(cur.getColumnIndex("NoOfMonth")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("IsDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getWdcBenListforadaptor(String awcid)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=? ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<OtpVerifyModel> getOtpListforadaptor(String awcid,String monthid,String year_id)
    {
        ArrayList<OtpVerifyModel> panchayatList = new ArrayList<OtpVerifyModel>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
        //    String[] params = new String[] { awcid};
            String[] params = new String[] {monthid,year_id};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            // Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  AwcId=? ", params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails where month_id=? and year_id=? ", null);
            int x = cur.getCount();
            while (cur.moveToNext())
            {
                OtpVerifyModel panchayat = new OtpVerifyModel();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));
                panchayat.setBenM_Name(cur.getString(cur.getColumnIndex("Ben_Mname")));
                panchayat.setBen_cat(cur.getString(cur.getColumnIndex("Ben_Categ")));
                panchayat.setOtp(cur.getString(cur.getColumnIndex("ben_otp")));
                panchayat.setOtp_valid_from(cur.getString(cur.getColumnIndex("otp_valid_from")));
                panchayat.setOtp_valid_to(cur.getString(cur.getColumnIndex("otp_valid_to")));
                panchayat.setMonth(cur.getString(cur.getColumnIndex("month_id")));
                panchayat.setYear(cur.getString(cur.getColumnIndex("year_id")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("isDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getWdcBenListforshowlist(String awcid)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=?",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<OtpVerifyModel> getOtpDetailsBenListforshowlist(String awcid,String monthid,String yearid)
    {
        ArrayList<OtpVerifyModel> panchayatList = new ArrayList<OtpVerifyModel>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid,monthid,yearid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  AwcId=? and month_id=? and year_id=? and Is_Verify='Y' and isDataReady!='Y' and isDataReady!='Y'",params);
            // Cursor cur = db.rawQuery("SELECT * from OtpDetails ",null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                OtpVerifyModel panchayat = new OtpVerifyModel();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                // panchayat.setBen_Id(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));
                panchayat.setOtp(cur.getString(cur.getColumnIndex("ben_otp")));
                panchayat.setBenM_Name(cur.getString(cur.getColumnIndex("Ben_Mname")));
                panchayat.setBen_cat(cur.getString(cur.getColumnIndex("Ben_Categ")));
                panchayat.setOtp_valid_from(cur.getString(cur.getColumnIndex("otp_valid_from")));
                panchayat.setOtp_valid_to(cur.getString(cur.getColumnIndex("otp_valid_to")));
                panchayat.setBen_Id(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setNutrition_type(cur.getString(cur.getColumnIndex("nutrition")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("gender")));
                panchayat.setBen_Gen(cur.getString(cur.getColumnIndex("gender")));
                panchayat.setBen_type(cur.getString(cur.getColumnIndex("bentype")));
                panchayat.setYob(cur.getString(cur.getColumnIndex("Yob")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setNoOfMonths(cur.getString(cur.getColumnIndex("NoofMonth")));

                panchayat.setMonth(cur.getString(cur.getColumnIndex("month_id")));
                panchayat.setYear(cur.getString(cur.getColumnIndex("year_id")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getWdcBenList(String awcid)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=? AND IsBenUpdated !='Y'",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getOtpBenList(String awcid)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  AwcId=? AND IsBenUpdated !='Y'",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getWdcBenList_toupload(String awcid)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awcid};
            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=?  and IsBenUpdated='Y'",params);
            int x = cur.getCount();


            while (cur.moveToNext())
            {
                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<BenList> getBenListWithoutBenType(String awcid)
    {
        ArrayList<BenList> panchayatList = new ArrayList<BenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid};

            Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE AwcId=? ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                BenList panchayat = new BenList();
                panchayat.setA_id(cur.getString(cur.getColumnIndex("a_ID")));
                panchayat.setHusband_Name(cur.getString(cur.getColumnIndex("Husband_Name")));
                panchayat.setWife_Name(cur.getString(cur.getColumnIndex("Wife_Name")));
                panchayat.setChild_Name(cur.getString(cur.getColumnIndex("Child_Name")));
                panchayat.setCategory(cur.getString(cur.getColumnIndex("Category")));
                panchayat.setAge(cur.getString(cur.getColumnIndex("Age")));
                panchayat.setGender(cur.getString(cur.getColumnIndex("Gender")));
                panchayat.setAadhar_Num(cur.getString(cur.getColumnIndex("Aadhar_Num")));
                panchayat.setAccount_Num(cur.getString(cur.getColumnIndex("Account_Num")));
                panchayat.setIFSC(cur.getString(cur.getColumnIndex("IFSCCode")));
                panchayat.setAwcId(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setLbharthi_Type(cur.getString(cur.getColumnIndex("BenType")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setNutritionId(cur.getString(cur.getColumnIndex("NutritionId")));
                panchayat.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));
                panchayat.setNoOfMonth(cur.getString(cur.getColumnIndex("NoOfMonth")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("IsDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<BenList> getBenListLocal(String bentype, String awcid)
    {
        ArrayList<BenList> panchayatList = new ArrayList<BenList>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { bentype ,awcid,"Y"};

            Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? AND IsBenUpdated=?",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                BenList panchayat = new BenList();

                panchayat.setA_id(cur.getString(cur.getColumnIndex("a_ID")));
                panchayat.setHusband_Name(cur.getString(cur.getColumnIndex("Husband_Name")));
                panchayat.setWife_Name(cur.getString(cur.getColumnIndex("Wife_Name")));
                panchayat.setChild_Name(cur.getString(cur.getColumnIndex("Child_Name")));
                panchayat.setCategory(cur.getString(cur.getColumnIndex("Category")));
                panchayat.setAge(cur.getString(cur.getColumnIndex("Age")));
                panchayat.setGender(cur.getString(cur.getColumnIndex("Gender")));
                panchayat.setAadhar_Num(cur.getString(cur.getColumnIndex("Aadhar_Num")));
                panchayat.setAccount_Num(cur.getString(cur.getColumnIndex("Account_Num")));
                panchayat.setIFSC(cur.getString(cur.getColumnIndex("IFSCCode")));
                panchayat.setAwcId(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setLbharthi_Type(cur.getString(cur.getColumnIndex("BenType")));

                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                Log.d("bhcbhbch","**"+panchayat.getIsBenPer());
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setNutritionId(cur.getString(cur.getColumnIndex("NutritionId")));
                panchayat.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));
                panchayat.setNoOfMonth(cur.getString(cur.getColumnIndex("NoOfMonth")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("IsDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<BenList> getBenList(String awcid)
    {
        ArrayList<BenList> panchayatList = new ArrayList<BenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {  awcid,"Y"};

            Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE  AwcId=? AND IsBenUpdated=? ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                BenList panchayat = new BenList();
                panchayat.setA_id(cur.getString(cur.getColumnIndex("a_ID")));
                panchayat.setHusband_Name(cur.getString(cur.getColumnIndex("Husband_Name")));
                panchayat.setWife_Name(cur.getString(cur.getColumnIndex("Wife_Name")));
                panchayat.setChild_Name(cur.getString(cur.getColumnIndex("Child_Name")));
                panchayat.setCategory(cur.getString(cur.getColumnIndex("Category")));
                panchayat.setAge(cur.getString(cur.getColumnIndex("Age")));
                panchayat.setGender(cur.getString(cur.getColumnIndex("Gender")));
                panchayat.setAadhar_Num(cur.getString(cur.getColumnIndex("Aadhar_Num")));
                panchayat.setAccount_Num(cur.getString(cur.getColumnIndex("Account_Num")));
                panchayat.setIFSC(cur.getString(cur.getColumnIndex("IFSCCode")));
                panchayat.setAwcId(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setLbharthi_Type(cur.getString(cur.getColumnIndex("BenType")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setNutritionId(cur.getString(cur.getColumnIndex("NutritionId")));
                panchayat.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));
                panchayat.setNoOfMonth(cur.getString(cur.getColumnIndex("NoOfMonth")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("IsDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<BenList> getBenListByAwc(String awcid)
    {
        ArrayList<BenList> panchayatList = new ArrayList<BenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid};

            Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE  AwcId=? ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                BenList panchayat = new BenList();
                panchayat.setA_id(cur.getString(cur.getColumnIndex("a_ID")));
                panchayat.setHusband_Name(cur.getString(cur.getColumnIndex("Husband_Name")));
                panchayat.setWife_Name(cur.getString(cur.getColumnIndex("Wife_Name")));
                panchayat.setChild_Name(cur.getString(cur.getColumnIndex("Child_Name")));
                panchayat.setCategory(cur.getString(cur.getColumnIndex("Category")));
                panchayat.setAge(cur.getString(cur.getColumnIndex("Age")));
                panchayat.setGender(cur.getString(cur.getColumnIndex("Gender")));
                panchayat.setAadhar_Num(cur.getString(cur.getColumnIndex("Aadhar_Num")));
                panchayat.setAccount_Num(cur.getString(cur.getColumnIndex("Account_Num")));
                panchayat.setIFSC(cur.getString(cur.getColumnIndex("IFSCCode")));
                panchayat.setAwcId(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setLbharthi_Type(cur.getString(cur.getColumnIndex("BenType")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setNutritionId(cur.getString(cur.getColumnIndex("NutritionId")));
                panchayat.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));
                panchayat.setNoOfMonth(cur.getString(cur.getColumnIndex("NoOfMonth")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("IsDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<PlantationSiteEntity> getPlantationSiteDetail()
    {
        ArrayList<PlantationSiteEntity> infoList = new ArrayList<PlantationSiteEntity>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            //String[] params = new String[] { blockCode };

            Cursor cur = db.rawQuery("SELECT * from PlantationSiteDetail ORDER BY id", null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                PlantationSiteEntity info = new PlantationSiteEntity();
                info.setId(cur.getString(cur.getColumnIndex("id")));
                info.setSite_Name(cur.getString(cur.getColumnIndex("Site_Name")));
                infoList.add(info);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return infoList;
    }

    public ArrayList<PanchayatData> getPanchaytAreawise(String blockCode, String areaType)
    {
        ArrayList<PanchayatData> panchayatList = new ArrayList<PanchayatData>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { blockCode, areaType };

            Cursor cur = db.rawQuery("SELECT PanchayatCode,PanchayatName,DistrictCode,BlockCode,PACName from Panchayat WHERE BlockCode = ? AND PACName = ? ORDER BY PanchayatName",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname(cur.getString(cur.getColumnIndex("PanchayatName")));
                panchayat.setBcode(cur.getString(cur.getColumnIndex("BlockCode")));
                panchayat.setDcode(cur.getString(cur.getColumnIndex("DistrictCode")));
                panchayat.setAreaType(cur.getString(cur.getColumnIndex("PACName")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<Sub_abyb> getSubAbyab(String Krinawayan_Code)
    {
        ArrayList<Sub_abyb> panchayatList = new ArrayList<Sub_abyb>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { Krinawayan_Code };

            Cursor cur = db.rawQuery("SELECT * from SubSubExectDept WHERE SubExectDeptId=?", params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                Sub_abyb panchayat = new Sub_abyb();
                panchayat.setSub_abyb_Code(cur.getString(cur.getColumnIndex("SubSubExectDept_Id")));
                panchayat.setSub_abyb_name(cur.getString(cur.getColumnIndex("SubSubExectDept_Name")));
                panchayat.setAbyb_Code(cur.getString(cur.getColumnIndex("SubExectDeptId")));

                panchayatList.add(panchayat);
            }
            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<AWC_Model> getAwcLiost(String secId)
    {
        //CREATE TABLE "DistDetail" ( `Code` TEXT NOT NULL, `Name` TEXT, `slno`
        // INTEGER, PRIMARY KEY(`Code`) )
        ArrayList<AWC_Model> districtList = new ArrayList<AWC_Model>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { secId };

            Cursor cur = db.rawQuery("SELECT * from AwcTable WHERE SectorId=?", params);


            int x = cur.getCount();

            while (cur.moveToNext())
            {
                AWC_Model district = new AWC_Model();
                district.setAWC_CODE(cur.getString(cur.getColumnIndex("AWC_CODE")));
                district.setAWC_NAME(cur.getString(cur.getColumnIndex("AWC_NAME")));
                district.setSectorId(cur.getString(cur.getColumnIndex("SectorId")));

                districtList.add(district);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return districtList;
    }

    public ArrayList<District> getDistrict()
    {
        //CREATE TABLE "DistDetail" ( `Code` TEXT NOT NULL, `Name` TEXT, `slno`
        // INTEGER, PRIMARY KEY(`Code`) )
        ArrayList<District> districtList = new ArrayList<District>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT DistCode,DistName from Districts ORDER BY DistName",null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                District district = new District();
                district.set_DistCode(cur.getString(cur.getColumnIndex("DistCode")));
                district.set_DistName(cur.getString(cur.getColumnIndex("DistName")));

                districtList.add(district);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return districtList;
    }

    public ArrayList<District> getDistrictUserBy()
    {
        //CREATE TABLE "DistDetail" ( `Code` TEXT NOT NULL, `Name` TEXT, `slno`
        // INTEGER, PRIMARY KEY(`Code`) )
        ArrayList<District> districtList = new ArrayList<District>();

        try
        {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT Code,Name from DistDetailUserBy ORDER BY Name",null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                District district = new District();
                district.set_DistCode(cur.getString(cur.getColumnIndex("Code")));
                district.set_DistName(cur.getString(cur.getColumnIndex("Name")));

                districtList.add(district);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        return districtList;

    }

    public ArrayList<Nutrition> getNuterition()
    {
        //CREATE TABLE "DistDetail" ( `Code` TEXT NOT NULL, `Name` TEXT, `slno`
        // INTEGER, PRIMARY KEY(`Code`) )
        ArrayList<Nutrition> NutritionList = new ArrayList<Nutrition>();

        try
        {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db.rawQuery("SELECT * from loadISNutrition ",null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                Nutrition district = new Nutrition();
                district.setNutritionId(cur.getString(cur.getColumnIndex("Nutritioid")));
                district.setNutritionName(cur.getString(cur.getColumnIndex("NutritionName")));

                NutritionList.add(district);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        return NutritionList;

    }

    public ArrayList<Block> getBlock(String distCode)
    {

        ArrayList<Block> blockList = new ArrayList<Block>();
//CREATE TABLE `Block` ( `slno` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
// `District_Code` TEXT, `Code` TEXT, `Name` TEXT )
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { distCode };
            Cursor cur = db.rawQuery("SELECT BlockCode,DistCode,BlockName from Blocks WHERE DistCode = ? ORDER BY BlockName ",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                Block block = new Block();
                block.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                block.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                block.setDistCode(cur.getString(cur.getColumnIndex("DistCode")));

                blockList.add(block);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return blockList;

    }

    public ArrayList<Financial_Year> getFinancialYr()
    {
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<Financial_Year> pdetail = new ArrayList<Financial_Year>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM  FinancialYear where Active='Y' order by Fin_Yr_Name", null);
            int x = cur.getCount();
            while (cur.moveToNext())
            {
                Financial_Year panchayat = new Financial_Year();
                panchayat.setFinancial_year(cur.getString(cur.getColumnIndex("Fin_Yr_Name")));
                panchayat.setYear_Id((cur.getString(cur.getColumnIndex("Fin_Yr_ID"))));
                pdetail.add(panchayat);
            }
            cur.close();
            db.close();
        }
        catch (Exception e)
        {

        }
        return pdetail;
    }


    public ArrayList<Financial_Month> getFinancialMonth()
    {
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<Financial_Month> pdetail = new ArrayList<Financial_Month>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM  FinMonth where Active='Y' order by Month_Name", null);
            int x = cur.getCount();
            while (cur.moveToNext())
            {
                Financial_Month panchayat = new Financial_Month();
                panchayat.setMonthID(cur.getString(cur.getColumnIndex("Month_Id")));
                panchayat.setMonthName((cur.getString(cur.getColumnIndex("Month_Name"))));
                panchayat.setYear((cur.getString(cur.getColumnIndex("Year"))));
                pdetail.add(panchayat);
            }
            cur.close();
            db.close();
        }
        catch (Exception e)
        {

        }
        return pdetail;
    }


    public ArrayList<PanchayatData> getPanchayatLocal(String blkId)
    {
        //CREATE TABLE `Panchayat1` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `DistCode` TEXT,
        // `BlockCode` TEXT, `PanchayatCode` TEXT, `PanchayatName` TEXT )
        ArrayList<PanchayatData> pdetail = new ArrayList<PanchayatData>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM  Panchayat1 where BlockCode='" + blkId + "' order by PanchayatName", null);
            int x = cur.getCount();
            while (cur.moveToNext())
            {
                PanchayatData panchayat = new PanchayatData();
                panchayat.setPcode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                panchayat.setPname((cur.getString(cur.getColumnIndex("PanchayatName"))));
                pdetail.add(panchayat);
            }
            cur.close();
            db.close();
        }
        catch (Exception e)
        {

        }
        return pdetail;
    }
    public long setBenLocal(ArrayList<BenList> result, String Awcid)
    {
        long c = -1;
        ArrayList<BenList> info = result;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("BenDetails", null, null);
        ContentValues values = new ContentValues();

        if (info != null)
        {
            try
            {
                for (int i = 0; i < info.size(); i++)
                {
                    values.put("a_ID", info.get(i).getA_id());
                    values.put("Husband_Name", info.get(i).getHusband_Name());
                    values.put("Wife_Name", info.get(i).getWife_Name());
                    values.put("Child_Name", info.get(i).getChild_Name());
                    values.put("Category", info.get(i).getCategory());
                    values.put("Age", info.get(i).getAge());
                    values.put("Gender", info.get(i).getGender());
                    values.put("Aadhar_Num", info.get(i).getAadhar_Num());
                    values.put("Account_Num", info.get(i).getAccount_Num());
                    values.put("IFSCCode", info.get(i).getIFSC());
                    values.put("AwcId", Awcid);
                    values.put("BenType",info.get(i).getLbharthi_Type());
                    //values.put("IsBenPer", "N");
                    values.put("IsBenPer", info.get(i).getIsBenPer());
                    // Log.d("bhcbhbch","**"+info.get(i).getIsBenPer());
                    values.put("BenPerDate", info.get(i).getBenPerDate());
                    // values.put("BenPerDate", "N");
                    values.put("IsRecordUpdated", "N");
                    values.put("NutritionName",info.get(i).getNutritionName());
                    values.put("NutritionId",info.get(i).getNutritionId());
                    values.put("NoOfMonth",info.get(i).getNoOfMonth());
                    values.put("IsBenUpdated", "N");
                    values.put("IsDataReady", info.get(i).getIsDataReady());

                    c = db.insert("BenDetails", null, values);

                    if (c > 0)
                    {
                        Log.e("Data", "Inserted");
                    }
                    else
                    {
                        Log.e("Data", "Not Inserted");
                    }
                }
                this.getWritableDatabase().close();
                db.close();
            }

            catch (Exception e)
            {
                e.printStackTrace();

                if(db!=null)
                {
                    db.close();
                }
                return c;
            }
            finally
            {
                if(db!=null)
                {
                    safeCloseDB(db);
                }
            }
        }
        return c;

    }

    public long setWDCBenLocal(ArrayList<wdcBenList> result, String Awcid)
    {
        long c = -1;
        ArrayList<wdcBenList> info = result;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("wdcBenDetails", null, null);
        ContentValues values = new ContentValues();

        if (info != null)
        {
            try
            {
                for (int i = 0; i < info.size(); i++)
                {
                    values.put("a_Id", info.get(i).getId());
                    values.put("Dist_Name", info.get(i).getDistName());
                    values.put("Block_Name", info.get(i).getBlockname());
                    values.put("Ben_Id", info.get(i).getBeneficieryId());
                    values.put("Ben_Name", info.get(i).getBen_Name());
                    values.put("BenFh_Name", info.get(i).getBenFH_Name());
                    values.put("Ben_Gender", info.get(i).getBen_Gender());
                    values.put("Ben_MobNo", info.get(i).getBen_MobileNo());
                    values.put("Ben_AadhaarNo", info.get(i).getBen_AadharCardNo());
                    values.put("Ben_AcHName", info.get(i).getBen_AccountHolderName());
                    values.put("Ben_AcNo", info.get(i).getBen_AccountNo());
                    values.put("Ben_Ifsc",info.get(i).getBen_IFSCCode());
                    values.put("Bank_Name",info.get(i).getBankName());
                    values.put("AwcId",info.get(i).getAWCCode());
                    //values.put("IsBenPer", "N");
                    values.put("IsBenPer", info.get(i).getIsBenPer());
                    values.put("BenPerDate", info.get(i).getBenPerDate());
                    values.put("IsRecordUpdated", "N");
                    values.put("IsBenUpdated", "N");
                    //  values.put("IsDataReady", info.get(i).getIsDataReady());

                    //  c = db.insert("wdcBenDetails", null, values);
                    String[] whereArgs = new String[]{info.get(i).getId()};

                    //  c = db.insert("OtpDetails", null, values);
                    c = db.update("wdcBenDetails", values, "a_Id=?", whereArgs);
                    if (c <= 0)
                    {
                        c = db.insert("wdcBenDetails", null, values);
                    }

                    if (c > 0)
                    {
                        Log.e("Data", "Inserted");
                    }
                    else
                    {
                        Log.e("Data", "Not Inserted");
                    }
                }
                this.getWritableDatabase().close();
                db.close();
            }

            catch (Exception e)
            {
                e.printStackTrace();

                if(db!=null)
                {
                    db.close();
                }
                return c;
            }
            finally
            {
                if(db!=null)
                {
                    safeCloseDB(db);
                }
            }
        }
        return c;
    }

    public long setOtpBenLocal(ArrayList<OtpVerifyModel> result, String Awcid,String monthid,String yearid)
    {
        _encrptor=new Encriptor();
        long c = -1;
        ArrayList<OtpVerifyModel> info = result;
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete("OtpDetails", null, null);
        ContentValues values = new ContentValues();

        if (info != null)
        {
            try
            {
                for (int i = 0; i < info.size(); i++)
                {
                    values.put("a_Id", info.get(i).getId());

                    values.put("Dist_Name", info.get(i).getDistName());
                    values.put("Block_Name", info.get(i).getBlockname());
                    values.put("Ben_Id", info.get(i).getBen_Id());
                    values.put("Ben_Name", info.get(i).getBen_Name());
                    values.put("BenFh_Name", info.get(i).getBenFH_Name());
                    values.put("Ben_Gender", info.get(i).getBen_Gender());
                    values.put("Ben_MobNo", info.get(i).getBen_MobileNo());
                    values.put("Ben_AadhaarNo", info.get(i).getBen_AadharCardNo());
                    values.put("Ben_AcHName", info.get(i).getBen_AccountHolderName());
                    values.put("Ben_AcNo", info.get(i).getBen_AccountNo());
                    values.put("Ben_Ifsc",info.get(i).getBen_IFSCCode());
                    values.put("Bank_Name",info.get(i).getBankName());
                    values.put("AwcId",Awcid);
                    values.put("IsBenPer", info.get(i).getIsBenPer());
                    values.put("BenPerDate", info.get(i).getBenPerDate());

                    values.put("ben_otp", info.get(i).getOtp());

                    values.put("Ben_Mname", info.get(i).getBenM_Name());
                    values.put("Ben_Categ", info.get(i).getBen_cat());
                    values.put("otp_valid_from", info.get(i).getOtp_valid_from());
                    values.put("otp_valid_to", info.get(i).getOtp_valid_to());
                    values.put("Is_Verify", info.get(i).getIs_Verify());
                    values.put("Yob", info.get(i).getYob());
                    values.put("NoofMonth", info.get(i).getNoOfMonths());
                    values.put("nutrition", info.get(i).getNutrition_type());
                    values.put("bentype", info.get(i).getBen_type());
                    values.put("gender", info.get(i).getBen_Gen());
                    values.put("IsRecordUpdated", "N");
                    values.put("IsBenUpdated", "N");
                    values.put("month_id",monthid);
                    values.put("year_id",yearid);
                    values.put("isDataReady",info.get(i).getIsDataReady());

                    //  values.put("IsDataReady", info.get(i).getIsDataReady());

                    String[] whereArgs = new String[]{info.get(i).getId(),monthid,yearid};

                    //  c = db.insert("OtpDetails", null, values);
                    c = db.update("OtpDetails", values, "a_Id=? and month_id=? and year_id=?", whereArgs);
                    if (c <= 0)
                    {
                        c = db.insert("OtpDetails", null, values);
                    }

                    if (c > 0)
                    {
                        Log.e("Data", "Inserted");
                    }
                    else
                    {
                        Log.e("Data", "Not Inserted");
                    }
                }
                this.getWritableDatabase().close();
                db.close();
            }

            catch (Exception e)
            {
                e.printStackTrace();

                if(db!=null)
                {
                    db.close();
                }
                return c;
            }
            finally
            {
                if(db!=null)
                {
                    safeCloseDB(db);
                }
            }
        }
        return c;

    }


    public static void safeCloseDB(SQLiteDatabase db)
    {
        if (db != null)
        {
            try
            {
                db.close();
            }
            catch (SQLiteException e)
            {
                Log.e("log_tag", "Error safeCloseDB" );
            }
        }
    }

    public ArrayList<wdcBenList> getWdcBenList_tosyncNew()
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            // String[] params = new String[] { awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  IsBenUpdated='Y'",null);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<OtpVerifyModel> getOtpVerifyList_toupload(String awcid,String monthid,String yearid)
    {
        ArrayList<OtpVerifyModel> panchayatList = new ArrayList<OtpVerifyModel>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid,monthid,yearid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  AwcId=? and month_id=? and year_id=? and IsBenUpdated='Y'and Is_Verify='Y' and isDataReady!='Y'",params);
            // Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  IsBenUpdated='Y'",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                OtpVerifyModel panchayat = new OtpVerifyModel();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));
                panchayat.setBen_cat(cur.getString(cur.getColumnIndex("Ben_Categ")));
                panchayat.setBenM_Name(cur.getString(cur.getColumnIndex("Ben_Mname")));
                panchayat.setOtp(cur.getString(cur.getColumnIndex("ben_otp")));
                panchayat.setMonth(cur.getString(cur.getColumnIndex("month_id")));
                panchayat.setYear(cur.getString(cur.getColumnIndex("year_id")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<OtpVerifyModel> getOtpVerifyList_toSyncNew(String awc_id)
    {
        ArrayList<OtpVerifyModel> panchayatList = new ArrayList<OtpVerifyModel>();
        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] { awc_id};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            //Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=?  and IsBenUpdated='Y'",params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE   AwcId=? and IsBenUpdated='Y'",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {

                OtpVerifyModel panchayat = new OtpVerifyModel();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));
                panchayat.setBen_cat(cur.getString(cur.getColumnIndex("Ben_Categ")));
                panchayat.setBenM_Name(cur.getString(cur.getColumnIndex("Ben_Mname")));
                panchayat.setOtp(cur.getString(cur.getColumnIndex("ben_otp")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public ArrayList<wdcBenList> getWdcBenListforshowlistByFilter(String awcid,String acc)
    {
        ArrayList<wdcBenList> panchayatList = new ArrayList<wdcBenList>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from wdcBenDetails WHERE  AwcId=? AND Ben_AcNo Like '%"+acc+"%'",params);
            int x = cur.getCount();


            while (cur.moveToNext())
            {

                wdcBenList panchayat = new wdcBenList();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }
        return panchayatList;
    }

    public ArrayList<OtpVerifyModel> getOTPBenListforshowlistByFilter(String awcid,String acc,String monthid,String yearid)
    {
        ArrayList<OtpVerifyModel> panchayatList = new ArrayList<OtpVerifyModel>();
        try
        {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] params = new String[] {awcid,monthid,yearid};

            //   Cursor cur = db.rawQuery("SELECT * from BenDetails WHERE BenType = ? AND AwcId=? ",params);
            Cursor cur = db.rawQuery("SELECT * from OtpDetails WHERE  AwcId=? AND month_id=? AND year_id=? AND Ben_AcNo Like '%"+acc+"%'",params);
            int x = cur.getCount();

            while (cur.moveToNext())
            {
                OtpVerifyModel panchayat = new OtpVerifyModel();
                panchayat.setId(cur.getString(cur.getColumnIndex("a_Id")));
                panchayat.setAWCCode(cur.getString(cur.getColumnIndex("AwcId")));
                panchayat.setDistName(cur.getString(cur.getColumnIndex("Dist_Name")));
                panchayat.setBlockname(cur.getString(cur.getColumnIndex("Block_Name")));
                panchayat.setBeneficieryId(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_Name(cur.getString(cur.getColumnIndex("Ben_Name")));
                panchayat.setBenFH_Name(cur.getString(cur.getColumnIndex("BenFh_Name")));
                panchayat.setBen_Gender(cur.getString(cur.getColumnIndex("Ben_Gender")));
                panchayat.setBen_MobileNo(cur.getString(cur.getColumnIndex("Ben_MobNo")));
                panchayat.setBen_AadharCardNo(cur.getString(cur.getColumnIndex("Ben_AadhaarNo")));
                panchayat.setBen_AccountHolderName(cur.getString(cur.getColumnIndex("Ben_AcHName")));
                panchayat.setBen_AccountNo(cur.getString(cur.getColumnIndex("Ben_AcNo")));
                panchayat.setBen_IFSCCode(cur.getString(cur.getColumnIndex("Ben_Ifsc")));
                panchayat.setIsBenPer(cur.getString(cur.getColumnIndex("IsBenPer")));
                panchayat.setBenPerDate(cur.getString(cur.getColumnIndex("BenPerDate")));
                panchayat.setIsBenUpdated(cur.getString(cur.getColumnIndex("IsBenUpdated")));
                panchayat.setRejectReason(cur.getString(cur.getColumnIndex("ben_rej_reason")));
                panchayat.setBen_cat(cur.getString(cur.getColumnIndex("Ben_Categ")));
                panchayat.setBenM_Name(cur.getString(cur.getColumnIndex("Ben_Mname")));
                panchayat.setOtp(cur.getString(cur.getColumnIndex("ben_otp")));
                panchayat.setOtp_valid_from(cur.getString(cur.getColumnIndex("otp_valid_from")));
                panchayat.setOtp_valid_to(cur.getString(cur.getColumnIndex("otp_valid_to")));
                panchayat.setBen_Id(cur.getString(cur.getColumnIndex("Ben_Id")));
                panchayat.setBen_type(cur.getString(cur.getColumnIndex("bentype")));
                panchayat.setNutrition_type(cur.getString(cur.getColumnIndex("nutrition")));
                panchayat.setBen_Gen(cur.getString(cur.getColumnIndex("gender")));
                panchayat.setYob(cur.getString(cur.getColumnIndex("Yob")));
                panchayat.setNoOfMonths(cur.getString(cur.getColumnIndex("NoofMonth")));
                panchayat.setMonth(cur.getString(cur.getColumnIndex("month_id")));
                panchayat.setYear(cur.getString(cur.getColumnIndex("year_id")));
                panchayat.setIsDataReady(cur.getString(cur.getColumnIndex("isDataReady")));

                panchayatList.add(panchayat);
            }

            cur.close();
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception

        }
        return panchayatList;
    }

    public long setFinancialMonthLocal(ArrayList<Financial_Month> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Financial_Month> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("FinMonth",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Month_Name", info.get(i).getMonthName());
                    values.put("Month_Id", info.get(i).getMonthID());
                    values.put("Year", info.get(i).getYear());
                    values.put("Active","Y");

                    String[] whereArgs = new String[]{info.get(i).getMonthID()};

                    c = db.update("FinMonth", values, "Month_Id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("FinMonth", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

}