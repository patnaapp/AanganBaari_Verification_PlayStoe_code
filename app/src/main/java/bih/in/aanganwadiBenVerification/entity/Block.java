package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class Block implements KvmSerializable, Serializable {

	private static final long serialVersionUID = 1L;

	public static Class<Block> BLOCK_CLASS = Block.class;

	private String DistCode = "";
	private String BlockCode = "";
	private String BlockName = "";
	private String PacsBankId = "";
	private String UserId;

	public Block() {
		super();
	}

	public Block(SoapObject obj) {
		this.DistCode  = obj.getProperty("DistCode").toString();
		this.BlockCode = obj.getProperty("BlockCode").toString();
		this.BlockName = obj.getProperty("BlockName").toString();
		//this.PacsBankId = obj.getProperty("PACSBankID").toString();
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

	public String getBlockName() {
		return BlockName;
	}

	public void setBlockName(String blockName) {
		BlockName = blockName;
	}

	public String getPacsBankId() {
		return PacsBankId;
	}

	public void setPacsBankId(String pacsBankId) {
		PacsBankId = pacsBankId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
}
