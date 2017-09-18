package magicmirror.calendar.boundary;


import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import magicmirror.calendar.control.GoogleCalendarProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.prettytime.PrettyTime;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ManagedBean
@ApplicationScoped
public class CalendarBean implements Serializable {

	private GoogleCalendarProvider googleCalendarProvider;

	private static final Logger LOG = LogManager.getLogger(CalendarBean.class);
	private List<Event> calendarEvents = new ArrayList<>();

	@PostConstruct
	public void init() {
		try {
			LOG.info("Initialize calendar");
			calendarEvents = googleCalendarProvider.getCalendarEvents(10);
		} catch (Exception e) {
			LOG.error("Error while initializing weather forecast", e);
		}
	}

	public List<Event> getEntries() throws Exception {
		return calendarEvents;
	}

	public void reloadCalendar() throws Exception {
		LOG.info("Reloading calendar entries");
		calendarEvents = googleCalendarProvider.getCalendarEvents(10);
	}

	public String getEventDate(Event event) {
		EventDateTime start = event.getStart();
		PrettyTime p = new PrettyTime();
		p.setLocale(Locale.GERMANY);

		DateTime dateTime;
		if (start.getDate() != null) {
			dateTime = start.getDate();
		} else {
			dateTime = start.getDateTime();
		}

		return p.format(new Date(dateTime.getValue()));
	}

	@Inject
	public void setGoogleCalendarProvider(GoogleCalendarProvider googleCalendarProvider) {
		this.googleCalendarProvider = googleCalendarProvider;
	}
}
