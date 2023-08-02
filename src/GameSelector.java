import java.util.Scanner;

public class GameSelector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Escolha o jogo:");
            System.out.println("1 - Threes");
            System.out.println("2 - 2048");
            System.out.println("3 - 1024");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ThreesGame game1 = new ThreesGame();
                    playGame(game1);
                    validChoice = true;
                    break;
                case 2:
                    Game2048 game2 = new Game2048();
                    playGame(game2);
                    validChoice = true;
                    break;
                case 3:
                    Game1024 game3 = new Game1024();
                    playGame(game3);
                    validChoice = true;
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, escolha 1, 2 ou 3.");
            }
        }
    }
    private static void playGame(GameStrategy game) {
        game.initialize();
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            printBoard(game);
            System.out.println("Digite um movimento (w - cima, s - baixo, a - esquerda, d - direita): ");
            String move = scanner.nextLine().toLowerCase();
            game.move(move);
        }

        System.out.println("Fim de jogo! Sua pontuação final: " + game.getScore());
    }

    private static void printBoard(GameStrategy game) {
        System.out.println("Pontuação: " + game.getScore());
        GameTile[][] board = game.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                GameTile tile = board[i][j];
                System.out.print(tile != null ? tile.getValue() : " " + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
