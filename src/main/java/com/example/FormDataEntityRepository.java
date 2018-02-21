package com.example;

import com.example.model.FormDataEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FormDataEntityRepository extends PagingAndSortingRepository<FormDataEntity, Long> {
}
