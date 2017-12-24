package Index;


import java.lang.reflect.InvocationTargetException;

public class TextXml extends Xml {
    @Override
    public void setFromUserName(String fromUserName) {
        super.setFromUserName(fromUserName);
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    public void setCreateTime(String createTime) {
        super.setCreateTime(createTime);
    }

    @Override
    public void setMsgType(String msgType) {
        super.setMsgType(msgType);
    }

    @Override
    public void setToUserName(String toUserName) {
        super.setToUserName(toUserName);
    }

    public static TextXml setPropertyByXml(String xml) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        TextXml textXml = new TextXml();
        Xml.setPropertyByXml(xml,textXml);
        return textXml;
    }
}
