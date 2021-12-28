package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class SpinnerMaster {

	private String code;
	private String value;
	private String field;
	
	public static Class<SpinnerMaster> SpinnerMaster_CLASS= SpinnerMaster.class;
	
	public SpinnerMaster(SoapObject sobj)
	{
		
		this.code=sobj.getProperty(0).toString();
		this.value=sobj.getProperty(1).toString();
		this.field=sobj.getProperty(2).toString();
		
	}
	
	
	public String getCode()
	{
		return code;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String getField()
	{
		return field;
	}
	
}
