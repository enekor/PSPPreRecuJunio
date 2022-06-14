package GrepPing.ping;

import java.util.concurrent.Callable;

public class PingCallable implements Callable {
    String web = "";

    public PingCallable(String web){
        this.web = web;
    }
    @Override
    public Object call() throws Exception {
        return web+" -> "+Ping.INSTANCE.ping(web);
    }
}
