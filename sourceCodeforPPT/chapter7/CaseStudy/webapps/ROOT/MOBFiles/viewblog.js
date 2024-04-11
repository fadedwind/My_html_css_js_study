// viewblog.js 

/* Make local times show as tool tips when mouse is over
   the date/time of an entry in the view-blog page. */
function init() {

  try {
    // For each div that has class attribute value
    // datetime
    var allDivs = document.getElementsByTagName("div");
    for (var i=0; i<allDivs.length; i++) {
      if (allDivs[i].className == "datetime") {

        // Normalize this div's text and get the content
        // of its first child element (assumed to be a Text node).
        // Use this text to construct a Date object representing
        // the given date and time, then use the toLocaleString()
        // method to get a string representing the time local
        // to the browser.  Assign this string to the title
        // attribute of the div, which produces the tool tip
        // effect.
        allDivs[i].normalize();
        allDivs[i].setAttribute("title",
          new Date(allDivs[i].childNodes[0].data).toLocaleString());
      }
    }
  }
  catch (e) {
  }
  return;
}
