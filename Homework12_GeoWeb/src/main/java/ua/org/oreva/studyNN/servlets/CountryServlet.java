/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.org.oreva.studyNN.servlets;

import ua.org.oreva.studyNN.db.GeoDataService;
import ua.org.oreva.studyNN.entities.Country;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Olga Reva
 */
@WebServlet(name = "CountryServlet", urlPatterns = {"/countryServlet"})
public class CountryServlet extends HttpServlet {
	private static final String jspURL = "/country.jsp";

	private static final String paramCode = "code";
	private static final String paramName = "name";

	/**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher(jspURL);

	    Map<String, String[]> paramMap = request.getParameterMap();
	    String[] code = paramMap.get(paramCode);
	    String[] name = paramMap.get(paramName);

	    GeoDataService dataService = GeoDataService.instance();
	    ResultSet result = dataService.searchCountries(code != null ? code[0] : null, name != null ? name[0] : null);
	    LinkedList<Country> countries = new LinkedList<Country>();
	    try {
		    while (result.next()) {
			    Country country = new Country(result.getInt(1), result.getString(2));
			    countries.add(country);
		    }
	    } catch (SQLException e) {
		    throw new RuntimeException(e);
	    }

	    request.setAttribute("countries", countries);

	    rd.forward(request, response);

        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CountryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CountryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        } */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
