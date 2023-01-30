package com.example.mealsapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsapplication.Constants;
import com.example.mealsapplication.MainActivity;
import com.example.mealsapplication.R;
import com.example.mealsapplication.model.Root;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MyViewHolder> {

    ArrayList<String> foodNames = new ArrayList<>();
    ArrayList<String> newFoodNames = new ArrayList<>();
    Context context;


    public MealsAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
    }

    Root root;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        if (foodNames.isEmpty()) {
//            try {
//                Toast.makeText(context, "going to arraylist", Toast.LENGTH_SHORT).show();
//                newFoodNames.addAll(returnFoodNames(root, position));
//            } catch (IndexOutOfBoundsException exception) {
//
//            }
//
//        }


        MainActivity object = new MainActivity(newFoodNames, root);
        holder.foodTitle.setText(root.meals.get(position).strMeal);
        holder.foodType.setText(root.meals.get(position).strCategory);
        holder.foodOrigin.setText(root.meals.get(position).strArea);
        holder.foodInstructions.setText(root.meals.get(position).strInstructions);
        Glide.with(context).load(root.meals.get(position).strMealThumb).into(holder.imageView);


    }

//    private ArrayList returnFoodNames(Root root, int position) {
//        for (int i = 0; i <= root.meals.size(); i++) {
//            foodNames.add(root.meals.get(i).strMeal);
//        }
//        return foodNames;
//    }

    @Override
    public int getItemCount() {
        return root.meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView foodTitle, foodType, foodOrigin, foodInstructions;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.foodImage);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodType = itemView.findViewById(R.id.foodType);
            foodOrigin = itemView.findViewById(R.id.foodOrigin);
            foodInstructions = itemView.findViewById(R.id.foodInstructions);
        }
    }

}
