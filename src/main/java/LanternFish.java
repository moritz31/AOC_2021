public class LanternFish {
    private int timer;

    LanternFish(int initialTimer) {
        this.timer = initialTimer;
    }

    LanternFish simulateDay() {
        if (timer > 0) {
            timer -= 1;
        } else {
            timer = 6;
            return new LanternFish(8);
        }
        return null;
    }

}
