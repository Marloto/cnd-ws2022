
const express = require("express")
const mongoose = require("mongoose")
const debug = require('debug')('example:app')

const mongoDbUrl = process.env.MONGO_DB_URL || "mongodb://localhost:27017/example"
const port = process.env.PORT || 3000;

const app = express()

debug(`Connect to db: ${mongoDbUrl}`);
mongoose
.connect(mongoDbUrl, { useNewUrlParser: true })
.then(() => {
        debug(`Database connected`);

        app.use(express.json())

        app.use(require('./routes/post'));

        app.listen(port, () => {
            debug(`Server has started on port ${port}`)
            app.emit("started");
            app.started = true;
        })
    })

module.exports = app;