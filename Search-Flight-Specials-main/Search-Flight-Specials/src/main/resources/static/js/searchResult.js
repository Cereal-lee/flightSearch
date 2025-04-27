let flightType = 'direct'; // デフォルトは直行便
const flightDetails = JSON.parse(localStorage.getItem('flightDetails'));
const selectOption = JSON.parse(localStorage.getItem('selectOption'));

document.addEventListener('DOMContentLoaded', function() {
	const directFlightBtn = document.getElementById('directFlightBtn');
	const connectingFlightBtn = document.getElementById('connectingFlightBtn');

	directFlightBtn.addEventListener('click', function() {
		flightType = 'direct';
		updateResults();
		this.classList.replace('btn-primary', 'btn-secondary');
		connectingFlightBtn.classList.replace('btn-secondary', 'btn-primary');
	});

	connectingFlightBtn.addEventListener('click', function() {
		flightType = 'connecting';
		updateResults();
		this.classList.replace('btn-primary', 'btn-secondary');
		directFlightBtn.classList.replace('btn-secondary', 'btn-primary');
	});

	updateResults(); // 初期表示
});

function updateResults() {
	let cardsHtml = "";
	if (selectOption == 2) { //往復
		flightDetails.data.forEach(item => {
			const inboundItemLen = item.itineraries[0].segments.length;
			const outboundItemLen = item.itineraries[1].segments.length;
			const fee = item.price.grandTotal;
			if ((flightType === 'direct' && inboundItemLen === 1 && outboundItemLen === 1) || (flightType === 'connecting' && inboundItemLen > 1) || (flightType === 'connecting' && outboundItemLen > 1)) {
				if (inboundItemLen === 1 && outboundItemLen === 1) {
					// 直行便の処理
					cardsHtml += `<div class="col">
					                <div class="card h-100">
					                    <div class="card-header bg-secondary text-white">
					                        ${getCarriers(item.itineraries[0])} / ${getCarriers(item.itineraries[1])}
					                    </div>
					                    <div class="card-body">
					                        <h5 class="mb-3">出国便</h5>
					                        ${getFlightDetails(item.itineraries[0])}
					                        <hr class="my-3">
					                        <h5 class="mb-3">帰国便</h5>
					                        ${getFlightDetails(item.itineraries[1])}
					                    </div>
					                    <div class="card-footer">
					                        <strong>料金: ${fee || 'N/A'}</strong>
					                    </div>
					                </div>
					            </div>`;
				} else {
					// 乗り継ぎ便の処理
					cardsHtml += `<div class="col">
					                <div class="card h-100">
					                    <div class="card-header bg-secondary text-white">
					                        ${getCarriers(item.itineraries[0])} / ${getCarriers(item.itineraries[1])}
					                    </div>
					                    <div class="card-body">
					                        <h5 class="mb-3">出国便</h5>
					                        ${getFlightDetails(item.itineraries[0])}
					                        <hr class="my-3">
					                        <h5 class="mb-3">帰国便</h5>
					                        ${getFlightDetails(item.itineraries[1])}
					                    </div>
					                    <div class="card-footer">
					                        <strong>料金: ${fee || 'N/A'}</strong>
					                    </div>
					                </div>
					            </div>`;
				}
			}
		});
	} else if (selectOption == 1) { // 片道
		flightDetails.data.forEach(item => {
			const itemLen = item.itineraries[0].segments.length;
			if ((flightType === 'direct' && itemLen === 1) || (flightType === 'connecting' && itemLen > 1)) {
				if (itemLen === 1) {
					// 直行便の処理
					const segment = item.itineraries[0].segments[0];
					const carrier = flightDetails.dictionaries.carriers[segment.carrierCode];
					const departureTerminal = segment.departure.iataCode;
					const departureAt = segment.departure.at.split("T")[1];
					const arrivalTerminal = segment.arrival.iataCode;
					const arrivalAt = segment.arrival.at.split("T")[1];
					const fee = item.price.grandTotal;
					cardsHtml += `<div class="col">
                                    <div class="card h-100">
                                        <div class="card-header bg-secondary text-white">
                                            ${carrier || 'N/A'}
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-6">
                                                    <h5 class="card-title">${departureTerminal || 'N/A'}</h5>
                                                    <p class="card-text">${departureAt || 'N/A'}</p>
                                                </div>
                                                <div class="col-6 text-end">
                                                    <h5 class="card-title">${arrivalTerminal || 'N/A'}</h5>
                                                    <p class="card-text">${arrivalAt || 'N/A'}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <strong>料金: ${fee || 'N/A'}</strong>
                                        </div>
                                    </div>
                                </div>`;
				} else {
					// 乗り継ぎ便の処理
					const carriers = [];
					const departureTerminals = [];
					const departureTimes = [];
					const arrivalTerminals = [];
					const arrivalTimes = [];
					const fee = item.price.grandTotal;
					for (let i = 0; i < itemLen; i++) {
						const segment = item.itineraries[0].segments[i];

						carriers.push(flightDetails.dictionaries.carriers[segment.carrierCode]);
						departureTerminals.push(segment.departure.iataCode);
						departureTimes.push(segment.departure.at.split("T")[1]);
						arrivalTerminals.push(segment.arrival.iataCode);
						arrivalTimes.push(segment.arrival.at.split("T")[1]);
					}
					const multiCarriers = getMultiCarriers(carriers);
					cardsHtml += `<div class="col">
                                    <div class="card h-100">
                                        <div class="card-header bg-secondary text-white">
                                            ${multiCarriers || 'N/A'}
                                        </div>
                                    <div class="card-body">`;
					for (let i = 0; i < itemLen; i++) {
						cardsHtml += `<div class="row">
                                            <div class="col-6">
                                                <h5 class="card-title">${departureTerminals[i] || 'N/A'}</h5>
                                                <p class="card-text">${departureTimes[i] || 'N/A'}</p>
                                            </div>
                                            <div class="col-6 text-end">
                                                <h5 class="card-title">${arrivalTerminals[i] || 'N/A'}</h5>
                                                <p class="card-text">${arrivalTimes[i] || 'N/A'}</p>
                                            </div>
                                        </div>`
						if (i < itemLen - 1) {
							cardsHtml += `<hr style = "margin-top:5px;margin-bottom:3px">`
						}
					}
					cardsHtml += `</div>
                                    <div class="card-footer">
                                        <strong>料金: ${fee || 'N/A'}</strong>
                                    </div>
                                </div>
                                </div>`;
				}
			}
		});
	}
	document.getElementById('results').innerHTML = cardsHtml;
}

function getCarriers(itinerary) {
	const carriers = itinerary.segments.map(segment => flightDetails.dictionaries.carriers[segment.carrierCode]);
	return getMultiCarriers(carriers);
}

function getFlightDetails(itinerary) {
	let detailsHtml = '';
	itinerary.segments.forEach((segment, index) => {
		const departureTerminal = segment.departure.iataCode;
		const departureAt = segment.departure.at.split("T")[1];
		const arrivalTerminal = segment.arrival.iataCode;
		const arrivalAt = segment.arrival.at.split("T")[1];

		detailsHtml += `
            <div class="row mb-2">
                <div class="col-6">
                    <h6 class="card-title">${departureTerminal || 'N/A'}</h6>
                    <p class="card-text">${departureAt || 'N/A'}</p>
                </div>
                <div class="col-6 text-end">
                    <h6 class="card-title">${arrivalTerminal || 'N/A'}</h6>
                    <p class="card-text">${arrivalAt || 'N/A'}</p>
                </div>
            </div>`;

		if (index < itinerary.segments.length - 1) {
			detailsHtml += `<hr style="margin-top:5px;margin-bottom:3px">`;
		}
	});
	return detailsHtml;
}

function getMultiCarriers(carriers) {
	let firstCarriers = [carriers[0]];

	for (let i = 1; i < carriers.length; i++) {
		if (carriers[i] != firstCarriers) {
			firstCarriers += " → ";
			firstCarriers += carriers[i];
		}
	}
	return firstCarriers;
}
