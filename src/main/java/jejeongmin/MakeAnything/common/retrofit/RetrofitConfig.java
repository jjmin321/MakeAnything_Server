package jejeongmin.MakeAnything.common.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if( retrofit == null )
        {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl( "http://dodam.b1nd.com/api/v2/");
            builder.addConverterFactory( GsonConverterFactory.create() );

            retrofit = builder.build();
        }

        return retrofit;
    }

}
