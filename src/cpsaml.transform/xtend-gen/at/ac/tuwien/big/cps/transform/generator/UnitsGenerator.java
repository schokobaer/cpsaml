package at.ac.tuwien.big.cps.transform.generator;

import at.ac.tuwien.big.cps.transform.util.FileUtils;
import at.ac.tuwien.big.cps.transform.util.NamingUtil;
import com.google.common.collect.Iterables;
import cpsaml.Cps;
import cpsaml.Resource;
import cpsaml.Sensor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class UnitsGenerator {
  public void generate(final String path, final Cps cps) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(path);
    _builder.append("/");
    String _clazz = NamingUtil.toClazz(cps.getName());
    _builder.append(_clazz);
    _builder.append("Units.sysml");
    FileUtils.write(_builder.toString(), this.generateCode(cps));
  }
  
  private CharSequence generateCode(final Cps cps) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _name = NamingUtil.toName(cps.getName());
    _builder.append(_name);
    _builder.append("Units {");
    _builder.newLineIfNotEmpty();
    {
      EList<Resource> _resources = cps.getResources();
      for(final Resource r : _resources) {
        _builder.append("\t");
        _builder.append("attribute def ");
        String _name_1 = NamingUtil.toName(r.getName());
        _builder.append(_name_1, "\t");
        _builder.append(" { }");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      final Function1<Sensor, EList<Resource>> _function = (Sensor it) -> {
        return it.getResources();
      };
      Iterable<Resource> _flatMap = IterableExtensions.<Sensor, Resource>flatMap(Iterables.<Sensor>filter(cps.getHardware(), Sensor.class), _function);
      for(final Resource r_1 : _flatMap) {
        _builder.append("\t");
        _builder.append("attribute def ");
        String _name_2 = NamingUtil.toName(r_1.getName());
        _builder.append(_name_2, "\t");
        _builder.append(" { }");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
