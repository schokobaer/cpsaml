package at.ac.tuwien.cps.codegen.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import at.ac.tuwien.cps.codegen.util.FileUtils;

public class TemplateFileProcessor {

	private Set<String> extensions = new HashSet<>();
	private Map<String, String> expressions = new HashMap<>();
	
	public TemplateFileProcessor addFileExtensionSupport(String extension) {
		extensions.add(extension);
		return this;
	}
	
	public TemplateFileProcessor setExpression(String pattern, String value) {
		expressions.put(pattern, value);
		return this;
	}
	
	public void process(String source, String dest) throws IOException {
		String content = Files.readString(Paths.get(source));
		
		// Fast way
		boolean inPattern = false;
		int start = 0;
		int end = 0;
		int line = 1;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '\n') {
				line++;
			}
			if (inPattern) {
				if (content.charAt(i) == '»') {
					String pattern = content.substring(start, i);
					if (!expressions.containsKey(pattern)) {
						throw new RuntimeException("No expression defined for pattern " + pattern + " in line " + line + " in file " + source);
					}
					builder.append(expressions.get(pattern));
					end = i + 1;
					inPattern = false;
				}
			} else {
				if (content.charAt(i) == '«') {
					builder.append(content.substring(end, i));
					inPattern = true;
					start = i + 1;
				}
			}
		}
		builder.append(content.substring(end));
		FileUtils.write(dest, builder);
		
		// Slow way
		/*
		for (Map.Entry<String, String> kv: expressions.entrySet()) {
			content = content.replace("�" + kv.getKey() + "�", kv.getValue());
		}
		FileUtils.write(dest, content);
		*/
	}
	
	public void deepCopy(String source, String dest, boolean inRoot) throws IOException {
		deepCopy(new File(source), new File(dest), inRoot);
	}
	
	public void deepCopy(File source, File dest, boolean inRoot) throws IOException {
		if (source.isFile()) {
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			if (extensions.stream().anyMatch(e -> source.getName().endsWith(e))) {
				process(source.getAbsolutePath(), dest.getAbsolutePath() + "/" + source.getName());
			} else {
				Files.copy(Path.of(source.toURI()), Path.of(new File(dest.getAbsoluteFile() + "/" + source.getName()).toURI()), StandardCopyOption.REPLACE_EXISTING);
			}
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
}
