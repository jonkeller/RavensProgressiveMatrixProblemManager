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
                            verbal: verbalData
                        }
                    };
                });
        if (req.query.shape) {
            data = _.filter(data, function(d) {
                if (!d.attributes.verbal) {
                    return false;
                }
                return -1 != d.attributes.verbal.indexOf('shape:'+req.query.shape);
            });
        }
        res.send({ data: data });
    });
});

module.exports = router;
