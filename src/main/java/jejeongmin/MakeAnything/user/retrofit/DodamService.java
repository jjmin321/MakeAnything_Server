package jejeongmin.MakeAnything.user.retrofit;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.retrofit.dto.DodamTokenDto;
import retrofit2.Call;
import retrofit2.Response;

public class DodamAPIImpl implements DodamAPI {

    private DodamAPI api;

    public DodamTokenDto dodamAuthLogin(UserDto userDto) throws Exception {
        api = DodamClient.getRetrofit().create( DodamAPI.class );
        Call<DodamTokenDto> call = api.dodamAuthLogin(userDto);
        try {
            Response<DodamTokenDto> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            throw e;
        }
//        return call;
    }

}
