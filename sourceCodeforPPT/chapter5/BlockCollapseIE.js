// BlockCollapseIE.js

// Add a button before the specified element (assumed
// to be block style) that will make the element
// disappear when clicked once and re-appear when
// clicked a second time.
// The button is placed within a div to ensure that
// the markup we generate is valid XHTML.

// This version should work in IE6 as well as more DOM2-compliant
// browsers.

function makeCollapsible(elementId) {
  var element = window.document.getElementById(elementId);
  if (element) {
    var div = window.document.createElement("div");
    element.parentNode.insertBefore(div, element);
    var button = window.document.createElement("button");
    div.appendChild(button);
    button.setAttribute("type", "button");
    var buttonText = window.document.createTextNode("Click to collapse");
    button.appendChild(buttonText);
    if (button.onclick === null) { // e.g., in IE
      button.onclick = toggleVisibility;
      button.elementId = elementId;
    } else { 
      button.setAttribute("onclick", 
                          "toggleVisibility(this,'" + elementId + "');");
    }
  }
  return;
}

// Function called when the button is clicked.

function toggleVisibility(inButton, elementId) { 

  // Local variables whose values will be defined
  // based on the event model supported.
  var button, element;

  // If DOM browser, inButton is an object and
  // elementId is a non-empty, non-Number String,
  // so this condition is true.
  if (inButton && elementId) {
    button = inButton;
    element = window.document.getElementById(elementId);
  }
  // Otherwise, if window.event exists assume this
  // is IE6 and attempt to get data from event and the
  // Element it references through currentTarget.
  else if (window.event) {
    button = this;
    if (button) {
      element = window.document.getElementById(button.elementId);
    }
  }
/*
  var button, element;
  if (inButton && elementId) {
    button = inButton;
    element = window.document.getElementById(elementId);
  } else if (!inButton && window.event) {
    button = window.event.srcElement;
    element = button.parentNode.nextSibling;
  }
*/
  if (element) {
    if (element.style.display == "none") {
      element.style.display = "block";
      button.childNodes[0].data = "Click to collapse";
    } else {
      element.style.display = "none";
      button.childNodes[0].data = "Click to expand";
    }
  }
  return;
}
