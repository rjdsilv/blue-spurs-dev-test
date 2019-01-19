package ca.bluespurs.bluespursapitest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtil {
	private static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();
		MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	static <M> byte[] toJsonByteArray(M model) throws Exception {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return MAPPER.writeValueAsBytes(model);
	}

	private static <M> String toJsonString(M model, boolean pretty) throws JsonProcessingException {
		if (pretty) {
			return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		}
		return MAPPER.writeValueAsString(model);
	}
}
