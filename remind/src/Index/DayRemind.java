package Index;

import Bean.User;
import Dao.UserDao;
import Tools.Data;
import Tools.MyUrl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DayRemind {
    public static void index(){
        UserDao userDao = new UserDao();
        try {
            List<User> userList = userDao.getTodaySchedule(true);
            for (int i = 0; i < userList.size(); i++) {
                sendRemind(userList.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //对每一个用户发送提醒消息
    public static void sendRemind(User user)
    {
        String todaySchedule = user.getTodaySchedule();
        JSONObject todaySchJson = JSONObject.parseObject(todaySchedule);
        int size = todaySchJson.size();
        if( size == 0)
        {
            return;
        }
        HashMap<String, Object> sendContent = new HashMap<>();
        sendContent.put("touser",user.getOpenId());
        sendContent.put("template_id", Data.getRemindTitleMsgTmpId());
        HashMap<String, HashMap> dataMag = new HashMap<>();
        dataMag.put("count",new HashMap<String,Integer>(){{
            put("value", size);
        }});
        sendContent.put("data",dataMag);
        MyUrl.sendTemplateMsg(JSON.toJSONString(sendContent));  //发送当日提醒的头部信息
        Set<String> keys = todaySchJson.keySet();

        HashMap<String, Object> classMag = new HashMap<>();
        for (String key : keys) {
            classMag.put("touser",user.getOpenId());
            classMag.put("template_id", Data.getRemindContentMsgTmpId());
            HashMap<String, HashMap> simpleClassMag = new HashMap<>();
            simpleClassMag.put("courses",new HashMap<String,String>(){{
                put("value",todaySchJson.getJSONObject(key).getString("subject"));
            }});
            simpleClassMag.put("classroom",new HashMap<String,String>(){{
                put("value",todaySchJson.getJSONObject(key).getString("classroom"));
            }});
            simpleClassMag.put("time",new HashMap<String,String>(){{
                put("value",Data.getWhichTime(Integer.valueOf(key)));
            }});
            classMag.put("data",simpleClassMag);
            String res = MyUrl.sendTemplateMsg(JSON.toJSONString(classMag));
            classMag.clear();
        }
    }
}
