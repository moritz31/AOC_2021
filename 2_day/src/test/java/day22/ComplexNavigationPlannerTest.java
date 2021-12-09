package day22;

import common.NavigationCommand;
import common.NavigationDirection;
import day21.SimpleNavigationPlanner;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexNavigationPlannerTest {

    List<NavigationCommand> simpleNavigationPlan = List.of(
            new NavigationCommand(NavigationDirection.FORWARD, 5),
            new NavigationCommand(NavigationDirection.DOWN, 5),
            new NavigationCommand(NavigationDirection.FORWARD, 8),
            new NavigationCommand(NavigationDirection.UP, 3),
            new NavigationCommand(NavigationDirection.DOWN, 8),
            new NavigationCommand(NavigationDirection.FORWARD, 2)
    );

    ComplexNavigationPlanner planner = new ComplexNavigationPlanner();

    @Test
    void testReadSimpleNavigation() {
        planner.loadNavigationPlan("samplePlan");
        assertEquals(planner.getNavigationPlan(), simpleNavigationPlan);
    }

    @Test
    void testSimpleNavigation() {
        planner.loadNavigationPlan("samplePlan");
        int position = planner.doPlan();

        assertEquals(position, 900);
    }
}
