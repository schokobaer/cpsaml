package at.ac.tuwien.big.cps.transform.util;

import java.util.stream.Stream
import java.util.stream.Collectors

class NamingUtil {
	
	static def String ws(String name) {
		return name.replace(" ", "")
	}
	
	/**
	 * to Class
	 */
	static def String c(String name) {
		return Stream.of(name.split(" "))
			.map[ it.toFirstUpper ]
			.collect(Collectors.joining)
	}
	
	/**
	 * to Constant
	 */
	static def String f(String name) {
		return ws(name).toUpperCase
	}
	
	/**
	 * to Variable
	 */
	static def String v(String name) {
		return Stream.of(name.split(" "))
			.map[ it.toFirstUpper ]
			.collect(Collectors.joining)
			.toFirstLower
	}
	
	/**
	 * to lower
	 */
	static def String l(String name) {
		return ws(name).toLowerCase
	}
	
	
	/* Extension methods */
	def static toConstant(String name) {
		f(name)
	}
	
	def static toClazz(String name) {
		c(name)
	}
	
	def static toVar(String name) {
		v(name)
	}
	
	def static toLow(String name) {
		l(name)
	}
	
	def static toPath(String name) {
		return name.replace(" ", "").toLowerCase
	}
	
	static def toName(String name) {
		return name.contains(' ') ? "'" + name + "'" : name
	}
	
	static def toPlural(String name) {
		// see https://github.com/atteo/evo-inflector
		return name.endsWith('s') ? name : name + 's'
	}
	
	static def notEmpty(String name) {
		return name !== null && !name.trim().empty
	}
}