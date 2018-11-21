public class OffByN implements CharacterComparator {
    private int n;

    OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int result = y - x;
        if (result == n || result == -n) {
            return true;
        }
        return false;
    }
}
