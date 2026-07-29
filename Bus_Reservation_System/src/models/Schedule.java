package models;

import java.sql.Time;
import java.util.Date;

// 1. Add "implements Comparable<Schedule>"
public class Schedule implements Comparable<Schedule> {

    private int scheduleId;
    private Time departureTime;
    private Time arrivalTime;
    private Date date;
    private Bus bus;
    private Route route;

    public Schedule(int scheduleId, Time departureTime, Time arrivalTime, Date date, Bus bus, Route route) {
        this.scheduleId = scheduleId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.bus = bus;
        this.route = route;
    }

    // 2. Implement the compareTo method (compares schedules by ID)
    @Override
    public int compareTo(Schedule other) {
        return Integer.compare(this.scheduleId, other.scheduleId);
    }

    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    public Time getDepartureTime() { return departureTime; }
    public Time getArrivalTime() { return arrivalTime; }
    public Date getDate() { return date; }
    public Bus getBus() { return bus; }
    public Route getRoute() { return route; }

    @Override
    public String toString() {
        return "Schedule #" + scheduleId + " | Bus: " + (bus != null ? bus.getBusNumber() : "N/A")
                + " | Route: " + (route != null ? route.getSource() + " -> " + route.getDestination() : "N/A")
                + " | Dep: " + departureTime;
    }
}