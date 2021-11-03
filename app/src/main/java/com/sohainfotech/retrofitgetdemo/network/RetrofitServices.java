package com.sohainfotech.retrofitgetdemo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//step4: To issue network requests to a REST API with Retrofit
public class RetrofitServices {
    private static RetrofitServices retrofitServices;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit retrofit;

    //create Retrofit instance
    private RetrofitServices() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //get Retrofit instance
    public static RetrofitServices getInstance() {
        if (retrofitServices == null) {
            retrofitServices = new RetrofitServices();
        }
        return retrofitServices;
    }

    //get api access
    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
