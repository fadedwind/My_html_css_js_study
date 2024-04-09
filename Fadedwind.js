function generateRandomText(wordCount) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    for (var i = 0; i < wordCount; i++) {
        for (var j = 0; j < 5; j++) {
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        }
        text += " ";
    }

    return text;
}

window.onload = function() {
    document.getElementById('random-text').innerText = generateRandomText(1000); // Generate 1000 random words
};