// TreeOutline.js

// treeOutline returns a string representing an
// outline of the Element nodes in the document tree.
function treeOutline() {
  return subtreeOutline(document.documentElement, 0);
}

// subtreeOutline returns a string representing
// an outline corresponding to the tree
// structure of a Node tree rooted at "root".
// "level" indicates indentation level of root.
function subtreeOutline(root, level) {
  var retString = "";  // String to be returned

  // Work around browsers that don't support Node
  var elementType = window.Node ? Node.ELEMENT_NODE : 1;

  // If this root is an Element node, then print its name
  // and recursively process any children it has.
  if (root.nodeType == elementType) {
    retString += printName(level, root.nodeName);
    var children = root.childNodes;
    for (var i=0; i<children.length; i++) {
      retString += subtreeOutline(children[i], level+1);
    }
  }
  return retString;
}

// printName creates a string consisting of
// the given string "thisName", indented
// as indicated by "level".
function printName(level, thisName) {
  var retString = "";
  for (var i=0; i<level; i++) {
    retString += "..";
  }
  retString += thisName + "\n";
  return retString;
}
