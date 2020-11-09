<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<title>新規登録</title>
</head>
<body>
	新規登録
	<br>
	<br> ${ message }
	<br>
	<br>
	<% //このページへのリンクの設定%>
	<form action="${ pageContext.request.contextPath }/regist"
		method="post">
		<% //表を作成する %>
		<table>
		    <tr>
				<th>区分</th>
				<td><input type="text" name="type" /></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<th>数量</th>
				<td><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<th>購入日</th>
				<td><input type="text" name="buy" /></td>
			</tr>
			<tr>
				<th>備考</th>
				<td><input type="text" name="note" /></td>
			</tr>
		</table>
		<input type="submit" value="登録" />
	</form>
	<% // 一覧表示ページへのリンク %>
	<a href="${ pageContext.request.contextPath }/findall">一覧ページへ</a>
</body>
</html>
