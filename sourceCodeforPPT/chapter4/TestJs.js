// TestJs.js
// Used to load and test other JavaScript files.

function init() {
  var scriptFile = prompt("Enter name of .js file to be tested", "");
  if (!scriptFile) {
    window.alert("No file entered.");
  }
  else {
    var scriptElt = document.createElement("script");
    document.body.appendChild(scriptElt);
    scriptElt.setAttribute("type", "text/javascript");
    scriptElt.setAttribute("src", scriptFile);
  }
  return;
}
