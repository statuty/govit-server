package com.statuty.govit.resource;

import com.statuty.govit.domain.Location;
import com.statuty.govit.service.ESLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/locations")
public class LocationResource {
    private final ESLocationService esLocationService;

    @Autowired
    public LocationResource(ESLocationService esLocationService) {
        this.esLocationService = esLocationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addLocation(@RequestBody Location location) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(esLocationService.save(location)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Location getLocation(@PathVariable String id) {
        return esLocationService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Location> getLocationByDistance(@RequestParam(name = "longitude") double longitude,
                                                @RequestParam(name = "latitude") double latitude,
                                                @RequestParam(name = "distance", defaultValue = "1km") String distance,
                                                @RequestParam(name = "category", required = false) String category,
                                                @RequestParam(name = "name", required = false) String name,
                                                @RequestParam(name = "workingDay", required = false) String workingDay,
                                                @RequestParam(name = "workingTime", required = false) String workingTime,
                                                @RequestParam(name = "isActivated", required = false) Boolean isActivated,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        return esLocationService.find(latitude, longitude, distance, name, category, workingDay, workingTime, isActivated, page, size);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/categories")
    public List<String> getCategories() {
        return esLocationService.findCategories();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/activate")
    public ResponseEntity<?> activateLocation(@RequestBody Location location) {
        if (Objects.isNull(location) || Objects.isNull(location.getId())) {
            return ResponseEntity.badRequest().build();
        }
        boolean activated = esLocationService.activateLocation(location.getId());
        return activated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/like")
    public ResponseEntity<?> addLike(@RequestBody Location location) {
        if (Objects.isNull(location) || Objects.isNull(location.getId())) {
            return ResponseEntity.badRequest().build();
        }
        boolean isLiked = esLocationService.addLike(location.getId());
        return isLiked ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable String id) {
        esLocationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
