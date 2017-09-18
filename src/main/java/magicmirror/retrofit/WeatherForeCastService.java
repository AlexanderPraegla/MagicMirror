package magicmirror.retrofit;

import magicmirror.weather.entity.WeatherInformation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherForeCastService {

	@GET("forecast/{apiKey}/{position}")
	Call<WeatherInformation> getForeCast(@Path("apiKey") String apiKey,
										 @Path("position") String position,
										 @Query("lang") String language,
										 @Query("units") String units);
}
