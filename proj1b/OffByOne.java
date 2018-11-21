public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int result = y - x;
        if (result == 1 || result == -1) {
            return true;
        }
        return false;
    }
}
