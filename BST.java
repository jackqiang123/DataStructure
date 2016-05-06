/**
* author: lian lu
* this is an binary search tree structure
* no dupliate number is allowed
*/
class TreeNode{
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int val){
    this.val = val;
  }
}
public class BST{
  TreeNode root;

  /**
  * create an empty BST
  */
  public BST(){
    root = null;
  }

  public BST(int []data){
    for (int i : data){
      add(i);
    }
  }

  public TreeNode add(int x){
    root = insert(root, x);
  }

  private TreeNode insert(TreeNdoe root, int x){
    if (root == null) return new TreeNode(x);
    if (root.val < x) root.left = insert(root.left, x);
    if (root.val > x) root.left = insert(root.right, x);
    return root;
  }

  /**
  * return the size of the tree.
  */
  public int size(){
    return size(root);
  }

  /**
  * return the size of subtree of node
  */
  private int size(TreeNode node){
    if (node == null) return 0;
    return 1 + size(node.left) + size(node.right);
  }

  //
  public int height(){
    return height(root);
  }
  /**
  * return the height of the bst
  */
  public int height(TreeNode root){
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 1;
    return 1 + Math.max(height(root.left), height(root.right));
  }

  /**
  * find element x in bst, return null if not found
  */
  public TreeNode search(int x){
    return search(root, x);
  }

  /**
  * find element x in bst, return null if not found
  */
  private TreeNode search(TreeNode root, int x){
    if (root == null) return null;
    if (root.val == x) return TreeNode;
    if (root.val < x) return search(root.right, x);
    return search(root.left, x);
  }

  /**
  * get the rank of number x. return null if that number is not in the tree
  * this is a bottom-up recrusion
  */
  public int rank(int x){
    TreeNode cur = search(x);
    if (cur == null) return null;
    return rank(root,x);
  }

  public int rank(TreeNode root, int x){

  }

  /**
  * get the insert rank of element x
  */
  public int insertrank(int x){
    // to be implemented.
  }

  /**
  * find rank k elentment. k is 1-based
  */
  public int select(int rank){

  }

  /**
  * find the min element in BST
  */
  public TreeNode min(){
    return min(root);
  }

  private TreeNode min(TreeNode root){
    if (root == null) return null;
    if (root.left != null) return min(root.left);
    return root;
  }

  /**
  * find the max element in BST
  */
  public int max(){
    return max(root);
  }
  private TreeNode max(TreeNode root){
    if (root == null) return null;
    if (root.right != null) return max(root.right);
    return root;
  }

  /**
  * find the prev element of number x in BST
    return -1 if it is not exitst
  */
  public int prev(int x){

  }

  /**
  * find the next element of number x in BST
    return -1 if it is not exitst
  */
  public int next(int x){

  }

  /**
  * return the iterator of the elements
  */
  public List<Integer> iterator(){

  }
}
