import day21.SimpleNavigationPlanner;
import day22.ComplexNavigationPlanner;

public class Day2_2 {
    public static void main(String[] args) {
        ComplexNavigationPlanner navigationPlanner = new ComplexNavigationPlanner();

        navigationPlanner.loadNavigationPlan("navigationPlan");
        int position = navigationPlanner.doPlan();
        System.out.println(position);
    }
}
