const express = require('express')
const mongoose = require('mongoose')

const router = express.Router()

const CourseModel = mongoose.model("Course")

router.get("/add", (req, res) => {
    res.render("add-course")
})

router.post("/add", (req, res) => {
    console.log(req)
    var course = new CourseModel()
    course.courseName = req.body.courseName
    course.courseId = req.body.courseId
    course.courseDuration = req.body.courseDuration
    course.courseId = Math.ceil(Math.random() * 100000)
    course.save((err, doc) => {
        if(!err){
            console.log(doc)
            // To display refreshed list of courses in the browser
            // uncomment below res.redirect, and
            // comment res.json() at the end of this if block

            //res.redirect("/course/list")

            // to implement as a REST API
            // comment above res.redirect  statement, and
            // send response as json, res.json() below.
            res.json({message : "successfull", status : "OK"})  
            
        } else {
            console.log(err)
            res.send("Error adding record.")
        }
    })
    //res.render("add-course")
})


router.get("/list", (req, res) => {
    // Setting
    /*
    var course = new CourseModel()
    course.courseName = "NodeJS"
    course.courseId = "102"
    course.save()
    */
    // Geeting
    CourseModel.find((err, docs) => {
        if (!err){
            console.log(docs)
            docs.forEach(element => {
                console.log(element.courseName)
            });
            //res.send('Course list!!!')
            res.render("list", {data : docs})
            //res.send(docs)
        } else {
            console.log(err)
        }
    })
    //res.send('courses controller')
})

module.exports = router