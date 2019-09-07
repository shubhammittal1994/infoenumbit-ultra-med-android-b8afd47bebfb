package com.soccermat.ultramed.connection;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Base64;

import com.soccermat.ultramed.R;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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
    private static Retrofit retrofit;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();


   public static   Api getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(90, TimeUnit.SECONDS).connectTimeout(90, TimeUnit.SECONDS).build();


       Retrofit retrofitForAPi = new Retrofit.Builder()
                .baseUrl("http://codeshades.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofitForAPi.create(Api.class);

    }

    public static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
  /*  public static void configClient() {
        httpClient.connectTimeout(30, TimeUnit.MINUTES);
        httpClient.readTimeout(30, TimeUnit.MINUTES);

        // BASE_API_URL = Constants.baseUrl;

    }

    public static void addLoggingIfNeeded() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);
    }*/


   /* private SSLContext trustCert(Context context) throws CertificateException,IOException, KeyStoreException,
            NoSuchAlgorithmException, KeyManagementException {
       // AssetManager assetManager = context.getResources().openRawResource(R.raw.xrentycom);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate ca = cf.generateCertificate(assetManager.open("COMODORSADomainValidationSecureServerCA.crt"));

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        SSLContext contextSSl = SSLContext.getInstance("TLS");
        contextSSl.init(null, tmf.getTrustManagers(), null);
        return contextSSl;
    }*/
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

   /* public Retrofit getClient() {
        return retrofit;
    }*/

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
