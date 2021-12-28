package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Delay_Reason implements KvmSerializable
{
    public static Class<Delay_Reason> Delay_Reason_CLASS = Delay_Reason.class;

    private String Reason_Id,Reason,Dept_ID,Inspection_id;

    public Delay_Reason(SoapObject sobj)
    {
        this.Reason_Id=sobj.getProperty("ReasonId").toString();
        this.Reason=sobj.getProperty("Reason").toString();
        this.Dept_ID=sobj.getProperty("DeptId").toString();
        this.Inspection_id=sobj.getProperty("InspTypeId").toString();


    }
    public Delay_Reason()
    {

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

    public String getReason_Id() {
        return Reason_Id;
    }

    public void setReason_Id(String reason_Id) {
        Reason_Id = reason_Id;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getDept_ID() {
        return Dept_ID;
    }

    public void setDept_ID(String dept_ID) {
        Dept_ID = dept_ID;
    }

    public String getInspection_id() {
        return Inspection_id;
    }

    public void setInspection_id(String inspection_id) {
        Inspection_id = inspection_id;
    }
}
