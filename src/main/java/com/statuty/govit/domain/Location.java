package com.statuty.govit.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "locations", type = "location")
public class Location {
    @Id
    private String id;
    private String name;
    private String description;
    private GeoPoint coordinates;
    private Category category;

    public Location(String id, String name, String description, GeoPoint coordinates, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.category = category;
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
}
