// JavaScript File: regexp.js

// Function to test if a string is an email address
function testEmail(email) {
    var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regex.test(email);
}

// Test the function
console.log(testEmail("test@example.com")); // Should print: true
console.log(testEmail("not an email")); // Should print: false