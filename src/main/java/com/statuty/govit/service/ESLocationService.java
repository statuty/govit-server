package com.statuty.govit.service;

import com.statuty.govit.domain.Location;
import com.statuty.govit.repository.ESCustomLocationRepository;
import com.statuty.govit.repository.ESLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public List<Location> find(double latitude, double longitude, String distance, String name, String category,
                               String workingDay, String workingTime, Boolean isActivated, int page, int size) {
        return customRepository.find(latitude, longitude, distance, name, category, workingDay, workingTime, isActivated, page, size);
    }

    public List<String> findCategories() {
        return customRepository.findCategories();
    }

    public boolean addLike(String id) {
        Location location = repository.findOne(id);
        if (Objects.isNull(location)) {
            return false;
        }
        location.setLikes(location.getLikes() + 1);
        repository.save(location);
        return true;
    }

    public boolean activateLocation(String id) {
        Location location = repository.findOne(id);
        if (Objects.isNull(location)) {
            return false;
        }
        location.setActivated(true);
        repository.save(location);
        return true;
    }


    public void delete(String id) {
        repository.delete(id);
    }
}
