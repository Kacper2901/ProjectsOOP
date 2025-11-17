import java.util.Scanner;
import java.lang.Thread;

class GameOfLife {
    public Board board;

    GameOfLife(Board b){
        this.board = b;
    }

    public int countLivingCells(int x, int y){
        int livingCellsCount = 0;
        if(x != 0 && y != 0 && this.board.pastBoard[x - 1][y - 1] == this.board.one) livingCellsCount ++;
        if(y != 0 && this.board.pastBoard[x][y - 1] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != 0 && this.board.pastBoard[x + 1][y - 1] == this.board.one) livingCellsCount ++;
        if(x != 0 && this.board.pastBoard[x - 1][y] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != 0 && this.board.pastBoard[x + 1][y] == this.board.one) livingCellsCount ++;
        if(x != 0 && y != this.board.sizeY - 1 && this.board.pastBoard[x - 1][y + 1] == this.board.one) livingCellsCount ++;
        if(y != this.board.sizeY - 1 && this.board.pastBoard[x][y + 1] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != this.board.sizeY - 1 && this.board.pastBoard[x + 1][y + 1] == this.board.one) livingCellsCount ++;

        return livingCellsCount;
    }

    public void simulationStep(){
        int livingCells;
        board.pastBoard = board.newBoard;
        for(int i = 0; i < board.sizeY; i++){
            for(int j = 0; j < board.sizeX; j++){
                livingCells = countLivingCells(j,i);
                if ((livingCells < 2 || livingCells > 4) && board.pastBoard[j][i] == board.one) board.newBoard[j][i] = board.zero;
                if (livingCells == 3 && board.pastBoard[j][i] == board.zero) board.newBoard[j][i] = board.one;
                if ((livingCells == 3 || livingCells == 4) && board.pastBoard[j][i] == board.one) board.newBoard[j][i] = board.one;
            }
        }
        board.DisplayBoard();
        println("");
        println("");
        println("");
    }

    public void startSimulation() throws InterruptedException{
        while(true){
            simulationStep();
            Thread.sleep(2000);
        }
    }
}

class Board {
    int sizeX;
    int sizeY;
    char[][] newBoard;
    char[][] pastBoard;

    final char zero = '□';
    final char one = '■';

    public Board (int SizeX, int SizeY) {
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        String flag = " ";
        int x;
        int y;
        this.newBoard = new char[sizeX][sizeY];
        this.pastBoard = new char[sizeX][sizeY];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                newBoard[j][i] = zero;
                pastBoard[j][i] = zero;
            }
        }
        while (!flag.equals("n")) {
            println("Enter x of living cell (0<x<" + (this.sizeX - 1) + ")");
            x = scanner.nextInt();
            println("Enter y of living cell 0<y<" + (this.sizeY - 1) + ")");
            y = scanner.nextInt();
            newBoard[x][y] = one;
            DisplayBoard();
            println("Do you want to add another one? y/n");
            scanner.nextLine();
            flag = scanner.nextLine();
        }
    }

    public void DisplayBoard () {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++){
                print(newBoard[j][i] + " ");
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
    board.DisplayBoard();
    GameOfLife gameOfLife = new GameOfLife(board);

    println("");
    println("");
    gameOfLife.startSimulation();
}

