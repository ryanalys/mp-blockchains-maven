package edu.grinnell.csc207.blockchains;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Blocks to be stored in blockchains.
 *
 * @author Alyssa Ryan
 * @author Samuel A. Rebelsky
 */
public class Block {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The previous hash.
   */
  private Hash prevHash;
  /**
   * The current has of this block.
   */
  private Hash hash;
  /**
   * The transaction.
   */
  Transaction transaction;
  /**
   * The number this current block is.
   */
  private int number;
  /**
   * The nonce.
   */
  long nonce;
  /**
   * An integer byte buffer for the class to use
   */
  private static ByteBuffer intByteBuffer = ByteBuffer.allocate(Integer.BYTES);
  /**
   * A long byte buffer for the class to use
   */
  private static ByteBuffer longByteBuffer = ByteBuffer.allocate(Long.BYTES);
  /**
   * Message digest to use throughout the class
   */
  private static MessageDigest mess;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new block from the specified block number, transaction, and
   * previous hash, mining to choose a nonce that meets the requirements
   * of the validator.
   *
   * @param num
   *   The number of the block.
   * @param transaction
   *   The transaction for the block.
   * @param prevHash
   *   The hash of the previous block.
   * @param check
   *   The validator used to check the block.
   */
  public Block(int num, Transaction transaction, Hash prevHash,
      HashValidator check) {
    this.number = num;
    this.transaction = transaction;
    this.prevHash = prevHash;
    if (check != null) {
      this.mine(check);
    } //if
  } // Block(int, Transaction, Hash, HashValidator)

  /**
   * Create a new block, computing the hash for the block.
   *
   * @param num
   *   The number of the block.
   * @param transaction
   *   The transaction for the block.
   * @param prevHash
   *   The hash of the previous block.
   * @param nonce
   *   The nonce of the block.
   */
  public Block(int num, Transaction transaction, Hash prevHash, long nonce) {
    this.number = num;
    this.transaction = transaction;
    this.prevHash = prevHash;
    this.nonce = nonce;
    this.computeHash();
  } // Block(int, Transaction, Hash, long)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Compute the hash of the block given all the other info already
   * stored in the block.
   */
  void computeHash() {
    byte[] numbuff = Block.intByteBuffer.putInt(this.number).array();
    Block.intByteBuffer.clear();
    byte[] tranbuffT = this.transaction.getTarget().getBytes();
    byte[] tranbuffS = this.transaction.getSource().getBytes();
    byte[] tranbuffA = Block.intByteBuffer.putInt(this.transaction.getAmount()).array();
    Block.intByteBuffer.clear();
    Hash previous = this.prevHash;
    if (previous != null) {
      byte[] hashbuff = previous.getBytes();
      mess.update(hashbuff);
    } //if
    long n = this.nonce;
    byte[] noncebuff = Block.longByteBuffer.putLong(n).array();

    mess.update(numbuff);
    mess.update(tranbuffT);
    mess.update(tranbuffS);
    mess.update(tranbuffA);
    mess.update(noncebuff);

    byte[] output = mess.digest();

    this.hash = new Hash(output);
  } // computeHash()

  private void mine(HashValidator check) {
    if (hash != null && check.isValid(hash)) {
      return;
    } //if
  } //mine(HashValidator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the number of the block.
   *
   * @return the number of the block.
   */
  public int getNum() {
    return this.number;
  } // getNum()

  /**
   * Get the transaction stored in this block.
   *
   * @return the transaction.
   */
  public Transaction getTransaction() {
    return this.transaction;
  } // getTransaction()

  /**
   * Get the nonce of this block.
   *
   * @return the nonce.
   */
  public long getNonce() {
    return this.nonce;
  } // getNonce()

  /**
   * Get the hash of the previous block.
   *
   * @return the hash of the previous block.
   */
  Hash getPrevHash() {
    return this.prevHash;
  } // getPrevHash

  /**
   * Get the hash of the current block.
   *
   * @return the hash of the current block.
   */
  Hash getHash() {
    return this.hash;
  } // getHash

  /**
   * Get a string representation of the block.
   *
   * @return a string representation of the block.
   */
  public String toString() {
    String output = "[" + this.number + ", Transaction: " + this.transaction + ", Hash: " + this.hash + ", Previous Hash: " + this.prevHash + " ]";
    return output;
  } // toString()
} // class Block
