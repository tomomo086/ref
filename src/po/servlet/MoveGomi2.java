package po.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.bean.DataAccessBean;

@SuppressWarnings("serial")
@WebServlet(name="MoveGomi2", urlPatterns="/movegomi2")
public class MoveGomi2 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String name2 = request.getParameter("name2");
			DataAccessBean dab2 = new DataAccessBean();
			dab2.movegomiRefInfo(name2);
			response.sendRedirect(request.getContextPath() + "/findsyoumi");

		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
