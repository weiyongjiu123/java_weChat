package Index;

import Bean.User;
import Dao.UserDao;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class DoEvent {
    public static void doSubscribe(String fromUsername){
        try {
            UserDao userDao = new UserDao();
            boolean hasUser = userDao.isHasUser(fromUsername);      //是否用户已经存在
            if(!hasUser)                                            //没有该用户需要添加
            {
                userDao.addUser(fromUsername);       //添加用户
            }else{
                userDao.doSubscribe(fromUsername,"1");//设置用户处于订阅状态
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String res = WeChat.sendWelcome(fromUsername);
    }
    //设置当天提醒为开启状态
    public static void setDayRemindOpen(EventXml eventXml, HttpServletResponse response){
        UserDao userDao = new UserDao();
        int res = userDao.setRemind(eventXml.getFromUserName(), "dayRemind",1);
        String content;
        if(res == 1)
        {
            content = "当日提醒已开启";
        }else{
            content = "当日提醒开启失败";
        }
        Tools.sendTextMsg(eventXml, content, response);
//        System.out.println(eventXml.getEventKey());
    }
    //设置课前提醒为开启状态setDayRemindClose
    public static void setDayRemindClose(EventXml eventXml, HttpServletResponse response){
        UserDao userDao = new UserDao();
        int res = userDao.setRemind(eventXml.getFromUserName(), "dayRemind", 0);
        String content;
        if(res == 1)
        {
            content = "当日提醒已关闭";
        }else{
            content = "当日提醒关闭失败";
        }
        Tools.sendTextMsg(eventXml,content,response);
    }
    //设置当天提醒为关闭状态
    public static void setBeforeRemindOpen(EventXml eventXml, HttpServletResponse response){
        UserDao userDao = new UserDao();
        int res = userDao.setRemind(eventXml.getFromUserName(), "beforeRemind", 1);
        String content;
        if(res == 1)
        {
            content = "课前提醒已开启";
        }else{
            content = "课前提醒开启失败";
        }
        Tools.sendTextMsg(eventXml,content,response);
    }
    //设置课前提醒为关闭状态
    public static void setBeforeRemindClose(EventXml eventXml, HttpServletResponse response){
        UserDao userDao = new UserDao();
        int res = userDao.setRemind(eventXml.getFromUserName(), "beforeRemind", 0);
        String content;
        if(res == 1)
        {
            content = "课前提醒已关闭";
        }else{
            content = "课前提醒关闭失败";
        }
        Tools.sendTextMsg(eventXml,content,response);
    }
    //获取所有提醒的状态
    public static void getRemindStatus(EventXml eventXml, HttpServletResponse response){
        UserDao userDao = new UserDao();
        String content = "";
        try {
            List<User> userlist = userDao.getRemindStatus(eventXml.getFromUserName());
            User user = userlist.get(0);
            content += "当天提醒状态：";
            if(user.getDayRemind().equals("1"))
            {
                content += "开启，";
            }else{
                content += "关闭，";
            }
            content += "课前提醒状态：";
            if(user.getBeforeRemind().equals("1"))
            {
                content += "开启";
            }else{
                content += "关闭";
            }
        } catch (SQLException e) {
           content = "查询提醒状态失败";
            e.printStackTrace();
        }
        Tools.sendTextMsg(eventXml,content,response);
    }
    //获取课表信息
    public static void getSchedule(EventXml eventXml, HttpServletResponse response){
        String content = "发送“s:第几周”，如“s:13”,就可以获取第13周课程信息。";
        Tools.sendTextMsg(eventXml,content,response);
    }

    public static void doUnSubscribe(String fromUserName) {
        try {
            new UserDao().doSubscribe(fromUserName,"0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
