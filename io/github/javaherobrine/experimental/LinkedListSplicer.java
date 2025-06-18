package io.github.javaherobrine.experimental;

import java.util.LinkedList;
import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import static java.lang.invoke.MethodHandles.Lookup;

/**
 * Oops, java.util.LinkedList doesn't support splice() <br />
 * what's worse, their fields and inner classes are all private! <br />
 * use reflection to crack them!
 * 
 * @deprecated Final solution is under discussion=
 * @author Java_Herobrine
 * @see java.util.LinkedList.Node
 */
public final class LinkedListSplicer {
	/**
	 * JNI implementation
	 * @param l1
	 * @param l2
	 */
	public static <T> void splice0(LinkedList<T> l1,LinkedList<T> l2) {
		if(l2==null) {
			return;
		}
		if(l1==null) {
			l1=l2;
			return;
		}
		splice(l1,l2,LIST,NODE);
	}
	private static native void splice(LinkedList<?> l1,LinkedList<?> l2,Class<?> list,Class<?> node);
	private LinkedListSplicer() {
	}

	public static final long LINKEDLIST_NODE_PREV;
	public static final long LINKEDLIST_NODE_NEXT;
	public static final long LINKEDLIST_NODE_ELEMENT;
	public static final long LINKEDLIST_HEAD;
	public static final long LINKEDLIST_TAIL;
	public static final long LINKEDLIST_SIZE;// They are all offsets
	private static final Unsafe UNSAFE;
	public static final Class<?> LIST;
	public static final Class<?> NODE;
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
		LIST=clazz;
		NODE=res;
	}

	/**
	 * equivalent to l1.splice(l2) if LinkedList supports splice <br />
	 * and l2 will be cleared <br />
	 * Unsafe implementation
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
		UNSAFE.getAndSetObject(l2, LINKEDLIST_HEAD, null);
		UNSAFE.getAndSetObject(l2, LINKEDLIST_TAIL, null);
		UNSAFE.getAndSetInt(l1, LINKEDLIST_SIZE,
				UNSAFE.getInt(l1, LINKEDLIST_SIZE) + UNSAFE.getAndSetInt(l2, LINKEDLIST_SIZE, 0));
	}
	/**
	 * discard this optimization
	 * @param <T>
	 * @param l1
	 * @param l2
	 */
	public static <T> void splice1(LinkedList<T> l1,LinkedList<T> l2) {
		l1.addAll(l2);
		l2.clear();
	}
	/**
	 * your implementation
	 * @param <T>
	 * @param l1
	 * @param l2
	 */
	public static <T> void splice2(LinkedList<T> l1,LinkedList<T> l2) {
		//TODO let LovelyZeeiam implement this method
	}
	/**
	 * VarHandle implementation(reflection)
	 * @param <T>
	 * @param l1
	 * @param l2
	 * @throws IllegalAccessException where parameter <code>--add-opens</code> works
	 * @throws NoSuchFieldException
	 */
	public static <T> void splice3(LinkedList<T> l1,LinkedList<T> l2) throws IllegalAccessException, NoSuchFieldException {
		if(l2==null||l2.isEmpty()) {
			return;
		}
		if(l1==null||l1.isEmpty()) {
			l1=l2;
			return;
		}
		Lookup lookup=MethodHandles.lookup();
		Lookup priv=MethodHandles.privateLookupIn(LIST, lookup);
		VarHandle head=priv.findVarHandle(LIST, "first", NODE);
		VarHandle tail=priv.findVarHandle(LIST, "last", NODE);
		VarHandle size=priv.findVarHandle(LIST, "size", int.class);
		Lookup priv0=MethodHandles.privateLookupIn(NODE, lookup);
		VarHandle prev=priv0.findVarHandle(NODE, "prev", NODE);
		VarHandle next=priv0.findVarHandle(NODE, "next", NODE);
		Object h=head.get(l2);
		Object t=tail.get(l1);
		prev.set(h,t);
		next.set(t,h);
		head.set(l2,null);
		tail.set(l2,null);
		size.set(l1,(int)size.get(l1)+(int)size.get(l2));
		size.set(l2,0);
	}
}
