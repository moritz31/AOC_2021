package day22;

import common.AbstractNavigationPlanner;
import common.NavigationCommand;
import common.Position;

import java.util.List;

public class ComplexNavigationPlanner extends AbstractNavigationPlanner {

    public List<NavigationCommand> getNavigationPlan() {
        return this.navigationPlan;
    }

    public int doPlan() {
        Position position = new Position(0, 0, 0);

        for (NavigationCommand command : this.navigationPlan) {
            position = this.evaluateCommand(command, position);
        }

        System.out.println(position);
        return position.calculateSum();
    }

    private Position evaluateCommand(NavigationCommand command, Position position) {
        return switch (command.direction()) {
            case UP -> new Position(position.horizontal(), position.depth(), position.aim() - command.amount());
            case DOWN -> new Position(position.horizontal(), position.depth(), position.aim() + command.amount());
            case FORWARD -> new Position(position.horizontal() + command.amount(), position.depth() + position.aim() * command.amount(), position.aim());
        };
    }
}
