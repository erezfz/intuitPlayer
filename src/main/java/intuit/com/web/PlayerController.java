package intuit.com.web;

import com.sun.net.httpserver.HttpServer;
import intuit.com.init.ServerNetworkConfiguration;
import intuit.com.init.interfaces.IServerNetworkConfiguration;
import intuit.com.web.importer.ImporterRunner;
import intuit.com.web.requesthandlers.PlayerRequestHandler;
import intuit.com.web.requesthandlers.PlayersRequestHandler;

import java.io.IOException;
import java.net.URISyntaxException;

public class PlayerController {

    public void startServer(){

        try {
            IServerNetworkConfiguration serverNetworkConfiguration = ServerNetworkConfiguration.getServerNetworkConfiguration();
//            IPlayerService playerService = new PlayerServiceImpl();
//            final PlayerToJsonMapper playerToJsonMapper = PlayerToJsonMapper.getMapper();
            HttpServer server = HttpServer.create(serverNetworkConfiguration.getServerAddress(), 0);
            server.createContext("/api/players", new PlayersRequestHandler());
            server.createContext("/api/players/", new PlayerRequestHandler());
            System.out.printf("Starting server on port %d, on host %s",
                    serverNetworkConfiguration.getServerAddress().getPort(),
                    serverNetworkConfiguration.getServerAddress().getHostName());
            server.start();

        } catch ( IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void migrateContentToDB() throws IOException, URISyntaxException {
        final ImporterRunner importer = new ImporterRunner();
        importer.run();
    };
}
