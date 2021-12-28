package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class ReportYear implements KvmSerializable, Serializable {
	    private String Year;
	    
	    
	    
	    
		public static Class<ReportYear> ReportYear_CLASS= ReportYear.class;
		
		public ReportYear(SoapObject sobj)
		{
			this.Year=sobj.getProperty(0).toString();
  
		}

		public String getYear() {
			return Year;
		}

		public void setYear(String year) {
			Year = year;
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

		@Override
		public void setProperty(int arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}


		



	
}
