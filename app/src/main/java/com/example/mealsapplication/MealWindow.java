package com.example.mealsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealsapplication.adapter.MealsAdapter;
import com.example.mealsapplication.model.Root;
import com.example.mealsapplication.retrofit.APIClient;
import com.example.mealsapplication.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealWindow extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView, recyclerViewFirstLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_window);
        textView = findViewById(R.id.foodTitle);
        imageView = findViewById(R.id.foodImage);
        recyclerView = findViewById(R.id.recyclerView);

        APIInterface api = APIClient.getClient().create(APIInterface.class);

//        String value = getIntent().getStringExtra("searchResult");
//        if (value.length() > 1) {
        searchActivity(api);
//        }
//
//        if (value.length() == 1) {
//            searchByFirst(api);
//        }
//    }


//    private void searchByFirst(APIInterface api) {
//        api.CALL_APISearchByFirst(getIntent().getStringExtra("searchResult")).enqueue(new Callback<Root>() {
//            @Override
//            public void onResponse(Call<Root> call, Response<Root> response) {
//                if (response.isSuccessful()) {
//                    Root root = response.body();
//                    GridLayoutManager layout = new GridLayoutManager(getApplicationContext(), 2);
//                    recyclerView.setLayoutManager(layout);
//                    MealsAdapter object = new MealsAdapter(getApplicationContext(), root);
//                    recyclerView.setAdapter(object);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Root> call, Throwable t) {
//
//            }
//        });
//    }


    }

    private void searchActivity(APIInterface api) {
        api.CALL_APISearchResult(getIntent().getStringExtra("searchResult")).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    MealsAdapter object = new MealsAdapter(getApplicationContext(), root);
                    recyclerView.setAdapter(object);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }
}