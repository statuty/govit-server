package com.statuty.govit.repository;

import com.statuty.govit.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESLocationRepository extends ElasticsearchCrudRepository<Location, String> {

    @Query("{\"bool\":{\"filter\":{\"geo_distance\":{\"coordinates\":{\"lat\":?0,\"lon\":?1},\"distance\":\"?2\"}}}}")
    Page<Location> findByDistance(double latitude, double longitude, String distance, Pageable pageable);

}
