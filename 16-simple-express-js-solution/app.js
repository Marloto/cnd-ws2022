const fs = require("fs")
const express = require("express");
const debug = require('debug')('example:app')

let config = {};
if(fs.existsSync('./config/config.json')) {
    config = require('./config/config.json');
}

let killed = false;
let data = [];
const chars = [..."ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"];

const greetingArg = process.argv.length > 0 ? process.argv[2] : '';

const port = process.env.PORT || 3000;
const greeting = config.greeting || greetingArg || process.env.GREETING || 'Hello, World!';

const app = express()

app.use(express.json())

app.get('/hello', (req, res) => {
    debug(`Send msg ${greeting}`)
    res.send({'msg': greeting});
});

app.get('/kill', (req, res) => {
    debug(`Killed service, bye bye`)
    killed = true;
    res.status(204).send();
});

app.get('/fill/:amount', (req, res) => {
    const amount = parseInt(req.params.amount) * 1024 * 1024;
    debug(`Fill space with ${req.params.amount} MB, current is ${process.memoryUsage().rss / 1024 / 1024} MB`)
    
    // create random 1024char strings
    for(let i = 0; i < amount; i += 1024) {
        data.push([...Array(1024)].map(i=>chars[Math.random()*chars.length|0]).join(''));
    }
    
    debug(`Usage is now ${process.memoryUsage().rss / 1024 / 1024} MB`)
    res.status(204).send();
});

app.get('/memory', (req, res) => {
    res.status(200).send({memory: process.memoryUsage().rss / 1024 / 1024, data: data.length});
});

app.get('/healthz', (req, res) => {
    debug(`Health request received`)
    if(!killed) {
        res.status(204).send();
    }
});

app.listen(port, () => {
    debug(`Server has started on port ${port}`)
    app.emit("started");
    app.started = true;
})

module.exports = app;