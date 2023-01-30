package com.example.mealsapplication.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mealsapplication.R;
import com.example.mealsapplication.model.Root;

import java.util.Objects;

public class SliderImageAdapter extends PagerAdapter {

    Context context;
    Root root;

    public SliderImageAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
    }

    @Override
    public int getCount() {
        return root.categories.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.image_slider_window, container,false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewSlider);

        // setting the image in the imageView
        Glide.with(context).load(root.categories.get(position).strCategoryThumb).into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}

