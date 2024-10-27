const Club = require("../models/club");
const multer = require("multer");
const path = require("path");

// Configure Multer
const storage = multer.diskStorage({
  destination: "./upload-directory",
  filename: function (req, file, cb) {
    cb(
      null,
      file.fieldname + "-" + Date.now() + path.extname(file.originalname)
    );
  },
});

exports.getAllClubs = async (req, res) => {
  try {
    const { name } = req.query;
    let query = {};

    if (name) {
      // Case-insensitive search by name
      query = { nom: { $regex: new RegExp(name, "i") } };
    }

    const clubs = await Club.find(query);
    res.json(clubs);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
};

exports.createClub = async (req, res) => {
  try {
    req.body.imageClub = "specDefaultImg.png"
    const club = new Club(req.body);
    await club.save();
    res.status(201).json(club);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
};

exports.getClubById = async (req, res) => {
  try {
    const club = await Club.findById(req.params.id);
    if (!club) {
      return res.status(404).json({ error: "Club not found" });
    }
    res.json(club);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
};

exports.updateClub = async (req, res) => {
  try {
    const { imageClub, ...updatedFields } = req.body;
    const club = await Club.findByIdAndUpdate(req.body._id, updatedFields, {
      new: true,
    });
    if (!club) {
      return res.status(404).json({ error: "Club not found" });
    }
    res.json(club);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
};

exports.deleteClub = async (req, res) => {
  try {
    const club = await Club.findByIdAndDelete(req.params.id);
    if (!club) {
      return res.status(404).json({ error: "Club not found" });
    }
    res.json(club);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
};

// exports.uploadImage = async (req, res) => {
//   try {
//     // Access uploaded file via req.file
//     const imagePath = req.file ? req.file.path : null;

//     if (imagePath) {
//       // Extract club ID from request parameters
//       const clubId = req.params.id;
//       const fileName = path.basename(req.file.path);

//       // Update the club with the uploaded image path
//       const updatedClub = await Club.findByIdAndUpdate(
//         clubId,
//         { image: fileName },
//         { new: true }
//       );

//       res.json({ fileName, updatedClub });
//     } else {
//       res.status(400).json({ message: "No image file provided" });
//     }
//   } catch (err) {
//     res.status(500).json({ message: err.message });
//   }
// };

exports.uploadImage = async (req, res) => {
  const { id } = req.params;

  try {
    const club = await Club.findById(id);
    if (!club) {
      return res.status(404).json({ message: "Club not found" });
    }

    if (!req.file) {
      return res.status(400).json({ message: "No file uploaded" });
    }

    // Get the file name from the file path
    const fileName = path.basename(req.file.path);

    // Update the image field with the file name
    club.imageClub = fileName;

    await club.save();

    res.json(club);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};
