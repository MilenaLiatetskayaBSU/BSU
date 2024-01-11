package final_task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Work_with_jsonTest {

	@Test
	void test() {
		Work_with_json file = new Work_with_json();
		ArrayList<Double>result = new ArrayList<>();
		result = file.Read("input.json", 0);
		assertEquals(11.5, result.get(0));
		assertEquals(6, result.get(1));
		assertEquals(15, result.get(2));
	}

}
