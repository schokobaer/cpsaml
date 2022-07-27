package at.ac.tuwien.big.cps.validation;

import at.ac.tuwien.big.cps.util.NameUtil;
import at.ac.tuwien.big.cps.validation.AbstractCpsamlValidator;
import com.google.common.collect.Iterables;
import cpsaml.CpsSystem;
import cpsaml.CpsamlPackage;
import cpsaml.MultiCps;
import cpsaml.SingletonCps;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class CpsamlValidator extends AbstractCpsamlValidator {
  public static final String INVALID_NAME = "invalidName";
  
  @Check
  public void distinctCpsIdentifiers(final MultiCps cps) {
    boolean _isEmpty = cps.getInstances().isEmpty();
    if (_isEmpty) {
      return;
    }
    EObject _eContainer = cps.eContainer();
    final Function1<SingletonCps, Boolean> _function = (SingletonCps sc) -> {
      final Function1<String, Boolean> _function_1 = (String i) -> {
        return Boolean.valueOf(NameUtil.toIdentifier(i).equals(NameUtil.toIdentifier(sc.getName())));
      };
      return Boolean.valueOf(IterableExtensions.<String>exists(cps.getInstances(), _function_1));
    };
    final Consumer<SingletonCps> _function_1 = (SingletonCps it) -> {
      this.error("Names must be unique", CpsamlPackage.Literals.DESCRIBED_NAMED_ELEMENT__NAME, CpsamlValidator.INVALID_NAME);
    };
    IterableExtensions.<SingletonCps>filter(Iterables.<SingletonCps>filter(((CpsSystem) _eContainer).getCps(), SingletonCps.class), _function).forEach(_function_1);
    EObject _eContainer_1 = cps.eContainer();
    final Function1<MultiCps, Boolean> _function_2 = (MultiCps it) -> {
      return Boolean.valueOf((it != cps));
    };
    final Function1<MultiCps, Boolean> _function_3 = (MultiCps mc) -> {
      final Function1<String, String> _function_4 = (String it) -> {
        return NameUtil.toIdentifier(it);
      };
      final Function1<String, Boolean> _function_5 = (String it) -> {
        final Function1<String, String> _function_6 = (String it_1) -> {
          return NameUtil.toIdentifier(it_1);
        };
        return Boolean.valueOf(ListExtensions.<String, String>map(cps.getInstances(), _function_6).contains(it));
      };
      return Boolean.valueOf(IterableExtensions.<String>exists(ListExtensions.<String, String>map(mc.getInstances(), _function_4), _function_5));
    };
    final Consumer<MultiCps> _function_4 = (MultiCps it) -> {
      this.error("Names must be unique", CpsamlPackage.Literals.SPECIFIC_INSTANCES_ELEMENT__INSTANCES, CpsamlValidator.INVALID_NAME);
    };
    IterableExtensions.<MultiCps>filter(IterableExtensions.<MultiCps>filter(Iterables.<MultiCps>filter(((CpsSystem) _eContainer_1).getCps(), MultiCps.class), _function_2), _function_3).forEach(_function_4);
  }
}
