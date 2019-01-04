package id.jmlcode.sample.util;

import id.jmlcode.sample.model.bean.BloombergCNBCHttpResponse;
import id.jmlcode.sample.model.http.exception.ApiException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jamal on 1/23/2018.
 */

public class RxUtil {
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<BloombergCNBCHttpResponse<T>, T> handleBloombergCNBCResult() {
        return new FlowableTransformer<BloombergCNBCHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BloombergCNBCHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BloombergCNBCHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BloombergCNBCHttpResponse<T> tBloombergCNBCHttpResponse) {
                        if(tBloombergCNBCHttpResponse.getStatus().equals("ok")) {
                            return createData(tBloombergCNBCHttpResponse.getArticles());
                        } else {
                            return Flowable.error(new ApiException("Someting wrong!!"));
                        }
                    }
                });
            }
        };
    }

    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
