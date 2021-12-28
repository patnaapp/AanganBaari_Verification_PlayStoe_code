package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class SectionalLevel implements KvmSerializable {

    public static Class<Efforts_Taken> EFFORTS_TAKEN_CLASS=Efforts_Taken.class;
    private String SectionalLevelId;
    private String SectionalLevelName;
    private String OrderID;

    public SectionalLevel(SoapObject sobj) {
        this.SectionalLevelId=sobj.getProperty("SanctionedLevelID").toString();
        this.SectionalLevelName=sobj.getProperty("SanctionedLevelName").toString();

        this.OrderID=sobj.getProperty("OrderById").toString();
    }

    public SectionalLevel() {

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

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getSectionalLevelId() {
        return SectionalLevelId;
    }

    public void setSectionalLevelId(String sectionalLevelId) {
        SectionalLevelId = sectionalLevelId;
    }

    public String getSectionalLevelName() {
        return SectionalLevelName;
    }

    public void setSectionalLevelName(String sectionalLevelName) {
        SectionalLevelName = sectionalLevelName;
    }
}
