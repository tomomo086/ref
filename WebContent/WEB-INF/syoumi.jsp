<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<title>賞味期限の早いもの</title>
</head>
<body>
	賞味期限の早いもの
	<table border="1">
		<tr>
			<th>区分</th>
			<th>名前</th>
			<th>数量</th>
			<th>購入日</th>
			<th>備考</th>
		</tr>
		<% // FindsyoumiServletにある(requestスコープに格納された)refsyoumiInfoListにある要素を中に入っている分取り出していく %>
		<c:forEach var="refsyoumiInfo" items="${ refsyoumiInfoList }">
			<tr>
			<% // EL式で出力していく %>
			    <td>${ refsyoumiInfo.type}</td>
				<td>${ refsyoumiInfo.name }</td>
				<td>${ refsyoumiInfo.amount }</td>
				<td>${ refsyoumiInfo.buy }</td>
				<td>${ refsyoumiInfo.note }</td>
				<td>
				<% // ゴミ箱移動機能 %>
				    <form action="${ pageContext.request.contextPath }/movegomi2" method="post">
				        <input type="hidden" name="name2" value="${ refsyoumiInfo.name }" />
				        <input type="submit" value="ゴミ箱" />
				    </form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<% // TODO ssj_ex15 1_【全件表示一覧機能】手順4 新規登録ページへのリンク %>
	<br><a href="${ pageContext.request.contextPath }/findall">一覧表示ページへ</a>
</body>
</html>
