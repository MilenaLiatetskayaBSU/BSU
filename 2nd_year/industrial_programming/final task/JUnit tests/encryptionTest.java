package final_task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class encryptionTest {

	@Test
	void test1() {
		encryption enc = new encryption();
		assertEquals("RcrjJlQgOoeY+5hMQ8ejmQ==", enc.encrypt("2+2"));
	}
	
	@Test
	void test2() {
		encryption enc = new encryption();
		assertEquals("dUt/LwELhI69KVEe+hhS5g==", enc.encrypt("ABCDE"));
	}
	
	@Test
	void test3() {
		encryption enc = new encryption();
		assertEquals("2+2", enc.decrypt("RcrjJlQgOoeY+5hMQ8ejmQ=="));
	}
	
	@Test
	void test4() {
		encryption enc = new encryption();
		assertEquals("ABCDE", enc.decrypt("dUt/LwELhI69KVEe+hhS5g=="));
	}

}
