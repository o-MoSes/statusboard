<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="common/header.jspf"%>
<title>dashboard</title>
</head>
<body class="h-100">
	<div class="container-fluid h-100">
		<div class="row">
			<div class="col px-0">
				<nav class="navbar navbar-expand navbar-top justify-content-between">
					<a class="navbar-brand" href="/"> <!-- <img
						src="/docs/4.3/assets/brand/bootstrap-solid.svg" width="30"
						height="30" class="d-inline-block align-top" alt=""> --> <i
						class="fas fa-list hilight"></i>&nbsp;statusboard
					</a>
					<ul class="navbar-nav">
						<li class="nav-item dropdown mr-3">
							<div class="drpdwn nav-link">
								<i class="far fa-user-circle fa-lg drp-toggle mr-1"></i>${employeeName}
								<div class="drp-content text-center">
									<div class="py-4"></div>
									<a href="/"><i class="fas fa-home"></i></a>
									<a href="#"><i class="fas fa-user-cog"></i></a>
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
			</div>
		</div>
		<div class="row h-100">
			<div class="col dashboard-container">
				<div class="status-container mx-2 my-3 p-2">
					<div class="d-flex align-items-center">
						<i class="fas fa-door-open fa-lg m-2"></i><span class="mt-1">Status</span>
						<button type="button"
							class="btn btn-add-status btn-sm ml-auto mt-2 mr-2">
							<i class="fas fa-plus"></i>
						</button>
					</div>
					<div>
						<table class="table table-hover mt-2">
							<thead>
								<tr>
									<th scope="col" class="border-top-0 col-period">Period</th>
									<th scope="col" class="border-top-0">Status</th>
									<th scope="col" class="border-top-0">Description</th>
									<th scope="col" class="border-top-0 col-action text-center">Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${statusList}" var="status">
									<tr>
										<td>${status.getStatusPeriod()}</td>
										<c:choose>
											<c:when test="${status.available}">
												<td><span class="dot-available align-middle mr-2"></span>&nbsp;available</td>
											</c:when>
											<c:otherwise>
												<td><span class="dot-unavailable align-middle mr-2"></span>&nbsp;absent</td>
											</c:otherwise>
										</c:choose>
										<td>${status.description}</td>
										<td class="text-center"><a href="#"><i
												class="fas fa-pen mx-2"></i></a> <a href="#"><i
												class="fas fa-trash mx-2"></i></a></td>

									</tr>
								</c:forEach>


								<!-- 	
								<tr>
									<th scope="row">1</th>
									<td>Mark</td>
									<td>Otto</td>
									<td class="text-center"><a href="#"><i
											class="fas fa-pen mx-2"></i></a> <a href="#"><i
											class="fas fa-trash mx-2"></i></a></td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>Jacob</td>
									<td>Thornton</td>
									<td class="text-center"><a href="#"><i
											class="fas fa-pen mx-2"></i></a> <a href="#"><i
											class="fas fa-trash mx-2"></i></a></td>
								</tr>
								<tr>
									<th scope="row">3</th>
									<td>Larry</td>
									<td>the Bird</td>
									<td class="text-center"><a href="#"><i
											class="fas fa-pen mx-2"></i></a> <a href="#"><i
											class="fas fa-trash mx-2"></i></a></td>
								</tr>
								 -->
							</tbody>
						</table>
					</div>
				</div>
				<div class="event-container mx-2 my-3 p-2">
					<i class="far fa-calendar-alt fa-lg m-2"></i> Events
					<div>Here are the events...</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>