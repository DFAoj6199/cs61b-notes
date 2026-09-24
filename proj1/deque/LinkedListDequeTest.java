package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    /** Tests the get method, including boundary cases. (你原本的测试里没有 get 的全面测试) */
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad1.addLast(i);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals("Should have the same value", Integer.valueOf(i), ad1.get(i));
        }

        // 测试越界
        assertNull("get(-1) should return null", ad1.get(-1));
        assertNull("get(10) should return null", ad1.get(10));
    }

    @Test
    /**
     * 专门针对环形数组的测试：强制指针绕回
     * 你原本的链表测试不需要这个，但环形数组如果没有正确取模，这里必挂
     */
    public void circularWrapTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // 加满前 8 个元素（初始数组长度 8）
        for (int i = 0; i < 8; i++) {
            ad1.addLast(i); // 0 1 2 3 4 5 6 7
        }

        // 删掉前 4 个，此时 nextHead 指针会向后移动
        for (int i = 0; i < 4; i++) {
            ad1.removeFirst(); // 剩余 4 5 6 7
        }

        // 再加 4 个，此时 nextTail 指针会绕回到数组开头
        for (int i = 0; i < 4; i++) {
            ad1.addLast(i + 100); // 剩余 4 5 6 7 100 101 102 103
        }

        // 验证顺序
        assertEquals(8, ad1.size());
        assertEquals(Integer.valueOf(4), ad1.get(0));
        assertEquals(Integer.valueOf(7), ad1.get(3));
        assertEquals(Integer.valueOf(100), ad1.get(4));
        assertEquals(Integer.valueOf(103), ad1.get(7));
    }

    @Test
    /**
     * 测试扩容和缩容
     * 比原本的 100万 缩小到 10万，既能触发多次扩容，又不会让电脑内存爆炸
     */
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        // 10万次会触发数组翻倍扩容
        for (int i = 0; i < 100000; i++) {
            ad1.addLast(i);
        }
        assertEquals(100000, ad1.size());

        for (double i = 0; i < 50000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 99999; i >= 50000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }

        // 此时元素清空，会触发我们之前写的 25% 缩容逻辑
        assertEquals(0, ad1.size());
        assertTrue(ad1.isEmpty());
    }
}
