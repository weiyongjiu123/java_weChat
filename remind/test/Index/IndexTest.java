package Index;

import Bean.Day;
import Tools.MyFile;
import org.junit.Test;

public class IndexTest {
    @Test
    public void file()
    {
        MyFile myFile = new MyFile();
        Day day = myFile.getDayByConf();
        System.out.println(day.getDay());
        System.out.println(day.getWeek());
    }
    @Test
    public void test()
    {
        Day day = new Day();
        day.setWeek("1");
        day.setDay("1");
        MyFile myFile = new MyFile();
        myFile.writeConfFile(day);
    }
    @Test
    public void user()
    {
        SetDaySch.index();
    }
    @Test
    public void dayRemind()
    {
        DayRemind.index();
    }
    @Test
    public void beforeRemind()
    {
        BeforeRemind.index("1");
    }
}