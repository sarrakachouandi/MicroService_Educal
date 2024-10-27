const express = require("express");
const router = express.Router();
const clubController = require("../controllers/clubController");
const multer = require("multer");
const path = require("path");

const storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, path.join(process.cwd(), "upload-directory"));
  },
  filename: (req, file, cb) => {
    cb(null, `${Date.now()}-${file.originalname}`);
  },
});

const upload = multer({ storage: storage });

router.get("/", clubController.getAllClubs);
router.post("/", clubController.createClub);
router.post(
  "/uploadImage/:id",
  upload.single("fileImage"),
  clubController.uploadImage
);

// router.post(
//   "/uploadImage/:id/",
//   upload.single("image"),
//   clubController.uploadImage
// );

router.get("/:id", clubController.getClubById);
router.put("/", clubController.updateClub);
router.delete("/:id", clubController.deleteClub);

module.exports = router;
