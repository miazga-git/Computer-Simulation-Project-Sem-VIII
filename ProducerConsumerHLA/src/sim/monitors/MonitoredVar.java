package sim.monitors;

/**
 * Description...
 * 
 * @author Dariusz Pierzchala
 *
 */
public class MonitoredVar {

	private Histogram histogram;
	private ChangesList changes;
	private double actualVal;

	public MonitoredVar() {
		this.changes = new ChangesList();
	}

	public MonitoredVar(double value) {
		this.actualVal = value;
		this.changes = new ChangesList();
		this.changes.add(new Change(value));
	}

	public void setValue(double newValue) {
		this.changes.add(new Change(newValue));
		this.actualVal = newValue;
	}

	public void setValue(double newValue, double simtime) {
		this.changes.add(new Change(newValue, simtime));
		this.actualVal = newValue;
	}

	public double getValue() {
		return actualVal;
	}

	public Histogram getHistogram() {
		if (histogram == null)
			histogram = new Histogram(this);
		return histogram;
	}

	public ChangesList getChanges() {
		return changes;
	}

	public int numberOfSamples() {
		return changes.size();
	}

}