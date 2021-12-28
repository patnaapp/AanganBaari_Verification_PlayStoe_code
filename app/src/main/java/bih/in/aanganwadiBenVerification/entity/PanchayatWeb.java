package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class PanchayatWeb {

	
	

	private String code;
	private String value;
	private String SectorCode;
	private String AWC_Code;
	private String userid;

	
	public static Class<PanchayatWeb> PanchayatWeb_CLASS= PanchayatWeb.class;
	
	public PanchayatWeb(SoapObject sobj)
	{
		
		this.value=sobj.getProperty(2).toString();
		this.code=sobj.getProperty(0).toString();
		this.SectorCode=sobj.getProperty(6).toString();
		this.AWC_Code=sobj.getProperty(1).toString();

		
	}

	public PanchayatWeb() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSectorCode() {
		return SectorCode;
	}

	public void setSectorCode(String sectorCode) {
		SectorCode = sectorCode;
	}

	public String getAWC_Code() {
		return AWC_Code;
	}

	public void setAWC_Code(String AWC_Code) {
		this.AWC_Code = AWC_Code;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
