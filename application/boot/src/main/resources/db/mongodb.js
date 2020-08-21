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
  roles: ["readWrite"],
});

// 各种漏洞类型对应漏洞数量
db.getCollection("vulnerabilities").aggregate([
  { $unwind: "$baseExtReferencesList" },
  {
    $group: {
      _id: { group_name: "$baseExtReferencesList.extReferenceTypes.name" },
      ids: { $addToSet: "$id" },
    },
  },
  { $unwind: "$ids" },
  {
    $group: {
      _id: "$_id",
      count: { $sum: 1 },
    },
  },
  { $sort: { count: -1 } },
  {
    $project: {
      _id: 0,
      group_name: "$_id.group_name",
      nums: "$count",
    },
  },
]);

db.getCollection("vulnerabilities").aggregate([
  { $unwind: "$baseExtReferencesList" },
  {
    $group: {
      _id: { group_name: "$baseExtReferencesList.extReferenceTypes.name" },
      ids: { $addToSet: "$id" },
    },
  },
  {
    $project: {
      _id: 0,
      group_name: "$_id.group_name",
      nums: { $size: "$ids" },
    },
  },
  { $sort: { nums: -1 } },
]);
