package com.example.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Profession {

    STUDENT(2), PROFESSIONAL(2), OTHER(3);

    private Integer value;

    private Profession(Integer value) {
        this.value = value;
    }

    public Integer getProfession() {
        return this.value;
    }
}
