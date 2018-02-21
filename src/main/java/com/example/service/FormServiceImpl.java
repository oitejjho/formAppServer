package com.example.service;

import com.example.FormDataEntityRepository;
import com.example.FormTemplateRepository;
import com.example.model.FormDataEntity;
import com.example.model.FormTemplateEntity;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FormServiceImpl implements FormService {
    private static final Logger LOG = LoggerFactory.getLogger(FormServiceImpl.class);

    @Autowired
    FormTemplateRepository formTemplateRepository;

    @Autowired
    FormDataEntityRepository formDataEntityRepository;

    @Override
    public List<FormTemplateEntity> getAllForm() {
        LOG.info("FormServiceImpl -> getAllForm");
        List<FormTemplateEntity> formTemplateEntities = new ArrayList<>();

        Iterable<FormTemplateEntity> entities = formTemplateRepository.findAll();
        for (FormTemplateEntity formTemplateEntity :
                entities) {
            formTemplateEntities.add(formTemplateEntity);
        }


        return formTemplateEntities;
    }

    @Override
    public FormTemplateEntity getFormTemplateById(Integer id) {
        LOG.info("FormServiceImpl -> getFormTemplateById : {}", id);
        FormTemplateEntity formTemplateEntity = formTemplateRepository.findOne(id);
        return formTemplateEntity;
    }

    @Override
    public FormTemplateEntity saveFormTemplate(JsonNode formSchema) {
        LOG.info("FormServiceImpl -> saveFormTemplate : {}", formSchema);

        JsonNode form = formSchema.get("form");
        JsonNode schema = formSchema.get("schema");

        FormTemplateEntity formTemplateEntity = new FormTemplateEntity();
        formTemplateEntity.setForm(form.toString());
        formTemplateEntity.setfSchema(schema.toString());
        formTemplateEntity.setCreated(new Date());

        formTemplateEntity = formTemplateRepository.save(formTemplateEntity);

        return formTemplateEntity;
    }

    @Override
    public FormDataEntity saveFormData(Integer formId, JsonNode formData) {
        LOG.info("FormServiceImpl -> saveFormData : {}", formData);

        FormTemplateEntity formTemplateEntity = formTemplateRepository.findOne(formId);

        FormDataEntity formDataEntity = new FormDataEntity();
        formDataEntity.setData(formData.toString());
        formDataEntity.setFormTemplateByFormId(formTemplateEntity);
        formDataEntity.setCreated(new Date());

        formDataEntity = formDataEntityRepository.save(formDataEntity);

        return formDataEntity;
    }
}
