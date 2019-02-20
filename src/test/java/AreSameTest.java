import static org.junit.Assert.*;
import org.junit.Test;


public class AreSameTest {

    @Test
    public void test1() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertEquals(true, AreSame.comp(a, b));
    }
    @Test
    public void test2() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{11 * 11, 121 * 121, 144 * 144, 190 * 190, 161 * 161, 19 * 19, 144 * 144, 19 * 19};
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test3() {
        int[] a = new int[]{};
        int[] b = new int[]{1};
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test4() {
        int[] a = new int[]{};
        int[] b = new int[]{};
        assertEquals(true, AreSame.comp(a, b));
    }
    @Test
    public void test5() {
        int[] a = new int[]{};
        int[] b = null;
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test6() {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11, 1008};
        int[] b = {11 * 11, 121 * 121, 144 * 144, 190 * 190, 161 * 161, 19 * 19, 144 * 144, 19 * 19};
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test7() {
        int[] a = new int[]{121, 1440, 191, 161, 19, 144, 195, 11};
        int[] b = {11 * 11, 121 * 121, 1440 * 1440, 191 * 191, 161 * 161, 19 * 19, 144 * 144, 195 * 195};
        assertEquals(true, AreSame.comp(a, b));
    }
    @Test
    public void test8() {
        int[] a = new int[]{0, -14, 191, 161, 19, 144, 195, 1};
        int[] b = {1, 0, 14 * 14, 191 * 191, 161 * 161, 19 * 19, 144 * 144, 195 * 195};
        assertEquals(true, AreSame.comp(a, b));
    }
    @Test
    public void test9() {
        int[] a = new int[]{0, -14, 191, 161, 19, 144, 195, 1, 2};
        int[] b = {1, 0, 14 * 14, 191 * 191, 161 * 161, 19 * 19, 144 * 144, 195 * 195, 3};
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test10() {
        int[] a = new int[]{2, 2, 3};
        int[] b = {4, 9, 9};
        assertEquals(false, AreSame.comp(a, b));
    }
    @Test
    public void test1a() {
        int[] a = new int[]{2, 2, 3};
        int[] b = {4, 4, 9};
        assertEquals(true, AreSame.comp(a, b));
    }
}