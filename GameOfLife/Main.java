import java.util.Scanner;
import java.lang.Thread;

final int SAME_BOARD_LIMIT = 5;
final boolean TEST = false;

final char[][] GLIDER_20 = {
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '■', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '■', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '■', '■', '■', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'}
};

final char[][] BLINKER_20 = {
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '■', '■', '■', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'}
};

final char[][] PATTERN_101 = {
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '■', '■', '□', '□', '□', '□', '□', '■', '□', '□', '□', '□', '■', '■', '□', '□', '□', '□'},
        {'□', '□', '■', '□', '■', '□', '□', '□', '■', '□', '□', '□', '□', '■', '□', '■', '□', '□', '□', '□'},
        {'□', '□', '■', '□', '□', '■', '□', '□', '■', '□', '□', '□', '□', '■', '□', '□', '■', '□', '□', '□'},
        {'□', '□', '■', '□', '□', '■', '□', '□', '□', '■', '□', '□', '□', '■', '□', '□', '■', '□', '□', '□'},
        {'□', '□', '■', '□', '■', '□', '□', '□', '□', '■', '□', '□', '□', '■', '□', '■', '□', '□', '□', '□'},
        {'□', '□', '■', '■', '□', '□', '□', '□', '□', '□', '■', '□', '□', '■', '■', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '■', '■', '□', '□', '□', '□', '□', '□', '■', '□', '□', '■', '■', '□', '□', '□', '□', '□'},
        {'□', '□', '■', '□', '■', '□', '□', '□', '□', '■', '□', '□', '□', '■', '□', '■', '□', '□', '□', '□'},
        {'□', '□', '■', '□', '□', '■', '□', '□', '□', '■', '□', '□', '□', '■', '□', '□', '■', '□', '□', '□'},
        {'□', '□', '■', '□', '□', '■', '□', '□', '■', '□', '□', '□', '□', '■', '□', '□', '■', '□', '□', '□'},
        {'□', '□', '■', '□', '■', '□', '□', '□', '■', '□', '□', '□', '□', '■', '□', '■', '□', '□', '□', '□'},
        {'□', '□', '■', '■', '□', '□', '□', '□', '□', '■', '□', '□', '□', '■', '■', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'},
        {'□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□', '□'}
};

final String[] NEGATIVE_SPACESHIP = {
        "############################################################",
        "############################################################",
        "############################################################",
        "############################################################",
        "############################ # ###  #  # #  # # # # # # # # ",
        "###########################  #  #   #   ##   #   #   #   #  ",
        "############################ # # #   # #  # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "###########################  #  #   #   ##   #   #   #   #  ",
        "############################ # ###  #  # #  # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################ # # # # # # # # # # # # # # # # ",
        "############################################################",
        "############################################################",
        "############################################################",
        "############################################################"
};

final String[] SHIP_IN_A_BOTTLE = {
        "    ##    ",
        "  #    #  ",
        " #      # ",
        " #      # ",
        "##  ##  ##",
        "##  ##  ##",
        " #      # ",
        " #      # ",
        "  #    #  ",
        "    ##    "
};


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

    public void startSimulation(int maxSteps) throws InterruptedException {
        int sameBoardCount = 0;
        int stepCount = 0;
        while (true) {
            simulationStep();
            stepCount++;
            if (sameBoard()) sameBoardCount += 1;
            if (sameBoardCount == SAME_BOARD_LIMIT || stepCount > maxSteps) {
                print("The same board state has occurred " + SAME_BOARD_LIMIT + " times or the maximum number of steps " +
                        "was exceeded. Terminating the simulation.");
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

    public Board(int SizeX, int SizeY, boolean isSelfInitialized) {
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        this.newBoard = new char[sizeY][sizeX];
        this.pastBoard = new char[sizeY][sizeX];
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        /* Board initialization */
        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                newBoard[i][j] = DEAD;
                pastBoard[i][j] = DEAD;
            }
        }

        while (isSelfInitialized) {
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
                print("Enter y of living cell (0 < y < " + (this.sizeY - 1) + ")");
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

/**
 * Main function for initializing the Game of Life.
 * The user is asked to enter the X and Y sizes of the board.
 */
void inputSimulation() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    int x = 0;
    int y = 0;
    int maxSteps;
    boolean SelfInitalized = true;
    while (true) {
        try {
            println("GAME OF LIFE");
            println("");
            print("Do you want to set up your own board (1) or use some well-known shapes (2): ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                print("Enter the X size of the board: ");
                while (x < 1) {
                    print("Enter a positive X size: ");
                    x = scanner.nextInt();
                }
                print("Enter the Y size of the board: ");
                while (y < 1) {
                    print("Enter a positive Y size: ");
                    y = scanner.nextInt();
                }
                Board board = new Board(x, y, SelfInitalized);
                maxSteps = 2 * (board.sizeX + board.sizeY);
                GameOfLife gameOfLife = new GameOfLife(board);
                gameOfLife.startSimulation(maxSteps);
            } else {
                println("""
                        Which shape do you choose:
                        1 - Glider
                        2 - Blinker
                        3 - Pattern 101
                        4 - Negative Spaceship
                        5 - Ship in a Bottle""");
                choice = scanner.nextInt();
                SelfInitalized = false;
                Board board = new Board(20, 20, SelfInitalized);
                switch (choice) {
                    case 1:
                        board.newBoard = board.pastBoard = GLIDER_20;
                        break;
                    case 2:
                        board.newBoard = board.pastBoard = BLINKER_20;
                        break;
                    case 3:
                        board.newBoard = board.pastBoard = PATTERN_101;
                        break;
                    case 4:
                        board = new Board(60, 22, SelfInitalized);
                        board.newBoard = board.pastBoard = StringPatternToChar(NEGATIVE_SPACESHIP);
                        break;
                    case 5:
                        board = new Board(10, 10, SelfInitalized);
                        board.newBoard = board.pastBoard = StringPatternToChar(SHIP_IN_A_BOTTLE);
                        break;
                }
                maxSteps = 2 * (board.sizeX + board.sizeY);
                GameOfLife gameOfLife = new GameOfLife(board);
                gameOfLife.startSimulation(maxSteps);
            }
            println("\nPress 'q' to quit\n");
        } catch (InputMismatchException e) {
            break;
        }
    }
}

void ShowShapes(char[][] shape, int size_x, int size_y) throws InterruptedException {
    Board board = new Board(size_x, size_y, false);
    board.pastBoard = shape;
    board.newBoard = shape;
    GameOfLife game = new GameOfLife(board);
    game.startSimulation(10);
}

/**
 * Creates a thread that listens to the user if they have pressed a key.
 */
void WatchForKey(String key) {
    final char match = !key.isEmpty() ? key.charAt(0) : '\0';
    Thread thread = new Thread(() -> {
        try {
            while (true) {
                if (System.in.available() > 0) {
                    int b = System.in.read();
                    if (b == -1) break;
                    char c = (char) b;
                    if (Character.toString(c).trim().equalsIgnoreCase(Character.toString(match))) System.exit(0);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                    break;
                }
            }
        } catch (java.io.IOException ignored) {
        }
    });
    thread.setDaemon(true);
    thread.start();
}

char[][] StringPatternToChar(String[] arg) {
    double MinStrLength = 1E7;
    for (String str : arg) {
        int len = str.length();
        if (len < MinStrLength) MinStrLength = len;
    }
    char[][] CharBoard = new char[arg.length][(int) MinStrLength];
    for (int i = 0; i < arg.length; i++) {
        for (int j = 0; j < MinStrLength; j++) {
            if (arg[i].charAt(j) == '#') CharBoard[i][j] = '■';
            else CharBoard[i][j] = '□';
        }
    }
    return CharBoard;
}

void main() throws InterruptedException {
    WatchForKey("q");
    if (!TEST) inputSimulation();
    else {
        println("GAME OF LIFE TEST MODE");
        ShowShapes(GLIDER_20, 20, 20);
        Thread.sleep(1000);
        ShowShapes(BLINKER_20, 20, 20);
        Thread.sleep(1000);
        ShowShapes(PATTERN_101, 20, 20);
        Thread.sleep(1000);
        ShowShapes(StringPatternToChar(NEGATIVE_SPACESHIP), 60, 22);
        Thread.sleep(1000);
        ShowShapes(StringPatternToChar(SHIP_IN_A_BOTTLE), 10, 10);
    }
}
