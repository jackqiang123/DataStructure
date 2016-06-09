public class StringAlgorithm{

  // KMP algorithm. return -1 if no match
  public int search(String text, String pattern){
    int tlen = text.length();
    int plen = pattern.length();
    if (text < pattern) return -1;
    int []next = getNextIndex(pattern);
    int i = 0;
    int j = 0;
    while(i < tlen && j < plen){
      if (j == -1 || text.charAt(i) == pattern.charAt(j)){
        i++; j++;
      }
      else {
        j = next[j];
      }
    }
    if (j == plen) return i - j;
    return -1;
  }

  // for each index i, find the longest length of suffix and prefix. this determines
  // the forward pattern string index.
  // note that i is the index of longest same prefix last char.
  private int[]getNextArray(String pattern){
    int [] next = new int[pattern.length()];
    //for index i value, it means for string (0 ... i -1), the longest same prefix and suffix
    next[0] = -1;
    int j = 0;  // j is suffix
    int k = -1; // k is prefix
    while(j < pattern.length() - 1){
        if (k == -1 || pattern.charAt(j) == pattern.charAt(k)){
          next[++j] = ++k;
          // an improvement of getNextArray is updating next[] by setting next[j] = next[k]
          // highlevel similiarly performance
        }
        else{
          k = next[k];
        }
    }
    return next;
  }
}
