package com.qdsg.ylt.ylt_jwt.utils;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: HttpUtils
 * @Description:工具类
 * @author: ZJ
 * @date: 2018年8月2日 下午2:09:27
 * @version 1.0
 * @Copyright: 2018 www.qdsgvision.com Inc. All rights reserved.
 */
public class HttpUtils {

  private static ObjectMapper objMapper = new ObjectMapper();
  private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

  private static PoolingHttpClientConnectionManager httpClientManager = null;
  /**
   * 初始化httpManager
   */
  static {
    httpClientManager = new PoolingHttpClientConnectionManager();
    httpClientManager.setDefaultMaxPerRoute(20);// 最大路由连接数
    httpClientManager.setMaxTotal(80);// 最大连接数
  }

  /**
   * 获取httpClient
   * 
   * @return
   */
  private static CloseableHttpClient getHttpClient() {
    // 设置请求配置
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000)
        .setConnectionRequestTimeout(5000).setCookieSpec(CookieSpecs.STANDARD).build();
    // 设置httpClient 配置
    CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientManager)
        .setDefaultRequestConfig(requestConfig).build();
    return httpClient;
  }

  /**
   * get 请求
   * 
   * @return
   */
  public static String httpGetRequest() {
    return null;
  };

  /**
   * post 请求,没有参数就传null
   * 
   * @return
   */
  public static String httpPostRequest(String uri, Map<String, String> params) {
    String result = null;

    try {
      String paramStr = null;
      HttpEntity httpEntity = null;
      HttpPost httpPost = new HttpPost(uri);
      if (params != null) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
          String name = (String) iter.next();
          String value = String.valueOf(params.get(name));
          nvps.add(new BasicNameValuePair(name, value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
      }
      CloseableHttpClient httpClient = getHttpClient();
      CloseableHttpResponse response = httpClient.execute(httpPost);
      result = responseDeal(response);

    } catch (IOException e) {
      logger.error("request params is {}", params);
      e.printStackTrace();
    }
    return result;
  }


  /**
   * 处理响应
   * 
   * @return
   */
  private static String responseDeal(CloseableHttpResponse response) {
    String result = null;
    if (response.getStatusLine().getStatusCode() == 200) {
      try {
        InputStream input = response.getEntity().getContent();
        String entity = IOUtils.toString(input, Charset.forName("UTF-8"));
        result = entity;
      } catch (UnsupportedOperationException | IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  /**
   * 判断是否ajax请求
   * @param req
   * @return
   */
  public static Boolean isAjaxRequest(HttpServletRequest req) {
    final String headName = "X-Requested-With";
    final String ajaxType = "XMLHttpRequest";
    String requestType = req.getHeader(headName);
    if(ajaxType.equals(requestType)) {
      return true;
    }
    return false;
  }

  public static String getTokenFromRequest(HttpServletRequest request){
    String header = request.getHeader("Authorization");
    String parameter = request.getParameter("token");
    final String token = header ==null ?parameter:header;
    return token;
  }

}
