package com.example.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "form_template", schema = "form_assignment", catalog = "")
public class FormTemplateEntity {
    private int id;
    private String form;
    private String fSchema;
    private Date created;
    private Collection<FormDataEntity> formDataById;

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "form")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "f_schema")
    public String getfSchema() {
        return fSchema;
    }

    public void setfSchema(String fSchema) {
        this.fSchema = fSchema;
    }

    @Basic
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormTemplateEntity that = (FormTemplateEntity) o;

        if (id != that.id) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (fSchema != null ? !fSchema.equals(that.fSchema) : that.fSchema != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (fSchema != null ? fSchema.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "formTemplateByFormId")
    public Collection<FormDataEntity> getFormDataById() {
        return formDataById;
    }

    public void setFormDataById(Collection<FormDataEntity> formDataById) {
        this.formDataById = formDataById;
    }
}
