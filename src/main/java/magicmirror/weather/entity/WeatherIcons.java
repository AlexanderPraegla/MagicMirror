package magicmirror.weather.entity;

public enum WeatherIcons {
	CLEAR_DAY("wi-day-sunny"),
	CLEAR_NIGHT("wi-night-clear"),
	RAIN("wi-rain"),
	SNOW("wi-snow"),
	SLEET("wi-sleet"),
	WIND("wi-strong-wind"),
	FOG("wi-fog"),
	CLOUDY("wi-cloudy"),
	PARTLY_CLOUDY_DAY("wi-day-cloudy"),
	PARTLY_CLOUDY_NIGHT("wi-night-cloudy"),
	HAIL("wi-hail"),
	THUNDERSTORM("wi-thunderstorm"),
	TORNADO("wi-tornado");

	private String iconIdentifier;

	WeatherIcons(String iconIdentifier) {
		this.iconIdentifier = iconIdentifier;
	}

	public String getIconIdentifier() {
		return iconIdentifier;
	}
}
