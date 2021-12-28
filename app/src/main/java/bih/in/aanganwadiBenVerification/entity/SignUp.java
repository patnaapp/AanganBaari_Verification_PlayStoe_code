package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class SignUp implements KvmSerializable {

    public static Class<SignUp> SIGNUP_CLASS= SignUp.class;

    private String dist_code,Dist_name,block_code,block_nm,address,name,fname,mobile,password,confirm_password,EntryBy,designation,IMEI,appVersion;

    public SignUp(SoapObject res1) {


        this.dist_code=res1.getProperty("DistCode").toString();
        this.block_code=res1.getProperty("BlockCode").toString();
        this.address=res1.getProperty("Address").toString();
        this.name=res1.getProperty("Name").toString();
        this.fname=res1.getProperty("FatherName").toString();
        this.mobile=res1.getProperty("Mobile").toString();
        this.EntryBy=res1.getProperty("EntryBy").toString();
        this.password=res1.getProperty("password").toString();

    }

    public static Class<SignUp> getSingUpClass(){
        return SIGNUP_CLASS;
    }

    public SignUp()
    {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDist_code() {
        return dist_code;
    }

    public void setDist_code(String dist_code) {
        this.dist_code = dist_code;
    }

    public String getDist_name() {
        return Dist_name;
    }

    public void setDist_name(String dist_name) {
        Dist_name = dist_name;
    }

    public String getBlock_code() {
        return block_code;
    }

    public void setBlock_code(String block_code) {
        this.block_code = block_code;
    }

    public String getBlock_nm() {
        return block_nm;
    }

    public void setBlock_nm(String block_nm) {
        this.block_nm = block_nm;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
