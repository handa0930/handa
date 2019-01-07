package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.logic.LoginLogic;


@WebServlet(name="login",urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String name =req.getParameter("name");
		String pass =req.getParameter("pass");

		if((name.equals("") || name == null)||
				pass.equals("") || pass == null) {
			RequestDispatcher rd = req.getRequestDispatcher("jsp/NotInputEntryJsp.jsp");
			rd.forward(req, resp);
			return;

		}

		LoginLogic login = new LoginLogic();

		if(login.isLogin(name,pass)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user",login.getUser());
			req.setAttribute("catgoryList", login.getCategoryList());
			RequestDispatcher rd = req.getRequestDispatcher("jsp/SearchJsp.jsp");
			rd.forward(req, resp);
			return;

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("jsp/ErrorEntryJsp.jsp");
			rd.forward(req, resp);
			return;
		}


	}

}
