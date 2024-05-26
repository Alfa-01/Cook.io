package com.example.cookio.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookio.R;
import com.example.cookio.data.utils.Utils;
import com.example.cookio.databinding.NewsFragmentBinding;
import com.example.cookio.news.list.NewsListViewModel;
import com.example.cookio.news.list.NewsRecyclerViewAdapter;

public class NewsFragment extends Fragment {
    private NewsFragmentBinding binding;
    private NewsListViewModel newsListViewModel;

    public NewsFragment() {
        super(R.layout.news_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = NewsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsListViewModel = new ViewModelProvider(this).get(NewsListViewModel.class);

        binding.refresh.setOnRefreshListener(() -> newsListViewModel.update());

        final NewsRecyclerViewAdapter adapter =
                new NewsRecyclerViewAdapter();

        binding.newsRecycler.setAdapter(adapter);

        subscribe(newsListViewModel, adapter);
    }

    public void subscribe(final NewsListViewModel viewModel,
                          final NewsRecyclerViewAdapter adapter) {
        viewModel.stateLiveData.observe(getViewLifecycleOwner(), state -> {
                boolean isSuccess = !state.isLoading()
                        && state.getErrorMessage() == null
                        && state.getItems() != null;

                binding.refresh.setEnabled(!state.isLoading());
                if (!state.isLoading()) binding.refresh.setRefreshing(false);

                binding.newsRecycler.setVisibility(Utils.visibleOrGone(isSuccess));
                binding.newsError.setVisibility(Utils.visibleOrGone(
                    state.getErrorMessage() != null));
                binding.newsLoading.setVisibility(Utils.visibleOrGone(state.isLoading()));

                binding.newsError.setText(state.getErrorMessage());

                if(isSuccess) {
                    adapter.updateData(state.getItems());
                }
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
