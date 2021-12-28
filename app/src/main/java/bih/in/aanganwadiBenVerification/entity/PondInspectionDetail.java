package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Date;
import java.util.Hashtable;

public class PondInspectionDetail implements KvmSerializable {

    private static Class<PondInspectionDetail> POND_CLASS = PondInspectionDetail.class;

    private int id = 0;
    private String InspectionID = "";
    private String blockCode = "";
    private String blockName = "";
    private String rajswaThanaNo = "";
    private String village = "";
    private String villageName = "";
    private String latitude = "";
    private String longitude = "";
    private String panchayatCode = "";
    private String panchayatName = "";
    private String khataKhesraNo = "";
    private String privateOrPublic = "";
    private String areaByGovtRecord = "";
    private String connectedWithPine = "";
    private String availiablityOfWater = "";
    private String statusOfEncroachment = "";
    private String typesOfEncroachment = "";
    private String requirementOfRenovation = "";
    private String recommendedExecutionDept = "";
    private String requirementOfMachine = "";
    private String remarks = "";
    private String isUpdated = "";
    private String ownerName = "";
    private String DistName = "";
    private String DistrictCode = "";
    private String Longitude_Mob;
    private String Latitude_Mob;
    private String Verified_Date;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String PondAvblValue = "";
    private String PondCatValue = "";
    private String pondCatName;
    private String pondOwnerOtherDeptName;

    public String getPondCatName() {
        return pondCatName;
    }

    public void setPondCatName(String pondCatName) {
        this.pondCatName = pondCatName;
    }

    public String getPondOwnerOtherDeptName() {
        return pondOwnerOtherDeptName;
    }

    public void setPondOwnerOtherDeptName(String pondOwnerOtherDeptName) {
        this.pondOwnerOtherDeptName = pondOwnerOtherDeptName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getPondAvblValue() {
        return PondAvblValue;
    }

    public void setPondAvblValue(String pondAvblValue) {
        PondAvblValue = pondAvblValue;
    }

    public String getPondCatValue() {
        return PondCatValue;
    }

    public void setPondCatValue(String pondCatValue) {
        PondCatValue = pondCatValue;
    }

    public PondInspectionDetail(){

    }

    public static void setPondClass(Class<PondInspectionDetail> pondClass) {
        POND_CLASS = pondClass;
    }

    public static Class<PondInspectionDetail> getPondClass() {
        return POND_CLASS;
    }

    public PondInspectionDetail(SoapObject obj) {
        this.setId(Integer.parseInt(obj.getProperty("id").toString()));
        this.setBlockCode(obj.getProperty("Block_Code").toString());
        this.setBlockName(obj.getProperty("Block_Name").toString());
        this.setRajswaThanaNo(obj.getProperty("Rajswa_Thana_No").toString());
        this.setVillage(obj.getProperty("Village").toString());
        this.setLatitude(obj.getProperty("Latitude").toString());
        this.setLongitude(obj.getProperty("Longitude").toString());
        this.setPanchayatCode(obj.getProperty("Panchayat_Code").toString());
        this.setPanchayatName(obj.getProperty("Panchayat_Name").toString());
        this.setKhataKhesraNo(obj.getProperty("Khata_Khesra_No").toString());
        this.setPrivateOrPublic(obj.getProperty("Private_or_Public").toString());
        this.setAreaByGovtRecord(obj.getProperty("Area_by_Govt_Record").toString());
        this.setConnectedWithPine(obj.getProperty("Connected_With_Pine").toString());
        this.setAvailiablityOfWater(obj.getProperty("Availiablity_Of_Water").toString());
        this.setStatusOfEncroachment(obj.getProperty("Status_of_Encroachment").toString());
        this.setTypesOfEncroachment(obj.getProperty("Types_of_Encroachment").toString());
        this.setRequirementOfRenovation(obj.getProperty("Requirement_of_Renovation").toString());
        this.setRecommendedExecutionDept(obj.getProperty("Recommended_Execution_Dept").toString());
        this.setRemarks(obj.getProperty("Remarks").toString());
        this.setIsUpdated(obj.getProperty("isUpdated").toString());
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
                object = this.id;
                break;
            }
            case 1: {
                object = this.blockCode;
                break;
            }
            case 2: {
                object = this.blockName;
                break;
            }
            case 3: {
                object = this.rajswaThanaNo;
                break;
            }
            case 4: {
                object = this.village;
                break;
            }

            case 5: {
                object = this.latitude;
                break;
            }

            case 6: {
                object = this.longitude;
                break;

            }
            case 7: {
                object = this.panchayatCode;
                break;
            }
            case 8: {
                object = this.panchayatName;
                break;
            }
            case 9: {
                object = this.khataKhesraNo;
                break;
            }
            case 10: {
                object = this.privateOrPublic;
                break;
            }

            case 11: {
                object = this.areaByGovtRecord;
                break;
            }

            case 12: {
                object = this.connectedWithPine;
                break;

            }
            case 13: {
                object = this.availiablityOfWater;
                break;

            }

            case 14: {
                object = this.statusOfEncroachment;
                break;

            }
            case 15: {
                object = this.typesOfEncroachment;
                break;
            }
            case 16: {
                object = this.requirementOfRenovation;
                break;
            }
            case 17: {
                object = this.recommendedExecutionDept;
                break;
            }
            case 18: {
                object = this.remarks;
                break;
            }
            case 19: {
                object = this.isUpdated;
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
                propertyInfo.name = "id";
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                break;
            }
            case 1: {
                propertyInfo.name = "Block_Code";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 2: {
                propertyInfo.name = "Block_Name";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 3: {
                propertyInfo.name = "Rajswa_Thana_No";
                propertyInfo.type = Integer.class;
                break;
            }
            case 4: {
                propertyInfo.name = "Village";
                propertyInfo.type = Date.class;
                break;
            }

            case 5: {
                propertyInfo.name = "Latitude";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 6: {
                propertyInfo.name = "Longitude";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 7: {
                propertyInfo.name = "Panchayat_Code";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 8: {
                propertyInfo.name = "Panchayat_Name";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 9: {
                propertyInfo.name = "Khata_Khesra_No";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 10: {
                propertyInfo.name = "Private_or_Public";
                propertyInfo.type = Integer.class;
                break;
            }
            case 11: {
                propertyInfo.name = "Area_by_Govt_Record";
                propertyInfo.type = Date.class;
                break;
            }

            case 12: {
                propertyInfo.name = "Connected_With_Pine";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 13: {
                propertyInfo.name = "Availiablity_Of_Water";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 14: {
                propertyInfo.name = "Status_of_Encroachment";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 15: {
                propertyInfo.name = "Types_of_Encroachment";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 16: {
                propertyInfo.name = "Requirement_of_Renovation";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 17: {
                propertyInfo.name = "Recommended_Execution_Dept";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 18: {
                propertyInfo.name = "Remarks";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 19: {
                propertyInfo.name = "isUpdated";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
        }
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.id = Integer.parseInt(obj.toString());
                break;
            }
            case 1: {
                this.blockCode = obj.toString();
                break;
            }
            case 2: {
                this.blockName = obj.toString();
                break;
            }
            case 3: {
                this.rajswaThanaNo = obj.toString();
                break;
            }
            case 4: {
                this.village = obj.toString();
                break;
            }

            case 5: {
                this.latitude = obj.toString();
                break;
            }

            case 6: {
                this.longitude = obj.toString();
                break;
            }
            case 7: {
                this.panchayatCode = obj.toString();
                break;
            }
            case 8: {
                this.panchayatName = obj.toString();
                break;
            }
            case 9: {
                this.khataKhesraNo = obj.toString();
                break;
            }
            case 10: {
                privateOrPublic = obj.toString();
                break;
            }
            case 11: {
                this.areaByGovtRecord = obj.toString();
                break;
            }

            case 12: {
                this.connectedWithPine = obj.toString();
                break;
            }

            case 13: {
                this.availiablityOfWater = obj.toString();
                break;
            }

            case 14: {
                this.statusOfEncroachment = obj.toString();
                break;
            }

            case 15: {
                this.typesOfEncroachment = obj.toString();
                break;
            }
            case 16: {
                this.requirementOfRenovation = obj.toString();
                break;
            }
            case 17: {
                this.recommendedExecutionDept = obj.toString();
                break;
            }
            case 18: {
                this.remarks = obj.toString();
                break;
            }
            case 19: {
                this.isUpdated = obj.toString();
                break;
            }
        }
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

    public PondInspectionDetail(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getRajswaThanaNo() {
        return rajswaThanaNo;
    }

    public void setRajswaThanaNo(String rajswaThanaNo) {
        this.rajswaThanaNo = rajswaThanaNo;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPanchayatCode() {
        return panchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        this.panchayatCode = panchayatCode;
    }

    public String getPanchayatName() {
        return panchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        this.panchayatName = panchayatName;
    }

    public String getKhataKhesraNo() {
        return khataKhesraNo;
    }

    public void setKhataKhesraNo(String khataKhesraNo) {
        this.khataKhesraNo = khataKhesraNo;
    }

    public String getPrivateOrPublic() {
        return privateOrPublic;
    }

    public void setPrivateOrPublic(String privateOrPublic) {
        this.privateOrPublic = privateOrPublic;
    }

    public String getAreaByGovtRecord() {
        return areaByGovtRecord;
    }

    public void setAreaByGovtRecord(String areaByGovtRecord) {
        this.areaByGovtRecord = areaByGovtRecord;
    }

    public String getConnectedWithPine() {
        return connectedWithPine;
    }

    public void setConnectedWithPine(String connectedWithPine) {
        this.connectedWithPine = connectedWithPine;
    }

    public String getAvailiablityOfWater() {
        return availiablityOfWater;
    }

    public void setAvailiablityOfWater(String availiablityOfWater) {
        this.availiablityOfWater = availiablityOfWater;
    }

    public String getStatusOfEncroachment() {
        return statusOfEncroachment;
    }

    public void setStatusOfEncroachment(String statusOfEncroachment) {
        this.statusOfEncroachment = statusOfEncroachment;
    }

    public String getTypesOfEncroachment() {
        return typesOfEncroachment;
    }

    public void setTypesOfEncroachment(String typesOfEncroachment) {
        this.typesOfEncroachment = typesOfEncroachment;
    }

    public String getRequirementOfRenovation() {
        return requirementOfRenovation;
    }

    public void setRequirementOfRenovation(String requirementOfRenovation) {
        this.requirementOfRenovation = requirementOfRenovation;
    }

    public String getRecommendedExecutionDept() {
        return recommendedExecutionDept;
    }

    public void setRecommendedExecutionDept(String recommendedExecutionDept) {
        this.recommendedExecutionDept = recommendedExecutionDept;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(String isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getRequirementOfMachine() {
        return requirementOfMachine;
    }

    public void setRequirementOfMachine(String requirementOfMachine) {
        this.requirementOfMachine = requirementOfMachine;
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

    public String getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(String inspectionID) {
        InspectionID = inspectionID;
    }
}
