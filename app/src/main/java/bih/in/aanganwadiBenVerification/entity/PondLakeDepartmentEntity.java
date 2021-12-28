package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PondLakeDepartmentEntity implements KvmSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    public static Class<PondLakeDepartmentEntity> PondLakeDeprt_CLASS = PondLakeDepartmentEntity.class;

    private int id;
    private String _DepatmentName;
    private String _DepatmentHNd;

    public PondLakeDepartmentEntity(SoapObject res1) {


        this._DepatmentName=res1.getProperty("_DepatmentName").toString();
        this._DepatmentHNd=res1.getProperty("_DepatmentHNd").toString();

    }

    public PondLakeDepartmentEntity() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Class<PondLakeDepartmentEntity> getPondLakeDeprt_CLASS() {
        return PondLakeDeprt_CLASS;
    }

    public static void setPondLakeDeprt_CLASS(Class<PondLakeDepartmentEntity> pondLakeDeprt_CLASS) {
        PondLakeDeprt_CLASS = pondLakeDeprt_CLASS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_DepatmentName() {
        return _DepatmentName;
    }

    public void set_DepatmentName(String _DepatmentName) {
        this._DepatmentName = _DepatmentName;
    }

    public String get_DepatmentHNd() {
        return _DepatmentHNd;
    }

    public void set_DepatmentHNd(String _DepatmentHNd) {
        this._DepatmentHNd = _DepatmentHNd;
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
