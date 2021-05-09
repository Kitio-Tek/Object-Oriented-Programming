package mountainhuts;

public class AltitudeRanges {
private int minValue,maxValue;
private String Range;

public AltitudeRanges(String Range) {
	this.Range=Range;
	String[] range=Range.split("-");
	minValue=Integer.parseInt(range[0]);
	maxValue=Integer.parseInt(range[1]);
}
public boolean includes(int tmp) {
	if(tmp>=minValue && tmp<=maxValue) return true;
	return false;
}
public String getRange() {
	return Range;
}
 
	
}
