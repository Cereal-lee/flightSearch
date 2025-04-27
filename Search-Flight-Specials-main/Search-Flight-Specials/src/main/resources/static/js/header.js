document.addEventListener('DOMContentLoaded', function() {
	var navbarToggler = document.querySelector('.navbar-toggler');
	var navbarCollapse = document.querySelector('.navbar-collapse');

	navbarToggler.addEventListener('click', function() {
		if (!navbarCollapse.classList.toggle('show')) {
			navbarCollapse.classList.toggle('show');
		}
		else {
			navbarCollapse.classList.remove('show');
		}
	});

	document.addEventListener('click', function(event) {
		var isClickInside = navbarCollapse.contains(event.target)
			|| navbarToggler.contains(event.target);
		if (!isClickInside && navbarCollapse.classList.contains('show')) {
			navbarCollapse.classList.remove('show');
		}
	});
});
