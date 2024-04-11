// ElapsedTime.js

var startTime = new Date();

// Perform some processing
for (var i=0; i<1e6; i++) {
  var a = i;
}

var endTime = new Date();
window.alert("Processing required " +
             (endTime - startTime)/1000 + 
             " seconds.");
