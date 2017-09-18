package magicmirror.weather.control;

import magicmirror.retrofit.Requester;
import magicmirror.retrofit.WeatherForeCastService;
import magicmirror.weather.entity.WeatherInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

import java.io.InputStream;
import java.util.Properties;

public class WeatherRequester extends Requester {

	private Logger LOG = LogManager.getLogger(WeatherRequester.class);

	public WeatherRequester() {
		super("https://api.darksky.net/");
	}

	public WeatherInformation getForecast() throws Exception {
		Properties properties = loadProperties();
		LOG.info("Loading weather information");

		Call<WeatherInformation> call = getService(WeatherForeCastService.class).
				getForeCast(properties.getProperty("apiKey"),
						properties.getProperty("location"),
						properties.getProperty("language"),
						properties.getProperty("units"));
		Response<WeatherInformation> response = call.execute();

		if (response.isSuccessful()) {
			return response.body();
		} else {
			throw new Exception(response.message());
		}
	}

	private Properties loadProperties() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("weather.properties");
		Properties prop = new Properties();
		prop.load(inputStream);

		return prop;
	}
}
