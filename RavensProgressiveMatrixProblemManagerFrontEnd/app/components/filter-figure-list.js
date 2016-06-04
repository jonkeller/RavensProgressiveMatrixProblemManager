import Ember from 'ember';

export default Ember.Component.extend({
  filter: null,
  filteredList: null,
  actions: {
    /*
    autoComplete() {
      // Bubble up
      this.get('autoComplete')(this.get('filterShape'), this.get('filterFill'));
    },
    */
    search() {
      // Bubble up
      this.get('search')({ shape: this.get('filterShape'), fill: this.get('filterFill'), size: this.get('filterSize') });
    },
    choose(shape) {
        debugger;
      this.set('filter', shape);
    }
  }
});