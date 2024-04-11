// HighLowWithFunction.js

var thinkingOf;  // Number the computer has chosen (1 through 1000)
var guess;       // User's latest guess

// This function returns a random number between 1 and
// the argument value 'high'.
function oneTo(high) {
  return Math.ceil(Math.random()*high);
}

// Initialize the computer's number
thinkingOf = oneTo(1000);

// Play until user guesses the number
guess = window.prompt("I'm thinking of a number between 1 and 1000." +
                      "  What is it?", "");
while (guess != thinkingOf) 
{

  // Evaluate the user's guess
  if (guess < thinkingOf) {
     guess = window.prompt("Your guess of " + guess + 
                           " was too low.  Guess again.", "");
  }
  else {
     guess = window.prompt("Your guess of " + guess + 
                           " was too high.  Guess again.", "");
  }
}

// Game over; congratulate the user
window.alert(guess + " is correct!");
