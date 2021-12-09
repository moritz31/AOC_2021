import java.util.ArrayList;
import java.util.List;

public class AvoidanceMap {

    int gameSize;
    List<List<Integer>> map = new ArrayList<>();

    AvoidanceMap(int size) {
        gameSize = size;
        for (int i = 0; i <= size; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j <= size; j++) {
                tempList.add(0);
            }
            map.add(tempList);
        }
    }

    public void insert(AvoidanceLine line) {
        SimplePoint start = line.start();
        SimplePoint end = line.end();

        List<SimplePoint> linePoints = new ArrayList<>();
        calculateLine(linePoints, start, end);

        for (SimplePoint point : linePoints) {
            Integer value = map.get(point.y()).get(point.x());
            map.get(point.y()).set(point.x(), value + 1);
        }
    }

    public List<SimplePoint> calculateLine(List<SimplePoint> linePoints, SimplePoint start, SimplePoint end) {
        linePoints.add(start);

        int directionX = Integer.signum(start.x() - end.x());
        int directionY = Integer.signum(start.y() - end.y());

        SimplePoint newPoint = new SimplePoint(start.x() - directionX, start.y() - directionY);
        if (newPoint.equals(end)) {
            linePoints.add(newPoint);
            return linePoints;
        } else {
            return calculateLine(linePoints, newPoint, end);
        }
    }

    void draw() {
        for (int i = 0; i <= gameSize; i++) {
            for (int j = 0; j <= gameSize; j++) {
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
    }

    public List<List<Integer>> getMap() {
        return map;
    }
}
