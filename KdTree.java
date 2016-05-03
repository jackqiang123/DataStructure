import java.util.ArrayList;
import java.util.List;
​
class Point2D {
	double x;
	double y;
​
	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
}
​
class RectHV {
	Point2D lb;
	Point2D rt;
​
	public RectHV(Point2D lb, Point2D rt) {
		this.lb = lb;
		this.rt = rt;
	}
​
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}
​
	public boolean intersect(RectHV rect) {
		// TODO Auto-generated method stub
		return false;
	}
​
}
​
/**
 * @author LL044937 We will assume all points are normilized in unit square.
 */
public class KdTree {
	class Node {
		public Node(Point2D p, RectHV rect, Node lb, Node rt) {
			super();
			this.p = p;
			this.rect = rect;
			this.lb = lb;
			this.rt = rt;
		}
​
		private Point2D p;
		private RectHV rect;
		private Node lb;
		private Node rt;
	}
​
	private int N;
​
	private Node root;
​
	/**
	 * Generate an empty KdTree
	 */
	public KdTree() {
		root = null;
		N = 0;
	}
​
	/**
	 * Generate an KdTree using a list of points
	 *
	 * @param node
	 */
	public KdTree(List<Point2D> node) {
		// Insert one by one is not a good idea, since the tree will not be balanced. We will build a balanced KDTree later.
		for (Point2D p : node){
			add(p);
		}
	}
​
	/**
	 * Insert a Point2D into KdTree
	 *
	 * @param node
	 */
	public void add(Point2D p) {
		if (p == null)
			return;
		this.root = insert(root, p, true, new RectHV(p, p));
	}
​
	private Node insert(Node root, Point2D p, boolean xcmp, RectHV rectHV) {
		if (root == null) {
			N++;
			return new Node(p, new RectHV(new Point2D(0.0, 0.0), new Point2D(1.0, 1.0)), null, null);
		} else if (root.p.x == p.x && root.p.y == p.y)
			return root;
		else {
			if (xcmp) {
				if (root.p.x >= p.x)
					root.lb = insert(root, p, !xcmp, new RectHV(rectHV.lb, new Point2D(root.p.x, root.rect.rt.y)));
				else
					root.rt = insert(root, p, !xcmp, new RectHV(new Point2D(root.p.x, root.rect.lb.y), rectHV.rt));
			} else {
				if (root.p.y >= p.y)
					root.lb = insert(root, p, !xcmp, new RectHV(rectHV.lb, new Point2D(root.rect.rt.x, root.p.y)));
				else
					root.rt = insert(root, p, !xcmp, new RectHV(new Point2D(root.rect.lb.x, root.p.y), rectHV.rt));
			}
			N++;
			return root;
		}
	}
​
	/**
	 * @param rect
	 * @return All points in the range rect
	 */
	public List<Point2D> rangeSearch(RectHV rect) {
		return range(root, rect);
	}
​
	private List<Point2D> range(Node node, RectHV rect) {
		ArrayList<Point2D> res = new ArrayList<Point2D>();
		if (node == null)  return res;
		if (rect.contains(node.p)) res.add(node.p);
		if (node.lb != null && rect.intersect(node.lb.rect))
			res.addAll(range(node.lb, rect));
		if (node.rt != null && rect.intersect(node.rt.rect))
			res.addAll(range(node.rt, rect));
		return res;

	}
​
	/**
	 * @param node
	 * @return The neareast point to node, if not found return null
	 */
	public Point2D findnearest(Point2D p) {
		if (size() == 0)
			return null;
		return nearest(root, p, true, root.p);
	}
​
	private Point2D nearest(Node node, Point2D p, boolean xcmp, Point2D currentBest) {
    if (node == null) return currentBest;
    double currentBestDis = p.distanceTo(currentBest);
    double disToRoot = p.distanceTo(node.p);
    if (disToRoot < currentBestDis){
        currentBestDis = disToRoot;
        currentBest = node.p;
    }

    // an optimization is to first check the subtree on the same side of p corresponding to node
    Point2D near, far;
    if ((xcmp && p.x < node.p.x) || (!xcmp && p.y < node.p.y)){
       near = nearest(node.lb, p, !xcmp, currentBest);
       far = nearest(node.rt, p, !xcmp, near);
    }else{
       near = nearest(node.rt, p, !xcmp, currentBest);
       far = nearest(node.lb, p, !xcmp, near);
    }
		return far;
	}
​
	/**
	 * @return whether the tree is empty or not
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
​
	/**
	 * @return the size of all Point2D
	 */
	public int size() {
		return N;
	}
}
