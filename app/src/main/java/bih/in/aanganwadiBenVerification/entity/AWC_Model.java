package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class AWC_Model implements KvmSerializable {

    private String AWC_CODE = "";
    private String AWC_NAME = "";
    private String AWCGOICode = "";
    private String AWC_PANCHAYATCODE = "";
    private String SectorId;

    public static Class<AWC_Model> AWC_CLASS = AWC_Model.class;

    public AWC_Model(SoapObject final_object) {
        this.AWC_CODE = final_object.getProperty("AWC_CODE").toString();
        this.AWC_NAME = final_object.getProperty("AWC_NAME").toString();
        this.AWCGOICode = final_object.getProperty("AWCGOICode").toString();
        this.AWC_PANCHAYATCODE = final_object.getProperty("AWC_PANCHAYATCODE").toString();
    }

    public AWC_Model() {

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

    public String getAWC_CODE() {
        return AWC_CODE;
    }

    public void setAWC_CODE(String AWC_CODE) {
        this.AWC_CODE = AWC_CODE;
    }

    public String getAWC_NAME() {
        return AWC_NAME;
    }

    public void setAWC_NAME(String AWC_NAME) {
        this.AWC_NAME = AWC_NAME;
    }

    public String getAWCGOICode() {
        return AWCGOICode;
    }

    public void setAWCGOICode(String AWCGOICode) {
        this.AWCGOICode = AWCGOICode;
    }

    public String getAWC_PANCHAYATCODE() {
        return AWC_PANCHAYATCODE;
    }

    public void setAWC_PANCHAYATCODE(String AWC_PANCHAYATCODE) {
        this.AWC_PANCHAYATCODE = AWC_PANCHAYATCODE;
    }

    public String getSectorId() {
        return SectorId;
    }

    public void setSectorId(String sectorId) {
        SectorId = sectorId;
    }
}
