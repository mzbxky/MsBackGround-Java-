package com.ruoyi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientUtil {
    //把对象转为map
    public static Map<String,String> getObject(Object o){
        if (o!=null){
            return JSON.parseObject(JSON.toJSONString(o),new TypeReference<Map<String,String>>(){});
        }
        return null;
    }
    //发送请求获取JSON类型的数据
    public static JSONObject doPost(String url, Map<String, String> paramMap) {
        //创建Http实例
        //创建HttpPost实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            //模拟表单上传文件(多部分实体构建)
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //设置编码集
            builder.setCharset(StandardCharsets.UTF_8);
            //声明表单形式
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //表单中参数
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                //mimeType:媒体类型
                builder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.create("application/json", Consts.UTF_8)));
            }
            //输入流的内容封装类
            HttpEntity entity = builder.build();
            //设置请求参数
            httpPost.setEntity(entity);
            if (paramMap.get("token") != null) {
                httpPost.setHeader("token", paramMap.get("token"));
            }
            HttpResponse response = httpClient.execute(httpPost);//执行提交
            //获取返回的状态栏的状态码
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JSONObject datas = JSONObject.parseObject(res);//转换成JSON格式
                //获取接口中返回的状态码
                Integer status = (Integer) datas.get("code");
                //返回的状态
                if (status == 0) {
                    JSONObject data;
                    //获取结果
                    if (datas.get("Data") == null) {
                        data = JSONObject.parseObject(datas.get("data").toString());
                    } else {
                        data = JSONObject.parseObject(datas.get("Data").toString());
                    }
                    //返回的数据就是我需要去解析的
                    return data;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //发送get请求方式
    public static JSONObject doGet(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response;
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String,String> entry:paramMap.entrySet()){
                builder.setParameter(entry.getKey(),entry.getValue());
            }
            // 执行get请求
            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader("token",paramMap.get("token"));
            httpGet.setHeader("Content-Type","application/json");
            response = httpClient.execute(httpGet);
            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String jsonString= EntityUtils.toString(entity, StandardCharsets.UTF_8);
                JSONObject jsonObject=JSONObject.parseObject(jsonString);
                JSONObject data ;
                if (jsonObject.get("Data") == null){
                    data = JSONObject.parseObject(jsonObject.get("data").toString());
                }else {
                    data = JSONObject.parseObject(jsonObject.get("Data").toString());
                }
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
