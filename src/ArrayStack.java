import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/***
 *This class represents a Stack that uses an array to hold its information
 * This is a generic Stack that can contain only Cloneable Objects
 */

public class ArrayStack<E extends Cloneable> implements Stack<E>{
    /***
     * Since Stack is based on the FILO system, the information in the data array is organized as the following:
     * the beginning of the array is the bottom of the stack, hence the feature size - 1 also serves as an index
     * to the top of the stack
     */
    private E[] data;
    private int size;
    private int maxCapacity;


    public ArrayStack(int maxC){
        if(maxC < 0)
            throw new NegativeCapacityException();
        else{
            this.maxCapacity = maxC;
            this.size = 0;
            this.data = (E[]) new Cloneable[this.maxCapacity];
        }
    }

    /***
     * This method allows to insert a new Object to the top of the Stack
     * @param element the object being inserted to the top of the Stack
     */
    @Override
    public void push(E element) {
        if(this.size == this.maxCapacity)
            throw new StackOverflowException();
        else{
            this.data[size] = element;
            size++;

        }
    }

    /***
     * This method removes the Object at the top of the Stack
     * @return the Object that was removed
     */
    @Override
    public E pop() {
        if(this.size == 0)
            throw new EmptyStackException();
        else{
            this.size--;
            E popped = this.data[this.size];
            this.data[this.size] = null;
            return popped;
        }
    }

    /***
     * This method shows the Object at the top of the Stack without removing it
     * @return The Object at the top of the Stack
     */
    @Override
    public E peek() {
        if(this.size == 0)
            throw new EmptyStackException();
        else
            return this.data[this.size - 1];
    }

    /***
     * This method returns the current size of the Stack (how many Objects does it contain)
     * @return how many Objects there are in the Stack
     */
    @Override
    public int size() {return this.size;}

    /***
     * This method checks whether the Stack is empty
     * @return true if it's empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        if(this.size == 0)
            return true;
        return false;
    }

    /***
     * This method allows to deep clone the current Stack
     * @return a copy of the current Stack
     */
    @Override
    public ArrayStack clone(){
        try {
            ArrayStack<E> copy = new ArrayStack<E>(this.maxCapacity);
            copy.size = this.size;
            copy.data = this.data.clone();
            Method clone = this.data[0].getClass().getMethod("clone");
            for (int i = 0; i < this.size; i++) {
                copy.data[i] = (E)clone.invoke(copy.data[i]);
            }
            return copy;
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /***
     * This is and internal class of an Iterator to enable scanning of the Stack
     */
    class StackIterator<E> implements Iterator<E> {

        private int currIndex;
        private E[] data;

        public StackIterator(int index, E[] data){
            this.currIndex = index;
            this.data = data;
        }

        /***
         * This method checks whether there is another Object to be scanned after the current one
         * @return true if there is and false otherwise
         */
        @Override
        public boolean hasNext(){
            if(this.currIndex >= 0)
                return true;
            return false;
        }

        /***
         * This method find the next Object that needs to be scanned
         * @return the next Object to be scanned
         */
        @Override
        public E next(){
            if(hasNext()) {
                E element = this.data[this.currIndex];
                this.currIndex--;
                return element;
            }
            return null;
        }
    }

    /***
     * This method creates the iterator used to scan the Objects in the Stack
     * @return a StackIterator used to scan the Objects in the Stack
     */
    @Override
    public StackIterator<E> iterator(){return new StackIterator(this.size - 1, this.data);}


}
