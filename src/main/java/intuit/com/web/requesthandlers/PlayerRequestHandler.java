package intuit.com.web.requesthandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import intuit.com.exceptions.IntuitValidationException;
import intuit.com.web.Mappers.PlayerToJsonMapper;
import intuit.com.web.persistance.models.Player;
import intuit.com.web.requesthandlers.interfaces.RequestValidator;
import intuit.com.web.service.PlayerServiceImpl;
import intuit.com.web.service.interfaces.IPlayerService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class PlayerRequestHandler implements HttpHandler, RequestValidator {
    final IPlayerService playerService = new PlayerServiceImpl();
    final PlayerToJsonMapper playerToJsonMapper = PlayerToJsonMapper.getMapper();
    HttpExchange httpExchange;


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestURI());
        this.httpExchange = exchange;
        validate();
        final String playerID = exchange.getRequestURI().toString().substring("/api/players/".length());
        final Optional<Player> playerByIdOptional = playerService.getPlayerById(playerID);
        final String responseMessage = playerByIdOptional.map(
                        player -> playerToJsonMapper.mapPlayerToJson(player).toString())
                .orElse(null);
        playerByIdOptional.ifPresentOrElse((player) -> {
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    try (OutputStream responseBodyStream = exchange.getResponseBody()) {
                        exchange.sendResponseHeaders(200, 0);
                        responseBodyStream.write(responseMessage.getBytes());
                        responseBodyStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    try (OutputStream responseBodyStream = exchange.getResponseBody()) {
                        exchange.sendResponseHeaders(204, -1);
                        responseBodyStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void validate() throws RuntimeException {
        final String playerID = this.getPlayerID();
        if (playerID.isEmpty()) {
            throw new RuntimeException("Player ID is empty");
        }
        if (playerID.contains("/")) {
            throw new RuntimeException("Player ID provided contains invalid characters");
        }
    }

    public String getPlayerID() {
        return this.httpExchange.getRequestURI().toString().substring("/api/players/".length());
    }
}
