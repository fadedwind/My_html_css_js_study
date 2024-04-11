// LocalScope.js
var j=6;   // global variable declaration and initialization
function test()
{
  var j;   // local variable declaration
  j=7;     // Which variable(s) does this change?
  return;
}
test();
window.alert(j);
