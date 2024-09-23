package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<ModelList>> list = jsonPlaceHolder.getModel();


        list.enqueue(new Callback<List<ModelList>>() {
            @Override
            public void onResponse(Call<List<ModelList>> call, Response<List<ModelList>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ModelList> modelList=response.body();
                Log.d(MainActivity.class.getSimpleName(),"chy1"+modelList);
                Adapter chyAdapter= new Adapter(modelList, MainActivity.this);
                recyclerView.setAdapter(chyAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelList>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}