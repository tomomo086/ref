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
@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//パラメーターを取得
			String name = request.getParameter("name");

			DataAccessBean dab = new DataAccessBean();

			//DataAccessBeanのメソッドにnameを渡す
			dab.deleteRefInfo(name);

			// /findall（一覧表示機能）にRedirectする
			response.sendRedirect(request.getContextPath() + "/findgomi");

		} catch (SQLException e) {
			e.printStackTrace();
			//エラーページへフォワード
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
	}

}
