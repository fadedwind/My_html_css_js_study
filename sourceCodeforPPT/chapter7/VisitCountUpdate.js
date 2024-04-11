// VisitCountUpdate.js

// Start a timer that every three seconds retrieves from the 
// server the current number of visitors to our site.
function init() {
  window.setInterval("getVisits()", 100);
  return;
}

// Use XMLHttpRequest to request the current number of
// visitors to our site.
function getVisits() {
  var connection;  // Object used to send HTTP requests to server
                   // and receive HTTP responses from server

  // Create the connection object using the appropriate constructor.
  // Many browsers provide XMLHttpRequest as a constructor host object.
  if (window.XMLHttpRequest) {
    connection = new XMLHttpRequest();
  }
  // IE6 provides similar functionality differently
  else if (window.ActiveXObject) {
    try {
      connection = new ActiveXObject("Microsoft.XMLHTTP");
    }
    catch (e) {
    }
  }
  if (connection) {

    // Associate this XMLHttpRequest object with a specific URL.
    // Use POST as the request method to avoid caching of the response.
    // Setting the third argument to true means that responses will
    // be received asynchronously.  The synchronous mode ties up
    // the scripting engine until the response is received and
    // therefore should generally be avoided.
    connection.open("POST", 
                    "http://localhost:8084/autoupdate/servlet/VisitCountUpdate", 
                    true);

    // Send an HTTP request to the server after specifying the
    // function that should be called when the response is received.
    // Note that we use a function expression to create a call 
    // to the actual response handler.  This is done so that the
    // connection object can be passed to the handler.
    // Normally, a non-empty query string would be sent to the
    // server, but our application has no data to send.
    connection.onreadystatechange = 
      function () { updateVisits(connection); };
    connection.setRequestHeader("Content-Type", 
                                "application/x-www-form-urlencoded");
    connection.send("");
  }
  return;
}

// Update the associated HTML document when the HTTP response
// containing the visit count is received.
function updateVisits(connection) {
  if (connection.readyState == 4 && connection.status == 200) {
    var visits = document.getElementById("visits");
    var count = connection.responseXML.documentElement;
    visits.childNodes[0].data = count.childNodes[0].data;
  }
  return;
}
