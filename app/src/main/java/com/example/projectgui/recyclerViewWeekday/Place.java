package com.example.projectgui.recyclerViewWeekday;

public class Place {
    private final String address;
    private final Underground underground;

    public Place(String address, Underground underground) {
        this.address = address;
        this.underground = underground;
    }

    public String getAddress() {
        return address;
    }

    public Underground getUnderground() {
        return underground;
    }
}
