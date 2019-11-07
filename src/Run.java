import com.sun.deploy.util.ArrayUtil;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import sun.plugin.javascript.navig.Array;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] table = new String[3][3];
        String gamer = "X";

       /* Draw(table);
        table = Fulltable(table, gamer);
        Draw(table);*/
        table = Fulltable(table, " ");
        Draw(table);

        while (canI(table)) {
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
            gamer = "O";
            table = MoveFromComputer(table, gamer);
            Draw(table);
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

        if (table[y][x].equalsIgnoreCase(" ")) {

            table[y][x] = mychar;
        }

        return table;
    }

    public static String[][] MoveFromComputer(String[][] table, String mychar) {
        boolean isChange = false;
        String[] table2 = new String[3];
        table2[0] = "1";
        table2[1] = "1";
        table2[2] = "1";
        if (canI(table)) {
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

    public static boolean canI(String[][] table) {
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
}

