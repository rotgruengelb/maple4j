package net.rotgruengelb.maple;

import net.rotgruengelb.maple.exception.MapleParseException;
import net.rotgruengelb.maple.exception.MapleSyntaxException;
import net.rotgruengelb.maple.value.MapleMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapleTests {

	@Test
	public void parseEmptyString() {
		String input = "";
		MapleMap map = Maple.parse(input);
		assertEquals(0, map.size());
	}

	@Test
	public void parseSingleKeyValuePair() {
		String input = "key = 'value'";
		MapleMap map = Maple.parse(input);
		assertEquals("value", map.getString("key"));
	}

	@Test
	public void parseMultipleKeyValuePairs() {
		String input = """
				key1 = 'value1'
				key2 = 'value2'
				key3 = 3
				key4 = 4.1
				key5 = true
				key6 = [ 0.5, 0.5 ]
				""";
		MapleMap map = Maple.parse(input);
		assertEquals("value1", map.getString("key1"));
		assertEquals("value2", map.getString("key2"));
		assertEquals(3, map.getInt("key3"));
		assertEquals(4.1f, map.getFloat("key4"));
		assertEquals(true, map.getBoolean("key5"));
		assertNotNull(map.getList("key6"));
		assertEquals(2, map.getList("key6")
				.size());
		assertEquals(0.5f, map.getList("key6")
				.getFloat(0));
		assertEquals(0.5f, map.getList("key6")
				.getFloat(1));

	}

	@Test
	public void parseNestedMap() {
		String input = "outer = { inner = 'value' }";
		MapleMap map = Maple.parse(input);
		MapleMap innerMap = map.getMap("outer");
		assertNotNull(innerMap);
		assertEquals("value", innerMap.getString("inner"));
	}

	@Test
	public void parseIntValue() {
		String input = "key = 123";
		MapleMap map = Maple.parse(input);
		assertEquals(123, map.getInt("key"));
	}

	@Test
	public void parseFloatValue() {
		String input = "key = 123.456";
		MapleMap map = Maple.parse(input);
		assertEquals(123.456f, map.getFloat("key"));
	}

	@Test
	public void syntaxExceptionInvalidString() {
		MapleSyntaxException e = assertThrows(MapleSyntaxException.class, () -> Maple.parse("key = 'd "));
		assertEquals("""
				MapleSyntaxException: Invalid token "'d " at line 1, column 7.
				
				key = 'd\s
				      ^
				""", e.getMessage());
	}

	@Test
	public void syntaxExceptionInvalidNumber() {
	    MapleSyntaxException e = assertThrows(MapleSyntaxException.class, () -> Maple.parse("key = 1.2.3"));
	    assertEquals("""
				MapleSyntaxException: Invalid token "." at line 1, column 10.
				
				key = 1.2.3
				         ^
				""", e.getMessage());
	}

	@Test
	public void parseExceptionUnclosedMap() {
		MapleParseException e = assertThrows(MapleParseException.class, () -> Maple.parse("key = {"));
		assertEquals("""
				MapleParseException: Unexpected token "<EOF>" at line 1, column 8.
				Expected: } or a key.
				
				key = {
				       ^
				""", e.getMessage());
	}

	@Test
	public void parseExceptionIncompletePair() {
		MapleParseException e = assertThrows(MapleParseException.class, () -> Maple.parse("key = "));
		assertEquals("""
				MapleParseException: Unexpected token "<EOF>" at line 1, column 7.
				Expected: {, [, a string, a number, or a boolean.
				
				key =\s
				      ^
				""", e.getMessage());
	}

	@Test
	public void parseExceptionMissingEquals() {
		MapleParseException e = assertThrows(MapleParseException.class, () -> Maple.parse("key"));
		assertEquals("""
				MapleParseException: Unexpected token "<EOF>" at line 1, column 4.
				Expected: =.
				
				key
				   ^
				""", e.getMessage());
	}

	@Test
	public void parseSingleKeyValuePairWithComment() {
		String input = """
				// This is a comment
				key = 'value'
				""";
		MapleMap map = Maple.parse(input);
		assertEquals("value", map.getString("key"));
	}

	@Test
	public void parseSingleKeyValuePairWithMultilineComment() {
		String input = """
				/*
				This is a
				multiline comment
				*/
				key = 'value'
				""";
		MapleMap map = Maple.parse(input);
		assertEquals("value", map.getString("key"));
	}

	@Test
	public void parseSingleKeyValuePairWithWhitespace() {
		String input = "key = 'value'     				  ";
		MapleMap map = Maple.parse(input);
		assertEquals("value", map.getString("key"));
	}
}
