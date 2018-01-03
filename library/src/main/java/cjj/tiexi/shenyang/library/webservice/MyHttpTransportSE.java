package cjj.tiexi.shenyang.library.webservice;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;

import java.io.IOException;
import java.net.Proxy;

/**
 * Created by CJJ on 2018/1/3.
 */

public class MyHttpTransportSE extends HttpTransportSE
{


    public MyHttpTransportSE(Proxy proxy, String url)
    {
        super(proxy, url);
        this.url =url;
    }

    public MyHttpTransportSE(String url,int timeout)
    {
        super(url);
        this.timeout=timeout;
    }

    public ServiceConnection getServiceConnection() throws IOException
    {
        ServiceConnectionSE serviceConnection=new ServiceConnectionSE(this.url,timeout);
        return serviceConnection;
    }


}
