/**
 * signup viewのJSファイル
 */
window.onload = function() {
	const myModal = document.getElementById('myModal')
	const ModalCloseBtn = document.getElementById("close-modal");
	const modalXBtn = document.getElementById("modal-x-btn");

	if(myModal) {
	const closeModal = () => {
		myModal.style.display = "none"
	}

	ModalCloseBtn.addEventListener("click", closeModal)
	modalXBtn.addEventListener("click", closeModal)
		
	}
	
}