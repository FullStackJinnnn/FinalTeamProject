document.addEventListener("DOMContentLoaded", function () {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const phoneNumberRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;
    const passwordRegex = /^(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]).{8,16}$/;

    const emailInput = document.getElementById("email");
    const phoneNumberInput = document.getElementById("ph");
    const phError = document.getElementById("phError");
    const pwInput = document.getElementById("pw");

    
});
