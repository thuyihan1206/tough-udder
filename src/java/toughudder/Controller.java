package toughudder;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
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
        String url = "/index.jsp";

        // get current action
        String action = request.getParameter("action");

        switch (action) {
            case "home":
                url = checkLogin(request, "/index_loggedin.jsp", "/index.jsp");
                break;

            case "login":
                // create Account object
                Account account = new Account();
                String userID = request.getParameter("userID");
                String password = request.getParameter("password");
                account.checkAccount(userID, password,
                        (ArrayList<Account>) servletContext.getAttribute("accountList"));
                session.setAttribute("account", account);

                url = checkLogin(request, "/index_loggedin.jsp", "/login.jsp");
                break;

            case "account":
                url = checkLogin(request, "/account.jsp", "/login.jsp");
                break;

            case "logout":
                session.removeAttribute("account");
                url = "/index.jsp";
                break;

            case "events":
                url = "/events.jsp";
                session.setAttribute("events", EventStore.instance().getEvents());
                break;

            case "cart":
                url = "/cart.jsp";
                break;

            case "Checkout":
                url = checkLogin(request, "/checkout.jsp", "/login.jsp");
                if (url.equals("/checkout.jsp")) {
                    // use HTTPS
//                    url = "https://localhost:8443/ToughUdder/checkout.jsp"; // local; need to start Tomcat standalone outside NetBeans
                    url = "https://dev8.jhuep.com:8443/ToughUdder/checkout.jsp"; // dev8 server
                    response.sendRedirect(url);
                    return;
                }
                break;

            case "complete":
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

                    session.setAttribute(CC_DATA, ccwb.getData());
                    if (ewb.isError()) {
                        request.setAttribute(ERROR, ewb.getError());
                    }
                    url = "/confirmation.jsp";
                }
                break;

            case "Remove from Cart":
                url = "/cart.jsp";
                updateCart(request, response, false);
                break;

            case "Add to Cart":
                url = "/cart.jsp";
                updateCart(request, response, true);
                break;

            case "profile":
                url = "/profile.jsp";
                break;

            case "print":
                printPDF(request, response);
                return;

            case "startover":
                session.removeAttribute(CC_DATA);
                session.removeAttribute(CART);
                url = "/events.jsp";
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
    private void updateCart(HttpServletRequest request, HttpServletResponse response, boolean add) {
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
                    System.out.println("Received a bad event name in updateCart():" + name);
                }
            }
        }
    }

    /**
     * Check if user has logged in. If yes: go to the desired page. If no:
     * redirect to the redirect page.
     *
     * @param request - The request object
     * @param desiredPage - The desired page
     * @param redirectPage - The redirect page
     */
    private String checkLogin(HttpServletRequest request,
            String desiredPage, String redirectPage) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String url;
        if (account != null && account.isLogin()) {
            url = desiredPage;
        } else {
            url = redirectPage;
        }
        return url;
    }

    /**
     * Print PDF
     *
     * @param request - The request object
     * @param response - The response object
     */
    protected void printPDF(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();

        try {
            // construct document body
            Document document = new Document();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, buffer);

            document.open();

            CreditCardInfoBean ccib = (CreditCardInfoBean) session.getAttribute(Controller.CC_DATA);
            Cart cart = (Cart) session.getAttribute("cart");
            Account account = (Account) session.getAttribute("account");
            List<Event> contents = cart.getEvents();

            String imagePath = getServletContext().getRealPath("/images/logo.jpg");
            Image image = Image.getInstance(imagePath);
            image.scaleAbsolute(200, 130);
            document.add(image);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph(account.getFirstName() + " " + account.getLastName() + ","));
            document.add(new Paragraph("Your Tough Udder registration is complete! A summary is shown below."));

            PdfPTable table = new PdfPTable(5);
            table.setWidths(new int[]{1, 1, 1, 1, 1});
            table.setWidthPercentage(100);
            table.getDefaultCell().setPaddingBottom(5);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell = new PdfPCell(table.getDefaultCell());
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPhrase(new Phrase(""));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Event"));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Date"));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Location"));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Cost"));
            table.addCell(cell);

            for (Event evt : contents) {
                // this version of iText doesn't support .png
                imagePath = getServletContext().getRealPath("/images/" + evt.getImgName().replaceAll(".png", ".jpg"));
                image = Image.getInstance(imagePath);
                table.addCell(image);
                table.addCell(evt.getName());
                table.addCell(evt.getDate());
                table.addCell(evt.getLocation());
                table.addCell(cart.getCostFormat().format(evt.getCost()));
            }

            cell.setBorder(Rectangle.TOP);
            cell.setPhrase(new Phrase(""));
            table.addCell(cell);
            cell.setPhrase(new Phrase(""));
            table.addCell(cell);
            cell.setPhrase(new Phrase(""));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Total"));
            table.addCell(cell);
            cell.setPhrase(new Phrase(cart.getCostFormat().format(cart.getTotalCost())));
            table.addCell(cell);

            document.add(table);

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("The above total has been charged to this credit card:"));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(ccib.getName()));
            document.add(new Paragraph(ccib.getAddress()));
            document.add(new Paragraph(ccib.getCity() + ", " + ccib.getState() + " " + ccib.getZip()));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(ccib.getType() + "/" + ccib.getRedactedNumber()));
            document.add(new Paragraph("Expires " + ccib.getExpiryMonth() + "/" + ccib.getExpiryYear()));

            document.close();

            // generate attachment
            response.setContentType("application/pdf");
            response.setContentLength(buffer.size());
            response.setHeader("content-disposition", "attachement; filename=registration.pdf");
            response.setHeader("cache-control", "no-cache");

            OutputStream out = response.getOutputStream();
            buffer.writeTo(out);
            out.close();
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
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
