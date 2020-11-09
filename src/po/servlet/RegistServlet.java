package po.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.bean.DataAccessBean;
import po.bean.RefInfo;
import po.errors.DuplicateNameException;

@SuppressWarnings("serial")
@WebServlet(name="RegistServlet",urlPatterns="/regist")
public class RegistServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			//ブラウザからの入力値を取得
			String type = request.getParameter("type");
			String name = request.getParameter("name");
			String amount = request.getParameter("amount");
			String buy = request.getParameter("buy");
			String note = request.getParameter("note");

			//未入力チェック
			if(name == null || name.length() < 1) {
				request.setAttribute("message", "名称を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
				rd.forward(request, response);
			}
			if(amount == null || amount.length() < 1) {
				request.setAttribute("message", "数量を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
				rd.forward(request, response);
			}
			if(buy == null || buy.length() < 1) {
				request.setAttribute("message", "購入日を入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
				rd.forward(request, response);
				//ソースコードの途中の場合はreturnをする
                return;
			}

			RefInfo refInfo = new RefInfo();

			DataAccessBean dab = new DataAccessBean();


			if(type == null || type.length() < 1) {

				refInfo.setType(type);
				refInfo.setName(name);
				refInfo.setAmount(amount);
				refInfo.setBuy(buy);
				refInfo.setNote(note);

				dab.registSyokuhinnRefInfo(refInfo);

			} else {

			//refInfoのオブジェクトに入力値を入れる
			refInfo.setType(type);
			refInfo.setName(name);
			refInfo.setAmount(amount);
			refInfo.setBuy(buy);
			refInfo.setNote(note);

			//DataAccessBeanのregistRefInfoメソッドにrefInfoを渡してdabで呼び出している
			dab.registRefInfo(refInfo);


			}










			//一覧表示ページにリダイレクトする
			response.sendRedirect(request.getContextPath() + "/findall");

		} catch(SQLException e) {
			e.printStackTrace();

			//DB関連の例外はエラーページへ遷移させる
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} catch(DuplicateNameException e) {

			//name(PRIMARY KEY)が重複して登録される場合は下記のメッセージを出力,登録ページへ遷移させる
			request.setAttribute("message", "この項目はすでに登録されています");
			RequestDispatcher rd = request.getRequestDispatcher("/regist.jsp");
			rd.forward(request, response);
		}


	}

}
