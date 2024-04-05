package com.example.aqw.api;

import android.content.Context;
import android.util.Log;

import com.example.aqw.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();
    private ExerciceAPIService service;
    private ExecutorService executorService;
    private Context context;

    public ApiManager(Context context) {
        this.context = context;
        executorService = Executors.newSingleThreadExecutor();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://work-out-api1.p.rapidapi.com/").build();
        service = retrofit.create(ExerciceAPIService.class);
    }

    public Map<String, String> nomsExercices() throws IOException {
        Map<String, String> output = new HashMap<>();
        if (executorService.isShutdown()) {
            executorService = Executors.newSingleThreadExecutor();
        }
        for (JsonElement x : service.allExercices().execute().body().getAsJsonArray()) {
            output.put(x.getAsJsonObject().get("WorkOut").getAsString(),
                    x.getAsJsonObject().get("Muscles").getAsString());
        }
        return output;

    }

    public DetailExercice details(String exercice) throws IOException {
        if (executorService.isShutdown()) {
            executorService = Executors.newSingleThreadExecutor();
        }
        Response<JsonElement> reponse = service.exercice(exercice).execute();
        DetailExercice details = new DetailExercice();
        Log.v(TAG, reponse.toString());
        for(JsonElement elt : reponse.body().getAsJsonArray()) {
            JsonObject x = elt.getAsJsonObject();
            if (x.get("WorkOut").getAsString().equalsIgnoreCase(exercice)) {
                Log.v(TAG, x.get("WorkOut").getAsString());
                details.setNom(x.get("WorkOut").getAsString());
                details.setMuscles(x.get("Muscles").getAsString());
                details.setLevel(x.get("Intensity_Level").getAsString());
                if (!x.get("Equipment").isJsonNull()) {
                    details.setEquipment(x.get("Equipment").getAsString());
                }
                else {
                    details.setEquipment(context.getText(R.string.no_equipment).toString());
                }
                details.setBeginnerSets(x.get("Beginner Sets").getAsString());
                details.setIntermediateSets(x.get("Intermediate Sets").getAsString());
                details.setExpertSets(x.get("Expert Sets").getAsString());
                details.setExplanation(x.get("Explaination").getAsString());
                details.setLongExplanation(x.get("Long Explanation").getAsString());
            }
        }
        return details;
    }
}
