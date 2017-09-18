package magicmirror.info;

import magicmirror.weather.boundary.WeatherBean;
import magicmirror.weather.entity.WeatherInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ManagedBean
@ApplicationScoped
public class InfoBean {
	private Logger LOG = LogManager.getLogger(InfoBean.class);

	@ManagedProperty(value = "#{weatherBean}")
	private WeatherBean weatherBean;
	private List<String> infos = new ArrayList<>();
	private int counter = 0;

	@PostConstruct
	public void init() {
		try {
			LOG.info("Initalize infos");
			createInfos();
		} catch (Exception e) {
			LOG.error("Error while initializing infos", e);
		}
	}

	public void createInfos() throws Exception {
		WeatherInformation forecast = weatherBean.getForecast();
		infos.add(String.format(Locale.US,"Aktuelle Windgeschwindigkeit: %1$,.2f km/h", forecast.getCurrently().getWindSpeed()));
		infos.add(String.format("Aktuelles Wetter: %s", forecast.getCurrently().getSummary()));
		infos.add(String.format("%s", forecast.getHourly().getSummary()));
		infos.add(String.format("%s", forecast.getDaily().getSummary()));

		counter = infos.size();
	}

	public String nextInfo() {
		String info = infos.get(counter - 1);
		counter--;

		if (counter == 0) {
			counter = infos.size();
		}

		return info;
	}

	@SuppressWarnings("unused")
	public void setWeatherBean(WeatherBean weatherBean) {
		this.weatherBean = weatherBean;
	}
}
