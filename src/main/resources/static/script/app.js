// mobile navigation
const mobileView = document.querySelector(".mobile-view");
const desktopView = document.querySelector(".navbar");
const gridIcon = document.querySelector(".icon[name='grid']");
const closeIcon = document.querySelector(".icon[name='close-circle']");

const toggleNavbar = ()=>{
    desktopView.classList.toggle("active");

    if(desktopView.classList.contains("active")){
        gridIcon.style.display = "none";
        closeIcon.style.display = "block";
    }
    else{
        gridIcon.style.display = "block";
        closeIcon.style.display = "none";
    }
}
mobileView.addEventListener("click", () => toggleNavbar());


// card dropdown
function toggleDropdown(button) {
    const dropdown = button.parentElement;
    dropdown.classList.toggle('active');
}

// Close dropdowns when clicking outside
document.addEventListener('click', (e) => {
    const dropdowns = document.querySelectorAll('.dropdown');
    dropdowns.forEach(dropdown => {
        if (!dropdown.contains(e.target)) {
            dropdown.classList.remove('active');
        }
    });
});
