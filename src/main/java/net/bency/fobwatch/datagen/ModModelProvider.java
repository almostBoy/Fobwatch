package net.bency.fobwatch.datagen;

import net.bency.fobwatch.tardis_classes.Components;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(Components.DEMATERIALIZATION_CIRCUIT, Models.GENERATED);
        itemModelGenerator.register(Components.FLUID_LINK, Models.GENERATED);
        itemModelGenerator.register(Components.CHAMELEON_CIRCUIT, Models.GENERATED);
        itemModelGenerator.register(Components.TELEPATHIC_ENGINE, Models.GENERATED);
        itemModelGenerator.register(Components.GEOMETRIC_MANIPULATOR, Models.GENERATED);
        itemModelGenerator.register(Components.DEFENSIVE_CIRCUIT, Models.GENERATED);
        itemModelGenerator.register(Components.RIFT_CHARGER, Models.GENERATED);
        itemModelGenerator.register(Components.MAGRANOMIC_SENSOR, Models.GENERATED);
        itemModelGenerator.register(Components.TARDIS_ENGINE, Models.GENERATED);
        itemModelGenerator.register(Components.PLASMIC_SHELL, Models.GENERATED);
        itemModelGenerator.register(Components.TARDIS_HEART, Models.GENERATED);
        itemModelGenerator.register(Components.ARS_EGG, Models.GENERATED);
        itemModelGenerator.register(Components.EYE_OF_HARMONY, Models.GENERATED);
        itemModelGenerator.register(Components.CIRCUIT_BOARD, Models.GENERATED);
        itemModelGenerator.register(Components.CHAMELEON_RECEIVER, Models.GENERATED);
    }
}
