package at.ac.tuwien.cps.codegen.generator.catkinws;

import at.ac.tuwien.cps.codegen.model.Part;
import at.ac.tuwien.cps.codegen.util.FileUtils;
import at.ac.tuwien.cps.codegen.util.NamingUtil;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class StartupScriptGenerator {
  public void generate(final String path, final Part part, final boolean singleton) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(path);
    _builder.append("/");
    String _low = NamingUtil.toLow(part.getName());
    _builder.append(_low);
    _builder.append(".sh");
    FileUtils.write(_builder.toString(), NamingUtil.newLineFix(this.generateContent(part, singleton)));
  }
  
  private CharSequence generateContent(final Part part, final boolean singleton) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#!/bin/bash");
    _builder.newLine();
    _builder.append("/opt/ros/kinetic/setup.bash");
    _builder.newLine();
    _builder.append("source devel/setup.bash");
    _builder.newLine();
    _builder.append("echo \"setup finished\"");
    _builder.newLine();
    _builder.append("roscore > /dev/null &");
    _builder.newLine();
    _builder.append("sleep 3");
    _builder.newLine();
    _builder.append("echo \"roscore started\"");
    _builder.newLine();
    _builder.append("rosparam set /rabbit_host \"$1\"");
    _builder.newLine();
    _builder.append("echo \"rabbit_host param set to $1\"");
    _builder.newLine();
    _builder.append("roslaunch ");
    String _low = NamingUtil.toLow(part.getName());
    _builder.append(_low);
    _builder.append(" ");
    String _low_1 = NamingUtil.toLow(part.getName());
    _builder.append(_low_1);
    _builder.append(".launch");
    {
      if ((!singleton)) {
        _builder.append(" ");
        String _low_2 = NamingUtil.toLow(part.getName());
        _builder.append(_low_2);
        _builder.append("_id:=$2");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
