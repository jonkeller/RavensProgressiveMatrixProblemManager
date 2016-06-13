import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('figures');
  this.route('problem-designer');
  this.route('problem-designer-3x3');
});

export default Router;
