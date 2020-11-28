package com.Pattern.template_method.advance;

import com.Pattern.template_method.advance.Level.AdvancedLevel;
import com.Pattern.template_method.advance.Level.SuperLevel;

public class Main {
    public static void main(String[] args){
        Player player = new Player();
        player.play(1);

        AdvancedLevel aLevel = new AdvancedLevel();
        player.upgradeLevel(aLevel);
        player.play(2);

        SuperLevel sLevel = new SuperLevel();
        player.upgradeLevel(sLevel);
        player.play(3);


    }
}
