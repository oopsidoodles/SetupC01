
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Scanner;

import java.net.URLDecoder;

/**
 * Servlet implementation class ClientProfileServlet
 */
@WebServlet("/ClientProfile")
public class ClientProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String dbname;
	private String dbuser;
	private String dbpass;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientProfileServlet() {
		super();
		try
		{
			String s = getClass().getName();
			int i = s.lastIndexOf(".");
			if(i > -1) s = s.substring(i + 1);
			s = s + ".class";
			System.out.println("name " +s);
			String testPath = this.getClass().getResource(s).toString();
			System.out.println(testPath);
			String realpath = URLDecoder.decode(testPath.substring(6), "UTF-8");
			System.out.println(realpath);
			
			Path p = Paths.get(realpath);
			Path folder = p.getParent();
			System.out.println(folder.toString());
			File config = new File(folder.toString() + "\\" + "dbconfig.txt");

			Scanner sc = new Scanner(config);
			dbname = sc.nextLine();
			dbuser = sc.nextLine();
			dbpass = sc.nextLine();
			sc.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + dbname + "?useSSL=false&allowPublicKeyRetrieval=true", dbuser,
						dbpass);
				)
		{
			//Gets params from request
			String unique_identifier = GetParam(request, "unique_identifier");
			String unique_identifier_value = GetParam(request, "unique_identifier_value");
			String date_of_birth = GetParam(request, "date_of_birth");
			String phone_number = GetParam(request, "phone_number");
			String has_email_address = GetParam(request, "has_email_address");
			String email_address = GetParam(request, "email_address");
			
			//Query string
			String query = "INSERT INTO ";
			query += "client_profile"; //TODO table name
			query += "(unique_identifier, unique_identifier_value, date_of_birth, phone_number, has_email_address, email_address) ";
			query += "VALUES(?,?,?,?,?,?)";

			//Sets values into query
			PreparedStatement querySql = conn.prepareStatement(query);
			querySql.setString(1, unique_identifier);
			querySql.setString(2, unique_identifier_value);
			querySql.setString(3, date_of_birth);
			querySql.setString(4, phone_number);
			querySql.setString(5, has_email_address);
			querySql.setString(6, email_address);
			querySql.executeUpdate();

			response.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.getOutputStream().println(e.getMessage());
		}
	}
	
	private String GetParam(HttpServletRequest request, String param)
	{
		return request.getParameter(param);
	}

}
