import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] table = new String[3][3];
        int[][] tablewage = new int[3][3];
        boolean isWinGame = false;

        String gamer = "X";

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

            table = SetUser(table, xaxis, yaxis, gamer);
            Draw(table);
            isWinGame = (IsWin(table, gamer));
            calculateWage(table, tablewage);
            Draw(tablewage);
            Draw(table);
            if (isWinGame) {
                break;
            }
           /* gamer = "O";
            table = MoveFromComputer(table, gamer);
            Draw(table);
            isWinGame = (IsWin(table, gamer));
            if (isWinGame) {
                break;
            }*/
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

    private static void Draw(int[][] tablewage) {
        System.out.println("Plansza gry (tablewage):");

        System.out.print("    0   1   2");
        for (int i = 0; i < tablewage.length; i++) {
            System.out.println((i == 0) ? "" : "\n  |---|---|---");
            System.out.print(i);
            for (int j = 0; j < tablewage.length; j++) {

                System.out.print(((j == 0) ? " | " : "") + tablewage[i][j] + " | ");
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

    public static void Fulltable(int[][] tablewage, int wage) {

        for (int i = 0; i < tablewage.length; i++) {

            for (int j = 0; j < tablewage.length; j++) {

                tablewage[i][j] = wage;
            }
        }

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

    public static void calculateWage(String[][] table, int[][] tablewage) {
        int wage;
        int countX;
        int countO;
        List<Integer> listofwage = new ArrayList<>();
        Map<String, Integer> wageMap = new HashMap<>();
        wageMap.clear();
        Fulltable(tablewage, 0);
        for (int j = 0; j < 3; j++) {
            countO = 0;
            countX = 0;
            for (int i = 0; i < 3; i++) {

                if (table[j][i].equalsIgnoreCase("X")) {
                    countX = +1;
                }
                if (table[j][i].equalsIgnoreCase("O")) {
                    countO = +1;
                }
            }
            System.out.println("\n" + countX + " " + countO + " " + j);
            wage = countX - countO;
            for (int i = 0; i < 3; i++) {
                if (table[j][i].equalsIgnoreCase(" ")) {
                    tablewage[j][i] = tablewage[j][i] + wage;
                    System.out.println("\n" + j + " " + wage);
                }
            }
        }
        for (int j = 0; j < 3; j++) {
            countO = 0;
            countX = 0;
            for (int i = 0; i < 3; i++) {

                if (table[i][j].equalsIgnoreCase("X")) {
                    countX = +1;
                }
                if (table[i][j].equalsIgnoreCase("O")) {
                    countO = +1;
                }
            }

            wage = countO - countX;
            for (int i = 0; i < 3; i++) {
                if (table[i][j].equalsIgnoreCase(" ")) {
                    tablewage[i][j] = tablewage[i][j] + wage;
                } else {
                    tablewage[i][j] = 99;

                }
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {

                if (tablewage[j][i] != 99) wageMap.put(String.valueOf(tablewage[j][i]), i + j * 3);
            }
        }
        System.out.println(wageMap.size());
        //System.out.println(wageMap.get(0).toString());
        wageMap.forEach((key, value) -> listofwage.add(Integer.valueOf(key)));
        int maxWage = Collections.max(listofwage);
        int computerMove = wageMap.get(String.valueOf(maxWage));
        System.out.println("max key= "+computerMove);
        int compMY = computerMove / 3;
        int compMX = computerMove % 3;
        System.out.println("x="+compMX+"y="+compMY);
        SetUser(table,compMX,compMY,"O");
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

    public static String[][] MoveFromComputerAI(String[][] table) {

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

