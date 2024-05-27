package com.example.cookio.data.source;

import com.example.cookio.data.dto.ReceiptDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReceiptApi {
    @GET("receipts.json")
    Call<List<ReceiptDto>> getAll();

    @GET("receipts/{id}.json")
    Call<ReceiptDto> getById(@Path("id") String id);
}
