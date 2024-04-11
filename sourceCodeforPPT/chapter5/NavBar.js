// NavBar.js

function highlight(element) {
  element.style.backgroundColor = "silver";
  return;
}
function lowlight(element) {
  if (element.style) {
    if (element.style.setProperty) {
      element.style.setProperty("background-color", "gray", "");
    } else if (element.style.backgroundColor) {
      element.style.backgroundColor = "gray";
    }
  }
  return;
}
function changeLight(element) {
  if (element.style.backgroundColor == "gray") {
    element.style.backgroundColor = "silver";
  } else {
    element.style.backgroundColor = "gray";
  }
  return;
}
