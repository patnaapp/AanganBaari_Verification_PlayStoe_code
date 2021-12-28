package bih.in.aanganwadiBenVerification.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class CatWiseBenStatus implements KvmSerializable {

    public static Class<CatWiseBenStatus> CatWiseBenStatus_CLASS = CatWiseBenStatus.class;

    private String id="";
    private String DistID;
    private String DistName;
    private String BlockID;
    private String BlockName;

    private String SchoolName;
    private String SchoolId;
    private String DiseCode;

    private String TotalBen;
    private String TotalBenMale;
    private String TotalBenFemale;

    private String TotalBenGen;
    private String TotalBenMaleGen;
    private String TotalBenFemaleGen;

    private String TOBC;
    private String OBCMale;
    private String OBCFemale;
    private String TSc;
    private String SCMale;
    private String SCFemale;
    private String TSCMushar;
    private String SCMusharMale;
    private String SCMusharFemale;
    private String TST;
    private String STMale;
    private String STFemale;
    private String TEBC;
    private String EBCMale;
    private String EBCFemale;

    public CatWiseBenStatus(SoapObject res1){
        //this.id=res1.getProperty("id").toString();
        //this.DistID=res1.getProperty("DistrictCode").toString();
        //this.DistName=res1.getProperty("DistName").toString();
        //this.BlockID=res1.getProperty("BlockCode").toString();
        this.DiseCode=res1.getProperty("_Dise_Code").toString();
        this.SchoolName=res1.getProperty("_SchoolName").toString();
        this.TotalBen=res1.getProperty("_TotalBeneficiary").toString();
        this.TotalBenMale=res1.getProperty("_TotalMale").toString();
        this.TotalBenFemale=res1.getProperty("_TotalFemale").toString();
        this.TotalBenGen=res1.getProperty("_TGeneral").toString();
        this.TotalBenMaleGen=res1.getProperty("_GeneralMale").toString();
        this.TotalBenFemaleGen=res1.getProperty("_GeneralFemale").toString();
        this.TOBC=res1.getProperty("_TOBC").toString();
        this.OBCMale=res1.getProperty("_OBCMale").toString();
        this.OBCFemale=res1.getProperty("_OBCFemale").toString();
        this.TSc=res1.getProperty("_TSc").toString();
        this.SCMale=res1.getProperty("_SCMale").toString();
        this.SCFemale=res1.getProperty("_SCFemale").toString();
        this.TSCMushar=res1.getProperty("_TSCMushar").toString();
        this.SCMusharMale=res1.getProperty("_SCMusharMale").toString();
        this.SCMusharFemale=res1.getProperty("_SCMusharFemale").toString();
        this.TST=res1.getProperty("_TST").toString();
        this.STMale=res1.getProperty("_STMale").toString();
        this.STFemale=res1.getProperty("_STFemale").toString();
        this.TEBC=res1.getProperty("_TEBC").toString();
        this.EBCMale=res1.getProperty("_EBCMale").toString();
        this.EBCFemale=res1.getProperty("_EBCFemale").toString();
    }

    public CatWiseBenStatus(String id, String distID, String distName, String blockID, String blockName, String schoolName, String schoolId, String diseCode, String totalBen, String totalBenMale, String totalBenFemale, String totalBenGen, String totalBenMaleGen, String totalBenFemaleGen) {
        this.id = id;
        DistID = distID;
        DistName = distName;
        BlockID = blockID;
        BlockName = blockName;
        SchoolName = schoolName;
        SchoolId = schoolId;
        DiseCode = diseCode;
        TotalBen = totalBen;
        TotalBenMale = totalBenMale;
        TotalBenFemale = totalBenFemale;
        TotalBenGen = totalBenGen;
        TotalBenMaleGen = totalBenMaleGen;
        TotalBenFemaleGen = totalBenFemaleGen;
    }


    public String getTOBC() {
        return TOBC;
    }

    public void setTOBC(String TOBC) {
        this.TOBC = TOBC;
    }

    public String getOBCMale() {
        return OBCMale;
    }

    public void setOBCMale(String OBCMale) {
        this.OBCMale = OBCMale;
    }

    public String getOBCFemale() {
        return OBCFemale;
    }

    public void setOBCFemale(String OBCFemale) {
        this.OBCFemale = OBCFemale;
    }

    public String getTSc() {
        return TSc;
    }

    public void setTSc(String TSc) {
        this.TSc = TSc;
    }

    public String getSCMale() {
        return SCMale;
    }

    public void setSCMale(String SCMale) {
        this.SCMale = SCMale;
    }

    public String getSCFemale() {
        return SCFemale;
    }

    public void setSCFemale(String SCFemale) {
        this.SCFemale = SCFemale;
    }

    public String getTSCMushar() {
        return TSCMushar;
    }

    public void setTSCMushar(String TSCMushar) {
        this.TSCMushar = TSCMushar;
    }

    public String getSCMusharMale() {
        return SCMusharMale;
    }

    public void setSCMusharMale(String SCMusharMale) {
        this.SCMusharMale = SCMusharMale;
    }

    public String getSCMusharFemale() {
        return SCMusharFemale;
    }

    public void setSCMusharFemale(String SCMusharFemale) {
        this.SCMusharFemale = SCMusharFemale;
    }

    public String getTST() {
        return TST;
    }

    public void setTST(String TST) {
        this.TST = TST;
    }

    public String getSTMale() {
        return STMale;
    }

    public void setSTMale(String STMale) {
        this.STMale = STMale;
    }

    public String getSTFemale() {
        return STFemale;
    }

    public void setSTFemale(String STFemale) {
        this.STFemale = STFemale;
    }

    public String getTEBC() {
        return TEBC;
    }

    public void setTEBC(String TEBC) {
        this.TEBC = TEBC;
    }

    public String getEBCMale() {
        return EBCMale;
    }

    public void setEBCMale(String EBCMale) {
        this.EBCMale = EBCMale;
    }

    public String getEBCFemale() {
        return EBCFemale;
    }

    public void setEBCFemale(String EBCFemale) {
        this.EBCFemale = EBCFemale;
    }

    public static Class<CatWiseBenStatus> getCatWiseBenStatus_CLASS() {
        return CatWiseBenStatus_CLASS;
    }

    public static void setCatWiseBenStatus_CLASS(Class<CatWiseBenStatus> catWiseBenStatus_CLASS) {
        CatWiseBenStatus_CLASS = catWiseBenStatus_CLASS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistID() {
        return DistID;
    }

    public void setDistID(String distID) {
        DistID = distID;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getBlockID() {
        return BlockID;
    }

    public void setBlockID(String blockID) {
        BlockID = blockID;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(String schoolId) {
        SchoolId = schoolId;
    }

    public String getDiseCode() {
        return DiseCode;
    }

    public void setDiseCode(String diseCode) {
        DiseCode = diseCode;
    }

    public String getTotalBen() {
        return TotalBen;
    }

    public void setTotalBen(String totalBen) {
        TotalBen = totalBen;
    }

    public String getTotalBenMale() {
        return TotalBenMale;
    }

    public void setTotalBenMale(String totalBenMale) {
        TotalBenMale = totalBenMale;
    }

    public String getTotalBenFemale() {
        return TotalBenFemale;
    }

    public void setTotalBenFemale(String totalBenFemale) {
        TotalBenFemale = totalBenFemale;
    }

    public String getTotalBenGen() {
        return TotalBenGen;
    }

    public void setTotalBenGen(String totalBenGen) {
        TotalBenGen = totalBenGen;
    }

    public String getTotalBenMaleGen() {
        return TotalBenMaleGen;
    }

    public void setTotalBenMaleGen(String totalBenMaleGen) {
        TotalBenMaleGen = totalBenMaleGen;
    }

    public String getTotalBenFemaleGen() {
        return TotalBenFemaleGen;
    }

    public void setTotalBenFemaleGen(String totalBenFemaleGen) {
        TotalBenFemaleGen = totalBenFemaleGen;
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
