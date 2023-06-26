<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="EsingleThreadsModel.ThreadDetailInfo" %>
<%@ page import="EsingleThreadsModel.CommentInfo" %>
<%@ page import="EsingleThreadsModel.CategoryInfo" %>         
<% 
	int isAdmin = (Integer)request.getAttribute("admin"); 
	String deleteComeMsg = (String)request.getAttribute("deleteComeMsg");
	String message = (String)request.getAttribute("message");
	ArrayList<CategoryInfo> categories = (ArrayList<CategoryInfo>)request.getAttribute("sendCategories");
	ThreadDetailInfo threadDetail = (ThreadDetailInfo)request.getAttribute("sendThreadDetail");
	ArrayList<CommentInfo> comments = (ArrayList<CommentInfo>)request.getAttribute("sendComments");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>スレッド詳細画面</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/thread.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body>
		<div class="flex">
			<div class="left-contents marginB100">
				<header>
					<ul>
						<li><a href="ThreadListServlet">TOP</a><span>></span></li>
						<li><span>スレッド詳細画面</span></li>
					</ul>
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
				</header>
				<div class="threadContent">
					<p><%=threadDetail.getThreadDetail() %></p>
					<%if(deleteComeMsg != null){ %>
						<p class="msg"><%=deleteComeMsg %></p>
					<%} %>					
						<ul>
							<%for(CommentInfo comment : comments){ %>
								<li class="comment">
									<form action="/BulletinBoard/AdminFilter/DeleteCommentServlet">
										<input type="hidden" name="threadCode" value=<%=comment.getThreadCode() %>>
										<input type="hidden" name="commentCode" value=<%=comment.getCommentCode() %>>
										<input type="hidden" name="userName" value=<%=comment.getUserName() %>> 
										<input type="hidden" name="contribution" value=<%=comment.getContribution() %>> 
										<input type="hidden" name="contents" value=<%=comment.getContents() %>> 
										<p>名前:<%=comment.getUserName() %> <%=comment.getContribution() %>
											<%if(isAdmin == 1){ %>
												<button class="delete btn" type="submit">
													<i class="far fa-trash-alt"></i>　削除
												</button>
											<%} %>	
										</p>
										<p><%=comment.getContents() %></p>
									</form>
								</li>
							<%} %>
						</ul>
					<%if(isAdmin == 0 || isAdmin == 1){ %>
						<%if(comments.size() <= 50){ %>
							<form action="/BulletinBoard/MemberFilter/PostCommentServlet">
								<button class="create btn floatR" type="submit">
									<img src="/BulletinBoard/images/comment.png"> コメントする
								</button>
							</form>
						<%}else{ %>
							<p>コメント数が50件のため、コメントすることができません</p>
						<%} %>	
					<%} %>
				</div>
			</div>
			<div class="right-navi marginT50">
				<%if(isAdmin == 0 || isAdmin == 1){ %>
						<form action="/BulletinBoard/MemberFilter/CreateThreadServlet">
							<button class="create btn floatR marginL50"type="submit">
								<img src="/BulletinBoard/images/thread.png"> スレッド作成
							</button> 
						</form>
				<%} %>
				<a href="/BulletinBoard/ThreadListServlet">TOPに戻る</a>
				<form action="/BulletinBoard/ThreadListServlet">
					<input class="width100" type="text" name="searchedThreadTitle" 
						placeholder="キーワードを入力" required="required">
					<button class="create btn floatR" type="submit" value="スレッド検索">
						<img src="/BulletinBoard/images/searchIcon.png"> スレッド検索
					</button>
				</form>
				<div class="categoryList marginT50">
					<h5><img src="/BulletinBoard/images/category.png">カテゴリー一覧</h5>
					<ul>
						<%for(CategoryInfo category : categories){ %>
							<li>
								<a href="/BulletinBoard/ThreadListServlet?categoryCode=<%=category.getCategoryCode() %>">
									<%=category.getCategoryName() %>
								</a>
							</li>					
						<%} %>
					</ul>
				</div>
				<div class="login">
					<%if(isAdmin == 2){ %>
						<form action="/BulletinBoard/LoginServlet" method="post">
							<p>e-mail</p>
							<input type="text" name="eMail" placeholder="メールアドレス" required="required"
									pattern="^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\.)+[a-zA-Z]{2,}$">
								<p>password</p>
								<input type="password" name="pass" placeholder="パスワード" required="required">
							<button class="btn loginBtn" type="submit">
								<img src="/BulletinBoard/images/login.png"> ログイン
							</button>
						</form>
					<%}else{ %>
						<form action="LogoutServlet" method="post">
							<button class="btn margin10" type="submit">ログアウト</button>
						</form>
					<%} %>
					<%if(message != null){ %>
						<p><%=message %></p>
					<%} %>								
				</div>
				<%if(isAdmin == 1) {%>
					<div class="adminMenu">
						<h5><img src="/BulletinBoard/images/admin.png">管理者メニュー</h5>
						<ul>
							<li>
								<a href="/BulletinBoard/AdminFilter/CategoryListServlet">
									<img src="/BulletinBoard/images/category.png">カテゴリー一覧へ
								</a>
							</li>
							<li>
								<a href="AccountListServlet">
									<img src="/BulletinBoard/images/account.png">アカウント一覧へ
								</a>
							</li>	
						</ul>
					</div>
				<%}%>
			</div>
		</div>			
	</body>
</html>