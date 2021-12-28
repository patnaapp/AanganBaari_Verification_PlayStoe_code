package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class ReportClass  {


	

	private String F1;
    private String F2;
    private String F3;
    private String F4;
    private String F5;
    private String F6;
    private String F7;
    private String F8;
    private String F9;
    private String F10;
    private String F11;
    private String F12;
    
    private String Remarks;
    private String EntryDate;
    private String Lat;
    private String Lng;
    private String EntryBy;
    private String DistCode;
    private String BlockCode;
    private String PanchayatCode;
    private String FYear;
    private String PanchayatName;
    private String BlockName;
    
    
    
    
	public String getPanchayatName() {
		return PanchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		PanchayatName = panchayatName;
	}

	public String getBlockName() {
		return BlockName;
	}

	public void setBlockName(String blockName) {
		BlockName = blockName;
	}



	public static Class<ReportClass> ReportClass_CLASS= ReportClass.class;


	public ReportClass() {};
	
	public ReportClass(SoapObject sobj)
	{
		this.slNo=sobj.getProperty(0).toString();
	    this.F1=sobj.getProperty(1).toString();
	    this.F2=sobj.getProperty(2).toString();
	    this.F3=sobj.getProperty(3).toString();
	    this.F4=sobj.getProperty(4).toString();
	    this.F5=sobj.getProperty(5).toString();
	    this.F6=sobj.getProperty(6).toString();
	    this.F7=sobj.getProperty(7).toString();
	    this.F8=sobj.getProperty(8).toString();
	    this.F9=sobj.getProperty(9).toString();
	    this.F10=sobj.getProperty(10).toString();
	    this.F11=sobj.getProperty(11).toString();
	    this.F12=sobj.getProperty(12).toString();
	    
	    this.Remarks=sobj.getProperty(13).toString();
	    this.EntryDate=sobj.getProperty(14).toString();
	    this.Lat=sobj.getProperty(15).toString();
	    this.Lng=sobj.getProperty(16).toString();
	 
	    this.DistCode=sobj.getProperty(17).toString();
	    this.BlockCode=sobj.getProperty(18).toString();
	    this.PanchayatCode=sobj.getProperty(19).toString();
	    
	    

//	    		ArrayList<com.bih.GrihNirikshan.entity.PanchayatName> pnamelist= WebServiceHelper.getPanchayatName(this.PanchayatCode);
//	    		if(pnamelist.size()>0)
//	    		this.PanchayatName=pnamelist.get(0).getName();
//
//
//	    		ArrayList<com.bih.GrihNirikshan.entity.BlockName> bnamelist= WebServiceHelper.getBlockName(this.BlockCode);
//	    		if(bnamelist.size()>0)
//	    		this.BlockName=bnamelist.get(0).getName();
	
	    
	}

	
	
	private String slNo;
    public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getF1() {
		return F1;
	}

	public void setF1(String f1) {
		F1 = f1;
	}

	public String getF2() {
		return F2;
	}

	public void setF2(String f2) {
		F2 = f2;
	}

	public String getF3() {
		return F3;
	}

	public void setF3(String f3) {
		F3 = f3;
	}

	public String getF4() {
		return F4;
	}

	public void setF4(String f4) {
		F4 = f4;
	}

	public String getF5() {
		return F5;
	}

	public void setF5(String f5) {
		F5 = f5;
	}

	public String getF6() {
		return F6;
	}

	public void setF6(String f6) {
		F6 = f6;
	}

	public String getF7() {
		return F7;
	}

	public void setF7(String f7) {
		F7 = f7;
	}

	public String getF8() {
		return F8;
	}

	public void setF8(String f8) {
		F8 = f8;
	}

	public String getF9() {
		return F9;
	}

	public void setF9(String f9) {
		F9 = f9;
	}

	public String getF10() {
		return F10;
	}

	public void setF10(String f10) {
		F10 = f10;
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

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getEntryDate() {
		return EntryDate;
	}

	public void setEntryDate(String entryDate) {
		EntryDate = entryDate;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLng() {
		return Lng;
	}

	public void setLng(String lng) {
		Lng = lng;
	}

	public String getEntryBy() {
		return EntryBy;
	}

	public void setEntryBy(String entryBy) {
		EntryBy = entryBy;
	}

	public String getDistCode() {
		return DistCode;
	}

	public void setDistCode(String distCode) {
		DistCode = distCode;
	}

	public String getBlockCode() {
		return BlockCode;
	}

	public void setBlockCode(String blockCode) {
		BlockCode = blockCode;
	}

	public String getPanchayatCode() {
		return PanchayatCode;
	}

	public void setPanchayatCode(String panchayatCode) {
		PanchayatCode = panchayatCode;
	}

	public String getFYear() {
		return FYear;
	}

	public void setFYear(String fYear) {
		FYear = fYear;
	}
	

	
	
	

	}


