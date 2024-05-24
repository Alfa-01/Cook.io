package com.example.cookio.news.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookio.databinding.NewsItemBinding;
import com.example.cookio.domain.entitites.NewsEntity;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter
        extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private final List<NewsEntity> data = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("firebase", "onCreateViewHolder");
        return new ViewHolder(
                NewsItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<NewsEntity> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NewsEntity item) {
            binding.postUsername.setText(item.getAuthorId());
            binding.postLikeButton.setText(String.valueOf(item.getLikes()));
            binding.description.setText(item.getDescription());

            Log.d("firebase", item.getDescription());
            //TODO: place image to layout (with picasso)
            //TODO: add usernames and profile's icon
        };
    }
}
