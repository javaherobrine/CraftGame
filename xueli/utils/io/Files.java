package xueli.utils.io;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import io.github.javaherobrine.GameUtils;
import io.github.javaherobrine.experimental.LinkedListSplicer;

/**
 * Must be noted that resources packed in jar must be included in --class-path
 */

public final class Files {//modification: make this class final in hope of more inlining (efficiency)

	private Files() {}//addition: avoid users to create object of this class
	
	public static void fileOutput(String name, String content) throws IOException {
		fileOutput(name, content.getBytes(StandardCharsets.UTF_8));

	}

	public static void fileOutput(String name, byte[] bytes) throws IOException {
		File file = new File(name);
		if (!file.exists())
			file.createNewFile();

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		out.write(bytes);
		out.flush();
		out.close();
	}

	public static void fileOutput(String name, int[] bytes) throws IOException {
		File file = new File(name);
		if (!file.exists())
			file.createNewFile();

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		DataOutputStream stream = new DataOutputStream(out);
		for (int i = 0; i < bytes.length; i++) {
			stream.writeInt(bytes[i]);
		}
		out.flush();
		out.close();
	}

	public static void mkDir(String path) {
		new File(path).mkdirs();

	}
	
	public static String readAllString(File file) throws IOException {//modification use an existing implementation
		return GameUtils.ofFile(file);
	}

	public static byte[] readAllByte(File file) throws IOException {//modification: use readAllBytes() function to replace available()
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		byte[] data = in.readAllBytes();
		in.close();
		return data;
	}
	
	public static LinkedList<File> getAllFiles(File file,FileFilter filter){//addition: allow filters
		LinkedList<File> files = new LinkedList<>();
		
		if(file.isDirectory()) {
			File[] allFiles = file.listFiles(filter);
			for(int i = 0; i < allFiles.length; i++) {
				File f=allFiles[i];
				if(f.isFile()) {
					files.add(f);
					continue;
					
				}
				
				LinkedListSplicer.splice(files, getAllFiles(f,filter));
				
			}
			
		}else if(file.isFile()) {
			if(filter.accept(file)) {
				files.add(file);
			}
			
		}
		
		return files;
	}
	
	public static LinkedList<File> getAllFiles(String path,FileFilter filter) {//addition: allow filters
		return getAllFiles(new File(path),filter);
	}
	
	public static LinkedList<File> getAllFiles(File file) {
		LinkedList<File> files = new LinkedList<>();

		if (file.isDirectory()) {
			File[] allFiles = file.listFiles();
			for (int i = 0; i < allFiles.length; i++) {
				File f = allFiles[i];
				if(f.isFile()) {
					files.add(f);
					continue;
				}
				
				LinkedListSplicer.splice(files, getAllFiles(f));

			}

		} else if (file.isFile()) {
			files.add(file);

		}

		return files;
	}

	public static LinkedList<File> getAllFiles(String path) {
		return getAllFiles(new File(path));
	}

	public static int[] readImageAndReturnRawData(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] data = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), data, 0, image.getWidth());
		return data;
	}
	//deletion: an incorrect implementation
	public static InputStream getResourcePackedInJarStream(String path) {
		return Files.class.getResourceAsStream(path);
	}

	public static byte[] readResourcePackedInJar(String path) throws IOException {
		InputStream in = Files.class.getResourceAsStream(path);
		if (in == null)
			throw new IOException("Stream is null! Maybe the file doesn't exist?");
		byte[] all = in.readAllBytes();
		in.close();
		return all;
	}

	private static ArrayList<ByteBuffer> stacks = new ArrayList<>();

	public static ByteBuffer readResourcePackedInJarAndPackedToBuffer(String path) throws IOException {
		byte[] all = readResourcePackedInJar(path);
		ByteBuffer buffer = BufferUtils.createByteBuffer(all.length);
		stacks.add(buffer);
		return buffer.put(all).flip();
	}

	public static String readResourcePackedInJarAndPackedToString(String path) throws IOException {
		byte[] all = readResourcePackedInJar(path);
		return new String(all);
	}

	public static void writeObject(Object obj, File file) throws Exception {
		FileOutputStream out = new FileOutputStream(file);
		ObjectOutputStream oo = new ObjectOutputStream(out);
		oo.writeObject(obj);
		oo.flush();
		oo.close();
	}

	public static Object readObject(File file) throws Exception {
		FileInputStream in = new FileInputStream(file);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	public static String getNameExcludeSuffix(String fileName) {
		String dest = fileName;
		int lastIndex = dest.lastIndexOf('.');
		if (lastIndex != -1)
			dest = dest.substring(0, lastIndex);
		int lastIndexSeparator = dest.lastIndexOf(File.separatorChar);
		if (lastIndexSeparator != -1)
			dest = dest.substring(lastIndexSeparator + 1);
		return dest;
	}

	public static String getFileExtension(String path) {
		String fileName = new File(path).getName();
		int index = fileName.lastIndexOf('.');
		return (index == -1) ? "" : fileName.substring(index + 1);
	}

}
