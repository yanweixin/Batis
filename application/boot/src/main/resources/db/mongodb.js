use("logdb");
db.createUser({
  user: "logs",
  pwd: "logs",
  roles: ["readWrite"],
});

use("admin");
db.createUser({
  user: "mongos",
  pwd: "mongos", //passwordPrompt(),
  roles: [
    { role: "userAdminAnyDatabase", db: "admin" },
    "readWriteAnyDatabase",
  ],
});

use("app");
db.createUser({
  user: "apps",
  pwd: "apps", //passwordPrompt(),
  roles: ["readWrite"]
});
