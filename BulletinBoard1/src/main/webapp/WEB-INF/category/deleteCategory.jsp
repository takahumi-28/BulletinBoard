<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String categoryCode = request.getParameter("sendCategoryCode");
	String categoryName = request.getParameter("sendCategoryName");
	String categoryNameKana = request.getParameter("sendCategoryNameKana");
%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カテゴリー削除確認画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/tableGridAndWhite.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="CategoryListServlet">カテゴリー一覧・検索画面</a><span>></span></li>
				<li><span>カテゴリー削除確認画面</span></li>
			</ul>
			<h1>カテゴリーの削除確認</h1>
		</header>
		
		<div class="margin10_200">
			<p>削除内容を確認してください</p>
			<form action="DeleteCategoryServlet" method="post">
				<table class="back-white">
					<tr>
						<td class="width50">カテゴリー名</td>
						<td class="width50"><%=categoryName %></td>
					</tr>
					<tr>
						<td>カテゴリー名（カナ）</td>
						<td><%=categoryNameKana %></td>
					</tr>
				</table>
				<input type="hidden" name="sendCategoryCode" value=<%=categoryCode %>> 
				<button type="submit" class="delete btn floatR margin10"><i class="far fa-trash-alt"></i>　削除</button> 
			</form>
		</div>
	</body>
</html>