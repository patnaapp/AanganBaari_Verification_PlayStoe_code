package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class WellInspectionEntity implements KvmSerializable, Serializable {

    private int id;
    private String InspectionID;
    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;
    private String RajswaThanaNumber;
    private String VillageID;
    private String VillageName;
    private String Latitude;
    private String Longitude;
    private String Longitude_Mob;
    private String Latitude_Mob;
    private String Verified_Date;

    private String Panchayat_Code;
    private String Panchayat_Name;
    private String Khata_Khesra_No;
    private String Private_or_Public;
    private String Outside_Diamter;
    private String Requirement_Of_Flyer;
    private String Status_of_Encroachment;
    private String Types_of_Encroachment;
    private String Requirement_of_Renovation;
   // private String Recommended_Execution_Dept;
    private String Remarks;
    private String isUpdated;
    private String Requirement_of_machine;
    private String Name_of_undertaken = "";
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String WellAvblValue;
    private String wellOwnerOtherDeptName;


    public String getWellOwnerOtherDeptName() {
        return wellOwnerOtherDeptName;
    }

    public void setWellOwnerOtherDeptName(String wellOwnerOtherDeptName) {
        this.wellOwnerOtherDeptName = wellOwnerOtherDeptName;
    }

    public String getWellAvblValue() {
        return WellAvblValue;
    }

    public void setWellAvblValue(String wellAvblValue) {
        WellAvblValue = wellAvblValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

//    @Override
//    public void setProperty(int i, Object o) {
//
//    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }

    public String getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(String inspectionID) {
        InspectionID = inspectionID;
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.InspectionID = obj.toString();
                break;
            }
            case 1: {
                this.DistID = obj.toString();
                break;
            }
            case 2: {
                this.BlockID = obj.toString();
                break;
            }
            case 3: {
                this.BlockName = obj.toString();
                break;
            }
            case 4: {
                this.RajswaThanaNumber = obj.toString();
                break;
            }

            case 5: {
                this.VillageID = obj.toString();
                break;
            }

            case 6: {
                this.VillageName = obj.toString();
                break;
            }
            case 7: {
                this.Latitude = obj.toString();
                break;
            }
            case 8: {
                this.Longitude = obj.toString();
                break;
            }
            case 9: {
                this.Longitude_Mob = obj.toString();
                break;
            }
            case 10: {
                Latitude_Mob = obj.toString();
                break;
            }
            case 11: {
                this.Verified_Date = obj.toString();
                break;
            }

            case 12: {
                this.Panchayat_Code = obj.toString();
                break;
            }

            case 13: {
                this.Panchayat_Name = obj.toString();
                break;
            }

            case 14: {
                this.Khata_Khesra_No = obj.toString();
                break;
            }

            case 15: {
                this.Private_or_Public = obj.toString();
                break;
            }
            case 16: {
                this.Outside_Diamter = obj.toString();
                break;
            }
            case 17: {
                this.Requirement_Of_Flyer = obj.toString();
                break;
            }
            case 18: {
                this.Status_of_Encroachment = obj.toString();
                break;
            }
            case 19: {
                this.Types_of_Encroachment = obj.toString();
                break;
            }
            case 20: {
                this.Requirement_of_Renovation = obj.toString();
                break;
            }
            case 21: {
                this.Remarks = obj.toString();
                break;
            }
            case 22: {
                this.isUpdated = obj.toString();
                break;
            }

            case 23: {
                this.Requirement_of_machine = obj.toString();
                break;
            }
        }
    }

    public String getDistID() {
        return DistID;
    }

    public void setDistID(String distID) {
        DistID = distID;
    }

    public String getBlockID() {
        return BlockID;
    }

    public void setBlockID(String blockID) {
        BlockID = blockID;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getRajswaThanaNumber() {
        return RajswaThanaNumber;
    }

    public void setRajswaThanaNumber(String rajswaThanaNumber) {
        RajswaThanaNumber = rajswaThanaNumber;
    }

    public String getVillageID() {
        return VillageID;
    }

    public void setVillageID(String villageID) {
        VillageID = villageID;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
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

    public String getKhata_Khesra_No() {
        return Khata_Khesra_No;
    }

    public void setKhata_Khesra_No(String khata_Khesra_No) {
        Khata_Khesra_No = khata_Khesra_No;
    }

    public String getPrivate_or_Public() {
        return Private_or_Public;
    }

    public void setPrivate_or_Public(String private_or_Public) {
        Private_or_Public = private_or_Public;
    }

    public String getOutside_Diamter() {
        return Outside_Diamter;
    }

    public void setOutside_Diamter(String outside_Diamter) {
        Outside_Diamter = outside_Diamter;
    }

    public String getRequirement_Of_Flyer() {
        return Requirement_Of_Flyer;
    }

    public void setRequirement_Of_Flyer(String requirement_Of_Flyer) {
        Requirement_Of_Flyer = requirement_Of_Flyer;
    }

    public String getStatus_of_Encroachment() {
        return Status_of_Encroachment;
    }

    public void setStatus_of_Encroachment(String status_of_Encroachment) {
        Status_of_Encroachment = status_of_Encroachment;
    }

    public String getTypes_of_Encroachment() {
        return Types_of_Encroachment;
    }

    public void setTypes_of_Encroachment(String types_of_Encroachment) {
        Types_of_Encroachment = types_of_Encroachment;
    }

    public String getRequirement_of_Renovation() {
        return Requirement_of_Renovation;
    }

    public void setRequirement_of_Renovation(String requirement_of_Renovation) {
        Requirement_of_Renovation = requirement_of_Renovation;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(String isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getRequirement_of_machine() {
        return Requirement_of_machine;
    }

    public void setRequirement_of_machine(String requirement_of_machine) {
        Requirement_of_machine = requirement_of_machine;
    }

    public String getName_of_undertaken() {
        return Name_of_undertaken;
    }

    public void setName_of_undertaken(String name_of_undertaken) {
        Name_of_undertaken = name_of_undertaken;
    }

    public String getLongitude_Mob() {
        return Longitude_Mob;
    }

    public void setLongitude_Mob(String longitude_Mob) {
        Longitude_Mob = longitude_Mob;
    }

    public String getLatitude_Mob() {
        return Latitude_Mob;
    }

    public void setLatitude_Mob(String latitude_Mob) {
        Latitude_Mob = latitude_Mob;
    }

    public String getVerified_Date() {
        return Verified_Date;
    }

    public void setVerified_Date(String verified_Date) {
        Verified_Date = verified_Date;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }
}
