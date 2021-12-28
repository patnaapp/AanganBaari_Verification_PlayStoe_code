package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.SoapObject;

public class ProjectListClass {

	public String getProjectCode() {
		return projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getCount() {
		return count;
	}

	private String projectCode;

	private String projectName;
	private String count;


	public static Class<ProjectListClass> ProjectList_CLASS= ProjectListClass.class;


	public ProjectListClass(String projectName, String projectCode, String count)
	{
		this.projectName=projectName;
		this.projectCode=projectCode;
		this.count=count;
	}



	public ProjectListClass(SoapObject sobj)
	{
		this.projectName=sobj.getProperty("projectName").toString();
		this.projectCode=sobj.getProperty("projectCode").toString();
		this.count=sobj.getProperty("count").toString();
	}






	
	
}
