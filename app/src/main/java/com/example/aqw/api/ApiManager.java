package com.example.aqw.api;

import android.util.Log;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();
    private ExerciceAPIService service;
    private ExecutorService executorService;

    public ApiManager() {
        executorService = Executors.newCachedThreadPool();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://work-out-api1.p.rapidapi.com").build();
        service = retrofit.create(ExerciceAPIService.class);
    }

    public List<String> nomsExercices() {
        List<String> output = new ArrayList<>();
        executorService.submit(() -> {
            try {
                for (JsonElement x : service.allExercices().execute().body().getAsJsonArray()) {
                    output.add(x.getAsJsonObject().get("WorkOut").getAsString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.v(TAG,output.toString());
        return output;

    }
}
