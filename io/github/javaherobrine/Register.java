package io.github.javaherobrine;
import java.util.*;
/**
 * A class that warps a useful data structure:
 * It acts like a map(with the simplest function), but with an extra,unique id
 * When you put your key, you'll get id
 * And you can query the value with both key and id, query key or id with the other one
 * This structure should be used if you are interested in the order the elements were put
 * Or you just don't care about the name(usually,to process an integer is faster than a string)
 * @author Java_Herobrine 
 */
public class Register<T>{
	private ArrayList<T> array=new ArrayList<>();
	private HashMap<String,T> map=new HashMap<>();
	private int id=0;
	public ArrayList<T> array(){
		return array;
	}
	public HashMap<String,T> map(){
		return map;
	}
	public int size() {
		return id;
	}
	public int put(String key,T value) {
		array.add(value);
		map.put(key,value);
		++id;
		return id-1;
	}
}
