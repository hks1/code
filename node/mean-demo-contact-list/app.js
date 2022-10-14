// importing modules

const bodyParser = require('body-parser')
const express = require('express')
const mongoose = require('mongoose')
const cors = require('cors')

const path = require('path')

// create app
var app = express()

// import routes
const route = require('./routes/route')

// connect to mongodb
mongoose.connect('mongodb://localhost:27017/contactlist')
// on connection
mongoose.connection.on('connected', () => {
    console.log('connected to database.')
})
//
mongoose.connection.on('error', (err) => {
    if(err){
        console.log(err)
    }
})

// port
const port = 3000

// adding middleware
app.use(cors())
app.use(bodyParser.json())

// define static files path
app.use(express.static(path.join(__dirname, '/public/')))

// specify route
app.use("/api", route)

app.get("/", (req, res) => {
    res.send('test success')
})

app.listen(3000, () => {
    console.log(`Server listening at port ${port}`)
})

    // (node:12920) DeprecationWarning: current URL string parser is deprecated, and will be removed in a future version. 
    // To use the new parser, pass option { useNewUrlParser: true } to MongoClient.connect.
    
    // (node: 12920) DeprecationWarning: current Server Discovery and Monitoring engine is deprecated, 
    // and will be removed in a future version.
    // To use the new Server Discover and Monitoring engine, pass option { useUnifiedTopology: true } to the MongoClient constructor.