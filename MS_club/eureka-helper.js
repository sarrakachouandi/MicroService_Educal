const Eureka = require("eureka-js-client").Eureka;
const eurekaHost =
  process.env.EUREKA_CLIENT_SERVICEURL_DEFAULTZONE || "localhost";
const eurekaPort = 8761;
const hostName = process.env.HOSTNAME || "club";
const ipAddr = "172.0.0.1:3000";

exports.registerWithEureka = function (appName, PORT) {
  // health check uRL
  const healthCheckUrl = `http://localhost:${PORT}/health`;
  const client = new Eureka({
    instance: {
      app: appName,
      instanceId: `${appName}:${PORT}`,
      hostName: hostName,
      ipAddr: ipAddr,
      port: {
        $: PORT,
        "@enabled": "true",
      },
      vipAddress: appName,
      dataCenterInfo: {
        "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
        name: "MyOwn",
      },
      healthCheckUrl,
    },
    //retry 10 time for 3 minute 20 seconds.
    eureka: {
      host: eurekaHost,
      port: eurekaPort,
      servicePath: "/eureka/apps/",
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  });

  client.logger.level("debug");

  client.start((error) => {
    console.log(error || "Club Service Registered");
  });

  function exitHandler(options, exitCode) {
    if (options.cleanup) {
    }
    if (exitCode || exitCode === 0) console.log(exitCode);
    if (options.exit) {
      client.stop();
    }
  }

  client.on("deregistered", () => {
    process.exit();
    console.log("Complaint Service Deregistered");
  });

  client.on("started", () => {
    console.log("Eureka Host  " + eurekaHost);
  });

  process.on("SIGINT", exitHandler.bind(null, { exit: true }));
};
