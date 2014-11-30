package toughudder;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller for the Tough Udder MVC architecture.
 *
 * @author T. Perrin
 */
public class Controller extends HttpServlet {

   private static final long serialVersionUID = 1L;
   public static final String CART = "cart";
   public static final String EVENT_CHOICES = "add-event";
   public static final String CC_DATA = "ccData";
   public static final String EMAIL = "email";
   public static final String ERROR = "error";

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
            account.checkLogin(userID, password,
                  (ArrayList<Account>) servletContext.getAttribute("accountList"));
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

         case "events":
            url = "/events.jsp";
            break;

         case "cart":
            url = "/cart.jsp";
            break;

         case "Remove from Cart":
            url = "/cart.jsp";
            updateCart(request, response, false);
            break;

         case "Checkout":
            url = "/checkout.jsp";
            break;

         case "Complete Registration":
            CreditCardWorkerBean ccwb = new CreditCardWorkerBean(request);
            // Bad CC info? Go back the the checkout page.
            if (ccwb.isError()) {
               request.setAttribute(ERROR, ccwb.getError());
               request.setAttribute(CC_DATA, ccwb.getData());
               url = "/checkout.jsp";
            } // Otherwise, complete the registration.
            else {
               // If we we're doing so, here's where we'd charge the CC.
               EmailWorkerBean ewb
                     = new EmailWorkerBean(request, ccwb.getData());

               if (ewb.isError()) {
                  request.setAttribute(ERROR, ewb.getError());
               }
               url = "/confirmation.jsp";
            }
            break;

         case "Add to Cart":
            url = "/cart.jsp";
            updateCart(request, response, true);
            break;
      }

      RequestDispatcher dispatcher = servletContext.getRequestDispatcher(url);
      dispatcher.forward(request, response);
   }

   /**
    * Updates the contents of the cart based on user selections, instantiating
    * the cart if one does not exist.
    *
    * @param request - The request object
    * @param response - The response object
    * @param add - true to add, false to remove
    */
   private void updateCart(
         HttpServletRequest request, HttpServletResponse response, boolean add) {

      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute(CART);
      if (cart == null) {
         cart = new Cart();
         session.setAttribute(CART, cart);
      }
      String[] selectedEvents = request.getParameterValues(EVENT_CHOICES); // Only the checked boxes are returned!
      if (selectedEvents != null) {
         for (String name : selectedEvents) {
            Event event = EventStore.instance().getEvent(name);
            if (event != null) {
               if (add) {
                  cart.addEvent(event);
                  System.out.println("Event added to cart: " + name);
               } else {
                  cart.removeEvent(event);
                  System.out.println("Event removed from cart: " + name);
               }
            } else {
               System.out.println("Received a bad event name in updateCart():"
                     + name);
            }
         }
      }
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
