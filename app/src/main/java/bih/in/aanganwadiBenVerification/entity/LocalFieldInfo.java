package bih.in.aanganwadiBenVerification.entity;

public class LocalFieldInfo {

	private String slNo;
	private String Field;
	private String Label;
	private String isActive;
	private String Type;
	private String Sequence;
	
	
	
	public LocalFieldInfo(String slNo, String Field, String Label, String isActive, String Type, String Sequence)
	{
		this.slNo=slNo;
		this.Field=Field;
		this.Label=Label;
		this.isActive=isActive;
		this.Type=Type;
		this.Sequence=Sequence;
	}
	
	public String getslNo()
	{
		return slNo;
	}
	
	public String getField()
	{
		return Field;
	}
	
	public String getLabel()
	{
		return Label;
	}
	
	public String getisActive()
	{
		return isActive;
	}
	
	public String getType()
	{
		return Type;
	}
	
	public String getSequence()
	{
		return Sequence;
	}
	
	
}
