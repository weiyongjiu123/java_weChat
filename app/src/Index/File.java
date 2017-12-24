package Index;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class File {
    public static void write(String content)
    {
        String time = String.valueOf((System.currentTimeMillis() / 1000));
//        java.io.File abc = new java.io.File("javaLog.txt");
        String filePath ="/root/javaApp/weChatPublic/javaApp3/javaLog.txt";

        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("-------------------"+time+"------------------------\r\n");
            bw.append(content);
            bw.write("\r\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
