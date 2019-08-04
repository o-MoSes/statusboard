/**
 * Adds js datepicker to fields of add status modal
 */
window.addEventListener('load', function() {
		$("#begin").datepicker({
			format : 'dd.mm.yyyy',
			startDate : '0d'
		});
		$("#end").datepicker({
			format : 'dd.mm.yyyy',
			startDate : '0d'
		});
	});