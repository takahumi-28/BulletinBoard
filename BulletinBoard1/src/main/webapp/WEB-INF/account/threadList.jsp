<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="EsingleThreadsModel.ThreadInfo" %>   
<%@ page import="EsingleThreadsModel.CategoryInfo" %>    
<%  
	request.setCharacterEncoding("UTF-8");
	
	int isAdmin = (Integer)request.getAttribute("admin"); 
	String deletedThreadTitle = (String)request.getAttribute("sendDeletedThreadTitle");
	String message = (String)request.getAttribute("message");
	ArrayList<CategoryInfo> categories = (ArrayList<CategoryInfo>)request.getAttribute("sendCategories");
	ArrayList<ThreadInfo> threads = (ArrayList<ThreadInfo>)request.getAttribute("sendThreads");
	String strPageNum = request.getParameter("page");
	int pageNum = 1;
	if(strPageNum != null){
		pageNum = Integer.parseInt(strPageNum);
	}
	int pageNumMax = (int)(Math.ceil(threads.size() / (double)10));
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>スレッド一覧</title>
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/base.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/thread.css">
		<link rel="stylesheet" type="text/css" href="/BulletinBoard/css/pagination.css">
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<body>
		<div class="flex">
			<div class="left-contents">
				<header>
					<h1 class="titleLogo"><span>『E'SingleThreads』</span>にようこそ！</h1>
					<div class="searchBox marginTB50">
						<form action="/BulletinBoard/ThreadListServlet">
							<input class="search" type="text" name="searchedThreadTitle" 
								size="40" placeholder="キーワードを入力" required="required">
							<input class="create searchBtn" type="submit" value="スレッド検索">
						</form>
					</div>		
				</header>
				<div>
					<h4>スレッドタイトル一覧</h4>
					<%if(deletedThreadTitle != null){ %>
						<p class="msg"><%=deletedThreadTitle %>が削除されました</p>
					<%} %>	
					<form action="/BulletinBoard/AdminFilter/DeleteThreadServlet">
						<table class="threadTitleTable">
							<% if(threads != null){ %>
								<%for(int i = (pageNum -1) * 10; i <= Math.min(pageNum * 10,threads.size() - 1); i++){ %>
									<tr>
										<td class="threadTitleCategory"><%=threads.get(i).getCategoryName() %></td>
										<td class="threadTitle">
											<a href="/BulletinBoard/ThreadDetailServlet?threads.get(i)Code=<%=threads.get(i).getThreadCode() %>">
												<%=threads.get(i).getThreadName() %>
											</a>
										</td>
										<%if(isAdmin == 1){ %>
											<td>
												<button class="delete btn" type="submit" 
													name="deletedthreadCode" value=<%=threads.get(i).getThreadCode() %>>
														<i class="far fa-trash-alt"></i>削除
												</button>
											</td>
										<%} %>	
									</tr>
								<%} %>
							<%} %>
						</table>				
					</form>
				</div>
				<div class="pagination">
					<ul>
						<%if(pageNum >= 2){%>
							<li>
								<form action="/BulletinBoard/ThreadListServlet">
									<input type="hidden" name="page" value=<%=pageNum - 1 %>>
									<button><</button>
								</form>
							</li>
						<%} %>
						<%for(int i = 1; i <= pageNumMax; i++ ){ %>
							<li>
								<form action="/BulletinBoard/ThreadListServlet">
									<input type="hidden" name="page" value=<%=i %>>
									<button><%=i %></button>
								</form>
							</li>
						<%} %>
						<%if(pageNum <= pageNumMax - 1){%>
							<li>
								<form action="/BulletinBoard/ThreadListServlet">
									<input type="hidden" name="page" value=<%=pageNum + 1 %>>
									<button>></button>
								</form>
							</li>
						<%} %>
					</ul>
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