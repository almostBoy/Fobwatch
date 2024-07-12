package net.bency.fobwatch;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class FobwatchSounds {
    private FobwatchSounds(){

    }
    public static final SoundEvent SCREWDRIVER_BUZZ = registerSound("screwdriver_buzz");

    private static SoundEvent registerSound(String id){
        Identifier identifier = Identifier.of(Fobwatch.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init(){}
}
