package Index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class WeChat {
    private static String appId = "wx730b7f9a60839346";
    private static String appSecret = "ecd6e74ad59d995a23ebe19cc5dc814f";
    private static String accessToken = null;
    private static long acceccTokenTime = 0;

    public static String getAccessToken()
    {
        if(acceccTokenTime < System.currentTimeMillis() / 1000){
            setAccessToken();
        }
        return accessToken;
    }
    public static String get(String urlString)
    {
        URL url;
        int responsecode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String line;
        try{
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url=new URL(urlString);
            //打开URL
            urlConnection = (HttpURLConnection)url.openConnection();
            //获取服务器响应代码
            responsecode=urlConnection.getResponseCode();
            if(responsecode==200){
                String str = "";
                //得到输入流，即获得了网页的内容
                reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((line=reader.readLine())!=null){
                    str += line;
                }
                reader.close();
                return str;
            }
        }
        catch(Exception e){
            System.out.println("获取不到网页的源码,出现异常："+e);
        }
        return "";
    }
    private static String  post(String remoteUrl,String content)
    {
        URL url = null;
        HttpURLConnection http = null;

        try {
            url = new URL(remoteUrl);
            http = (HttpURLConnection) url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(50000);//设置连接超时
//如果在建立连接之前超时期满，则会引发一个 java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setReadTimeout(50000);//设置读取超时
//如果在数据可读取之前超时期满，则会引发一个 java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setRequestMethod("POST");
            // http.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.connect();


            OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            osw.write(content);
            osw.flush();
            osw.close();

            if (http.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
                String inputLine;
                String result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                in.close();
                return result;

            }
        } catch (Exception e) {
            System.out.println("err");
        } finally {
            if (http != null) http.disconnect();
        }
        return "";
    }
    private static void setAccessToken()
    {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
        String result = post(url, "");
        JSONObject jsonObject = JSONObject.parseObject(result);
        String access_token = (String)jsonObject.get("access_token");
        if(!access_token.equals(null)){
            accessToken = access_token;
            acceccTokenTime = System.currentTimeMillis() / 1000 + (int)jsonObject.get("expires_in");
        }
    }
    private static String sendTemplateMsg(String url,String content)
    {
        return post(url, content);
    }
    public static String sendWelcome(String toUsername)
    {
        HashMap<String, Object> firtst = new HashMap<>();
        firtst.put("touser",toUsername);
        firtst.put("template_id",Data.getSendWelComeTemId());
        String jsonStr = JSON.toJSONString(firtst, true);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken();
        return sendTemplateMsg(url,jsonStr);
    }
    public static String sendScheduleMsg(String content){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken();
        return sendTemplateMsg(url,content);
    }
}
