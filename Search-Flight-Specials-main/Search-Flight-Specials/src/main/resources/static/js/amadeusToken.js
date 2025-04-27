var token = "";

$(document).ready(function() {
	fetch('/api/get-amadeusKey')
		.then(response => response.json())
		.then(data => {
			var key = data.key;
			var secret = data.secret;
			fetch('https://test.api.amadeus.com/v1/security/oauth2/token', {
				body: `grant_type=client_credentials&client_id=${key}&client_secret=${secret}`,
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
				},
				method: "POST"
			}
			)
				.then(res => res.json())
				.then(data => {
					token = data.access_token;
				})
				.catch(error => console.error('Error:', error));
		})
		.catch(error => {
			console.error('Error fetching credentials:', error);
		});
});
