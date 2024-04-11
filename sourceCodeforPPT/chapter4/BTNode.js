// BTNode.js

// BTNode(value) is a constructor for a binary tree node.
// It initializes its value to the given argument.
// It also adds an isLeaf() method to the node.
function BTNode(value) {
  // Notice that we no longer need to create an Object
  // and that we use "this" to reference the object
  // initialized.
  this.left = this.right = null;
  this.value = value;
  this.isLeaf = 
    function leaf() {
      return this.left == null && this.right == null;
    };
  // Notice that we no longer return a value.
}

// Create and initialize two node objects, making the second 
// a child of the first.
// Notice the use of "new" to call a function as a constructor.
var node1 = new BTNode(3);
var node2 = new BTNode(7);
node1.right = node2;

// Output the value of isLeaf() on each node
window.alert("node1 is a leaf: " + node1.isLeaf());
window.alert("node2 is a leaf: " + node2.isLeaf());

// Test that node1 is an instance of BTNode
window.alert("node1 is instance of BTNode: " + 
             (node1 instanceof BTNode));
