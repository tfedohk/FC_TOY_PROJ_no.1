package org.example.controller;

import org.example.exeption.FileNotExistException;
import org.example.exeption.NotAppropriateOptionException;
import org.example.exeption.NotIntegerException;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.service.SearchTripService;
import org.example.util.FolderCreator;
import org.example.util.Verifier;
import org.example.view.Viewer;
import org.example.service.SaveTripService;
import java.util.List;
import java.util.Optional;

import static java.lang.System.exit;
import static org.example.util.constant.FileNameConstant.*;
import static org.example.util.constant.MenuOptionConstant.*;

public class TripController {
    private Viewer viewer;
    private SearchTripService searchTripService;
    private SaveTripService saveTripService;

    public TripController(Viewer viewer, SearchTripService searchTripService, SaveTripService saveTripService) {
        this.viewer = viewer;
        this.searchTripService = searchTripService;
        this.saveTripService = saveTripService;

        FolderCreator.createFolder();
    }

    public void launch() {
        viewer.printMenuOption();
        int menuOption;
        while (true){
            try {
                menuOption = Verifier.validOptionFormatIsInteger(viewer.receiveMenuOptionSelection());
                Verifier.validInOptionRange(menuOption, MAXIMUM_MAIN_MENU_SIZE);
                break;
            } catch (NotAppropriateOptionException | NotIntegerException e) {
                e.printStackTrace();
            }
        }
        switch (menuOption) {
            case RECORD_OPTION:
                recordTrip();
                break;
            case SEARCH_OPTION:
                printTrip();
                break;
            case TERMINATE_OPTION:
                terminate();
        }
        launch();
    }

    private void recordTrip() {
        Trip trip = new Trip();
        viewer.receiveTripInfo(trip);
        receiveItinerary(trip.getItineraries());
        saveTripService.setIdToTrip(trip);
        saveTripService.saveTrip(trip);

        int addTripOption;
        while (true){
            try {
                addTripOption = Verifier.validOptionFormatIsInteger(viewer.receiveAddTripSelection());
                Verifier.validInOptionRange(addTripOption, MAXIMUM_RECORD_TRIP_MENU_SIZE);
                break;
            } catch (NotAppropriateOptionException | NotIntegerException e) {
                e.printStackTrace();
            }
        }

        if (addTripOption == TERMINATE_RECORD_TRIP_OPTION) {
            return;
        }
        recordTrip();
    }

    private void printTrip() {
        int fileTypeOption;
        while (true){
            try {
                fileTypeOption = Verifier.validOptionFormatIsInteger(viewer.receiveFileTypeSelection());
                Verifier.validInOptionRange(fileTypeOption, MAXIMUM_SEARCH_MENU_SIZE);
                break;
            } catch (NotAppropriateOptionException | NotIntegerException e) {
                e.printStackTrace();
            }
        }

        List<Trip> trips;
        if(fileTypeOption == JSON_FILE_TYPE_OPTION){
            trips = searchTripService.getTripsAs(JSON_FILE_FORMAT_SUFFIX);
        } else{
            trips = searchTripService.getTripsAs(CSV_FILE_FORMAT_SUFFIX);
        }
        if(trips.isEmpty()){
            return;
        }
        viewer.printTripsNameAndId(trips);

        int selectedTripId;
        while (true){
            try {
                selectedTripId = Verifier.validOptionFormatIsInteger(viewer.receiveTripId());
                break;
            } catch (NotIntegerException e) {
                e.printStackTrace();
            }
        }

        Optional<Trip> foundTripOptional = searchTripService.getTripById(selectedTripId);
        try {
            Trip foundTrip = foundTripOptional.orElseThrow(FileNotExistException::new);
            viewer.printTrip(foundTrip);
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
    }

    private void receiveItinerary(List<Itinerary> itineraries) {
        while (true) {
            Itinerary itinerary = new Itinerary();
            viewer.receiveItineraryInfo(itinerary);
            itineraries.add(itinerary);

            int addItineraryOption;
            while (true){
                try {
                    addItineraryOption = Verifier.validOptionFormatIsInteger(viewer.receiveAddItinerarySelection());
                    Verifier.validInOptionRange(addItineraryOption, MAXIMUM_RECORD_ITINERARY_MENU_SIZE);
                    break;
                } catch (NotAppropriateOptionException | NotIntegerException e) {
                    e.printStackTrace();
                }
            }

            if (addItineraryOption == TERMINATE_RECORD_ITINERARY_OPTION) {
                break;
            }
        }
    }
    private void terminate() {
        viewer.printExit();
        exit(0);
    }
}