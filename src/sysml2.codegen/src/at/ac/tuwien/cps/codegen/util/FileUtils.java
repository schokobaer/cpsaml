package at.ac.tuwien.cps.codegen.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileUtils {

	public static void deepCopy(String source, String dest, boolean inRoot) throws IOException {
		deepCopy(new File(source), new File(dest), inRoot);
	}
	
	public static void deepCopy(File source, File dest, boolean inRoot) throws IOException {
		if (source.isFile()) {
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			Files.copy(Path.of(source.toURI()), Path.of(new File(dest.getAbsoluteFile() + "/" + source.getName()).toURI()), StandardCopyOption.REPLACE_EXISTING);
		} else {
			if (!inRoot) {
				dest = new File(dest.getAbsoluteFile() + "/" + source.getName());
				dest.mkdirs();
			}
			
			for (File f: source.listFiles()) {
				deepCopy(f, dest, false);
			}
		}
	}
	
	public static void copyFolder(Path src, Path dest) throws IOException {
	    try (Stream<Path> stream = Files.walk(src)) {
	        stream.forEach(source -> copy(source, dest.resolve(src.relativize(source))));
	    }
	}
	
	private static void copy(Path source, Path dest) {
	    try {
	        Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}
	
	public static void write(String path, CharSequence content) {
		try {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Files.writeString(Paths.get(path), content, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mkdir(String path) {
		try {
			Files.createDirectories(Paths.get(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean deleteDirectory(String directoryToBeDeleted) {
		File file = new File(directoryToBeDeleted);
		if (!deleteDirectoryContent(file)) {
			return false;
		}
		return file.delete();
	}
	
	public static boolean deleteDirectoryContent(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectoryContent(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
}
