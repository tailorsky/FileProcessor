package com.xmlandcss;

import java.io.*;
import java.util.*;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException{
        List <City> cities = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь к файлу(CSV/XML) или напишите 'exit', чтобы выйти: ");

            String ans = sc.nextLine();
            
            if (ans.equals("exit")){
                break;
            }

            File file = new File(ans);
            if (!file.exists() || !file.isFile()){
                System.out.println("Путь к файлу указан неправильно, попробуйте заново.");
                continue;
            }

            try{
                long startTime = System.currentTimeMillis();
                if (ans.endsWith(".csv")){
                    cities = GetInfoFromFile.readCSV(ans);
                }
                else if (ans.endsWith(".xml")){
                    cities = GetInfoFromFile.readXML(ans);
                }
                else{
                    System.out.println("Ошибка, неподдерживаемый формат файла. Выберите другой файл.");
                    continue;
                }
                FileProcess.printDuplicates(cities);
                FileProcess.printStatsOfFloors(cities);
                FileProcess.printProcessingTime(startTime);
            } 
            catch (Exception e) {
                System.out.println("Ошибка обработки файла: " + e.getMessage());
            }
        }
        sc.close();
    }
}