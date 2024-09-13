<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang chi tiết</title>
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
	                <a href="/user/${sessionScope.loggedInUser.userId}" class="list-group-item list-group-item-action">Thông tin cá nhân</a>
	                <a href="#" class="list-group-item list-group-item-action">Account của tôi</a>
	                <form action="${pageContext.request.contextPath}/logout" method="get" style="display:inline;">
	                    <button type="submit" class="list-group-item list-group-item-action list-group-item-danger">Đăng xuất</button>
	                </form>
	            </div>
	        </div>
    		
    		<div class="col-md-9">
    			<h2>Account của tôi</h2>
		        
				    <!-- Kiểm tra xem có gameAccounts không -->
				    <c:choose>
				        <c:when test="${not empty gameAccounts}">
				            <!-- Danh sách tài khoản game -->
				            <div class="row" id="accountList">
				                <c:forEach var="account" items="${gameAccounts}">
				                    <div class="col-md-4">
				                        <div class="card mb-4">
				                            <c:if test="${not empty account.images}">
				                                <a href="${pageContext.request.contextPath}/productDetail?accountId=${account.accountId}">
				                                    <img src="${pageContext.request.contextPath}/img/${account.images[0].imageUrl}"
				                                         class="card-img-top" alt="Game Image" />
				                                </a>
				                            </c:if>
				                            <div class="card-body text-center">
				                                <h5 class="card-title">${account.accountId}</h5>
				                                <p class="card-text">${account.description}</p>
				                                <p class="card-text">Price: $${account.price}</p>
				                                <a href="${pageContext.request.contextPath}/productDetail?accountId=${account.accountId}">
				                                    <button type="button" class="btn btn-dark">Chi tiết Account</button>
				                                </a>
				                            </div>
				                        </div>
				                    </div>
				                </c:forEach>
				            </div>
				
				            <!-- Phân trang -->
				            <div class="pagination mt-4">
				                <ul class="pagination justify-content-center">
				                    <c:forEach var="i" begin="0" end="${totalPages - 1}">
				                        <li class="page-item ${i == currentPage ? 'active' : ''}">
				                            <a class="page-link" href="?page=${i}">${i + 1}</a>
				                        </li>
				                    </c:forEach>
				                </ul>
				            </div>
				        </c:when>
				        <c:otherwise>
				            <!-- Thông báo khi không có gameAccounts -->
				            <div class="alert alert-info text-center mt-4" role="alert">
				                No game accounts found.
				            </div>
				        </c:otherwise>
				    </c:choose>
				
    		</div>
    	</div>
    </div>

    <%@include file="layout/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@solana/web3.js@latest/lib/index.iife.js"></script>
    
</body>

</html>
