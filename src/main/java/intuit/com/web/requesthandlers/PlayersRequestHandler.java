package intuit.com.web.requesthandlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpHandler;
import intuit.com.exceptions.IntuitValidationException;
import intuit.com.web.Mappers.PlayerToJsonMapper;
import intuit.com.web.persistance.models.Player;
import intuit.com.web.requesthandlers.interfaces.RequestValidator;
import intuit.com.web.service.PlayerServiceImpl;
import intuit.com.web.service.interfaces.IPlayerService;
import lombok.SneakyThrows;

public class PlayersRequestHandler implements HttpHandler, RequestValidator {

    final IPlayerService playerService = new PlayerServiceImpl();
    final PlayerToJsonMapper playerToJsonMapper = PlayerToJsonMapper.getMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestURI());
        validate();
        final List<Player> players = playerService.getPlayers();
        List<JsonNode> jsonNodes = playerToJsonMapper.mapPlayersToJson(players);
        String responseMessage = jsonNodes.stream().map(Objects::toString).collect(Collectors.joining());
        exchange.sendResponseHeaders(200, 0);
        final OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(responseMessage.getBytes());
        responseBody.flush();
        responseBody.close();
    }

    @Override
    public void validate() throws RuntimeException {}
}
