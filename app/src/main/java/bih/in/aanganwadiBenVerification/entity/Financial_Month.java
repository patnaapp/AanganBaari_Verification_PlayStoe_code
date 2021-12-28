package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class Financial_Month implements KvmSerializable, Serializable {

    public static Class<Financial_Month> Financial_Month_CLASS = Financial_Month.class;

    private String MonthID, MonthName,Year;

    public Financial_Month(SoapObject sobj) {
        this.MonthID = sobj.getProperty("MonthID").toString();
        this.MonthName = sobj.getProperty("MonthName").toString();
        this.Year = sobj.getProperty("Year").toString();

    }

    public Financial_Month() {

    }

    @Override
    public Object getProperty(int index) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }

    public String getMonthID() {
        return MonthID;
    }

    public void setMonthID(String monthID) {
        MonthID = monthID;
    }

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String monthName) {
        MonthName = monthName;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
}
