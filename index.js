// JS_BASICS
// console.log(`Hello`);
// console.log(`I love pizza!`);
// window.alert(`This is an alert!`);
// window.alert(`I love pizza!`);
// document.getElementById("myH1").textContent = `Hello`;
// document.getElementById("myp").textContent = `I love pizza~`;
// let age = 100;
// let price = 10.99;
// let gpa = 3.5;
// let firstName = "Bro";
// let favoriteFood = "pizza";
// let email = "elwind297@outlook.com";
// let online = false;
// let forSale = true;
// console.log(`i am online:${online}`);
// console.log(typeof firstName);
// console.log(typeof firstName);
// console.log(`You like ${favoriteFood}`);
// console.log('You are ${age} you years old');
// console.log('The price is $${price}');
// console.log('Your gpa is: ${gpa}');
// let fullName = "Fadedwind";
// let age = 20;
// let isStudent = true;
// document.getElementById("p1").textContent = `your name is ${fullName}`;
// document.getElementById("p2").textContent = `you are ${age} years old`;
// document.getElementById("p3").textContent = `Enrolled: ${isStudent}`;


// OPERATIONS
// let student = 30;
// exponent **n   =>   nth power
//  mod    %3    ->   取余3
//  += -= *= /= **=  %=
// ++ --
// also operrator precedence
// console.log(students);


// HOW TO ACCEPT INPUT
// 1.window prompt
// 2.professional way: html textbox;

// let username;
// username = window.prompt(" Username pls.");// WINDOW.PRINT
// console.log(username);
// document.getElementById("mySubmit").onclick = function(){
//     username = document.getElementById("myText").value;   //INPUT
//     document.getElementById("myH1").textContent = `Hello ${username}`;
// }                            // OUTPUT
// let age = window.prompt("how old are you?");
// age = Number(age);
// age+=1;
// console.log(age, typeof age);


// TYPE CONVERSION (STR,NUM,BOOLEANS)
// let x = "0";
// let y = "0";
// let z = "0";
// // let x;
// // let y;    // -> undefined
// // let z;
// //转化为相应的类型
// x = Number(x);
// y = String(y);
// z = Boolean(z);
// console.log(x, typeof x);
// console.log(y, typeof y);
// console.log(z, typeof z);

// JS constants
// const PI = 3.14159;
// let radius;
// let circumference;

// // radius = window.prompt('Enter the radius of a circle');
// // radius = Number(radius);
// // circumference = 2 * PI * radius;
// document.getElementById("mySubmit#02").onclick = function() {
//     radius = document.getElementById("myText#02").value;
//     radius = Number(radius);
//     circumference = 2 * PI * radius;
//     document.getElementById("myH3").textContent = circumference + "cm";
// }

const decreaseBtn = document.getElementById("decreaseBtn");
const resetBtn = document.getElementById("resetBtn");
const increaseBtn = document.getElementById("increaseBtn");
const countlabel = document.getElementById("countLabel");

let count = 0;

increaseBtn.onclick = function() {
    count++;
    countlabel.textContent = count;
}










