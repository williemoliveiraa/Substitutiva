import java.util.Random;

public class Game1024 implements GameStrategy {
    private GameTile[][] board;
    private int score;
    private Random random;

    public Game1024() {
        board = new GameTile[4][4];
        score = 0;
        random = new Random();
    }

    @Override
    public void initialize() {
        // Lógica para gerar os números iniciais do jogo 1024
        addRandomTile();
        addRandomTile();
    }

    @Override
    public boolean isGameOver() {
        // Lógica para verificar se o jogo 1024 acabou (quando não há mais movimentos possíveis)
        return !canMove();
    }

    @Override
    public void move(String direction) {
        // Lógica para mover os blocos na direção especificada
        switch (direction) {
            case "w":
                moveUp();
                break;
            case "s":
                moveDown();
                break;
            case "a":
                moveLeft();
                break;
            case "d":
                moveRight();
                break;
            default:
                System.out.println("Movimento inválido. Digite w, s, a ou d.");
                break;
        }
    }

    private void moveUp() {
        // Lógica para mover os blocos para cima e combinar blocos
        for (int col = 0; col < 4; col++) {
            int prevRow = -1;
            for (int row = 1; row < 4; row++) {
                if (board[row][col] != null) {
                    int newRow = row;
                    while (newRow > 0 && (board[newRow - 1][col] == null || canCombine(newRow, col, newRow - 1, col))) {
                        if (board[newRow - 1][col] != null) {
                            score += board[newRow][col].getValue();
                        }
                        combineTiles(newRow, col, newRow - 1, col);
                        newRow--;
                    }
                }
            }
        }
        addRandomTile();
    }

    private void moveDown() {
        // Lógica para mover os blocos para baixo e combinar blocos
        for (int col = 0; col < 4; col++) {
            int prevRow = 4;
            for (int row = 2; row >= 0; row--) {
                if (board[row][col] != null) {
                    int newRow = row;
                    while (newRow < 3 && (board[newRow + 1][col] == null || canCombine(newRow, col, newRow + 1, col))) {
                        if (board[newRow + 1][col] != null) {
                            score += board[newRow][col].getValue();
                        }
                        combineTiles(newRow, col, newRow + 1, col);
                        newRow++;
                    }
                }
            }
        }
        addRandomTile();
    }

    private void moveLeft() {
        // Lógica para mover os blocos para a esquerda e combinar blocos
        for (int row = 0; row < 4; row++) {
            int prevCol = -1;
            for (int col = 1; col < 4; col++) {
                if (board[row][col] != null) {
                    int newCol = col;
                    while (newCol > 0 && (board[row][newCol - 1] == null || canCombine(row, newCol, row, newCol - 1))) {
                        if (board[row][newCol - 1] != null) {
                            score += board[row][newCol].getValue();
                        }
                        combineTiles(row, newCol, row, newCol - 1);
                        newCol--;
                    }
                }
            }
        }
        addRandomTile();
    }

    private void moveRight() {
        // Lógica para mover os blocos para a direita e combinar blocos
        for (int row = 0; row < 4; row++) {
            int prevCol = 4;
            for (int col = 2; col >= 0; col--) {
                if (board[row][col] != null) {
                    int newCol = col;
                    while (newCol < 3 && (board[row][newCol + 1] == null || canCombine(row, newCol, row, newCol + 1))) {
                        if (board[row][newCol + 1] != null) {
                            score += board[row][newCol].getValue();
                        }
                        combineTiles(row, newCol, row, newCol + 1);
                        newCol++;
                    }
                }
            }
        }
        addRandomTile();
    }

    private boolean canCombine(int row, int col, int newRow, int newCol) {
        // Lógica para verificar se dois blocos podem ser combinados
        if (board[newRow][newCol] == null) {
            return true;
        }
        return board[newRow][newCol].getValue() == board[row][col].getValue();
    }

    private void combineTiles(int row, int col, int newRow, int newCol) {
        // Lógica para combinar dois blocos em um único bloco
        if (board[newRow][newCol] == null) {
            board[newRow][newCol] = board[row][col];
        } else {
            board[newRow][newCol].setValue(board[newRow][newCol].getValue() + board[row][col].getValue());
        }
        board[row][col] = null;
    }

    private void addRandomTile() {
        // Lógica para adicionar um novo bloco aleatório ao tabuleiro
        int emptyTiles = 0;
        for (GameTile[] row : board) {
            for (GameTile tile : row) {
                if (tile == null) {
                    emptyTiles++;
                }
            }
        }

        if (emptyTiles == 0) {
            return;
        }

        int randomValue = random.nextInt(10) < 9 ? 2 : 4;
        int randomPosition = random.nextInt(emptyTiles) + 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    randomPosition--;
                    if (randomPosition == 0) {
                        board[i][j] = new GameTile(randomValue);
                        return;
                    }
                }
            }
        }
    }

    private boolean has1024Tile() {
        // Lógica para verificar se existe um bloco com o número 1024 no tabuleiro
        for (GameTile[] row : board) {
            for (GameTile tile : row) {
                if (tile != null && tile.getValue() == 1024) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public GameTile[][] getBoard() {
        return board;
    }

    private boolean canMove() {
        // Lógica para verificar se existem movimentos possíveis no tabuleiro
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == null) {
                    return true;
                }
                if (row > 0 && board[row - 1][col] == null) {
                    return true;
                }
                if (row < 3 && board[row + 1][col] == null) {
                    return true;
                }
                if (col > 0 && board[row][col - 1] == null) {
                    return true;
                }
                if (col < 3 && board[row][col + 1] == null) {
                    return true;
                }
                if (row > 0 && board[row - 1][col] != null && board[row - 1][col].getValue() == board[row][col].getValue()) {
                    return true;
                }
                if (row < 3 && board[row + 1][col] != null && board[row + 1][col].getValue() == board[row][col].getValue()) {
                    return true;
                }
                if (col > 0 && board[row][col - 1] != null && board[row][col - 1].getValue() == board[row][col].getValue()) {
                    return true;
                }
                if (col < 3 && board[row][col + 1] != null && board[row][col + 1].getValue() == board[row][col].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
