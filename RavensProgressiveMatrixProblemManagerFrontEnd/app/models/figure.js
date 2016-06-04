import Model from 'ember-data/model';
import attr from 'ember-data/attr';

export default Model.extend({
  name: attr(),
  visual: attr(),
  verbal: attr(),
  verbalAsString: Ember.computed('verbal', function() {
    return JSON.stringify(this.get('verbal').objects);
  })
});