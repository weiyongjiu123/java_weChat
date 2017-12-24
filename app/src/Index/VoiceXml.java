package Index;

import java.lang.reflect.InvocationTargetException;

public class VoiceXml  extends Xml{
    private String mediaId = null;
    private String format = null;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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
        String[] elemArr = {"MediaId","Format"};
        boolean[] isCDataArr = {true,true};
        VoiceXml voiceXml = new VoiceXml();
        voiceXml.updateElemArr(elemArr);
        voiceXml.updateIsCdataArr(isCDataArr);
        try {
            Xml.setPropertyByXml(xml,voiceXml);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return voiceXml;
    }
}
