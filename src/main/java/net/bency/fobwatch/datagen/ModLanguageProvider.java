package net.bency.fobwatch.datagen;

import net.bency.fobwatch.FobwatchSounds;
import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.bency.fobwatch.tardis_classes.Components;
import net.bency.fobwatch.tardis_classes.TARDIS;
import net.bency.fobwatch.tardis_classes.TARDIS_Spawner;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {

    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(Components.DEMATERIALIZATION_CIRCUIT, "Dematerialization Circuit");
        translationBuilder.add(Components.FLUID_LINK, "Fluid Link");
        translationBuilder.add(Components.CHAMELEON_CIRCUIT, "Chameleon Circuit");
        translationBuilder.add(Components.TELEPATHIC_ENGINE, "Telepathic Engine");
        translationBuilder.add(Components.GEOMETRIC_MANIPULATOR, "Geometric Manipulator");
        translationBuilder.add(Components.DEFENSIVE_CIRCUIT, "Defensive Circuit");
        translationBuilder.add(Components.RIFT_CHARGER, "Rift Charger");
        translationBuilder.add(Components.MAGRANOMIC_SENSOR, "Magranomic Sensor");
        translationBuilder.add(Components.TARDIS_ENGINE, "TARDIS Engine");
        translationBuilder.add(Components.PLASMIC_SHELL, "Plasmic Shell");
        translationBuilder.add(Components.TARDIS_HEART, "The Heart of the TARDIS");
        translationBuilder.add(Components.ARS_EGG, "A.R.S Egg");
        translationBuilder.add(Components.EYE_OF_HARMONY, "The Eye of Harmony");
        translationBuilder.add(Components.CIRCUIT_BOARD, "Circuit Board");
        translationBuilder.add(Components.CHAMELEON_RECEIVER, "Chameleon Receiver");

        translationBuilder.add(TARDIS_Spawner.TARDIS_SPAWNER, "TARDIS");

        translationBuilder.add(Components.FOBWATCH_EQUIPMENT_GROUP_KEY, "Fobwatch: Equipment");

        translationBuilder.add(TARDIS.TARDIS, "TARDIS");

        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_TWO, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_THREE, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_FOUR, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_FIVE, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_SIX, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_SEVEN, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_EIGHT, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.SCREWDRIVER_MARK_NINE, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.WAR_SCREWDRIVER, "Sonic Screwdriver");
        translationBuilder.add(Sonic_Screwdriver.DARK_EYES_SCREWDRIVER, "Sonic Screwdriver");

        translationBuilder.add(Components.DIRECTIONAL_REMOTE, "Directional Remote");
        translationBuilder.add(Components.CHAMELEON_REMOTE, "Chameleon Remote");
        translationBuilder.add(Components.STATTENHEIM_REMOTE, "Stattenheim Remote");

        translationBuilder.add("sound.fobwatch.screwdriver_buzz", "Sonic Screwdriver buzzes");
    }
}
