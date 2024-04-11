// ListReOrder.js

// Switch item in an ordered list with
// the item following it, if there is one.
function switchItems(itemNode) {

  // Work around browsers that don't support Node
  var elementType = window.Node ? Node.ELEMENT_NODE : 1;

  // Locate the item following itemNode, skipping
  // over any non-Element nodes (white space, comments,
  // etc.).  The XHTML DTD only allows li elements
  // as content of an ol, so any Element following
  // itemNode shouldbe an li.  But we'll test the nodeName
  // property just to be safe.
  var nextItem = itemNode.nextSibling;
  while (nextItem && 
         !(nextItem.nodeType == elementType &&
           nextItem.nodeName.toLowerCase() == "li")) {
    nextItem = nextItem.nextSibling;
  }

  // If there is a next item, remove it and re-insert
  // it before itemNode
  if (nextItem) {
    itemNode.parentNode.removeChild(nextItem);
    itemNode.parentNode.insertBefore(nextItem, itemNode);
  }
  return;
}
