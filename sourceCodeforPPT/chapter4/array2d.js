// array2d.js

var ttt = [ [ "X", "O", "O" ],
            [ "O", "X", "O" ],
            [ "O", "X", "X" ] ];

// Alternate definition of the same array
var ttt2 = new Array(3);
ttt2[0] = [ "X", "O", "O" ];
ttt2[1] = [ "O", "X", "O" ];
ttt2[2] = [ "O", "X", "X" ];

window.alert(ttt);
window.alert(ttt2);
