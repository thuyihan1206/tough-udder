/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toughudder;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Evan Chan
 */
public class CreateAccountListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // initialize accounts
        ServletContext sc = sce.getServletContext();
        ArrayList<Account> accountList = new ArrayList<>();

        accountList.add(new Account("evan", "chan", "Evan", "Chan", "chchan1206@gmail.com"));
        accountList.add(new Account("tammy", "perrin", "Tammy", "Perrin", "tperrin47@gmail.com"));
        accountList.add(new Account("john", "junghans", "John", "Junghans", "jmasterj@gmail.com"));
        sc.setAttribute("accountList", accountList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no cleanup necessary
    }
;

}
