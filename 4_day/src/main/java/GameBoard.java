import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.function.Predicate.not;

public record GameBoard(List<List<GameField>> rows) {
    public static final int GAME_SIZE = 5;


    int getPlayedSum() {
        return rows.stream()
                .flatMap(Collection::stream)
                .filter(not(GameField::isPlayed))
                .map(GameField::getValue)
                .reduce(0, Integer::sum);
    }

    List<List<GameField>> transpose() {
        List<List<GameField>> transposedList = new ArrayList<>();
        for (int i = 0; i < GAME_SIZE; i++) {
            List<GameField> tempList = new ArrayList<>();
            for (List<GameField> row : rows) {
                tempList.add(row.get(i));
            }
            transposedList.add(tempList);
        }
        return transposedList;
    }
}
