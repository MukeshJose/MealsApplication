package com.example.mealsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.DocumentsContract;

import com.example.mealsapplication.adapter.FirstLetterSearchAdapter;
import com.example.mealsapplication.model.Root;
import com.example.mealsapplication.retrofit.APIClient;
import com.example.mealsapplication.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstLetterSearch extends AppCompatActivity {
    RecyclerView firstLetterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_letter_search);
        firstLetterView = findViewById(R.id.firstLetterView);

        APIInterface api = APIClient.getClient().create(APIInterface.class);
        firstLetterSearch(api);
    }

    private void firstLetterSearch(APIInterface api) {
        api.CALL_APISearchByFirst(getIntent().getStringExtra("searchResult")).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    firstLetterView.setLayoutManager(layoutManager);
                    FirstLetterSearchAdapter object = new FirstLetterSearchAdapter(getApplicationContext(), root);
                    firstLetterView.setAdapter(object);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }
}