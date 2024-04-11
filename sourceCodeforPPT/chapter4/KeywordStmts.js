// KeywordStmts.js

// Use 'var' to define a loop variable in a 'for'
for (var i=1; i<=3; i++) {

  switch (i) {

    // 'case' value can be any expression and data type, 
    // not just constant int as in Java.  Automatic
    // type conversion is performed if needed.
    case 1.0 + 2:
      window.alert("i = " + i);
      break;
    default:
      try {
        throw("A JavaScript exception can be anything");
        window.alert("This is not executed.");
      }
      // Do not supply exception data type in 'catch'
      catch (e) {
        window.alert("Caught: " + e);
      }
      break;
  }
}
