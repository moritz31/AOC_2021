public class Day5 {
    public static void main(String[] args) {
        HydrothermalVentAvoidanceSystem avoidanceSystem = new HydrothermalVentAvoidanceSystem();

        avoidanceSystem.loadAvoidanceLines("avoidanceInput");
        long points = avoidanceSystem.draw();
        System.out.println(points);
    }
}
