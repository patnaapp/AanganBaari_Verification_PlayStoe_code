package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PanchayatEntity implements KvmSerializable, Serializable {

    String Pcode="";
    String Pname="";
    String AreaType="";

    public static Class<PanchayatEntity> PanchayatEntity_CLASS=PanchayatEntity.class;

    public PanchayatEntity(SoapObject sobj)
    {

        this.Pcode=sobj.getProperty("Panchayat_Code").toString();
        this.Pname=sobj.getProperty("Panchayat").toString();
        this.AreaType=sobj.getProperty("AreaType").toString();
    }

    public String getAreaType() {
        return AreaType;
    }

    public void setAreaType(String areaType) {
        AreaType = areaType;
    }

    public String getPcode() {
        return Pcode;
    }

    public void setPcode(String pcode) {
        Pcode = pcode;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public static Class<PanchayatEntity> getPanchayatEntity_CLASS() {
        return PanchayatEntity_CLASS;
    }

    public static void setPanchayatEntity_CLASS(Class<PanchayatEntity> panchayatEntity_CLASS) {
        PanchayatEntity_CLASS = panchayatEntity_CLASS;
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
