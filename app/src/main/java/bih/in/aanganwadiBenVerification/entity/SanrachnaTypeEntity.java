package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class SanrachnaTypeEntity implements KvmSerializable, Serializable {

    public static Class<SanrachnaTypeEntity> SanrachnaType_CLASS = SanrachnaTypeEntity.class;

    private String sancrachnaId;
    private String sancrachnaName;
    private String sub_Execution_DeptID;

    public SanrachnaTypeEntity(SoapObject res1) {

        this.sancrachnaId=res1.getProperty("Types_OfSarchnaId").toString();
        this.sancrachnaName=res1.getProperty("Types_OfSarchnaName").toString();
        this.sub_Execution_DeptID=res1.getProperty("Sub_Execution_DeptID").toString();
    }


    public String getSub_Execution_DeptID() {
        return sub_Execution_DeptID;
    }

    public void setSub_Execution_DeptID(String sub_Execution_DeptID) {
        this.sub_Execution_DeptID = sub_Execution_DeptID;
    }

    public SanrachnaTypeEntity() {
    }

    public static Class<SanrachnaTypeEntity> getSanrachnaType_CLASS() {
        return SanrachnaType_CLASS;
    }

    public static void setSanrachnaType_CLASS(Class<SanrachnaTypeEntity> sanrachnaType_CLASS) {
        SanrachnaType_CLASS = sanrachnaType_CLASS;
    }

    public String getSancrachnaId() {
        return sancrachnaId;
    }

    public void setSancrachnaId(String sancrachnaId) {
        this.sancrachnaId = sancrachnaId;
    }

    public String getSancrachnaName() {
        return sancrachnaName;
    }

    public void setSancrachnaName(String sancrachnaName) {
        this.sancrachnaName = sancrachnaName;
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
