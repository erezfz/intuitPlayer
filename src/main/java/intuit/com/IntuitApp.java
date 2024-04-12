package intuit.com;

import intuit.com.init.ServerNetworkConfiguration;
import intuit.com.web.PlayerController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class IntuitApp {

    public static void main(String[] args) {
        try {
            final Properties properties = AppConfig.getProperties();
            initAppConfiguration();
            startApp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initAppConfiguration() throws RuntimeException {
        try {
            ServerNetworkConfiguration.getServerNetworkConfiguration();

        } catch( IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void startApp() throws RuntimeException{
        try {
            PlayerController playerController = new PlayerController();
            playerController.migrateContentToDB();
            playerController.startServer();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
