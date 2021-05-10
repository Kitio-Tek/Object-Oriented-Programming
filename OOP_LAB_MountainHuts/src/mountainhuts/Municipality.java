package mountainhuts;

/**
 * Represents a municipality
 *
 */
public class Municipality {
  private String name,province,AltitudeRange;
  private Integer altitude;
	

	public Municipality(String name, String province, Integer altitude) {
		
		this.name = name;
		this.province = province;
		this.altitude = altitude;
		
	}

	public void setAltitudeRange(String altitudeRange) {
		AltitudeRange = altitudeRange;
	}

	/**
	 * Name of the municipality.
	 * 
	 * Within a region the name of a municipality is unique
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public String getAltitudeRange() {
		return AltitudeRange;
	}

	/**
	 * Province of the municipality
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Altitude of the municipality
	 * 
	 * @return altitude
	 */
	public Integer getAltitude() {
		return altitude;
	}

}
