package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

/**
 * Created by nisci on 11-Aug-2017.
 */

public class ReportEntity implements Serializable {
    public static Class<ReportEntity> ReportEntity_CLASS= ReportEntity.class;
    private String AWCGOICode;
    private String AWCName;
    private String MakeFood;
    private String NoOfRegChild;
    private String Open_Flag;
    private String Children_Present;
    private String Morning_Snacks;
    private String Menu_Followed;
    private String Rating;
    private String Remarks;
    private String Insp_Date_GPS;
    private String Latitude;
    private String Longitude;
    private String Upload_Date;
    private String Children_PhotoPath;
    private String S_W_Children_PhotoPath;
    private String Attend_Reg_PhotoPath;
    private String Attend_Reg_PhotoPath2;
    private String Insp_Reg_PhotoPath;

    public ReportEntity()
    {

    }
    public ReportEntity(SoapObject sobj)
    {
        AWCGOICode= String.valueOf(sobj.getProperty("AWCGOICode"));
        AWCName= String.valueOf(sobj.getProperty("AWCName"));
        NoOfRegChild= String.valueOf(sobj.getProperty("NoOfRegChild"));
        Open_Flag= String.valueOf(sobj.getProperty("Open_Flag"));
        Children_Present= String.valueOf(sobj.getProperty("Children_Present"));
        Morning_Snacks= String.valueOf(sobj.getProperty("Morning_Snacks"));
        Menu_Followed= String.valueOf(sobj.getProperty("Menu_Followed"));
        MakeFood= String.valueOf(sobj.getProperty("MakeFood"));
        Rating= String.valueOf(sobj.getProperty("Rating"));
        Remarks= String.valueOf(sobj.getProperty("Remarks"));
        Insp_Date_GPS= String.valueOf(sobj.getProperty("Insp_Date_GPS"));
        Latitude= String.valueOf(sobj.getProperty("Latitude"));
        Longitude= String.valueOf(sobj.getProperty("Longitude"));
        Upload_Date= String.valueOf(sobj.getProperty("Upload_Date"));
        Children_PhotoPath= String.valueOf(sobj.getProperty("Children_PhotoPath"));
        S_W_Children_PhotoPath= String.valueOf(sobj.getProperty("S_W_Children_PhotoPath"));
        Attend_Reg_PhotoPath= String.valueOf(sobj.getProperty("Attend_Reg_PhotoPath"));
        Attend_Reg_PhotoPath2= String.valueOf(sobj.getProperty("Attend_Reg_PhotoPath2"));
        Insp_Reg_PhotoPath= String.valueOf(sobj.getProperty("Insp_Reg_PhotoPath"));

    }

    public String getAWCGOICode() {
        return AWCGOICode;
    }

    public void setAWCGOICode(String AWCGOICode) {
        this.AWCGOICode = AWCGOICode;
    }

    public String getAWCName() {
        return AWCName;
    }

    public void setAWCName(String AWCName) {
        this.AWCName = AWCName;
    }

    public String getMakeFood() {
        return MakeFood;
    }

    public void setMakeFood(String makeFood) {
        MakeFood = makeFood;
    }

    public String getNoOfRegChild() {
        return NoOfRegChild;
    }

    public void setNoOfRegChild(String noOfRegChild) {
        NoOfRegChild = noOfRegChild;
    }

    public String getOpen_Flag() {
        return Open_Flag;
    }

    public void setOpen_Flag(String open_Flag) {
        Open_Flag = open_Flag;
    }

    public String getChildren_Present() {
        return Children_Present;
    }

    public void setChildren_Present(String children_Present) {
        Children_Present = children_Present;
    }

    public String getMorning_Snacks() {
        return Morning_Snacks;
    }

    public void setMorning_Snacks(String morning_Snacks) {
        Morning_Snacks = morning_Snacks;
    }

    public String getMenu_Followed() {
        return Menu_Followed;
    }

    public void setMenu_Followed(String menu_Followed) {
        Menu_Followed = menu_Followed;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getInsp_Date_GPS() {
        return Insp_Date_GPS;
    }

    public void setInsp_Date_GPS(String insp_Date_GPS) {
        Insp_Date_GPS = insp_Date_GPS;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getChildren_PhotoPath() {
        return Children_PhotoPath;
    }

    public void setChildren_PhotoPath(String children_PhotoPath) {
        Children_PhotoPath = children_PhotoPath;
    }

    public String getS_W_Children_PhotoPath() {
        return S_W_Children_PhotoPath;
    }

    public void setS_W_Children_PhotoPath(String s_W_Children_PhotoPath) {
        S_W_Children_PhotoPath = s_W_Children_PhotoPath;
    }

    public String getUpload_Date() {
        return Upload_Date;
    }

    public void setUpload_Date(String upload_Date) {
        Upload_Date = upload_Date;
    }

    public String getAttend_Reg_PhotoPath() {
        return Attend_Reg_PhotoPath;
    }

    public void setAttend_Reg_PhotoPath(String attend_Reg_PhotoPath) {
        Attend_Reg_PhotoPath = attend_Reg_PhotoPath;
    }

    public String getAttend_Reg_PhotoPath2() {
        return Attend_Reg_PhotoPath2;
    }

    public void setAttend_Reg_PhotoPath2(String attend_Reg_PhotoPath2) {
        Attend_Reg_PhotoPath2 = attend_Reg_PhotoPath2;
    }

    public String getInsp_Reg_PhotoPath() {
        return Insp_Reg_PhotoPath;
    }

    public void setInsp_Reg_PhotoPath(String insp_Reg_PhotoPath) {
        Insp_Reg_PhotoPath = insp_Reg_PhotoPath;
    }
}
