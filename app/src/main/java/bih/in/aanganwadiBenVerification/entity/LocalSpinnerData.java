package bih.in.aanganwadiBenVerification.entity;

public class LocalSpinnerData {

	private String code;
	private String value;
	private String field;
	private String code2;

	public LocalSpinnerData()
	{
	}
	
	public LocalSpinnerData(String code, String value, String field, String code2)
	{
		this.code=code;
		this.value=value;
		this.field=field;
		this.code2=code2;
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

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setField(String field) {
		this.field = field;
	}
}
