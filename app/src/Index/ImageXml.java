package Index;

import java.lang.reflect.InvocationTargetException;

public class ImageXml extends Xml {
    private String picUrl = null;
    private String mediaId = null;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    public static Xml setPropertyByXml(String xml) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] elemArr = {"MediaId","PicUrl"};
        boolean[] isCDataArr = {true,true};
        ImageXml imageXml = new ImageXml();
        imageXml.updateElemArr(elemArr);
        imageXml.updateIsCdataArr(isCDataArr);
        Xml.setPropertyByXml(xml,imageXml);
        return imageXml;
    }
}
