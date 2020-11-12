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
@WebServlet(name="MoveGomi", urlPatterns="/movegomi")
public class MoveGomi extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String name = request.getParameter("name");

			DataAccessBean dab = new DataAccessBean();

			dab.movegomiRefInfo(name);

			response.sendRedirect(request.getContextPath() + "/findall");


		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

}
