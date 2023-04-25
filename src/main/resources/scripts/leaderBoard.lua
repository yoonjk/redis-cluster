local rank = redis.call('zrank', KEYS[1], KEYS[2])
local min = math.max(rank - ARGV[1], 0)
local max = rank + ARGV[1]
local ldb = redis.call('zrange', KEYS[1], min, max)
local results = {}

results['rank'] = tostring(rank + 1)
results['item'] = ldb

local vars = cjson.encode(results)
return vars
