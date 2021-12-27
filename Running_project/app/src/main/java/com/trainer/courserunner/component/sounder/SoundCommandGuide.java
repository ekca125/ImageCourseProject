package com.trainer.courserunner.component.sounder;

import com.trainer.courserunner.Application.sound.GuideSound;
import com.trainer.courserunner.Application.sound.SoundManagerGuide;

public class SoundCommandGuide implements SoundCommand {
    GuideSound guideSound;

    public SoundCommandGuide(GuideSound guideSound) {
        this.guideSound = guideSound;
    }

    @Override
    public void execute() {
        SoundManagerGuide.getSoundManager().playSound(this.guideSound.ordinal());
    }
}
