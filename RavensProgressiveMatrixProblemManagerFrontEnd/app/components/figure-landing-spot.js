import Ember from 'ember';

export default Ember.Component.extend({
  attributeBindings: ['draggable'],
  draggable: 'true',

/*  
  click(ev) {
      alert('click! ' + ev);
  },
  allowDrop(ev) {
      ev.preventDefault();
  },

  drag(ev) {
      //ev.dataTransfer.setData("text", ev.target.id);
      return false;
  },
  */

  dragOver() {
      return false;
  },

  drop(ev) {
      ev.preventDefault();
      var target = ev.target.tagName == "IMG" ? ev.target.parentElement.parentElement : ev.target;
      while (target.firstChild) {
          target.removeChild(target.firstChild);
      }

      var data = ev.dataTransfer.getData("text/html");
      ev.dataTransfer.clearData();
      // data contains a meta tag followed by an img tag
      var element = document.createElement("div");
      element.innerHTML = data;
      target.appendChild(element);
      //this.sendAction('action', id);
/*
      var data = ev.dataTransfer.getData("text");
      ev.target.appendChild(document.getElementById(data));
*/
  }
});
