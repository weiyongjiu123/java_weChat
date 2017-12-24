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

public class BeforeRemind {
    /**
     *
     * @param which 哪一个时间段
     */
    public static void index(String which)
    {
        UserDao userDao = new UserDao();
        try {
            List<User> userList = userDao.getTodaySchedule(false);
            for (int i = 0; i < userList.size(); i++) {
                sendBeforeRemind(userList.get(i),which);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void sendBeforeRemind(User user, String which)
    {
        String todaySchedule = user.getTodaySchedule();
        JSONObject todaySchJson = JSONObject.parseObject(todaySchedule);
        JSONObject thisClass = todaySchJson.getJSONObject(which);//这节课
        if(thisClass != null)
        {
            HashMap<String, Object> sendContent = new HashMap<>();
            sendContent.put("touser",user.getOpenId());
            sendContent.put("template_id", Data.getBeforeSchRemTmpId());
            HashMap<String, HashMap> dataMag = new HashMap<>();
            dataMag.put("classroom",new HashMap<String,String>(){{
                put("value",thisClass.getString("classroom"));
            }});
            dataMag.put("courses",new HashMap<String,String>(){{
                put("value",thisClass.getString("subject"));
            }});
            dataMag.put("time",new HashMap<String,String>(){{
                put("value",Data.getWhichTime(Integer.valueOf(which)));
            }});
            sendContent.put("data",dataMag);
            MyUrl.sendTemplateMsg(JSON.toJSONString(sendContent));
        }
    }
}
