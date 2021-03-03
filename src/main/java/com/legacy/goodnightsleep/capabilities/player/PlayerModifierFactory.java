package com.legacy.goodnightsleep.capabilities.player;

import net.minecraft.entity.SharedMonsterAttributes;

import java.util.concurrent.Callable;

public class PlayerModifierFactory implements Callable<IPlayerModifier> {
    @Override
    public IPlayerModifier call() throws Exception {
        return new IPlayerModifier() {

            // DreamStalker ability
            private int ability;

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
        };
    }
}