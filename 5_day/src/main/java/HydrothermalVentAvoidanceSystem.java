import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Objects.nonNull;

public class HydrothermalVentAvoidanceSystem {
    private List<AvoidanceLine> avoidanceLines;
    public void loadAvoidanceLines(String path) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(path);
        List<AvoidanceLine> avoidanceLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line;
            while (nonNull(line = bufferedReader.readLine())) {
                String[] split = line.split("->");
                String[] startPoint = split[0].split(",");
                String[] endPoint = split[1].split(",");
                SimplePoint start = new SimplePoint(Integer.parseInt(startPoint[0].trim()), Integer.parseInt(startPoint[1].trim()));
                SimplePoint end = new SimplePoint(Integer.parseInt(endPoint[0].trim()), Integer.parseInt(endPoint[1].trim()));
                avoidanceLines.add(new AvoidanceLine(start, end));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.avoidanceLines = avoidanceLines;
    }

    public List<AvoidanceLine> getAvoidanceLines() {
        return avoidanceLines;
    }

    public long draw() {
        int fieldSize = findMaxNumber();
        AvoidanceMap avoidanceMap = new AvoidanceMap(fieldSize);

        for(AvoidanceLine line : avoidanceLines) {
            avoidanceMap.insert(line);
        }
        avoidanceMap.draw();

        return avoidanceMap.getMap().stream()
                .flatMap(Collection::stream)
                .filter(value -> value > 1)
                .count();
    }

    private int findMaxNumber() {
        int maxSize = 0;
        for(AvoidanceLine line : avoidanceLines) {
            maxSize = evaluateMaxSize(maxSize, line.start());
            maxSize =evaluateMaxSize(maxSize, line.end());
        }
        return maxSize;
    }

    private int evaluateMaxSize(int maxSize, SimplePoint point) {
        if(point.x() > maxSize) {
            maxSize = point.x();
        }
        if(point.y() > maxSize) {
            maxSize = point.y();
        }
        return maxSize;
    }
}
