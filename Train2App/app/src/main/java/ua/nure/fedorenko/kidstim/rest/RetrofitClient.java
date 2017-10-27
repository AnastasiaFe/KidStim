package ua.nure.fedorenko.kidstim.rest;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://10.23.14.116:8080/kidstim/";
    //private static final String BASE_URL = "http://10.0.2.2:8080/kidstim/";
    private static final String AUTHENTICATION_HEADER = "Authorization";

    private RetrofitClient() {
    }

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            String token = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("Authorization", "");
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(
                            chain -> {
                                Request request = chain.request()
                                        .newBuilder()
                                        .addHeader(AUTHENTICATION_HEADER, token)
                                        .build();
                                return chain.proceed(request);
                            });
            OkHttpClient client = builder.build();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
