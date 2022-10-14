const express = require('express')
const router = express.Router()
//for debugging
const fs = require('fs')
const path = require('path')

const Contacts = require('../models/contacts')

router.get('/contact', (req, res, next) => {
    Contacts.find(function(err, contacts) {
        res.json(contacts)
    })
})

router.post('/contact', (req, res, next) => {
    console.log(`''''''''''''adding contact'''''''\n${req}`)
    //console.log(req)
    
    let newContact = new Contacts({
        first_name: req.body.first_name,
        last_name: req.body.last_name,
        phone: req.body.phone
    })

    newContact.save((err, contact) => {
        if(err){
            res.json({msg: "failed to add contact"})
        } else {
            res.json({msg: "Contact added"})
        }
    })
})

router.delete('/contact/:id', (req, res, next) => {
    
    // fs.writeFile(path.join(__dirname, './temmpppp'), req, (err) => {
    //     if (err) {
    //         console.log(err)
    //     }
    // })
    Contacts.remove({_id: req.params.id}, function(err, result) {
        if(err){
            res.json(err)
        } else {
            res.json(result)
        }
    })
})

module.exports = router

// (node:12920) DeprecationWarning: collection.remove is deprecated. 
// Use deleteOne, deleteMany, or bulkWrite instead.