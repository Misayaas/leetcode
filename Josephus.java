public class Josephus {
    public int iceBreakingGame(int num, int target) {
        int x = 0;
        for (int i = 2; i <= num; i++) {
            x = (x + target) % i;
        }
        return x;
    }
}
