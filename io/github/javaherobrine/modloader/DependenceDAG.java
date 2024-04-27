package io.github.javaherobrine.modloader;
import java.util.*;
/**
 * Dependence DAG for mods
 * To load mods in a correct order(the dependence items should be loaded previously)(use topological sort)
 * If dependence items missing dependence relations forming a circle,throw an exception and terminate
 */
public class DependenceDAG {
	public static final Map<String,GraphNode> LOADED_MODS=new HashMap<>();
	public static GraphNode getNodeByID(String ID) {
		if(!LOADED_MODS.containsKey(ID)) {
			LOADED_MODS.put(ID,new GraphNode());
		}
		return LOADED_MODS.get(ID);
	}
	/**
	 * Node for building DAG
	 * Each node represents a mod
	 */
	public static class GraphNode{
		JarClassLoader info;
		ArrayList<GraphNode> linkto=new ArrayList<GraphNode>();
		ArrayList<GraphNode> linkfrom=new ArrayList<GraphNode>();
		int indegree=0;
		boolean enable=true;
		public void link(GraphNode to) {
			linkto.add(to);
			to.indegree+=1;
			to.linkfrom.add(this);
		}
		/**
		 * Enable a mod and its dependence items
		 */
		public void enable() {
			if(enable) {
				return;
			}
			enable=true;
			linkfrom.forEach(from->{
				from.enable();
			});
		}
		/**
		 * Disable a mod and mod(s) which depend(s) on it
		 */
		public void disable() {
			if(!enable) {
				return;
			}
			enable=false;
			linkto.forEach(to->{
				to.disable();
			});
		}
		/**
		 * Load mods
		 * @return true if mods are loaded properly
		 * false otherwise
		 */
		public boolean topologicalSort() {
			Queue<GraphNode> q=new LinkedList<GraphNode>();
			q.add(this);
			List<GraphNode> order=new LinkedList<>();
			while(!q.isEmpty()) {
				GraphNode current=q.poll();
				order.add(current);
				current.linkto.stream().filter(next->{//disabled mods won't be loaded
					return next.enable;
				}).forEach(next->{
					next.indegree-=1;
					if(next.indegree==0) {
						q.add(next);
					}
				});
			}
			Iterator<GraphNode> iter=order.iterator();
			while(iter.hasNext()) {
				GraphNode node=iter.next();
				for(int i=0;i<node.linkto.size();++i) {
					if(!node.linkto.get(i).enable) {
						continue;
					}
					if(node.linkto.get(i).indegree!=0) {
						return false;//circle
					}
				}
			}
			order.stream().forEach(node->{
				node.info.getInstance().load();
			});
			return true;
		}
	}
}
