package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PondWellReportEntity implements KvmSerializable, Serializable {

    public static Class<PondWellReportEntity> PondWellReportCLASS = PondWellReportEntity.class;

    private String DistCode;
    private String DistName;
    private String BlockCode;
    private String BlockName;
    private String PanchayatCode;
    private String PanchayatName;
    private String VILLCODE;
    private String VILLNAME;
    private String RajswaThanaNumber;
    private String id;
    private String Latitude;
    private String Longitude;
    private String Verified_By;

    public PondWellReportEntity(SoapObject res1) {


        this.DistCode=res1.getProperty("DistCode").toString();
        this.DistName=res1.getProperty("DistName").toString();
        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.PanchayatCode=res1.getProperty("PanchayatCode").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.VILLCODE=res1.getProperty("VILLCODE").toString();
        this.VILLNAME=res1.getProperty("VILLNAME").toString();
        this.RajswaThanaNumber=res1.getProperty("RajswaThanaNumber").toString();
        this.id=res1.getProperty("id").toString();
        this.Latitude=res1.getProperty("Latitude").toString();
        this.Longitude=res1.getProperty("Longitude").toString();
        this.Verified_By=res1.getProperty("Verified_By").toString();

    }

    public static Class<PondWellReportEntity> getPondWellReportCLASS() {
        return PondWellReportCLASS;
    }

    public static void setPondWellReportCLASS(Class<PondWellReportEntity> pondWellReportCLASS) {
        PondWellReportCLASS = pondWellReportCLASS;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
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

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getVILLCODE() {
        return VILLCODE;
    }

    public void setVILLCODE(String VILLCODE) {
        this.VILLCODE = VILLCODE;
    }

    public String getVILLNAME() {
        return VILLNAME;
    }

    public void setVILLNAME(String VILLNAME) {
        this.VILLNAME = VILLNAME;
    }

    public String getRajswaThanaNumber() {
        return RajswaThanaNumber;
    }

    public void setRajswaThanaNumber(String rajswaThanaNumber) {
        RajswaThanaNumber = rajswaThanaNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getVerified_By() {
        return Verified_By;
    }

    public void setVerified_By(String verified_By) {
        Verified_By = verified_By;
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
