package Index;

import java.lang.reflect.InvocationTargetException;

public class LocationXml extends Xml{
    private String location_X = null;
    private String location_Y = null;
    private String scale = null;
    private String label = null;
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

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static Xml setPropertyByXml(String xml){
        String[] elemArr = {"Location_X","Location_Y","Scale","Label"};
        boolean[] isCDataArr = {false,false,false,true};
        LocationXml locationXml = new LocationXml();
        locationXml.updateElemArr(elemArr);
        locationXml.updateIsCdataArr(isCDataArr);
        try {
            Xml.setPropertyByXml(xml,locationXml);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return locationXml;
    }
}
