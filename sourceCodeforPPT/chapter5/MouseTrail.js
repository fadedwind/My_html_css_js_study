// MouseTrail.js

// Number of "blips" (divs) used to form the mouse trail
var NUM_BLIPS = 10;
// Each div's id will be this string followed by an integer
var DIV_ID_PREFIX = "mouseTrailDiv";
// CSS class for the "blips"
var CSS_CLASS = "mouseTrailClass";

// Create "blip" divs and add mousemove listener.
function init() {

  // Create div elements that will be "blips" trailing the mouse.
  // Style for these is provided by a separate style sheet.
  for (var i=0; i<NUM_BLIPS; i++) {
    var aDiv = window.document.createElement("div");
    window.document.body.appendChild(aDiv);
    aDiv.setAttribute("id", DIV_ID_PREFIX + i);
    aDiv.setAttribute("class", CSS_CLASS);
  }

  // Listen for every mouse move.
  window.document.addEventListener("mousemove", updateDivs, false);
  return;
}

// Blip 0 will be the first to have its position changed
// when the mouse begins to move.
var nextToChange = 0;
// Has the mouse moved at all yet?
var moved = false;

// mousemove event listener.
function updateDivs(event) {

  var aDiv; // object corresponding to a blip div element

  // If first mouse movement, initialize all blips to be at
  // the mouse cursor location and make them visible.
  if (!moved) {
    moved = true;
    for (var i=0; i<NUM_BLIPS; i++) {
      aDiv = 
        window.document.getElementById(DIV_ID_PREFIX + i);
      aDiv.style.left = event.clientX + "px";
      aDiv.style.top = event.clientY + "px";
      aDiv.style.display = "block";
    }

  // On subsequent calls, change the location of one
  // blip and update a counter indicating which blip
  // should change location after the next mouse move.
  } else {
    aDiv = 
        window.document.getElementById(DIV_ID_PREFIX + nextToChange);
    aDiv.style.left = event.clientX + "px";
    aDiv.style.top = event.clientY + "px";
    nextToChange = (nextToChange+1) % NUM_BLIPS;
  }
  return;
}
