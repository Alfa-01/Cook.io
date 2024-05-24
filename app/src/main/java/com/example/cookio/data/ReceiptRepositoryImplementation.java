package com.example.cookio.data;

import androidx.annotation.NonNull;

import com.example.cookio.data.dto.ReceiptDto;
import com.example.cookio.data.network.RetrofitFactory;
import com.example.cookio.data.source.ReceiptApi;
import com.example.cookio.data.utils.CallToConsumer;
import com.example.cookio.domain.ReceiptRepository;
import com.example.cookio.domain.entitites.FullReceiptEntity;
import com.example.cookio.domain.entitites.ItemReceiptEntity;
import com.example.cookio.domain.entitites.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class ReceiptRepositoryImplementation implements ReceiptRepository {
    private static ReceiptRepositoryImplementation INSTANCE;
    private final ReceiptApi receiptApi = RetrofitFactory.getInstance().getReceiptApi();

    private ReceiptRepositoryImplementation() {}

    public static synchronized ReceiptRepositoryImplementation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReceiptRepositoryImplementation();
        }
        return INSTANCE;
    }

    @Override
    public void getAllReceiptNames(@NonNull Consumer<Status<List<ItemReceiptEntity>>> callback) {
        receiptApi.getAll().enqueue(new CallToConsumer<>(
                callback,
                receiptsDto -> {
                    ArrayList<ItemReceiptEntity> result = new ArrayList<>(receiptsDto.size());
                    for (ReceiptDto receipt : receiptsDto) {
                        final String id = receipt.id;
                        final String name = receipt.name;
                        if (id != null && name != null) {
                            result.add(new ItemReceiptEntity(id, name));
                        }
                    }
                    return result;
                }
        ));
    }

    @Override
    public void getReceiptById(@NonNull String id, @NonNull Consumer<Status<FullReceiptEntity>> callback) {
        receiptApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                receipt -> {
                    final String resultId = receipt.id;
                    final String name = receipt.name;
                    if (resultId != null && name != null) {
                        return new FullReceiptEntity(
                                resultId, name, receipt.category, receipt.country,
                                receipt.instructions, receipt.thumbUrl, receipt.tags,
                                receipt.ingredients, receipt.measures
                        );
                    } else {
                        return null;
                    }
                }
        ));

    }
}
