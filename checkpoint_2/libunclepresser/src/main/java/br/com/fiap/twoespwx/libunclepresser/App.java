/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Grupo: Batata Doce
 * Autores: 
 *      - João da Silva     -   jaodasilva@example.com
 *      - Jose da Silva     -   zedasilva@example.com
 *      - Maria da Silva    -   marydasilva@example.com
 *      - Monica da Silva   -   nicadasilva@example.com
 */

package br.com.fiap.twoespwx.libunclepresser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java -jar <path/to/jar/file> <path/to/input> <path/to/output>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            // Ler o conteúdo do arquivo de entrada
            String nucleotides = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // Aplicar o algoritmo RLE
            String compressedData = compressRLE(nucleotides);

            // Escrever o conteúdo comprimido no arquivo de saída
            Files.write(Paths.get(outputFilePath), compressedData.getBytes());

            // Exibir a interface textual com informações de compressão
            displayInterface(inputFilePath, outputFilePath, nucleotides, compressedData);

        } catch (IOException e) {
            System.out.println("Erro ao acessar os arquivos: " + e.getMessage());
        }
    }

    // Função para comprimir a sequência usando Run-Length Encoding (RLE)
    private static String compressRLE(String data) {
        StringBuilder compressed = new StringBuilder();

        int count = 1;
        for (int i = 1; i < data.length(); i++) {
            if (data.charAt(i) == data.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(data.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append(data.charAt(data.length() - 1)).append(count);
        
        return compressed.toString();
    }

    // Função para exibir a interface textual conforme o modelo
    private static void displayInterface(String inputFilePath, String outputFilePath, String originalData, String compressedData) throws IOException {
        long inputFileSize = Files.size(Paths.get(inputFilePath));
        long outputFileSize = compressedData.getBytes().length;

        Map<Character, Integer> frequencyMap = calculateFrequencies(originalData);

        System.out.println(" -----------------------------------------------------------");
        System.out.println("|           LIB UNCLE PRESSER - GRUPO BATATA-DOCE           |");
        System.out.println("|-----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.printf("| INPUT  FILENAME: %s                                        |\n", Paths.get(inputFilePath).getFileName());
        System.out.printf("| OUTPUT FILENAME: %s                                       |\n", Paths.get(outputFilePath).getFileName());
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.printf("| INPUT FILE SIZE: %d KB                                   |\n", inputFileSize / 1024);
        System.out.println("|                                                           |");
        System.out.printf("| TOTAL INPUT CHARACTERS: %d                              |\n", originalData.length());
        System.out.println("|                                                           |");
        System.out.println("| FREQUENCIES:                                              |");

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / originalData.length();
            System.out.printf("| %c: %d (%.2f%%)                                       |\n", entry.getKey(), entry.getValue(), percentage);
        }

        System.out.println("|                                                           |");
        System.out.println("| OPTIONS:                                                  |");
        System.out.println("|                                                           |");
        System.out.println("| ALGORITHM: Run-Length Encoding (RLE)                      |");
        System.out.println("| TEXT-CODIFICATION: UTF-8                                  |");
        System.out.printf("| COMPRESSION RATE: ~= %.2f%%                             |\n", ((double) outputFileSize / inputFileSize) * 100);
        System.out.println("|                                                           |");
        System.out.printf("| OUTPUT FILE SIZE: %d BYTES                              |\n", outputFileSize);
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("| SCORE: WELL-DONE                                          |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
    }

    // Função para calcular a frequência dos caracteres
    private static Map<Character, Integer> calculateFrequencies(String data) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : data.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }
    
}

