const mongoose = require("mongoose")

const schema = mongoose.Schema({
	title: String,
	content: String, //ggf. nutzerreferenz
})

module.exports = mongoose.model("Post", schema)