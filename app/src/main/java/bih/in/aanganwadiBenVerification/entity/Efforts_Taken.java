package bih.in.aanganwadiBenVerification.entity;



import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Efforts_Taken implements KvmSerializable {

    public static Class<Efforts_Taken> EFFORTS_TAKEN_CLASS=Efforts_Taken.class;

    private String EffortTakenId,EffortTaken,Dept_ID,Inspection_id;

    public Efforts_Taken(SoapObject sobj)
    {
        this.EffortTakenId=sobj.getProperty("EffortTakenId").toString();
        this.EffortTaken=sobj.getProperty("EffortTaken").toString();
        this.Dept_ID=sobj.getProperty("DeptId").toString();
        this.Inspection_id=sobj.getProperty("InspTypeId").toString();


    }
    public Efforts_Taken()
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

    public String getEffortTakenId() {
        return EffortTakenId;
    }

    public void setEffortTakenId(String effortTakenId) {
        EffortTakenId = effortTakenId;
    }

    public String getEffortTaken() {
        return EffortTaken;
    }

    public void setEffortTaken(String effortTaken) {
        EffortTaken = effortTaken;
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

