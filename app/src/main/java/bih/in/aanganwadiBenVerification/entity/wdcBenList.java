package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class wdcBenList {
    private String Id="";
    private String DeptId="";
    private String DistName="";
    private String blockname="";
    private String BeneficieryId="";
    private String Ben_Name="";
    private String BenFH_Name="";
    private String Ben_Gender="";
    private String Ben_MobileNo="";
    private String Ben_AadharCardNo="";
    private String Ben_Address="";
    private String Ben_AccountHolderName="";
    private String Ben_AccountNo="";
    private String Ben_IFSCCode="";
    private String BankName="";
    private String AWCCode="";
    private String IsBenPer = "";
    private String BenPerDate= "";
    private String IsBenUpdated;
    private String _VerifiedBy_;
    private boolean isSelected;
    private  String RejectReason;
    private  String scheme_Id;

    public static Class<wdcBenList> Ben_CLASS = wdcBenList.class;

    public wdcBenList(SoapObject final_object) {
        this.Id = final_object.getProperty("Id").toString();
        this.DeptId = final_object.getProperty("DeptId").toString();
        this.DistName = final_object.getProperty("DistName").toString();
        this.blockname = final_object.getProperty("blockname").toString();
        this.BeneficieryId = final_object.getProperty("BeneficieryId").toString();
        this.Ben_Name = final_object.getProperty("Ben_Name").toString();
        this.BenFH_Name = final_object.getProperty("BenFH_Name").toString();
        this.Ben_Gender = final_object.getProperty("Ben_Gender").toString();
        this.Ben_MobileNo = final_object.getProperty("Ben_MobileNo").toString();
        this.Ben_AadharCardNo = final_object.getProperty("Ben_AadharCardNo").toString();
        this.Ben_AccountHolderName = final_object.getProperty("Ben_AccountHolderName").toString();
        this.Ben_AccountNo = final_object.getProperty("Ben_AccountNo").toString();
        this.Ben_IFSCCode = final_object.getProperty("Ben_IFSCCode").toString();
        this.BankName = final_object.getProperty("BankName").toString();
        this.AWCCode = final_object.getProperty("AWCCode").toString();
        this.IsBenPer="anyType{}";
    }

    public wdcBenList() {

    }


    public String getId() {


        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeptId() {
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

    public String getScheme_Id() {
        return scheme_Id;
    }

    public void setScheme_Id(String scheme_Id) {
        this.scheme_Id = scheme_Id;
    }
}
