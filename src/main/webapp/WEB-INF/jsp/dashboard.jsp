<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglibprefix ="form"
uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="common/header.jspf"%>
<script defer type="text/javascript"
	src="${pageContext.request.contextPath}js/showAddStatusModal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}js/addDatepicker.js"></script>
<script type="text/javascript">
	window.addEventListener('load', function() {
		//modelattribute cant be accessed from external js
		if ("${showAddStatusModal}")
			showModal();
	});
</script>
<title>dashboard</title>
</head>
<body class="h-100">
	<div class="modal fade" id="addStatusModal" tabindex="-1" role="dialog"
		aria-labelledby="addStatusModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content bg-transp">
				<div class="modal-header flex-column align-items-center border-0">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="text-light" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title font-weight-bold ">Status</h4>
				</div>
				<div class="modal-body mt-2">
					<form:form method="POST"
						action="${pageContext.request.contextPath}addStatus"
						modelAttribute="newStatus">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="begin">begin</label>
									<!-- parse and format the toString date returned by EL -->
									<fmt:parseDate value="${newStatus.begin}" var="parsedBegin"
										pattern="yyyy-MM-dd" />
									<fmt:formatDate type="date" value="${parsedBegin}"
										pattern="dd.MM.yyyy" var="formattedBegin" />
									<form:input type="text"
										class="form-control bg-transparent  bottom-border text-white text-center"
										placeholder="begin" id="begin" path="begin"
										value="${formattedBegin}" />
									<form:errors path="begin" style="color:red" />
								</div>
								<div class="form-group">
									<label for="end">end</label>
									<!-- parse and format the toString date returned by EL -->
									<fmt:parseDate value="${newStatus.end}" var="parsedEnd"
										pattern="yyyy-MM-dd" />
									<fmt:formatDate type="date" value="${parsedEnd}"
										pattern="dd.MM.yyyy" var="formattedEnd" />
									<form:input type="text"
										class="form-control bg-transparent bottom-border text-white text-center"
										placeholder="end" path="end" value="${formattedEnd}" />
									<form:errors path="end" style="color:red" />
								</div>
								<div class="form-group">
									<label for="statusSelect">availability</label> <select
										class="form-control  bottom-border text-white"
										id="statusSelect">
										<option>available</option>
										<option>absent</option>
									</select>
								</div>
								<div class="form-group">
									<label for="description">description:</label>
									<form:input type="text"
										class="form-control bg-transparent bottom-border text-white "
										placeholder="Out for lunch with my team, yeah!"
										id="description" path="description"
										value="${newStatus.description}" maxlength="22" />
								</div>
							</div>
						</div>
						<button type="submit"
							class="btn btn-outline-light mx-2 mt-4 login-btn rounded-pill float-right">Submit</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid h-100">
		<div class="row">
			<div class="col px-0">
				<nav class="navbar navbar-expand navbar-top justify-content-between">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">
					<!-- <img
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
								<a href="${pageContext.request.contextPath}/"><i
									class="fas fa-home"></i></a> <a
									href="${pageContext.request.contextPath}#"><i
									class="fas fa-user-cog"></i></a>
								<form class="dropdown-item p-0"
									action="${pageContext.request.contextPath}/checkout"
									method="post">
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
						<button type="button" id="toggleModal"
							class="btn btn-add-status btn-sm ml-auto mt-2 mr-2"
							data-toggle="modal" data-target="#addStatusModal">
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
										<td class="text-center"><a
											href="<c:url value="/editStatus?id=${status.id}"/>"><i
												class="fas fa-pen mx-2"></i></a> <a
											href="<c:url value="/delete/status?id=${status.id}"/>" onclick="if (!(confirm('Do you really wanna delete the status?'))) return false"><i
												class="fas fa-trash mx-2"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="event-container mx-2 my-3 p-2">
					<i class="far fa-calendar-alt fa-lg m-2"></i> Events
					<div id="events">Here are the events...</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>