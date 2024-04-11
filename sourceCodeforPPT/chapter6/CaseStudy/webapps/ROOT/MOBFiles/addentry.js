// addentry.js

/* Show a preview of the blog entry in a separate window. */
function showPreview() {

  var windowName = "MOBPreviewWindow"; // Name for new window

  // Open a separate window
  var previewWindow = window.open("about:blank", windowName);

  // Target the response to the form at the new window
  var addForm = document.getElementById("addForm");
  addForm.setAttribute("target", windowName);

  // Indicate to the server that this is a preview
  var doPreview = document.getElementById("doPreview");
  doPreview.value = "true";

  // Submit the form
  document.getElementById("addentry").click();

  // Reset form to original values
  doPreview.value = "false";
  addForm.setAttribute("target", "");

  // Give the new window the focus
  previewWindow.focus();
  return;
}

/* Add event handlers to buttons. */
function init() {

  var clear = document.getElementById("clear");
  if (clear.onclick !== null) { 
    clear.addEventListener("click", confirmClear, false);
  }
  else {  // non-DOM browser; assume IE6
    clear.onclick = confirmClear;
  }
  return;
}

/* Confirm that user wants to clear form. */
function confirmClear(event) {
  var OK = window.confirm("Clear the form?");
  if (!OK) {
    if ((arguments.length == 1) && window.Event &&
        (arguments[0] instanceof window.Event)) {
      event.preventDefault();
    }
    else { // non-DOM browser; assume IE6
      window.event.returnValue = false;    
    }
  }
  return;
}
