//app.js
const express = require('express')

const app = new express()

app.get('/', (req, res) => {
    res.send('Hello!')
})

const server = app.listen(3000, () => console.log('server ready'))

process.on('SIGTERM', () => {
    server.close(() => console.log('process terminated.'))
})