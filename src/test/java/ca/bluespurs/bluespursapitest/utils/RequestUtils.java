package ca.bluespurs.bluespursapitest.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Utility class containing methods to handle mock requests.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
public class RequestUtils {
	public static class RequestParam {
		private final String name;
		private final String value;

		RequestParam(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	/**
	 * Method responsible for performing a GET request on the given path and matching the status passed.
	 *
	 * @param mockMvc     The mock MVC to be used.
	 * @param path        The path to GET.
	 * @param matchStatus The status to be validated.
	 * @return A {@link ResultActions} that can be further used to add more validations.
	 * @throws Exception If any error occurs.
	 */
	public static ResultActions mockGetRequest(final MockMvc mockMvc,
	                                           final String path,
	                                           final ResultMatcher matchStatus,
	                                           final String name) throws Exception {
		return mockMvc.perform(get(path).param("name", name))
				.andExpect(matchStatus);
	}

	/**
	 * Creates a new request parameter with the given name and value.
	 *
	 * @param name  The name to be set.
	 * @param value The value to be set.
	 * @return The parameter created.
	 */
	public static RequestParam newParam(String name, String value) {
		return new RequestParam(name, value);
	}
}
