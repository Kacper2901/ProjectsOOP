import java.util.Scanner;
import java.lang.Thread;

final int SAME_BOARD_LIMIT = 5;

class GameOfLife {
    public Board board;

    GameOfLife(Board b) {
        this.board = b;
    }

    /**
     * Counts the number of living neighbors for the given cell.
     */
    public int countLivingNeighbors(int x, int y) {
        int livingCellsCount = 0;
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                if ((x + i >= 0 && x + i <= board.sizeX - 1) &&
                        (y + j >= 0 && y + j <= board.sizeY - 1) &&
                        board.pastBoard[y + j][x + i] == board.ALIVE) {
                    livingCellsCount++;
                }
            }
        }
        if (board.pastBoard[y][x] == board.ALIVE) livingCellsCount--;
        return livingCellsCount;
    }

    /**
     * Performs one step of the simulation. The rules of the game are applied to the board.
     */
    public void simulationStep() {
        int livingCells;
        board.pastBoard = board.copyBoard(board);
        for (int i = 0; i < board.sizeY; i++) {
            for (int j = 0; j < board.sizeX; j++) {
                livingCells = countLivingNeighbors(j, i);
                if ((livingCells < 2 || livingCells > 3) && board.pastBoard[i][j] == board.ALIVE)
                    board.newBoard[i][j] = board.DEAD;
                if (livingCells == 3 && board.pastBoard[i][j] == board.DEAD) board.newBoard[i][j] = board.ALIVE;
                if ((livingCells == 2 || livingCells == 3) && board.pastBoard[i][j] == board.ALIVE)
                    board.newBoard[i][j] = board.ALIVE;
            }
        }
        System.out.print("\033[H\033[2J");
        print("\n");
        System.out.flush();
        board.DisplayBoard(board.newBoard);
    }

    /**
     * Checks whether the previous board state is the same as the new one.
     */
    public boolean sameBoard() {
        for (int i = 0; i < board.sizeY; i++) {
            for (int j = 0; j < board.sizeX; j++) {
                if (board.pastBoard[i][j] != board.newBoard[i][j]) return false;
            }
        }
        return true;
    }

    public void startSimulation() throws InterruptedException {
        int sameBoardCount = 0;
        while (true) {
            simulationStep();
            if (sameBoard()) sameBoardCount += 1;
            else sameBoardCount = 0;
            if (sameBoardCount == SAME_BOARD_LIMIT) {
                print("The same board state has occurred " + SAME_BOARD_LIMIT + " times. Terminating the simulation.");
                break;
            }
            Thread.sleep(500);
        }
    }
}

static class Board {
    public int sizeX;
    public int sizeY;
    char[][] newBoard;
    char[][] pastBoard;

    final char DEAD = '□';
    final char ALIVE = '■';

    public Board(int SizeX, int SizeY) {
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        int x;
        int y;
        this.newBoard = new char[sizeY][sizeX];
        this.pastBoard = new char[sizeY][sizeX];
        Scanner scanner = new Scanner(System.in);

        /* Board initialization */
        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                newBoard[i][j] = DEAD;
                pastBoard[i][j] = DEAD;
            }
        }

        while (true) {
          try {
              println("Enter coordinates or press 'q' if you've finished");
              print("Enter x of living cell (0 < x < " + (this.sizeX - 1) + "): ");
              x = scanner.nextInt();
              while (x < 0 || x > this.sizeX - 1) {
                  print("Out of scope. Enter x (0 - " + (this.sizeX - 1) + "): ");
                  x = scanner.nextInt();
              }
            } catch (InputMismatchException e) {
                break;
            }
            try {
                println("Enter y of living cell (0 < y < " + (this.sizeY - 1) + ")");
                y = scanner.nextInt();
                while (y < 0 || y > this.sizeY - 1) {
                    print("Out of scope. Enter y (0 - " + (this.sizeY - 1) + "): ");
                    y = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                break;
            }
            newBoard[y][x] = ALIVE;
            DisplayBoard(newBoard);
        }
    }

    /**
     * Copies the provided board and returns a new instance of it.
     */
    public char[][] copyBoard(Board board) {
        char[][] newBoard = new char[board.sizeY][board.sizeX];
        for (int i = 0; i < board.sizeY; i++) {
            for (int j = 0; j < board.sizeX; j++) {
                newBoard[i][j] = board.newBoard[i][j];
            }
        }
        return newBoard;
    }

    /**
     * Displays the board in the Terminal.
     */
    public void DisplayBoard(char[][] board) {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                print(board[i][j] + " ");
            }
            print("\n");
        }
        print("\n");
    }
}

static void println(Object s) {
    System.out.println(s);
}

static void print(Object s) {
    System.out.print(s);
}


void main() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        try {
            println("GAME OF LIFE");
            print("Enter the X size of the board: ");
            int x = scanner.nextInt();
            while (x < 1) {
                print("Enter a positive X size: ");
                x = scanner.nextInt();
            }
            print("Enter the Y size of the board: ");
            int y = scanner.nextInt();
            while (y < 1) {
                print("Enter a positive Y size: ");
                y = scanner.nextInt();
            }

            Board board = new Board(x, y);
            board.DisplayBoard(board.newBoard);
            GameOfLife gameOfLife = new GameOfLife(board);
            print("\n".repeat(2));
            gameOfLife.startSimulation();
            println("\nPress 'q' to quit\n");
        }
        catch (InputMismatchException e) {
            break;
        }
    }
}

