package com.example.mealsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealsapplication.adapter.CategoryAdapter;
import com.example.mealsapplication.adapter.RandomFoodAdapter;
import com.example.mealsapplication.adapter.SliderImageAdapter;
import com.example.mealsapplication.model.Root;
import com.example.mealsapplication.retrofit.APIClient;
import com.example.mealsapplication.retrofit.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText searchBar;
    Button button;
    ViewPager viewPager;
    RecyclerView categoriesView, randomView;

    ArrayList<String> foodNames = new ArrayList<>();

    Root root;

    public MainActivity() {
    }

    public MainActivity(ArrayList<String> newFoodNames, Root root) {
        this.foodNames = newFoodNames;
        this.root = root;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBar = findViewById(R.id.searchBar);
        button = findViewById(R.id.searchButton);
        viewPager = findViewById(R.id.imageSlider);
        categoriesView = findViewById(R.id.categoryView);
        randomView = findViewById(R.id.mainIngredient);

        foodNames.add("arrabiata");
        foodNames.add("corba");
        foodNames.add("corned beef and cabbage");
        foodNames.add("cream cheese tart");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item, foodNames);
        AutoCompleteTextView auto = findViewById(R.id.searchBar);
        auto.setThreshold(1);
        auto.setAdapter(adapter);
        auto.setTextColor(Color.GREEN);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "" + foodNames.size(), Toast.LENGTH_LONG).show();
                buttonPressActivity();
            }
        });
        APIInterface api = APIClient.getClient().create(APIInterface.class);
        sliderImageActivity(api);
        categoryDisplay(api);
        randomDisplay(api);
    }

    private void randomDisplay(APIInterface api) {
        api.CALL_APIRandom().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    randomView.setLayoutManager(layout);
                    RandomFoodAdapter object = new RandomFoodAdapter(getApplicationContext(), root);
                    randomView.setAdapter(object);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }


    private void categoryDisplay(APIInterface api) {
        api.CALL_APICategories().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    categoriesView.setLayoutManager(layoutManager);
                    CategoryAdapter object = new CategoryAdapter(getApplicationContext(), root);
                    categoriesView.setAdapter(object);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sliderImageActivity(APIInterface api) {
        api.CALL_APISliderImage().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                SliderImageAdapter sliderImageAdapter = new SliderImageAdapter(getApplicationContext(), root);
                viewPager.setAdapter(sliderImageAdapter);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    private void buttonPressActivity() {

        if (searchBar.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter your query!", Toast.LENGTH_LONG).show();
        } else {

            if (searchBar.getText().toString().length() == 1) {
                Intent intent = new Intent(MainActivity.this, FirstLetterSearch.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("searchResult", searchBar.getText().toString());
                startActivity(intent);
            }
        }
        if (searchBar.getText().toString().length() > 1) {
            Intent intent = new Intent(MainActivity.this, MealWindow.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("searchResult", searchBar.getText().toString());
            startActivity(intent);
        }
    }
}

