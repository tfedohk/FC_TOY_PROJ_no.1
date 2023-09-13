package org.example.service;

import org.example.exeption.FileNotExistException;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.repository.TripDao;
import org.example.util.FileListLoader;
import org.example.util.FolderLocator;

import java.io.File;

public class SaveTripService {
    private final TripDao tripDao;

    public SaveTripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public void setIdToTrip(Trip trip) {
        int lastTripId = FileListLoader.getNumberFromLastFiles(FolderLocator.getPath());

        trip.setTripId(lastTripId + 1);

        int itineraryId = 1;
        for (Itinerary itinerary : trip.getItineraries()) {
            itinerary.setItineraryId(itineraryId++);

        }
    }

    public void saveTrip(Trip trip) {
        tripDao.saveTripAsJSON(trip);
        tripDao.saveTripAsCSV(trip);
    }
}
