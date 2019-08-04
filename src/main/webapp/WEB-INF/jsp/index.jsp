<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="common/header.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}js/time.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}js/showLoginLogoutResult.js" defer></script>
<title>statusboard</title>
</head>
<body>
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content bg-transp">
				<div class="modal-header flex-column align-items-center border-0">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="text-light" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title font-weight-bold ">Login</h4>
				</div>
				<div class="modal-body">
					<form action="/authenticate" method="post">
						<div class="form-group mx-2 mt-2 bg-transparent">
							<input type="text" id="position" name="username"
								class="form-control bg-transparent bottom-border text-light"
								placeholder="Position">
						</div>
						<div class="form-group mx-2 mt-4">
							<input type="password" id="password" name="password"
								class="form-control bg-transparent bottom-border text-light"
								placeholder="Password">
							<c:if test="${param.error != null}">
								<p class="text-center mt-3 text-danger">
									<i class="fas fa-times mr-2"></i>Oops, seems like the
									credentials are wrong!
								</p>
							</c:if>
						</div>
						<button type="submit"
							class="btn btn-outline-light mx-2 mt-4 login-btn rounded-pill float-right">Submit</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="d-flex flex-row-reverse w-100 show-btn">
		<!-- using href instead of  data-toggle="modal" data-target="#loginModal" to clean url params when unsuccessfull login is followed by click on chevron-->
		<sec:authorize access="!isAuthenticated()">
			<a href="${pageContext.request.contextPath}/login" class="btn modal-btn mt-4 mr-5"><i
				class="fas fa-chevron-right fa-lg"></i></a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<nav
				class="navbar navbar-expand navbar-top justify-content-between navbar-hidden mt-3">
			<ul class="navbar-nav">
				<li class="nav-item dropdown mr-3">
					<div class="drpdwn nav-link">
						<i class="far fa-user-circle fa-lg drp-toggle mr-1"></i>
						<sec:authentication property="name" />
						<div class="drp-content text-center">
							<div class="py-4"></div>
							<a href="${pageContext.request.contextPath}/dashboard"><i class="fas fa-list"></i></a> <a href="${pageContext.request.contextPath}#"><i
								class="fas fa-user-cog"></i></a>
							<form class="dropdown-item p-0" action="/checkout" method="post">
								<button class="logout-btn" type="submit">
									<i class="fas fa-sign-out-alt"></i>
								</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
					</div>
				</li>
			</ul>
			</nav>
		</sec:authorize>
	</div>
	<div class="container px-0 pt-4">
		<div class="toast">
			<div class="toast-body p-2 logout-toast">
				<i class="fas fa-check mx-2 logout-check"></i>Logout successfull!
			</div>
		</div>
		<div class="row status">
			<div class="col">


				<table class="table mt-5 gradient-table">
					<tbody>
						<c:forEach items="${employees}" var="employee">
							<tr class="grad-bor-bot-left">
								<td>${employee.position}</td>
								<td>${employee.title}&nbsp;${employee.name}</td>
								<c:choose>
									<%--  <c:when test="${employee.position == 'master'}"> --%>
									<c:when test="${employee.getCurrentStatus().available}">
										<td><span class="dot-available"></span></td>
									</c:when>
									<c:otherwise>
										<td><span class="dot-unavailable"></span></td>
									</c:otherwise>
								</c:choose>
								<%-- <td>${employee.status.description}</td> --%>
								<td>${employee.getCurrentStatus().getStatusPeriod()}</td>
								<td>${employee.getCurrentStatus().description}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row events ">
			<div class="col">
				<h4 class="hilight">Events</h4>

				<!-- Event carousel -->
				<div id="carouselExampleControls" class="carousel slide  mb-5"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active" data-interval="7000">
							<h3 class="text-center">${employees[0].position}</h3>
						</div>
						<div class="carousel-item" data-interval="7000">
							<h3 class="text-center">${employees[1].position}</h3>
						</div>
					</div>
					<a class="carousel-control-prev" href="${pageContext.request.contextPath}#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="${pageContext.request.contextPath}#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<!-- show entities during dev -->
				<c:forEach items="${employees}" var="employee">
					<div>
						<p>
							<c:out value="${employee}" />
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--footer for system time-->
	<div class="footer w-100">
		<p id="clock" class="text-center"></p>
	</div>
</body>
</html>