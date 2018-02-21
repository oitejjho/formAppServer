package com.example.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class FormSchemaDTO {

    private Integer id;
    private JsonNode form;
    private JsonNode schema;

    public Integer getId() {
        return id;
    }

    public FormSchemaDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public JsonNode getForm() {
        return form;
    }

    public FormSchemaDTO setForm(JsonNode form) {
        this.form = form;
        return this;
    }

    public JsonNode getSchema() {
        return schema;
    }

    public FormSchemaDTO setSchema(JsonNode schema) {
        this.schema = schema;
        return this;
    }

    @Override
    public String toString() {
        return "FormSchemaDTO{" +
                "id=" + id +
                ", form=" + form +
                ", schema=" + schema +
                '}';
    }
}
