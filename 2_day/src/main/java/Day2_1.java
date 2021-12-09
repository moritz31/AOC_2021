import day21.SimpleNavigationPlanner;

public class Day2_1 {
    public static void main(String[] args) {
        SimpleNavigationPlanner navigationPlanner = new SimpleNavigationPlanner();

        navigationPlanner.loadNavigationPlan("navigationPlan");
        int position = navigationPlanner.doPlan();
        System.out.println(position);
    }
}
