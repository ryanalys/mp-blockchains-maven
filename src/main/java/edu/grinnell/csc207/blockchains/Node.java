package edu.grinnell.csc207.blockchains;

/**
 * One node, which holds one block
 * @author Alyssa Ryan
 */
public class Node<T> {
  private T data;
  private Node<T> next;
  private Node<T> prev;
  /**
   * Get the data of the node.
   * @return the data
   */
  public T getData() {
    return this.data;
  } //getData()
  /**
   * Get the next node in the chain.
   * @return the next node
   */
  public Node<T> getNext() {
    return this.next;
  } //getNext()
  /**
   * Get the last node in the chain.
   * @return the previous node
   */
  public Node<T> getPrev() {
    return this.prev;
  } //getPrev;
  /**
   * Sets the data of this node
   * @param value The value to set data to
   */
  public void setData(T value) {
    this.data = value;
  } //setData(T)
  /**
   * Sets the next node of this node
   */
  public void setNext(Node<T> node) {
    this.next = node;
  } //setNext(Node<T>)
  /**
   * Sets the previous node of this node
   */
  public void setPrev(Node<T> node) {
    this.prev = node;
  } //setPrev(Node<T>)
  /**
   * Constructor of the node.
   * @param value Data for the node
   * @param nextNode Next node in the chain
   * @param prevNode Last node in the chain
   */
  public Node (T value, Node<T> nextNode, Node<T> prevNode) {
    this.data = value;
    this.next = nextNode;
    this.prev = prevNode;
  } //Node(T, Node<T>, Node<T>)
  /**
   * Constructor of the node.
   * @param value Data to add to the node
   */
  public Node (T value) {
    this.data = value;
    this.next = null;
    this.prev = null;
  } //Node(Block)
} //class Node
