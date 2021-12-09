public class Day3 {

    public static void main(String[] args) {
        PowerConsumptionCalculator powerConsumptionCalculator = new PowerConsumptionCalculator();
        powerConsumptionCalculator.loadDiagnosticData("diagnosticInput");
        int powerConsumption = powerConsumptionCalculator.getPowerConsumption();
        int lifeSupportRating = powerConsumptionCalculator.getLifeSupportRating();

        System.out.println(powerConsumption);
        System.out.println(lifeSupportRating);
    }
}
