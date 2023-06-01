package com.example.projectgui.recyclerViewWeekday;

import java.util.HashMap;
import java.util.Map;


/*
0 Киевская
1Парк культуры
2 Октябрьская
3 Добрынинская
4 Таганская
5 Аннино
6 Южная
7 Чертановская
8 Нагорная
9 Тульская
10 Аэропорт
 */


public class Underground {
    private final int id;
    private final String name;
    private Map<Integer, String> undergrounds = new HashMap<>();

    public Underground(String name) {
        this.name = name;
        this.id = detectId(name);
        this.undergrounds = new DataClass().getUndergrounds();
    }

    private int detectId(String name) {
        int ans = 0;
        for (Map.Entry<Integer, String> pair : undergrounds.entrySet()) {
            if (pair.getValue().equals(name)) {
                ans = pair.getKey();
                break;
            }
        }
        return ans;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
