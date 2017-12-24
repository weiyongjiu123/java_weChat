package Index;

import Bean.Day;
import Bean.User;
import Dao.UserDao;
import Tools.MyFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetDaySch {
    public static void main(String[] argv) throws SQLException {

    }
    public static void updateConf(int week,int day){
        int newDay;
        int newWeek;
        if(day >= 7)
        {
            newDay = 1;
            newWeek = week + 1;
        }else{
            newDay = day+1;
            newWeek = week;
        }
        Day newDayWeek = new Day();
        newDayWeek.setDay(String.valueOf(newDay));
        newDayWeek.setWeek(String.valueOf(newWeek));
        MyFile myFile = new MyFile();
        myFile.writeConfFile(newDayWeek);
    }
    public static void index()
    {
        MyFile myFile = new MyFile();
        Day day = myFile.getDayByConf();
        int iDay = Integer.valueOf(day.getDay());
        int iWeek = Integer.valueOf(day.getWeek());
        if(iWeek > 17 || iWeek < 1)
        {
            return;
        }
        updateConf(iWeek,iDay);
        UserDao userDao = new UserDao();
        try {
            List<User> userList = userDao.getUserList();
            for (int i = 0; i < userList.size(); i++) {
                setTodaySchedule(userList.get(i),userDao,day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setTodaySchedule(User user,UserDao userDao,Day day){
        JSONObject schedule = JSONObject.parseObject(user.getSchedule());
        JSONObject week = schedule.getJSONObject(day.getWeek()); //获取一周的课程
        JSONObject daySch = week.getJSONObject(day.getDay());//获取一天的课程
        HashMap<String, HashMap> todayScheduleMag = new HashMap<>();
        String tmpStr;
        for(int i=1;i<=8;i++)
        {
            tmpStr = String.valueOf(i);
            JSONObject oneClass = daySch.getJSONObject(tmpStr); //获取一节课
            if (!oneClass.getString("classroom").equals("0")) { //提取需要的课程
                Pattern compile = Pattern.compile("^ZX[\\S\\s]*$");
                Matcher isOnlineClass = compile.matcher(oneClass.getString("classroom"));   //判断是否是在线课
                if(!isOnlineClass.find())                       //不要在线课
                {
                    todayScheduleMag.put(tmpStr,new HashMap<String,String>(){{
                        put("classroom",oneClass.getString("classroom"));
                        put("subject",oneClass.getString("subject"));
                    }});
                }
            }
        }
        try {
            userDao.setTodaySchedule(JSON.toJSONString(todayScheduleMag),user.getOpenId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
