package io.github.javaherobrine.render;
public class RenderQueue {
	public static class LinkedListNode{
		public VAO vao;
		private LinkedListNode prev,next;
		public void remove() {
			prev.next=next;
			next.prev=prev;
		}
		public void render() {}
	}
	private static class LinkedList{
		LinkedListNode NIL=new LinkedListNode();
		LinkedList(){
			NIL.prev=NIL;
			NIL.next=NIL;
		}
		void put(LinkedListNode node) {
			node.next=NIL.next;
			NIL.next.prev=node;
			node.prev=NIL;
			NIL.next=node;
		}
	}
}
