import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HydrothermalVentAvoidanceSystemTest {

    HydrothermalVentAvoidanceSystem avoidanceSystem = new HydrothermalVentAvoidanceSystem();

    @Test
    void testLoadAvoidanceLines() {
        avoidanceSystem.loadAvoidanceLines("sampleInput");
        assertEquals(10, avoidanceSystem.getAvoidanceLines().size());
    }

    @Test
    void testAvoidancePointCalculation() {
        avoidanceSystem.loadAvoidanceLines("sampleInput");
        long count = avoidanceSystem.draw();
        assertEquals(12,count);
    }
}
