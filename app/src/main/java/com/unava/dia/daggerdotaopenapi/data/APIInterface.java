package com.unava.dia.daggerdotaopenapi.data;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface APIInterface {
    // Get Player Profile
    @GET("players/{accountId}/matches")
    Observable<List<PlayerMatches>> getPlayerMatches(@Path("accountId")String accountId);
}