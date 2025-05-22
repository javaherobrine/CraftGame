package io.github.javaherobrine;

import java.util.LinkedList;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Oops, java.util.LinkedList doesn't support splice() <br />
 * what's worse, their fields and inner classes are all private! <br />
 * use reflection to crack them!
 * 
 * @author Java_Herobrine
 * @see java.util.LinkedList.Node
 */
public final class LinkedListSplicer {
	private LinkedListSplicer() {
	}

	public static final long LINKEDLIST_NODE_PREV;
	public static final long LINKEDLIST_NODE_NEXT;
	public static final long LINKEDLIST_NODE_ELEMENT;
	public static final long LINKEDLIST_HEAD;
	public static final long LINKEDLIST_TAIL;
	public static final long LINKEDLIST_SIZE;// They are all offsets
	private static final Unsafe UNSAFE;
	static {
		Class<?> clazz = LinkedList.class;
		Class<?>[] whereNodeIsIn = clazz.getDeclaredClasses();
		Class<?> res = void.class;
		for (int i = 0; i < whereNodeIsIn.length; i++) {
			if (whereNodeIsIn[i].getName().equals("java.util.LinkedList$Node")) {
				res = whereNodeIsIn[i];
			}
		}
		Field prev = null, next = null, head = null, tail = null, element = null, size = null;
		Unsafe u = null;
		try {
			prev = res.getDeclaredField("prev");
			next = res.getDeclaredField("next");
			element = res.getDeclaredField("item");
			tail = clazz.getDeclaredField("last");
			head = clazz.getDeclaredField("first");
			size = clazz.getDeclaredField("size");
			Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
			unsafe.setAccessible(true);
			u = (Unsafe) unsafe.get(null);
		} catch (Exception e) {
		}
		UNSAFE = u;
		LINKEDLIST_NODE_PREV = UNSAFE.objectFieldOffset(prev);
		LINKEDLIST_NODE_NEXT = UNSAFE.objectFieldOffset(next);
		LINKEDLIST_NODE_ELEMENT = UNSAFE.objectFieldOffset(element);
		LINKEDLIST_HEAD = UNSAFE.objectFieldOffset(head);
		LINKEDLIST_TAIL = UNSAFE.objectFieldOffset(tail);
		LINKEDLIST_SIZE = UNSAFE.objectFieldOffset(size);
	}

	/**
	 * equivalent to l1.splice(l2) if LinkedList supports splice <br />
	 * and l2 will be cleared
	 * 
	 * @param <T> type
	 * @param l1  the first linked list
	 * @param l2  the second linked list
	 * @author Java_Herobrine
	 */
	public static <T> void splice(LinkedList<T> l1, LinkedList<T> l2) {
		Object tail = UNSAFE.getObject(l1, LINKEDLIST_TAIL);
		if (tail == null) {
			UNSAFE.getAndSetObject(l1, LINKEDLIST_HEAD, UNSAFE.getObject(l2, LINKEDLIST_HEAD));
			UNSAFE.getAndSetInt(l1, LINKEDLIST_SIZE, UNSAFE.getInt(l2, LINKEDLIST_SIZE));
			UNSAFE.getAndSetObject(l1, LINKEDLIST_TAIL, UNSAFE.getObject(l2, LINKEDLIST_TAIL));
			return;
		}
		Object head = UNSAFE.getObject(l2, LINKEDLIST_HEAD);
		if (head == null) {
			return;
		}
		UNSAFE.getAndSetObject(tail, LINKEDLIST_NODE_NEXT, head);
		UNSAFE.getAndSetObject(head, LINKEDLIST_NODE_PREV, tail);
		UNSAFE.getAndSetObject(l1,LINKEDLIST_TAIL,UNSAFE.getAndSetObject(l2,LINKEDLIST_TAIL,null));
		UNSAFE.getAndSetObject(l2, LINKEDLIST_HEAD, null);
		UNSAFE.getAndSetInt(l1, LINKEDLIST_SIZE,
				UNSAFE.getInt(l1, LINKEDLIST_SIZE) + UNSAFE.getAndSetInt(l2, LINKEDLIST_SIZE, 0));
	}
}
