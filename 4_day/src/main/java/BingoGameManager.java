import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.function.Predicate.not;

public class BingoGameManager {
    List<GameBoard> gameBoards = new ArrayList<>();
    Input gameInput;

    public void loadGame(String path) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(path);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line;
            boolean isFirstLine = true;
            List<List<GameField>> newGameboard = new ArrayList<>();
            while (nonNull(line = bufferedReader.readLine())) {
                if (isFirstLine) {
                    List<Integer> gameInputs = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
                    this.gameInput = new Input(gameInputs);
                    isFirstLine = false;
                    bufferedReader.readLine();
                } else {
                    if (line.equals("")) {
                        this.gameBoards.add(new GameBoard(new ArrayList<>(newGameboard)));
                        newGameboard.clear();
                    } else {
                        newGameboard.add(Arrays.stream(line.split(" "))
                                .filter(value -> !value.isEmpty())
                                .map(Integer::parseInt)
                                .map(value -> new GameField(value, false))
                                .toList());
                    }
                }
            }
            this.gameBoards.add(new GameBoard(newGameboard));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int playGame(boolean lastGameWonMode) {
        for (int input : this.gameInput.input()) {
            for (GameBoard board : this.gameBoards) {
                board.rows().stream()
                        .flatMap(Collection::stream)
                        .filter(gameField -> gameField.getValue() == input)
                        .forEach(GameField::setPlayed);
                if (!lastGameWonMode) {
                    boolean gameWon = this.evaluateWinCondition(board);
                    if (gameWon) {
                        return board.getPlayedSum() * input;
                    }
                } else {
                    boolean allBoardsFinished = this.gameBoards.stream()
                            .map(this::evaluateWinCondition).noneMatch(not(Boolean::booleanValue));
                    if(allBoardsFinished) {
                        return board.getPlayedSum() * input;
                    }
                }
            }

        }
        return 0;
    }

    private boolean evaluateWinCondition(GameBoard board) {
        return evaluateRow(board.rows()) || evaluateRow(board.transpose());
    }

    private boolean evaluateRow(List<List<GameField>> gameRows) {
        List<GameField> gameFields = gameRows.stream()
                .filter(row -> row.stream().filter(GameField::isPlayed).count() == GameBoard.GAME_SIZE)
                .findFirst()
                .orElse(null);
        return nonNull(gameFields);
    }
}
