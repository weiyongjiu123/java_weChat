package Index;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppIndex {
    public static void replyTextMsg(Xml xml, HttpServletResponse response) {
        TextXml textXml = (TextXml) xml;
        Pattern compile = Pattern.compile("^s:(\\d*)$");
        Matcher matcher = compile.matcher(textXml.getContent());
        String content;
        if(matcher.find())
        {
            int week = Integer.valueOf(matcher.group(1));
            if(week > 0 && week < 18)
            {
                DoText.sendSchedule(week,textXml.getFromUserName());
                return;
            }
        }
        Pattern loginCompile = Pattern.compile("^login:(\\d{10})_([\\S]*$)");
        Matcher loginMatcher = loginCompile.matcher(textXml.getContent());
        if(loginMatcher.find())
        {
            String number = loginMatcher.group(1);
            String password = loginMatcher.group(2);
            content = DoText.doLogin(textXml.getFromUserName(), number, password);
            Tools.sendTextMsg(xml, content, response);
            return;
        }
        content = "你发的是：" + textXml.getMsgType() + "类型的消息，内容是" +
                textXml.getContent();
        Tools.sendTextMsg(xml, content, response);

    }

    public static void replyImageMsg(Xml xml, HttpServletResponse response) {
        ImageXml imageXml = (ImageXml) xml;
        String content = "你发的是：" + imageXml.getMsgType() + "类型的消息，图片路径是" +
                imageXml.getPicUrl() + "，图片消息媒体id：" + imageXml.getMediaId();
        Tools.sendTextMsg(xml, content, response);
    }

    public static void replyVoiceMsg(Xml xml, HttpServletResponse response) {
        VoiceXml voiceXml = (VoiceXml) xml;
        String content = "你发的是：" + voiceXml.getMsgType() + "类型的消息，语音消息媒体id是：" +
                voiceXml.getMediaId() + "，语音格式：" + voiceXml.getFormat();
        Tools.sendTextMsg(xml, content, response);
    }

    public static void replyVideoMsg(Xml xml, HttpServletResponse response) {
        VideoXml videoXml = (VideoXml) xml;
        String content = "你发的是：" + videoXml.getMsgType() + "类型的消息，语音消息媒体id是：" +
                videoXml.getMediaId() + "，视频消息缩略图的媒体id：" + videoXml.getThumbMediaId();
        Tools.sendTextMsg(xml, content, response);
    }

    public static void replyLocationMsg(Xml xml, HttpServletResponse response) {
        LocationXml locationXml = (LocationXml) xml;
        String content = "你发的是：" + locationXml.getMsgType() + "类型的消息，地理位置维度：" +
                locationXml.getLocation_X() + "，地理位置经度：" + locationXml.getLocation_Y() +
                "，地图缩放大小：" + locationXml.getScale() + "，地理位置信息：" + locationXml.getLabel();
        Tools.sendTextMsg(xml, content, response);
    }

    public static void replyLinkMsg(Xml xml, HttpServletResponse response) {
        LinkXml linkXml = (LinkXml) xml;
        String content = "你发的是：" + linkXml.getMsgType() + "类型的消息，消息标题：" +
                linkXml.getTitle() + "，消息描述：" + linkXml.getDescription() + "，消息链接："
                + linkXml.getUrl();
        Tools.sendTextMsg(xml, content, response);
    }
    public static void replyEventMsg(Xml xml,HttpServletResponse response){
        EventXml eventXml = (EventXml) xml;
        if(eventXml.getEvent().equals("subscribe"))
        {
            DoEvent.doSubscribe(eventXml.getFromUserName());
            return;
        }
        if(eventXml.getEvent().equals("unsubscribe"))
        {
            DoEvent.doUnSubscribe(eventXml.getFromUserName());
            return;
        }
       if(eventXml.getEvent().equals("CLICK"))
       {
           try {
               Class eventClass = Class.forName("Index.DoEvent");
               Object eventObj = eventClass.newInstance();
               Method declaredMethod = eventObj.getClass().getDeclaredMethod(eventXml.getEventKey(), EventXml.class,HttpServletResponse.class);
               declaredMethod.invoke(eventObj,eventXml,response);
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InstantiationException e) {
               e.printStackTrace();
           } catch (NoSuchMethodException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }
           return;
       }
    }
}
