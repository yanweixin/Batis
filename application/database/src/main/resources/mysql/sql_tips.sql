# 删除全部外键
SELECT concat('ALTER TABLE ', CONSTRAINT_SCHEMA, '.', TABLE_NAME, ' DROP FOREIGN KEY ', CONSTRAINT_NAME, ';')
FROM information_schema.key_column_usage
WHERE CONSTRAINT_SCHEMA = 'YOUR DB HERE'
  AND TABLE_NAME = 'YOUR TABLE HERE'
  AND REFERENCED_TABLE_NAME IS NOT NULL;

# 关闭外键检查
SET foreign_key_checks = 0;
# 重新启用外键检查
SET foreign_key_checks = 1;