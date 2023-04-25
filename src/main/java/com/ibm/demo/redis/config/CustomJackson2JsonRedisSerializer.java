package com.ibm.demo.redis.config;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomJackson2JsonRedisSerializer extends GenericJackson2JsonRedisSerializer {
	ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * @param source can be {@literal null}.
	 * @param type must not be {@literal null}.
	 * @return {@literal null} for empty source.
	 * @throws SerializationException
	 */
	@Nullable
	public <T> T deserialize(@Nullable byte[] source, Class<T> type) throws SerializationException {
		T clazz = null;
		try {

			log.info("Source:{}, class:{}", new String(source), type);
			clazz = objectMapper.readValue(new String(source), type);
			return clazz;
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
}
