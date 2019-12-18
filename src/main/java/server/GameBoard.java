package server;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class GameBoard {

    final static int HEIGHT = 6;
    final static int WIDTH = 9;

    private final char[][] board;

    private int currentCol, currentRow;

    private Player[] players = new Player[2];

    public GameBoard() {

        board = new char[HEIGHT][];

        for (int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i] = new char[WIDTH], ' ');
        }
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public String printBoard() {
        String gameBoard = "";
        System.out.println(" 1  2  3  4  5  6  7  8  9 ");
        gameBoard += " 1  2  3  4  5  6  7  8  9 \n";
        for (char[] row: board) {

            for (char column: row) {
                gameBoard += "[";
                System.out.print("[");
                gameBoard += column;
                System.out.print(column);
                gameBoard += "]";
                System.out.print("]");
            }

            gameBoard += "\n";
            System.out.println(" ");
        }
        return gameBoard;
    }

    public char getToken(int playerNumber) {
        char token = ' ';
        if (playerNumber == 1) {
            token = 'X';
        } else if (playerNumber == 2) {
            token = 'O';
        }
        return token;
    }

    public void dropToken(int columnNumber, int playerNumber) {

        char token = getToken(playerNumber);

        for (int i = HEIGHT-1; i>= 0; i--) {
            if (board[i][columnNumber-1] == ' ') {
                board[currentRow = i][currentCol = columnNumber-1] = token;
                return;
            }
        }

        System.out.println("Column full, please choose a different one.");

    }

    public boolean checkWon(String line, int playerNumber) {

        char token = getToken(playerNumber);

        int replace = line.length() - line.replace(String.valueOf(token), "").length();
        int occurences = 0;

        if (replace >= 5) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == token) {
                    occurences++;

                    if (occurences == 5) {
                        return true;
                    }

                } else {
                    occurences = 0;
                }
            }
        }

        return false;
    }

    public String getCurrentRow() {

        String row = new String(board[currentRow]);
        return row;

    }

    public String getCurrentColumn() {

        String column = "";

        for (char[] row : board) {
            column += row[currentCol];
        }

        return column;
    }

    public String getRightDiagonal() {

        String diagRight = "";

        for (int i = HEIGHT-1; i >= 0; i--) {
            int w = currentCol - (i - currentRow);

            if (w >= 0 && w < WIDTH) {
                diagRight += board[i][w];
            }
        }

        return diagRight;
    }

    public String getLeftDiagonal() {

        String diagLeft = "";

        for (int i = HEIGHT-1; i >= 0; i--) {
            int w = currentCol + (i - currentRow);

            if (w >= 0 && w < WIDTH) {
                diagLeft += board[i][w];
            }
        }

        return diagLeft;
    }

    public static void sendGet() throws IOException {
        URL url = new URL("http://localhost:8888");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        //System.out.println("Response Code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not working");
        }
    }

    public static void sendPost(String msg, HttpURLConnection con) throws IOException {
//        URL url = new URL("http://localhost:8888");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();

        //con.setDoOutput(true);

        OutputStream out = con.getOutputStream();
        out.write(msg.getBytes());
        out.flush();
        out.close();
    }


//    public static void main (String[] args) {
//
//        boolean waiting = true;
//        HttpURLConnection connection = null;
//
//
//        try {
//
//            URL url = new URL("http://localhost:8888/game");
//            connection = (HttpURLConnection) url.openConnection();
//            //connection.setRequestMethod("POST");
//
//        } catch (IOException e) {
//            System.out.println("Caught");
//        }
////
////        while(waiting) {
////
////            try {
////                sendGet();
////            } catch (IOException e) {
////
////            }
////        }
//
////        boolean notWon = true;
//        Player player1, player2;
////
//        GameBoard game = new GameBoard();
//
//        System.out.println("Welcome to five-in-a-row! Form a vertical, horizontal, or diagonal line of your disks to beat your opponent");
//
//        System.out.println("Hi Player 1! Please enter your name: ");
//        Scanner scanner = new Scanner(System.in);
//        String name1 = scanner.nextLine();
//        player1 = new Player(name1, 1);
//
//        try{
//            sendPost(name1, connection);
//        } catch (IOException e){
//
//        }
////
////        System.out.println("Hi Player 2! Please enter your name: ");
////        scanner = new Scanner(System.in);
////        String name2 = scanner.nextLine();
////        player2 = new Player(name2, 2);
////
////
////        String row, col, leftDiag, rightDiag;
////
////        Player players[] = {player1, player2};
//
////        while (notWon) {
////
////            for (Player player: players) {
////
////                String name = player.getName();
////                int num = player.getPlayerNumber();
////
////                System.out.print("Hi " + name + ", it's your turn!");
////
////                System.out.println(" Please enter column (1-9)");
////                int column = scanner.nextInt();
////                game.dropToken(column, num);
////                row = game.getCurrentRow();
////                col = game.getCurrentColumn();
////                leftDiag = game.getLeftDiagonal();
////                rightDiag = game.getRightDiagonal();
////
////                if (game.checkWon(row, num) ||
////                        game.checkWon(col, num) ||
////                        game.checkWon(leftDiag, num) ||
////                        game.checkWon(rightDiag, num)) {
////                    notWon = false;
////                    System.out.println("Game over");
////                    return;
////                }
////
////                game.printBoard();
////            }
////
////
////        }
//
//    }



}
