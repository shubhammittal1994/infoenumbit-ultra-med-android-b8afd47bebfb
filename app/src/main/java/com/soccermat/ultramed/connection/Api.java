package com.soccermat.ultramed.connection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("messages")
    Call<ResponseBody> sendEmail(
            @Field("from") String from,
            @Field("to") String to,
            @Field("subject") String subject,
            @Field("html") String text
    );


 @POST("/api/register")
    Call<UserList> registerUser(@Field("name") String name, @Field("job") String job);



    @POST("/api/register")
    Call<UserList> loginUser(@Field("name") String name, @Field("job") String job);

    @POST("/api/register")
    Call<UserList> logoutUser(@Field("name") String name, @Field("job") String job);


}