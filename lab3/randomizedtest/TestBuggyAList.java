package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> L = new AListNoResizing<>();

    int N = 500;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        System.out.println("addLast(" + randVal + ")");
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        System.out.println("size: " + size);
      }
      else if (operationNumber == 2 && L.size() > 0) {
        int r = L.getLast();
        System.out.println("getLast(): " + r);
      }
      else if (operationNumber == 3 && L.size() > 0) {
        int r = L.removeLast();
        System.out.println("removeLast(): " + r);
      }
    }
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> correct = new AListNoResizing<>();
    BuggyAList<Integer> broken = new BuggyAList<>();

    int N = 500000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        correct.addLast(randVal);
        broken.addLast(randVal);
        System.out.println("addLast(" + randVal + ")");
        assertEquals(correct.getLast(), broken.getLast());
      } else if (operationNumber == 1) {
        // size
        int sizeCorrct = correct.size();
        int sizeBroken = broken.size();
        System.out.println("size: " + sizeCorrct);
        assertEquals(sizeCorrct, sizeBroken);
      }
      else if (operationNumber == 2) {
        int correctLast = 0;
        int brokenLast = 0;
        assertEquals(correct.size() > 0, broken.size() > 0);
        if(correct.size() > 0)
          correctLast = correct.getLast();
        if(broken.size() > 0)
          brokenLast = broken.getLast();
        assertEquals(correctLast, brokenLast);
        System.out.println("getLast(" + correctLast + ")");
      }
      else if (operationNumber == 3) {
        int correctLast = 0;
        int brokenLast = 0;
        assertEquals(correct.size() > 0, broken.size() > 0);
        if(correct.size() > 0)
          correctLast = correct.removeLast();
        if(broken.size() > 0)
          brokenLast = broken.removeLast();
        assertEquals(correctLast, brokenLast);
        System.out.println("removeLast(: " + correctLast + ")");
      }
    }
  }
}
