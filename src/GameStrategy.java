public interface GameStrategy {
    void initialize();
    boolean isGameOver();
    void move(String direction);

    int getScore();

    GameTile[][] getBoard();
}
