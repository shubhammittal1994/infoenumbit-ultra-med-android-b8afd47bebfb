package com.soccermat.ultramed.connection;

import com.soccermat.ultramed.models.RegisterResponse;
import com.soccermat.ultramed.models.loginResponse;

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
    Call<RegisterResponse> registerUser(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("country") String country,@Field("city") String city,
                                        @Field("email") String email ,@Field("password") String password, @Field("device_type") String deviceType,
                                        @Field("device_token") String deviceToken,@Field("zipcode") String zipcode);


    @FormUrlEncoded
    @POST("http://codeshades.com/api/login")
    Call<loginResponse> loginUser(@Field("email") String email, @Field("password") String password);

//    @POST("/api/register")
//    Call<UserList> logoutUser(@Field("name") String name, @Field("job") String job);


}