package magicmirror.calendar.control;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class GoogleCalendarProvider {
	private Logger LOG = LogManager.getLogger(GoogleCalendarProvider.class);
	/**
	 * Application name.
	 */
	private static final String APPLICATION_NAME =
			"MagicMirrorId";

	/**
	 * Directory to store user credentials for this application.
	 */
	private static final File DATA_STORE_DIR = new java.io.File("usercredentials");

	/**
	 * Global instance of the {@link FileDataStoreFactory}.
	 */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/**
	 * Global instance of the JSON factory.
	 */
	private static final JsonFactory JSON_FACTORY =
			JacksonFactory.getDefaultInstance();

	/**
	 * Global instance of the HTTP transport.
	 */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 * <p>
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/calendar-java-quickstart
	 */
	private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 *
	 * @return an authorized Credential object.
	 * @throws IOException d
	 */
	private Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in =
				GoogleCalendarProvider.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
						.setDataStoreFactory(DATA_STORE_FACTORY)
						.setAccessType("offline")
						.build();
		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		LOG.info(
				"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Calendar client service.
	 *
	 * @return an authorized Calendar client service
	 * @throws IOException d
	 */
	private Calendar getCalendarService() throws IOException {
		Credential credential = authorize();
		return new com.google.api.services.calendar.Calendar.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	public List<Event> getCalendarEvents(int results) throws IOException {
		// Build a new authorized API client service.
		// Note: Do not confuse this class with the com.google.api.services.calendar.model.Calendar class.
		Calendar service =
				getCalendarService();

		// List the next 10 events from the primary calendar.
		DateTime now = new DateTime(System.currentTimeMillis());
		Events events = service.events().list("primary")
				.setMaxResults(results)
				.setTimeMin(now)
				.setOrderBy("startTime")
				.setSingleEvents(true)
				.execute();
		List<Event> items = events.getItems();
		if (items.size() == 0) {
			LOG.info("No upcoming events found.");
		}
		return items;
	}
}
