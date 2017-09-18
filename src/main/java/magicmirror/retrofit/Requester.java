package magicmirror.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester {

	private String baseUrl;

	public Requester(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public <T> T getService(Class<T> clazz) {
		Retrofit retrofit = create();
		return retrofit.create(clazz);
	}

	private Retrofit create() {
		return new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
}
