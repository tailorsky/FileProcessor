package com.xmlandcss;

import java.util.*;

public class FileProcess {
    public static void printDuplicates(List<City> cities) {
        Map<String, Long> duplicates = new HashMap<>();

        for (City city : cities) {
            String key = "City {Name= " + city.getCityName() + ", Street= " + city.getStreet() + ", house= " + city.getHouse() + ", floor= "+ city.getFloor() + "}";
            duplicates.put(key, duplicates.getOrDefault(key, 0L) + 1);
        }

        System.out.println("Повторяющиеся записи городов с указанием количества:");
        duplicates.forEach((cityDetails, count) -> {
            if (count > 1) {
                System.out.println(cityDetails + " - Количество повторений: " + count);
            }
        });
    }

    public static void printStatsOfFloors(List<City> cities) {
        Map<String, int[]> cityStats = new HashMap<>();
        Set<String> uniqueBuildings = new HashSet<>();
    
        for (City city : cities) {
            String uniqueKey = city.getCityName() + "-" + city.getStreet() + "-" + city.getHouse() + "-" + city.getFloor();
    
            if (!uniqueBuildings.add(uniqueKey)) {
                continue;
            }
    
            cityStats.putIfAbsent(city.getCityName(), new int[5]);
            int[] floorsCount = cityStats.get(city.getCityName());
            if (city.getFloor() <= 5) {
                floorsCount[city.getFloor() - 1]++;
            } else {
                floorsCount[4]++;
            }
        }
    
        System.out.println("Статистика этажей здания:");
        for (Map.Entry<String, int[]> entry : cityStats.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            System.out.println(Arrays.toString(entry.getValue()));
        }
    }
    

    public static void printProcessingTime(long startTime){
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " ms");
    }
}
