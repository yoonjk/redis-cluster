local current = redis.call('zrangebyscore', KEYS[1], ARGV[1], ARGV[2], 'LIMIT', ARGV[3], ARGV[4])
if (current == nil or current == '') then
    return 0
else
    for i, mem in pairs(current) do
        redis.call('zincrby', KEYS[1], 1, mem)
    end

    return 1
end