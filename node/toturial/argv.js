//node argv.js 123
//node argv.js {name: Joe}
//node argv.js name=Mr.Node age=54
process.argv.forEach((val, index) => {
    console.log(`${index} : ${val}`)
})

//const args = process.argv.slice(2)
//console.log(args)

const args = require('minimist')(process.argv.slice(2))
console.log(args)
console.log(args['name'], args['age'])