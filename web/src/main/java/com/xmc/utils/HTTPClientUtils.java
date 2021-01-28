package com.xmc.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @Description: TODO
 * @Autohr xiaomingcong
 * @date 2021/1/14 10:41 上午
 * Version 1.0
 */
public class HTTPClientUtils {

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
//        get();
        String url = "https://127.0.0.1/postHello";
        String jsonStr = "{xxx}";
        String httpOrgCreateTestRtn = doPost(url,443, jsonStr, "utf-8");
    }

    public static void get() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        URI uri = new URI("http://localhost:8888/hello");
        HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();
        HttpResponse.BodyHandler bodyHandler = new HttpResponse.BodyHandler(){

            @Override
            public HttpResponse.BodySubscriber apply(HttpResponse.ResponseInfo responseInfo) {
                System.out.println(responseInfo);

//                return HttpResponse.BodySubscribers.replacing(Paths.get("/NULL"));
                return HttpResponse.BodySubscribers.ofString(Charset.forName("UTF-8"));

            }
        };
        HttpResponse httpResponse = httpClient.send(httpRequest,bodyHandler);
        System.out.println(httpResponse.body());
//        httpClient.executor();

    }

    /**
       * @Description: 使用https提交post请求
       * @Param  url
     * @param port
     * @param jsonstr
     * @param charset
       * @return java.lang.String
       * @Autohr xiaomingcong
       * @date 2021/1/17 11:22 下午
       * Version 1.0
    */
    @SuppressWarnings("resource")
    public static String doPost(String url,int port,String jsonstr,String charset){
        org.apache.http.client.HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient(port);
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("JSESSIONID","21A1C075508F9CB0C411C562B43CE9E4");
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            org.apache.http.HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }


    /**
       * @Description: TODO
       * @Param
       * @return javax.net.ssl.SSLContext    
       * @Autohr xiaomingcong
       * @date 2021/1/23 9:09 下午
       * Version 1.0
    */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException, IOException {
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, null, null);
//
//        SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
//        SSLSocket socket = (SSLSocket) factory.createSocket();
//
//        String[] protocols = socket.getSupportedProtocols();
//
//        System.out.println("Supported Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }
//
//        protocols = socket.getEnabledProtocols();
//
//        System.out.println("Enabled Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }

        SSLContext sc = SSLContext.getInstance("TLSv1.3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法  
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    public static CloseableHttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String body = "";

        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        //CloseableHttpClient client = HttpClients.createDefault();
        return client;
    }

}
