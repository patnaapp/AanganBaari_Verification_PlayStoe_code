package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Date;
import java.util.Hashtable;

public class UserDetails implements KvmSerializable {
    private static Class<UserDetails> USER_CLASS = UserDetails.class;

    private boolean isAuthenticated = true;

    private String Password = "";
    private String UserID = "";
    private String Dist_Code = "";
    private String Dist_Name = "";
    private String Project_Code = "";
    private String Project_Name = "";
   private String  Sector_Code = "";
    private String Sector_Name = "";
    private String Panchayat_Code = "";
    private String Panchayat_Name = "";
    private String Awc_Code = "";
    private String Awc_Name = "";
    private String Otp = "";
    private String Husband_Name = "";
    private String Wife_Name = "";
    private String Account_Number = "";
    private String Aadhar_Number = "";
    private String Labharthi_type_Id = "";


    private String LastVisitedOn = "";
    private String MobileNo = "";
    private String Address = "";
    private String Email = "";
    private String DistrictCode = "";
    private String DistName = "";
    private String BlockCode = "";
    private String BlockName = "";
    private String PanchayatName = "";
    private String PanchayatCode = "";
    private String Degignation = "";
    private String Userrole = "";
    private String Name = "";
    private String UserName = "";
    private String AWCID = "";
    private String AWCIDNAME = "";




    public UserDetails() {
    }

    @SuppressWarnings("deprecation")
    public UserDetails(SoapObject obj) {
        this.setAuthenticated(Boolean.parseBoolean(obj.getProperty("isAuthenticated").toString()));
        this.setUserID(obj.getProperty("UserID").toString());
        this.setUserName(obj.getProperty("UserName").toString());
        this.setMobileNo(obj.getProperty("Mobile").toString());
        this.setUserrole(obj.getProperty("Role").toString());
        this.setPanchayatCode(obj.getProperty("panchayatcode").toString());
        this.setPanchayatName(obj.getProperty("Panchayatname").toString());

        this.setDistrictCode(obj.getProperty("DistCode").toString());
        this.setDegignation(obj.getProperty("Designation").toString());
        this.setDistName(obj.getProperty("DistName").toString());
        this.setProject_Code(obj.getProperty("ProjectCode").toString());
        this.setProject_Name(obj.getProperty("Project_Name").toString());
        this.Sector_Code=(obj.getProperty("SectorCode").toString());
        this.Sector_Name=(obj.getProperty("SectorName").toString());
       this.setAwc_Code(obj.getProperty("AWCID").toString());
       this.Awc_Name=(obj.getProperty("AWCName").toString());
        this.setOtp(obj.getProperty("OTP").toString());
    }

    public static Class<UserDetails> getUserClass() {
        return USER_CLASS;
    }

    public static void setUserClass(Class<UserDetails> userClass) {
        USER_CLASS = userClass;
    }


    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        return 8;
    }

    @Override
    public Object getProperty(int index) {
        Object object = null;
        switch (index) {
            case 0: {
                object = this.isAuthenticated;
                break;
            }
            case 1: {
                object = this.Password;
                break;
            }
            case 2: {
                object = this.UserID;
                break;
            }
            case 3: {
                object = this.LastVisitedOn;
                break;
            }
            case 4: {
                object = this.MobileNo;
                break;
            }

            case 5: {
                object = this.Address;
                break;
            }

            case 6: {
                object = this.Email;
                break;

            }
            case 7: {
                object = this.DistrictCode;
                break;
            }
            case 8: {
                object = this.DistName;
                break;
            }
            case 9: {
                object = this.BlockCode;
                break;
            }
            case 10: {
                object = this.BlockName;
                break;
            }

            case 11: {
                object = this.Degignation;
                break;
            }

            case 12: {
                object = this.Userrole;
                break;

            }
            case 13: {
                object = this.Name;
                break;

            }
        }
        return object;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable arg1,
                                PropertyInfo propertyInfo) {
        switch (index) {
            case 0: {
                propertyInfo.name = "isAuthenticated";
                propertyInfo.type = PropertyInfo.BOOLEAN_CLASS;
                break;
            }
            case 1: {
                propertyInfo.name = "Password";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 2: {
                propertyInfo.name = "UserID";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 3: {
                propertyInfo.name = "LastVisitedOn";
                propertyInfo.type = Integer.class;
                break;
            }
            case 4: {
                propertyInfo.name = "MobileNo";
                propertyInfo.type = Date.class;
                break;
            }

            case 5: {
                propertyInfo.name = "Address";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 6: {
                propertyInfo.name = "Email";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 7: {
                propertyInfo.name = "DistrictCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 8: {
                propertyInfo.name = "DistName";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 9: {
                propertyInfo.name = "BlockCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 10: {
                propertyInfo.name = "BlockName";
                propertyInfo.type = Integer.class;
                break;
            }
            case 11: {
                propertyInfo.name = "Degignation";
                propertyInfo.type = Date.class;
                break;
            }

            case 12: {
                propertyInfo.name = "Userrole";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 13: {
                propertyInfo.name = "Name";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
        }
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.isAuthenticated = Boolean.parseBoolean(obj.toString());
                break;
            }
            case 1: {
                this.Password = obj.toString();
                break;
            }
            case 2: {
                this.UserID = obj.toString();
                break;
            }
            case 3: {
                this.LastVisitedOn = obj.toString();
                break;
            }
            case 4: {
                this.MobileNo = obj.toString();
                break;
            }

            case 5: {
                this.Address = obj.toString();
                break;
            }

            case 6: {
                this.Email = obj.toString();
                break;
            }
            case 7: {
                this.DistrictCode = obj.toString();
                break;
            }
            case 8: {
                this.DistName = obj.toString();
                break;
            }
            case 9: {
                this.BlockCode = obj.toString();
                break;
            }
            case 10: {
                this.BlockName = obj.toString();
                break;
            }

            case 11: {
                this.Degignation = obj.toString();
                break;
            }

            case 12: {
                this.Userrole = obj.toString();
                break;
            }

            case 13: {
                this.Name = obj.toString();
                break;
            }
        }
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getLastVisitedOn() {
        return LastVisitedOn;
    }

    public void setLastVisitedOn(String lastVisitedOn) {
        LastVisitedOn = lastVisitedOn;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getDegignation() {
        return Degignation;
    }

    public void setDegignation(String degignation) {
        Degignation = degignation;
    }

    public String getUserrole() {
        return Userrole;
    }

    public void setUserrole(String userrole) {
        Userrole = userrole;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    public String getDist_Code() {
        return Dist_Code;
    }

    public void setDist_Code(String dist_Code) {
        Dist_Code = dist_Code;
    }

    public String getDist_Name() {
        return Dist_Name;
    }

    public void setDist_Name(String dist_Name) {
        Dist_Name = dist_Name;
    }

    public String getProject_Code() {
        return Project_Code;
    }

    public void setProject_Code(String project_Code) {
        Project_Code = project_Code;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public void setProject_Name(String project_Name) {
        Project_Name = project_Name;
    }

    public String getPanchayat_Code() {
        return Panchayat_Code;
    }

    public void setPanchayat_Code(String panchayat_Code) {
        Panchayat_Code = panchayat_Code;
    }

    public String getPanchayat_Name() {
        return Panchayat_Name;
    }

    public void setPanchayat_Name(String panchayat_Name) {
        Panchayat_Name = panchayat_Name;
    }

    public String getAwc_Code() {
        return Awc_Code;
    }

    public void setAwc_Code(String awc_Code) {
        Awc_Code = awc_Code;
    }

    public String getAwc_Name() {
        return Awc_Name;
    }

    public void setAwc_Name(String awc_Name) {
        Awc_Name = awc_Name;
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

    public String getAccount_Number() {
        return Account_Number;
    }

    public void setAccount_Number(String account_Number) {
        Account_Number = account_Number;
    }

    public String getAadhar_Number() {
        return Aadhar_Number;
    }

    public void setAadhar_Number(String aadhar_Number) {
        Aadhar_Number = aadhar_Number;
    }

    public String getLabharthi_type_Id() {
        return Labharthi_type_Id;
    }

    public void setLabharthi_type_Id(String labharthi_type_Id) {
        Labharthi_type_Id = labharthi_type_Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAWCID() {
        return AWCID;
    }

    public void setAWCID(String AWCID) {
        this.AWCID = AWCID;
    }

    public String getAWCIDNAME() {
        return AWCIDNAME;
    }

    public void setAWCIDNAME(String AWCIDNAME) {
        this.AWCIDNAME = AWCIDNAME;
    }

    public String getSector_Code() {
        return Sector_Code;
    }

    public void setSector_Code(String sector_Code) {
        Sector_Code = sector_Code;
    }

    public String getSector_Name() {
        return Sector_Name;
    }

    public void setSector_Name(String sector_Name) {
        Sector_Name = sector_Name;
    }
}

