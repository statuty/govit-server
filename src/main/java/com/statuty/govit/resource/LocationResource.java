package com.statuty.govit.resource;

import com.statuty.govit.domain.Location;
import com.statuty.govit.service.ESLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        return esLocationService.findByDistance(latitude, longitude, distance, page, size);
    }
}
