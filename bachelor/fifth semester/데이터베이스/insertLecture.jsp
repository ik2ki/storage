<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
		<title>레코드 삽입</title>
	</head>
	<body>

<%
	request.setCharacterEncoding("euc-kr");

	String cno = request.getParameter("cno");
	int pno = Integer.parseInt(request.getParameter("pno"));
	String lec_time= request.getParameter("lec_time");
	String room = request.getParameter("room");

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException cnfe){
		cnfe.printStackTrace();
		System.out.println("드라이버 로딩 실패");
	}		
	try{
		String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String userId = "pjaguer";
		String userPass = "pkjaguer";			
		con = DriverManager.getConnection(jdbcUrl, userId, userPass);
		
		String sql = "INSERT INTO Lecture(cno, pno, lec_time, room) VALUES (?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cno);
		pstmt.setInt(2, pno);
		pstmt.setString(3, lec_time);
		pstmt.setString(4, room);
		pstmt.executeUpdate();

%>
				<p>Lecture 테이블에 새로운 레코드를 삽입(추가)했습니다.</p>
				<p><a href="searchLecture.jsp">확인</a></p>
<%
	} catch(Exception e) {
		out.println(e);
	} finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException sqle) {
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch(SQLException sqle) {
			}
		}
	}
%>
	</body>
</html>