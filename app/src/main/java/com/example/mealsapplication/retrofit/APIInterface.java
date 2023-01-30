package com.example.mealsapplication.retrofit;

import com.example.mealsapplication.Constants;
import com.example.mealsapplication.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("search.php")
    Call<Root> CALL_APISearchResult(@Query("s") String name);

    @GET("search.php")
    Call<Root> CALL_APISearchByFirst(@Query("f") String name);

    @GET("categories.php")
    Call<Root> CALL_APITopRated();

    @GET("categories.php")
    Call<Root> CALL_APISliderImage();

    @GET("categories.php")
    Call<Root> CALL_APICategories();

    @GET("filter.php")
    Call<Root> CALL_APIMain(@Query("i") String mainIngredient);

    @GET("filter.php")
    Call<Root> CALL_APICategoryFilter(@Query("c") String category);

    @GET("random.php")
    Call<Root> CALL_APIRandom();
}
