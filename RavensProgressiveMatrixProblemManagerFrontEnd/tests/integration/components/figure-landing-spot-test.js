import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('figure-landing-spot', 'Integration | Component | figure landing spot', {
  integration: true
});

test('it renders', function(assert) {
  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{figure-landing-spot}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#figure-landing-spot}}
      template block text
    {{/figure-landing-spot}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
