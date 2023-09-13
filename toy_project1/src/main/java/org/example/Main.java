package org.example;

import org.example.controller.TripController;
import org.example.repository.TripDao;
import org.example.service.SaveTripService;
import org.example.service.SearchTripService;
import org.example.view.Viewer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Viewer viewer = new Viewer(scanner);
        TripDao tripDao = new TripDao();
        SearchTripService searchTripService = new SearchTripService(tripDao);
        SaveTripService saveTripService = new SaveTripService(tripDao);
        TripController tripController = new TripController(viewer, searchTripService, saveTripService);

        tripController.launch();
    }
}