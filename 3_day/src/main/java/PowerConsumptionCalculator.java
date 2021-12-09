import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class PowerConsumptionCalculator {

    private List<DiagnosticInformation> diagnosticInformations;

    public void loadDiagnosticData(String plan) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(plan);
        List<DiagnosticInformation> navigationCommands = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line;
            while (nonNull(line = bufferedReader.readLine())) {
                navigationCommands.add(new DiagnosticInformation(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.diagnosticInformations = navigationCommands;
    }

    public int getPowerConsumption() {
        List<Map<Boolean,Long>> splittedInformation = this.getSplittedMapByQuantity();

        String gamma = "";
        String epsilon = "";
        for (Map<Boolean, Long> countMap : splittedInformation) {
            gamma = gamma.concat(Collections.max(countMap.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey() ? "1" : "0");
            epsilon = epsilon.concat(Collections.min(countMap.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey() ? "1" : "0");
        }

        return Integer.parseInt(gamma,2) * Integer.parseInt(epsilon,2);
    }

    private List<Map<Boolean, Long>> getSplittedMapByQuantity() {
        List<Map<Boolean, Long>> splittedInformation = new LinkedList<>();
        int diagnosticLength = this.diagnosticInformations.stream().findFirst().orElse(new DiagnosticInformation("")).diagnostic().length();
        for (int i = 0; i < diagnosticLength; i++) {
            int finalI = i;
            splittedInformation.add(diagnosticInformations.stream()
                    .map(info -> info.getBitAtPosition(finalI))
                    .collect(partitioningBy(value -> Objects.equals(value, "0"), counting())));
        }
        return splittedInformation;
    }

    public int getLifeSupportRating() {
        int oxygenGeneratorRating = getOxygenGeneratorRating(this.diagnosticInformations, 0);
        int co2ScrubberRating = getCo2ScrubberRating(this.diagnosticInformations, 0);
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    public int getOxygenGeneratorRating(List<DiagnosticInformation> informations, int bitToConsider) {
        Map<Boolean, List<DiagnosticInformation>> collect = informations.stream().collect(partitioningBy(value -> Objects.equals(value.getBitAtPosition(bitToConsider), "0")));
        List<DiagnosticInformation> maxEntries = Collections.max(collect.entrySet(), Comparator.comparing(entry -> entry.getValue().size())).getValue();
        if(maxEntries.size() > 1) {
            return getOxygenGeneratorRating(maxEntries,bitToConsider+1);
        } else {
            return Integer.parseInt(maxEntries.get(0).diagnostic(),2);
        }
    }
    public int getCo2ScrubberRating(List<DiagnosticInformation> informations, int bitToConsider) {
        Map<Boolean, List<DiagnosticInformation>> collect = informations.stream().collect(partitioningBy(value -> Objects.equals(value.getBitAtPosition(bitToConsider), "1")));
        List<DiagnosticInformation> minEntries = Collections.min(collect.entrySet(), Comparator.comparing(entry -> entry.getValue().size())).getValue();
        if(minEntries.size() > 1) {
            return getCo2ScrubberRating(minEntries,bitToConsider+1);
        } else {
            return Integer.parseInt(minEntries.get(0).diagnostic(),2);
        }
    }


    public List<DiagnosticInformation> getDiagnosticInformations() {
        return this.diagnosticInformations;
    }
}
