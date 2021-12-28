package bih.in.aanganwadiBenVerification.entity;
import org.ksoap2.serialization.SoapObject;

public class BindClass {

	    private String Name;
		public static Class<BindClass> BindClass_CLASS= BindClass.class;
		public static SoapObject sobj;

		
		
		public SoapObject getAllData(SoapObject sobj)
		{
			this.Name=sobj.getProperty(0).toString();
			return sobj;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name =name;
		}
		
	}


