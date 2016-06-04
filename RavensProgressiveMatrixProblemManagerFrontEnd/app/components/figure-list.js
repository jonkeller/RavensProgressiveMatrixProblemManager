import Ember from 'ember';

export default Ember.Component.extend({
  filteredList: null,
  actions: {
    autoComplete(param) {
      // Bubble up
      this.get('controller').sendAction('autoComplete', param);
    },
    search(param) {
      // Bubble up
      this.get('controller').sendAction('search', param);
    }
  }
});
