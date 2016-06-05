# RavensProgressiveMatrixProblemManager

This is a project for generating Ravens Progressive Matrix problems.
It's not complete yet.
It exists in two parts, a back-end and a front-end.

## Back-End
This is a nodeJS / Express app. It implements a REST API.

Example:

http://localhost:3000/api/v1/figures?shape=square -> returns a JSON blob representing all figures whose verbal descriptions contain at least one square.
http://localhost:3000/api/v1/figures?shape=square&fill=yes&size=huge -> returns a JSON blob representing all figures whose verbal descriptions contain at least one huge filled square.
Currently shape, size, and fill are the only 3 attributes it can search on. Partial strings like "squa" don't work yet, and it's also currently case-sensitive.

To start it:
* cd RavensProgressiveMatrixProblemManagerBackEnd
* Ensure that the set of all figures is in ../../Project-Code-Java/Problems/AllGivenFigures
** This will likely change, and a tool to generate that set may be provided.
* npm install
* npm start
* Test at the URLs given above.

## Front-End
This is an Ember application which uses the REST API.
* cd RavensProgressiveMatrixProblemManagerFrontEnd
* npm install
* ember server
* Go to http://localhost:4200/

