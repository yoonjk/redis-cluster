package com.ibm.demo.redis.config;

import java.util.Objects;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class CustomJackson2JsonRedisSerializer extends GenericJackson2JsonRedisSerializer {
	ObjectMapper objectMapper = new ObjectMapper();
	
	public CustomJackson2JsonRedisSerializer(ObjectMapper objectMapper) {
		super(objectMapper);
		this.objectMapper = objectMapper;
	}
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
			
			Assert.notNull(type,
					"Deserialization type must not be null! Please provide Object.class to make use of Jackson2 default typing.");

			if (Objects.isNull(source)) {
				return null;
			}

			log.info("Source:{}, class:{}", new String(source), type);
			clazz = objectMapper.readValue(new String(source), type);
			return clazz;
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
}
