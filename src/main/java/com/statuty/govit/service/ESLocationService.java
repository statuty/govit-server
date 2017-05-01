package com.statuty.govit.service;

import com.statuty.govit.domain.Location;
import com.statuty.govit.repository.ESCustomLocationRepository;
import com.statuty.govit.repository.ESLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESLocationService {
    private final ESLocationRepository repository;
    private final ESCustomLocationRepository customRepository;

    @Autowired
    public ESLocationService(ESLocationRepository repository, ESCustomLocationRepository customRepository) {
        this.repository = repository;
        this.customRepository = customRepository;
    }

    public String save(Location location) {
        return repository.save(location).getId();
    }

    public Location findOne(String id) {
        return repository.findOne(id);
    }

    public List<Location> find(double latitude, double longitude, String distance, String category, String workingDay, String workingTime, int page, int size) {
        return customRepository.find(latitude, longitude, distance, category, workingDay, workingTime, page, size);
    }
}
