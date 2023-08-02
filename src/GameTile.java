public class GameTile {
    private int value;

    public GameTile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean canCombineWith(GameTile other) {
        return other != null && (this.value == other.value || this.value + other.value == 3);
    }

    public void setValue(int i) {
    }
}
