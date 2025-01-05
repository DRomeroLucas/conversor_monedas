package com.dsrl.conversor_monedas.principal;

import com.dsrl.conversor_monedas.calculo.Calculo;
import com.dsrl.conversor_monedas.calculo.Conversion;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int opcion = 0;
        String apiKey = "e125caf964852efbfe73049d";
        String url;
        String primeraDivisa = "", segundaDivisa = "", json;
        double monto, resultado;

        // Creación protocolo HTTPs
        HttpClient client = HttpClient.newHttpClient();

        Scanner scanner = new Scanner(System.in);

        while (opcion != 7) {
            // Menu
            System.out.println("****************************************");
            System.out.println("Bienvenido al conversor de monedas!");
            System.out.println("Por favor elija un procedimiento de cambio:");
            System.out.println("1. Dólar Américano => Peso Argentino");
            System.out.println("2. Peso Argentino => Dólar Américano");
            System.out.println("3. Real Brasileño => Dólar Américano");
            System.out.println("4. Dólar Américano => Real Brasileño");
            System.out.println("5. Dólar Américano => Peso Colombiano");
            System.out.println("6. Peso Colombiano => Dólar Américano");
            System.out.println("7. Salir");
            System.out.println("Elija una opción válida.");
            System.out.println("****************************************");


            try {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > 7) {
                    System.out.println("Elija una opción válida entre el 1 a 7.");
                    continue;
                } else if ( opcion == 7 ) {
                    System.out.println("Gracias por usar nuestros servicios!");
                    break;
                } else if (opcion > 1 || opcion < 6){
                    System.out.println("Por favor, ingrese la cantidad a convertir: ");
                    monto = scanner.nextDouble();

                    Calculo calculo = new Calculo(opcion);
                    try {
                        calculo.conversionDeDivisa();
                        primeraDivisa = calculo.getPrimeraDivisa();
                        segundaDivisa = calculo.getSegundaDivisa();
                    }catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    url = "https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+primeraDivisa+"/"+segundaDivisa;
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();

                    // Enviar request
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    // Respuesta
                    json = response.body();
                    System.out.println("Respuesta API: "+json);

                    // Análisis de la respuesta en formato JSON
                    Gson gson = new Gson();
                    Conversion conversion = gson.fromJson(json, Conversion.class);

                    resultado = monto * conversion.getConversion_rate();

                    System.out.println("El monto de "+monto+" ("+primeraDivisa+")"+" equivale a "+resultado+" ("+segundaDivisa+").");

                } else {
                    System.out.println("Opciones no válidas, cerrando el programa, gracias por usar nuestros servicios");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
