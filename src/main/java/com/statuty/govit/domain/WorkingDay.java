package com.statuty.govit.domain;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

public class WorkingDay {
    private String day;
    @Field(type = FieldType.Nested)
    private List<WorkingTime> time = new ArrayList<>();

    public WorkingDay(String day, List<WorkingTime> time) {
        this.day = day;
        this.time = time;
    }

    public WorkingDay() {
    }

    public String getDay() {
        return day;
    }

    public List<WorkingTime> getTime() {
        return time;
    }
}
