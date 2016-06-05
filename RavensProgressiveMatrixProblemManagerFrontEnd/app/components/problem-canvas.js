import Ember from 'ember';

export default Ember.Component.extend({
    actions: {
        generateCommand() {
            var command = "java ravensproject.test.ProblemGenerator 2x2 ";
            command += document.getElementById('problem-name').value + " ";
            var ids = [ 'A', 'B', 'C', '1', '2', '3', '4', '5', '6' ];
            for (var i=0; i<ids.length; ++i) {
                var fls = document.getElementById('figure-landing-spot-' + ids[i]);
                var figureId = fls.getElementsByTagName('IMG')[0].id;
                figureId = figureId.substring(14);
                command += figureId + " ";
            }
            command += document.getElementById('problem-answer').value;
            document.getElementById('command').innerHTML = command;
        },

        clearAll() {
            var ids = [ 'A', 'B', 'C', '1', '2', '3', '4', '5', '6' ];
            for (var i=0; i<ids.length; ++i) {
                var fls = document.getElementById('figure-landing-spot-' + ids[i]).childNodes[0];
                while (fls.firstChild) {
                    fls.removeChild(fls.firstChild);
                }
            }
        }
    }
});
