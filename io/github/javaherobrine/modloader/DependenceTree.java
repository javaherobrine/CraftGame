package io.github.javaherobrine.modloader;
import java.util.*;
/**
 * Dependence tree for mods
 * To load mods in a correct order(the dependence items should be loaded previously)
 * If dependence items missing dependence relations forming a circle,throw an exception and terminate
 */
public class DependenceTree {
	private static final DependenceTree TREE=new DependenceTree();
	public static DependenceTree getTree() {
		return TREE;
	}
	public static class Node{
		ArrayList<Node> children;
		Node father;
		Node(){
			father=null;
			children=new ArrayList<Node>();
		}
	}
}
