package com.aconex.scrutineer.javautil;

import com.aconex.scrutineer.javautil.TimeSource;

public class ControlledTimeSource implements TimeSource {
    private long expectedTime;

    public ControlledTimeSource(long expectedTime) {
        this.expectedTime = expectedTime;
    }

    @Override
    public long getCurrentTime() {
        return expectedTime;
    }

    public void setCurrentTime(long currentTime) {
        this.expectedTime = currentTime;
    }
}
