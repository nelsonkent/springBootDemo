package com.nelson.springBootDemo;

import com.nelson.springBootDemo.controller.AccountController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Scope(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
class SpringBootDemoApplicationTests {
	private MockMvc mvc;
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.standaloneSetup(new AccountController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		RequestBuilder request = null;
		request = post("/add.do")
				.param("account","nancy")
				.param("password","123456");
		mvc.perform(request).andExpect(content().string(equalTo("success")));
	}

}
