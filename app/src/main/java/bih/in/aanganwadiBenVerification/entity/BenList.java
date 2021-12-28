package bih.in.aanganwadiBenVerification.entity;

import android.util.Log;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class BenList extends ArrayList<BenList> implements KvmSerializable{

    private static final long serialVersionUID = 1L;

    public static Class<BenList> Ben_CLASS = BenList.class;

    private String id = "";
    private String Husband_Name = "";
    private String Wife_Name = "";
    private String Aadhar_Num = "";
    private String Gender = "";
    private String Category = "";
    private String Age = "";
    private String Account_Num = "";
    private String Child_Name = "";
    private String Lbharthi_Type = "";
    private String a_id = "";
    private String IsBenPer = "";
    private String BenPerDate= "";
    private boolean isSelected;
    private String AwcName="";
    private String AWCGOICODE="";
    private String AWNM="";
    private String IFSC="";
    private String AwcId;
    private String IsBenUpdated;
    private String _VerifiedBy_;
    private String NutritionName;
    private String NutritionId;
    private String NoOfMonth;
    private String IsDataReady;
    private String Delete_Ben;


    public BenList() {
    }

    public BenList(SoapObject final_object) {
        this.a_id = final_object.getProperty("a_ID").toString();
        this.AwcName = final_object.getProperty("AwcName").toString();
        this.AWCGOICODE = final_object.getProperty("AWCGOICODE").toString();
        this.AWNM = final_object.getProperty("AWNM").toString();

        this.Husband_Name = final_object.getProperty("FNAMEAADHAR").toString();
        this.Wife_Name = final_object.getProperty("MNAMEAADHAR").toString();
        this.IFSC = final_object.getProperty("IFSC").toString();

        this.Aadhar_Num = final_object.getProperty("AADHARNUMBERF").toString();
        this.Account_Num = final_object.getProperty("ACCOUNTNUMBER").toString();
        this.Category = final_object.getProperty("CATEGORYDETAIL").toString();


        this.Gender = final_object.getProperty("GENDERNAME").toString();
        this.Age = final_object.getProperty("AGE").toString();
        this.NutritionName=final_object.getProperty("NutritionName").toString();
        this.Child_Name = final_object.getProperty("BENNAME").toString();
        this.Lbharthi_Type = final_object.getProperty("BENTYPE").toString();

        this.IsBenPer = final_object.getProperty("ISverified").toString();
        if(IsBenPer.equalsIgnoreCase("NA")){
         this.IsBenPer="anyType{}";
        }
        this.BenPerDate = final_object.getProperty("VerifiedDate").toString();
        if(BenPerDate.equalsIgnoreCase("NA")){
            this.BenPerDate="anyType{}";
        }
        this.NutritionId=final_object.getProperty("IsNutrition").toString();
        this.NoOfMonth=final_object.getProperty("NoMonth").toString();

        this.IsDataReady=final_object.getProperty("IsDataReady").toString();
        if(BenPerDate.equalsIgnoreCase("NA")){
            this.BenPerDate="N";
        }

       // Log.d("TAGADATA&",IsBenPer+BenPerDate);


    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHusband_Name() {
        return Husband_Name;
    }

    public void setHusband_Name(String husband_Name) {
        Husband_Name = husband_Name;
    }

    public String getWife_Name() {
        return Wife_Name;
    }

    public void setWife_Name(String wife_Name) {
        Wife_Name = wife_Name;
    }

    public String getAadhar_Num() {
        return Aadhar_Num;
    }

    public void setAadhar_Num(String aadhar_Num) {
        Aadhar_Num = aadhar_Num;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCategory() {
        return Category;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAccount_Num() {
        return Account_Num;
    }

    public void setAccount_Num(String account_Num) {
        Account_Num = account_Num;
    }

    public String getChild_Name() {
        return Child_Name;
    }

    public void setChild_Name(String child_Name) {
        Child_Name = child_Name;
    }

    public String getLbharthi_Type() {
        return Lbharthi_Type;
    }

    public void setLbharthi_Type(String lbharthi_Type) {
        Lbharthi_Type = lbharthi_Type;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getIsBenPer() {
        return IsBenPer;
    }

    public void setIsBenPer(String isBenPer) {
        IsBenPer = isBenPer;
    }

    public String getBenPerDate() {
        return BenPerDate;
    }

    public void setBenPerDate(String benPerDate) {
        BenPerDate = benPerDate;
    }

    public String getAwcName() {
        return AwcName;
    }

    public void setAwcName(String awcName) {
        AwcName = awcName;
    }

    public String getAWCGOICODE() {
        return AWCGOICODE;
    }

    public void setAWCGOICODE(String AWCGOICODE) {
        this.AWCGOICODE = AWCGOICODE;
    }

    public String getAWNM() {
        return AWNM;
    }

    public void setAWNM(String AWNM) {
        this.AWNM = AWNM;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getAwcId() {
        return AwcId;
    }

    public void setAwcId(String awcId) {
        AwcId = awcId;
    }

    public String getIsBenUpdated() {
        return IsBenUpdated;
    }

    public void setIsBenUpdated(String isBenUpdated) {
        IsBenUpdated = isBenUpdated;
    }

    public String get_VerifiedBy_() {
        return _VerifiedBy_;
    }

    public void set_VerifiedBy_(String _VerifiedBy_) {
        this._VerifiedBy_ = _VerifiedBy_;
    }

    public String getNutritionName() {
        return NutritionName;
    }

    public void setNutritionName(String nutritionName) {
        NutritionName = nutritionName;
    }

    public String getNutritionId() {
        return NutritionId;
    }

    public void setNutritionId(String nutritionId) {
        NutritionId = nutritionId;
    }

    public String getNoOfMonth() {
        return NoOfMonth;
    }

    public void setNoOfMonth(String noOfMonth) {
        NoOfMonth = noOfMonth;
    }

    public String getIsDataReady() {
        return IsDataReady;
    }

    public void setIsDataReady(String isDataReady) {
        IsDataReady = isDataReady;
    }

    public String getDelete_Ben() {
        return Delete_Ben;
    }

    public void setDelete_Ben(String delete_Ben) {
        Delete_Ben = delete_Ben;
    }
}
