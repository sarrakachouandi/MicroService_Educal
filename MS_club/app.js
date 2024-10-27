const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
const clubRoutes = require("./routes/clubRoutes");
const eurekaHelper = require("./eureka-helper");
const path = require("path");

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());

// Connect to MongoDB Atlas (replace 'YOUR_CONNECTION_STRING' with your actual MongoDB Atlas connection string)
mongoose.connect(
  "mongodb+srv://nabilmersni123:cm7CD45EWccAFxVHi@club-ms.fwrurps.mongodb.net/?retryWrites=true&w=majority",
  {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  }
);

app.use(
  "/upload-directory",
  express.static(path.join(process.cwd(), "upload-directory"))
);
app.use("/clubs", clubRoutes);

// Register with Eureka
// const eurekaHost =
//   process.env.EUREKA_CLIENT_SERVICEURL_DEFAULTZONE || "localhost";
// const eurekaClient = new Eureka({
//   instance: {
//     app: "club-node",
//     hostName: "localhost", // Change this if needed
//     ipAddr: "127.0.0.1", // Change this if needed
//     port: {
//       $: PORT,
//       "@enabled": "true",
//     },
//     vipAddress: "club-ms",
//     dataCenterInfo: {
//       "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
//       name: "MyOwn",
//     },
//   },
//   eureka: {
//     host: eurekaHost, // Eureka server host
//     port: 8761, // Eureka server port
//     servicePath: "/eureka/apps/",
//   },
// });

// eurekaClient.start();

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});

eurekaHelper.registerWithEureka("club-MS", PORT);
