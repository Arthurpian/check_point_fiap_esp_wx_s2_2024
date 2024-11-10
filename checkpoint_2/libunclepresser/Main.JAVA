package br.com.fiap.twoespwx.libunclegerardo;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Verifica se os argumentos estão corretos
        if (args.length != 2 || !args[0].startsWith("size:") || !args[1].startsWith("output:")) {
            System.out.println("Uso: java -jar <jar-file> size:<numero-inteiro-maior-que-zero> output:<nome-do-arquivo-de-saida>.txt");
            return;
        }

        // Extrai o tamanho da sequência e o nome do arquivo de saída
        int size = Integer.parseInt(args[0].substring(5));
        String outputFileName = args[1].substring(7);

        if (size <= 0) {
            System.out.println("O tamanho da sequência deve ser maior que zero.");
            return;
        }

        // Gera a sequência de nucleotídeos
        NucleotideoRandomGenerator generator = new RandomNucleotideGenerator();
        String sequence = generator.generate(size);

        // Salva a sequência em um arquivo de saída
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(sequence);
            System.out.println("Sequência gerada e salva em " + outputFileName);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
