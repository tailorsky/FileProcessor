package com.xmlandcss;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.io.*;
import java.util.*;

public class GetInfoFromFile {

    public static List<City> readCSV(String filePath) throws IOException, CsvValidationException {
        List<City> cities = new ArrayList<>();
    
        CSVParser parser = new CSVParserBuilder()
                            .withSeparator(';')
                            .withIgnoreQuotations(true)
                            .build();
        
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))
                                .withSkipLines(1)
                                .withCSVParser(parser)
                                .build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String cityName = line[0];
                String street = line[1];
                int house = Integer.parseInt(line[2].trim());
                int floor = Integer.parseInt(line[3].trim());
                cities.add(new City(cityName, street, house, floor));
            }
        } catch (CsvValidationException e) {
            System.out.println("Ошибка валидации CSV: " + e.getMessage());
            throw e;
        }
        return cities;
    }

    public static List<City> readXML (String filePath) throws Exception{
        List<City> cities = new ArrayList<>();
        File file = new File(filePath);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList itemList = doc.getElementsByTagName("item");
        for (int i = 0; i < itemList.getLength(); i++) {
            Node itemNode = itemList.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element itemElement = (Element) itemNode;
                String cityName = itemElement.getAttribute("city");
                String street = itemElement.getAttribute("street");
                int house = Integer.parseInt(itemElement.getAttribute("house"));
                int floors = Integer.parseInt(itemElement.getAttribute("floor"));
                cities.add(new City(cityName, street, house, floors));
            }
        }
        return cities;
    }
}
