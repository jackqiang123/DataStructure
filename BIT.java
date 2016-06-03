//This is an implementation of binary indexed tree. Binary is the number be seen in binary number. Rather than 2-branch.
//This data structure is used to store data, such that both operations are efficient: 1) update an element; 2) do range sum quer
//A tree view of BIT can be found here http://d1gjlxt8vb0knt.cloudfront.net//wp-content/uploads/BITSum.png
//This is an array based mutiple branch tree.
//Assume i = 0 is a dummy node. For index i node, its direct parent is i-lowbit(i). Thus, for power of 2 number,
//they are direct children of root 0. For index node (1000)2, its direct children is (1001)2,(1010)2 and (1100)2.
//In each node, we store the segment sum.  For node index i, it can be written in binary number (10110)2, for example,
//node (10000)2 stores sume from 0 to (10000)2, (10100)2 stores sum from (10000)2 to (10100)2, while (10100)2 to (10110)2.
//therefore, when we are seeking sum from 1 to i, we summation from i topward along its parent path. That is we are summing
//all segments and return the complete sum. that is i -= lowbit(i);
//On the other hand, when we are updating an elements, only successors nodes will need to be updated (consider the segment sum concept).
//Therefore, we get the element changes, and we change all relevent ndoes. These nodes include: all successor bothers in the
//same subtree; such change propograte to top. that is i += lowbit(i);

//an extension of BIT is 2D BIT. Where we do the similiar thing for both dimension. We will first iterater on i,
//and then iterator over j, with the same update method for both update and sum.
//this can be seen as for node (i,j), its parent is (i-lowbit(i), j-lowbit(j))
public class BIT{
  private int data[];
  public BIT(int []input){
    data = new int[input.length + 1];
    for (int i = 0; i < input.length; i++)
      modify(i, data[i]);
  }
  public void modify(int i, int val){
    update(i + 1, val - data[i]);
  }
  private void update(int i, int val){
    while(i < data.length){
      data[i] += val;
      i += lowbit(i);
    }
  }
  private int getSum(int i){
    int res = 0;
    while(i > 0){
      res += data[i];
      i -= lowbit(i);
    }
    return res;
  }
  private void lowbit(int i){
    return i&(-i);
  }
  public int getRangeSum(int i, int j){
    return getSum(j + 1) - getSum(i);
  }

}
