import Ember from 'ember';

export default Ember.Component.extend({
  filter: null,
  filteredList: null,
  actions: {
    autoComplete() {
      // Bubble up
      this.get('autoComplete')(this.get('filter'));
    },
    search() {
      // Bubble up
      this.get('search')(this.get('filter'));
    },
    choose(shape) {
        debugger;
      this.set('filter', shape);
    }
  }
});