import java.util.Scanner;
import java.lang.Thread;

class GameOfLife {
    public Board board;

    GameOfLife(Board b){
        this.board = b;
    }

    public int countLivingNeighbours(int x, int y){
        int livingCellsCount = 0;
        for(int j = -1; j <= 1; j++){
            for(int i = -1; i <= 1; i++){
                if((x + i >= 0 && x + i <= board.sizeX - 1) && (y + j >= 0 && y + j <= board.sizeY - 1 )&& board.pastBoard[y + j][x + i] == board.alive){
                    livingCellsCount ++;
                }
            }
        }
        if(board.pastBoard[y][x] == board.alive) livingCellsCount--;

        return livingCellsCount;
    }



    public void simulationStep(){
        int livingCells;
        for(int i = 0; i < board.sizeY; i++){
            for(int j = 0; j < board.sizeX; j++){
                livingCells = countLivingNeighbours(j,i);
                if ((livingCells < 2 || livingCells > 3) && board.pastBoard[i][j] == board.alive) board.newBoard[i][j] = board.dead;
                if (livingCells == 3 && board.pastBoard[i][j] == board.dead) board.newBoard[i][j] = board.alive;
                if ((livingCells == 2 || livingCells == 3) && board.pastBoard[i][j] == board.alive) board.newBoard[i][j] = board.alive;
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        board.DisplayBoard(board.newBoard);
        board.pastBoard = board.copyBoard(board);
        println("");
        println("");
        println("");

    }

    public void startSimulation() throws InterruptedException{
        while(true){
            simulationStep();
            Thread.sleep(500);
        }
    }
}

class Board {
    public int sizeX;
    public int sizeY;
    char[][] newBoard;
    char[][] pastBoard;

    final char dead = '□';
    final char alive = '■';

    public Board (int SizeX, int SizeY) {
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        String flag = " ";
        int x;
        int y;
        this.newBoard = new char[sizeY][sizeX];
        this.pastBoard = new char[sizeY][sizeX];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                newBoard[i][j] = dead;
                pastBoard[i][j] = dead;
            }
        }
        while (true) {
            println("Enter coordinates or press q if you finished");
            println("Enter x of living cell (0<x<" + (this.sizeX - 1) + ")");
            try {
                x = scanner.nextInt();
            } catch (InputMismatchException e) {
                break;
            }

            println("Enter y of living cell 0<y<" + (this.sizeY - 1) + ")");
            y = scanner.nextInt();
            pastBoard[y][x] = alive;
            DisplayBoard(pastBoard);

        }
    }

    public char[][] copyBoard(Board board){
        char[][] newBoard= new char[board.sizeY][board.sizeX];
        for(int i = 0; i < board.sizeY; i++){
            for(int j = 0; j < board.sizeX; j++){
                newBoard[i][j] = board.newBoard[i][j];
            }
        }
        return newBoard;
    }

    public void DisplayBoard (char[][] board) {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++){
                print(board[i][j] + " ");
            }
            print("\n");
        }
    }
}

static void println(Object s) {
    System.out.println(s);
}

static void print(Object s) {
    System.out.print(s);
}


void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);

    println("Podaj rozmiar x tabeli");
    int x = scanner.nextInt();
    println("Podaj rozmiar y tabeli");
    int y = scanner.nextInt();
    Board board = new Board(x, y);
    board.DisplayBoard(board.newBoard   );
    GameOfLife gameOfLife = new GameOfLife(board);

    println("");
    println("");
    gameOfLife.startSimulation();
}

