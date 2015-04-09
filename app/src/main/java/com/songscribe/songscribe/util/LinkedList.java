package com.songscribe.songscribe.util;
import com.songscribe.songscribe.util.e.EmptyListException;

import java.lang.reflect.Array;


public class LinkedList<E> {

    private ListNode<E> firstNode;
    private ListNode<E> lastNode;
    private int numElements;
    private String name;
    /*  Method Name	: linkedlist
    Parameters		: none
    Return value(s)	: none
    Partners     	: None
    Description	: make a list named list that is empty
     */
    public LinkedList(){
        name = "list";
        firstNode = null;
        lastNode = null;
        numElements = 0;
    }
    /*  Method Name	: linkedlist
    Parameters		: string that is the name of the new list
    Return value(s)	: none
    Partners     	: None
    Description	: make one with a name
     */
    public LinkedList(String listName){
        name = listName;
        firstNode = null;
        lastNode = null;
        numElements = 0;
    }
    /*  Method Name	: insertatfront
    Parameters		: the item to add
    Return value(s)	: none
    Partners     	: None
    Description	: make a new node with the data and put it at the front
     */
    public void insertAtFront(E item){
        if(isEmpty()){
            firstNode = new ListNode<E>(item);
            lastNode = firstNode;
        }
        else firstNode = new ListNode<E>(item, firstNode);
        numElements++;
    }
    /*  Method Name	: insertatback
    Parameters		: the data to add
    Return value(s)	: none
    Partners     	: None
    Description	: make a new node with the item and put it at the back
     */
    public void insertAtBack(E item){
        if(isEmpty()){
            firstNode = new ListNode<E>(item);
            lastNode = firstNode;
        }
        else{
            ListNode<E> temp = new ListNode<E>(item);
            lastNode.setNext(temp);
            lastNode = temp;
        }
        numElements++;
    }
    /*  Method Name	: removefromfront
    Parameters		: none
    Return value(s)	: the data removed
    Partners     	: None
    Description	: removes the front node
     */
    public E removeFromFront() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException(name);
        ListNode<E> temp = firstNode;
        if(firstNode.getNext()!=null)firstNode = firstNode.getNext();
        else {
            firstNode = null;
            lastNode = null;
        }
        numElements--;



        return temp.getData();
    }
    /*  Method Name	: removefromback
    Parameters		: none
    Return value(s)	: the data removed
    Partners     	: None
    Description	: removes the node on the back
     */
    public E removeFromBack() throws EmptyListException{
        if(isEmpty()) throw new EmptyListException(name);
        ListNode<E> temp1 = lastNode;

        ListNode<E> temp = firstNode;
        for(int i = 0; i < numElements-2; i++){
            temp = temp.getNext();
        }
        lastNode = temp;
        lastNode.setNext(null);


        numElements--;
        return temp1.getData();
    }
    /*  Method Name	: remove
    Parameters		: the index of the node to be removed
    Return value(s)	: none
    Partners     	: None
    Description	: remove a node at the specified location
     */
    @SuppressWarnings("unchecked")
    public void remove(int index) throws EmptyListException, IndexOutOfBoundsException{
        if(index == 0) {
            removeFromFront();
        }else if(index == numElements-1){
            removeFromBack();
        }else{
            if(index > numElements-1 || index < 0) throw new IndexOutOfBoundsException(name);
            if(isEmpty()) throw new EmptyListException(name);


            ListNode<E> temp = firstNode;

            Class<E> type = (Class<E>) ListNode.class;
            ListNode<E>[] list;
            list = (ListNode<E>[])Array.newInstance(type,numElements);

            for(int i = 0; i < numElements; i++){
                list[i] = (ListNode<E>) temp;
                if(temp.getNext() != null)temp = temp.getNext();
                else {numElements = i+1;
                    break;
                }
            }



            ListNode<E> before = null;
            ListNode<E> after = null;

            if(index > 0)before = list[index-1];
            if(index < numElements)after = list[index+1];

            if(before != null)before.setNext(after);




            numElements--;
        }
    }
    /*  Method Name	: get
    Parameters		: the index to get the data
    Return value(s)	: teh data at in the node
    Partners     	: None
    Description	: get the data from the node at the specific index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) throws EmptyListException, IndexOutOfBoundsException{
        if(isEmpty()) throw new EmptyListException(name);
        if(index > numElements-1 || index < 0) throw new IndexOutOfBoundsException(name);
        ListNode<E> temp = firstNode;
        Class<E> type = (Class<E>) ListNode.class;
        ListNode<E>[] list;
        list = (ListNode<E>[])Array.newInstance(type,numElements);



        for(int i = 0; i < numElements; i++){
            list[i] = (ListNode<E>) temp;
            if(temp.getNext() != null)temp = temp.getNext();
            else {numElements = i+1;
                break;
            }
        }



        ListNode<E> t = list[index];
        if(t == null) throw new IndexOutOfBoundsException(name);
        return t.getData();

    }
    /*  Method Name	: findandremove
    Parameters		: the data to find and remove
    Return value(s)	: true if it wa found false if it wasnt
    Partners     	: None
    Description	: find the data and remove it
     */
    public boolean findAndRemove(E item) throws EmptyListException{


        if(isEmpty()) throw new EmptyListException(name);

        int index = findItem(item);
        if(index == -1) return false;
        else{

            remove(index);
            return true;
        }

    }
    /*  Method Name	: findItem
    Parameters		: data to find
    Return value(s)	: index of the data
    Partners     	: None
    Description	: find the data in the list
     */
    public int findItem(E item){
        ListNode<E> temp = firstNode;

        for(int i = 0; i < numElements; i++){
            if(temp.getData() == item) return i;
            if(temp.getNext() != null)temp = temp.getNext();

        }
        return -1;
    }
    /*  Method Name	: lengthis
    Parameters		: none
    Return value(s)	: length of the list
    Partners     	: None
    Description	: return the length
     */
    public int lengthIs(){
        return numElements;
    }
    /*  Method Name	: clear
    Parameters		: none
    Return value(s)	: none
    Partners     	: None
    Description	: clear the list
     */
    public void clear(){
        while(!isEmpty()){
            removeFromFront();

        }
        firstNode = null;
        lastNode = null;
    }
    /*  Method Name	: print
    Parameters		: none
    Return value(s)	: none
    Partners     	: None
    Description	: print the contents of the list
     */
    public void print(){
        String print = "";
        if(isEmpty()) print = "Empty " + name;
        else{
            ListNode<E> temp = firstNode;
            print = "The list "+name+" is: ";

            for(int i = 0; i < numElements; i++){
                print+=temp.getData()+" ";
                temp = temp.getNext();
            }
        }
        System.out.println(print);
    }
    /*  Method Name	: isEmpty
    Parameters		: none
    Return value(s)	: if it is empty return true
    Partners     	: None
    Description	: check if the list is empty
     */
    public boolean isEmpty(){
        if(numElements <1) return true;
        else return false;
    }
}
