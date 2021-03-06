package com.songscribe.songscribe.util;
public class ListNode<E> {

    private E data;
    private ListNode<E> nextNode;
    /*  Method Name	: listnode
    Parameters		:  data to be stored
    Return value(s)	: none
    Partners     	: None
    Description	: make one with the type and set the next node to null
     */
    public ListNode(E d){
        data = d;
        nextNode = null;
    }
    /*  Method Name	: listnode
    Parameters		: data and next node
    Return value(s)	: none
    Partners     	: None
    Description	: make one with the next node
     */
    public ListNode(E d, ListNode<E> node){
        data = d;
        nextNode = node;
    }
    /*  Method Name	: setdata
    Parameters		: new data
    Return value(s)	: none
    Partners     	: None
    Description	: set the data to new data
     */
    public void setData(E d){
        data = d;
    }
    /*  Method Name	: getdata
    Parameters		: none
    Return value(s)	: the data
    Partners     	: None
    Description	: gets the data stored
     */
    public E getData(){
        return data;
    }
    /*  Method Name	: setnext
    Parameters		: the next node
    Return value(s)	: none
    Partners     	: None
    Description	: sets the next node to the node you want
     */
    public void setNext(ListNode<E> next){
        nextNode = next;
    }
    /*  Method Name	: getnext
    Parameters		: none
    Return value(s)	: listnode
    Partners     	: None
    Description	: return the next node
     */
    public ListNode<E> getNext(){
        return nextNode;
    }
}
