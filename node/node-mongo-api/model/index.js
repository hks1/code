const mongoose = require('mongoose')

mongoose.connect('mongodb://127.0.0.1:27017/node-mongo-demo', {
    useUnifiedTopology: true, useNewUrlParser: true
}, (err) => {
    if (!err) {
        console.log('success')
    } else {
        console.log(`error connecting to database: ${err}`)
    }
})

const Course = require('./course.model')