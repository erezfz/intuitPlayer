package intuit.com.web.importer.utils;

import com.opencsv.exceptions.CsvException;
import intuit.com.AppConfig;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public final class PlayerFileReader {

    public static List<String[]> getPlayersData() throws IOException, URISyntaxException {
        String dataFilename = AppConfig.getProperties().getProperty("data.file.players.name");
        final InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream(dataFilename);

//        final String filePath = ClassLoader.getSystemClassLoader().getResource(dataFilename).toURI().getPath();
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))){
        try (CSVReader reader = new CSVReader(new InputStreamReader(new BufferedInputStream(resourceAsStream)))){
            reader.skip(1);
            return reader.readAll();
//            BufferedReader br = new BufferedReader(reader)) {
//            List<String> players = new ArrayList<>();
//            String line;
//            while ((line = br.readLine()) != null) {
//                players.add(line);
//            }
        }  catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

}
