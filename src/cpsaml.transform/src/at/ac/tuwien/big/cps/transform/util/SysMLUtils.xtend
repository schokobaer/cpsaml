package at.ac.tuwien.big.cps.transform.util

import cpsaml.Command
import cpsaml.Resource

import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*

class SysMLUtils {
	
	static def toComment(String comment) {
		return comment.replace('\n', '\n* ')
	}
	
	static def generateDef(Resource p)
	'''attribute def «p.name.toName»;'''
	
	static def generateUsage(Resource p)
	'''attribute «p.name.toVar»: «p.name.toName»;'''
	
	static def generateDef(Command cmd)
	'''action def «cmd.name.toFirstUpper.toName»();'''
	
	static def generatePerform(Command cmd)
	'''perform action «cmd.name.toVar»: «cmd.name.toFirstUpper.toName»;'''
	
	
}