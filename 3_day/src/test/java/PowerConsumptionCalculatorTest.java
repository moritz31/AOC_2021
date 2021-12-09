import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerConsumptionCalculatorTest {

    List<DiagnosticInformation> diagnosticInformations = List.of(
            new DiagnosticInformation("00100"),
            new DiagnosticInformation("11110"),
            new DiagnosticInformation("10110"),
            new DiagnosticInformation("10111"),
            new DiagnosticInformation("10101"),
            new DiagnosticInformation("01111"),
            new DiagnosticInformation("00111"),
            new DiagnosticInformation("11100"),
            new DiagnosticInformation("10000"),
            new DiagnosticInformation("11001"),
            new DiagnosticInformation("00010"),
            new DiagnosticInformation("01010"));

    PowerConsumptionCalculator consumptionCalculator = new PowerConsumptionCalculator();

    @Test
    void loadDiagnosticInformationTest() {
        consumptionCalculator.loadDiagnosticData("sampleDiagnostic");
        assertEquals(consumptionCalculator.getDiagnosticInformations(),this.diagnosticInformations);
    }

    @Test
    void calculatePowerConsumptionTest() {
        consumptionCalculator.loadDiagnosticData("sampleDiagnostic");
        int powerConsumption = consumptionCalculator.getPowerConsumption();
        assertEquals(powerConsumption,198);
    }

    @Test
    void calculateLifeSupportRatingTest() {
        consumptionCalculator.loadDiagnosticData("sampleDiagnostic");
        int lifeSupportRating = consumptionCalculator.getLifeSupportRating();
        assertEquals(lifeSupportRating, 230);
    }
}
