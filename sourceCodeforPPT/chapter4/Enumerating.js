// Enumerating.js
var hash = new Object();
hash.kim = "85";
hash.sam = "92";
hash.lynn = "78";
for (var aName in hash)
{
	window.alert(aName+" scored "+hash[aName]);
	window.alert(window.aName+" scored "+hash[aName]);
	window.alert(hash.aName+" scored "+hash[aName]);
}
 
