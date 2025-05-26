import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();

        String apiKey = "75030fefc65ed8e4d068d17c";  // sua chave da API
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro ao acessar a API. Código: " + response.statusCode());
                return;
            }

            Gson gson = new Gson();
            RespostaDaApi dados = gson.fromJson(response.body(), RespostaDaApi.class);

            int sair = 0;
            while (sair != 1) {
                System.out.println("\n=== Conversor de Moedas ===");
                System.out.println("Moedas disponíveis: USD, BRL, ARS, BOB, CLP, COP");
                System.out.print("Digite a moeda de origem (ex: USD): ");
                String moedaOrigem = scanner.nextLine().toUpperCase();

                System.out.print("Digite a moeda de destino (ex: BRL): ");
                String moedaDestino = scanner.nextLine().toUpperCase();

                System.out.print("Digite o valor a ser convertido: ");
                double valor;
                try {
                    valor = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite um número.");
                    continue;
                }

                double taxaOrigem = getTaxa(dados.conversion_rates, moedaOrigem);
                double taxaDestino = getTaxa(dados.conversion_rates, moedaDestino);

                if (taxaOrigem == -1 || taxaDestino == -1) {
                    System.out.println("Moeda inválida.");
                    continue;
                }

                double valorEmUSD = valor / taxaOrigem;
                double convertido = valorEmUSD * taxaDestino;

                System.out.printf("%.2f %s → %.2f %s\n", valor, moedaOrigem, convertido, moedaDestino);

                System.out.print("\nDigite 1 para sair ou qualquer outra tecla para continuar: ");
                String resposta = scanner.nextLine();
                if (resposta.equals("1")) {
                    sair = 1;
                    System.out.println("Programa encerrado.");
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }

    public static double getTaxa(Conversoes taxas, String moeda) {
        return switch (moeda) {
            case "USD" -> taxas.USD;
            case "BRL" -> taxas.BRL;
            case "ARS" -> taxas.ARS;
            case "BOB" -> taxas.BOB;
            case "CLP" -> taxas.CLP;
            case "COP" -> taxas.COP;
            default -> -1;
        };
    }
}
