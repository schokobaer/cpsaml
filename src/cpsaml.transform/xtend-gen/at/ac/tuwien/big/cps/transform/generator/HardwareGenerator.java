package at.ac.tuwien.big.cps.transform.generator;

import at.ac.tuwien.big.cps.transform.util.FileUtils;
import at.ac.tuwien.big.cps.transform.util.NamingUtil;
import at.ac.tuwien.big.cps.transform.util.SysMLUtils;
import cpsaml.Actuator;
import cpsaml.Command;
import cpsaml.Hardware;
import cpsaml.Resource;
import cpsaml.Sensor;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HardwareGenerator {
  public void generate(final String path, final Hardware hw) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(path);
    _builder.append("/");
    String _clazz = NamingUtil.toClazz(hw.getName());
    _builder.append(_clazz);
    _builder.append(".sysml");
    FileUtils.write(_builder.toString(), this.generateCode(hw));
  }
  
  private CharSequence _generateCode(final Sensor sensor) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _name = NamingUtil.toName(sensor.getName());
    _builder.append(_name);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("private import Cps::RosNodeAnnotation;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<Resource> _resources = sensor.getResources();
      for(final Resource r : _resources) {
        {
          boolean _notEmpty = NamingUtil.notEmpty(r.getDescription());
          if (_notEmpty) {
            _builder.append("        ");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("        ");
            _builder.append(" ");
            _builder.append("* ");
            String _comment = SysMLUtils.toComment(r.getDescription());
            _builder.append(_comment, "         ");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
          }
        }
        _builder.append("        ");
        CharSequence _generateDef = SysMLUtils.generateDef(r);
        _builder.append(_generateDef, "        ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    {
      boolean _notEmpty_1 = NamingUtil.notEmpty(sensor.getDescription());
      if (_notEmpty_1) {
        _builder.append("\t    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t    ");
        _builder.append(" ");
        _builder.append("* ");
        String _comment_1 = SysMLUtils.toComment(sensor.getDescription());
        _builder.append(_comment_1, "\t     ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("part def ");
    String _name_1 = NamingUtil.toName(sensor.getName());
    _builder.append(_name_1, "\t\t");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("@RosNodeAnnotation;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    {
      EList<Resource> _resources_1 = sensor.getResources();
      for(final Resource p : _resources_1) {
        _builder.append("\t        ");
        CharSequence _generateUsage = SysMLUtils.generateUsage(p);
        _builder.append(_generateUsage, "\t        ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence _generateCode(final Actuator actuator) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _name = NamingUtil.toName(actuator.getName());
    _builder.append(_name);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("private import Cps::RosNodeAnnotation;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<Command> _commands = actuator.getCommands();
      for(final Command a : _commands) {
        {
          boolean _notEmpty = NamingUtil.notEmpty(a.getDescription());
          if (_notEmpty) {
            _builder.append("\t    ");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("\t    ");
            _builder.append(" ");
            _builder.append("* ");
            String _comment = SysMLUtils.toComment(a.getDescription());
            _builder.append(_comment, "\t     ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t    ");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
          }
        }
        _builder.append("\t    ");
        CharSequence _generateDef = SysMLUtils.generateDef(a);
        _builder.append(_generateDef, "\t    ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.newLine();
      }
    }
    _builder.append("\t    ");
    _builder.newLine();
    {
      boolean _notEmpty_1 = NamingUtil.notEmpty(actuator.getDescription());
      if (_notEmpty_1) {
        _builder.append("\t    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t    ");
        _builder.append(" ");
        _builder.append("* ");
        String _comment_1 = SysMLUtils.toComment(actuator.getDescription());
        _builder.append(_comment_1, "\t     ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("part def ");
    String _name_1 = NamingUtil.toName(actuator.getName());
    _builder.append(_name_1, "\t\t");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("@RosNodeAnnotation;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t        ");
    _builder.newLine();
    {
      EList<Command> _commands_1 = actuator.getCommands();
      for(final Command a_1 : _commands_1) {
        _builder.append("\t\t\t");
        CharSequence _generatePerform = SysMLUtils.generatePerform(a_1);
        _builder.append(_generatePerform, "\t\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCode(final Hardware actuator) {
    if (actuator instanceof Actuator) {
      return _generateCode((Actuator)actuator);
    } else if (actuator instanceof Sensor) {
      return _generateCode((Sensor)actuator);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(actuator).toString());
    }
  }
}
