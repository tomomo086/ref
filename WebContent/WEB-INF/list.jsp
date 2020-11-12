<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<title>冷蔵庫管理表</title>
</head>
<body>
	一覧
	<table border="1">
		<tr>
			<th>区分</th>
			<th>名前</th>
			<th>数量</th>
			<th>購入日</th>
			<th>備考</th>
		</tr>

		<% // FindAllServletにある(requestスコープに格納された)refInfoListにある要素を中に入っている分取り出していく %>
		<c:forEach var="refInfo" items="${ refInfoList }">
			<tr>
			<% // EL式で出力していく %>
			    <td>${ refInfo.type}</td>
				<td>${ refInfo.name }</td>
				<td>${ refInfo.amount }</td>
				<td>${ refInfo.buy }</td>
				<td>${ refInfo.note }</td>
				<td>
				<% // ゴミ箱移動機能 %>
				    <form action="${ pageContext.request.contextPath }/movegomi" method="post">
				        <input type="hidden" name="name" value="${ refInfo.name }" />
				        <input type="submit" value="ゴミ箱" />
				    </form>
				</td>
			</tr>
		</c:forEach>

	</table>
	<% // TODO ssj_ex15 1_【全件表示一覧機能】手順4 新規登録ページへのリンク %>
	<br><a href="${ pageContext.request.contextPath }/regist.jsp">新規登録ページへ</a>
	<br><a href="${ pageContext.request.contextPath }/findsyoumi">賞味期限ページへ</a>
	<br><a href="${ pageContext.request.contextPath }/findgomi">ゴミ箱ページへ</a>
</body>
</html>
