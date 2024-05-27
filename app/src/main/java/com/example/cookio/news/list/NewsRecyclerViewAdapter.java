package com.example.cookio.news.list;


import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookio.R;
import com.example.cookio.data.utils.Utils;
import com.example.cookio.databinding.NewsItemBinding;
import com.example.cookio.domain.entitites.NewsEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter
        extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {


    private final List<NewsEntity> data = new ArrayList<>();
    private DatabaseReference reference;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            binding.unlikeValue.setText(String.valueOf(item.getLikes()));
            binding.description.setText(item.getDescription());
            binding.postUsername.setText(item.getAuthorNickname());

            if (!item.getAuthorImagePreview().isEmpty()) {
                Picasso.get()
                        .load(item.getAuthorImagePreview())
                        .fit()
                        .into(binding.postUsernameProfileIcon);
            } else {
                Picasso.get()
                        .load(R.drawable.profile)
                        .fit()
                        .into(binding.postUsernameProfileIcon);
            }

            Picasso.get()
                    .load(item.getImage())
                    .into(binding.postContent);

            binding.unlikeField.setOnClickListener(v -> {
                reference = FirebaseDatabase.getInstance().getReference();

                    reference.child("news").child(item.getId())
                            .child("likes").setValue(item.getLikes() + 1);
                    binding.unlikeField.setVisibility(Utils.visibleOrGone(false));
                    binding.likeField.setVisibility(Utils.visibleOrGone(true));
                    binding.likeValue.setText(String.valueOf(item.getLikes() + 1));
                    binding.unlikeValue.setText(String.valueOf(item.getLikes() + 1));
            });

            binding.likeField.setOnClickListener(v -> {
                reference = FirebaseDatabase.getInstance().getReference();

                    reference.child("news").child(item.getId())
                            .child("likes").setValue(item.getLikes() + 1);
                    binding.unlikeField.setVisibility(Utils.visibleOrGone(true));
                    binding.likeField.setVisibility(Utils.visibleOrGone(false));
                    binding.likeValue.setText(String.valueOf(item.getLikes()));
                    binding.unlikeValue.setText(String.valueOf(item.getLikes()));
            });
        };
    }
}
