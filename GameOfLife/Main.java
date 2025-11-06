import java.util.Scanner;

class GameOfLife {
    static void Rules() {

    }
}

class Board {
    int SizeX;
    int SizeY;

    final char zero = '□';
    final char one = '■';

    public void DisplayBoard () {
        for (int i = 1; i <= SizeX; i++) {
            for (int j = 1; j <= SizeY; j++){
                print(zero + " ");
            }
            print("\n");
        }
    }

    public Board (int SizeX, int SizeY) {
        this.SizeX = SizeX;
        this.SizeY = SizeY;

        DisplayBoard();

        int[][] Plansza = new int[SizeX][SizeY];


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
}

