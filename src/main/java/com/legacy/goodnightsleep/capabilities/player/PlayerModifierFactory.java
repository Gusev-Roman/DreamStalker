package com.legacy.goodnightsleep.capabilities.player;

import net.minecraft.entity.SharedMonsterAttributes;

import java.util.concurrent.Callable;

public class PlayerModifierFactory implements Callable<IPlayerModifier> {
    @Override
    public IPlayerModifier call() throws Exception {
        return new IPlayerModifier() {

            // DreamStalker ability
            private int ability;
            // Previous dimension
            private int current_dim = 0;    // overworld by default: возможны проблемы при входе в игру непосредственно в сон. Как определить, что это новое подключение, а не телепорт?

            @Override
            public int getAbility() {
                return ability;
            }

            @Override
            public void setAbility(int value) {
                ability = value;
            }

            //@Override - not override method!
            public void incrementAbility(int value) {
                ability += value;
            }

            public int getCurDim(){
                return current_dim;
            }
            public void setCurDim(int value){
                current_dim = value;
            }
      
        };
    }
}