local obj = redis.call("get",KEYS[1]);
local obj2 = string.gsub(obj,"(" ..ARGV[1] .. "\":)([^,}]+)", "%1" .. "\"" ..ARGV[2] .."\"")

return redis.call("set",KEYS[1],obj2);