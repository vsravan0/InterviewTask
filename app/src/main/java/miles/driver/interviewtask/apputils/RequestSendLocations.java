package miles.driver.interviewtask.apputils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nisha on 7/23/2017.
 */

public class RequestSendLocations implements Serializable {

    /*
    ={"channer: "#android", "username":
    ',your narne>",
    "text": "Name: <your name> -
    Latitude: <latitude>,
    Longitude: <longitude>.",
    "icon_emor ":ghost:"}
     */
    @SerializedName("channel")
    private String channel;

    @SerializedName("text")
    private String text;

    @SerializedName("username")
    private String userName;

    @SerializedName("Name")
    private String name;

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Longitude")
    private String longitude;

    @SerializedName("icon_emoji")
    private String iconEmoji;

    public Map<String,String> getMapObject(RequestSendLocations request){
        Map<String,String>  map=new HashMap<String, String>();
        map.put("channel",request.getChannel());
        map.put("text",request.getText());
        map.put("username",request.getUserName());
        map.put("Name",request.getName());
        map.put("Latitude",""+request.getLatitude());
        map.put("Longitude",""+request.getLongitude());
        map.put("icon_emoji",""+request.getIconEmoji());
        return map;

    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIconEmoji() {
        return iconEmoji;
    }

    public void setIconEmoji(String iconEmoji) {
        this.iconEmoji = iconEmoji;
    }
}
