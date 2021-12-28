package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Sub_abyb implements KvmSerializable {

    String Sub_abyb_Code="";
    String Sub_abyb_name="";
    String Abyb_Code="";
    public Sub_abyb() {

    }
    public static Class<Sub_abyb> kriwayan_CLASS=Sub_abyb.class;
    public Sub_abyb(SoapObject sobj)
    {

        this.Sub_abyb_Code=sobj.getProperty("Abyab_Code").toString();
        this.Sub_abyb_name=sobj.getProperty("Abyab_name").toString();
        this.Abyb_Code=sobj.getProperty("Abyab_name").toString();
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

    public String getSub_abyb_Code() {
        return Sub_abyb_Code;
    }

    public void setSub_abyb_Code(String sub_abyb_Code) {
        Sub_abyb_Code = sub_abyb_Code;
    }

    public String getSub_abyb_name() {
        return Sub_abyb_name;
    }

    public void setSub_abyb_name(String sub_abyb_name) {
        Sub_abyb_name = sub_abyb_name;
    }

    public String getAbyb_Code() {
        return Abyb_Code;
    }

    public void setAbyb_Code(String abyb_Code) {
        Abyb_Code = abyb_Code;
    }
}
