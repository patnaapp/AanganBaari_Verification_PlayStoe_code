package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PondEncroachmentEntity implements KvmSerializable, Serializable {
    public static  Class<PondEncroachmentEntity> PondEncroachmentEntity_CLASS = PondEncroachmentEntity.class;

    private int id;
    private String InspectionID;
    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;
    private String PanchayatID;
    private String PanchayatName;
    private String RajswaThanaNumber;
    private String Khaata_Kheshara_Number;
    private String VillageID;
    private String VILLNAME;
    private String Latitude;
    private String Longitude;
    private String Status_Of_Encroachment;
    private String Type_Of_Encroachment;
    private String Verified_By;
    private String IsInspected;
    private String Verified_Date;

    private String Ench_Verified_By;
    private String EStatus;
    private String EnchrochmentStartDate;
    private String EnchrochmentEndDate;
    private String NoticeDate;
    private String NoticeNo;
    private String isUpdated;
    private String AppVersion;
    private String UploadType;

    public static Class<PondEncroachmentEntity> getPondEncroachmentEntity_CLASS() {
        return PondEncroachmentEntity_CLASS;
    }

    public static void setPondEncroachmentEntity_CLASS(Class<PondEncroachmentEntity> pondEncroachmentEntity_CLASS) {
        PondEncroachmentEntity_CLASS = pondEncroachmentEntity_CLASS;
    }

    public PondEncroachmentEntity(SoapObject res1) {

        this.InspectionID=res1.getProperty("InspectionID").toString();
        this.DistID=res1.getProperty("DistID").toString();
        this.DistName=res1.getProperty("DistName").toString();
        this.BlockID=res1.getProperty("BlockID").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.PanchayatID=res1.getProperty("PanchayatID").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.RajswaThanaNumber=res1.getProperty("RajswaThanaNumber").toString();
        this.VillageID=res1.getProperty("VillageID").toString();
        this.VILLNAME=res1.getProperty("VILLNAME").toString();
        this.Latitude=res1.getProperty("Latitude").toString();
        this.Longitude=res1.getProperty("Longitude").toString();
        this.Status_Of_Encroachment=res1.getProperty("Status_Of_Encroachment").toString();
        this.Type_Of_Encroachment=res1.getProperty("Type_Of_Encroachment").toString();
        this.Verified_By=res1.getProperty("Verified_By").toString();
        this.IsInspected=res1.getProperty("IsInspected").toString();
        this.Khaata_Kheshara_Number=res1.getProperty("Khaata_Kheshara_Number").toString();
        this.Ench_Verified_By=res1.getProperty("Ench_Verified_By").toString();
        this.EStatus=res1.getProperty("EStatus").toString();

    }

    public PondEncroachmentEntity(){

    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public String getUploadType() {
        return UploadType;
    }

    public void setUploadType(String uploadType) {
        UploadType = uploadType;
    }

    public String getEnch_Verified_By() {
        return Ench_Verified_By;
    }

    public void setEnch_Verified_By(String ench_Verified_By) {
        Ench_Verified_By = ench_Verified_By;
    }

    public String getEStatus() {
        return EStatus;
    }

    public void setEStatus(String EStatus) {
        this.EStatus = EStatus;
    }

    public String getEnchrochmentStartDate() {
        return EnchrochmentStartDate;
    }

    public void setEnchrochmentStartDate(String enchrochmentStartDate) {
        EnchrochmentStartDate = enchrochmentStartDate;
    }

    public String getEnchrochmentEndDate() {
        return EnchrochmentEndDate;
    }

    public void setEnchrochmentEndDate(String enchrochmentEndDate) {
        EnchrochmentEndDate = enchrochmentEndDate;
    }

    public String getNoticeDate() {
        return NoticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        NoticeDate = noticeDate;
    }

    public String getNoticeNo() {
        return NoticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        NoticeNo = noticeNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(String inspectionID) {
        InspectionID = inspectionID;
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

    public String getRajswaThanaNumber() {
        return RajswaThanaNumber;
    }

    public void setRajswaThanaNumber(String rajswaThanaNumber) {
        RajswaThanaNumber = rajswaThanaNumber;
    }

    public String getKhaata_Kheshara_Number() {
        return Khaata_Kheshara_Number;
    }

    public void setKhaata_Kheshara_Number(String khaata_Kheshara_Number) {
        Khaata_Kheshara_Number = khaata_Kheshara_Number;
    }

    public String getVillageID() {
        return VillageID;
    }

    public void setVillageID(String villageID) {
        VillageID = villageID;
    }

    public String getVILLNAME() {
        return VILLNAME;
    }

    public void setVILLNAME(String VILLNAME) {
        this.VILLNAME = VILLNAME;
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

    public String getStatus_Of_Encroachment() {
        return Status_Of_Encroachment;
    }

    public void setStatus_Of_Encroachment(String status_Of_Encroachment) {
        Status_Of_Encroachment = status_Of_Encroachment;
    }

    public String getType_Of_Encroachment() {
        return Type_Of_Encroachment;
    }

    public void setType_Of_Encroachment(String type_Of_Encroachment) {
        Type_Of_Encroachment = type_Of_Encroachment;
    }

    public String getVerified_By() {
        return Verified_By;
    }

    public void setVerified_By(String verified_By) {
        Verified_By = verified_By;
    }

    public String getIsInspected() {
        return IsInspected;
    }

    public void setIsInspected(String isInspected) {
        IsInspected = isInspected;
    }

    public String getVerified_Date() {
        return Verified_Date;
    }

    public void setVerified_Date(String verified_Date) {
        Verified_Date = verified_Date;
    }

    public String getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(String isUpdated) {
        this.isUpdated = isUpdated;
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
