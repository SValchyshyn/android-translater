package com.example.stepanv.android_translater.rest;

import com.example.stepanv.android_translater.model.Translation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NewsApiService {
    String API_KEY = "trnsl.1.1.20161227T143428Z.eecd5f02733cd7de.600e2b11371811d23941701a0a019832d74b3154";
    String ENDPOINT = "https://translate.yandex.net/api/v1.5/tr.json/";
    String LANGUAGE = "en-ru";

    @GET("translate")
    Observable<Translation> getTranslation(@Query("lang") String language, @Query("key") String key, @Query("text") String text);
}
