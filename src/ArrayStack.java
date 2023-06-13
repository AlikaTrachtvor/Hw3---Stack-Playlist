public class ArrayStack<E> implements Stack<E> {
    private E[] data;
    private int size;
    private int maxCapacity;


    public ArrayStack(int maxC){
        if(maxC < 0)
            throw new NegativeCapacityException();
        else{
            this.maxCapacity = maxC;
            this.size = 0;
            this.data = (E[]) new Object[this.maxCapacity];
        }
    }

    @Override
    public void push(E element) {
        if(this.size == this.maxCapacity)
            throw new StackOverflowException();
        else{
            this.data[size] = element;
            size++;

        }
    }

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

    @Override
    public E peek() {
        if(this.size == 0)
            throw new EmptyStackException();
        else
            return this.data[this.size - 1];
    }

    @Override
    public int size() {return this.size;}

    @Override
    public boolean isEmpty() {
        if(this.size == 0)
            return true;
        return false;
    }

    public ArrayStack clone(){
        
    }
}
