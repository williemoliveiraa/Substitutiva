import java.util.Random;

public class Game2048 implements GameStrategy {
    private GameTile[][] board;
    private int score;
    private Random random;

    public Game2048() {
        board = new GameTile[4][4];
        random = new Random();
    }
    // Implementação do método getBoard() da interface GameStrategy
    @Override
    public GameTile[][] getBoard() {
        return board;
    }

    public void initialize() {
        board = new GameTile[4][4];
        score = 0;
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        int emptyCells = countEmptyCells();
        if (emptyCells > 0) {
            int index = random.nextInt(emptyCells);
            int value = (random.nextInt(10) < 9) ? 2 : 4; // 90% chance for 2, 10% chance for 4
            int count = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == null) {
                        if (count == index) {
                            board[i][j] = new GameTile(value);
                            return;
                        }
                        count++;
                    }
                }
            }
        }
    }

    private int countEmptyCells() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isGameOver() {
        return countEmptyCells() == 0 && !canTilesCombine();
    }

    private boolean canTilesCombine() {
        // Check if any two adjacent tiles can be combined according to 2048 rules
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null && board[i][j].canCombineWith(board[i + 1][j])) {
                    return true;
                }
                if (board[i][j] != null && board[i][j].canCombineWith(board[i][j + 1])) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean has2048Tile() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && board[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    public void move(String direction) {
        // Converter a entrada para minúsculas para tratar tanto maiúsculas quanto minúsculas
        direction = direction.toLowerCase();

        // Verificar se a entrada é válida
        if (direction.equals("w")) {
            moveUp();
        } else if (direction.equals("s")) {
            moveDown();
        } else if (direction.equals("a")) {
            moveLeft();
        } else if (direction.equals("d")) {
            moveRight();
        } else {
            // Se a entrada for inválida, exibir mensagem de erro
            System.out.println("Movimento inválido. Digite w, s, a ou d.");
        }
    }

    private void moveUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if (board[i][j] != null) {
                    int value = board[i][j].getValue();
                    int row = i;
                    while (row > 0 && (board[row - 1][j] == null || board[row - 1][j].canCombineWith(board[row][j]))) {
                        if (board[row - 1][j] != null) {
                            value += board[row - 1][j].getValue();
                        }
                        board[row - 1][j] = new GameTile(value);
                        board[row][j] = null;
                        row--;
                    }
                }
            }
        }
        addRandomTile();
        int mergedValue = 0;
        score += mergedValue;

        // Check for victory
        if (has2048Tile()) {
            System.out.println("Congratulations! You reached 2048!");
        }
    }

    private void moveDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (board[i][j] != null) {
                    int value = board[i][j].getValue();
                    int row = i;
                    while (row < 3 && (board[row + 1][j] == null || board[row + 1][j].canCombineWith(board[row][j]))) {
                        if (board[row + 1][j] != null) {
                            value += board[row + 1][j].getValue();
                        }
                        board[row + 1][j] = new GameTile(value);
                        board[row][j] = null;
                        row++;
                    }
                }
            }
        }
        addRandomTile();
    }

    private void moveLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[i][j] != null) {
                    int value = board[i][j].getValue();
                    int col = j;
                    while (col > 0 && (board[i][col - 1] == null || board[i][col - 1].canCombineWith(board[i][col]))) {
                        if (board[i][col - 1] != null) {
                            value += board[i][col - 1].getValue();
                        }
                        board[i][col - 1] = new GameTile(value);
                        board[i][col] = null;
                        col--;
                    }
                }
            }
        }
        addRandomTile();
    }

    private void moveRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if (board[i][j] != null) {
                    int value = board[i][j].getValue();
                    int col = j;
                    while (col < 3 && (board[i][col + 1] == null || board[i][col + 1].canCombineWith(board[i][col]))) {
                        if (board[i][col + 1] != null) {
                            value += board[i][col + 1].getValue();
                        }
                        board[i][col + 1] = new GameTile(value);
                        board[i][col] = null;
                        col++;
                    }
                }
            }
        }
        addRandomTile();
    }

    public int getScore() {
        return score;
    }
}