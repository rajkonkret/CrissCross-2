import java.util.MissingFormatWidthException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] table = new String[3][3];
        String gamer = "X";
        boolean isWinGame = false;

       /* Draw(table);
        table = Fulltable(table, gamer);
        Draw(table);*/
        table = Fulltable(table, " ");
        Draw(table);

        while (CanI(table)) {
            gamer = "X";
            System.out.println("\nGracz " + gamer + " twój ruch");
            System.out.println("x=");
            int xaxis = scanner.nextInt();
            System.out.println("y=");
            int yaxis = scanner.nextInt();

        /*for (int i=0; i<3;i++){

            table = SetUser(table, i, i, "O");
            table = SetUser(table, 2-i, i, "X");

        }*/
            table = SetUser(table, xaxis, yaxis, gamer);
            Draw(table);
            isWinGame = (IsWin(table, gamer));
            if (isWinGame) {
                break;
            }
            gamer = "O";
            table = MoveFromComputer(table, gamer);
            Draw(table);
            isWinGame = (IsWin(table, gamer));
            if (isWinGame) {
                break;
            }
        }
        if (isWinGame) {
            System.out.println("\nWygrał: " + gamer);
        } else {
            System.out.println("\nREMIS");
        }
        System.out.println("\nKONIEC");
    }


    private static void Draw(String[][] table) {
        System.out.println(table.length);
        System.out.println("Plansza gry:");

        System.out.print("    0   1   2");
        for (int i = 0; i < table.length; i++) {
            System.out.println((i == 0) ? "" : "\n  |---|---|---");
            System.out.print(i);
            for (int j = 0; j < table.length; j++) {

                System.out.print(((j == 0) ? " | " : "") + table[i][j] + " | ");
            }
        }
    }

    public static String[][] Fulltable(String[][] table, String myChar) {

        for (int i = 0; i < table.length; i++) {

            for (int j = 0; j < table.length; j++) {

                table[i][j] = myChar;
            }
        }
        return table;
    }

    public static String[][] SetUser(String[][] table, int x, int y, String mychar) {
        boolean isChange = false;

        while (!isChange) {

            if (table[y][x].equalsIgnoreCase(" ")) {

                table[y][x] = mychar;
                isChange = true;
            } else {
                System.out.println("Błędny ruch!!!");
                break;
            }
        }
        return table;
    }

    public static String[][] MoveFromComputer(String[][] table, String mychar) {
        boolean isChange = false;
        String[] table2 = new String[3];
        table2[0] = "1";
        table2[1] = "1";
        table2[2] = "1";
        if (CanI(table)) {
            while (!isChange) {
                int randomValueX = ThreadLocalRandom.current().nextInt(0, 3);
                int randomValueY = ThreadLocalRandom.current().nextInt(0, 3);
                if (table[randomValueX][randomValueY].equalsIgnoreCase(" ")) {
                    table[randomValueX][randomValueY] = mychar;
                    isChange = true;
                }
            }
            //System.out.println(Arrays.toString(table2));
        }

        return table;
    }

    public static boolean CanI(String[][] table) {
        boolean canI = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].equalsIgnoreCase(" ")) {
                    canI = true;
                }
            }
        }
        return canI;
    }

    public static boolean IsWin(String[][] table, String mychar) {
        boolean isWin = false;

        for (int i = 0; i < 3; i++) {

            if (table[i][0].equalsIgnoreCase(mychar) && table[i][1].equalsIgnoreCase(mychar) && table[i][2].equalsIgnoreCase(mychar)) {
                isWin = true;
            }
            if (table[0][i].equalsIgnoreCase(mychar) && table[1][i].equalsIgnoreCase(mychar) && table[2][i].equalsIgnoreCase(mychar)) {
                isWin = true;
            }
        }
        if (table[0][0].equalsIgnoreCase(mychar) && table[1][1].equalsIgnoreCase(mychar) && table[2][2].equalsIgnoreCase(mychar)) {
            isWin = true;
        }
        if (table[0][2].equalsIgnoreCase(mychar) && table[1][1].equalsIgnoreCase(mychar) && table[2][0].equalsIgnoreCase(mychar)) {
            isWin = true;
        }

        return isWin;

    }
}

