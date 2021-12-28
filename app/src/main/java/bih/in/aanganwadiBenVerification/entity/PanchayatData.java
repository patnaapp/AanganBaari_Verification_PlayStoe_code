package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class PanchayatData {


    String Pcode="";
    String Pname="";
    String Dcode="";
    String Bcode="";
    String AreaType="";

    public static Class<PanchayatData> PanchayatData_CLASS=PanchayatData.class;


    public PanchayatData(SoapObject sobj)
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

    public PanchayatData() {
        super();
    }
    public String getPcode() {
        return Pcode;
    }

    public void setPcode(String _pcode) {
        Pcode = _pcode;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String _pname) {
        Pname = _pname;
    }

    public String getDcode() {
        return Dcode;
    }

    public void setDcode(String dcode) {
        Dcode = dcode;
    }

    public String getBcode() {
        return Bcode;
    }

    public void setBcode(String bcode) {
        Bcode = bcode;
    }
}
