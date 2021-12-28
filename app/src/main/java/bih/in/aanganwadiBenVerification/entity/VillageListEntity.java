package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class VillageListEntity implements KvmSerializable, Serializable {

    public static Class<VillageListEntity> VillageList_CLASS = VillageListEntity.class;
    private int id;

    private String PanchayatCode;
    private String VillCode;
    private String villName;
    private String BlockCode;

    public VillageListEntity(SoapObject res1) {

        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.PanchayatCode=res1.getProperty("PanchayatCode").toString();
        this.VillCode=res1.getProperty("VILLCODE").toString();
        this.villName=res1.getProperty("VILLNAME").toString();
    }

//    public static Class<VillageListEntity> getVillageList_CLASS() {
//        return VillageList_CLASS;
//    }
//
//    public static void setVillageList_CLASS(Class<VillageListEntity> villageList_CLASS) {
//        VillageList_CLASS = villageList_CLASS;
//    }


    public VillageListEntity() {
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getVillCode() {
        return VillCode;
    }

    public void setVillCode(String villCode) {
        VillCode = villCode;
    }

    public String getVillName() {
        return villName;
    }

    public void setVillName(String villName) {
        this.villName = villName;
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
