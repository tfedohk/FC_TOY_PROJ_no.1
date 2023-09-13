package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.example.util.constant.ItineraryAttributeConstant.*;

public class Itinerary {
    @JsonProperty(ITINERARY_ID)
    private int itineraryId;
    @JsonProperty(ITINERARY_DEPARTURE_PLACE)
    private String departurePlace;
    @JsonProperty(ITINERARY_DESTINATION)
    private String destination;
    @JsonProperty(ITINERARY_DEPARTURE_TIME)
    private String departureTime;
    @JsonProperty(ITINERARY_ARRIVAL_TIME)
    private String arrivalTime;
    @JsonProperty(ITINERARY_CHECK_IN)
    private String checkIn;
    @JsonProperty(ITINERARY_CHECK_OUT)
    private String checkOut;
    public Itinerary() {
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}