// makeAdd.js
function makeAdd(op1) {
  return function (op2) { return op1 + op2; };
}
var add4 = makeAdd(4);
window.alert(add4(6));
