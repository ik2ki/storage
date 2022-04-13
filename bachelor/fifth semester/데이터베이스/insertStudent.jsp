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

	int sno = Integer.parseInt(request.getParameter("sno"));
	int pno = Integer.parseInt(request.getParameter("pno"));
	String sname= (request.getParameter("sname"));
	int year = Integer.parseInt(request.getParameter("year"));
	String dept = request.getParameter("dept");

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
		
		String sql = "SELECT * FROM Professor WHERE pno = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, pno);
		rs = pstmt.executeQuery();

		if(!rs.next()){
%>
		<script>
			alert("지도교수번호 항목에 존재하지 않는 교수가 입력되었습니다.");
			history.back();
		</script>
<%
		}
		sql = "INSERT INTO Student(sno, pno, sname, year, dept) VALUES (?,?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, sno);
		pstmt.setInt(2, pno);
		pstmt.setString(3, sname);
		pstmt.setInt(4, year);
		pstmt.setString(5, dept);
		pstmt.executeUpdate();

%>
				<p> Student 테이블에 새로운 레코드를 삽입(추가)했습니다.</p>
				<p><a href="searchStudent.jsp">확인</a></p>
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