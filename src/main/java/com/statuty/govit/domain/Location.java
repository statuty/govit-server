package com.statuty.govit.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Document(indexName = "locations", type = "location")
public class Location {
    @Id
    private String id;
    private String name;
    private String description;
    private GeoPoint coordinates;
    private Category category;
    @Field(type = FieldType.Nested)
    private List<WorkingDay> workingDays;

    public Location(String id, String name, String description, GeoPoint coordinates, Category category, List<WorkingDay> workingDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.category = category;
        this.workingDays = workingDays;
    }

    public Location() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }

    public Category getCategory() {
        return category;
    }

    public List<WorkingDay> getWorkingDays() {
        return workingDays;
    }
}
