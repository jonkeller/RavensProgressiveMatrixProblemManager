var express = require('express');
var router = express.Router();
var fs = require('fs');
var _ = require('lodash');

router.all('/*', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
});

router.get('/api/v1/figures', function(req, res) {
    var path = "../../Project-Code-Java/Problems/AllGivenFigures";
    console.log('Query Shape: ' + req.query.shape);
    fs.readdir(path, function(err, files) {
        if (err) {
            res.send('Error: ' + err);
            return;
        }
        var id = 0;
        files = _.filter(files, function(f) { return f.startsWith('Figure') && f.endsWith('.png'); });
        data = files.map(function(f) {
                    var figureName = f.substring(0, f.length-4);
                    var visualData = fs.readFileSync(path + '/' + f);
                    var verbalData = "";
                    var verbalFilename = path + '/' + figureName + ".txt";
                    try {
                        var stats = fs.statSync(verbalFilename);
                        if (stats.isFile()) {
                            verbalData = fs.readFileSync(verbalFilename, {encoding:'utf-8'});
                        }
                    } catch (e) {
                        // verbal file doesn't exist.
                    }
                    id = id + 1;
                    return {
                        type: 'figure',
                        id: id,
                        attributes: {
                            name: figureName,
                            visual: visualData.toString('base64'),
                            verbal: toJSON(verbalData)
                        }
                    };
                });
        // TODO: Make these be ANDed at the object level.
        console.dir('Before: ' + req.query);
        req.query = _.pickBy(req.query, function(q) { return !_.isEmpty(q); });
        console.dir('After: ' + req.query);
        if (req.query.shape || req.query.fill || req.query.size) {
            data = _.filter(data, function(d) {
                if (!d.attributes.verbal) {
                    return false;
                }
                return _.some(d.attributes.verbal.objects, req.query);
            });
        }
        res.send({ data: data });
    });
});

function toJSON(data) {
    if (!data) {
        return '';
    }
    var figure = { objects: [] };
    //console.log('data: ' + data);
    var lines = data.split('\n');
    //console.log('lines: ' + lines);
    for (var i=0; i<lines.length; ++i) {
        var line = lines[i];
        if (!line) {
            continue;
        }
        if (!line.startsWith('\t')) {
            // Start of figure
            figure.name = line;
        } else if (!line.startsWith('\t\t')) {
            // Start of object
            //console.log('Object: ' + line);
            //console.log('Object #: ' + figure.objects.length);
            figure.objects.push({ name: line.trim() });
        } else {
            // Attribute
            //console.log('Attr: ' + line);
            entry = line.trim().split(':');
            key = entry[0];
            val = entry[1];
            //console.log('key: ' + key);
            //console.log('val: ' + val);
            figure.objects[figure.objects.length-1][key]=val;
        }
    }
    //console.dir(figure);
    return figure;
}
module.exports = router;
