package jejeongmin.MakeAnything.common.lib.retrofit;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import retrofit2.Call;

public class DodamLib {

    DodamAPI api;
    public Call<retrofit2.Response<ResponseData>> dodamAuthLogin(UserDto userDto) throws NullPointerException{
        api = HttpClient.getRetrofit().create( DodamAPI.class );
        Call<retrofit2.Response<ResponseData>> call = api.dodamAuthLogin(userDto);
        return call;
    }

}
