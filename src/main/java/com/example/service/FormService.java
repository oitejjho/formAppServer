package com.example.service;

import com.example.model.FormDataEntity;
import com.example.model.FormTemplateEntity;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.print.Pageable;
import java.util.List;

public interface FormService {

    List<FormTemplateEntity> getAllForm();

    FormTemplateEntity getFormTemplateById(Integer id);

    FormTemplateEntity saveFormTemplate(JsonNode formSchema);

    FormDataEntity saveFormData(Integer formId, JsonNode formData);
}
