package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class ward implements KvmSerializable {

    String wardCode="";
    String wardname="";
    String blockCode="";
    String panchayatCode="";
    String areaType="";

    public static Class<ward> ward_CLASS=ward.class;
    public ward(SoapObject sobj)
    {

        this.wardCode=sobj.getProperty("WARDCODE").toString();
        this.wardname=sobj.getProperty("WARDNAME").toString();
        this.panchayatCode=sobj.getProperty("PANCHAYATCODE").toString();
        this.areaType=sobj.getProperty("AreaType").toString();
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public static Class<ward> getWard_CLASS() {
        return ward_CLASS;
    }

    public static void setWard_CLASS(Class<ward> ward_CLASS) {
        ward.ward_CLASS = ward_CLASS;
    }

    public ward() {
        super();
    }
    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String _wardCode) {
        this.wardCode = _wardCode;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String _wardname) {
        this.wardname = _wardname;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getPanchayatCode() {
        return panchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        this.panchayatCode = panchayatCode;
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
