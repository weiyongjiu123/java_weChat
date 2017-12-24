package Tools;

public class Data {
    private final static String remindTitleMsgTmpId = "hfBzFLEcAzqQ9CWncemjsXyEn0nU0fY6_nt-HKzkntk";
    private final static String remindContentMsgTmpId = "zjJSh3BYl_H8TsfJ22v0Du8jwyaN186I98F301aX2uU";
    private final static String beforeSchRemTmpId = "-JoLnDsCL9gw-Cx1jxEU9RcojkyviZQ-0qtZivyzjnk";
    private static String[] time = {
            "09：00~10：20",
            "10：40~12：00",
            "12：30~13：50",
            "14：00~15：20",
            "15：30~16：50",
            "17：00~18：20",
            "19：00~20：20",
            "20：30~21：50"
    };
    public static String getBeforeSchRemTmpId() {
        return beforeSchRemTmpId;
    }

    public static String getRemindTitleMsgTmpId() {
        return remindTitleMsgTmpId;
    }

    public static String getRemindContentMsgTmpId() {
        return remindContentMsgTmpId;
    }
    public static String getWhichTime(int which)
    {
        return time[which - 1];
    }

}
