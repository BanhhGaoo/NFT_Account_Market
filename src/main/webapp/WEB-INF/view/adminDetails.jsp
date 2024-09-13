<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Thông tin Admin</title>
<link
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/styles.css"
    rel="stylesheet" type="text/css" />
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<style>
	body > div > div > div.col-md-3 {
		background-color: white;
	    box-shadow: 0px 0px 5px 1px #aaaaaa;
    	padding: 0;
	}
	
	body > div > div {
		padding-left: 6%;
	}
	
	body > div > div > div.col-md-9 {
		padding-left: 5%;
	}
	
	body > div > div > div.col-md-3 {
		max-width: 15%;
	}
</style>

</head>

<body>
    <%@include file="layout/header.jsp"%>
    <div class="user-details">
    	<div class="row" style="min-height: 45vh;">
    		<!-- Menu con bên trái -->
	        <div class="col-md-3">
	            <div class="list-group">
	                <a href="#" class="list-group-item list-group-item-action">Thông tin cá nhân</a>
	                <a href="/admin/${sessionScope.loggedInAdmin.adminId}/accountManagement" class="list-group-item list-group-item-action">Quản lý account</a>
	                <form action="${pageContext.request.contextPath}/logout" method="get" style="display:inline;">
	                    <button type="submit" class="list-group-item list-group-item-action list-group-item-danger">Đăng xuất</button>
	                </form>
	            </div>
	        </div>
    		
    		<div class="col-md-9">
    			<h2>Thông tin Admin</h2>
		        <form action="${pageContext.request.contextPath}/updateAdmin" method="post" style="display:inline;">
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="card">
		                        <div class="card-body">
		                            <h5 class="card-title">Admin ID</h5>
		                            <p class="card-text">${admin.adminId}</p>
		                            <input type="hidden" name="adminId" value="${admin.adminId}">
		                        </div>
		                    </div>
		                    <div class="card">
		                        <div class="card-body">
		                            <h5 class="card-title">Password</h5>
		                            <input type="password" class="form-control input-details" name="password" value="${admin.password}">
		                        </div>
		                    </div>
		                    
		                    
		                </div>
		                <div class="col-md-6">
		                    <div class="card">
		                        <div class="card-body">
		                            <h5 class="card-title">Admin Name</h5>
		                            <p class="card-text">${admin.adminName}</p>
		                            <input type="hidden" name="adminName" value="${admin.adminName}">
		                        </div>
		                    </div>
		                    <div class="card">
		                        <div class="card-body">
		                            <h5 class="card-title">Email</h5>
		                            <input type="email" class="form-control input-details" name="email" value="${admin.email}">
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <button type="submit" class="btn btn-outline-primary">Cập nhật</button>
		        </form>
    		</div>
    	</div>
    </div>

    <%@include file="layout/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
