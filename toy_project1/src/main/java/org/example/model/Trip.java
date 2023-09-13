package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

import static org.example.util.constant.TripAttributeConstant.*;

public class Trip {
    @JsonProperty(TRIP_ID)
    private int tripId;
    @JsonProperty(TRIP_NAME)
    private String tripName;
    @JsonProperty(TRIP_START_DATE)
    private String startDate;
    @JsonProperty(TRIP_END_DATE)
    private String endDate;
    @JsonProperty(TRIP_ITINERARIES)
    private List<Itinerary> Itineraries = new ArrayList<>();
    public Trip() {
    }
    public int getTripId() {
        return tripId;
    }
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    public String getTripName() {
        return tripName;
    }
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public List<Itinerary> getItineraries() {
        return Itineraries;
    }
    public void setItineraries(List<Itinerary> itineraries) {
        Itineraries = itineraries;
    }
}