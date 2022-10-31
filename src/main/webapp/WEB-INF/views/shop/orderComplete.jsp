<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>

<html>
<head>
<title>STRAP MAIN</title>
<!-- CDN -->
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
</head>
<style>
	th{
		text-align:right;
	}
</style>
<body>
<div class="wrap container">
<!-- 헤더&메뉴바 -->
	<div id="header" class="row">
		<div class="col">
			<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
		</div>
	</div>
<!-- 컨텐츠 -->
	<div id="contents" class="row" style="width:60%; margin:50px auto;">
		<div class="contents col">
			<div id="complete-header">
				<h1><i class="fa-regular fa-calendar-check"></i> 주문이 완료되었습니다.</h1><hr>
			</div>
			<div id="order-products-wrap">
				<div><h3>구매상품 정보</h3></div>
				<c:forEach items="${completeOrder.buyProducts }" var="product" varStatus="n" >
					<div class="oneCart row" style="margin: 10px auto;">
						<div class="pImg col-3" style="text-align:center;">
							<img src="${product.mainImgRoot }" style="width:80px;height:70px;">
						</div>
						<div class="cartInfo col-6">
							<div>
								<div class="pName" style="font-weight:bold;text-align:center;">
									<span class="brandName">[${product.productBrand }]</span>
									<span class="pName">${product.productName }</span>
								</div>
							</div>
							<div>
								<div class="cartPrice-wrap" style="text-align:center;">
									<span class="cartPrice">
										<fmt:formatNumber value="${product.productPrice}" pattern="#,###"/> 
									</span>
									<span class='wonSymbol'>원</span>*
									<span class="pQty">
										${product.orderQty }개
									</span>
								</div>
							</div>
						</div>
						<div class="col-3"  style="font-size:20px;font-weight:bold;text-align:center;">
							<span class="cartPrice">
								<fmt:formatNumber value="${product.productPrice * product.orderQty }" pattern="#,###"/> 
							</span>
							<span class='wonSymbol'>원</span>
						</div>
					</div>
					<input type="hidden" class="calPrice" value="${product.productPrice * product.orderQty }">
				</c:forEach>	
			</div>
			<div id="order-info" class="row">
				<div id="order-info" class="col-6" style="height:350px;margin:70px auto;padding:20px 40px; border: 1px solid #c0c0c0;text-align:left;">
					<div id="orderInfo">
						<h3>주문 정보</h3><hr>
						<table>
							<tr>
								<th>주문번호 : </th>
								<td>${completeOrder.orderNo }</td>
							</tr>
							<tr>
								<th>주문자 : </th>
								<td>${loginUser.memberName }</td>
							</tr>
							<tr>
								<th>전화번호 : </th>
								<td>${completeOrder.contactPhone }</td>
							</tr>
							<tr>
								<th>배송지 : </th>
								<td>${completeOrder.address }</td>
							</tr>
							<tr>
								<th>배송방법 : </th>
								<td>일반택배</td>
							</tr>
							<tr>
								<th>배송메모 : </th>
								<td>${completeOrder.deliveryRequest }</td>
							</tr>
							<tr>
								<th>입금계좌 : </th>
								<td>${completeOrder.vBankName } / ${completeOrder.vBankNum } </td>
							</tr>
							<tr>
								<th>입금기한 : </th>
								<td>${completeOrder.vBankDueDate }</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="paid-info" class="col-6">
					<div class="order-side" style="height:350px;margin:70px auto;padding:20px 40px; border: 1px solid #c0c0c0;text-align:left;">
						 <h3>결제 금액</h3><hr>
						 <table>
							<tr>
								<th>상품금액 : </th>
								<td>
							 		<span id="productsPrice" style="font-weight:bold;font-size:18px;">
							 			<fmt:formatNumber value="${completeOrder.finalCost + completeOrder.discountAmount - completeOrder.deliveryFee }" pattern="#,###"/>원
							 		</span>
								</td>
							</tr>
							<tr>
								<th>할인 금액 : </th>
								<td>
							 		<span id="discountAmount" style="font-weight:bold;font-size:18px;">
							 			<fmt:formatNumber value="${completeOrder.discountAmount }" pattern="#,###"/>원
							 		</span>
								</td>
							</tr>
							<tr>
								<th>배송비 : </th>
								<td>
							 		<span id="deleiveryFee" style="font-weight:bold;font-size:18px;">
							 			<fmt:formatNumber value="${completeOrder.deliveryFee }" pattern="#,###"/>원
							 		</span>
								</td>
							</tr>
							<tr style="padding:12px;">
								<th>최종 결제금액 : </th>
								<td>
							 		<span id=finalCost style="color:darkorange;font-weight:bold;font-size:20px;">
							 			<fmt:formatNumber value="${completeOrder.finalCost }" pattern="#,###"/>원
							 		</span>
								</td>
							</tr>
						</table>
				</div>
			</div>
			<div id="orderComBtn" style="text-align:center;">
				<button style="font-size:26px;font-weight:bold;color:darkorange;background-color:white;border: 2px solid darkorange;border-radius:4px;height:70px;width:200px;" onclick="location.href='/product/listView.strap';">쇼핑계속</button>
				<button style="font-size:26px;font-weight:bold;color:white;background-color:darkorange;border: 2px solid darkorange;border-radius:4px;height:70px;width:200px;" onclick="location.href='/order/listView.strap';">주문내역</button>
			</div>
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer" class="row">
		<div class="col">
			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
		</div>
	</div>
</div>
</body>
</html>
