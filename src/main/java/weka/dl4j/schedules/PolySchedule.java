package weka.dl4j.schedules;

import java.util.Enumeration;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import weka.core.Option;
import weka.core.OptionMetadata;

/**
 * Polynomial decay schedule for learning rates.
 *
 * @author Steven Lang
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class PolySchedule extends Schedule<org.nd4j.linalg.schedule.PolySchedule> {


  private static final long serialVersionUID = 1550520224115867571L;
  private double power = 1;
  private int maxIter = 10;

  @OptionMetadata(
    displayName = "maxIter",
    description = "The maxIter value (default = 10). Should be set to numEpochs.",
    commandLineParamName = "maxIter",
    commandLineParamSynopsis = "-maxIter <maxIter>",
    displayOrder = 2
  )
  public int getMaxIter() {
    return maxIter;
  }

  public void setMaxIter(int maxIter) {
    this.maxIter= maxIter;
  }

  @OptionMetadata(
    displayName = "power",
    description = "The power value (default = 1.0).",
    commandLineParamName = "power",
    commandLineParamSynopsis = "-power <double>",
    displayOrder = 3
  )
  public double getPower() {
    return power;
  }

  public void setPower(double power) {
    this.power = power;
  }

  @Override
  public void initializeBackend() {
    backend = new org.nd4j.linalg.schedule.PolySchedule(scheduleType.getBackend(), initialValue, power, maxIter);
  }

  @Override
  public void setBackend(org.nd4j.linalg.schedule.PolySchedule newBackend) {
    this.maxIter= newBackend.getMaxIter();
    this.power = newBackend.getPower();
    this.initialValue = newBackend.getInitialValue();
    this.scheduleType = ScheduleType.fromBackend(newBackend.getScheduleType());
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {
    return Option.listOptionsForClassHierarchy(this.getClass(),super.getClass()).elements();
  }

  /**
   * Gets the current settings of the Classifier.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    return Option.getOptionsForHierarchy(this, super.getClass());
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  public void setOptions(String[] options) throws Exception {
    Option.setOptionsForHierarchy(options, this, super.getClass());
  }

}
