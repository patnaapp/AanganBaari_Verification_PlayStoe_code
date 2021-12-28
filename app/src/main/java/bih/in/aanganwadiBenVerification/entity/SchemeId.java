package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class SchemeId implements KvmSerializable {

    public static Class<SchemeId> EFFORTS_TAKEN_CLASS=SchemeId.class;
    private String SchemeId;
    private String SchemeName;
    private String SchemeYearName;
    private String SchemeYearId;

    public SchemeId(SoapObject sobj) {
        this.SchemeId=sobj.getProperty("SchemeId").toString();
        this.SchemeName=sobj.getProperty("SchemeName").toString();
        this.SchemeYearId=sobj.getProperty("FYearID").toString();
        this.SchemeYearName=sobj.getProperty("FinYear").toString();

    }

    public SchemeId() {

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

    public String getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(String schemeId) {
        SchemeId = schemeId;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getSchemeYearName() {
        return SchemeYearName;
    }

    public void setSchemeYearName(String schemeYearName) {
        SchemeYearName = schemeYearName;
    }


    public String getSchemeYearId() {
        return SchemeYearId;
    }

    public void setSchemeYearId(String schemeYearId) {
        SchemeYearId = schemeYearId;
    }
}
