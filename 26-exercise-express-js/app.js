const waitPort = require('wait-port')
const express = require("express")
const mongoose = require("mongoose")
const debug = require('debug')('example:app')

const mongoDbUrlString = process.env.MONGO_DB_URL || "mongodb://localhost:27017/example"
const mongoDbUrl = new URL(mongoDbUrlString);
const port = process.env.PORT || 3000;

const app = express()

debug(`Wait for db: ${mongoDbUrlString}`);
waitPort({
    host: mongoDbUrl.hostname,
    waitForDns: true,
    port: parseInt(mongoDbUrl.port)
})
    .then(_ => {
        debug(`Connect to db: ${mongoDbUrlString}`);
        mongoose
            .connect(mongoDbUrlString, { useNewUrlParser: true })
            .then(() => {
                debug(`Database connected`);

                app.use(express.json())

                app.use(require('./routes/post'));

                app.get('/healthz', (req, res) => {
                    debug(`Health request received`)
                    res.status(204).send();
                });

                app.listen(port, () => {
                    debug(`Server has started on port ${port}`)
                    app.emit("started");
                    app.started = true;
                })
            })
    })
    .catch((err) => {
        debug(`An unknown error occured while waiting for the port: ${err}`);
    });

module.exports = app;