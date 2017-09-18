
package magicmirror.weather.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Currently {

	@SerializedName("time")
	@Expose
	private long time;
	@SerializedName("summary")
	@Expose
	private String summary;
	@SerializedName("icon")
	@Expose
	private String icon;
	@SerializedName("precipIntensity")
	@Expose
	private double precipIntensity;
	@SerializedName("precipProbability")
	@Expose
	private double precipProbability;
	@SerializedName("precipType")
	@Expose
	private String precipType;
	@SerializedName("temperature")
	@Expose
	private double temperature;
	@SerializedName("apparentTemperature")
	@Expose
	private double apparentTemperature;
	@SerializedName("dewPoint")
	@Expose
	private double dewPoint;
	@SerializedName("humidity")
	@Expose
	private double humidity;
	@SerializedName("pressure")
	@Expose
	private double pressure;
	@SerializedName("windSpeed")
	@Expose
	private double windSpeed;
	@SerializedName("windGust")
	@Expose
	private double windGust;
	@SerializedName("windBearing")
	@Expose
	private int windBearing;
	@SerializedName("cloudCover")
	@Expose
	private double cloudCover;
	@SerializedName("uvIndex")
	@Expose
	private int uvIndex;
	@SerializedName("visibility")
	@Expose
	private double visibility;
	@SerializedName("ozone")
	@Expose
	private double ozone;

	public long getTime() {
		return time;
	}

	public Date getDate() {
		return new Date(time * 1000);
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public double getPrecipIntensity() {
		return precipIntensity;
	}

	public void setPrecipIntensity(double precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipProbability() {
		return precipProbability;
	}

	public void setPrecipProbability(double precipProbability) {
		this.precipProbability = precipProbability;
	}

	public String getPrecipType() {
		return precipType;
	}

	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getApparentTemperature() {
		return apparentTemperature;
	}

	public void setApparentTemperature(double apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	public double getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(double dewPoint) {
		this.dewPoint = dewPoint;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getWindGust() {
		return windGust;
	}

	public void setWindGust(double windGust) {
		this.windGust = windGust;
	}

	public int getWindBearing() {
		return windBearing;
	}

	public void setWindBearing(int windBearing) {
		this.windBearing = windBearing;
	}

	public double getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(double cloudCover) {
		this.cloudCover = cloudCover;
	}

	public int getUvIndex() {
		return uvIndex;
	}

	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}

	public double getOzone() {
		return ozone;
	}

	public void setOzone(double ozone) {
		this.ozone = ozone;
	}

	public String getIconMapping() {
		String name = icon.replace("-", "_").toUpperCase();
		return WeatherIcons.valueOf(name).getIconIdentifier();
	}

	public String getSunState() {

		return null;
	}
}
