package lab5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class multicookerTest {

	@Test
	void testGetCost() {
		multicooker cooker = new multicooker.Builder("Agata", 11, 1110).Color("Red").build();
		assertEquals(cooker.GetCost(), 1110);
	}
	
	@Test
	void testGetVolume() {
		multicooker cooker = new multicooker.Builder("Agata", 11, 1110).Color("Red").build();
		assertEquals(cooker.GetVolume(), 11);
	}
	
	@Test
	void testGetName() {
		multicooker cooker = new multicooker.Builder("Agata", 11, 1110).Color("Red").build();
		assertEquals(cooker.GetName(),"Agata");
	}
	
	@Test
	void testGetColor() {
		multicooker cooker = new multicooker.Builder("Agata", 11, 1110).Color("Red").build();
		assertEquals(cooker.GetColor(),"Red");
		multicooker cooker2 = new multicooker.Builder("Agata2", 111, 1110).build();
		assertEquals(cooker2.GetColor()," ");
	}
	
	@Test
	void testToString() {
		multicooker cooker = new multicooker.Builder("Agata", 11, 1110).Color("Red").build();
		assertEquals(cooker.toString(),"cost - 1110  volume - 11  name - Agata  color - Red");
		
	}
}
