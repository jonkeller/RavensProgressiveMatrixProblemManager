export default function() {

  // These comments are here to help you get started. Feel free to delete them.

  /*
    Config (with defaults).

    Note: these only affect routes defined *after* them!
  */

  // this.urlPrefix = '';    // make this `http://localhost:8080`, for example, if your API is on a different server
  // this.namespace = '';    // make this `api`, for example, if your API is namespaced
  // this.timing = 400;      // delay for each request, automatically set to 0 during testing

  /*
    Shorthand cheatsheet:

    this.get('/posts');
    this.post('/posts');
    this.get('/posts/:id');
    this.put('/posts/:id'); // or this.patch
    this.del('/posts/:id');

    http://www.ember-cli-mirage.com/docs/v0.2.0-beta.7/shorthands/
  */

  //this.urlPrefix = 'http://localhost:1234';
  //this.namespace = '/api';
  //this.passthrough();
  this.get('/figures');

/*
  this.get('/figures', function() {
    return {
      data: [{
        type: 'figures',
        id: 1,
        attributes: {
          name: 'Figure0001'
        }
      }, {
        type: 'figures',
        id: 2,
        attributes: {
          name: 'Figure0002'
        }
      }, {
        type: 'figures',
        id: 3,
        attributes: {
          name: 'Figure0003'
        }
      }]
    };
  });
  */
}