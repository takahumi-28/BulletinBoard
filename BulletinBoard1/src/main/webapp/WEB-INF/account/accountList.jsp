<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="EsingleThreadsModel.UserAccountInfo" %>

<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<UserAccountInfo> accounts = (ArrayList<UserAccountInfo>)request.getAttribute("sendAccounts");
	String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント一覧・検索画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
		<link rel="stylesheet" type="text/css" href="css/pagination.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body class="accountList">
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><span>アカウント一覧・検索画面</span></li>
			</ul>
			<h1>アカウント一覧・検索</h1>
			<div class="flex">
				<div class="searchBox">
					<form>
						<input class="search" type="text" name="searchThreadTitle" size="30" placeholder="ユーザー名を入力">
						<input class="searchBtn create" type="submit" value="アカウント検索">
					</form>
				</div>
				<div class="newResister">
					<form action="AccountRegistServlet"> 
						<button class="create btn" type="submit" value=""><img src="../images/person.png"> 新規登録</button>
					</form>	
				</div>	
			</div>
		</header>
		<div>
			<p>アカウント一覧</p>
			<%if(message != null){ %>
				<p><%= message %></p>
			<%} %>
				<table>
					<tr>
						<td>No.</td>
						<td>会員ID</td>
						<td>メール</td>
						<td>ユーザー名</td>
						<td>生年月日</td>
						<td>性別</td>
						<td>管理者権限</td>
						<td>登録者</td>
						<td>登録日</td>
						<td>更新者</td>
						<td>更新日</td>
					</tr>
					<%for(int i = 0; i < accounts.size(); i++){ %>
						<tr class="border-bottom">
							<td><%=i + 1 %></td>
							<td><%= accounts.get(i).getUserId() %></td>
							<td><%= accounts.get(i).geteMail() %></td>
							<td><%= accounts.get(i).getUserName() %></td>
							<td><%= accounts.get(i).getBirthDay() %></td>
							<td><% if(accounts.get(i).getGender() == 0){
										%>男<%
									} else {
										%>女<%
									}%></td>
							<td><% if(accounts.get(i).getAdministrator() == 0){
										%>無<%
									} else {
										%>有<%
									}%></td>
							<td><%= accounts.get(i).getResister_user() %></td>
							<td><%= accounts.get(i).getResister_date() %></td>
							<td><% if(accounts.get(i).getUpdate_user() != null ) {
										%><%=accounts.get(i).getUpdate_user()%>
								   <%} else {
									   %>無<%
								   }%></td>
							<td><% if(accounts.get(i).getUpdate_date() != null ) {
										%><%=accounts.get(i).getUpdate_date()%>
								   <%} else {
									   %>無<%
								   }%></td>
						 <form action="AccountCorrectServlet">
							<td class="border-none"><button  type="submit" class="modify btn" name="modify" value=<%= accounts.get(i).getUserId() %>><img src="../images/modify.png"> 修正</button></td> 
						</form>
						<form action="AccountDeleteServlet">
							<td class="border-none"><button type="submit" class="delete btn" name="delete" value=<%= accounts.get(i).getUserId() %>><i class="far fa-trash-alt" ></i> 削除</button></td>
						</form>
						</tr>
						
					<%} %>
				</table>
			<div class="pagination">
				<ul>
					<li><a href="accountList.jsp">前へ</a></li>
					<li><a href="accountList.jsp">1</a></li>
					<li><a href="accountList.jsp">2</a></li>
					<li><a>3</a></li>
					<li><a>4</a></li>
					<li><a>5</a></li>
					<li><a>次へ</a></li>
				</ul>
			</div>
		</div>
	</body>
</html>