package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateServlet extends HttpServlet {

    GameBoard game = null;
    Player[] players;
    String row, col, leftDiag, rightDiag;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        game = (GameBoard)session.getAttribute("Game");
        players = game.getPlayers();
        String board = game.printBoard();
        Player player1 = (Player)session.getAttribute("player1");
        //Player player2 = (Player)session.getAttribute("player2");

        PrintWriter out = response.getWriter();
        out.println("<PRE>" + board + "</PRE>");
        out.println("\nHi " + player1.getName() + " : " + player1.getPlayerNumber() + "! It's your turn, please select column (1-9)");
        out.println("<form action='update' method='post'>" +
                "Column : <input type='text' name='column'>" +
                "<button type='submit'>Submit</button><br>" +
                "</form>");
        String column = request.getParameter("column");
        game.dropToken(Integer.valueOf(column), player1.getPlayerNumber());
        row = game.getCurrentRow();
        col = game.getCurrentColumn();
        leftDiag = game.getLeftDiagonal();
        rightDiag = game.getRightDiagonal();
        int num = player1.getPlayerNumber();

        if (game.checkWon(row, num) ||
                game.checkWon(col, num) ||
                game.checkWon(leftDiag, num) ||
                game.checkWon(rightDiag, num)) {

            System.out.println("Game over");
            return;
        }



    }
}
