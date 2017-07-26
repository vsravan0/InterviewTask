package com.nisha.interviewtask.apputils;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface WebServiceAPI {


    /*
      map.put("channel",request.getChannel());
        map.put("text",request.getText());
        map.put("username",request.getUserName());
        map.put("Name",request.getName());
        map.put("Latitude",""+request.getLatitude());
        map.put("Longitude",""+request.getLongitude());
        map.put("icon_emoji",""+request.getIconEmoji());
     */
    @POST("T042FMKHH/B1LF1T12L/HoTvYOUSovFpfl9LI59GBKnq")
    @FormUrlEncoded
    Call<Void> sendLocation(@Field("channel") String channel,
                            @Field("text") String text,
                            @Field("username") String userName,
                            @Field("Name") String name,
                            @Field("Latitude") String latitude,
                            @Field("Longitude") String longitude,
                            @Field("icon_emoji") String iconEmoji);


}

