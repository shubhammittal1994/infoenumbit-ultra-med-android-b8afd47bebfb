package com.soccermat.ultramed.connection;

import com.soccermat.ultramed.models.DataDTO;
import com.soccermat.ultramed.models.ExersiesResultRequest;
import com.soccermat.ultramed.models.GetCategoryExerciseWithID;
import com.soccermat.ultramed.models.GetExercieseResponse;

import com.soccermat.ultramed.models.RegisterResponse;
import com.soccermat.ultramed.models.ResponseDTO;
import com.soccermat.ultramed.models.ResponseGetCategoriesResponse;
import com.soccermat.ultramed.models.StatusClass;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @FormUrlEncoded
 @POST("/api/register")
    Call<RegisterResponse> registerUser(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("country") String country,@Field("city") String city,
                                        @Field("email") String email ,@Field("password") String password, @Field("device_type") String deviceType,
                                        @Field("device_token") String deviceToken,@Field("zipcode") String zipcode);


    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseDTO> loginUser(@Field("email") String email, @Field("password") String password);



    @GET("/api/logout")
    Call<Void> logoutUser(@Header("authorization") String token);


    @POST("api/all_exercise_category")
    Call<ResponseGetCategoriesResponse> getAllExerciseCategory(@Header("authorization") String token, @Body ExersiesResultRequest user_id);




    @FormUrlEncoded
    @POST("api/get_category_exercise")
    Call<GetExercieseResponse> getCategoryExercise(@Header("authorization") String token ,@Field("user_id") String user_id,@Field("category_id") String category_id);


    @FormUrlEncoded
    @POST("api/change_status_category")
    Call<StatusClass> setStatus(@Header("authorization") String token , @Field("user_id") String user_id, @Field("category_id") String category_id, @Field("status") int status);


//    @POST("/api/register")
//    Call<UserList> logoutUser(@Field("name") String name, @Field("job") String job);


}