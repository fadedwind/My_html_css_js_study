// ArgChange.js

var message = "bye";

function change(message) {
  message = "hi";
  window.alert(message);
  return;
}

change(message);
window.alert(message);
