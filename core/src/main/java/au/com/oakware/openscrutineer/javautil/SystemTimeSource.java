package au.com.oakware.openscrutineer.javautil;

public class SystemTimeSource implements TimeSource {

    @Override
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
