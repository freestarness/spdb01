package com.logictech.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static com.logictech.App.logger;


public class HttpclientUtil {



	/**
	 * httpclient post
	 *
	 * @return
	 */
	public static String postUrl(String url, Map<String, String> para) {

		String uri=url.trim();
		String responseDate = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		logger.debug("-------------------------uri:" + uri);
		HttpPost httpPostReq = new HttpPost(uri);
		httpPostReq.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		Builder rconfigbu = RequestConfig.custom();
		rconfigbu.setSocketTimeout(30000);
		rconfigbu.setConnectTimeout(5000);
		RequestConfig rconfig = rconfigbu.build();
		httpPostReq.setConfig(rconfig);
		// --------End----------
		BufferedReader reader =null;
		InputStreamReader inputReader=null;
		CloseableHttpResponse resp =null;
		try {
			if (null != para) {
				ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String obj : para.keySet()) {

					paramList.add(new BasicNameValuePair(obj, para.get(obj)));
				}

				httpPostReq.setEntity(new UrlEncodedFormEntity(paramList,"UTF-8"));
			}
			resp = httpClient.execute(httpPostReq);

			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				inputReader = new InputStreamReader(resp.getEntity().getContent());
				
				reader = new BufferedReader(inputReader);
				StringBuffer result = new StringBuffer();
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					result.append(inputLine);
				}
				responseDate = new String(result.toString().getBytes(),"utf-8");
				logger.info(result.toString());
				
				
			} else {
				logger.info("http client request fail..StatusCode:"
						+ resp.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("TAG", e);
		} finally {
			if(null!=resp)
			{
				try {
					resp.close();
				} catch (IOException e) {
					logger.error("close CloseableHttpResponse exception.",e);
				}
			}
			if(null!=reader)
			{
				try {
					reader.close();
					reader=null;
				} catch (IOException e) {
					logger.error("close reader exception.",e);
				}
			}
			if(null!=inputReader)
			{
				try {
					inputReader.close();
				} catch (IOException e) {
					
					logger.error("close inputReader exception.",e);
				}
			}
			httpPostReq.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseDate;
	}

	/**
	 * httpclient post
	 *
	 * @return
	 */
	public static String postUrlOfStr(String url, String questStr) {
		String uri=url.trim();
		String responseDate = "";
		HttpClient httpClient = HttpClients.createDefault();
		logger.debug("-------------------------uri:" + uri);
		HttpPost httpPostReq = new HttpPost(uri);
		httpPostReq.addHeader("Connection", "close");
		httpPostReq.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		// ---
		Builder rconfigbu = RequestConfig.custom();
		rconfigbu.setSocketTimeout(30000);
		rconfigbu.setConnectTimeout(5000);
		RequestConfig rconfig = rconfigbu.build();
		httpPostReq.setConfig(rconfig);
		
		
		
		// --------End----------
		try {
			if (null != questStr && questStr.trim().length() > 0) {

				StringEntity strEntity = new StringEntity(questStr,"UTF-8");
				strEntity.setContentEncoding("UTF-8");
				httpPostReq.setEntity(strEntity);
			}
			HttpResponse resp = httpClient.execute(httpPostReq);

			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				responseDate = EntityUtils.toString(resp.getEntity());

				// logger.info(result.toString());
			} else {
				logger.info("http client request fail..StatusCode:"
						+ resp.getStatusLine().getStatusCode());
			}

		} catch (Exception e) {
			logger.error("TAG", e);
		} finally {
			httpPostReq.releaseConnection();
			httpClient = null;
		}
		return responseDate;
	}
	
	/** 
	* @Title: postUrlOfJson 
	* @Description: 提交json格式数据 
	* @param url
	* @param jsonStr
	* @return 
	*/
	public String postUrlOfJson(String url, String jsonStr) {
		String uri=url.trim();
		String responseDate = "";
		HttpClient httpClient = HttpClients.createDefault();
		logger.debug("-------------------------uri:" + uri);
		HttpPost httpPostReq = new HttpPost(uri);
		httpPostReq.addHeader("Connection", "close");
		httpPostReq.addHeader("Content-Type",
				"application/json;charset=UTF-8");

		// ---
		Builder rconfigbu = RequestConfig.custom();
		rconfigbu.setSocketTimeout(30000);
		rconfigbu.setConnectTimeout(5000);
		RequestConfig rconfig = rconfigbu.build();
		httpPostReq.setConfig(rconfig);
		
		
		
		// --------End----------
		try {
			if (null != jsonStr && jsonStr.trim().length() > 0) {

				StringEntity strEntity = new StringEntity(jsonStr,"UTF-8");
				strEntity.setContentEncoding("UTF-8");
				httpPostReq.setEntity(strEntity);
			}
			HttpResponse resp = httpClient.execute(httpPostReq);

			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				responseDate = EntityUtils.toString(resp.getEntity());

				// logger.info(result.toString());
			} else {
				logger.info("http client request fail..StatusCode:"
						+ resp.getStatusLine().getStatusCode());
			}

		} catch (Exception e) {
			logger.error("TAG", e);
		} finally {
			httpPostReq.releaseConnection();
			httpClient = null;
		}
		return responseDate;
	}
	/** 
	* @Title: postUrlOfJson 
	* @Description: 提交json格式数据 
	* @param url
	* @param jsonStr
	* @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	*/
	public String postUrlOfJson2(String url, String jsonStr) throws Exception {
		String uri=url.trim();
		String responseDate = "";
		HttpClient httpClient = HttpClients.createDefault();
		logger.debug("-------------------------uri:" + uri);
		HttpPost httpPostReq = new HttpPost(uri);
		httpPostReq.addHeader("Connection", "close");
		httpPostReq.addHeader("Content-Type","application/json;charset=UTF-8");
		Builder rconfigbu = RequestConfig.custom();
		rconfigbu.setSocketTimeout(30000);
		rconfigbu.setConnectTimeout(5000);
		RequestConfig rconfig = rconfigbu.build();
		httpPostReq.setConfig(rconfig);
		
		if (null != jsonStr && jsonStr.trim().length() > 0) {

			StringEntity strEntity = new StringEntity(jsonStr,"UTF-8");
			strEntity.setContentEncoding("UTF-8");
			httpPostReq.setEntity(strEntity);
		}
		HttpResponse resp = httpClient.execute(httpPostReq);

		if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			responseDate = EntityUtils.toString(resp.getEntity());

			// logger.info(result.toString());
		} else {
			logger.info("http client request fail..StatusCode:"
					+ resp.getStatusLine().getStatusCode());
		}

		httpPostReq.releaseConnection();
		httpClient = null;
		return responseDate;
	}

	/**
	 * httpclient Get
	 * 
	 * @param uri
	 * @param params
	 * @return
	 */
	/*public String getUrl(String url, Map<String, Object> params) {
		String jsonStr = "";
		String uri=url.trim();
		if (null != params) {
			String paraStr = StringUtil.mapToUrlStr(params);
			if (paraStr.length() > 2) {
				String tempPara = paraStr.substring(1, paraStr.length());
				uri = uri + "?" + tempPara;
			}
		}
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		logger.debug("-------------------------uri:" + uri);
		HttpGet httpGetReq = new HttpGet(uri);
		// -------
		Builder rconfigbu = RequestConfig.custom();
		rconfigbu.setSocketTimeout(30000);
		rconfigbu.setConnectTimeout(5000);
		RequestConfig rconfig = rconfigbu.build();
		httpGetReq.setConfig(rconfig);

		// httpGetReq.setHeader(name, value);

		// --------End----------
		try {
			CloseableHttpResponse resp = httpClient.execute(httpGetReq);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// Read the response body.
				HttpEntity entity = resp.getEntity();
				String tmpJsonStr = EntityUtils.toString(entity);
				if(null!=tmpJsonStr && tmpJsonStr.trim().length()>0)
				{
					String newStr=new String(tmpJsonStr.getBytes("ISO-8859-1"), "UTF-8");
					jsonStr=newStr;
				}
				
			} else {
				logger.warn("http client request fail..StatusCode:"
						+ resp.getStatusLine().getStatusCode());
			}

		} catch (Exception e) {
			logger.error("TAG", e);
		} finally {
			httpGetReq.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("httpClient close error.", e);
			}
			httpClient = null;
		}
		return jsonStr;
	}*/
	/***
	 * 
	 * @deprecated：通过get请求 实现远程下载文件，可下载二进制流文件
	 * @author:szyr-huangshengming
	 * @date:2015-8-28 上午9:06:52
	 */ 
	public static boolean  downLoadUrlFile(String strUrl, HttpServletResponse response)
			throws Exception {
		InputStream is = null;
		OutputStream os = null;
		try {
			// 取到文件名
			String fileName = strUrl.substring(strUrl.lastIndexOf("/") + 1);
			logger.debug("downFile url>>:"+strUrl);
			HttpURLConnection urlConn = getHttpURLConnection(strUrl);
			if (ishasExitsUrlFile(strUrl)) {
				// 远程获取到文件流
				is = urlConn.getInputStream();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("multipart/form-data");
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + fileName);
				os = response.getOutputStream();
				byte[] b = new byte[1024 * 5];
				int length = 0;
				while ((length = is.read(b)) > 0) {
					os.write(b, 0, length);
				}
				logger.warn("downFile [" + fileName + "] is Success ..");
				return true;
			}
			else
			{
				logger.error("DownloadFile  not found....");
				return false;
			}
			
		} catch (Exception e) {
			
			return false;
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
	}

	/***
	 * 
	 * @功能描述：获得HttpURLConnection对象
	 * @author:szyr-huangshengming
	 * @date:2015-8-28 上午10:50:48
	 * 
	 */
	private static HttpURLConnection getHttpURLConnection(String strUrl) {
		HttpURLConnection urlConn = null;
		try {
			URL url = new URL(strUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(3000);
			urlConn.setReadTimeout(10000);
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlConn;
	}
	/**
	 * 
	 * @功能描述：查看远程文件是否存在
	 * @author:szyr-huangshengming
	 * @date:2015-8-28 上午10:50:11
	 * 
	 */
	public static boolean ishasExitsUrlFile(String url) {
		HttpURLConnection urlConnection = getHttpURLConnection(url);
		// 获取到远程文件的大小
		int size = urlConnection.getContentLength();
		return size > 0 ? true : false;
	}
	
	/**
	 * get获取输入流，适用于获取微信二维码
	 * @return
	 */
	public InputStream getInputStream(String url){
		InputStream inputStream=null;
		HttpURLConnection urlConnection = getHttpURLConnection(url);
		int responseCodeCode=0;
		try {
			responseCodeCode = urlConnection.getResponseCode();
			if(responseCodeCode==200){
				inputStream=urlConnection.getInputStream();
			}
		} catch (IOException e) {
			logger.error("faild to get inputStream，url:"+url,e);
		}
		return inputStream;
	}
}
