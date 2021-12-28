package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PlantationDetail  implements KvmSerializable, Serializable {

    public static Class<PlantationDetail> PlantationDetail_CLASS = PlantationDetail.class;

    private int id;
    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;
    private String PanchayatID;
    private String PanchayatName;
    private String WorkStateFyear;
    private String WorkName;
    private String WorkCode;
    private String Worktype;
    private String AgencyName;
    private String SanctionAmtLabourCom;
    private String SanctionAmtMaterialCom;
    private String InspectionID;
    private String IsInspectedDate;
    private String IsInspectedBy;
    private String IsInspectedByDSTAE;
    private String IsInspectedByDSTEE;
    private String IsInspectedByDSTDRDA;
    private String IsInspectedByDSTDDC;

    private String Plantation_Site_Id;
    private String Plantation_Site_Name;
    private String Van_Posako_No;
    private String Posak_bhugtaanMonth;
    private String Posak_bhugtaanYear;
    private String gavyan_percentage;
    private String Average_height_Plant;

    private String Fyear;
    private String BhumiType;
    private String Ropit_PlantNo;
    private String Utarjibit_PlantNo;
    private String UtarjibitaPercent;
    private String Utarjibit_90PercentMore;
    private String Utarjibit_75_90Percent;
    private String Utarjibit_50_75Percent;
    private String Utarjibit_25PercentLess;
    private String Remarks;

    private String photo;
    private String Photo1;
    private String Latitude_Mob;
    private String Longitude_Mob;

    private String isUpdated;
    private String AppVersion;
    private String verifiedBy;
    private String verifiedDate;
    private String userRole;

    public PlantationDetail(SoapObject res1){
        this.DistID=res1.getProperty("DistID").toString();
        this.DistName=res1.getProperty("DistName").toString();
        this.BlockID=res1.getProperty("BlockID").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.PanchayatID=res1.getProperty("PanchayatID").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.WorkStateFyear=res1.getProperty("WorkStateFyear").toString();
        this.WorkName=res1.getProperty("WorkName").toString();
        this.WorkCode=res1.getProperty("WorkCode").toString();
        this.Worktype=res1.getProperty("Worktype").toString();
        this.AgencyName=res1.getProperty("AgencyName").toString();
        this.SanctionAmtLabourCom=res1.getProperty("SanctionAmtLabourCom").toString();
        this.SanctionAmtMaterialCom=res1.getProperty("SanctionAmtMaterialCom").toString();
        this.InspectionID=res1.getProperty("InspectionID").toString();
        this.IsInspectedDate=res1.getProperty("IsInspectedDate").toString();
        this.IsInspectedBy=res1.getProperty("IsInspectedBy").toString();
        this.IsInspectedByDSTAE=res1.getProperty("IsInspectedByDSTAE").toString();
        this.IsInspectedByDSTEE=res1.getProperty("IsInspectedByDSTEE").toString();
        this.IsInspectedByDSTDRDA=res1.getProperty("IsInspectedByDSTDRDA").toString();
        this.IsInspectedByDSTDDC=res1.getProperty("IsInspectedByDSTDDC").toString();
    }

    public PlantationDetail() {
    }

    public static Class<PlantationDetail> getPlantationDetail_CLASS() {
        return PlantationDetail_CLASS;
    }

    public static void setPlantationDetail_CLASS(Class<PlantationDetail> plantationDetail_CLASS) {
        PlantationDetail_CLASS = plantationDetail_CLASS;
    }

    public String getPlantation_Site_Name() {
        return Plantation_Site_Name;
    }

    public void setPlantation_Site_Name(String plantation_Site_Name) {
        Plantation_Site_Name = plantation_Site_Name;
    }

    public String getPlantation_Site_Id() {
        return Plantation_Site_Id;
    }

    public void setPlantation_Site_Id(String plantation_Site_Id) {
        Plantation_Site_Id = plantation_Site_Id;
    }

    public String getVan_Posako_No() {
        return Van_Posako_No;
    }

    public void setVan_Posako_No(String van_Posako_No) {
        Van_Posako_No = van_Posako_No;
    }

    public String getPosak_bhugtaanMonth() {
        return Posak_bhugtaanMonth;
    }

    public void setPosak_bhugtaanMonth(String posak_bhugtaanMonth) {
        Posak_bhugtaanMonth = posak_bhugtaanMonth;
    }

    public String getPosak_bhugtaanYear() {
        return Posak_bhugtaanYear;
    }

    public void setPosak_bhugtaanYear(String posak_bhugtaanYear) {
        Posak_bhugtaanYear = posak_bhugtaanYear;
    }

    public String getGavyan_percentage() {
        return gavyan_percentage;
    }

    public void setGavyan_percentage(String gavyan_percentage) {
        this.gavyan_percentage = gavyan_percentage;
    }

    public String getAverage_height_Plant() {
        return Average_height_Plant;
    }

    public void setAverage_height_Plant(String average_height_Plant) {
        Average_height_Plant = average_height_Plant;
    }

    public String getIsInspectedByDSTAE() {
        return IsInspectedByDSTAE;
    }

    public void setIsInspectedByDSTAE(String isInspectedByDSTAE) {
        IsInspectedByDSTAE = isInspectedByDSTAE;
    }

    public String getIsInspectedByDSTEE() {
        return IsInspectedByDSTEE;
    }

    public void setIsInspectedByDSTEE(String isInspectedByDSTEE) {
        IsInspectedByDSTEE = isInspectedByDSTEE;
    }

    public String getIsInspectedByDSTDRDA() {
        return IsInspectedByDSTDRDA;
    }

    public void setIsInspectedByDSTDRDA(String isInspectedByDSTDRDA) {
        IsInspectedByDSTDRDA = isInspectedByDSTDRDA;
    }

    public String getIsInspectedByDSTDDC() {
        return IsInspectedByDSTDDC;
    }

    public void setIsInspectedByDSTDDC(String isInspectedByDSTDDC) {
        IsInspectedByDSTDDC = isInspectedByDSTDDC;
    }

    public String getFyear() {
        return Fyear;
    }

    public void setFyear(String fyear) {
        Fyear = fyear;
    }

    public String getBhumiType() {
        return BhumiType;
    }

    public void setBhumiType(String bhumiType) {
        BhumiType = bhumiType;
    }

    public String getRopit_PlantNo() {
        return Ropit_PlantNo;
    }

    public void setRopit_PlantNo(String ropit_PlantNo) {
        Ropit_PlantNo = ropit_PlantNo;
    }

    public String getUtarjibit_PlantNo() {
        return Utarjibit_PlantNo;
    }

    public void setUtarjibit_PlantNo(String utarjibit_PlantNo) {
        Utarjibit_PlantNo = utarjibit_PlantNo;
    }

    public String getUtarjibitaPercent() {
        return UtarjibitaPercent;
    }

    public void setUtarjibitaPercent(String utarjibitaPercent) {
        UtarjibitaPercent = utarjibitaPercent;
    }

    public String getUtarjibit_90PercentMore() {
        return Utarjibit_90PercentMore;
    }

    public void setUtarjibit_90PercentMore(String utarjibit_90PercentMore) {
        Utarjibit_90PercentMore = utarjibit_90PercentMore;
    }

    public String getUtarjibit_75_90Percent() {
        return Utarjibit_75_90Percent;
    }

    public void setUtarjibit_75_90Percent(String utarjibit_75_90Percent) {
        Utarjibit_75_90Percent = utarjibit_75_90Percent;
    }

    public String getUtarjibit_50_75Percent() {
        return Utarjibit_50_75Percent;
    }

    public void setUtarjibit_50_75Percent(String utarjibit_50_75Percent) {
        Utarjibit_50_75Percent = utarjibit_50_75Percent;
    }

    public String getUtarjibit_25PercentLess() {
        return Utarjibit_25PercentLess;
    }

    public void setUtarjibit_25PercentLess(String utarjibit_25PercentLess) {
        Utarjibit_25PercentLess = utarjibit_25PercentLess;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto1() {
        return Photo1;
    }

    public void setPhoto1(String photo1) {
        Photo1 = photo1;
    }

    public String getLatitude_Mob() {
        return Latitude_Mob;
    }

    public void setLatitude_Mob(String latitude_Mob) {
        Latitude_Mob = latitude_Mob;
    }

    public String getLongitude_Mob() {
        return Longitude_Mob;
    }

    public void setLongitude_Mob(String longitude_Mob) {
        Longitude_Mob = longitude_Mob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistID() {
        return DistID;
    }

    public void setDistID(String distID) {
        DistID = distID;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
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

    public String getPanchayatID() {
        return PanchayatID;
    }

    public void setPanchayatID(String panchayatID) {
        PanchayatID = panchayatID;
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getWorkStateFyear() {
        return WorkStateFyear;
    }

    public void setWorkStateFyear(String workStateFyear) {
        WorkStateFyear = workStateFyear;
    }

    public String getWorkName() {
        return WorkName;
    }

    public void setWorkName(String workName) {
        WorkName = workName;
    }

    public String getWorkCode() {
        return WorkCode;
    }

    public void setWorkCode(String workCode) {
        WorkCode = workCode;
    }

    public String getWorktype() {
        return Worktype;
    }

    public void setWorktype(String worktype) {
        Worktype = worktype;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String agencyName) {
        AgencyName = agencyName;
    }

    public String getSanctionAmtLabourCom() {
        return SanctionAmtLabourCom;
    }

    public void setSanctionAmtLabourCom(String sanctionAmtLabourCom) {
        SanctionAmtLabourCom = sanctionAmtLabourCom;
    }

    public String getSanctionAmtMaterialCom() {
        return SanctionAmtMaterialCom;
    }

    public void setSanctionAmtMaterialCom(String sanctionAmtMaterialCom) {
        SanctionAmtMaterialCom = sanctionAmtMaterialCom;
    }

    public String getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(String inspectionID) {
        InspectionID = inspectionID;
    }

    public String getIsInspectedDate() {
        return IsInspectedDate;
    }

    public void setIsInspectedDate(String isInspectedDate) {
        IsInspectedDate = isInspectedDate;
    }

    public String getIsInspectedBy() {
        return IsInspectedBy;
    }

    public void setIsInspectedBy(String isInspectedBy) {
        IsInspectedBy = isInspectedBy;
    }

    public String getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(String isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
}
