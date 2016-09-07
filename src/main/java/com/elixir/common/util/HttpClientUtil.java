package com.elixir.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jingyan HTTP 请求工具类
 */
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();


	/**
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public static JSONObject httpPost(String url, Map<String, String> paramMap) {
		String jsonStr=httpPost(url, paramMap, "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return jsonObject;
	}
	
	/**
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public static JSONObject httpGet(String url, Map<String, String> paramMap){
		String paramStr=createLinkString(paramMap);
		logger.info("http_get 请求入参："+paramStr);
		String jsonStr=httpGet(url + "?" + paramStr);
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return jsonObject;
	}

	/**
	 * @param url
	 * @param paramMap
	 * @param charset
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> paramMap, String charset) {
		// HttpClient
		CloseableHttpClient client = httpClientBuilder.build();
		HttpPost method = new HttpPost(url);
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		method.setConfig(requestConfig);
		try {
			if (null != paramMap) {
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> e : paramMap.entrySet()) {
					list.add(new BasicNameValuePair(e.getKey(), e.getValue()));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				method.setEntity(entity);
			}
			CloseableHttpResponse response = client.execute(method);
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				logger.error("http_post请求失败, code:" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("http_post请求出现异常,url:" + url, e);
		}
		return null;
	}

	public static String httpGet(String url) {
		// get请求返回结果
		try {
			// HttpClient
			CloseableHttpClient client = httpClientBuilder.build();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
					.build();
			request.setConfig(requestConfig);
			CloseableHttpResponse response = client.execute(request);
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				logger.error("http_get请求失败, code:" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("http_get请求出现异常,url:" + url, e);
		}
		return null;
	}

	
    /**
     * @param params
     * @return
     * GET 请求参数拼接
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
 
}
