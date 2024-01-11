package yyy;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProgramTest {

	@Test
	void test() {
		Program a = new Program();
		
		assertEquals(Math.sqrt(1.1), a.getResult(0.1,3));
	}

}
