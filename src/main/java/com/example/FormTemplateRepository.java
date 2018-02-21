package com.example;

import com.example.model.FormTemplateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FormTemplateRepository extends PagingAndSortingRepository<FormTemplateEntity, Integer> {
}
