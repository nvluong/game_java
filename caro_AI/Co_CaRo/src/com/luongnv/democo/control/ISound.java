package com.luongnv.democo.control;

public abstract class ISound {
    public ISound(String fileName){
    }
    public abstract void play();
    public abstract void stop();
    public abstract void loop(int count);

}
