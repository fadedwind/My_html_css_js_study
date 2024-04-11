// EventConvert.js
  
// Attempt to determine whether or not event conversion is needed.

function needEventConversion(args) {
  return !((args.length == 1) && 
           window.Event &&
           (args[0] instanceof window.Event));
}

// Convert from IE6 event object to DOM event object.

function eventConvert(ieEvent, currentTarget) {

  var event = new Object();
  try {

    // Do a (poor) simulation of IE dblclick
    // using DOM2 click event and detail property.
    // Let other event types pass through unchanged.
    event.detail = 1;
    if (ieEvent.type == "dblclick") {
      event.type = "click";
      event.detail = 2;
    } else {
      event.type = ieEvent.type;
    }
    // No equivalent to timeStamp is defined in IE event object
    event.target = ieEvent.srcElement;
    event.currentTarget = currentTarget;
    // stopPropagation only cancels bubbling, not event flow to capturing
    // or target listeners.
    event.stopPropagation = function () {ieEvent.cancelBubble = true;};  
    event.preventDefault = function () {ieEvent.returnValue = false;};
    event.screenX = ieEvent.screenX;
    event.screenY = ieEvent.screenY;
    event.clientX = ieEvent.clientX;
    event.clientY = ieEvent.clientY;
    event.altKey = ieEvent.altKey;
    event.ctrlKey = ieEvent.ctrlKey;
    // No meta key defined in IE event object
    event.shiftKey = ieEvent.shiftKey;
    switch (ieEvent.button) {
      case 1: event.button = 0; break;
      case 4: event.button = 1; break;
      case 2: event.button = 2; break;
    }
    switch (ieEvent.type) {
      case "mouseover": event.relatedTarget = ieEvent.fromElement; break;
      case "mouseout": event.relatedTarget = ieEvent.toElement; break;
    }
  } catch (e) {
    // If we fail, just return whatever we have and hope for
    // the best...
  }
  return event;
}
