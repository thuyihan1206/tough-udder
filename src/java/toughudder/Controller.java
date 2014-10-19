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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
         ServletContext servletContext = getServletContext();
         
         // Do stuff
         
         RequestDispatcher dispatcher = servletContext.getRequestDispatcher("index.jsp");
         dispatcher.forward(request, response);
    	 
     }
     
     /** 
      * Handles the HTTP <code>GET</code> method.
      * @param request  - servlet request
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
      * @param request  - servlet request
      * @param response - servlet response
      * @throws javax.servlet.ServletException
      * @throws java.io.IOException
      */
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          processRequest(request, response);
      }
}