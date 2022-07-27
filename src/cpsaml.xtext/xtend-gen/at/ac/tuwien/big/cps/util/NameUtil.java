package at.ac.tuwien.big.cps.util;

import cpsaml.Cps;
import cpsaml.MultiCps;

@SuppressWarnings("all")
public class NameUtil {
  public static String toIdentifier(final String name) {
    return name.replace(" ", "").toLowerCase();
  }
  
  public static boolean hasSpecificInstances(final Cps cps) {
    return ((cps instanceof MultiCps) && (!((MultiCps) cps).getInstances().isEmpty()));
  }
}
