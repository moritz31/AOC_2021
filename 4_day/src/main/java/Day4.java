public class Day4 {
    public static void main(String[] args) {
        BingoGameManager bingoController = new BingoGameManager();
        bingoController.loadGame("gameInput");

        int winSum = bingoController.playGame(false);
        int lastWinSum = bingoController.playGame(true);
        System.out.println(winSum);
        System.out.println(lastWinSum);
    }
}
