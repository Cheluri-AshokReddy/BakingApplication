// Show registration form and hide other sections
function showRegistrationForm() {
    document.getElementById('main-menu').style.display = 'none';
    document.getElementById('registration-form').style.display = 'block';
}

// Show login form and hide other sections
function showLoginForm() {
    document.getElementById('main-menu').style.display = 'none';
    document.getElementById('login-form').style.display = 'block';
}

// Go back to the main menu from other sections
function backToMainMenu() {
    document.getElementById('registration-form').style.display = 'none';
    document.getElementById('login-form').style.display = 'none';
    document.getElementById('main-menu').style.display = 'block';
}

// Close the system (works only in some browsers)
function exitSystem() {
    window.close(); // This will work if it's opened as a pop-up or in a controlled environment
}
