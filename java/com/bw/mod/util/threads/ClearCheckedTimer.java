package com.bw.mod.util.threads;

import com.bw.mod.BWMod;
import com.bw.mod.util.Timer;

public class ClearCheckedTimer extends Thread {

    public static Timer timer = new Timer();

    @Override
    public void run() {
        while (true) {
            if (timer.hasReached(1000 * 90)) {
                BWMod.getInstance().cache.clear();
                timer.reset();
            }
        }
    }
}
