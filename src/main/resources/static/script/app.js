// Select necessary elements
const popUpModal = document.getElementById('popUp');
const taskList = document.querySelector('.task_list');

// Function to open the modal
function openModal() {
    popUpModal.classList.add("active");
    taskList.classList.add("hidden");

    // Focus on the modal for accessibility
    popUpModal.focus();
}

// Function to close the modal
function closeModal(event) {
    // Prevent default form submission if used on a button inside a form
    if (event) event.preventDefault();

    popUpModal.classList.remove("active");
    taskList.classList.remove("hidden");
}

// Event listener for 'Escape' key to close the modal
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape' && popUpModal.classList.contains("active")) {
        closeModal();
    }
});

// Adding event listeners for buttons if needed (optional)
document.querySelector('.modal_button').addEventListener('click', openModal);
document.querySelector('.modal_button[onclick="closeModal()"]').addEventListener('click', closeModal);
