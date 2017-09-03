package twitteroperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.util.concurrent.*;
import twitter4j.TwitterException;
import weka.classifiers.Evaluation;

/**
 * Servlet implementation class TwitterServlet
 */
@WebServlet("/TwitterServlet")
public class TwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session= request.getSession(true);
		ArrayList<String> arr1= new ArrayList<String>();
	if(request.getParameter("name")!=null&&request.getParameter("psw")!=null){
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("psw"));
		if(connectDatabase(request.getParameter("name"),request.getParameter("psw"))){
			response.sendRedirect("search.jsp");
		}else{
			request.setAttribute("valid", "invalid");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}else if(request.getParameter("tweets")!=null){
		System.out.println("read file only");
		FileReader fr= new FileReader("C:/B.E Project/SVM/Test Dataset/output.txt");
		BufferedReader br= new BufferedReader(fr);
		String line="";
		while((line=br.readLine())!=null){
			arr1.add(line);
		}
		session.removeAttribute("eval");
		session.removeAttribute("cleanTweets");
		session.setAttribute("tweets", arr1);
		response.sendRedirect("result.jsp");
		//request.getRequestDispatcher("result.jsp").forward(request, response);
	}else if(request.getParameter("cleanTweets")!=null) {
		Preprocess pp= new Preprocess();
		arr1=pp.datapreprocessing();
		session.removeAttribute("tweets");
		session.removeAttribute("eval");
		session.setAttribute("cleanTweets", arr1);
		response.sendRedirect("result.jsp");
	}else if(request.getParameter("summary")!=null) {
		Classification cf= new Classification();
		Evaluation eval = null;
		try {
			eval=cf.summary();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("tweets");
		session.removeAttribute("cleanTweets");
		session.setAttribute("eval", eval);
		response.sendRedirect("result.jsp");
	}
	else{
		System.out.println("new tweets");
		session.removeAttribute("tweets");
		session.removeAttribute("cleanTweets");
		session.removeAttribute("eval");
		request.setAttribute("result", request.getParameter("input"));
		PrintSampleStream pss=new PrintSampleStream();
		
		try {
			pss.collectTweets(request.getParameter("input"));
			
		} catch (TwitterException e) {
			System.out.println("error1");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("error2");
			e.printStackTrace();
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";  
    static final String DB_URL="jdbc:oracle:thin:@localhost:1521:ankit";

    //  Database credentials
    static final String USER = "SYS as SYSDBA";
    static final String PASS = "ankit";

	public boolean connectDatabase(String username, String password){
		
	      // Set response content type
		boolean valid=false;
	      try{
	         // Register JDBC driver
	         Class.forName("oracle.jdbc.driver.OracleDriver");

	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         //System.out.println(conn.isValid(10));
	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	        // String sql;
	         System.out.println(username+" "+password);
	         PreparedStatement ps=conn.prepareStatement("SELECT username,password FROM credentials where username=? and password=?");
	        // sql = "SELECT username,password FROM credentials where username='"+username+"' and password='"+password+"'";
	         //System.out.println(sql);
	        ps.setString(1, username);
	        ps.setString(2, password);
	        
	         ResultSet rs = ps.executeQuery();
	          valid= rs.next();
	          System.out.println(valid);
	         // Extract data from result set
	         /*while(rs.next()){
	        	 System.out.println("abc");	 
	            //Retrieve by column name
	            String id  = rs.getString("username");
	            String age = rs.getString("password");
System.out.println(id+" s"+age);
	            //Display values
	           
	         }*/
	         

	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         conn.close();
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      }
	    return valid;
	}

}
