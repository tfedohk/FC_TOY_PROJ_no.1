package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.util.FileListLoader;
import org.example.util.FolderLocator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.constant.FileNameConstant.*;
import static org.example.util.constant.ItineraryAttributeConstant.*;
import static org.example.util.constant.TripAttributeConstant.*;
import static org.example.util.constant.TripAttributeConstant.TRIP_END_DATE;

public class TripDao {
    private final static int NUMBER_FOR_CSV_HEADER = 0;

    public List<Trip> findTripsAsJsonFrom() {
        Trip tripForJson;
        ObjectMapper objectMapper = new ObjectMapper();

        List<File> jsonFiles = FileListLoader.getJsonFiles(FolderLocator.getPath());
        List<Trip> trips = new ArrayList<>();

        for (File jsonFile : jsonFiles) {
            try {
                tripForJson = objectMapper.readValue(jsonFile, Trip.class);
                trips.add(tripForJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

    public List<Trip> findTripsAsCsvFrom() {
        List<File> csvFiles = FileListLoader.getCsvFiles(FolderLocator.getPath());

        List<Trip> trips = new ArrayList<>();
        for (File csvFile : csvFiles) {
            Trip trip = new Trip();
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> records = reader.readAll();

                records.remove(NUMBER_FOR_CSV_HEADER);

                List<Itinerary> itineraries = new ArrayList<>();
                for (String[] record : records) {
                    trip.setTripId(Integer.parseInt(record[0]));
                    trip.setTripName(record[1]);
                    trip.setStartDate(record[2]);
                    trip.setEndDate(record[3]);

                    Itinerary itinerary = new Itinerary();
                    itinerary.setItineraryId(Integer.parseInt(record[4]));
                    itinerary.setDeparturePlace(record[5]);
                    itinerary.setDestination(record[6]);
                    itinerary.setDepartureTime(record[7]);
                    itinerary.setArrivalTime(record[8]);
                    itinerary.setCheckIn(record[9]);
                    itinerary.setCheckOut(record[10]);

                    itineraries.add(itinerary);
                }
                trip.setItineraries(itineraries);
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
            trips.add(trip);
        }
        return trips;
    }

    public void saveTripAsCSV(Trip trip) {
        String csvColumnName = TRIP_ID + "," + TRIP_NAME + "," + TRIP_START_DATE + "," + TRIP_END_DATE + ","
                + ITINERARY_ID + "," + ITINERARY_DEPARTURE_PLACE + "," +ITINERARY_DESTINATION + ","
                + ITINERARY_DEPARTURE_TIME + "," + ITINERARY_ARRIVAL_TIME + "," + ITINERARY_CHECK_IN + "," + ITINERARY_CHECK_OUT + "\n";

        String filename = FolderLocator.getPath() + FILENAME_PREFIX + "_" + trip.getTripId() + CSV_FILE_FORMAT_SUFFIX;
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(filename))) {
            writer.append(csvColumnName);
            for (Itinerary itinerary : trip.getItineraries()) {
                writer.append(Integer.toString(trip.getTripId())).append(",")
                        .append(trip.getTripName()).append(",")
                        .append(trip.getStartDate()).append(",")
                        .append(trip.getEndDate()).append(",")
                        .append(String.valueOf(itinerary.getItineraryId())).append(",")
                        .append(itinerary.getDeparturePlace()).append(",")
                        .append(itinerary.getDestination()).append(",")
                        .append(itinerary.getDepartureTime()).append(",")
                        .append(itinerary.getArrivalTime()).append(",")
                        .append(itinerary.getCheckIn()).append(",")
                        .append(itinerary.getCheckOut()).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveTripAsJSON(Trip trip) {
        ObjectMapper objectMapper = new ObjectMapper();
        String filename = FolderLocator.getPath() + FILENAME_PREFIX + "_" + trip.getTripId() + JSON_FILE_FORMAT_SUFFIX;
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename))) {
            objectMapper.writeValue(writer, trip);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}