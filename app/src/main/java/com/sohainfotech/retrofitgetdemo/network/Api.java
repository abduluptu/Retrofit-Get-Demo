package com.sohainfotech.retrofitgetdemo.network;

//step5: The endpoints are defined inside of an interface using special retrofit annotations
// to encode details about the parameters and request method.

import com.sohainfotech.retrofitgetdemo.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/photos")
    Call<List<PhotoModel>> getAllPhotos();

}
