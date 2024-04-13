package intuit.com.web.importer.utils;

import com.opencsv.exceptions.CsvException;
import intuit.com.AppConfig;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import com.opencsv.CSVReader;

public final class PlayerFileReader {

    public static List<String[]> getPlayersData() throws IOException, URISyntaxException {
        String dataFilename = AppConfig.getProperties().getProperty("data.file.players.name");
        final InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream(dataFilename);
        try (CSVReader reader = new CSVReader(new InputStreamReader(new BufferedInputStream(resourceAsStream)))){
            reader.skip(1);
            return reader.readAll();
        }  catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

}
