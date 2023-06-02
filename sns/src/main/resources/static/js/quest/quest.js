// quest.js
document.addEventListener("DOMContentLoaded", () => {
	const modal = document.querySelector("#modal");
	const closeButton = document.querySelector(".close-button");
	const images = document.querySelectorAll(".image-container a");
  
	modal.style.display = "block";
	document.body.style.overflow = "hidden";
  
	closeButton.addEventListener("click", () => {
	  modal.style.display = "none";
	  document.body.style.overflow = "auto";
	});
  
	images.forEach((image) => {
	  image.addEventListener("click", (event) => {
		event.preventDefault();
		window.location.href = "/profile";
	  });
	});
  });