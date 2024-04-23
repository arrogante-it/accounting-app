package com.arroganteIT.app;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ArroganteITappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void whenTestApp_thenEmptyResponse() throws Exception {
		this.mockMvc.perform(get("/api/v1/ex-user/all"))
				.andExpect(status().isOk()); // 200 ok // 405 not ok
	}
}
