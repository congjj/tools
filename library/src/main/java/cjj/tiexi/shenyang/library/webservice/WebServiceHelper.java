package cjj.tiexi.shenyang.library.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CJJ on 2018/1/3.
 */

public class WebServiceHelper
{

    private static WebServiceHelper webService  = null;
    private WebServiceHelper(){ }
    /** 获取实例 */
    public static synchronized WebServiceHelper getInstance()
    {
        if (webService == null)
        {
            webService = new WebServiceHelper();
        }
        return webService;
    }

    // .net提供的WebService
    private Boolean dotNet = Boolean.TRUE ;
    // 请求响应时间（毫秒）
    private Integer timeout = 60000;

    /**
     * 调用callWebService服务
     * @param param 请求参数
     */
    public String callWebService(String param){
        String result = null;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("json", param);
        String[] strs = callWebServices("callWebService",paramMap);
        if( null != strs && strs.length>0){
            result = strs[0];
        }
        return appendChar(result);
    }

    /**
     * 调用指定（@param methodName）服务
     */
    public String callWebService(String methodName,String param){
        String result = null;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("json", param);
        String[] strs = callWebServices(methodName,paramMap);
        if( null != strs && strs.length>0){
            result = strs[0];
        }
        return appendChar(result);
    }

    public String callWebService(String methodName,Map<String, String> paramMap)
    {
        String result = null;
        if(methodName.trim() .length() !=0)
        {
            String[] strs = callWebServices(methodName,paramMap);
            if(null!=strs && strs.length>0)
            {
                result = strs[0];
            }
        }
        return appendChar(result);
    }

    public String[] callWebServices(String methodName,Map<String, String> paramMap)
    {
        String[] result = null;
        try
        {
            SoapObject soapObject = initSoapObject(methodName,paramMap);
            if(null != soapObject && soapObject.getPropertyCount() > 0)
            {
                result = new String[soapObject.getPropertyCount()];
                for(int i=0;i< soapObject.getPropertyCount();i++)
                {
                    SoapPrimitive value=(SoapPrimitive) soapObject.getProperty(i);
                    result[i] = value.toString();
                }
            }
        }
        catch (Exception ex)
        {
            //Log.e("log", "调用WebService失败：" + ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    private SoapObject initSoapObject(String methodName , Map<String, String> paramMap) throws Exception
    {
        String nameSpace ="";// LoadProperties.getInstance().getPropertieValue("service_namespace");
        String serviceAction = "";//LoadProperties.getInstance().getPropertieValue("service_action");

        SoapObject request = new SoapObject(nameSpace, methodName);
        PropertyInfo propertyInfo  = getPropertyInfo(paramMap);
        if( null != propertyInfo ){
            request.addProperty(propertyInfo);
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope( SoapEnvelope.VER10 | SoapEnvelope.VER11 );
        envelope.bodyOut = request;
        envelope.dotNet = getDotNet();
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(serviceAction, getTimeout());
        transport.debug = true;
        transport.call( nameSpace + methodName , envelope);
        SoapObject soapObject = (SoapObject) envelope.bodyIn;
        return soapObject;
    }

    private PropertyInfo getPropertyInfo(Map<String, String> paramMap) throws Exception{
        PropertyInfo propertyInfo = null;
        if(null!=paramMap && paramMap.size()>0){
            propertyInfo = new PropertyInfo();
            for(Map.Entry<String, String> entry : paramMap.entrySet()){
                propertyInfo.setName(entry.getKey());
                propertyInfo.setValue(entry.getValue());
                propertyInfo.setType(String.class);
            }
        }
        return propertyInfo;
    }

    private String appendChar(String result)
    {
        return "{jsonParam=" + result + "}";
    }

    public Boolean getDotNet()
    {
        return dotNet;
    }

    /** 设置 .net提供的WebService */
    public void setDotNet(Boolean dotNet)
    {
        this.dotNet = dotNet;
    }

    /** 请求响应时间（毫秒） */
    public Integer getTimeout()
    {
        return timeout;
    }

    /** 设置请求响应时间（毫秒） */
    public void setTimeout(Integer timeout)
    {
        this.timeout = timeout;
    }


}
