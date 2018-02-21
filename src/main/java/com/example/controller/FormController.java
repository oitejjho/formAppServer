package com.example.controller;

import com.example.dto.FormSchemaDTO;
import com.example.model.FormDataEntity;
import com.example.model.FormTemplateEntity;
import com.example.service.FormService;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/form")
@Produces("application/json")
public class FormController {

    private static final Logger LOG = LoggerFactory.getLogger(FormController.class);

    @Autowired
    FormService formService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FormSchemaDTO> saveForm(@RequestBody JsonNode formSchema) {
        LOG.info("FormController -> saveForm -> form : {}", formSchema);

        ObjectMapper mapper = new ObjectMapper();
        FormSchemaDTO formSchemaDTO = new FormSchemaDTO();
        FormTemplateEntity formTemplateEntity = formService.saveFormTemplate(formSchema);

        try {
            formSchemaDTO.setId(formTemplateEntity.getId())
                    .setForm(mapper.readTree(formTemplateEntity.getForm()))
                    .setSchema(mapper.readTree(formTemplateEntity.getfSchema()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(formSchemaDTO);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FormSchemaDTO>> get() {
        LOG.info("FormController -> get");
        List<FormSchemaDTO> formSchemaDTOS = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.createObjectNode();
        List<FormTemplateEntity> formTemplateEntities = formService.getAllForm();
        for (FormTemplateEntity formTemplateEntity :
                formTemplateEntities) {
            try {
                formSchemaDTOS.add(new FormSchemaDTO()
                        .setId(formTemplateEntity.getId())
                        .setForm(mapper.readTree(formTemplateEntity.getForm()))
                        .setSchema(mapper.readTree(formTemplateEntity.getfSchema())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(formSchemaDTOS);
    }

    @RequestMapping(value = "/{formId}", method = RequestMethod.GET)
    public ResponseEntity<FormSchemaDTO> getForm(@PathVariable("formId") Integer formId) {
        LOG.info("FormController -> getForm : {} ", formId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        FormSchemaDTO formSchemaDTO = new FormSchemaDTO();
        try {

            FormTemplateEntity formTemplateEntity = formService.getFormTemplateById(formId);
            formSchemaDTO.setId(formTemplateEntity.getId())
                    .setForm(mapper.readTree(formTemplateEntity.getForm()))
                    .setSchema(mapper.readTree(formTemplateEntity.getfSchema()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(formSchemaDTO);
    }


    @RequestMapping(value = "/{formId}/data", method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<JsonNode> save(@PathVariable("formId") Integer formId, @RequestBody JsonNode object) {
        LOG.info("FormController -> save -> object : {}", object);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        FormDataEntity formDataEntity = formService.saveFormData(formId, object);
        try {
            node = mapper.readTree(formDataEntity.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(node);
    }


   /* @RequestMapping(value = "/{formId}/data", method = RequestMethod.GET)
    @Consumes("application/json")
    public ResponseEntity<List<JsonNode>> getDataByFormId(@PathVariable("formId") Integer formId) {
        LOG.info("FormController -> getDataByFormId -> formId : {}", formId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        List<JsonNode> jsonNodes = new ArrayList<>();
        File[] files = this.getDataFiles(formId);
        for (File file :
                files) {
            try {
                jsonNodes.add(mapper.readTree(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(jsonNodes);
    }

    @RequestMapping(value = "/{formId}/data/{dataId}", method = RequestMethod.GET)
    public ResponseEntity<JsonNode> getData(@PathVariable("formId") Integer formId, @PathVariable("dataId") Integer dataId) {
        LOG.info("FormController -> save -> formId : {}, dataId : {}", formId, dataId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        File file = new File("schema_form_" + formId + "_data_" + dataId + ".json");
        try {
            node = mapper.readTree(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer i = getDataCount(formId);
        return ResponseEntity.ok(node);
    }*/


}
