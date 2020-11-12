<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<title>ゴミ箱</title>
</head>
<body>
	ゴミ箱
	<table border="1">
		<tr>
			<th>区分</th>
			<th>名前</th>
			<th>数量</th>
			<th>購入日</th>
			<th>備考</th>
		</tr>

		<% // FindGomiにある(requestスコープに格納された)refgomiInfoListにある要素を中に入っている分取り出していく %>
		<c:forEach var="refgomiInfo" items="${ refgomiInfoList }">
			<tr>
			<% // EL式で出力していく %>
			    <td>${ refgomiInfo.type}</td>
				<td>${ refgomiInfo.name }</td>
				<td>${ refgomiInfo.amount }</td>
				<td>${ refgomiInfo.buy }</td>
				<td>${ refgomiInfo.note }</td>
				<td>
				<% // 削除機能 %>
				    <form action="${ pageContext.request.contextPath }/delete" method="post">
				        <input type="hidden" name="name" value="${ refgomiInfo.name }" />
				        <input type="submit" value="削除" />
				    </form>
				</td>
			</tr>
		</c:forEach>

	</table>
	<% // TODO ssj_ex15 1_【全件表示一覧機能】手順4 新規登録ページへのリンク %>
	<br><a href="${ pageContext.request.contextPath }/findall">一覧ページへ</a>
</html>
