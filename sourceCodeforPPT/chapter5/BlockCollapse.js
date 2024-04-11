// BlockCollapse.js

// Add a button before the specified element (assumed
// to be block style) that will make the element
// disappear when clicked once and re-appear when
// clicked a second time.
// The button is placed within a div to ensure that
// the markup we generate is valid XHTML.

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
    button.setAttribute("onclick", 
                        "toggleVisibility(this,'" + elementId + "');");
  }
  return;
}

// Function called when the button is clicked.

function toggleVisibility(button, elementId) { 
  var element = window.document.getElementById(elementId);
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
