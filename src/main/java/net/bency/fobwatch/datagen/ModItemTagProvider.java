package net.bency.fobwatch.datagen;

import net.bency.fobwatch.FobwatchTags;
import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(FobwatchTags.Items.SCREWDRIVERS)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_TWO)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_THREE)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_FOUR)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_FIVE)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_SIX)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_SEVEN)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_EIGHT)
                .add(Sonic_Screwdriver.SCREWDRIVER_MARK_NINE)
                .add(Sonic_Screwdriver.DARK_EYES_SCREWDRIVER)
                .add(Sonic_Screwdriver.WAR_SCREWDRIVER);
        }
    }