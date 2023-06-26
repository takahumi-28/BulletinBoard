<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%int gender = 0; int isAdmin = 0; int isLocked = 0;%>
<%
	String correct = (String)request.getAttribute("correct");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント修正画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/account.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="ThreadListServlet">TOP</a><span>></span></li>
				<li><a href="accountList.jsp">アカウント一覧・検索画面</a><span>></span></li>
				<li><span>アカウント修正画面</span></li>
			</ul>
			<h1>アカウントの修正</h1>
		</header>
		<div class="modifyForm">
			<p>修正内容を入力してください</p>
			<form action="AccountCorrectServlet" method="post">
				<table>
					<tr>
						<th>会員ID</th>
						<td><%=correct %><input type="hidden" name="modifyId" value=<%=correct %>></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="text" name="modifyEmail" ></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="modifyPass" ></td>
					</tr>
					<tr>
						<th>ユーザー名</th>
						<td><input type="text" name="modifyName" ></td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<select name="modifyBirthYear">
								<%for(int i = 1950; i <= 2022; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
							<select name="modifyBirthMonth">
								<%for(int i = 1; i <= 12; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
							<select name="modifyBirthDay">
								<%for(int i = 1; i <= 30; i++){ %>
									<option><%=i %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<th>性別</th>
						<td>
							<label>
								<input type="radio" name="modifyGender" value="0" 
									<%if(gender == 0){ %>
										checked="checked"
									<%} %>
								>男性
							</label>
							<label>
								<input type="radio" name="modifyGender" value="1"
									<%if(gender == 1){ %>
										checked="checked"
									<%} %>
								>女性
							</label>		
						</td>
					</tr>
					<tr>
						<th>管理者権限</th>
						<td>
							<label>
								<input type="radio" name="modifyAdmin" value="0" 
									<%if(isAdmin == 0){ %>
										checked="checked"
									<%} %>	
								>無
							</label>
							<label>
								<input type="radio" name="modifyAdmin" value="1"
									<%if(isAdmin == 1) {%>
										checked="checked"
									<%} %>	
								>有
							</label>
						</td>
					</tr>
					<tr>
						<th>ロック解除の有無</th>
						<td>
							<label>
								<input type="radio" name="modifyLock" value="0" 
									<%if(isLocked == 1){ %>
										checked="checked"
									<%} %>	
								>無
							</label>
							<label>
								<input type="radio" name="modifyLock" value="1"
									<%if(isLocked == 0) {%>
										checked="checked"
									<%} %>	
								>有
							</label>
						</td>
					</tr>
				</table>
				<input class="btn create floatR right15" type="submit" value="修正">
			</form>		
		</div>		
	</body>
</html>