package bih.in.aanganwadiBenVerification.database;

import java.util.ArrayList;

public class DatabaseInfo {

	private ArrayList<String> scolumn_Name;
	private ArrayList<String> tcolumn_Name;
	private ArrayList<String> spriority;
	private ArrayList<String> tpriority;
	
	public void setSpinnerDataInfo(ArrayList<String> column_Name, ArrayList<String> priority)
	{
		this.scolumn_Name=column_Name;
		this.spriority=priority;
			
	}
	
	public void setTextDataInfo(ArrayList<String> column_Name, ArrayList<String> priority)
	{
		this.tcolumn_Name=column_Name;
		this.spriority=priority;
	}
	
	
	
	public ArrayList<String> getSpinnerColumnInfo()
	{
		String[] data=(String[]) scolumn_Name.toArray();
		int[] priority = null;
		String[] sortedData = null;
		ArrayList<String> sorted_Data = null;
		for(int i=0;i<spriority.size();i++)
		{
			priority[i]= Integer.parseInt(spriority.get(i));
		}
		
		try {
			sortedData=qSort(data,priority,0,data.length-1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<sortedData.length;i++)
		{
			sorted_Data.add(sortedData[i]);
		}
		return sorted_Data;
	}
	
	
	public ArrayList<String> getTextColumnInfo()
	{
		String[] data=(String[]) tcolumn_Name.toArray();
		int[] priority = null;
		String[] sortedData = null;
		ArrayList<String> sorted_Data = null;
		for(int i=0;i<tpriority.size();i++)
		{
			priority[i]= Integer.parseInt(tpriority.get(i));
		}
		
		try {
			sortedData=qSort(data,priority,0,data.length-1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<sortedData.length;i++)
		{
			sorted_Data.add(sortedData[i]);
		}
		return sorted_Data;
	}
	
	
	
	

		
	
	
	
	
	private  String[] qSort(String[] data, int[] a, int low, int high) throws InterruptedException
	{
		int p=a[0];
	     int l=a.length-1; 
	     int temp;
	     String temp1;
	     if(low<high)
	     {
		do
		{
			
			if(a[low]<p) low++;
			try
			{
			if(a[high]>p) high--;
			}catch(Exception e){  System.out.print(high);}
			
			if(a[low]>p && a[high]<p)
			{
				
				temp=a[low];
				a[low]=a[high];
				a[high]=temp;
				
				temp1=data[low];
				data[low]=data[high];
				data[high]=temp1;
				
				
			}
			 	
		}while(low<high);
		
		temp=a[0];
		a[0]=a[high];
		a[high]=temp;
		
		temp1=data[0];
		data[0]=data[high];
		data[high]=temp1;
		
		qSort(data,a,1,high-1);
	     
		qSort(data,a,high+1,l);

	}
	   return data;  
	     
	}

}
