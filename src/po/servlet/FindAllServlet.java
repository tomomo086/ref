package po.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.bean.DataAccessBean;
import po.bean.RefInfo;

@SuppressWarnings("serial")
@WebServlet(name = "FindAllServlet", urlPatterns = "/findall")
public class FindAllServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,IOException{

			try{

				DataAccessBean dab = new DataAccessBean();
				Collection<RefInfo> refInfoList = dab.findAllRefInfo();
				request.setAttribute("refInfoList", refInfoList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
				rd.forward(request, response);

			} catch(SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			}
		}

}
