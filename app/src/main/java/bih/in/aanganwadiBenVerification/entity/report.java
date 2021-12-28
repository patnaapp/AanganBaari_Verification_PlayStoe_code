package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.ArrayList;

public class report extends ArrayList<report> implements Serializable {

    private String beneficiary_id;
    private String beneficiery_name;
    private String beneficiery_f_name;
    private String NextInspDate;
    private String IsComplition;
    private String IsComplitionDate;
    private String SanctionNo;
    private String EffortTaken;
    private String Reason;
    private String UserName;
    private String SanctionedLevelName;
    private String SanctionedLevelID;
    private String InspectinId;

    public report(SoapObject final_object) {
        this.beneficiary_id=final_object.getProperty("BenId").toString();
        this.beneficiery_name=final_object.getProperty("BenName").toString();
        this.beneficiery_f_name=final_object.getProperty("FHName").toString();
        this.NextInspDate=final_object.getProperty("NextInspDate").toString();
        this.IsComplition=final_object.getProperty("IsComplition").toString();
        this.IsComplitionDate=final_object.getProperty("IsComplitionDate").toString();
        this.SanctionNo=final_object.getProperty("SanctionNo").toString();
        this.EffortTaken=final_object.getProperty("EffortTaken").toString();
        this.UserName=final_object.getProperty("UserName").toString();
        this.SanctionedLevelName=final_object.getProperty("SanctionedLevelName").toString();
        this.SanctionedLevelID=final_object.getProperty("SanctionedLevelID").toString();
        this.Reason=final_object.getProperty("Reason").toString();
    }

    public report()
    {

    }

    public report(SoapObject final_object, String s) {
        this.beneficiary_id=final_object.getProperty("DistCode").toString();
        this.InspectinId=final_object.getProperty("BlockCode").toString();
        this.UserName=final_object.getProperty("UserName").toString();

    }

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(String beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiery_name() {
        return beneficiery_name;
    }

    public void setBeneficiery_name(String beneficiery_name) {
        this.beneficiery_name = beneficiery_name;
    }

    public String getBeneficiery_f_name() {
        return beneficiery_f_name;
    }

    public void setBeneficiery_f_name(String beneficiery_f_name) {
        this.beneficiery_f_name = beneficiery_f_name;
    }

    public String getNextInspDate() {
        return NextInspDate;
    }

    public void setNextInspDate(String nextInspDate) {
        NextInspDate = nextInspDate;
    }

    public String getIsComplition() {
        return IsComplition;
    }

    public void setIsComplition(String isComplition) {
        IsComplition = isComplition;
    }

    public String getIsComplitionDate() {
        return IsComplitionDate;
    }

    public void setIsComplitionDate(String isComplitionDate) {
        IsComplitionDate = isComplitionDate;
    }

    public String getSanctionNo() {
        return SanctionNo;
    }

    public void setSanctionNo(String sanctionNo) {
        SanctionNo = sanctionNo;
    }

    public String getEffortTaken() {
        return EffortTaken;
    }

    public void setEffortTaken(String effortTaken) {
        EffortTaken = effortTaken;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSanctionedLevelName() {
        return SanctionedLevelName;
    }

    public void setSanctionedLevelName(String sanctionedLevelName) {
        SanctionedLevelName = sanctionedLevelName;
    }

    public String getSanctionedLevelID() {
        return SanctionedLevelID;
    }

    public void setSanctionedLevelID(String sanctionedLevelID) {
        SanctionedLevelID = sanctionedLevelID;
    }

    public String getInspectinId() {
        return InspectinId;
    }

    public void setInspectinId(String inspectinId) {
        InspectinId = inspectinId;
    }
}
