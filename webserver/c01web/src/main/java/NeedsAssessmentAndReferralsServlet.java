

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NeedsAssessmentAndReferralsServlet
 */
@WebServlet("/NeedsAssessmentAndReferrals")
public class NeedsAssessmentAndReferralsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String dbname;
	private String dbuser;
	private String dbpass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeedsAssessmentAndReferralsServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + dbname + "?useSSL=false&allowPublicKeyRetrieval=true", dbuser,
						dbpass);
				)
		{
			//Gets params from request
			String unique_identifier = GetParam(request, "unique_identifier");
			
			//Query string
			String query = "INSERT INTO ";
			query += "needs_assessment_and_referrals"; //TODO table name
			query += "(unique_identifier) ";
			query += "VALUES(?)"; //Add extra question mark for each attribute

			//Sets values into query
			PreparedStatement querySql = conn.prepareStatement(query);
			querySql.setString(1, unique_identifier);
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
