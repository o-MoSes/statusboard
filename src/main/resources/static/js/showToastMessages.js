/**
 * 
 */

var urlParams = new URLSearchParams(window.location.search);

//window.onload = function() {
window.addEventListener('load', function() {
	if (urlParams.has('statusDeleted')) {
		$('.toast').toast({
			delay : 2500
		});
		$('.toast').toast('show');
	}
})