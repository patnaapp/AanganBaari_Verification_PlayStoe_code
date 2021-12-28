package bih.in.aanganwadiBenVerification.entity;

import java.io.Serializable;

public class inspectionDetail implements Serializable {


    public static Class<inspectionDetail> DataProgress_CLASS = inspectionDetail.class;

   private String DepId;
   private String insid;
   private String InspTypeId;
    private String BenId;
    private String BenName;
    private String BenfName;
    private String DistCode;
    private String BlockCode;
    private String PanchayatCode;
    private String WardId;
    private String SectionedLevelId;
    private String EffortTakenId;
    private String IsCompletionId;
    private String IsCompletionDate;
    private String ReasonId;
    private String DelayId;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String Latitude;
    private String Longitude;
    private String Appversion;
    private String Device;
    private String Uploaddate;
    private String UploadBy;
    private String Status;
    private String NextVisitDate;
    private String fYearId;
    private String SectionalLelId;
    private String SectionalLelName;
    private String VillageCode;
    private String SchemeName;
    private String SchemeID;
    private String landless;
    private String infoland;
    private String infolanddate;
    private String AgreeLand;
    private String AgreelandDate;
    private String datefundtrans;


    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getSchemeID() {
        return SchemeID;
    }

    public void setSchemeID(String schemeID) {
        SchemeID = schemeID;
    }

    public String getBenId() {
        return BenId;
    }

    public void setBenId(String benId) {
        BenId = benId;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getSectionedLevelId() {
        return SectionedLevelId;
    }

    public void setSectionedLevelId(String sectionedLevelId) {
        SectionedLevelId = sectionedLevelId;
    }

    public String getEffortTakenId() {
        return EffortTakenId;
    }

    public void setEffortTakenId(String effortTakenId) {
        EffortTakenId = effortTakenId;
    }

    public String getIsCompletionId() {
        return IsCompletionId;
    }

    public void setIsCompletionId(String isCompletionId) {
        IsCompletionId = isCompletionId;
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

    public String getAppversion() {
        return Appversion;
    }

    public void setAppversion(String appversion) {
        Appversion = appversion;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getUploaddate() {
        return Uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        Uploaddate = uploaddate;
    }

    public String getUploadBy() {
        return UploadBy;
    }

    public void setUploadBy(String uploadBy) {
        UploadBy = uploadBy;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getIsCompletionDate() {
        return IsCompletionDate;
    }

    public void setIsCompletionDate(String isCompletionDate) {
        IsCompletionDate = isCompletionDate;
    }

    public String getWardId() {
        return WardId;
    }

    public void setWardId(String wardId) {
        WardId = wardId;
    }

    public String getReasonId() {
        return ReasonId;
    }

    public void setReasonId(String reasonId) {
        ReasonId = reasonId;
    }

    public String getNextVisitDate() {
        return NextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        NextVisitDate = nextVisitDate;
    }

    public String getDelayId() {
        return DelayId;
    }

    public void setDelayId(String delayId) {
        DelayId = delayId;
    }

    public String getBenName() {
        return BenName;
    }

    public void setBenName(String benName) {
        BenName = benName;
    }

    public String getBenfName() {
        return BenfName;
    }

    public void setBenfName(String benfName) {
        BenfName = benfName;
    }

    public String getDepId() {
        return DepId;
    }

    public void setDepId(String depId) {
        DepId = depId;
    }

    public String getInspTypeId() {
        return InspTypeId;
    }

    public void setInspTypeId(String inspTypeId) {
        InspTypeId = inspTypeId;
    }

    public String getfYearId() {
        return fYearId;
    }

    public void setfYearId(String fYearId) {
        this.fYearId = fYearId;
    }

    public String getInsid() {
        return insid;
    }

    public void setInsid(String insid) {
        this.insid = insid;
    }

    public String getSectionalLelId() {
        return SectionalLelId;
    }

    public void setSectionalLelId(String sectionalLelId) {
        SectionalLelId = sectionalLelId;
    }

    public String getSectionalLelName() {
        return SectionalLelName;
    }

    public void setSectionalLelName(String sectionalLelName) {
        SectionalLelName = sectionalLelName;
    }

    public String getVillageCode() {
        return VillageCode;
    }

    public void setVillageCode(String villageCode) {
        VillageCode = villageCode;
    }

    public String getLandless() {
        return landless;
    }

    public void setLandless(String landless) {
        this.landless = landless;
    }

    public String getInfoland() {
        return infoland;
    }

    public void setInfoland(String infoland) {
        this.infoland = infoland;
    }

    public String getInfolanddate() {
        return infolanddate;
    }

    public void setInfolanddate(String infolanddate) {
        this.infolanddate = infolanddate;
    }

    public String getAgreeLand() {
        return AgreeLand;
    }

    public void setAgreeLand(String agreeLand) {
        AgreeLand = agreeLand;
    }

    public String getAgreelandDate() {
        return AgreelandDate;
    }

    public void setAgreelandDate(String agreelandDate) {
        AgreelandDate = agreelandDate;
    }

    public String getDatefundtrans() {
        return datefundtrans;
    }

    public void setDatefundtrans(String datefundtrans) {
        this.datefundtrans = datefundtrans;
    }
}
