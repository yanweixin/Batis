# 项目说明

## 环境准备
### Redis
redis-cli:
```shell script
CONFIG SET requirepass "PassW0rds"
CONFIG set notify-keyspace-events Kx
```
或者redis.conf:
```ini
requirepass PassW0rds
notify-keyspace-events Kx
```