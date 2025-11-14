import java.util.Scanner;

class GameOfLife {
    Board board;


    public int countLivingCells(int x, int y){
        int livingCellsCount = 0;
        if(x != 0 && y != 0 && this.board.plansza[x - 1][y - 1] == this.board.one) livingCellsCount ++;
        if(y != 0 && this.board.plansza[x][y - 1] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != 0 && this.board.plansza[x + 1][y - 1] == this.board.one) livingCellsCount ++;
        if(x != 0 && this.board.plansza[x - 1][y] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != 0 && this.board.plansza[x + 1][y] == this.board.one) livingCellsCount ++;
        if(x != 0 && y != this.board.sizeY && this.board.plansza[x - 1][y + 1] == this.board.one) livingCellsCount ++;
        if(y != this.board.sizeY - 1 && this.board.plansza[x][y + 1] == this.board.one) livingCellsCount ++;
        if(x != this.board.sizeX - 1 && y != this.board.sizeY - 1 && this.board.plansza[x + 1][y + 1] == this.board.one) livingCellsCount ++;

        return livingCellsCount;
    }

    GameOfLife(Board b){
        this.board = b;
    }
}

class Board {
    int sizeX;
    int sizeY;
    char[][] plansza;

    final char zero = '□';
    final char one = '■';

    public void DisplayBoard () {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++){
                print(plansza[i][j] + " ");
            }
            print("\n");
        }
    }

    public Board (int SizeX, int SizeY) {
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        String flag = " ";
        int x;
        int y;
        this.plansza = new char[sizeX][sizeY];


        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++){
                plansza[i][j] = zero;
            }
        }

        while(!flag.equals("n")){
            println("Enter x of living cell (0<x<" + (this.sizeX-1) +")");
            x = scanner.nextInt();
            println("Enter y of living cell 0<y<" + (this.sizeY - 1) + ")");
            y = scanner.nextInt();
            plansza[x][y] = one;
            println(plansza[x][y]);
            println("Do you want to add another one? y/n");
            scanner.nextLine();
            flag = scanner.nextLine();

        }



    }



}

static void println(Object s) {
    System.out.println(s);
}

static void print(Object s) {
    System.out.print(s);
}


void main(String[] args) {
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
    println(gameOfLife.countLivingCells(0,2));
}

