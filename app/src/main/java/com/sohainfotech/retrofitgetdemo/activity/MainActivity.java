package com.sohainfotech.retrofitgetdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.sohainfotech.retrofitgetdemo.R;
import com.sohainfotech.retrofitgetdemo.adapter.PhotoAdapter;
import com.sohainfotech.retrofitgetdemo.model.PhotoModel;
import com.sohainfotech.retrofitgetdemo.network.Api;
import com.sohainfotech.retrofitgetdemo.network.RetrofitServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//step8:

public class MainActivity extends AppCompatActivity {
    private PhotoAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callPhotoApi();
    }

    private void callPhotoApi() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //Create handler for the RetrofitInstance interface
        Api api = RetrofitServices.getInstance().getApi();

        Call<List<PhotoModel>> call = api.getAllPhotos();
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    generatePhotoList(response.body());
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generatePhotoList(List<PhotoModel> photoList) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PhotoAdapter(MainActivity.this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}