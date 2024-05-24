package com.example.cookio.new_receipt.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookio.databinding.ReceiptItemInSearchBinding;
import com.example.cookio.domain.entitites.ItemReceiptEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ReceiptRecyclerViewAdapter
        extends RecyclerView.Adapter<ReceiptRecyclerViewAdapter.ViewHolder> {

    @NonNull
    private final Consumer<String> onItemClick;

    private final List<ItemReceiptEntity> data = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ReceiptItemInSearchBinding binding;
        public ViewHolder(@NonNull ReceiptItemInSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemReceiptEntity item) {
            binding.nameReceipt.setText(item.getName());
            binding.getRoot().setOnClickListener(v -> {
                onItemClick.accept(item.getId());
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ReceiptItemInSearchBinding.inflate(
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

    public ReceiptRecyclerViewAdapter(@NonNull Consumer<String> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void updateData(List<ItemReceiptEntity> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

}
