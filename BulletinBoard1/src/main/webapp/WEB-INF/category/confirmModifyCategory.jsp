<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String userName 		= (String)request.getAttribute("sendLoginedUserName");
	String categoryCode	 	= request.getParameter("sendCategoryCode");
	String categoryName 	= request.getParameter("additionCategory");
	String categoryNameKana = request.getParameter("additionCategoryKana");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー修正確認画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/tableGridAndWhite.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="CategoryListServlet">カテゴリー一覧・検索画面</a><span>></span></li>
				<li><a href="ModifyCategoryServlet">カテゴリー修正画面</a><span>></span></li>
				<li><span>カテゴリー修正確認画面</span></li>
			</ul>
			<h1>カテゴリー修正確認</h1>
		</header>
		<div class="margin10_200">
			<p>入力内容を確認してください</p>
			<form action="ConfirmModifyCategoryServlet" method="post">
				<table class="back-white">
					<tr>
						<td class="width30">・ユーザー</td>
						<td class="width60"><%=userName %></td>
					</tr>
					<tr>
						<td>・カテゴリー名</td>
						<td><%=categoryName %></td>
					</tr>
					<tr>
						<td>・カテゴリー名(カナ)</td>
						<td><%=categoryNameKana %></td>
					</tr>
				</table>
				<input type="hidden" name="sendCategoryCode" value=<%=categoryCode %>>
				<input type="hidden" name="sendCategoryName" value=<%=categoryName %>>
				<input type="hidden" name="sendCategoryNameKana" value=<%=categoryNameKana %>>
				<input class="modify btn floatR margin10" type="submit" value="修正">
			</form>
		</div>
	</body>
</html>