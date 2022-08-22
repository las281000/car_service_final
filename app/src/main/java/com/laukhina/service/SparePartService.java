package com.laukhina.service;

import com.laukhina.entity.SparePart;
import com.laukhina.repository.Repository;
import com.laukhina.request.SparePartRequest;

public class SparePartService {
    private final Repository<SparePart> repository;

    public SparePartService(Repository<SparePart> repository) {
        this.repository = repository;
    }

    public SparePart createSparePart(long carModelId, SparePartRequest request) {
        SparePart newPart = SparePart.builder()
                .type(request.getType())
                .serialNum(request.getSerialNum())
                .manufacturer(request.getManufacturer())
                .carModelId(carModelId)
                .isOriginal(request.isOriginal())
                .build();

        newPart = repository.save(newPart);
        return newPart;
    }
}
