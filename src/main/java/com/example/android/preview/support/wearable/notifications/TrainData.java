package com.example.android.preview.support.wearable.notifications;

/**
 * Created by Michael Yoon Huh on 6/14/2014.
 */
public class TrainData {

    // CLASS VARIABLE
    private String stationName;
    private int trainArrivalTime;
    private String trainTransitTime;
    private String trainLine;

    // PRESET VARIABLES
    public void initialize() {
        stationName = "Embarcadero St. Station";
        trainArrivalTime = 6;
        trainTransitTime = "1 HR, 10 MIN.";
        trainLine = "Richmond Line";
    }


    /** GET METHODS **/


    public void setStationName(String name) {

        this.stationName = name;
    }

    public void settrainArrivalTime(int time) {

        this.trainArrivalTime = time;
    }

    public void setTrainTransitTime(String name) {

        this.trainTransitTime = name;
    }

    public void setTrainLine(String name) {

        this.trainLine = name;
    }

    /** GET METHODS **/

    public String getStationName() {

        return this.stationName;
    }

    public int gettrainArrivalTime() {

        return this.trainArrivalTime;
    }

    public String getTrainTransitTime() {

        return this.trainTransitTime;
    }

    public String getTrainLine() {

        return this.trainLine;
    }

}
