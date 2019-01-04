package id.jmlcode.sample.model.http.api;

import id.jmlcode.sample.model.bean.IcndbBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IcndbApi {

    String HOST = "https://api.icndb.com/";

    @GET("jokes/random/{count}")
    Flowable<IcndbBean> getJoke(@Path("count") int count);

}
