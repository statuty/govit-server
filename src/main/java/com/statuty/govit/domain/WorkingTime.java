package com.statuty.govit.domain;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class WorkingTime {
    @Field(type = FieldType.Date, format = DateFormat.hour_minute)
    private String from;
    @Field(type = FieldType.Date, format = DateFormat.hour_minute)
    private String to;

    public WorkingTime(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public WorkingTime() {
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
