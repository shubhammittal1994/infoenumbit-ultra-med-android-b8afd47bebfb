package com.soccermat.ultramed.connection;

import com.soccermat.ultramed.models.GetAllExerciseCategory;
import com.soccermat.ultramed.models.LoginResponse;
import com.soccermat.ultramed.models.RegisterResponse;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("messages")
    Call<ResponseBody> sendEmail(
            @Field("from") String from,
            @Field("to") String to,
            @Field("subject") String subject,
            @Field("html") String text
    );
    @FormUrlEncoded
 @POST("/api/register")
    Call<RegisterResponse> registerUser(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("country") String country,@Field("city") String city,
                                        @Field("email") String email ,@Field("password") String password, @Field("device_type") String deviceType,
                                        @Field("device_token") String deviceToken,@Field("zipcode") String zipcode);


    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("password") String password);



    @GET("/api/logout")
    Call<Void> logoutUser(@Header("authorization") String token);

    @GET("api/all_exercise_category")
    Call<GetAllExerciseCategory> getAllExerciseCategory(@Header("authorization") String token);


    @GET("api/get_category_exercise/{id}")
    Call<GetAllExerciseCategory> getAllExerciseCategory(@Header("authorization") String token,@Path("id") int id);



//    @POST("/api/register")
//    Call<UserList> logoutUser(@Field("name") String name, @Field("job") String job);


}