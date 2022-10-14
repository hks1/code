const express = require('express')
const app = express()

app.get("/", (req, res) => {
    res.send('Hello from Docker!!!')
})

app.listen(3000, () => {
    console.log('Server listening at port 3000')
})

// docker build -t hello-docker .

// docker run -it -p 3000:3000 hello-docker