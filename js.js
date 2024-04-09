// JavaScript File: common.js

// Function to get an element by ID
function getById(id) {
    return document.getElementById(id);
}

// Function to create a new element
function createElement(tag) {
    return document.createElement(tag);
}

// Function to set innerHTML of an element
function setHTML(id, html) {
    var element = getById(id);
    if (element) {
        element.innerHTML = html;
    }
}

// Function to add an event listener
function addEvent(id, event, handler) {
    var element = getById(id);
    if (element) {
        element.addEventListener(event, handler);
    }
}

// Function to remove an event listener
function removeEvent(id, event, handler) {
    var element = getById(id);
    if (element) {
        element.removeEventListener(event, handler);
    }
}

// Function to hide an element
function hide(id) {
    var element = getById(id);
    if (element) {
        element.style.display = 'none';
    }
}

// Function to show an element
function show(id) {
    var element = getById(id);
    if (element) {
        element.style.display = '';
    }
}

// Function to toggle visibility of an element
function toggle(id) {
    var element = getById(id);
    if (element) {
        if (element.style.display === 'none') {
            show(id);
        } else {
            hide(id);
        }
    }
}

// Function to make an Ajax GET request
function ajaxGet(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.responseText);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

// Function to make an Ajax POST request
function ajaxPost(url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.responseText);
        }
    };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(data);
}