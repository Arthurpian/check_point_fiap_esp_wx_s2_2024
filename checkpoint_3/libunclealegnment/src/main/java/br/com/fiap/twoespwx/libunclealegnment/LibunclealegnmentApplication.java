package br.com.fiap.twoespwx.libunclealegnment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class LibunclealegnmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibunclealegnmentApplication.class, args);
	}

	public class Sequence {
    private String sequenceUuid;
    private String sequence;

}



public class InputData {
    private String tracingUuid;
    private String format;
    private String method;
    private List<Sequence> sequences;

    // Getters e setters
}


public class Result {
    private double distanceScore;
    private double similarityScore;
    private String observations;
    private int length;
    private String format;
    private String processingTime;
    private LocalDateTime createdAt;
    private String status;

    // Getters e setters
}

public class Result {
    private double distanceScore;
    private double similarityScore;
    private String observations;
    private int length;
    private String format;
    private String processingTime;
    private LocalDateTime createdAt;
    private String status;

    // Getters e setters
}

public class OutputData {
    private String tracingId;
    private String alignmentUuid;
    private MethodInfo method;
    private List<Result> result;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters e setters
}

public class MethodInfo {
    private String name;
    private String about;
    private List<String> references;

    // Getters e setters
}

@RestController
@RequestMapping("/v1/alignment")
public class AlignmentController {

    @PostMapping("/distance")
    public OutputData calculateDistance(@RequestBody InputData inputData) {
        OutputData outputData = new OutputData();
        
        // Gera UUID para o alignment
        outputData.setAlignmentUuid(UUID.randomUUID().toString());
        outputData.setTracingId(inputData.getTracingUuid());

        // Configura informações do método
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setName("HAMMING_DISTANCE");
        methodInfo.setAbout("Calcula a diferença entre duas cadeias de mesmo tamanho, contando o número de posições onde os caracteres correspondentes diferem.");
        methodInfo.setReferences(List.of(
                "https://www.sciencedirect.com/topics/computer-science/hamming-distance",
                "https://en.wikipedia.org/wiki/Hamming_distance"
        ));
        outputData.setMethod(methodInfo);

        // Calcular distância e similaridade entre as sequências
        // (implementaremos o cálculo no serviço)
        Result result = new Result();
        result.setDistanceScore(1.0); // exemplo de valor
        result.setSimilarityScore(0.9375); // exemplo de valor
        result.setFormat(inputData.getFormat());
        result.setLength(inputData.getSequences().get(0).getSequence().length());
        result.setProcessingTime("0.005s");
        result.setCreatedAt(LocalDateTime.now());
        result.setStatus("PROCESSED");

        outputData.setResult(List.of(result));
        outputData.setStatus("PROCESSED");
        outputData.setCreatedAt(LocalDateTime.now());
        outputData.setUpdatedAt(LocalDateTime.now());

        return outputData;
    }
}

@Service
public class AlignmentService {

    public int calculateHammingDistance(String seq1, String seq2) {
        if (seq1.length() != seq2.length()) {
            throw new IllegalArgumentException("As sequências devem ter o mesmo comprimento.");
        }

        int distance = 0;
        for (int i = 0; i < seq1.length(); i++) {
            if (seq1.charAt(i) != seq2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public double calculateSimilarityScore(int hammingDistance, int length) {
        return 1 - ((double) hammingDistance / length);
    }
}

@RestController
@RequestMapping("/v1/alignment")
public class AlignmentController {

    @Autowired
    private AlignmentService alignmentService;

    @PostMapping("/distance")
    public OutputData calculateDistance(@RequestBody InputData inputData) {
        // ... (configuração do outputData e methodInfo)

        // Calcula a distância de Hamming e a similaridade
        String seq1 = inputData.getSequences().get(0).getSequence();
        String seq2 = inputData.getSequences().get(1).getSequence();

        int hammingDistance = alignmentService.calculateHammingDistance(seq1, seq2);
        double similarityScore = alignmentService.calculateSimilarityScore(hammingDistance, seq1.length());

        Result result = new Result();
        result.setDistanceScore(hammingDistance);
        result.setSimilarityScore(similarityScore);
        // ... (restante da configuração do resultado)

        outputData.setResult(List.of(result));
        return outputData;
    }
}
}
