<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang Chủ</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
</head>

<body>
	<%@include file="layout/header.jsp"%>

	<main>
		<div class="container">
			<div class="row">
				<div class="col-12 text-center my-4">
					<div class="coming-soon">
						<h1>COMING SOON</h1>
						<p>12-01-2024</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="card mb-4">
						<img src="img/valorant.jpg" class="card-img-top" alt="Valorant" />
						<div class="card-body text-center">
							<h5 class="card-title">VALORANT</h5>
							<a href="/products?gameName=Valorant" class="btn btn-dark mt-3">More</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4">
						<img
							src="img/hinh-nen-dinh-cao-lien-minh-huyen-thoai-danh-cho-pc-va-dien-thoai_21.jfif"
							class="card-img-top" alt="League of Legends" />
						<div class="card-body text-center">
							<h5 class="card-title">LEAGUE OF LEGENDS</h5>
							<a href="/products?gameName=LOL" class="btn btn-dark mt-3">More</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card mb-4">
						<img src="img/tft.jpg" class="card-img-top"
							alt="Team Fight Tactics" />
						<div class="card-body text-center">
							<h5 class="card-title">TEAM FIGHT TACTICS</h5>
							<a href="/products?gameName=TFT" class="btn btn-dark mt-3">More</a>
						</div>
					</div>
				</div>
			</div>
			<div class="divider"> <h2>TRANG CHỦ</h2> </div>
			<div class="row" id="accountList">
				<c:forEach var="account" items="${gameAccounts}">
					<div class="col-md-4">
						<div class="card mb-4">
							<c:if test="${!empty account.images}">
								<a
									href="${pageContext.request.contextPath}/productDetail?accountId=${account.accountId}">
									<img
									src="${pageContext.request.contextPath}/img/${account.images[0].imageUrl}"
									class="card-img-top" alt="Game Image" />
								</a>
							</c:if>
							<div class="card-body text-center">
								<h5 class="card-title">${account.accountId}</h5>
								<p class="card-text">${account.description}</p>
								<p class="card-text">Price: $${account.price}</p>
								<form style="display: inline-block;" action="${pageContext.request.contextPath}/addToCart"
									method="post">
									<input type="hidden" name="gameAccountId"
										value="${account.accountId}"> <input type="hidden"
										name="returnPage" value="productDetail">
									<button type="submit" class="btn btn-dark">Add to cart</button>
								</form>
								<a href="#" class="btn btn-dark">BUY NOW</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="pagination">
			    <ul class="pagination">

			        <c:forEach var="i" begin="0" end="${totalPages - 1}">
			            <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
			                <a class="page-link" href="?page=${i}">${i + 1}</a>
			            </li>
			        </c:forEach>

			    </ul>
			</div>
		</div>
	</main>

	<%@include file="layout/footer.jsp"%>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>