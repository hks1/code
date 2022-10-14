const connection = require('./model')
const express = require('express')
const application = express()
const path = require('path')
const expressHandlebars = require('express-handlebars')
const bodyParser = require('body-parser')

// Installed new package 
// To resolve handlebars issue (access denied), 
// logs at the bottom in this file
const Handlebars = require('handlebars')
const {allowInsecurePrototypeAccess} = require('@handlebars/allow-prototype-access')

const CourseController = require('./controllers/courses')

application.use(bodyParser.urlencoded({
    extended : true
}))

application.set('views',path.join(__dirname, "/views/"))

application.engine("hbs", expressHandlebars({
    extname : "hbs", 
    defaultLayout : "mainlayout",
    layoutsDir : __dirname + "/views/layouts",
    handlebars : allowInsecurePrototypeAccess(Handlebars)  // Added to resolve handlebars error - Access Denied
}))

application.set('view engine', 'hbs')

application.get("/", (req, res) => {
    //res.send('<h1>Some HTMLL!!!</h1>')
    res.render('index', {})
})

application.use("/course", CourseController)

application.listen("3000", () =>{
    console.log("Server started...")
})

// Error before using @handlebars/allow-prototype-access
/*
Handlebars: Access has been denied to resolve the property "courseId" because it is not an "own property" of its parent.
You can add a runtime option to disable the check or this warning:
See https://handlebarsjs.com/api-reference/runtime-options.html#options-to-control-prototype-access for details
Handlebars: Access has been denied to resolve the property "courseName" because it is not an "own property" of its parent.
You can add a runtime option to disable the check or this warning:
See https://handlebarsjs.com/api-reference/runtime-options.html#options-to-control-prototype-access for details
*/