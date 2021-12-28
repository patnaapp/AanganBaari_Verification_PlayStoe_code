package bih.in.aanganwadiBenVerification.web_services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import bih.in.aanganwadiBenVerification.entity.AWC_Model;
import bih.in.aanganwadiBenVerification.entity.BenList;
import bih.in.aanganwadiBenVerification.entity.CatWiseBenStatus;
import bih.in.aanganwadiBenVerification.entity.Financial_Month;
import bih.in.aanganwadiBenVerification.entity.OtpVerifyModel;
import bih.in.aanganwadiBenVerification.entity.PlantationDetail;
import bih.in.aanganwadiBenVerification.entity.SevikaAttendance_Entity;
import bih.in.aanganwadiBenVerification.entity.SignUp;
import bih.in.aanganwadiBenVerification.entity.UserDetails;
import bih.in.aanganwadiBenVerification.entity.Versioninfo;
import bih.in.aanganwadiBenVerification.entity.ward;
import bih.in.aanganwadiBenVerification.entity.wdcBenList;
import io.michaelrocks.paranoid.Obfuscate;

import static org.apache.http.util.EntityUtils.getContentCharSet;

@Obfuscate
public class WebServiceHelper
{
    public static final String SERVICENAMESPACE = "http://164.100.251.19/";
    public static final String SERVICENAMESPACE2 = "http://icdsonline.bih.nic.in/";

    public static final String SERVICEURL1 = "http://164.100.251.19/Anganlabharthiverify.asmx";
    public static final String SERVICEURL2 = "http://icdsonline.bih.nic.in/KanyaUtthanWDcWebservice.asmx";

    public static final String APPVERSION_METHOD = "getAppLatest";
    public static final String AUTHENTICATE_METHOD = "Authenticate";

    private static final String FIELD_METHOD = "getFieldInformation";
    private static final String SPINNER_METHOD = "getSpinnerInformation";
    private static final String REGISTER_USER = "RegisterUser";

    private static final String GETINITIALPLANTATIONDATA = "getInitialDetailRDDPlantation";

    private static final String GETWARDLIST = "getWardList";
    private static final String GETPANCHAYATLIST = "getPanchayatList";
    private static final String GETSBenDetails = "getPanchayatList";

    private static final String getAWCList = "getAWCList";
    private static final String SevikaBenificiary = "SevikaBenificiary";
    private static final String SevikaAttendenceApprove = "SevikaAttendenceApprove";
    private static final String LsWDCUpdate = "LsWDCUpdate";

    private static final String geCDPOWDCList = "geCDPOWDCList";
  //  private static final String BenificiaryToken = "BenificiaryToken";
   // private static final String BenificiaryToken = "BenificiaryWiseToken_Verification";
    private static final String BenificiaryToken = "BenificiaryWiseToken_Verification_new";
  //  private static final String BenificiaryTokenApprove = "BeneficiaryTokenVerify";
    private static final String BenificiaryTokenApprove = "BeneficiaryTokenVerifynew";
    private static final String GetOtp = "BeneficiaryTokenVerify";
    private static final String FinancialMonth_LIST_METHOD = "FinYearM";
    private static final String Attendance = "InsertStudent_Photo";

    static String rest;

    public static Versioninfo CheckVersion(String version)
    {
        Versioninfo versioninfo;
        SoapObject res1;
        try
        {
            res1=getServerData(APPVERSION_METHOD, Versioninfo.Versioninfo_CLASS,"IMEI","Ver","0",version);
            SoapObject final_object = (SoapObject) res1.getProperty(0);

            versioninfo = new Versioninfo(final_object);
        }
        catch (Exception e)
        {
            return null;
        }
        return versioninfo;

    }


    public static String completeSignup(SignUp data,String imei,String version)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, REGISTER_USER);
        request.addProperty("Name",data.getName());
        request.addProperty("DistrictCode",data.getDist_code());
        request.addProperty("BlockCode",data.getBlock_code());
        request.addProperty("MobileNo",data.getMobile());
        request.addProperty("Degignation",data.getDesignation());
        request.addProperty("IMEI",imei);
        request.addProperty("Appversion",version);
        request.addProperty("Pwd","abc");
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + REGISTER_USER,envelope);
            rest = envelope.getResponse().toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "0";
        }
        return rest;
    }

    public static String resizeBase64Image(String base64image)
    {
        byte [] encodeByte=Base64.decode(base64image.getBytes(), Base64.DEFAULT);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length,options);

        if(image.getHeight() <= 200 && image.getWidth() <= 200)
        {
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 100, 100, false);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);

        byte [] b=baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public static UserDetails Login(String User_ID,String password,String otp)
    {
        try
        {
            SoapObject res1;
            res1=getServerData(AUTHENTICATE_METHOD, UserDetails.getUserClass(),"UserID","Password",User_ID,password);
            if (res1 != null)
            {
                return new UserDetails(res1);
            }
            else
            {
                return null;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<PlantationDetail> getPlantationData(String disctrictCode, String userRole)
    {
        SoapObject res1;
        res1=getServerData(GETINITIALPLANTATIONDATA, PlantationDetail.PlantationDetail_CLASS, "DistCode", "UserRole", disctrictCode,userRole);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<PlantationDetail> fieldList = new ArrayList<PlantationDetail>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    PlantationDetail plantationData= new PlantationDetail(final_object);
                    fieldList.add(plantationData);
                }
            }
            else
                return fieldList;
        }

        return fieldList;
    }


    public static ArrayList<ward> getWardListData(String BlockCode)
    {

        SoapObject res1;
        res1 = getServerData(GETWARDLIST, ward.ward_CLASS, "BlockCode", BlockCode);
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();

        ArrayList<ward> fieldList = new ArrayList<ward>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    ward wardInfo = new ward(final_object);
                    fieldList.add(wardInfo);
                }
            }
            else
                return fieldList;
        }

        return fieldList;
    }


    public static ArrayList<BenList> getBenificiaryList(String DistCode)
    {

        SoapObject res1;
        res1=getServerData(SevikaBenificiary, BenList.Ben_CLASS,"AWCID", DistCode);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<BenList> fieldList = new ArrayList<BenList>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    BenList villageData= new BenList(final_object);
                    fieldList.add(villageData);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<wdcBenList> getWdcBenificiaryList(String DistCode)
    {

        SoapObject res1;
        res1=getServerData1(geCDPOWDCList, wdcBenList.Ben_CLASS,"_AWCCode", DistCode);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<wdcBenList> fieldList = new ArrayList<wdcBenList>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    wdcBenList villageData= new wdcBenList(final_object);
                    fieldList.add(villageData);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<OtpVerifyModel> getOtpBenificiaryList(String awc_code,String monthid,String yearid)
    {

        SoapObject res1;
        res1=getServerData(BenificiaryToken, OtpVerifyModel.OTP_CLASS,"AWCCode","Month","Year", awc_code,monthid,yearid);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<OtpVerifyModel> fieldList = new ArrayList<OtpVerifyModel>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    OtpVerifyModel villageData= new OtpVerifyModel(final_object);
                    fieldList.add(villageData);
                }
            }
            else
            {
                return fieldList;
            }

        }

        return fieldList;
    }

    public static ArrayList<AWC_Model> getAWCList(String sector, String proj)
    {

        SoapObject res1;
        res1=getServerData(getAWCList, AWC_Model.AWC_CLASS,"sectorID", "ProjectCode", sector, proj);
        int TotalProperty=0;
        if(res1!=null) TotalProperty= res1.getPropertyCount();

        ArrayList<AWC_Model> fieldList = new ArrayList<AWC_Model>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    AWC_Model villageData= new AWC_Model(final_object);
                    fieldList.add(villageData);
                }
            }
            else
                return fieldList;
        }

        return fieldList;
    }

    public static SoapObject getServerData(String methodName,Class bindClass,String param,String value )
    {
        SoapObject res1;
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param,value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static SoapObject getServerData1(String methodName,Class bindClass,String param,String value )
    {
        SoapObject res1;
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE2,methodName);
            request.addProperty(param,value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE2,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL2);
            androidHttpTransport.call(SERVICENAMESPACE2 + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static SoapObject getServerData(String methodName,Class bindClass,String param1,String param2,String value1,String value2 )
    {
        SoapObject res1;
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static SoapObject getServerData(String methodName,Class bindClass,String param1,String param2,String param3,String value1,String value2,String value3 )
    {
        SoapObject res1;
        try
        {
            SoapObject request = new SoapObject(SERVICENAMESPACE,methodName);
            request.addProperty(param1,value1);
            request.addProperty(param2,value2);
            request.addProperty(param3,value3);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE,bindClass.getSimpleName(),bindClass);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + methodName,envelope);
            res1 = (SoapObject) envelope.getResponse();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return res1;
    }

    public static String UploadTeacherDetails(Context context, ArrayList<BenList> checkbox, String AppVersion, String Devicet,String UserName)
    {

        context=context;
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;
        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        //Document doc = domImpl.createDocument("http://mobapp.bih.nic.in/",   UPLOAD_TRACKING_DATA_ARRAY, null);
        Document doc = domImpl.createDocument(SERVICENAMESPACE, SevikaAttendenceApprove   , null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();
        //--------------
        Element pdlsElement = doc.createElement("BenApprove");
        ArrayList<BenList> poleDetail = checkbox;

        // Upload(poleDetail, res);

        // Element pdElement = doc.getDocumentElement();
        for(int x=0;x<poleDetail.size();x++)
        {
            Element pdElement = doc.createElement("MarkApprove");

            Element fid = doc.createElement("a_Id_");
            fid.appendChild(doc.createTextNode(poleDetail.get(x).getA_id()));
            // fid.appendChild(doc.createTextNode("20830"));

            pdElement.appendChild(fid);

            Element vLebel = doc.createElement("_IsVerifiedchar");
            vLebel.appendChild(doc.createTextNode(poleDetail.get(x).getIsBenPer()));

            Log.d("TagData",poleDetail.get(x).getIsBenPer()+" "+poleDetail.get(x).getA_id());
            pdElement.appendChild(vLebel);

            Element vLebel2 = doc.createElement("_VerifiedBy_");
            vLebel2.appendChild(doc.createTextNode(UserName));
            pdElement.appendChild(vLebel2);

            Element vLebel3 = doc.createElement("IsNutrition");
            vLebel3.appendChild(doc.createTextNode(poleDetail.get(x).getNutritionId()));
            pdElement.appendChild(vLebel3);

            Element vLebel4 = doc.createElement("NoMonth");
            vLebel4.appendChild(doc.createTextNode(poleDetail.get(x).getNoOfMonth()));
            pdElement.appendChild(vLebel4);
            Log.d("TagData",poleDetail.get(x).getIsBenPer()+" "+poleDetail.get(x).getNutritionId()+poleDetail.get(x).getNoOfMonth());

            pdlsElement.appendChild(pdElement);
        }
        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try
        {

            try
            {
                trans = transfac.newTransformer();
            }
            catch (TransformerConfigurationException e1)
            {

                // TODO Auto-generated catch block

                e1.printStackTrace();
                return "0";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;

            try
            {
                trans.transform(source, result);
            }
            catch (TransformerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0";
            }

            String SOAPRequestXML = sw.toString();

            String startTag = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"   >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";

//       HttpPost httppost = new HttpPost("http://mobapp.bih.nic.in/locationcapturewebservice.asmx");

            HttpPost httppost = new HttpPost(SERVICEURL1);

            // Log.i("Request: ", "XML Request= " + startTag + SOAPRequestXML
            // + endTag);

            StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag);

            sEntity.setContentType("text/xml");
            // httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
            httppost.setEntity(sEntity);

            HttpClient httpclient = new DefaultHttpClient();

            httpResponse = (BasicHttpResponse) httpclient.execute(httppost);
            HttpEntity entity = httpResponse.getEntity();
            Log.i("Responddddddddse: ", httpResponse.getStatusLine().toString());

            if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK"))
            {

                String output = _getResponseBody(entity);
                Log.d("egffgfdg",output);
                String result1 = output.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><InsertDataNewResponse xmlns=\"http://164.100.251.19/\"><InsertDataNewResult>", "");
                result1 = result1.replace("</InsertDataNewResult></InsertDataNewResponse></soap:Body></soap:Envelope>","");
                Log.e("Result", result1);

                res = "1";
            }
            else
            {
                res = "0";
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }

        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());
        return res;

    }

    public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException
    {

        if (entity == null)
        {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }

        InputStream instream = entity.getContent();

        if (instream == null)
        {
            return "";
        }

        if (entity.getContentLength() > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        }

        String charset = getContentCharSet(entity);

        if (charset == null)
        {
            charset = org.apache.http.protocol.HTTP.DEFAULT_CONTENT_CHARSET;
        }

        Reader reader = new InputStreamReader(instream, charset);

        StringBuilder buffer = new StringBuilder();

        try
        {

            char[] tmp = new char[1024];

            int l;

            while ((l = reader.read(tmp)) != -1)
            {
                buffer.append(tmp, 0, l);
            }

        }
        finally
        {
            reader.close();
        }

        return buffer.toString();

    }

    public static String UploadBenMemberDetail(Context context, ArrayList<BenList> members, String AppVersion, String Devicet,String UserName)
    {

        context=context;
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;
        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        //Document doc = domImpl.createDocument("http://mobapp.bih.nic.in/",   UPLOAD_TRACKING_DATA_ARRAY, null);
        Document doc = domImpl.createDocument(SERVICENAMESPACE,    SevikaAttendenceApprove, null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();

//        Element eid1 = doc.createElement("_Dist_Code");
//        eid1.appendChild(doc.createTextNode(user.getDistCode()));
//        poleElement.appendChild(eid1);

        //--------------Array-----------------//

        Element pdlsElement = doc.createElement("Approve");
        ArrayList<BenList> list = members;

        // Upload(poleDetail, res);

        // Element pdElement = doc.getDocumentElement();
        for(int x=0;x<list.size();x++)
        {
            Element pdElement = doc.createElement("MarkApprove");

            Element fid = doc.createElement("a_Id_");
            fid.appendChild(doc.createTextNode(list.get(x).getA_id()));

            pdElement.appendChild(fid);
            Element vLebel = doc.createElement("_IsVerifiedchar");
            vLebel.appendChild(doc.createTextNode(list.get(x).getIsBenPer()));
            pdElement.appendChild(vLebel);

            Element vLebel2 = doc.createElement("_VerifiedBy_");
            vLebel2.appendChild(doc.createTextNode(UserName));
            pdElement.appendChild(vLebel2);

            pdlsElement.appendChild(pdElement);
        }
        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try
        {

            try
            {
                trans = transfac.newTransformer();
            }
            catch (TransformerConfigurationException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "0";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;

            try
            {
                trans.transform(source, result);
            }
            catch (TransformerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0";
            }

            String SOAPRequestXML = sw.toString();

            String startTag = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";

//       HttpPost httppost = new HttpPost("http://mobapp.bih.nic.in/locationcapturewebservice.asmx");

            try
            {
                HttpPost httppost = new HttpPost(SERVICEURL1);
                // Log.i("Request: ", "XML Request= " + startTag + SOAPRequestXML
                // + endTag);
                StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag);

                sEntity.setContentType("text/xml");
                // httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
                httppost.setEntity(sEntity);

                //HttpClient httpclient = new DefaultHttpClient();
                HttpClient httpClient = new DefaultHttpClient();

                httpResponse = (BasicHttpResponse) httpClient.execute(httppost);

                HttpEntity entity = httpResponse.getEntity();
                //String output = _getResponseBody(entity);

                Log.e("Responddddddddse: ", httpResponse.getStatusLine().toString());
                //Log.e("ResponseBody", ""+httpResponse.getEntity().getContent());

                if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK"))
                {
                   /* String output = _getResponseBody(entity);
                    String result1 = output.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><InsertDataNewResponse xmlns=\"http://164.100.251.19/\"><InsertDataNewResult>", "");
                    result1 = result1.replace("</InsertDataNewResult></InsertDataNewResponse></soap:Body></soap:Envelope>","");
                    Log.e("Result", result1);
                   */ //SoapResponseEntity result = new SoapResponseEntity(httpResponse.getEntity());
                    res = "1";
                    // res = result1;
                }
                else
                    {
                    res = "0";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "0";
            }

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());

        return res;
    }

    public static Element getSoapPropert(Document doc, String key, String value)
    {
        Element eid = doc.createElement(key);
        eid.appendChild(doc.createTextNode(value));
        return eid;
    }

    public static String parseRespnse(String xml)
    {
        String result = "Failed to parse";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try
        {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("LsWDCUpdateResult");
            result = list.item(0).getTextContent();
            //System.out.println(list.item(0).getTextContent());
        }
        catch (ParserConfigurationException e)
        {
        }
        catch (SAXException e)
        {
        }
        catch (IOException e)
        {
        }

        return result;
    }


    public static String parseRespnse1(String xml)
    {
        String result = "Failed to parse";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try
        {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("BeneficiaryTokenVerifynewResult");
            result = list.item(0).getTextContent();
            //System.out.println(list.item(0).getTextContent());
        }
        catch (ParserConfigurationException e)
        {
        }
        catch (SAXException e)
        {
        }
        catch (IOException e)
        {
        }

        return result;
    }

    public static String UploadwdcTeacherDetails(Context context, ArrayList<wdcBenList> checkbox, String AppVersion, String Devicet,String UserName)
    {

        context=context;
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;
        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        Document doc = domImpl.createDocument(SERVICENAMESPACE2,LsWDCUpdate,null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();
        Element pdlsElement = doc.createElement("BenApprove");
        ArrayList<wdcBenList> poleDetail = checkbox;

        for(int x=0;x<poleDetail.size();x++)
        {
            Element pdElement = doc.createElement("updateimage");
            Element fid = doc.createElement("_LsVerified");
            fid.appendChild(doc.createTextNode(poleDetail.get(x).getIsBenPer()));
            pdElement.appendChild(fid);

            Element vLebel = doc.createElement("_LsVerifiedby");
            vLebel.appendChild(doc.createTextNode(UserName));
            pdElement.appendChild(vLebel);

            Element vLebel2 = doc.createElement("_LsRejectedReason");
            vLebel2.appendChild(doc.createTextNode(poleDetail.get(x).getRejectReason()));
            //vLebel.appendChild(doc.createTextNode("1234"));
            pdElement.appendChild(vLebel2);

            Element vLebel3 = doc.createElement("_id");
            vLebel3.appendChild(doc.createTextNode(poleDetail.get(x).getId()));
            pdElement.appendChild(vLebel3);
//
            Element vLebel4 = doc.createElement("_LsVerifiedDate");
            vLebel4.appendChild(doc.createTextNode(poleDetail.get(x).getBenPerDate()));
            pdElement.appendChild(vLebel4);

            Element vLebel5 = doc.createElement("_App_ver");
            vLebel5.appendChild(doc.createTextNode(AppVersion));
            pdElement.appendChild(vLebel5);

            pdlsElement.appendChild(pdElement);
        }
        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try
        {
            try
            {
                trans = transfac.newTransformer();
            }
            catch (TransformerConfigurationException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "0";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;
            try
            {
                trans.transform(source, result);
            }
            catch (TransformerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0";
            }

            String SOAPRequestXML = sw.toString();
            String startTag = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"   >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";
//			HttpPost httppost = new HttpPost("http://mobapp.bih.nic.in/locationcapturewebservice.asmx");

            HttpPost httppost = new HttpPost(SERVICEURL2);

            // Log.i("Request: ", "XML Request= " + startTag + SOAPRequestXML
            // + endTag);

            StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag, HTTP.UTF_8);

            sEntity.setContentType("text/xml");
            // httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
            httppost.setEntity(sEntity);

            HttpClient httpclient = new DefaultHttpClient();

            httpResponse = (BasicHttpResponse) httpclient.execute(httppost);
            HttpEntity entity = httpResponse.getEntity();

            Log.i("Responddddddddse: ", httpResponse.getStatusLine().toString());

            if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK"))
            {
                String output = _getResponseBody(entity);
                res = parseRespnse(output);
                // res = "1";
            }
            else
            {
                res = "0";
                // res = "0, Server no reponse";
//                String output = _getResponseBody(entity);
//                res = parseRespnse(output);
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
            //return "0, Exception Caught";
        }
        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());
        return res;

    }


    public static String UploadwdcOTPVerifiedDetails(Context context, ArrayList<OtpVerifyModel> checkbox, String AppVersion, String Devicet,String UserName)
    {

        context=context;
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        dbfac.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;
        try
        {
            docBuilder = dbfac.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
        DOMImplementation domImpl = docBuilder.getDOMImplementation();
        Document doc = domImpl.createDocument(SERVICENAMESPACE,	BenificiaryTokenApprove, null);
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(true);

        Element poleElement = doc.getDocumentElement();
        Element pdlsElement = doc.createElement("TokenApprove");
        ArrayList<OtpVerifyModel> poleDetail = checkbox;

        for(int x=0;x<poleDetail.size();x++)
        {
            Element pdElement = doc.createElement("MarkTokenVerifynew");
            Element fid = doc.createElement("a_Id_");
            fid.appendChild(doc.createTextNode(poleDetail.get(x).getId()));
            pdElement.appendChild(fid);

            Element vLebel = doc.createElement("_IsVerifiedchar");
            vLebel.appendChild(doc.createTextNode(poleDetail.get(x).getIsBenPer()));
            pdElement.appendChild(vLebel);

            Element vLebel2 = doc.createElement("_VerifiedBy_");
           // vLebel2.appendChild(doc.createTextNode(poleDetail.get(x).get_VerifiedBy_()));
            vLebel2.appendChild(doc.createTextNode(UserName));
            //vLebel.appendChild(doc.createTextNode("1234"));
            pdElement.appendChild(vLebel2);

            Element vLebel3 = doc.createElement("_Month_");
            vLebel3.appendChild(doc.createTextNode(poleDetail.get(x).getMonth()));
            pdElement.appendChild(vLebel3);
////
            Element vLebel4 = doc.createElement("_Year_");
            vLebel4.appendChild(doc.createTextNode(poleDetail.get(x).getYear()));
            pdElement.appendChild(vLebel4);

//            Element vLebel5 = doc.createElement("_App_ver");
//            vLebel5.appendChild(doc.createTextNode(AppVersion));
//            pdElement.appendChild(vLebel5);

            pdlsElement.appendChild(pdElement);
        }
        poleElement.appendChild(pdlsElement);

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = null;
        String res = "0";
        try {

            try
            {
                trans = transfac.newTransformer();
            }
            catch (TransformerConfigurationException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "0";
            }

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            BasicHttpResponse httpResponse = null;

            try
            {
                trans.transform(source, result);
            }
            catch (TransformerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "0";
            }

            String SOAPRequestXML = sw.toString();

            String startTag = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchem\" "
                    + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"   >  "
                    + "<soap:Body > ";
            String endTag = "</soap:Body > " + "</soap:Envelope>";

//			HttpPost httppost = new HttpPost("http://mobapp.bih.nic.in/locationcapturewebservice.asmx");

            HttpPost httppost = new HttpPost(SERVICEURL1);

            // Log.i("Request: ", "XML Request= " + startTag + SOAPRequestXML
            // + endTag);

            StringEntity sEntity = new StringEntity(startTag + SOAPRequestXML+ endTag, HTTP.UTF_8);

            sEntity.setContentType("text/xml");
            // httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
            httppost.setEntity(sEntity);

            HttpClient httpclient = new DefaultHttpClient();

            httpResponse = (BasicHttpResponse) httpclient.execute(httppost);
            HttpEntity entity = httpResponse.getEntity();

            Log.i("Responddddddddse: ", httpResponse.getStatusLine().toString());

            if (httpResponse.getStatusLine().getStatusCode() == 200|| httpResponse.getStatusLine().getReasonPhrase().toString().equals("OK"))
            {
                String output = _getResponseBody(entity);
                res = parseRespnse1(output);
                // res = "1";
            }
            else
            {
                res = "0";
                // res = "0, Server no reponse";
//                String output = _getResponseBody(entity);
//                res = parseRespnse(output);
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
            //return "0, Exception Caught";
        }
        // response.put("HTTPStatus",httpResponse.getStatusLine().toString());
        return res;

    }


    public static ArrayList<CatWiseBenStatus> getCategoryWiseBenData(String districtCode, String blockCode, String diseCode)
    {
        SoapObject request = new SoapObject(SERVICENAMESPACE, GetOtp);
        request.addProperty("DistrictCode", districtCode);
        request.addProperty("BlockCode", blockCode);
        request.addProperty("DiseCode", diseCode);
        SoapObject res1;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, CatWiseBenStatus.CatWiseBenStatus_CLASS.getSimpleName(), CatWiseBenStatus.CatWiseBenStatus_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + GetOtp,envelope);

            res1 = (SoapObject) envelope.getResponse();

        }
        catch (Exception e    )
        {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<CatWiseBenStatus> list = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++)
        {
            if (res1.getProperty(ii) != null)
            {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    CatWiseBenStatus district = new CatWiseBenStatus(final_object);
                    list.add(district);
                }
            }
                           return list;
        }

        return list;
    }


    public static ArrayList<Financial_Month> getFinancialMonth()
    {
        SoapObject res1;
        res1 = getServerData(FinancialMonth_LIST_METHOD, Financial_Month.Financial_Month_CLASS, "Active", "Y");
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();
        ArrayList<Financial_Month> fieldList = new ArrayList<Financial_Month>();

        for (int i = 0; i < TotalProperty; i++)
        {
            if (res1.getProperty(i) != null)
            {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject)
                {
                    SoapObject final_object = (SoapObject) property;
                    Financial_Month sm = new Financial_Month(final_object);
                    fieldList.add(sm);
                }
            } else
                return fieldList;
        }
        return fieldList;
    }


    public static String UploadAttendanceDate(SevikaAttendance_Entity data) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Attendance);
        request.addProperty("_FyearID", data.get_FyearID());
        request.addProperty("_Month_ID", data.get_Month_ID());
        request.addProperty("_Distcode", data.get_Distcode());
        request.addProperty("_projectCode", data.get_projectCode());
        request.addProperty("_PanchayatCode", data.get_PanchayatCode());
        request.addProperty("_AWCID", data.get_AWCID());
        request.addProperty("_EntryBy", data.get_EntryBy());

        request.addProperty("_UploadDate", data.get_UploadDate());
        request.addProperty("_AttaendanceDate", data.get_AttaendanceDate());
        request.addProperty("_StudentCount", data.get_StudentCount());
        request.addProperty("_StudentPhoto", data.get_StudentPhoto());
        request.addProperty("_AppVersion", data.get_AppVersion());
        request.addProperty("_DeviceType", data.get_DeviceType());
        request.addProperty("_Imei", data.get_Imei());
        request.addProperty("_Lat", data.get_Lat());
        request.addProperty("_Long", data.get_Long());


        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE + Attendance, envelope);

            rest = envelope.getResponse().toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            //return "0";
            return null;
        }
        return rest;

    }

}
