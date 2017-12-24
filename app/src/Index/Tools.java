package Index;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static void xmlSetObjPro(Object object,String xml,String[] elemArr,boolean[] isCdataArr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Pattern compile = null;
        Matcher matcher = null;
        String str = null;
        for (int i = 0; i < elemArr.length; i++) {
            if (isCdataArr[i]) {
                str = "<" + elemArr[i] + "><!\\[CDATA\\[([\\S\\s]*)]]></" + elemArr[i] + ">";
            } else {
                str = "<" + elemArr[i] + ">([\\S\\s]*)</" + elemArr[i] + ">";
            }
            compile = Pattern.compile(str);
            matcher = compile.matcher(xml);
            if (matcher.find()) {
//                obj.getClass().getDeclaredMethod
                Method declaredMethod = object.getClass().getDeclaredMethod("set" + elemArr[i], String.class);
                declaredMethod.invoke(object,matcher.group(1));
            }
        }
    }
    public static Object mapGet(HashMap<String,String> map,String key)
    {
        Set<String> keySet = map.keySet();
        for (String k : keySet) {
            if(k.equals(key))
            {
                return map.get(k);
            }
        }
        return "";
    }

    public static String getTextXml(TextXml textXml)
    {
        String xml = "<xml>" +
                "<ToUserName><![CDATA["+textXml.getToUserName()+"]]></ToUserName>" +
                "<FromUserName><![CDATA["+textXml.getFromUserName()+"]]></FromUserName>" +
                "<CreateTime>"+textXml.getCreateTime()+"</CreateTime>" +
                "<MsgType><![CDATA["+textXml.getMsgType()+"]]></MsgType>" +
                "<Content><![CDATA["+textXml.getContent()+"]]></Content>" +
                "</xml>";
        return xml;
    }
    public static void sendTextMsg(Xml xml,String content,HttpServletResponse response)
    {
        TextXml textXml = new TextXml();
        textXml.setToUserName(xml.getFromUserName());
        textXml.setFromUserName(xml.getToUserName());
        textXml.setCreateTime(String.valueOf((System.currentTimeMillis() / 1000)));
        textXml.setMsgType("text");
        textXml.setContent(content);
        try {
            response.getWriter().write(Tools.getTextXml(textXml));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
