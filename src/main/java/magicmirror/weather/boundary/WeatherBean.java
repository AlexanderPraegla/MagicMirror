package magicmirror.weather.boundary;

import magicmirror.weather.control.WeatherRequester;
import magicmirror.weather.entity.WeatherInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
@ApplicationScoped
public class WeatherBean {

	private Logger LOG = LogManager.getLogger(WeatherBean.class);
	private WeatherRequester weatherRequester;
	private WeatherInformation forecast;

	@PostConstruct
	public void init() {
		try {
			LOG.info("Initialize weather forecast");
			forecast = weatherRequester.getForecast();
		} catch (Exception e) {
			LOG.error("Error while initializing weather forecast", e);
		}
	}

	public WeatherInformation getForecast() throws Exception {
		return forecast;
	}

	public void reloadForecast() throws Exception {
		LOG.info("Reloading weather forecast");
		forecast = weatherRequester.getForecast();
	}

	@Inject
	public void setWeatherRequester(WeatherRequester weatherRequester) {
		this.weatherRequester = weatherRequester;
	}
}
