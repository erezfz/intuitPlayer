package intuit.com.web.importer.interfaces;

import java.io.IOException;
import java.util.List;

public interface IImporterService {
    void importPlayers(List<String[]> rawPlayers) throws IOException;
}
