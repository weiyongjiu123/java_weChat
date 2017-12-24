package Tools;


import Bean.Day;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MyFile {
//    private final static String confPath=System.getProperty("user.dir")+"/conf.txt";
    private final static String confPath="/root/javaApp/weChatPublic/crontab/conf.txt";
    public void write(String content)
    {
        String filePath =confPath;
        try {
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(content);
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readConfFile()
    {
        java.io.File file = new java.io.File(confPath);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
       return result.toString();
    }
    private Day setDay(String fileContent) {
        String[] split = fileContent.split("\\|");
        String[] weekArr = split[0].split("=");
        String[] dayArr = split[1].split("=");
        Day day = new Day();
        day.setDay(dayArr[1]);
        day.setWeek(weekArr[1]);
        return day;
    }
    public Day getDayByConf(){
        String fileContent = this.readConfFile();
        return this.setDay(fileContent);
    }

    public void writeConfFile(Day day) {
        String fileContent = "week=" + day.getWeek() + "|day="+ day.getDay();
        this.write(fileContent);
    }
}
