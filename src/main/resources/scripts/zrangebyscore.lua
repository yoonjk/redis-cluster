local current = redis.call('zrangebyscore', KEYS[1], ARGV[1], ARGV[2], 'LIMIT', ARGV[3], ARGV[4])
local results = {}

if (current == nil or current == '') then
    results[1] = 'failed'


else
    for i, mem in pairs(current) do
        redis.call('zincrby', KEYS[1], 1, mem)
    end

    results = current
end

local vars = cjson.encode(results)

return vars