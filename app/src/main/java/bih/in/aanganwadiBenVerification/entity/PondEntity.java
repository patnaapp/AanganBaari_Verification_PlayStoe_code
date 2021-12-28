package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PondEntity implements KvmSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    public static Class<PondEntity> PondInfo_CLASS = PondEntity.class;




    String slno;

    String InspectionID;
    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;
    private String RajswaThanaNumber;
    private String VillageID;
    private String VillageName;
    private String Latitude;
    private String Longitude;
    private String PanchayatID;
    private String PanchayatName;
    private String KhataKhesraNo;

    private int id;

    public static Class<PondEntity> getPondInfo_CLASS() {
        return PondInfo_CLASS;
    }

    public static void setPondInfo_CLASS(Class<PondEntity> pondInfo_CLASS) {
        PondInfo_CLASS = pondInfo_CLASS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PondEntity(SoapObject res1) {


        this.InspectionID=res1.getProperty("InspectionID").toString();
        this.DistID=res1.getProperty("DistID").toString();
        this.DistName=res1.getProperty("DistName").toString();
        this.BlockID=res1.getProperty("BlockID").toString();
        this.RajswaThanaNumber=res1.getProperty("RajswaThanaNumber").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.VillageID=res1.getProperty("VillageID").toString();
        this.VillageName=res1.getProperty("VILLNAME").toString();
        this.Latitude=res1.getProperty("Latitude").toString();
        this.Longitude=res1.getProperty("Longitude").toString();
        this.PanchayatID=res1.getProperty("PanchayatID").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.KhataKhesraNo=res1.getProperty("Khaata_Kheshara_Number").toString();

    }

    public PondEntity() {
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

    public String getKhataKhesraNo() {
        return KhataKhesraNo;
    }

    public void setKhataKhesraNo(String khataKhesraNo) {
        KhataKhesraNo = khataKhesraNo;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
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

    public String getBlockID() {
        return BlockID;
    }

    public void setBlockID(String blockID) {
        BlockID = blockID;
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

    public String getBlockName() {
        return BlockName;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
    }
}
