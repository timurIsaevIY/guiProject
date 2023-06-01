package com.example.projectgui.recyclerViewWeekday;

import java.util.HashMap;
import java.util.Map;

public class DataClass {
    private final Map<Integer, String> undergrounds = new HashMap<>();

    public DataClass() {
        undergrounds.put(1, "Охотный Ряд");
        undergrounds.put(2, "Театральная");
        undergrounds.put(3, "Площадь Революции");
        undergrounds.put(4, "Арбатская");
        undergrounds.put(5, "Курская");
        undergrounds.put(6, "Новокузнецкая");
        undergrounds.put(7, "Библиотека имени Ленина");
        undergrounds.put(8, "Чистые пруды");
        undergrounds.put(9, "Кузнецкий Мост");
    }

    public boolean containsUnderground(String underground) {
        boolean ans = false;
        for (Map.Entry<Integer, String> pair: undergrounds.entrySet()) {
            if (pair.getValue().equals(underground)) {
                ans = true;
                break;
            }
        }
        return ans;
    }

    public Map<Integer, String> getUndergrounds() {
        return undergrounds;
    }
}
