import Ember from 'ember';

export default Ember.Component.extend({
  click(ev) {
    alert('click! ' + ev);
  },
/*  
  allowDrop(ev) {
      ev.preventDefault();
  },
  */

  drag(ev) {
      ev.dataTransfer.effectAllowed = "copy";
      ev.dataTransfer.dropEffect = "copy";
      ev.dataTransfer.setData("text/html", ev.target.id);
      return false;
  }

/*
  drop(ev) {
      let id = event.dataTransfer.getData('text/data');
      this.sendAction('action', id);
      ev.preventDefault();
      var data = ev.dataTransfer.getData("text");
      ev.target.appendChild(document.getElementById(data));
  }
*/
});
