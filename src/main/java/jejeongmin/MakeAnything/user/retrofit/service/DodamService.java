package jejeongmin.MakeAnything.user.retrofit.service;

import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.retrofit.RetrofitAPI;
import jejeongmin.MakeAnything.user.retrofit.RetrofitConfig;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamTokenVo;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamUserDataVo;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamUserVo;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
public class DodamService {

    private RetrofitAPI api = RetrofitConfig.getRetrofit().create(RetrofitAPI.class);

    public String authLogin(UserDto userDto, String dodamApiKey) throws Exception {
        Call<ResponseData<DodamTokenVo>> call = api.dodamAuthLogin(userDto, dodamApiKey);
        try {
            Response<ResponseData<DodamTokenVo>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getData().getToken();
            } else {
                throw new NullPointerException("도담도담에 일치하는 정보가 없습니다");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public DodamUserDataVo getUserInfo(String token) throws Exception {
        Call<ResponseData<DodamUserVo<DodamUserDataVo>>> call = api.dodamGetUserInfo(token);
        try {
            Response<ResponseData<DodamUserVo<DodamUserDataVo>>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getData().getMemberData();
            } else {
                throw new NullPointerException("도담도담 서버에서 값을 받아오지 못했습니다");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
