package Index;

import java.lang.reflect.InvocationTargetException;

public class LinkXml extends Xml {
    private String title = null;
    private String description = null;
    private String url = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setToUserName(String toUserName) {
        super.setToUserName(toUserName);
    }

    @Override
    public void setMsgType(String msgType) {
        super.setMsgType(msgType);
    }

    @Override
    public void setCreateTime(String createTime) {
        super.setCreateTime(createTime);
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    public void setFromUserName(String fromUserName) {
        super.setFromUserName(fromUserName);
    }
    public static Xml setPropertyByXml(String xml){
        String[] elemArr = {"Title","Description","Url"};
        boolean[] isCDataArr = {true,true,true};
        LinkXml linkXml = new LinkXml();
        linkXml.updateElemArr(elemArr);
        linkXml.updateIsCdataArr(isCDataArr);
        try {
            Xml.setPropertyByXml(xml,linkXml);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return linkXml;
    }
}
