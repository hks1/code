//test.js
const x = 1
const y = 2
const z = 3
console.count(
  'The value of x is ' + x + 
  ' and has been checked .. how many times?'
)
console.count(
  'The value of x is ' + x + 
  ' and has been checked .. how many times?'
)
console.count(
  'The value of y is ' + y + 
  ' and has been checked .. how many times?'
)
//console.count()
// const oranges = ['orange', 'orange', 'banana', 'mango', 'orange']
// const apples = ['just one apple']
// oranges.forEach(fruit => {
//     console.count(fruit)
// })
// apples.forEach(fruit => {
//     console.count(fruit)
// })

const function2 = () => console.trace()
const function1 = () => function2()
function1()
    
// Calculate the time spent
const doSomething = () => console.log('test')
const measureDoingSomething = () => {
    console.time('doSomething()')
    //do something, and measure the time it takes
    doSomething()
    console.timeEnd('doSomething()')
}
measureDoingSomething()

//color the output
console.log('\x1b[33m%s\x1b[0m', 'hi!')

//color using chalk library
const chalk = require('chalk')
console.log(chalk.yellow('hi! - with chalk'))

// Create a progress bar
const ProgressBar = require('progress')

const bar = new ProgressBar(':bar', { total: 10 })
const timer = setInterval(() => {
    bar.tick()
    if (bar.complete) {
        clearInterval(timer)
    }
}, 10)

//Accept input from the command line
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
})

readline.question(`Como se llama usted?`, name => {
    console.log(`Hi ${name}!`)
    readline.close()
})

// input using inquirer package (more complete and abstract solution)
// - 
const inquirer = require('inquirer')

var questions = [
    {
        type: 'input',
        name: 'name',
        message: "What's your name?"
    }
]

inquirer.prompt(questions).then(answers => {
    console.log(`Hii ${answers['name']}! `)
})