package com.Pattern.template_method.advance.Level;

public abstract class PlayerLevel {

    public abstract void run();
    public abstract void jump();
    public abstract void turn();
    public abstract void showLevelMessage();

    final public void go(int count){
        run();
        for(int i = 0; i < count; i += 1){
            jump();
        }
        turn();
    }

}
