<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)request.getAttribute("sendId");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント登録画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="../thread/threadList.jsp">TOP</a><span>></span></li>
				<li><a href="accountList.jsp">アカウント一覧・検索画面</a><span>></span></li>
				<li><span>アカウント登録画面</span></li>
			</ul>
			<h1>アカウントの登録</h1>
		</header>
		<div class="resisterForm">
			<form action="AccountRegistServlet" method="post" >
				<table>
					<tr>
						<th>会員ID</th>
						<td><%= id %></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="text" name="resisterEmail" required="required"></td>
						<td><span>※必須</span></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="resisterPass" required="required"></td>
						<td><span>※必須</span></td>
					</tr>
					<tr>
						<th>ユーザー名</th>
						<td><input type="text" name="resisterName" required="required"></td>
						<td><span>※必須</span></td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<select name="resisterBirthYear">
								<%for(int i = 1950; i <= 2022; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
							<select name="resisterBirthMonth">
								<%for(int i = 1; i <= 12; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
							<select name="resisterBirthDay">
								<%for(int i = 1; i <= 30; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
						</td>
						<td><span>※必須</span></td>
					</tr>
					<tr>
						<th>性別</th>
						<td>
							<label><input type="radio" name="resisterGender" value="0" checked="checked">男性</label>
							<label><input type="radio" name="resisterGender" value="1" >女性</label>		
						</td>						
					</tr>
					<tr>
						<th>管理者権限</th>
						<td>
							<label><input type="radio" name="resisterAdmin" value="0" checked="checked">無</label>
							<label><input type="radio" name="resisterAdmin" value="1">有</label>
						</td>						
					</tr>
				</table>
				<button class="btn create floatR right15" type="submit" value="">確認</button>
			</form>		
		</div>		
	</body>
</html>