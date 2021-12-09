public class GameField {

    private final int value;
    private boolean played;

    GameField(int value, boolean played) {
        this.value = value;
        this.played = played;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isPlayed() {
        return this.played;
    }

    void setPlayed() {
        this.played = true;
    }

    @Override
    public String toString() {
        return value + (this.played ? "*" : "");
    }
}
