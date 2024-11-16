package edu.grinnell.csc207.blockchains;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


/**
 * Some simple tests of our Hash class.
 *
 * @author Samuel A. Rebelsky
 */
public class TestHash {
  /**
   * Some basic tests.
   */
  @Test
  public void basicTests() {
    Hash h = new Hash(new byte[] {1, 1, 2, 3, 5, 8});
    assertEquals(6, h.length(), "length of basic hash");
    assertEquals(1, h.get(0), "element 0 of basic hash");
    assertEquals(1, h.get(1), "element 1 of basic hash");
    assertEquals(2, h.get(2), "element 2 of basic hash");
    assertEquals(3, h.get(3), "element 3 of basic hash");
    assertEquals(5, h.get(4), "element 4 of basic hash");
    assertEquals(8, h.get(5), "element 5 of basic hash");
    assertArrayEquals(new byte[] {1, 1, 2, 3, 5, 8}, h.getBytes(),
        "bytes of basic hash");
    assertEquals("010102030508", h.toString(), "basic hash as string");
    assertTrue(h.equals(new Hash(new byte[] {1, 1, 2, 3, 5, 8})),
        "basic hash equals a hash with the same bytes");
    assertFalse(h.equals(new Hash(new byte[] {1, 1, 2, 3, 5})),
        "basic hash does not equal similar hash with fewer bytes");
    assertFalse(h.equals(new Hash(new byte[] {1, 1, 2, 3, 5, 8, 11})),
        "basic hash does not equal similar hash with more bytes");
    assertFalse(h.equals(h.toString()),
        "basic hash does not equal string representation of hash");
    assertFalse(h.equals(new Hash(new byte[] {0, 1, 2, 3, 5, 8})),
        "basic hash does not equal similar hash with diff elt 0");
    assertFalse(h.equals(new Hash(new byte[] {1, 1, 2, 3, 5, 7})),
        "basic hash does not equal similar hash with diff last elt");
  } // basicTests

  /**
   * Ensure that future modifications to the byte array don't
   * affect the hash.
   */
  @Test
  public void testParamCopied() {
    byte[] bytes = new byte[] {2, 0, 7};
    Hash h = new Hash(bytes);
    assertArrayEquals(bytes, h.getBytes(), "original bytes are the same");
    assertEquals(bytes[1], h.get(1), "byte 1 is the same");

    bytes[1] = 9;
    assertFalse(h.equals(new Hash(bytes)), 
        "hash should not hash built from modified bytes");
    assertNotEquals(bytes[1], h.get(1), "byte 1 has changed");
    assertEquals(bytes[0], h.get(0), "byte 0 is the same");
    assertEquals(bytes[2], h.get(2), "byte 2 is the same");
  } // testParamCopied

  /**
   * Ensure that the returned array is a copy.
   */
  @Test
  public void testReturnBytes() {
    Hash h = new Hash(new byte[] {3, 1, 4, 1, 5, 9});
    byte[] bytes = h.getBytes();
    assertTrue(h.equals(new Hash(bytes)), 
       "a hash equals a hash made from its bytes");
    assertEquals(3, h.get(0), "byte 0 is 3");
    bytes[0] = 15;
    assertEquals(3, h.get(0), "byte 0 is 3");
    assertFalse(h.equals(new Hash(bytes)), 
       "a hash does not equal a hash made from its modified bytes");
  } // testReturnBytes
  
} // class TestHash
