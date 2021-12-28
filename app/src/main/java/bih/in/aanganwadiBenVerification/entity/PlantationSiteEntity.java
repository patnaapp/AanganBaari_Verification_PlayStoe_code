package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PlantationSiteEntity implements KvmSerializable, Serializable {

    public static Class<PlantationSiteEntity> PlantationSiteEntity_CLASS = PlantationSiteEntity.class;

    private String Id;
    private String Site_Name;

    public PlantationSiteEntity() {
    }

    public PlantationSiteEntity(SoapObject res1) {
        this.Id=res1.getProperty("Id").toString();
        this.Site_Name=res1.getProperty("Site_Name").toString();
    }

    public static Class<PlantationSiteEntity> getPlantationSiteEntity_CLASS() {
        return PlantationSiteEntity_CLASS;
    }

    public static void setPlantationSiteEntity_CLASS(Class<PlantationSiteEntity> plantationSiteEntity_CLASS) {
        PlantationSiteEntity_CLASS = plantationSiteEntity_CLASS;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSite_Name() {
        return Site_Name;
    }

    public void setSite_Name(String site_Name) {
        Site_Name = site_Name;
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
