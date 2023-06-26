<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)request.getAttribute("sendId");
	String mail = (String)request.getAttribute("sendMail");
	String pass = (String)request.getAttribute("sendPass");
	String name = (String)request.getAttribute("sendName");
	String year = (String)request.getAttribute("sendYear");
	String month = (String)request.getAttribute("sendMounth");
	String day = (String)request.getAttribute("sendDay");
	int gender = Integer.parseInt((String)request.getAttribute("sendGender"));
	int admin = Integer.parseInt((String)request.getAttribute("sendAdmin"));

%>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント登録確認画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
		<link rel="stylesheet" type="text/css" href="css/tableGridAndWhite.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="../thread/threadList.jsp">TOP</a><span>></span></li>
				<li><a href="accountList.jsp">アカウント一覧・検索画面</a><span>></span></li>
				<li><a href="resisterAccount.jsp">アカウント登録画面</a><span>></span></li>
				<li><span>アカウント登録確認画面</span></li>
			</ul>
			<h1>アカウント登録確認</h1>
		</header>
		<div class="confirmResisterForm"> 
			<p>このアカウントを登録してよろしいですか？</p>
			<table class="back-white">
				<tr>
					<td>会員ID</td>
					<td><%= id %></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><%= mail %></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><%= pass %></td>
				</tr>
				<tr>
					<td>ユーザー名</td>
					<td><%= name %></td>
				</tr>
				<tr>
					<td>生年月日</td>
					<td><%= year %>/<%= month %>/<%= day %></td>
				</tr>
				<tr>
					<td>性別</td>
					<td>
						<%if(gender == 0){ %>
							<%="男" %>
						<%}else{ %>
							<%="女" %>
						<%} %>	
					</td>
				</tr>
				<tr>
					<td>管理者権限</td>
					<td>
						<%if(admin == 0){ %>
							<%="会員ユーザー" %>
						<%}else{ %>
							<%="管理者ユーザー" %>
						<%} %>	
					</td>
				</tr>
			</table>
			<form action="AccountRegistConfirmServlet" method="post">
				<input class="btn create floatR margin10" type="submit" value="実行">
			</form>
		</div>
	</body>
</html>