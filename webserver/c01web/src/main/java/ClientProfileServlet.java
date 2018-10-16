
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Scanner;

/**
 * Servlet implementation class ClientProfileServlet
 */
@WebServlet("/ClientProfile")
public class ClientProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO return 200 or error
		try (
				Scanner sc = new Scanner(new File("dbconfig.txt"));
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + sc.nextLine() + "?useSSL=false&allowPublicKeyRetrieval=true", sc.nextLine(),
						sc.nextLine());
				)
		{
			// String firstname = request.getParameter("firstname");
			// String lastname = request.getParameter("lastname");

			// String insert = String.format("INSERT INTO test VALUES (%s, %s)", firstname,
			// lastname);

			//String insert = "INSERT INTO " + "test" + "(firstname, lastname) " + "VALUES(?,?)";

			//PreparedStatement insertSql = conn.prepareStatement(insert);
			// insertSql.setString(1, firstname);
			// insertSql.setString(2, lastname);
			//insertSql.executeUpdate();

			response.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.getOutputStream().println(e.getMessage());
		}
	}

}
