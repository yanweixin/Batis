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

use app
db.vulnerability.aggregate([
  { $unwind: "$extReferences" },
  {
    $group: {
      _id: {
        ext_type: "$extReferences.type",
        years: {
          $year: "$publishDate"
        }
      },
      "year_cnt": {
        $sum: 1
      }
    }
  }, {
    $project: {
      _id: 0,
      group_name: "$_id.ext_type",
      years: "$_id.years",
      cnt: "$year_cnt"
    }
  }, {
    $sort: {
      years: -1,
      group_name: -1
    }
  }, {

    $limit: 1000
  }])