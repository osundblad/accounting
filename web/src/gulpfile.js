var source = require('vinyl-source-stream');
var gulp = require('gulp');
var gutil = require('gulp-util');
var watchify = require('watchify');
var browserify = require('browserify');
var uglify = require('gulp-uglify');
var streamify = require('gulp-streamify');

var srcDir = './main/resources/static/';
var sources = [srcDir + 'main.js'];
var destination = '../target/compiled/';
var onError = function (error) {
    gutil.log(gutil.colors.red(error.message));
};
var standalone = 'ErisAccounting';

gulp.task('prod', function () {
    return browserify(srcDir + 'main.js', {
        standalone: standalone
    }).bundle()
        .on('error', onError)
        .pipe(source('eris.accounting.min.js'))
        .pipe(streamify(uglify()))
        .pipe(gulp.dest(destination));
});

gulp.task('dev', function () {
    return browserify(srcDir + 'main.js', {
        standalone: standalone
    }).bundle()
        .on('error', onError)
        .pipe(source('eris.accounting.js'))
        .pipe(gulp.dest(destination));
});

gulp.task('watch', function () {
    var opts = watchify.args;
    opts.debug = true;
    opts.standalone = standalone;

    var bundleStream = watchify(browserify(sources, opts))
        .on('update', rebundle)
        .on('log', gutil.log);

    function rebundle() {
        return bundleStream.bundle()
            .on('error', onError)
            .pipe(source('eris.accounting.js'))
            .pipe(gulp.dest(destination));
    }

    return rebundle();
});

gulp.task('default', ['watch']);
