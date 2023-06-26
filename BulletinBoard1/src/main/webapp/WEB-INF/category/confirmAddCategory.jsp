<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String additionCategory = request.getParameter("additionCategory");
	String additionCategoryKana = request.getParameter("additionCategoryKana");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー追加確認画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/tableGridAndWhite.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="CategoryListServlet">カテゴリー一覧・検索画面</a><span>></span></li>
				<li><a href="AddCategoryServlet">カテゴリー追加画面</a><span>></span></li>
				<li><span>カテゴリー追加確認画面</span></li>
			</ul>
			<h1>カテゴリー追加確認</h1>
		</header>
		<div>
			<p>入力内容を確認してください</p>
			<form action="ConfirmAddCategoryServlet" method="post">
				<table>
					<tr>
						<th class="width30">・カテゴリー名</th>
						<td class="width60 "><%=additionCategory %></td>
					</tr>
					<tr>
						<th >・カテゴリー名（カナ）</th>
						<td ><%=additionCategoryKana %></td>
					</tr>
				</table>
				<input type="hidden" name="additionCategory"		value=<%=additionCategory %>>
				<input type="hidden" name="additionCategoryKana"	value=<%=additionCategoryKana %>>
				<button class="create btn floatR margin10" type="submit" value="">
					<img src="/BulletinBoard/images/plus.png"> カテゴリー追加
				</button>
			</form>
		</div>
	</body>
</html>