package org.example.view;

import org.example.model.Itinerary;
import org.example.model.Trip;
import java.util.List;
import java.util.Scanner;

public class Viewer {
    private final Scanner sc;
    public Viewer(Scanner sc) {
        this.sc = sc;
    }
    public void printMenuOption() {
        System.out.println("+-----------------------------------------------+");
        System.out.println("| 여행 여정을 기록과 관리하는 SNS 서비스\t\t\t\t|");
        System.out.println("| [메인 메뉴] 여행기록(1) 여행조회(2) 종료(3)\t\t\t|");
    }
    public String receiveMenuOptionSelection() {
        System.out.print("| 시작할 메뉴번호를 입력 하세요: ");
        return sc.nextLine();
    }
    public void receiveTripInfo(Trip trip) {
        System.out.println("+-----------------------------------------------+");
        System.out.println("|\t\t\t\t\t여행정보 입력\t\t\t\t\t|");
        System.out.print("| 여행이름: ");
        trip.setTripName(sc.nextLine());;
        System.out.println("| 여행 시작날짜 입력 (입력예시: 20230101)\t\t\t|");
        System.out.print("| 여행 시작날짜: ");
        trip.setStartDate(sc.nextLine());
        System.out.println("| 여행 종료날짜 입력 (입력예시: 20230101)\t\t\t|");
        System.out.print("| 여행 종료날짜: ");
        trip.setEndDate(sc.nextLine());
    }
    public void receiveItineraryInfo(Itinerary itinerary) {
        System.out.println("|-----------------------------------------------|");
        System.out.println("|\t\t\t\t\t여정정보 입력\t\t\t\t\t|");
        System.out.print("| 출발지: ");
        itinerary.setDeparturePlace(sc.nextLine());
        System.out.print("| 도착지: ");
        itinerary.setDestination(sc.nextLine());
        System.out.println("| 출발시각 입력 (입력예시: 20230101-2030)\t\t\t|");
        System.out.print("| 출발시각: ");
        itinerary.setDepartureTime(sc.nextLine());
        System.out.println("| 도착시각 입력 (입력예시: 20230101-2030)\t\t\t|");
        System.out.print("| 도착시각: ");
        itinerary.setArrivalTime(sc.nextLine());
        System.out.println("| 체크인 시각 입력 (입력예시: 20230101-2030)\t\t|");
        System.out.print("| 체크인 시각: ");
        itinerary.setCheckIn(sc.nextLine());
        System.out.println("| 체크아웃 시각 입력 (입력예시: 20230101-2030)\t\t|");
        System.out.print("| 체크아웃 시각: ");
        itinerary.setCheckOut(sc.nextLine());
    }
    public String receiveAddItinerarySelection() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("| [여정 추가여부] 다른 여정 기록(1) 여정 기록 종료(2)\t|");
        System.out.print("| 여정 추가여부를 선택하세요: ");
        return sc.nextLine();
    }
    public String receiveAddTripSelection() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("| [여행 추가여부] 다른 여행 기록(1) 메인으로(2)\t\t|");
        System.out.print("| 여행 추가여부를 선택하세요: ");
        return sc.nextLine();
    }
    public String receiveFileTypeSelection() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("| [불러올 파일 종류 선택] JSON(1) CSV(2)\t\t\t\t|");
        System.out.print("| 파일 종류를 선택하세요: ");
        return sc.nextLine();
    }
    public void printTripsNameAndId(List<Trip> trips) {
        System.out.println("|       여행 리스트\t\t\t\t\t\t\t\t|");
        for (Trip trip : trips) {
            System.out.print("| " + trip.getTripName() + " 여행   id: ");
            System.out.println(trip.getTripId());
        }
    }
    public String receiveTripId() {
        System.out.print("| 여정을 보고싶은 여행 id를 입력하세요: ");
        return sc.nextLine();
    }
    public void printTrip(Trip trip) {
        System.out.println("+-----------------------------------------------+");
        System.out.println("|       선택한 여행 정보\t\t\t\t\t\t\t|");
        System.out.println("+-----------------------------------------------+");
        System.out.println("| 여행 이름: " + trip.getTripName());
        System.out.println("| 여행 시작일: " + trip.getStartDate());
        System.out.println("| 여행 종료일: " + trip.getEndDate());
        List<Itinerary> itineraries = trip.getItineraries();
        System.out.println("|-----------------------------------------------|");
        System.out.println("|         여행 여정 정보\t\t\t\t\t\t\t|");
        System.out.println("|-----------------------------------------------|");
        for (Itinerary itinerary : itineraries) {
            System.out.println("| 출발지: " + itinerary.getDeparturePlace());
            System.out.println("| 도착지: " + itinerary.getDestination());
            System.out.println("| 출발시각: " + itinerary.getDepartureTime());
            System.out.println("| 도착시각: " + itinerary.getArrivalTime());
            System.out.println("| 체크인: " + itinerary.getCheckIn());
            System.out.println("| 체크아웃: " + itinerary.getCheckOut());
            System.out.println("-------------------------------------------------");
        }

    }
    public void printExit() {
        System.out.println("| 이용해주셔서 감사합니다. 서비스를 종료합니다.\t\t\t|");
        System.out.println("+-----------------------------------------------+");
    }
}