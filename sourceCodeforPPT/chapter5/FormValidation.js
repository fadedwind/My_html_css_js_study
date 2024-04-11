// FormValidation.js

function addListeners() {
  var form = window.document.getElementById("validatedForm");
  form.addEventListener("submit", validateForm, false);
  return;
}

function validateForm(event) {
  var textfield = window.document.getElementById("requiredField");
  var fieldValue = textfield.value; // getAttribute doesn't work here!

  // If text field contains only white space, do not submit form
  if (/^\s*$/.test(fieldValue)) {
    window.alert("Data must be entered in the field\n" +
                 "before submitting the form");
    event.preventDefault();
    textfield.select();
  }
  return;
}
