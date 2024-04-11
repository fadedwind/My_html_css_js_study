// HostObjects.js

/* Functions to create a small pop-up window and move/resize
   it every second until 10 seconds have elapsed without the
   user clicking a button. */

var popup;         // Reference to popup window's global object
var intervalID;    // ID of one-second interval timer
var countdownElt;  // span containing number of seconds until popup closes

// init is called when document has loaded.
// It creates a pop-up window and a one-second interval timer.
function init() {
  popup = window.open("HostObjectsPopUp.html", "popup", 
                      "width=100,height=100");
  intervalID = window.setInterval("messWithPopUp();", 1000);
  countdownElt = window.document.getElementById("countdown");
  return;
}

// resetCountdown is called when user clicks button.
// It changes a "countdown" value that is displayed in the HTML document.
function resetCountdown() {
  countdownElt.childNodes[0].data = "10";
  popup.focus();  // Make sure the pop-up is still visible.
  return;
}

// messWithPopUp is called every second by the interval timer.
// It decrements the "countdown" value, closes the pop-up window
// if the countdown has reached 0, and otherwise moves and resizes
// the pop-up.
function messWithPopUp() {
  var secondsLeft = countdownElt.childNodes[0].data - 1;
  countdownElt.childNodes[0].data = String(secondsLeft);
  if (secondsLeft == 0) {
    window.clearInterval(intervalID);
    popup.close();
  } else {
    popup.moveBy(10,10);
    popup.resizeBy(10,10);
    popup.focus();
  }
  return;
}
