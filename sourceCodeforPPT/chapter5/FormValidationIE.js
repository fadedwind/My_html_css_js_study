// FormValidationIE.js

function addListeners() {
  var form = window.document.getElementById("validatedForm");
  form.onsubmit = validateForm;
  return;
}

function validateForm() {
  var textfield = window.document.getElementById("requiredField");
  var fieldValue = textfield.value; // getAttribute can be used
                                    // instead in IE6
  // var fieldValue = textfield.getAttribute("value");

  // If text field contains only white space, do not submit form
  if (/^\s*$/.test(fieldValue)) {
    window.alert("Data must be entered in the field\n" +
                 "before submitting the form");
    window.event.returnValue = false;
    textfield.select();
  }
  return;
}
