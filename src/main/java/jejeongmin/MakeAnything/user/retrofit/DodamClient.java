package jejeongmin.MakeAnything.user.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if( retrofit == null )
        {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl( "http://dodam.b1nd.com/api/v2/auth/login/");
            builder.addConverterFactory( GsonConverterFactory.create() );

            retrofit = builder.build();
        }

        return retrofit;
    }
}
