import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('problem-canvas-3x3', 'Integration | Component | problem canvas 3x3', {
  integration: true
});

test('it renders', function(assert) {
  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{problem-canvas-3x3}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#problem-canvas-3x3}}
      template block text
    {{/problem-canvas-3x3}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
