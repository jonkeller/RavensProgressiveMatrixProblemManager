# RavensProgressiveMatrixProblemManager

This is a project for generating Ravens Progressive Matrix problems.
It's not complete yet.
It exists in two parts, a back-end and a front-end.
It depends on code from Georgia Tech's CS7637 Knowledge-Based AI. So if you're not in that class, this will not be very useful to you.

## Prerequisites
Download and compile the FigureCollector and ProblemGenerator Java classes. Take the "test" directory from put-the-contents-of-this-in-ravensproject-directory/test and copy it over so that it is at Project-Code-Java/ravensproject/test. (The Project-Code-Java is obtained from the github link provided by the course instructors - I cannot provide this code to non-students.)

The directory structure should look like:
* Wherever you put the KBAI project
  * Project-Code-Java <- From KBAI
    * Problems <- From KBAI
      * MyGeneratedProblems <- Create this directory after running the FigureCollector
    * ravensproject <- From KBAI
      * test <- Create this directory and put FigureCollector.java, ProblemGenerator.java here
  * RavensProgressiveMatrixProblemManager <-- Git clone this repo to here

Run the FigureCollector.

Then go back and create the "MyGeneratedProblems" directory once that is done.

Also, add "MyGeneratedProblems" to Project-Code-Java/Problems/ProblemSetList.txt. Again, do this after running the FigureCollector. Otherwise it will try to collect the figures from the problems which you haven't generated yet, because you don't have the figures yet ;)

## Caveats
* Currently it only does 2x2 matrices. The underlying ProblemGenerator utility will do 3x3 matrices, I just didn't make a UI for 3x3 matrices yet. UPDATE: It will do 3x3 matrices, but the layout needs some work so that everything will fit on the same page nicely. This has not been tested much yet.
* It doesn't create the single big .png file with all 9 figures in it. Only the individual figure .png files, ProblemData.txt, and ProblemAnswer.txt.

## Back-End
This is a nodeJS / Express app. It implements a REST API.

Example:

http://localhost:3000/api/v1/figures?shape=square -> returns a JSON blob representing all figures whose verbal descriptions contain at least one square.

http://localhost:3000/api/v1/figures?shape=square&fill=yes&size=huge -> returns a JSON blob representing all figures whose verbal descriptions contain at least one huge filled square.

Currently shape, size, and fill are the only 3 attributes it can search on. Partial strings like "squa" don't work yet, and it's also currently case-sensitive.

To install and start it:
* Install NodeJS and npm
* cd RavensProgressiveMatrixProblemManagerBackEnd
* Ensure that the set of all figures is in ../../Project-Code-Java/Problems/AllGivenFigures
* npm install
* npm start
* Test at the URLs given above.

## Front-End
This is an Ember application which uses the REST API.

To install and start it:
* Install ember, if you haven't already:
  * npm install -g ember-cli
  * npm install -g bower
  * bower install
* cd RavensProgressiveMatrixProblemManagerFrontEnd
* npm install
* ember server
* Go to http://localhost:4200/
* Make a problem
  * Give it a name
  * Drag figures around as desired
  * Be sure to select the correct answer!
* Click "Create" button
* Copy Java command
* Paste in command prompt, in Project-Code-Java directory
* Manually add the problem name to Project-Code-Java/Problems/MyGeneratedProblems/ProblemList.txt
* As mentioned above, you also have to add "MyGeneratedProblems" to Project-Code-Java/Problems/ProblemSetList.txt, but you only have to do this once.

![screenshot](https://raw.githubusercontent.com/jonkeller/RavensProgressiveMatrixProblemManager/master/img/Screen%20Shot%202016-06-05%20at%209.22.54%20AM.png?token=ACV2Bx1aVPlPgrXLKf1TsNs4VVjbDZXRks5XXXLXwA%3D%3D)

![screenshot](https://raw.githubusercontent.com/jonkeller/RavensProgressiveMatrixProblemManager/master/img/Screen%20Shot%202016-06-05%20at%209.24.24%20AM.png?token=ACV2BzC2T7pEpLS7dMQQcvtzLsZW2TyGks5XXXMIwA%3D%3D)
