package com.trainer.courserunner.runactivity.set;

import com.trainer.courserunner.trainertype.ModeType;

import java.io.Serializable;

public class RunningSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    int drawableId;
    ModeType modeType;

    public RunningSetting(ModeType modeType) {
        this.modeType = modeType;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public ModeType getModeType() {
        return modeType;
    }

    public void setModeType(ModeType modeType) {
        this.modeType = modeType;
    }
}
