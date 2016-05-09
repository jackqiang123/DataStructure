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
  * get the rank of number x. return -1 if that number is not in the tree
  */
  public int rank(int x){
    return rank(root,x);
  }

  public int rank(TreeNode root, int x){
    if (root == null) return -1;
    if (root.val == x) return 1 + size(root.left, x);
    else if (root.val < x) return 1 + size(root.left) + rank(root.right, x);
    else return rank(root.left, x);
  }

  /**
  * get the insert rank of element key. the insert rank is 1 based
  */
  public int insertrank(int key){
    if (root == null) return 1;
    return insertrank(root, key);
  }

  private int insertrank(TreeNdoe root, int x)
  {
    if (root.val == x) return 1 + size(root.left, x);
    else if (root.val < x) {
      if (root.right == null)
        return 1 + size(root.left);
      return 1 + size(root.left) + insertrank(root.right, x);
    }
    else {
      if (root.left == null)
        return 1;
      return insertrank(root.left, x);
    }
  }

  /**
  * find rank k elentment. k is 1-based
  * when k is out of rank, we will return null.
  */
  public TreeNode select(int rank){
    return select(root, rank);
  }

  private TreeNode select(TreeNdoe root, int rank){
    if (root == null) return null;
    int leftsize = size(root.left);
    if (rank == leftsize + 1) return root;
    else if (rank < leftsize + 1) return select(root.left, rank);
    else return select(root.right, rank - leftsize - 1)
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
    The invariance is the prev can only in two place.
    first, the path from root to the target, second, on the substree.
  */
  public TreeNode prev(int x){
    if (root == null) return null;
    TreeNode tempprev = x > root.val ? root : null;
    TreeNode node = root;
    while(node != null){
      if (node.val < x){
        if (tempprev != null && tempprev.val < node.val){
          tempprev = node;
        }
        node = node.right;
      }
      else if (node.val > x)
      {
        node = node.left;
      }
      else break;
    }
    if (node == null){// no such node in the tree
      return null;
    }
    //search the subtree.
    if (node.left == null) return tempprev;
    node = node.left;
    while(node.right != null)
      node = node.right;
    return tempprev == null ? node : (tempprev.val > node.val ? tempprev.val : node.val);
  }

  /**
  * find the next element of number x in BST
    return -1 if it is not exitst
  */
  public TreeNode next(int x){
    if (root == null) return null;
    TreeNode tempnext = x < root.val ? root : null;
    TreeNode node = root;
    while(node != null){
      if (node.val > x){
        if (tempnext != null && tempnext.val > node.val){
          tempnext = node;
        }
        node = node.left;
      }
      else if (node.val < x)
      {
        node = node.right;
      }
      else break;
    }
    if (node == null){// no such node in the tree
      return null;
    }
    //search the subtree.
    if (node.right == null) return node;
    node = node.right;
    while(node.left != null)
      node = node.left;
    return tempnext == null ? node : (tempnext.val < node.val ? tempnext.val : node.val);
  }

  /**
  * return the iterator of the elements
  */
  public List<Integer> iterator(){
    List<Integer> iterator = new ArrayList<Integer>();
    dfs(root, iterator);
    return iterator;
  }

  private void dfs(TreeNode root, List<Integer> res){
    if (root == null) return;
    dfs(root.left);
    res.add(root.val);
    dfs(root.right);
  }
}
