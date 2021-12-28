package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by nisci on 28-Jun-2017.
 */

public class SectorWeb {
    private String code;
    private String value;
    private String DistCode;
    private String BlockCode;
    private String PanchayatCode;
    private String UserId;



    public static Class<SectorWeb> SectorWeb_CLASS= SectorWeb.class;

    public SectorWeb(SoapObject sobj)
    {
        this.DistCode=sobj.getProperty("DistCode").toString();
        this.BlockCode=sobj.getProperty("BlockCode").toString();
//        this.value=sobj.getProperty(2).toString();
//        this.code=sobj.getProperty(3).toString();

        //Changed BY Shekhar
        this.value=sobj.getProperty("PanchayatName").toString();
        this.code=sobj.getProperty("PanchayatCode").toString();


    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }
}