package id.jmlcode.sample.util;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.BuildConfig;

import static id.jmlcode.sample.util.Constants.PATH_BLOCK_CANARY;


public class AppBlockCanaryContext extends BlockCanaryContext {
    @Override
    public int getConfigBlockThreshold() {
        return 500;
    }

    @Override
    public boolean isNeedDisplay() {
        // return true untuk production
        // return false untuk development untuk detect seberapa besar app kita menghabiskan memory
        return BuildConfig.DEBUG;
    }

    @Override
    public String getLogPath() {
        return PATH_BLOCK_CANARY;
    }
}
