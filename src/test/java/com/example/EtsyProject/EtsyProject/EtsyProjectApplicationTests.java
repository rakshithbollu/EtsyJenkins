package com.example.EtsyProject.EtsyProject;

import org.apache.logging.log4j.status.StatusConsoleListener;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EtsyProjectApplicationTests {

	public static Logger Logger = LoggerFactory.getLogger(EtsyProjectApplicationTests.class);

	@Test
	public void contextLoads() {
		Logger.info(() -> "Test case executing....");
		assertEquals(true,true);
	}

}
