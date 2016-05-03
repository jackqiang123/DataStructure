/**
* This is a class of heap. The natural order is assumed.
* It can be easily be extende to generic object and comparator.
*/
public class Heap{
  private ArrayList<Integer> data;
​
  //construct an empty heap
  public Heap(){
    data = new ArrayList<Integer>();
    data.add(Integer.MIN_VALUE); // the array should be 1-index based
  }
​
  //heapify an array
  public Heap(int []input){ //heapify
    Heap();
    for (int i : input)
      data.add(i);
    for (int i = data.size()/2; i >= 1; i--)
      heapify(i);
  }
​
  /**
  * add a new element into the heap
  */
  public void add(int x){
    data.add(x);
    int pos = data.size() - 1;
    while(data.get(pos/2) > data.get(pos)){
      swap(pos/2, pos);
      pos = pos/2;
    }
  }
​
  /**
  * return the min element of the heap
  */
  public int getMin(){
    return data.get(1);
  }
​
  /**
  * remove the min element and return it.
  */
  public int removeMin(){
    int min = data.get(1);
    swap(1, data.size() - 1);
    data.remove(data.size() -1);
    int pos = 1;
    int tail = data.size() - 2;
    while(pos*2 <= tail){
      int target = 2*pos;
      //swap with the smaller value children
      if(pos*2+1<=tail && data.get(pos*2) > data.get(pos*2+1)){
          target = 2*pos + 1;
      }
      if (data.get(pos) < data.get(target)) break;
      swap(pos, target);
      pos = target;
    }
    return min;
  }
​
  /**
  * return whether the heap is empty
  */
  public boolean isEmpty(){
    return data.size() == 1;
  }
​
  //swap i and j
  private void swap(int i, int j){
    int t = data.get(i);
    data.set(i,data.get(j));
    data.set(j,t);
  }
​
  // make index i element satify heap property
  private void heapify(int i){
    int left = 2*i;
    int right = 2*i+1;
    int tail = data.size() - 2;
    int target = i;
    if (left <= tail && data.get(target) > data.get(left)){
      target = left;
    }
    if (right <= tail && data.get(target) > data.get(right)){
      target = right;
    }
    if (target != i){
      swap(target,i);
      heapify(target);
    }
  }
}
