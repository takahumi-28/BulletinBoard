<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="EsingleThreadsModel.UserAccountInfo"%>
<%
	request.setCharacterEncoding("UTF-8");
	UserAccountInfo before = (UserAccountInfo)request.getAttribute("sendBefore");
	UserAccountInfo after = (UserAccountInfo)request.getAttribute("sendAfter");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント修正確認画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
		<link rel="stylesheet" type="text/css" href="css/tableGridAndWhite.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="/BulletinBoard/ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="/BulletinBoard/AccountListServlet">アカウント一覧・検索画面</a><span>></span></li>
				<li><a href="modifyAccount.jsp">アカウント修正画面</a><span>></span></li>
				<li><span>アカウント修正確認画面</span></li>
			</ul>
			<h1>アカウント修正確認</h1>
		</header>
		<p>指定されたアカウントを修正しますか？</p>
		<div class="flex">
			<div class="confirmModifyForm"> 
				<p>修正前</p>
				<table class="back-white">
					<tr>
						<td>会員ID</td>
						<td><%=before.getUserId() %></td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><%=before.geteMail() %></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><%=before.getPassword() %></td>
					</tr>
					<tr>
						<td>ユーザー名</td>
						<td><%=before.getUserName() %></td>
					</tr>
					<tr>
						<td>生年月日</td>
						<td><%=before.getBirthDay() %></td>
					</tr>
					<tr>
						<td>性別</td>
						<td>
							<%if(before.getGender() == 0){ %>
								<%="男" %>
							<%}else{ %>
								<%="女" %>
							<%} %>	
						</td>
					</tr>
					<tr>
						<td>管理者権限</td>
						<td>
							<%if(before.getAdministrator() == 0){ %>
								<%="会員ユーザー" %>
							<%}else{ %>
								<%="管理者ユーザー" %>
							<%} %>	
						</td>
					</tr>
				</table>
			</div>
			<div class="confirmModifyForm"> 
				<p>修正後</p>
				<table class="back-white">
					<tr>
						<td>会員ID</td>
						<td><%=after.getUserId() %></td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><%=after.geteMail() %></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><%=after.getPassword() %></td>
					</tr>
					<tr>
						<td>ユーザー名</td>
						<td><%=after.getUserName() %></td>
					</tr>
					<tr>
						<td>生年月日</td>
						<td><%=after.getBirthDay() %></td>
					</tr>
					<tr>
						<td>性別</td>
						<td>
							<%if(after.getGender() == 0){ %>
								<%="男" %>
							<%}else{ %>
								<%="女" %>
							<%} %>	
						</td>
					</tr>
					<tr>
						<td>管理者権限</td>
						<td>
							<%if(after.getAdministrator() == 0){ %>
								<%="会員ユーザー" %>
							<%}else{ %>
								<%="管理者ユーザー" %>
							<%} %>	
						</td>
					</tr>
				</table>
				<form action="AccountCorrectConfirmServlet">
					<input class="btn create floatR margin10" type="submit" value="実行">
				</form>
			</div>
		</div>
	</body>
</html>