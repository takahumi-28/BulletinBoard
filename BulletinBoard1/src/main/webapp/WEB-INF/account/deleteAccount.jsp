<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="EsingleThreadsModel.UserAccountInfo"%>   

<%
	request.setCharacterEncoding("UTF-8");
	UserAccountInfo delete = (UserAccountInfo)request.getAttribute("goDelete");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント削除確認画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
		<link rel="stylesheet" type="text/css" href="css/tableGridAndWhite.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="accountList.jsp">アカウント一覧・検索画面</a><span>></span></li>
				<li><span>アカウント削除確認画面</span></li>
			</ul>
			<h1>アカウントの削除確認</h1>
		</header>
		<p class="center">対象のユーザーが作成したスレッドとコメントがすべて削除されますが、本当に削除しますか？</p>
		<div class="confirmResisterForm"> 
			<table class="back-white">
				<tr>
					<td>会員ID</td>
					<td><%=delete.getUserId() %></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><%=delete.geteMail() %></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><%=delete.getPassword() %></td>
				</tr>
				<tr>
					<td>ユーザー名</td>
					<td><%=delete.getUserName() %></td>
				</tr>
				<tr>
					<td>生年月日</td>
					<td><%=delete.getBirthDay() %></td>
				</tr>
				<tr>
					<td>性別</td>
					<td>
						<%if(delete.getGender() == 0){ %>
							<%="男" %>
						<%}else{ %>
							<%="女" %>
						<%} %>	
					</td>
				</tr>
				<tr>
					<td>管理者権限</td>
					<td>
						<%if(delete.getAdministrator() == 0){ %>
							<%="会員ユーザー" %>
						<%}else{ %>
							<%="管理者ユーザー" %>
						<%} %>	
					</td>
				</tr>
			</table>
			<form action="AccountDeleteServlet" method="post">
				<button class="btn delete floatR margin10" type="submit"><i class="far fa-trash-alt"></i>　実行</button>
			</form>
		</div>
	</body>
</html>