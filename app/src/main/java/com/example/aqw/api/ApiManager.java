package com.example.aqw.api;

import android.util.Log;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, String> nomsExercices() throws IOException {
        Map<String, String> output = new HashMap<>();
        executorService.submit(() -> {
            try {
                for (JsonElement x : service.allExercices().execute().body().getAsJsonArray()) {
                    output.put(x.getAsJsonObject().get("WorkOut").getAsString(),
                            x.getAsJsonObject().get("Muscles").getAsString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                Log.v(TAG,"Timeout");
                throw new IOException("Connexion à l'api timeout");
            }
            Log.v(TAG,"OK");
        } catch (InterruptedException e) {
            Log.v(TAG,"Erreur");
            throw new RuntimeException(e);
        }
        return output;

    }
}
