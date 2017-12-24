package Index;

import java.lang.reflect.InvocationTargetException;

public class Xml {
    private String FromUserName;
    private String toUserName;
    private String Content;
    private String  CreateTime;
    private String MsgType;
    static private String[] elemArr = {"FromUserName","ToUserName","Content","MsgType","CreateTime"};
    static private boolean isCdataArr[] = {true,true,true,true,false};

    public void updateElemArr(String[] addElemArr) {
//        String arr = this.elemArr;
        String[] arr = new String[elemArr.length+addElemArr.length];
        for (int i = 0; i <elemArr.length; i++) {
            arr[i] = elemArr[i];
        }
        for (int i = 0; i < addElemArr.length; i++) {
            arr[elemArr.length + i] = addElemArr[i];
        }
        elemArr = arr;
    }
    public static String[] getElement()
    {
        return elemArr;
    }

    public static boolean[] getIsCdataArr() {
        return isCdataArr;
    }

    public void updateIsCdataArr(boolean[] AddIsCdataArr) {
        boolean[] arr = new boolean[isCdataArr.length+AddIsCdataArr.length];
        for (int i = 0; i < isCdataArr.length; i++) {
            arr[i] = isCdataArr[i];
        }
        for (int i = 0; i < AddIsCdataArr.length; i++) {
            arr[isCdataArr.length + i] = AddIsCdataArr[i];
        }
        isCdataArr = arr;
    }


    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public static void setPropertyByXml(String xml,Xml obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        String[] elemArr = {"FromUserName","ToUserName","Content","MsgType","CreateTime"};
//        boolean isCdataArr[] = {true,true,true,true,false};
        Tools.xmlSetObjPro(obj,xml,elemArr,isCdataArr);
    }

}
