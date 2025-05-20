import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/75030fefc65ed8e4d068d17c/latest/USD"))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        RespostaDaApi dados = gson.fromJson(response.body(), RespostaDaApi.class);

        System.out.println("Taxas de câmbio a partir do USD:");
        System.out.println("USD → ARS (Peso argentino): " + dados.conversion_rates.ARS);
        System.out.println("USD → BOB (Boliviano boliviano): " + dados.conversion_rates.BOB);
        System.out.println("USD → BRL (Real brasileiro): " + dados.conversion_rates.BRL);
        System.out.println("USD → CLP (Peso chileno): " + dados.conversion_rates.CLP);
        System.out.println("USD → COP (Peso colombiano): " + dados.conversion_rates.COP);
        System.out.println("USD → USD (Dólar americano): " + dados.conversion_rates.USD);

    }
}

