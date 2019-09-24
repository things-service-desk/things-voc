const { series, parallel, src, dest, watch } = require('gulp')
const $ = require('gulp-load-plugins')()
const del = require('del')
const path = require('path')
const ensureFilesTask = require('./tasks/ensure-files.js')
const merge = require('merge-stream')
const glob = require('glob-all')
const crypto = require('crypto')
const fs = require('fs')
const browserSync = require('browser-sync')
const devServer = browserSync.create()
const historyApiFallback = require('connect-history-api-fallback')
const PACKAGE_JSON = require('./package.json')

const AUTOPREFIXER_BROWSERS = [
  'ie >= 10',
  'ie_mob >= 10',
  'ff >= 30',
  'chrome >= 34',
  'safari >= 7',
  'opera >= 23',
  'ios >= 7',
  'android >= 4.4',
  'bb >= 10'
]

var DIST = 'dist'
const TEMPDIR = '.tmp'

function dist(subpath) {
  return !subpath ? DIST : path.join(DIST, subpath)
}

function styleTask(stylesPath, sources) {
  return src(sources.map(source => path.join('app', stylesPath, source)))
    .pipe(
      $.changed(stylesPath, {
        extension: '.css'
      })
    )
    .pipe($.autoprefixer(AUTOPREFIXER_BROWSERS))
    .pipe(dest(`${TEMPDIR}/${stylesPath}`))
    .pipe($.cleanCss())
    .pipe(dest(dist(stylesPath)))
    .pipe(
      $.size({
        showFiles: true
      })
    )
}

function imageOptimizeTask(source, destination) {
  return src(source)
    .pipe(
      $.imagemin({
        progressive: true,
        interlaced: true
      })
    )
    .pipe(dest(destination))
    .pipe(
      $.size({
        showFiles: true
      })
    )
}

// Optimize images
function images() {
  return imageOptimizeTask('app/images/**/*', dist('images'))
}

// Copy web fonts to dist
function fonts() {
  return src(['app/fonts/**'])
    .pipe(dest(dist('fonts')))
    .pipe(
      $.size({
        showFiles: true
      })
    )
}

// Browser reload
function reload(cb) {
  devServer.reload()
  cb()
}

// Clean output directory
function clean(cb) {
  return del([TEMPDIR, dist()], {
    force: true
  })
}

function ensureFiles(cb) {
  var requiredFiles = ['.bowerrc']

  ensureFilesTask(
    requiredFiles.map(function(p) {
      return path.join(__dirname, p)
    }),
    cb
  )
}

function copy(cb) {
  var app = src(
    [
      'app/*',
      '!app/test',
      '!app/elements',
      '!app/bower_components',
      '!app/cache-config.json',
      '!app/licenses',
      '!**/.DS_Store'
    ],
    {
      dot: true
    }
  ).pipe(dest(dist()))

  // Copy over only the specifically lib we need
  // These are things which cannot be vulcanized
  var licenses = src(['app/licenses/**/*']).pipe(dest(dist('licenses')))

  return merge(app, licenses).pipe(
    $.size({
      showFiles: true
    })
  )
}

// Compile and automatically prefix stylesheets
function styles(cb) {
  styleTask('styles', ['**/*.css'])
  cb()
}

// Scan your HTML for assets & optimize them
function buildApp(cb) {
  var app = src(['app/**/*.html', '!app/{elements,test,bower_components}/**/*.html'])
    .pipe($.useref())
    .pipe(
      $.if(
        '*.js',
        $.uglify({
          output: {
            comments: 'all'
          }
        })
      )
    )
    .pipe($.if('*.css', $.cleanCss()))
    .pipe(
      $.if(
        '*.html',
        $.htmlmin({
          minifyJS: true,
          minifyCSS: true,
          removeComments: true
        })
      )
    )
    .pipe(dest(dist()))

  var libs = src(['app/bower_components/things-libraries/*.html'])
    .pipe($.useref())
    .pipe(
      $.if(
        '*.js',
        $.uglify({
          output: {
            comments: 'all'
          }
        })
      )
    )
    .pipe($.if('*.css', $.cleanCss()))
    .pipe(
      $.if(
        '*.html',
        $.htmlmin({
          minifyJS: true,
          minifyCSS: true,
          removeComments: true
        })
      )
    )
    .pipe(dest(dist('bower_components/things-libraries')))

  return merge(app, libs).pipe(
    $.size({
      showFiles: true
    })
  )
}

function copyBower() {
  var elements = src(['app/elements/**'], {
    dot: true
  }).pipe(dest(dist('elements')))

  var style = src(['app/styles/**'], {
    dot: true
  }).pipe(dest(dist('styles')))

  var bower = src(['app/bower_components/**'], {
    dot: true
  }).pipe(dest(dist('bower_components')))

  return merge(elements, bower, style).pipe(
    $.size({
      showFiles: true
    })
  )
}

function buildBower() {
  var bower = src([
    dist('bower_components/**/iron-*.html'),
    dist('bower_components/**/things-*.html'),
    dist('bower_components/**/paper-*.html'),
    dist('bower_components/**/app-*.html'),
    dist('bower_components/**/gold-*.html'),
    dist('bower_components/**/platinum-*.html'),
    dist('bower_components/**/neon-*.html')
  ])
    .pipe(
      $.if(
        '*.html',
        $.htmlmin({
          minifyJS: true,
          minifyCSS: true,
          removeComments: true
        })
      )
    )
    .pipe(dest(dist('bower_components')))

  var elements = src([dist('elements/**/*.html')])
    .pipe(
      $.if(
        '*.html',
        $.htmlmin({
          minifyJS: true,
          minifyCSS: true,
          removeComments: true
        })
      )
    )
    .pipe(dest(dist('elements')))

  var styles = src([dist('styles/**/*.html')])
    .pipe(
      $.if(
        '*.html',
        $.htmlmin({
          minifyJS: true,
          minifyCSS: true,
          removeComments: true
        })
      )
    )
    .pipe(dest(dist('styles')))

  return merge(elements, bower, styles).pipe(
    $.size({
      showFiles: true
    })
  )
}

// Vulcanize granular configuration
function vulcanize() {
  return src(dist('elements/elements.html'))
    .pipe(
      $.vulcanize({
        excludes: [
          // dist('bower_components/things-libraries/things-libraries.html'),
          dist('bower_components/things-libraries/things-enc-lib.html'),
          dist('bower_components/things-libraries/things-c3-lib.html'),
          dist('bower_components/things-libraries/things-d3-lib.html'),
          dist('bower_components/things-libraries/things-jquery-lib.html'),
          dist('bower_components/things-libraries/things-jsxlsx-lib.html'),
          dist('bower_components/things-libraries/things-jszip-lib.html'),
          dist('bower_components/things-libraries/things-momentjs-lib.html'),
          dist('bower_components/things-libraries/things-numeraljs-lib.html'),
          dist('bower_components/things-libraries/things-sockjs-lib.html'),
          dist('bower_components/things-libraries/things-sweetalert-lib.html'),
          dist('bower_components/things-libraries/things-fullcalendar-lib.html'),
          dist('bower_components/things-libraries/things-ace-lib.html')
        ],
        stripComments: true,
        inlineCss: true,
        inlineScripts: true
      })
    )
    .on('error', function(e) {
      console.log('vulc', e)
    })
    .pipe(
      $.htmlmin({
        minifyJS: true,
        minifyCSS: true,
        removeComments: true
      })
    )
    .on('error', function(e) {
      console.log('minify', e)
    })
    .pipe(dest(dist('elements')))
    .on('error', function(e) {
      console.log('save', e)
    })
    .pipe(
      $.size({
        showFiles: true
      })
    )
    .on('error', function(e) {
      console.log('final', e)
    })
}

// del elements
function deleteElements(cb) {
  // del(
  //   [
  //     dist('bower_components/{things-*,siron-*,paper-*,gold-*,neon-*,app-*,pouchdb*,prism*}/**'),
  //     '!' + dist('bower_components/things-libraries/**'),
  //     '!' + dist('bower_components/things-grid-behavior/**'),
  //     '!' + dist('bower_components/things-license-checker-min/**'),
  //     dist('bower_components/{threejs,TinyColor,test-fixture,bwip-js,chart.js,intl-messageformat,async}/**'),
  //     dist('bower_components/**/{locale*,dev*,example*,demo*,*test*,doc*,st,html,src}/**')
  //   ],
  //   {
  //     force: true
  //   }
  // )

  del([dist('elements/{things-*}/**')], {
    force: true
  })

  cb()
}

// Generate config data for the <sw-precache-cache> element.
// This include a list of files that should be precached, as well as a (hopefully unique) cache
// id that ensure that multiple PSK projects don't share the same Cache Storage.
// This task does not run by default, but if you are interested in using service worker caching
// in your project, please enable it within the 'default' task.
// See https://github.com/PolymerElements/polymer-starter-kit#enable-service-worker-support
// for more context.
function cacheConfig(cb) {
  var dir = dist()
  var config = {
    cacheId: PACKAGE_JSON.name || path.basename(__dirname),
    disabled: false
  }

  glob(
    [
      'index.html',
      'sw-import.js',
      'favicon.ico',
      './',
      'bower_components/webcomponentsjs/webcomponents-lite.min.js',
      'bower_components/things-libraries/**/*.html',
      '{scripts,images,styles,licenses,bower_components/things-grid-behavior,bower_components/things-license-checker-min}/**/*.*'
    ],
    {
      cwd: dir
    },
    function(error, files) {
      if (error) {
        cb(error)
      } else {
        config.precache = files

        var md5 = crypto.createHash('md5')
        md5.update(JSON.stringify(config.precache))
        config.precacheFingerprint = md5.digest('hex')

        var configPath = path.join(dir, 'cache-config.json')
        fs.writeFile(configPath, JSON.stringify(config), cb)
      }
    }
  )
}

function runDevServer(opt = {}) {
  var {
    port = 5100,
    server = {
      baseDir: [TEMPDIR, 'app'],
      middleware: [historyApiFallback()]
    }
  } = opt

  devServer.init({
    port,
    // notify: true,
    logPrefix: 'Things Suite Base',
    host: 'localhost',
    single: true,
    // open:'external',
    snippetOptions: {
      rule: {
        match: '<span id="browser-sync-binding"></span>',
        fn: function(snippet) {
          return snippet
        }
      }
    },
    browser: ['google chrome'],
    // Run as an https by uncommenting 'https: true'
    // Note: this uses an unsigned certificate which on first access will present a certificate warning in the browser.
    // https: true,
    server
  })
}

function watchFiles(cb) {
  var watchers = [
    watch(['app/**/*.html', '!app/bower_components/**/*.html'], reload),
    watch(['app/styles/**/*.css'], parallel(styles, reload)),
    watch(['app/scripts/**/*.js'], reload),
    watch(['app/images/**/*'], reload)
  ]

  watchers.forEach(w => {
    w.on('change', (path, stats) => {
      console.info(`File ${path} was changed`)
    })
    w.on('add', (path, stats) => {
      console.info(`File ${path} was added`)
    })
    w.on('unlink', (path, stats) => {
      console.info(`File ${path} was removed`)
    })
  })

  cb()
}

const defaultTask = series(
  clean,
  series(
    parallel(ensureFiles, copy, styles),
    series(images, fonts, buildApp),
    copyBower,
    buildBower,
    vulcanize,
    deleteElements,
    cacheConfig
  )
)

const serveTask = series(
  styles,
  function runServer(cb) {
    runDevServer()
    cb()
  },
  watchFiles
)

const serveDistTask = series(defaultTask, function runServer(cb) {
  runDevServer({
    port: 5102,
    server: dist()
  })
  cb()
})

const deployTask = series(function changeTargetDist(cb) {
  DIST = '../things-suite-built/base'
  cb()
}, defaultTask)

// Build production files, the default task
exports.default = defaultTask

exports.clean = clean

// Watch files for changes & reload
exports.serve = serveTask

// Build and serve the output from the dist build
exports['serve:dist'] = serveDistTask

// Build production files and move to build
exports['deploy'] = deployTask

exports['images'] = images
