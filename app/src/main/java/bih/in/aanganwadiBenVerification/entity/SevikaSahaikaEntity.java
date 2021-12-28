package bih.in.aanganwadiBenVerification.entity;

import java.io.Serializable;

/**
 * Created by nisci on 07-Sep-2017.
 */

public class SevikaSahaikaEntity implements Serializable {
    private String id;
    private String userId;
    private String districtCode;
    private String projectCode;
    private String sectorCode;
    private String aanganvariCode;
    private String sevikaPhoto;
    private String sahaikaPhoto;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public String getAanganvariCode() {
        return aanganvariCode;
    }

    public void setAanganvariCode(String aanganvariCode) {
        this.aanganvariCode = aanganvariCode;
    }

    public String getSevikaPhoto() {
        return sevikaPhoto;
    }

    public void setSevikaPhoto(String sevikaPhoto) {
        this.sevikaPhoto = sevikaPhoto;
    }

    public String getSahaikaPhoto() {
        return sahaikaPhoto;
    }

    public void setSahaikaPhoto(String sahaikaPhoto) {
        this.sahaikaPhoto = sahaikaPhoto;
    }
}
