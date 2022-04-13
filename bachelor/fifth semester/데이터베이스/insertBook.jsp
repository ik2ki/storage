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

	String ISBN = request.getParameter("ISBN");
	String title = request.getParameter("title");
	int year = Integer.parseInt(request.getParameter("year"));
	int price = Integer.parseInt(request.getParameter("price"));
	String aname = request.getParameter("aname");
	String pname = request.getParameter("pname");
	String code = request.getParameter("code");
	int num = Integer.parseInt(request.getParameter("num"));

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
		
		String sql="SELECT * FROM Author WHERE name=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, aname);
		rs=pstmt.executeQuery();
		
		if(!rs.next()){
%>
		<script>
			alert("작가 항목에 존재하지 않는 작가가 입력되었습니다.");
			history.back();
		</script>
<%
		}
		
		sql="SELECT * FROM Publisher WHERE name=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, pname);
		rs=pstmt.executeQuery();
		
		if(!rs.next()){
%>
		<script>
			alert("출판사 항목에 존재하지 않는 출판사가 입력되었습니다.");
			history.back();
		</script>
<%
		}
		
		sql="SELECT * FROM WareHouse WHERE code=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, code);
		rs=pstmt.executeQuery();
		
		if(!rs.next()){
%>
		<script>
			alert("창고코드 항목에 존재하지 않는 창고코드가 입력되었습니다.");
			history.back();
		</script>
<%
		}
		if(ISBN==null){
%>
		<script>
			alert("ISBN은 반드시 입력되어야 합니다.");
			history.back();
		</script>
<%
		}
		sql = "INSERT INTO Book(ISBN, title, year, price) VALUES (?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ISBN);
		pstmt.setString(2, title);
		pstmt.setInt(3, year);
		pstmt.setInt(4, price);
		pstmt.executeUpdate();
		
		sql="SELECT address FROM Author WHERE name=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,aname);
		rs = pstmt.executeQuery();
		
		rs.next();
		String address = rs.getString("address");
		
		sql="INSERT INTO Written_by(name, address, ISBN) VALUES (?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,aname);
		pstmt.setString(2,address);
		pstmt.setString(3,ISBN);
		pstmt.executeUpdate();
		
		sql="INSERT INTO Published_by(name, ISBN) VALUES (?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,pname);
		pstmt.setString(2,ISBN);
		pstmt.executeUpdate();
		
		sql="INSERT INTO Stocks(ISBN, code, num) VALUES (?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,ISBN);
		pstmt.setString(2,code);
		pstmt.setInt(3,num);
		pstmt.executeUpdate();
		
%>
				<p> Book 테이블에 새로운 레코드를 삽입(추가)했습니다.</p>
				<p><a href="searchBook.jsp">확인</a></p>
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