package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class ReportMonth {

    private String Month;
    
    
    
    
	public static Class<ReportMonth> ReportMonth_CLASS= ReportMonth.class;
	
	public ReportMonth(SoapObject sobj)
	{
		this.Month=sobj.getProperty(0).toString();

	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}




	
	
}
