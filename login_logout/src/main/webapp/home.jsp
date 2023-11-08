<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>홈페이지</title>
<script>
	function displayLoginFail(){
		alert("로그인에 실패하였습니다.");
	}	
</script>
</head>
<body>
 <h1>홈페이지에 오신 걸 환영합니다.</h1>
 <!-- 만약에 로그인 상태일 경우 로그인 버튼을 숨기고 싶고
 	로그아웃 버튼이 보이게 하고 싶을 경우 -->
 	<%
 	if(session.getAttribute("mno")!= null){
 	%>
		<a href="logout.jsp">로그아웃</a>
 	<%
 	} else {
 	%>
		<a href="login.jsp">로그인</a>
 	<%
 	}
 	%>
 	
 	<% 
	String loginError = (String)request.getAttribute("loginError");

 	if(loginError != null){
 	%>
	<script>
		displayLoginFail();
	</script>
 	<%
 		}
 	%>
</body>
</html>