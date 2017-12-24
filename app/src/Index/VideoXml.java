package Index;

import java.lang.reflect.InvocationTargetException;

public class VideoXml extends Xml{
    private String mediaId = null;
    private String thumbMediaId = null;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
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
        String[] elemArr = {"MediaId","ThumbMediaId"};
        boolean[] isCDataArr = {true,true};
        VideoXml videoXml = new VideoXml();
        videoXml.updateElemArr(elemArr);
        videoXml.updateIsCdataArr(isCDataArr);
        try {
            Xml.setPropertyByXml(xml,videoXml);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return videoXml;
    }

}
