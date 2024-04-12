package intuit.com.init;

import intuit.com.AppConfig;
import intuit.com.init.interfaces.IServerNetworkConfiguration;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerNetworkConfiguration implements IServerNetworkConfiguration {

    private static ServerNetworkConfiguration instance;
    private final InetSocketAddress serverAddress;

    public InetSocketAddress getServerAddress() {
        return instance.serverAddress;
    }

    public static ServerNetworkConfiguration getServerNetworkConfiguration() throws IOException {
        if (null == instance) {
            instance = new ServerNetworkConfiguration();
        }
        return instance;
    }

    private ServerNetworkConfiguration() throws IOException {
        serverAddress = new InetSocketAddress(AppConfig.getProperties().getProperty("server.host"),
                Integer.parseInt(AppConfig.getProperties().getProperty("server.port")));
    }

}
