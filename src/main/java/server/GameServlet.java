package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class GameServlet extends HttpServlet {

    GameBoard game = new GameBoard();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Player player = null;
        String name = request.getParameter("name");
        String board = game.printBoard();
        Player[] players = game.getPlayers();

        HttpSession session1 = request.getSession();
        HttpSession session2 = request.getSession();

        if (players[0] == null) {
            player = new Player(name, 1);
            players[0] = player;
            System.out.println(players);
            session1.setAttribute("player1", player);
        } else if (players[0] != null) {
            player = new Player(name, 2);
            players[1] = player;
            session2.setAttribute("player2", player);
        } else {
            System.out.println("Too many players");
        }

        session1.setAttribute("Game", game);
//        session2.setAttribute("Game", game);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<PRE>" + board + "</PRE>");
        out.println("\nHi " + name + " : " + player.getPlayerNumber() + "! It's your turn, please select column (1-9)");
        out.println("<form action='update' method='post'>" +
                    "Column : <input type='text' name='column'>" +
                    "<button type='submit'>Submit</button><br>" +
                            "</form>");
        //String column = request.getParameter("column");
        //session.setAttribute("column1", column);
        //game.dropToken(Integer.valueOf(column), player.getPlayerNumber());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String requestURL = request.getRequestURI();
        System.out.println(requestURL);
        //int column = Integer.parseInt(request.getParameter("column"));


    }

}
