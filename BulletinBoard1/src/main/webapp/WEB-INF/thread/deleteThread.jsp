<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="EsingleThreadsModel.ThreadDetailInfo" %>
<%@ page import="EsingleThreadsModel.CommentInfo" %>
<%@ page import="EsingleThreadsModel.CategoryInfo" %>    

<%	
	ThreadDetailInfo threadDetail = (ThreadDetailInfo)request.getAttribute("sendThreadDetail");
	ArrayList<CommentInfo> comments = (ArrayList<CommentInfo>)request.getAttribute("sendComments");
	String deletedThreadCode = request.getParameter("deletedThreadCode");
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>スレッド削除確認画面</title>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/thread.css">
	</head>
	<body>
		<header>
			<ul>
				<li><a href="ThreadListServlet">TOP</a><span>></span></li>
				<li><span>スレッド削除確認画面</span></li>
			</ul>
			<h1>スレッドの削除確認</h1>
		</header>
		<p>削除内容を確認してください</p>
		<form action="DeleteThreadServlet" method="post">
			<div class="left-contents marginB100">
				<div>
					<div class="flex">
						<div class="category">
							<p>カテゴリー</p>
							<p><%=threadDetail.getCategoryName() %></p>
						</div>
						<div class="threadDetailTitle">
							<h1><%=threadDetail.getThreadName() %></h1>															
							<ul>
								<li class="marginL50">作成者 <%=threadDetail.getUserName() %></li>
								<li class="floatR marginL50">作成日　<%=threadDetail.getThreadDate() %></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="threadContent">
					<p><%=threadDetail.getThreadDetail() %></p>
					
						<ul>
							<%for(CommentInfo comment : comments){ %>
								<li class="comment">
									<p>名前:<%=comment.getUserName() %> <%=comment.getContribution() %></p>
									<p><%=comment.getContents() %></p>
								</li>
							<%} %>
						</ul>
						<input type="hidden" name="deletedThreadTitle" value=<%=threadDetail.getThreadName() %>>
						<input type="hidden" name="deletedThreadCode" value=<%=deletedThreadCode %>>
						<input type="submit" value="削除" class="delete btn floatR margin10">
				</div>
			</div>
		</form>
	</body>	
</html>