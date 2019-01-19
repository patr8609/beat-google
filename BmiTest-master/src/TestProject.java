

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String buttonString;
	private static final double weight = 20;
	private static final double weight2 = 10;
	private static final double weight3 = 5;
	public Keyword keyword;
	public ArrayList<Keyword> keywords;
	public WebTree news;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}


		keyword = new Keyword();
		keywords = new ArrayList<>();
		String input = request.getParameter("keyword");
		int m = input.indexOf(" ");
		String input1 = input;
		String input2 = "review";
		String text = "movie";  
		String text2 = "review";
		String text3 = "popular";

		if (!input.isEmpty()) {
			System.out.print(text);
			text= text2;
			while (input1.contains(" ")) {
				m = input1.indexOf(" ");
				input2 = input1.substring(m + 1, input1.length());
				input1 = input1.substring(0, m);

				keyword.addKeyword(new Keyword(input1, weight, buttonString));
				text = text + "+" + input1;
				input1 = input2;
			}
			keyword.addKeyword(new Keyword(input1, weight, buttonString));
			text = text + "+" + input1;
		}

		if (buttonString != "") {
			keyword.addKeyword(new Keyword(text2, weight2, buttonString));
			if (input.isEmpty()) {
				text = text2;
			
			}
		} else {
			keyword.addKeyword(new Keyword(text3, weight3, buttonString));
			if (input.isEmpty()) {
				text = text3;
			}
		}

		keywords = keyword.keywords;
		try {
			new HtmlMatcher(buttonString, text);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			news = new WebTree(keywords);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String[] related = HtmlMatcher.relatedKeyword;
		request.setAttribute("related", related);

		String[][] result = news.sort();	
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}
        
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
