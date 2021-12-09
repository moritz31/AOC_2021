package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class AbstractNavigationPlanner {

    protected List<NavigationCommand> navigationPlan;

    public void loadNavigationPlan(String plan) {
        InputStream resourceAsStream = AbstractNavigationPlanner.class.getResourceAsStream(plan);
        List<NavigationCommand> navigationCommands = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))){
            String line;
            while(nonNull(line = bufferedReader.readLine())) {
                navigationCommands.add(parseLine(line));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        this.navigationPlan = navigationCommands;
    }

    private NavigationCommand parseLine(String line) throws ParseException {
        String[] parts = line.split(" ");
        if(parts.length != 2) {
            throw new ParseException("",0);
        }
        return new NavigationCommand(NavigationDirection.valueOf(parts[0].toUpperCase()), Integer.parseInt(parts[1]));
    }

    protected List<NavigationCommand> getNavigationPlan() {
        return this.navigationPlan;
    }

    abstract protected int doPlan();
}
