package bih.in.aanganwadiBenVerification.entity;

import java.io.Serializable;

/**
 * Created by nisci on 07-Sep-2017.
 */

public class SevikaAttendance_Entity implements Serializable {


    public String get_FyearID() {
        return _FyearID;
    }

    public void set_FyearID(String _FyearID) {
        this._FyearID = _FyearID;
    }

    public String get_Month_ID() {
        return _Month_ID;
    }

    public void set_Month_ID(String _Month_ID) {
        this._Month_ID = _Month_ID;
    }

    public String get_Distcode() {
        return _Distcode;
    }

    public void set_Distcode(String _Distcode) {
        this._Distcode = _Distcode;
    }

    public String get_projectCode() {
        return _projectCode;
    }

    public void set_projectCode(String _projectCode) {
        this._projectCode = _projectCode;
    }

    public String get_PanchayatCode() {
        return _PanchayatCode;
    }

    public void set_PanchayatCode(String _PanchayatCode) {
        this._PanchayatCode = _PanchayatCode;
    }

    public String get_AWCID() {
        return _AWCID;
    }

    public void set_AWCID(String _AWCID) {
        this._AWCID = _AWCID;
    }

    public String get_EntryBy() {
        return _EntryBy;
    }

    public void set_EntryBy(String _EntryBy) {
        this._EntryBy = _EntryBy;
    }

    public String get_UploadDate() {
        return _UploadDate;
    }

    public void set_UploadDate(String _UploadDate) {
        this._UploadDate = _UploadDate;
    }

    public String get_AttaendanceDate() {
        return _AttaendanceDate;
    }

    public void set_AttaendanceDate(String _AttaendanceDate) {
        this._AttaendanceDate = _AttaendanceDate;
    }

    public String get_StudentCount() {
        return _StudentCount;
    }

    public void set_StudentCount(String _StudentCount) {
        this._StudentCount = _StudentCount;
    }

    public String get_StudentPhoto() {
        return _StudentPhoto;
    }

    public void set_StudentPhoto(String _StudentPhoto) {
        this._StudentPhoto = _StudentPhoto;
    }

    public String get_AppVersion() {
        return _AppVersion;
    }

    public void set_AppVersion(String _AppVersion) {
        this._AppVersion = _AppVersion;
    }

    public String get_DeviceType() {
        return _DeviceType;
    }

    public void set_DeviceType(String _DeviceType) {
        this._DeviceType = _DeviceType;
    }

    public String get_Imei() {
        return _Imei;
    }

    public void set_Imei(String _Imei) {
        this._Imei = _Imei;
    }

    private String _FyearID;
    private String _Month_ID;
    private String _Distcode;
    private String _projectCode;
    private String _PanchayatCode;
    private String _AWCID;
    private String _EntryBy;
    private String _UploadDate;
    private String _AttaendanceDate;
    private String _StudentCount;
    private String _StudentPhoto;
    private String _AppVersion;
    private String _DeviceType;
    private String _Imei;
    private String _Lat;
    private String _Long;


    public String get_Long() {
        return _Long;
    }

    public void set_Long(String _Long) {
        this._Long = _Long;
    }

    public String get_Lat() {
        return _Lat;
    }

    public void set_Lat(String _Lat) {
        this._Lat = _Lat;
    }
}
