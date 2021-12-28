package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PlantationReportEntity  implements KvmSerializable, Serializable {

    public static  Class<PlantationReportEntity> PlantationReportEntity_CLASS = PlantationReportEntity.class;

    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;
    private String PanchayatID;
    private String PanchayatName;
    private String BhumiType;
    private String Years;
    private String Remarks;
    private String Ropit_PlantNo;
    private String Utarjibit_PlantNo;
    private String UtarjibitaPercent;
    private String Utarjibit_90PercentMore;
    private String Utarjibit_75_90Percent;
    private String Utarjibit_50_75Percent;
    private String Utarjibit_25PercentLess;
    private String IsInspected;
    private String IsInspectedDate;
    private String IsInspectedBy;

    private String Userrole;
    private String Plantation_Site_Id;
    private String Van_Posako_No;
    private String Van_Posak_bhugtaan;
    private String gavyan_percentage;
    private String Average_height_Plant;
    private String Worktype;
    private String WorkName;
    private String WorkCode;
    private String AgencyName;
    private String SanctionAmtLabourCom;
    private String SanctionAmtMaterialCom;
    private String WorkStateFyear;

    public PlantationReportEntity(SoapObject res1) {
        this.DistID=res1.getProperty("DistID").toString();
        this.DistName=res1.getProperty("DistName").toString();
        this.BlockID=res1.getProperty("BlockID").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.PanchayatID=res1.getProperty("PanchayatID").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.BhumiType=res1.getProperty("BhumiType").toString();
        this.Years=res1.getProperty("Years").toString();
        this.Remarks=res1.getProperty("Remarks").toString();
        this.Ropit_PlantNo=res1.getProperty("Ropit_PlantNo").toString();
        this.Utarjibit_PlantNo=res1.getProperty("Utarjibit_PlantNo").toString();
        this.UtarjibitaPercent=res1.getProperty("UtarjibitaPercent").toString();
        this.Utarjibit_90PercentMore=res1.getProperty("Utarjibit_90PercentMore").toString();
        this.Utarjibit_75_90Percent=res1.getProperty("Utarjibit_75_90Percent").toString();
        this.Utarjibit_50_75Percent=res1.getProperty("Utarjibit_50_75Percent").toString();
        this.Utarjibit_25PercentLess=res1.getProperty("Utarjibit_25PercentLess").toString();
        this.IsInspected=res1.getProperty("IsInspected").toString();
        this.IsInspectedDate=res1.getProperty("IsInspectedDate").toString();
        this.IsInspectedBy=res1.getProperty("IsInspectedBy").toString();

        this.Userrole=res1.getProperty("Userrole").toString();
        this.Plantation_Site_Id=res1.getProperty("Plantation_Site_Id").toString();
        this.Van_Posako_No=res1.getProperty("Van_Posako_No").toString();
        this.Van_Posak_bhugtaan=res1.getProperty("Van_Posak_bhugtaan").toString();
        this.gavyan_percentage=res1.getProperty("gavyan_percentage").toString();
        this.Average_height_Plant=res1.getProperty("Average_height_Plant").toString();
        this.Worktype=res1.getProperty("Worktype").toString();
        this.WorkName=res1.getProperty("WorkName").toString();
        this.WorkCode=res1.getProperty("WorkCode").toString();
        this.AgencyName=res1.getProperty("AgencyName").toString();
        this.SanctionAmtLabourCom=res1.getProperty("SanctionAmtLabourCom").toString();
        this.SanctionAmtMaterialCom=res1.getProperty("SanctionAmtMaterialCom").toString();
        this.WorkStateFyear=res1.getProperty("WorkStateFyear").toString();
    }

    public PlantationReportEntity() {

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

    public static Class<PlantationReportEntity> getPlantationReportEntity_CLASS() {
        return PlantationReportEntity_CLASS;
    }

    public static void setPlantationReportEntity_CLASS(Class<PlantationReportEntity> plantationReportEntity_CLASS) {
        PlantationReportEntity_CLASS = plantationReportEntity_CLASS;
    }

    public String getUserrole() {
        return Userrole;
    }

    public void setUserrole(String userrole) {
        Userrole = userrole;
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

    public String getVan_Posak_bhugtaan() {
        return Van_Posak_bhugtaan;
    }

    public void setVan_Posak_bhugtaan(String van_Posak_bhugtaan) {
        Van_Posak_bhugtaan = van_Posak_bhugtaan;
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

    public String getWorktype() {
        return Worktype;
    }

    public void setWorktype(String worktype) {
        Worktype = worktype;
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

    public String getWorkStateFyear() {
        return WorkStateFyear;
    }

    public void setWorkStateFyear(String workStateFyear) {
        WorkStateFyear = workStateFyear;
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

    public String getBhumiType() {
        return BhumiType;
    }

    public void setBhumiType(String bhumiType) {
        BhumiType = bhumiType;
    }

    public String getYears() {
        return Years;
    }

    public void setYears(String years) {
        Years = years;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
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

    public String getIsInspected() {
        return IsInspected;
    }

    public void setIsInspected(String isInspected) {
        IsInspected = isInspected;
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
}
