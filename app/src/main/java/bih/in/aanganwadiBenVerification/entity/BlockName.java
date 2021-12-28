package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class BlockName {

    private String Name;
    
    
    
    
	public static Class<BlockName> BlockName_CLASS= BlockName.class;
	
	public BlockName(SoapObject sobj)
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
