/**
 * Checking the request-params added by spring-security to show login-modal or
 * logout-toast
 */
var urlParams = new URLSearchParams(window.location.search);

window.onload = function() {

	if (urlParams.has('error') || window.location.pathname === "/login") {
		$('#loginModal').modal('show');

	} else if (urlParams.has('logout')) {
		$('.toast').toast({
			delay : 2500
		});
		$('.toast').toast('show');
	}

	
	// delay is required when animation is used (Not working in developer mode)
	$("#loginModal").on("shown.bs.modal", function() {
		$('#position').delay(1000).focus();
	});

};
