package com.trainer.courserunner.Application.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.trainer.courserunner.R;


public class SoundManagerGuide {
    static private SoundManager soundManager;

    private SoundManagerGuide() {
    }

    static public void initSoundManager(Context context) {
        //builder
        AudioAttributes.Builder audioAttributesBuilder = new AudioAttributes.Builder();
        audioAttributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
        audioAttributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_UNKNOWN);
        //attribute
        AudioAttributes audioAttributes = audioAttributesBuilder.build();
        //builder
        SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
        soundPoolBuilder.setAudioAttributes(audioAttributes);
        soundPoolBuilder.setMaxStreams(2);
        //
        SoundPool soundPool = soundPoolBuilder.build();

        soundManager = new SoundManager(context, soundPool);
        soundManager.addSound(GuideSound.FINISHKID.ordinal(), R.raw.finish_kid);
        soundManager.addSound(GuideSound.FINISHMAN.ordinal(), R.raw.finish_man);
        soundManager.addSound(GuideSound.FINISHWOMAN.ordinal(), R.raw.finish_woman);
    }

    static public SoundManager getSoundManager() {
        return soundManager;
    }
}
