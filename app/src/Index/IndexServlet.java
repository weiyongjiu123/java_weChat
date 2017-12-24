package Index;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "IndexServlet",urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        BufferedReader reader = request.getReader();
        char[] buf = new char[512];
        int len = 0;
        StringBuffer contentBuffer = new StringBuffer();
        while ((len = reader.read(buf)) != -1) {
            contentBuffer.append(buf, 0, len);
        }
        String content = contentBuffer.toString();

        if(content == null){
            content = "";
        }

        Pattern compile = Pattern.compile("<MsgType><!\\[CDATA\\[([\\S\\s]*)]]></MsgType>");
        Matcher matcher = compile.matcher(content);
        if(matcher.find())
        {
            String msgType = matcher.group(1);
            msgType = msgType.substring(0,1).toUpperCase() + msgType.substring(1);
            String xmlClassStr = "Index."+msgType+"Xml";
            try {
                Class xmlClass = Class.forName(xmlClassStr);
                Object xmlObject = xmlClass.newInstance();
                Method setPropertyByXml = xmlObject.getClass().getDeclaredMethod("setPropertyByXml", String.class);
                Xml xml = (Xml)setPropertyByXml.invoke(xmlObject, content);
                //开始调用appIndex的方法
                String appIndexMethod = "reply"+msgType+"Msg";
                Class AppIndex = Class.forName("Index.AppIndex");
                Object obj = AppIndex.newInstance();
                Method declaredMethod = obj.getClass().getDeclaredMethod(appIndexMethod, Xml.class,HttpServletResponse.class);
                declaredMethod.invoke(obj, xml, response);
//                System.out.println(xml.getContent());
                return;
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
        }else{
            return;
        }
        response.getWriter().write("success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String echostr = request.getParameter("echostr");
        response.getWriter().write(echostr);
    }
}
