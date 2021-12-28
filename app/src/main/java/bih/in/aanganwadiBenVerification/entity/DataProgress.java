package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;


public class DataProgress implements KvmSerializable, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static Class<DataProgress> DataProgress_CLASS = DataProgress.class;
	
	
	
	String spinner1_dataCode,spinner1_dataName,spinner2_dataCode,spinner2_dataName,spinner3_dataCode,spinner3_dataName;
	String spinner4_dataCode;
	String spinner4_dataName;
	String spinner5_dataCode;
	String spinner5_dataName;
	String spMakeFoodData;

	String data_Text1;
	String data_Text2;
	String data_Text3;
	String data_Text4;
	String data_Text5;
	String data_remark;
	
	String F11;
	String F12;
	String RegisteredChildren;
	String slno;
	


	String panchayat_code;
	String sector_code;
	String block_code;
	String district_code;
	String fyear;
	
	String fyearName;
	String panchayatName;
	private String sectorName;
	String blockName;
	String districtName;
	

	String Latitude="";
	String Longitude="";
	String gps_date="";
	String entry_Date="";
	String Upload_by="";

	
	String Photo1;
	String Photo2;
	String Photo3;
	String Photo4;
	String Photo5;

	String schemeID;

	public String getSchemeID() {
		return schemeID;
	}

	public void setSchemeID(String schemeID) {
		this.schemeID = schemeID;
	}

	public String getFyearName() {
		return fyearName;
	}


	public void setFyearName(String fyearName) {
		this.fyearName = fyearName;
	}


	public String getPanchayatName() {
		return panchayatName;
	}


	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}


	public String getBlockName() {
		return blockName;
	}


	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	

	
	
	public String getFyear() {
		return fyear;
	}

	public void setFyear(String fyear) {
		this.fyear = fyear;
	}


	
	public String getF11() {
		return F11;
	}

	public void setF11(String f11) {
		F11 = f11;
	}

	public String getF12() {
		return F12;
	}

	public void setF12(String f12) {
		F12 = f12;
	}

	
	
	
	
	public String getSlno() {
		return slno;
	}
	
	public void setSlno(String slno) {
		this.slno = slno;
	}
	
	
	
	

	
	
	
	
	

	public String getPanchayat_code() {
		return panchayat_code;
	}

	public void setPanchayat_code(String panchayat_code) {
		this.panchayat_code = panchayat_code;
	}

	public String getBlock_code() {
		return block_code;
	}

	public void setBlock_code(String block_code) {
		this.block_code = block_code;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getSpinner1_Data() {
		return spinner1_dataName;
	}

	public void setSpinner1_Data(String s1) {
		spinner1_dataName=s1;
	}



	public String getSpinner2_Data() {
		return spinner2_dataName;
	}
	
	public void setSpinner2_Data(String s2) {
		spinner2_dataName=s2;
	}


	public String getSpinner3_Data() {
		return spinner3_dataName;
	}

	
	public void setSpinner3_Data(String s3) {
		spinner3_dataName=s3;
	}

	
	public String getSpinner4_Data() {
		return spinner4_dataName;
	}

	public void setSpinner4_Data(String s4) {
		spinner4_dataName=s4;
	}

	
	public String getSpinner5_Data() {
		return spinner5_dataName;
	}
	
	public void setSpinner5_Data(String s5) {
		spinner5_dataName=s5;
	}




	
	public String getText1_Data()
	{
		return data_Text1;
	}
	
	public void setText1_Data(String t1)
	{
		data_Text1=t1;
	}
	
	public String getText2_Data()
	{
		return data_Text2;
	}
	
	public void setText2_Data(String t2)
	{
		data_Text2=t2;
	}
	
	
	public String getText3_Data()
	{
		return data_Text3;
	}
	
	
	public void setText3_Data(String t3)
	{
		data_Text3=t3;
	}
	
	public String getText4_Data()
	{
		return data_Text4;
	}
	
	
	public void setText4_Data(String t4)
	{
		data_Text4=t4;
	}
	
	
	public String getText5_Data()
	{
		return data_Text5;
	}
	
	
	public void setText5_Data(String t5)
	{
		data_Text5=t5;
	}
	
	
	
	public String getRemark_Data()
	{
		return data_remark;
	}
	
	
	public void setRemark_Data(String remark)
	{
		data_remark=remark;
	}
	
	
	public String getLatitude()
	{
		return Latitude;
	}
	
	public String getLongitude()
	{
		return Longitude;
	}
	
	
	public String getEntry_Date() {
		return entry_Date;
	}

	
	
	public void setEntry_Date(String date) {
		entry_Date=date;
	}


	public void setLatitude(String latitude) {
		Latitude = latitude;
	}


	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPhoto1() {
		return Photo1;
	}




	public void setPhoto1(String photo1) {
		Photo1 = photo1;
	}




	public String getPhoto2() {
		return Photo2;
	}




	public void setPhoto2(String photo2) {
		Photo2 = photo2;
	}


	public String getPhoto3() {
		return Photo3;
	}


	public void setPhoto3(String photo3) {
		Photo3 = photo3;
	}


	public String getPhoto4() {
		return Photo4;
	}


	public void setPhoto4(String photo4) {
		Photo4 = photo4;
	}



	public String getUpload_by() {
		return Upload_by;
	}




	public void setUpload_by(String upload_by) {
		Upload_by = upload_by;
	}


	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		
	}


	public String getSector_code() {
		return sector_code;
	}

	public void setSector_code(String sector_code) {
		this.sector_code = sector_code;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getGps_date() {
		return gps_date;
	}

	public void setGps_date(String gps_date) {
		this.gps_date = gps_date;
	}

	public String getSpMakeFoodData() {
		return spMakeFoodData;
	}

	public void setSpMakeFoodData(String spMakeFoodData) {
		this.spMakeFoodData = spMakeFoodData;
	}

	public String getRegisteredChildren() {
		return RegisteredChildren;
	}

	public void setRegisteredChildren(String registeredChildren) {
		RegisteredChildren = registeredChildren;
	}

	public String getPhoto5() {
		return Photo5;
	}

	public void setPhoto5(String photo5) {
		Photo5 = photo5;
	}
}
