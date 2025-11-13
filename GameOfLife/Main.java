import java.util.Scanner;

class GameOfLife {
    Board b;

    public static int countLivingCells(Board b){
        for(int i = 0; i < b.sizeX; i++){
            continue;
        }
        return 0;
    }
}

class Board {
    int sizeX;
    int sizeY;
    char[][] plansza = new char[sizeX][sizeY];

    final char zero = '□';
    final char one = '■';

    public void DisplayBoard () {
        for (int i = 1; i <= sizeX; i++) {
            for (int j = 1; j <= sizeY; j++){
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


        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++){
                plansza[i][j] = zero;
            }
        }

        println("Enter x,y of living cell (0<x<" + (this.sizeX-1) +", 0<y<" + (this.sizeY - 1));
        while(!flag.equals("n")){
            x = scanner.nextInt();
            y = scanner.nextInt();
            plansza[x][y] = one;

            println("Do you want to add another one? y/n");
            flag = scanner.nextLine();
        }

        DisplayBoard();



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
}

