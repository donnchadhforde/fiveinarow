//package client;
//
//import server.Player;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class PlayerClient {
//
//    final static int WIDTH = 9;
//    final static int HEIGHT = 7;
//
//    static char[][] board = new char[WIDTH][HEIGHT];
//
//    static Player player1, player2;
//    static Scanner scanner;
//
//    public static void printBoard() {
//
//        for (char[] horizontal: board) {
//            System.out.print("[");
//            if (true) {
//
//            } else {
//                System.out.print(" ");
//            }
//            System.out.print("]");
//            for (char vertical: horizontal ) {
//                System.out.print("[");
//                if (true) {
//
//                } else {
//                    System.out.print(" ");
//                }
//                System.out.print("]");
//            }
//            System.out.println(" ");
//        }
//
//    }
//
//    public static void sendPost(String msg) throws IOException {
//        URL url = new URL("http://localhost:8888");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//
//        connection.setDoOutput(true);
//
//        OutputStream out = connection.getOutputStream();
//        out.write(msg.getBytes());
//        out.flush();
//        out.close();
//
//    }
//
//    public static void main (String[] args) {
//
//        try {
//
//            URL url = new URL("http://localhost:8888");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) { // success
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        connection.getInputStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // print result
//                System.out.println(response.toString());
//            } else {
//                System.out.println("GET request not working");
//            }
//
//        } catch (IOException e) {
//            System.out.println("Caught");
//        }
//
//        boolean newGame = true;
//        boolean gameWon = false;
//
//        Map<String, Integer> parameters = new HashMap<>();
//
//        if (newGame) {
//
//            System.out.println("Welcome to five-in-a-row! Form a vertical, horizontal, or diagonal line of your disks to beat your opponent");
//
//            System.out.println("Hi Player! Please enter your name: ");
//            scanner = new Scanner(System.in);
//            String name = scanner.nextLine();
//            player1 = new Player(name, 1);
//            parameters.put(player1.getName(), player1.getPlayerNumber());
//            try {
//                sendPost(name);
//                System.out.println("Sent name");
//            } catch(IOException e) {
//
//            }
//
//        }
//
//        while (!gameWon) {
//
//            System.out.print("Hi " + player1.getName() + ", it's your turn!");
//
//
//            System.out.println(" Please enter column (1-9)");
//            int column = scanner.nextInt();
//            try {
//                sendPost(Integer.toString(column));
//                System.out.println("Sent name");
//            } catch(IOException e) {
//
//            }
//
//        }
//
//    }
//
//}
//
