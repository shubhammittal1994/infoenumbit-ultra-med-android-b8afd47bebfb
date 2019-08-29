package com.soccermat.ultramed.connection;

import android.util.Base64;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

   // private static final String BASE_URL = "https://api.mailgun.net/v3/sandbox38f54f88940347c8a239c1ca1861e68f.mailgun.org/";
    private static final String BASE_URL = "https://api.mailgun.net/v3/www.ultramed.mx/";

    private static final String API_USERNAME = "api";

    //you need to change the value to your API key
    // private static final String API_PASSWORD = "44905ff62baa4d0daae1bc74bd453324-c8e745ec-4c575de8";
  //  private static final String API_PASSWORD = "c5a0c3c7f2dfe2f1b37b056723209bb2-4836d8f5-1c7e2eca";
    private static final String API_PASSWORD = "44905ff62baa4d0daae1bc74bd453324-c8e745ec-4c575de8";

    private static final String AUTH = "Basic " + Base64.encodeToString((API_USERNAME + ":" + API_PASSWORD).getBytes(), Base64.NO_WRAP);

    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    public static void configClient() {
        httpClient.connectTimeout(30, TimeUnit.MINUTES);
        httpClient.readTimeout(30, TimeUnit.MINUTES);

        // BASE_API_URL = Constants.baseUrl;

    }

    public static void addLoggingIfNeeded() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);
    }


    private RetrofitClient() {


        //   addLoggingIfNeeded();
        //  configClient();

     /*   Gson gson = new GsonBuilder()
                .setLenient()
                .create();
*/
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                //Adding basic auth
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Authorization", AUTH)
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Retrofit getClient() {
        return retrofit;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
