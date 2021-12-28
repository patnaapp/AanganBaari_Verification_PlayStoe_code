package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

import bih.in.aanganwadiBenVerification.utility.CommonPref;
import bih.in.aanganwadiBenVerification.utility.Encriptor;

public class OtpVerifyModel {

    private String Id="";
    private String Ben_Id="";
    private String DeptId="";
    private String DistName="";
    private String blockname="";
    private String BeneficieryId="";
    private String Ben_Name="";
    private String BenFH_Name="";
    private String BenM_Name="";
    private String Ben_Gender="";
    private String Ben_MobileNo="";
    private String Ben_AadharCardNo="";
    private String Ben_Address="";
    private String Ben_AccountHolderName="";
    private String Ben_AccountNo="";
    private String Ben_IFSCCode="";
    private String BankName="";
    private String AWCCode="";
    private String AWCName="";
    private String IsBenPer = "";
    private String BenPerDate= "";
    private String IsBenUpdated;
    private String _VerifiedBy_;
    private boolean isSelected;
    private  String RejectReason;
    private  String Otp;
    private  String OTPNew;
    private String Ben_cat="";
    private String Ben_Gen="";
    private String otp_valid_from="";
    private String otp_valid_to="";
    private String Is_Verify="";
    private String nutrition_type="";
    private String ben_type="";
    private String yob="";
    private String noOfMonths="";
    private String month="";
    private String year="";
    private String IsDataReady="";
    Encriptor _encrptor;


    public static Class<OtpVerifyModel> OTP_CLASS = OtpVerifyModel.class;

    public OtpVerifyModel(SoapObject final_object) {
        _encrptor=new Encriptor();

        try {
            this.Id = final_object.getProperty("a_ID").toString();
            this.Ben_Id = final_object.getProperty("BenId").toString();

            this.BenFH_Name = final_object.getProperty("FNAMEAADHAR").toString();
            this.BenM_Name = final_object.getProperty("MNAMEAADHAR").toString();

            this.Ben_AadharCardNo = final_object.getProperty("AADHARNUMBERF").toString();
            this.Ben_AccountNo = final_object.getProperty("ACCOUNTNUMBER").toString();
            this.Ben_IFSCCode = final_object.getProperty("IFSC").toString();
            this.AWCCode = final_object.getProperty("AWCGOICODE").toString();
            this.AWCName = final_object.getProperty("AwcName").toString();
            this.Ben_cat = final_object.getProperty("CATEGORYDETAIL").toString();

            //this.Otp=_encrptor.Encrypt(final_object.getProperty("OTP").toString(), CommonPref.CIPER_KEY);
            //  this.Otp = final_object.getProperty("OTP").toString();
            this.Otp = final_object.getProperty("OTPNew").toString();
            //  this.OTPNew = final_object.getProperty("OTPNew").toString();
            this.otp_valid_from = final_object.getProperty("OTPValidFrom").toString();
            this.otp_valid_to = final_object.getProperty("OTPValidTo").toString();
            this.Is_Verify = final_object.getProperty("Isverify").toString();
            this.nutrition_type = final_object.getProperty("Nutrition").toString();
            this.ben_type = final_object.getProperty("Ben_Type").toString();
            this.yob = final_object.getProperty("Age").toString();
            this.noOfMonths = final_object.getProperty("NoOfMonthNew").toString();
            this.Ben_Gen = final_object.getProperty("Gender").toString();
            this.Ben_Name = final_object.getProperty("BenName").toString();
            this.IsDataReady = final_object.getProperty("IsDataReady").toString();

            this.IsBenPer="anyType{}";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public OtpVerifyModel()
    {

    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public String getDeptId()
    {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getBeneficieryId() {
        return BeneficieryId;
    }

    public void setBeneficieryId(String beneficieryId) {
        BeneficieryId = beneficieryId;
    }

    public String getBen_Name() {
        return Ben_Name;
    }

    public void setBen_Name(String ben_Name) {
        Ben_Name = ben_Name;
    }

    public String getBenFH_Name() {
        return BenFH_Name;
    }

    public void setBenFH_Name(String benFH_Name) {
        BenFH_Name = benFH_Name;
    }

    public String getBen_Gender() {
        return Ben_Gender;
    }

    public void setBen_Gender(String ben_Gender) {
        Ben_Gender = ben_Gender;
    }

    public String getBen_MobileNo() {
        return Ben_MobileNo;
    }

    public void setBen_MobileNo(String ben_MobileNo) {
        Ben_MobileNo = ben_MobileNo;
    }

    public String getBen_AadharCardNo() {
        return Ben_AadharCardNo;
    }

    public void setBen_AadharCardNo(String ben_AadharCardNo) {
        Ben_AadharCardNo = ben_AadharCardNo;
    }

    public String getBen_Address() {
        return Ben_Address;
    }

    public void setBen_Address(String ben_Address) {
        Ben_Address = ben_Address;
    }

    public String getBen_AccountHolderName() {
        return Ben_AccountHolderName;
    }

    public void setBen_AccountHolderName(String ben_AccountHolderName) {
        Ben_AccountHolderName = ben_AccountHolderName;
    }

    public String getBen_AccountNo() {
        return Ben_AccountNo;
    }

    public void setBen_AccountNo(String ben_AccountNo) {
        Ben_AccountNo = ben_AccountNo;
    }

    public String getBen_IFSCCode() {
        return Ben_IFSCCode;
    }

    public void setBen_IFSCCode(String ben_IFSCCode) {
        Ben_IFSCCode = ben_IFSCCode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getAWCCode() {
        return AWCCode;
    }

    public void setAWCCode(String AWCCode) {
        this.AWCCode = AWCCode;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getRejectReason() {
        return RejectReason;
    }

    public void setRejectReason(String rejectReason) {
        RejectReason = rejectReason;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    public String getBen_cat() {
        return Ben_cat;
    }

    public void setBen_cat(String ben_cat) {
        Ben_cat = ben_cat;
    }

    public String getAWCName() {
        return AWCName;
    }

    public void setAWCName(String AWCName) {
        this.AWCName = AWCName;
    }

    public String getBenM_Name() {
        return BenM_Name;
    }

    public void setBenM_Name(String benM_Name) {
        BenM_Name = benM_Name;
    }

    public String getOtp_valid_from() {
        return otp_valid_from;
    }

    public void setOtp_valid_from(String otp_valid_from) {
        this.otp_valid_from = otp_valid_from;
    }

    public String getOtp_valid_to() {
        return otp_valid_to;
    }

    public void setOtp_valid_to(String otp_valid_to) {
        this.otp_valid_to = otp_valid_to;
    }

    public String getIs_Verify() {
        return Is_Verify;
    }

    public void setIs_Verify(String is_Verify) {
        Is_Verify = is_Verify;
    }

    public String getBen_Id() {
        return Ben_Id;
    }

    public void setBen_Id(String ben_Id) {
        Ben_Id = ben_Id;
    }

    public String getBen_Gen() {
        return Ben_Gen;
    }

    public void setBen_Gen(String ben_Gen) {
        Ben_Gen = ben_Gen;
    }

    public String getNutrition_type() {
        return nutrition_type;
    }

    public void setNutrition_type(String nutrition_type) {
        this.nutrition_type = nutrition_type;
    }

    public String getBen_type() {
        return ben_type;
    }

    public void setBen_type(String ben_type) {
        this.ben_type = ben_type;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public String getNoOfMonths() {
        return noOfMonths;
    }

    public void setNoOfMonths(String noOfMonths) {
        this.noOfMonths = noOfMonths;
    }

    public String getOTPNew() {
        return OTPNew;
    }

    public void setOTPNew(String OTPNew) {
        this.OTPNew = OTPNew;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsDataReady() {
        return IsDataReady;
    }

    public void setIsDataReady(String isDataReady) {
        IsDataReady = isDataReady;
    }
}
