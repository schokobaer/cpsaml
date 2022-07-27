/*
 * generated by Xtext 2.22.0
 */
package at.ac.tuwien.big.cps.ide;

import at.ac.tuwien.big.cps.CpsamlRuntimeModule;
import at.ac.tuwien.big.cps.CpsamlStandaloneSetup;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class CpsamlIdeSetup extends CpsamlStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new CpsamlRuntimeModule(), new CpsamlIdeModule()));
	}
	
}