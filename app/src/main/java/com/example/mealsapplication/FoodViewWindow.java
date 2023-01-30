package com.example.mealsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mealsapplication.adapter.FoodViewAdapter;
import com.example.mealsapplication.model.Root;
import com.example.mealsapplication.retrofit.APIClient;
import com.example.mealsapplication.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodViewWindow extends AppCompatActivity {
    RecyclerView foodViewRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view_window);
        foodViewRecycler = findViewById(R.id.foodView);

        APIInterface api = APIClient.getClient().create(APIInterface.class);

        foodViewDisplay(api);
    }

    private void foodViewDisplay(APIInterface api) {
        api.CALL_APICategoryFilter(getIntent().getStringExtra("categoryName")).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    foodViewRecycler.setLayoutManager(gridLayoutManager);
                    FoodViewAdapter object = new FoodViewAdapter(getApplicationContext(), root);
                    foodViewRecycler.setAdapter(object);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}