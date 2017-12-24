package Index;

import java.lang.reflect.InvocationTargetException;

public class EventXml extends Xml {
    private String event;
    private String eventKey;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

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
    public static Xml setPropertyByXml(String xml){
        String[] elemArr = {"Event","EventKey"};
        boolean[] isCDataArr = {true,true};
        EventXml eventXml = new EventXml();
        eventXml.updateElemArr(elemArr);
        eventXml.updateIsCdataArr(isCDataArr);
        try {
            Xml.setPropertyByXml(xml,eventXml);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return eventXml;
    }
}
