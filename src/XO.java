import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class XO {

    public static void main(String[] args) throws IOException {
        System.out.println("Game started");
        drawField();
        while (checkGame() == '#' && canMove()) {
            humanMove();
            botMove();
            drawField();
        }
        if (checkGame() == 'X') {
            System.out.println("YOU WIN");
        }
        if (checkGame() == '0') {
            System.out.println("BOT WIN");
        }
        if (checkGame() == '#' && !canMove()) {
            System.out.println("NOBODY WIN");
        }
    }

    //initBattleField
    public static char[][] field = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    //drawBattleField
    public static void drawField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println();
        }
    }

    //doHumanMove
    public static void humanMove() throws IOException {
        int x, y;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ввод x (1..3): ");
        x = Integer.parseInt(reader.readLine()) - 1;
        System.out.println("ввод y (1..3): ");
        y = Integer.parseInt(reader.readLine()) - 1;
        while (field[x][y] == '0' || field[x][y] == 'X' || x < 0 || x > 2 || y < 0 || y > 2) {
            System.out.println("ввод x (1..3): ");
            x = Integer.parseInt(reader.readLine()) - 1;
            System.out.println("ввод y (1..3): ");
            y = Integer.parseInt(reader.readLine()) - 1;
        }
        field[x][y] = 'X';
    }

    //doBotMove
    public static void botMove() {
        int x = (int) (Math.random() * 3);
        int y = (int) (Math.random() * 3);

        while (field[x][y] == '0' || field[x][y] == 'X') {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
        }
        field[x][y] = '0';
    }

    //moveAvailable
    public static boolean canMove() {
        boolean abilityToMove = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    abilityToMove = true;
                    break;
                }
            }
            if (abilityToMove)
                break;
        }
        return abilityToMove;
    }

    //checkGameState
    public static char checkGame() {
        char winner = '#';

        // horizontal
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != ' ') {
                winner = field[i][0];
                break;
            }
        }

        // vertical
        if (winner == '#') {
            for (int i = 0; i < 3; i++) {
                if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != ' ') {
                    winner = field[0][i];
                    break;
                }
            }
        }

        // main diagonal
        if (winner == '#') {
            if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != ' ') {
                winner = field[0][0];
            }
        }

        // another diagonal
        if (winner == '#') {
            if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != ' ') {
                winner = field[0][2];
            }
        }
        //return the winner or none
        return winner;
    }
}
