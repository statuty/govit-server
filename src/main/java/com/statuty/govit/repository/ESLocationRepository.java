package com.statuty.govit.repository;

import com.statuty.govit.domain.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESLocationRepository extends ElasticsearchCrudRepository<Location, String> {
}
