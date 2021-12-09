package common;

public record Position(int horizontal, int depth, int aim) {

    public int calculateSum() {
        return horizontal * depth;
    }
}
