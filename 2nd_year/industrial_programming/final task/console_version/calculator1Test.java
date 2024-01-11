package final_task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class calculator1Test {

	@Test
	void test1() {
		assertEquals(4, new calculator1().calculate("2+2"));
	}
	
	@Test
	void test2() {
		assertEquals(8, new calculator1().calculate("2*2*2"));
	}
	
	@Test
	void test3() {
		assertEquals(-4, new calculator1().calculate("100/10-6+2*2-12"));
	}

}
