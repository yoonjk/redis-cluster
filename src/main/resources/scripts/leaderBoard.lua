local rank = redis.call('zrank', KEYS[1], KEYS[2])
local min = math.max(rank - ARGV[1], 0)
local max = rank + ARGV[1]
local ldb = redis.call('zrange', KEYS[1], min, max)
local results = {}
redis.log(redis.LOG_DEBUG, ldb)
results[1] = tostring(rank + 1)

return results
