package org.example.service;

import org.example.model.Trip;
import org.example.repository.TripDao;

import java.util.List;
import java.util.Optional;

import static org.example.util.constant.FileNameConstant.*;

public class SearchTripService {
    private final TripDao tripDao;

    public SearchTripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsAs(String fileFormat) {
        if (JSON_FILE_FORMAT_SUFFIX.equals(fileFormat)) {
            return tripDao.findTripsAsJsonFrom();
        }
        return tripDao.findTripsAsCsvFrom();
    }

    public Optional<Trip> getTripById(int tripId) {
        Optional<Trip> foundTrip = Optional.empty();
        List<Trip> trips = tripDao.findTripsAsJsonFrom();
        for (Trip trip : trips) {
            if (trip.getTripId() == tripId) {
                foundTrip = Optional.of(trip);
            }
        }
        return foundTrip;
    }
}