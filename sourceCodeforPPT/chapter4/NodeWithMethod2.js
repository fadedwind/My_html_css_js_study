// NodeWithMethod2.js

// makeBTNode(value) creates a binary tree node and
// initializes its value to the given argument.
// It also adds an isLeaf() method to the node.
function makeBTNode(value) {
  var node = new Object();
  node.left = node.right = null;
  node.value = value;
  node.isLeaf = 
    function leaf() {
      return this.left == null && this.right == null;
    };
  return node;
}

// Create and initialize two node objects, making the second 
// a child of the first.
var node1 = makeBTNode(3);
var node2 = makeBTNode(7);
node1.right = node2;

// Output the value of isLeaf() on each node
window.alert("node1 is a leaf: " + node1.isLeaf());
window.alert("node2 is a leaf: " + node2.isLeaf());
