package com.example.aqw.api;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ExerciceAPIService {

    @Headers({
            "X-RapidAPI-Key: d5b72ff36cmsh8b7bb78e4fcda9ap1a9d24jsn1f9aa271e7cd",
            "X-RapidAPI-Host: work-out-api1.p.rapidapi.com"
    })
    @GET("search")
    public Call<JsonElement> allExercices();
}
