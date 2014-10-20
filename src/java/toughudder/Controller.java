package toughudder;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Controller for the Tough Udder MVC architecture.
 *
 * @author T. Perrin
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();

        // default variables
        Account account;
        String url = "/index.jsp";

        // get current action
        String action = request.getParameter("action");

        switch (action) {
            case "home":
                account = (Account) session.getAttribute("account");
                if (account != null && account.isLogin()) {
                    url = "/index_loggedin.jsp";
                } else {
                    url = "/index.jsp";
                }
                break;

            case "login":
                // create Account object
                account = new Account();
                String userID = request.getParameter("userID");
                String password = request.getParameter("password");
                account.checkLogin(userID, password);
                session.setAttribute("account", account);

                if (account.isLogin()) {
                    url = "/index_loggedin.jsp";
                } else {
                    url = "/index.jsp";
                }
                break;

            case "logout":
                session.invalidate();
                url = "/index.jsp";
                break;
        }

        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request - servlet request
     * @param response - servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request - servlet request
     * @param response - servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
