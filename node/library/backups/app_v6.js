var express = require('express');
var chalk = require('chalk')
var debug = require('debug')('app');
var morgan = require('morgan');
var path = require('path');
const sql = require('mssql');

var app = express();
const port = process.env.PORT || 3000;
//const bookRouter = express.Router();

const config = {
    user: 'library',
    password: 'PSnodejsdemo1',
    server: 'psnodejs.database.windows.net', // You can use 'localhost\\instance' to connect to named instance
    database: 'psnodejs',

    options: {
        encrypt: true // Use this if you are on Windows Azure
    }
};

//sql.Connect(config).catch(err => {debug(err)});
sql.connect(config).catch(err => debug(err) );

//app.use(morgan('combined'));
app.use(morgan('tiny'));

app.use((req, res, next) => {
    debug('My middleware');
    next();
})

app.use(express.static(path.join(__dirname, '/public/')));
app.use('/css', express.static(path.join(__dirname, '/node_modules/bootstrap/dist/css')));
app.use('/js', express.static(path.join(__dirname, '/node_modules/bootstrap/dist/js')));
app.use('/js', express.static(path.join(__dirname, '/node_modules/jquery/dist')));

app.set('views', './src/views');
app.set('view engine', 'ejs')

const nav = [
    { link: '/books', title: 'Books' },
    { link: '/authors', title: 'Authors' }
];

const bookRouter = require('./src/routes/bookRoutes')(nav);

app.use('/books', bookRouter);

app.get('/', function (req, res) {
    //res.send('Hello from my library app!!!');
    //res.sendFile(path.join(__dirname, '/views/index.html'));
    //res.render('index')
    //res.render('index', {title: 'MyLibrary'})
    res.render('index', {
        //list: ['a', 'b'],
        nav: [{ link: '/books', title: 'Books' },
        { link: '/authors', title: 'Authors' }],
        title: "Library"
    })
})

app.listen(port, function () {
    //console.log('listening on port 3000');
    //console.log('listening on port ' + chalk.green('3000'));
    //using string template
    //console.log(`listening on port ${chalk.green('3000')}`);
    debug(`listening on port ${chalk.green(port)}`);
})