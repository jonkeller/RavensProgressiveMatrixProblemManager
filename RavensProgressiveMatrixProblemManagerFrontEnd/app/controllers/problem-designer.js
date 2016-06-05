import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    autoComplete(param) {
      // This DOES get called
      if (param !== '') {
        this.get('store').query('figure', param).then((result) => this.set('filteredList', result));
      } else {
        this.set('filteredList', null);
      }
    },
    search(param) {
      // This DOES get called
      if (param !== '') {
        this.store.query('figure', param).then((result) => this.set('model', result));
      } else {
        this.store.findAll('figure').then((result) => this.set('model', result));
      }
    },
    filterFigureList(param) {
        // This does not seem to ever get called
        debugger;
    }
  }
});
