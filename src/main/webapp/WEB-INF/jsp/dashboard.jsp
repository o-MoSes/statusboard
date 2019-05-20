<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta charset="UTF-8">
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
									<a href="#"><i class="fas fa-user-cog"></i></a>
									<form class="dropdown-item" action="/checkout" method="post">
										<button class="logout-btn" type="submit">
											<i class="fas fa-sign-out-alt"></i>
										</button>
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>
								</div>
							</div>
						</li>



						<!-- 
						<li class="nav-item dropdown mr-3"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"><i class="far fa-user-circle fa-lg"></i>&nbsp;${username}</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Profile</a>
								<div class="dropdown-divider"></div>
								<form class="dropdown-item" action="/checkout" method="post">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <input type="submit" value="Logout">
								</form>
							</div></li> -->
					</ul>
				</nav>
			</div>
		</div>
		<div class="row h-100">
			<div class="col dashboard-container">
				<div class="status-container mx-2 my-3 p-2">

					<div class="">
						<i class="fas fa-door-open fa-lg m-2"></i>Status
					</div>
					<div>
						<table class="table table-hover mt-3">
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
										<th>${status.begin}</th>
										<td>${status.available}</td>
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