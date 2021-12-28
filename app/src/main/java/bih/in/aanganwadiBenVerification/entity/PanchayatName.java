package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class PanchayatName {

    private String Name;
    
    
    
    
	public static Class<PanchayatName> PanchayatName_CLASS= PanchayatName.class;
	
	public PanchayatName(SoapObject sobj)
	{
		this.Name=sobj.getProperty(0).toString();

	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name =name;
	}




	
	
}
