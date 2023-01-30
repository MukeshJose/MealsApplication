package com.example.mealsapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsapplication.R;
import com.example.mealsapplication.model.Root;

public class RandomFoodAdapter extends RecyclerView.Adapter<RandomFoodAdapter.MyViewHolder> {
    Context context;

    public RandomFoodAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
    }

    Root root;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todays_pick_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(root.meals.get(position).strMealThumb).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return root.meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.randomFoodImage);
        }
    }
}
