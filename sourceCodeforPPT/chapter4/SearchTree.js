// SearchTree.js

// Constructor for BTNode objects
function BTNode(value) {

  /**** Variables ****/
  this.left = this.right = null;
  this.value = value;

  /**** Methods ****/
  this.isLeaf = 
    function leaf() {
      return this.left == null && this.right == null;
    };
}

// Constructor for SearchTree objects
function SearchTree() {

  /**** Variables ****/
  this.root = null;
  
  /**** Methods ****/

  // locate(value) returns a reference to the node
  // containing the value if the given value is in the search tree,
  // returns null if the search tree is empty,
  // and otherwise returns a reference to the node that 
  // should be this value's parent.
  // This method is only intended to be used by other
  // methods, not called directly.
  this.locate = 
    function locate(value) {
      var curr = this.root;  // Node currently being visited
      var parent = null;     // Parent of current node

      // Search for value, remembering parent nodes
      while (curr != null && curr.value != value) {
        parent = curr;
        if (value < parent.value) {
          curr = parent.left;
        }
        else {
          curr = parent.right;
        }
      }

      // If curr is null, we did not locate the value.
      // Return parent (which is null if tree is empty) instead.
      if (curr == null) {
        curr = parent;
      }
      return curr;
    };

  // insert(value) adds the given value to the search
  // tree if it is not already present.  
  this.insert =
    function insert(value) {

      // Search for location of value or its parent
      var location = this.locate(value);

      // If value is not present (location is non-null
      // and location.value is not same as given value), 
      // then create a node containing the value 
      // and insert it at the appropriate location.
      var node = new BTNode(value);
      if (location == null) { // Empty tree
        this.root = node;
      }
      else if (location.value != value) { // location is parent
        if (value < location.value) {
          location.left = node;
        }
        else {
          location.right = node;
        }
      }
    };

  // search(value) returns true iff the given value is
  // present in the tree
  this.search = 
    function search(value) {

      // Search for location of value or its parent
      var location = this.locate(value);

      // Found value iff location is not null and value
      // of location is the given value
      return location != null && location.value == value;
    };
}

// Create a search tree and insert values
var tree = new SearchTree();
tree.insert(7);
tree.insert(3);
tree.insert(12);
tree.insert(12); // Try inserting a second time

// Search the tree
window.alert("Search for 12 produces " + tree.search(12));
window.alert("Search for 6 produces " + tree.search(6));
window.alert('Search for "3" produces ' + tree.search("3"));
