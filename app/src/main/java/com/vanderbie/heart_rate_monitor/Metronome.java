package com.vanderbie.heart_rate_monitor;

import org.apache.commons.lang3.ArrayUtils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;


public class Metronome extends Thread {

    private final SoundPool sp;
    private final int soundIndex;

    Metronome(Context context) {
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundIndex = sp.load(context, R.raw.tick, 1);
    }

    @Override
    public void run() {
        while (HeartRateMonitor.bpm != -1)
        {
            // Make noise

            sp.play(soundIndex, .1f, .1f, 0, 0, 1);

            int[] time = ArrayUtils.toPrimitive((Integer[]) HeartRateMonitor.bpmQueue.toArray(new Integer[0]));
            int bpm = 1000;

            if (HeartRateMonitor.bpmQueue.size() > 0)
            {
                bpm = 0;


                for (Object b : HeartRateMonitor.bpmQueue)
                {
                    bpm += (Integer)b;
                }
                bpm = bpm / HeartRateMonitor.bpmQueue.size();


            }


            try {
                int msPerBeat = (int) (60f /(bpm + 1) * 1000);
                Log.d(HeartRateMonitor.TAG,"Average BPM:"+ bpm + " msPerBeat:" + msPerBeat);
                int sleep = Math.max(200, Math.min(2000, msPerBeat));
                Log.d(HeartRateMonitor.TAG, "sleeping: " + sleep);
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                Log.e(HeartRateMonitor.TAG,"error", e);
            }

        }


    }
}
