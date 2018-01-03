package cjj.tiexi.shenyang.library.webservice;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by CJJ on 2018/1/3.
 */

public class SoapControlAPP
{
    private String NameSpace ;
    private String soapAction;
    private String url ;

    public SoapControlAPP (String url,String NameSpace)
    {
        this.url =url ;
        this.NameSpace = NameSpace ;
    }

    public SoapPrimitive ExecuteWebMethod(String MethodName, Map map, int timeout)
    {
        try
        {
            soapAction = NameSpace + MethodName;
            // step1 指定WebService的命名空间和调用的方法名
            SoapObject request = new SoapObject(NameSpace, MethodName);

            // step2 设置调用方法的参数值,这里的参数名称不一定和WebService一致
            if (map != null)
            {
                Set keySet = map.keySet();// 返回键的集合
                Iterator it = keySet.iterator();
                while (it.hasNext()) // 第一种迭代方式取键值
                {
                    Object key = it.next();
                    request.addProperty(key.toString(), map.get(key));
                }
            }
            // step3 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // 设置是否调用的是dotNet下的WebService
            envelope.dotNet = true;
            // 必须，等价于envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            // step4 创建HttpTransportSE对象
            MyHttpTransportSE ht = new MyHttpTransportSE(url, timeout);
            // step5 调用WebService
            ht.call(soapAction, envelope);
            // step6 使用getResponse方法获得WebService方法的返回结果
            if (envelope.getResponse() != null)
            {
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                return response;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
            return null;
        }
    }

    public SoapObject ExecuteWebMethodReturnClass(String MethodName, Map map)
    {
        try
        {
            soapAction = NameSpace + MethodName;
            soapAction= soapAction.replaceAll(" ", "%20");
            // step1 指定WebService的命名空间和调用的方法名
            SoapObject request = new SoapObject(NameSpace, MethodName);
            // step2 设置调用方法的参数值,这里的参数名称不一定和WebService一致
            if (map != null)
            {
                Set keySet = map.keySet();// 返回键的集合
                Iterator it = keySet.iterator();
                while (it.hasNext()) // 第一种迭代方式取键值
                {
                    Object key = it.next();
                    request.addProperty(key.toString(), map.get(key));
                }
            }
            // step3 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // 设置是否调用的是dotNet下的WebService
            envelope.dotNet = true;
            // 必须，等价于envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            // step4 创建HttpTransportSE对象
            MyHttpTransportSE ht = new MyHttpTransportSE(url, 60000);
            // step5 调用WebService
            ht.call(soapAction, envelope);
            // step6 使用getResponse方法获得WebService方法的返回结果
            if (envelope.getResponse() != null)
            {
                SoapObject response = (SoapObject) envelope.getResponse();
                return response;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
            return null;
        }
    }

//    public SoapPrimitive ExecuteWebMethodSubmitBatchNos(
//            String billNo, int num,
//            List<String> lineNos, List<String> beginDates,
//            List<String> beginNos, List<String> endDates, List<String> endNos,
//            String MethodName, Map map, int timeout)
//    {
//        try
//        {
//
//            soapAction = NameSpace + MethodName;
//            // step1 指定WebService的命名空间和调用的方法名
//            SoapObject request = new SoapObject(NameSpace, MethodName);
//
//            // step2 设置调用方法的参数值,这里的参数名称不一定和WebService一致
//            if (map != null)
//            {
//                Set keySet = map.keySet();// 返回键的集合
//                Iterator it = keySet.iterator();
//                while (it.hasNext()) // 第一种迭代方式取键值
//                {
//                    Object key = it.next();
//                    request.addProperty(key.toString(), map.get(key));
//                }
//            }
//            //-------------------------------------------------------------
//            PropertyInfo pInfo_lineNos = new PropertyInfo();
//            StringArraySerializer stringArray = new StringArraySerializer();
//            for(String str : lineNos)
//            {
//                stringArray.add(str);
//            }
//            pInfo_lineNos.setName("lineNos");
//            pInfo_lineNos.setValue(stringArray);
//            pInfo_lineNos.setType(stringArray.getClass());
//            pInfo_lineNos.setNamespace(NameSpace);
//
//            request.addProperty(pInfo_lineNos);
//            //-------------------------------------------------------------
//            PropertyInfo pInfo_beginDates = new PropertyInfo();
//            stringArray = new StringArraySerializer();
//
//            for(String str : beginDates)
//            {
//                stringArray.add(str);
//            }
//            pInfo_beginDates.setName("beginDates");
//            pInfo_beginDates.setValue(stringArray);
//            pInfo_beginDates.setType(stringArray.getClass());
//            pInfo_beginDates.setNamespace(NameSpace);
//
//            request.addProperty(pInfo_beginDates);
//            //-------------------------------------------------------------
//            PropertyInfo pInfo_beginNos = new PropertyInfo();
//            stringArray = new StringArraySerializer();
//            for(String str : beginNos)
//            {
//                stringArray.add(str);
//            }
//            pInfo_beginNos.setName("beginNos");
//            pInfo_beginNos.setValue(stringArray);
//            pInfo_beginNos.setType(stringArray.getClass());
//            pInfo_beginNos.setNamespace(NameSpace);
//
//            request.addProperty(pInfo_beginNos);
//            //-------------------------------------------------------------
//            PropertyInfo pInfo_endDates = new PropertyInfo();
//            stringArray = new StringArraySerializer();
//            for(String str : endDates)
//            {
//                stringArray.add(str);
//            }
//            pInfo_endDates.setName("endDates");
//            pInfo_endDates.setValue(stringArray);
//            pInfo_endDates.setType(stringArray.getClass());
//            pInfo_endDates.setNamespace(NameSpace);
//
//            request.addProperty(pInfo_endDates);
//
//            //-------------------------------------------------------------
//            PropertyInfo pInfo_endNos = new PropertyInfo();
//            stringArray = new StringArraySerializer();
//            for(String str : endNos)
//            {
//                stringArray.add(str);
//            }
//            pInfo_endNos.setName("endNos");
//            pInfo_endNos.setValue(stringArray);
//            pInfo_endNos.setType(stringArray.getClass());
//            pInfo_endNos.setNamespace(NameSpace);
//
//            request.addProperty(pInfo_endNos);
//            //-------------------------------------------------------------
//
//            // step3 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//                    SoapEnvelope.VER11);
//            // 设置是否调用的是dotNet下的WebService
//            envelope.dotNet = true;
//            // 必须，等价于envelope.bodyOut = request;
//            envelope.setOutputSoapObject(request);
//            // step4 创建HttpTransportSE对象
//            MyHttpTransportSE ht = new MyHttpTransportSE(url, timeout);
//            // step5 调用WebService
//            ht.call(soapAction, envelope);
//            // step6 使用getResponse方法获得WebService方法的返回结果
//            if (envelope.getResponse() != null)
//            {
//                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
//                return response;
//            }
//            else
//            {
//                return null;
//            }
//        }
//        catch (Exception e)
//        {
//            // TODO: handle exception
//            return null;
//        }
//    }

    //------------------------------------------------------------------------------------------

    public SoapPrimitive ExecuteWebMethod(String MethodName, Map map, int timeout,Context context)
    {
        try
        {
            soapAction = NameSpace + MethodName;
            // step1 指定WebService的命名空间和调用的方法名
            SoapObject request = new SoapObject(NameSpace, MethodName);

            // step2 设置调用方法的参数值,这里的参数名称不一定和WebService一致
            if (map != null)
            {
                Set keySet = map.keySet();// 返回键的集合
                Iterator it = keySet.iterator();
                while (it.hasNext()) // 第一种迭代方式取键值
                {
                    Object key = it.next();
                    request.addProperty(key.toString(), map.get(key));
                }
            }
            // step3 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // 设置是否调用的是dotNet下的WebService
            envelope.dotNet = true;
            // 必须，等价于envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            // step4 创建HttpTransportSE对象
            MyHttpTransportSE ht = new MyHttpTransportSE(url, timeout);
            // step5 调用WebService
            ht.call(soapAction, envelope);
            // step6 使用getResponse方法获得WebService方法的返回结果
            if (envelope.getResponse() != null)
            {
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                return response;
            }
            else
            {
                showNormalDialog("更新失败！","返回数据："+envelope .getResponse(),context );
                return null;
            }
        }
        catch (Exception e)
        {
            showNormalDialog("更新失败！",e.getMessage(),context );
            // TODO: handle exception
            return null;
        }
    }

    public SoapObject ExecuteWebMethodReturnClass( String MethodName, Map map, Context context )
    {
        try
        {
            url= url.replaceAll(" ", "%20");
            soapAction = NameSpace + MethodName;
            soapAction= soapAction.replaceAll(" ", "%20");
            // step1 指定WebService的命名空间和调用的方法名
            SoapObject request = new SoapObject(NameSpace, MethodName);
            // step2 设置调用方法的参数值,这里的参数名称不一定和WebService一致
            if (map != null)
            {
                Set keySet = map.keySet();// 返回键的集合
                Iterator it = keySet.iterator();
                while (it.hasNext()) // 第一种迭代方式取键值
                {
                    Object key = it.next();
                    request.addProperty(key.toString(), map.get(key));
                }
            }
            // step3 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // 设置是否调用的是dotNet下的WebService
            envelope.dotNet = true;
            // 必须，等价于envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            // step4 创建HttpTransportSE对象
            MyHttpTransportSE ht = new MyHttpTransportSE(url, 60000);
            // step5 调用WebService
            ht.call(soapAction, envelope);
            // step6 使用getResponse方法获得WebService方法的返回结果
            if (envelope.getResponse() != null)
            {
                SoapObject response = (SoapObject) envelope.getResponse();
                return response;
            }
            else
            {
                showNormalDialog("更新失败！","返回数据："+envelope .getResponse(),context );
                return null;
            }
        }
        catch (Exception e)
        {
            String a =e.getMessage() ;
            showNormalDialog("更新失败！",e.getMessage(),context );
            // TODO: handle exception
            return null;
        }
    }

    private static void showNormalDialog(String info1,String info2,Context context )
    {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle(info1);
        normalDialog.setMessage(info2 );
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //...To-do
                    }
                });

        // 显示
        normalDialog.show();
    }

}
