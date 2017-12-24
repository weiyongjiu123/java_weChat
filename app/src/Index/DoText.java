package Index;

import Bean.User;
import Dao.UserDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @description 处理一些特殊文本信息，如登陆,获取课表信息
 */
public class DoText {
    private static String[] classroomArr ={"oneCla","twoCla","threeCla","fourCla","fiveCla","sixCla","sevenCla","eightCla"};
    private static String[] subjectArr = {"oneCou","twoCou","threeCou","fourCou","fiveCou","sixCou","sevenCou","eightCou"};
    private static String[] day = {"一","二","三","四","五","六","日"};

    public static void sendSchedule(int week, String fromUserName)
    {
        UserDao userDao = new UserDao();
        try {
            List<User> usersList = userDao.getSchedule(fromUserName);
            User user = usersList.get(0);
            JSONObject jsonObject = JSONObject.parseObject(user.getSchedule());
            JSONObject weekSchedule = jsonObject.getJSONObject(String.valueOf(week));
            HashMap<String, HashMap> dataMap = new HashMap<>();
            for(int i=1;i<=7;i++)
            {
                JSONObject daySchedule = weekSchedule.getJSONObject(String.valueOf(i));
                for (int j=1;j<=8;j++)
                {
                    JSONObject thisSchedule = daySchedule.getJSONObject(String.valueOf(j));
                    if (!thisSchedule.getString("classroom").equals("0")) {
                        dataMap.put(classroomArr[j-1],new HashMap<String,String>(){{
                            put("value",thisSchedule.getString("classroom"));
                        }});
                        dataMap.put(subjectArr[j-1],new HashMap<String,String>(){{
                            put("value",thisSchedule.getString("subject"));
                        }});
                    }
                }
                dataMap.put("week",new HashMap<String,String>(){{
                    put("value",String.valueOf(week));
                }});
                int whichDay = i-1;
                dataMap.put("day",new HashMap<String,String>(){{
                    put("value",day[whichDay]);
                }});
                HashMap<String, Object> sendContentMag = new HashMap<String,Object>(){{
                    put("touser",fromUserName);
                    put("template_id",Data.getScheduleMsgTemID());
                    put("data",dataMap);
                }};
                String sendContentStr = JSON.toJSONString(sendContentMag);
                String res = WeChat.sendScheduleMsg(sendContentStr);
                dataMap.clear();
                sendContentMag.clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String doLogin(String fromUsername, String number, String password)
    {
        String content = "";
        try {
            boolean isLogin= new UserDao().isLogin(fromUsername);
            if(isLogin){
                content = "你已经登录过了";
            }else{
                String url = "https://smallsi.com:9503/?number="+number+"&password="+password+"&type=schedule";
                String res = WeChat.get(url);
                JSONObject jsonObject = JSONObject.parseObject(res);
                String error = jsonObject.getString("error");
                if(!error.equals("0"))
                {
                    return "登陆失败";
                }
                String schedule = jsonObject.getString("content");
//                File.write(schedule);
                int row = new UserDao().setSchedule(fromUsername, schedule);
                if(row > 0)
                {
                    int updateRes = new UserDao().setLogin(fromUsername, "1");
                    if(updateRes > 0)
                    {
                        content = "登录成功";
                    }else{
                        content = "登陆失败";
                    }
                }else{
                    content = "登陆失败";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            content = "登陆失败，请稍后再试";
        }
        return content;
    }
}
