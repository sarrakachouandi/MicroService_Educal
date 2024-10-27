const mongoose = require("mongoose");

const clubSchema = new mongoose.Schema({
  nomClub: String,
  descriptionClub: String,
  imageClub: String,
});

const Club = mongoose.model("Club", clubSchema);

module.exports = Club;
