package ua.org.oreva.studyNN.servlets;

import ua.org.oreva.studyNN.db.GeoDataImporter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Olga Reva
 * Date: 1/1/14
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final String jspURL = "/uploadResult.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

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
		final Part filePart = request.getPart("file");
		InputStream fileContent = null;

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(jspURL);
		try {
			fileContent = filePart.getInputStream();

			GeoDataImporter importer = new GeoDataImporter(request.getServletContext());
			importer.importGeoData(new InputStreamReader(fileContent));

			request.setAttribute("result", "File uploaded!");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("result", "Upload failed with exception: " + e.getLocalizedMessage());
			rd.forward(request, response);
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
