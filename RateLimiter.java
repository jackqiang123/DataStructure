// leak bucket algorithm for rate limiter
// no burst rate is allowed
public class RateLimiter{
  private long win;
  private int maxHit;
  private Deque deque;
  public RateLimiter(long millsec, int maxHit){
    this.win = millsec;
    this.maxHit = maxHit;
    deque = new LinkedList();
  }

  public void callFunction(Object someArgument){
    if (deque.isEmpty() && deque.size() < maxHit){
      call(someArgument);
      deque.addLast(System.getMillionSeconds());
    }
    else {
      if (deque.peekFirst() > System.getMillionSeconds - win){
        reject(someArgument);
      }
      else {
        deque.removeFirst();
        deque.addLast(System.getMillionSeconds);
      }
    }

  }
}
