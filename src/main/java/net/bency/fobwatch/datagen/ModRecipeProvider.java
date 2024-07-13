package net.bency.fobwatch.datagen;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.FobwatchTags;
import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.bency.fobwatch.tardis_classes.Components;
import net.bency.fobwatch.tardis_classes.TardisSpawningItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // TARDIS Component recipes
        {

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.DEMATERIALIZATION_CIRCUIT, 1)
                    .pattern("LGL")
                    .pattern(" P ")
                    .pattern(" L ")
                    .input('L', Items.REDSTONE_LAMP)
                    .input('G', Items.GREEN_DYE)
                    .input('P', Items.ENDER_PEARL)
                    .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                    .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                    .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                    .offerTo(exporter, Identifier.of("fobwatch","demat_circ"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.FLUID_LINK, 1)
                    .pattern("IBI")
                    .pattern("IMI")
                    .pattern("IBI")
                    .input('I', Items.IRON_NUGGET)
                    .input('B', Items.STICK)
                    .input('M', Items.MAGMA_CREAM)
                    .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                    .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                    .criterion(hasItem(Items.MAGMA_CREAM), conditionsFromItem(Items.MAGMA_CREAM))
                    .offerTo(exporter, Identifier.of("fobwatch","flu_lin"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.CHAMELEON_CIRCUIT, 1)
                    .pattern("CLC")
                    .pattern("ECR")
                    .pattern("CGC")
                    .input('C', Items.COPPER_INGOT)
                    .input('L', Items.LAPIS_LAZULI)
                    .input('E', Items.EMERALD)
                    .input('R', Items.REDSTONE)
                    .input('G', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                    .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(exporter, Identifier.of("fobwatch","cham_circ"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.TELEPATHIC_ENGINE, 1)
                    .pattern("PGG")
                    .pattern("GEG")
                    .pattern("GGC")
                    .input('P', Items.PINK_STAINED_GLASS_PANE)
                    .input('C', Items.CYAN_STAINED_GLASS_PANE)
                    .input('E', Items.ENDER_PEARL)
                    .input('G', Items.GOLD_INGOT)
                    .criterion(hasItem(Items.PINK_STAINED_GLASS_PANE), conditionsFromItem(Items.PINK_STAINED_GLASS_PANE))
                    .criterion(hasItem(Items.CYAN_STAINED_GLASS_PANE), conditionsFromItem(Items.CYAN_STAINED_GLASS_PANE))
                    .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .offerTo(exporter, Identifier.of("fobwatch","tele_eng"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.GEOMETRIC_MANIPULATOR, 1)
                    .pattern(" II")
                    .pattern("IWI")
                    .pattern(" II")
                    .input('I', Items.IRON_INGOT)
                    .input('W', Items.ENDER_PEARL)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                    .offerTo(exporter, Identifier.of("fobwatch","geo_manip"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.DEFENSIVE_CIRCUIT, 1)
                    .pattern("DOD")
                    .pattern("OCO")
                    .pattern("DOD")
                    .input('D', Items.DIAMOND)
                    .input('O', Items.OBSIDIAN)
                    .input('C', Items.CRYING_OBSIDIAN)
                    .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                    .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                    .criterion(hasItem(Items.CRYING_OBSIDIAN), conditionsFromItem(Items.CRYING_OBSIDIAN))
                    .offerTo(exporter, Identifier.of("fobwatch","def_circ"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.RIFT_CHARGER, 1)
                    .pattern("I I")
                    .pattern("BCB")
                    .pattern("I I")
                    .input('I', Items.IRON_INGOT)
                    .input('B', Items.LIGHT_BLUE_STAINED_GLASS_PANE)
                    .input('C', Items.COPPER_BULB)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.LIGHT_BLUE_STAINED_GLASS_PANE), conditionsFromItem(Items.LIGHT_BLUE_STAINED_GLASS_PANE))
                    .criterion(hasItem(Items.COPPER_BULB), conditionsFromItem(Items.COPPER_BULB))
                    .offerTo(exporter, Identifier.of("fobwatch","rift_char"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.MAGRANOMIC_SENSOR, 1)
                    .pattern("SSS")
                    .pattern("IDI")
                    .pattern(" I ")
                    .input('I', Items.IRON_INGOT)
                    .input('D', Items.CALIBRATED_SCULK_SENSOR)
                    .input('S', Items.POLISHED_DEEPSLATE)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.CALIBRATED_SCULK_SENSOR), conditionsFromItem(Items.CALIBRATED_SCULK_SENSOR))
                    .criterion(hasItem(Items.POLISHED_DEEPSLATE), conditionsFromItem(Items.POLISHED_DEEPSLATE))
                    .offerTo(exporter, Identifier.of("fobwatch","mag_sense"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.CIRCUIT_BOARD, 1)
                    .pattern("NNN")
                    .pattern("GPG")
                    .pattern("RCR")
                    .input('N', Items.GOLD_NUGGET)
                    .input('G', Items.GREEN_DYE)
                    .input('P', Items.GLASS_PANE)
                    .input('C', Items.COPPER_INGOT)
                    .input('R', Items.REDSTONE)
                    .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                    .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                    .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .offerTo(exporter, Identifier.of("fobwatch","circ_bor"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.PLASMIC_SHELL, 1)
                    .pattern("AIA")
                    .pattern("INI")
                    .pattern("AIA")
                    .input('I', Items.IRON_INGOT)
                    .input('A', Items.AMETHYST_SHARD)
                    .input('N', Items.NETHERITE_INGOT)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .offerTo(exporter, Identifier.of("fobwatch","plas_shell"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.TARDIS_HEART, 1)
                    .pattern("GEG")
                    .pattern("ESE")
                    .pattern("GEG")
                    .input('G', Items.GLOWSTONE_DUST)
                    .input('E', Items.ENDER_PEARL)
                    .input('S', Items.SEA_LANTERN)
                    .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                    .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                    .criterion(hasItem(Items.SEA_LANTERN), conditionsFromItem(Items.SEA_LANTERN))
                    .offerTo(exporter, Identifier.of("fobwatch","heart_rec"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.ARS_EGG, 1)
                    .pattern("ISI")
                    .pattern("SES")
                    .pattern("ISI")
                    .input('I', Items.IRON_INGOT)
                    .input('E', Items.EGG)
                    .input('S', Items.ECHO_SHARD)
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                    .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                    .offerTo(exporter, Identifier.of("fobwatch","ars_eggrec"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.EYE_OF_HARMONY, 1)
                    .pattern("BGB")
                    .pattern("GEG")
                    .pattern("BGB")
                    .input('G', Items.GOLD_INGOT)
                    .input('E', Items.ENDER_EYE)
                    .input('B', Items.BLAZE_ROD)
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                    .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                    .offerTo(exporter, Identifier.of("fobwatch","eye_rec"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Components.TARDIS_ENGINE,1)
                    .input(Components.DEMATERIALIZATION_CIRCUIT)
                    .input(Components.FLUID_LINK)
                    .input(Components.CHAMELEON_CIRCUIT)
                    .input(Components.TELEPATHIC_ENGINE)
                    .input(Components.GEOMETRIC_MANIPULATOR)
                    .input(Components.DEFENSIVE_CIRCUIT)
                    .input(Components.RIFT_CHARGER)
                    .input(Components.MAGRANOMIC_SENSOR)
                    .input(Components.CIRCUIT_BOARD)
                    .criterion(hasItem(Components.DEMATERIALIZATION_CIRCUIT), conditionsFromItem(Components.DEMATERIALIZATION_CIRCUIT))
                    .criterion(hasItem(Components.FLUID_LINK), conditionsFromItem(Components.FLUID_LINK))
                    .criterion(hasItem(Components.CHAMELEON_CIRCUIT), conditionsFromItem(Components.CHAMELEON_CIRCUIT))
                    .criterion(hasItem(Components.TELEPATHIC_ENGINE), conditionsFromItem(Components.TELEPATHIC_ENGINE))
                    .criterion(hasItem(Components.GEOMETRIC_MANIPULATOR), conditionsFromItem(Components.GEOMETRIC_MANIPULATOR))
                    .criterion(hasItem(Components.DEFENSIVE_CIRCUIT), conditionsFromItem(Components.DEFENSIVE_CIRCUIT))
                    .criterion(hasItem(Components.RIFT_CHARGER), conditionsFromItem(Components.RIFT_CHARGER))
                    .criterion(hasItem(Components.MAGRANOMIC_SENSOR), conditionsFromItem(Components.MAGRANOMIC_SENSOR))
                    .criterion(hasItem(Components.CIRCUIT_BOARD), conditionsFromItem(Components.CIRCUIT_BOARD))
                    .offerTo(exporter, Identifier.of("fobwatch","tardisenginerecipe"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TardisSpawningItem.TARDIS_SPAWNER,1)
                    .input(Components.TARDIS_ENGINE)
                    .input(Components.TARDIS_HEART)
                    .input(Components.EYE_OF_HARMONY)
                    .input(Components.ARS_EGG)
                    .input(Components.PLASMIC_SHELL)
                    .criterion(hasItem(Components.TARDIS_ENGINE), conditionsFromItem(Components.TARDIS_ENGINE))
                    .criterion(hasItem(Components.TARDIS_HEART), conditionsFromItem(Components.TARDIS_HEART))
                    .criterion(hasItem(Components.EYE_OF_HARMONY), conditionsFromItem(Components.EYE_OF_HARMONY))
                    .criterion(hasItem(Components.ARS_EGG), conditionsFromItem(Components.ARS_EGG))
                    .criterion(hasItem(Components.PLASMIC_SHELL), conditionsFromItem(Components.PLASMIC_SHELL))
                    .offerTo(exporter, Identifier.of("fobwatch","tardisspawnerrecipe"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Sonic_Screwdriver.SCREWDRIVER_MARK_ONE, 1)
                    .pattern(" A ")
                    .pattern("LBC")
                    .pattern(" I ")
                    .input('A', Items.AMETHYST_SHARD)
                    .input('I', Items.IRON_INGOT)
                    .input('B', Components.CIRCUIT_BOARD)
                    .input('C', Items.COMPARATOR)
                    .input('L', Items.LEVER)
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .criterion(hasItem(Components.CIRCUIT_BOARD), conditionsFromItem(Components.CIRCUIT_BOARD))
                    .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                    .criterion(hasItem(Items.LEVER), conditionsFromItem(Items.LEVER))
                    .offerTo(exporter, Identifier.of("fobwatch","sonic_screwdriver_creation"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.DIRECTIONAL_REMOTE, 1)
                    .pattern(" A ")
                    .pattern("LCM")
                    .pattern(" I ")
                    .input('A', Items.AMETHYST_SHARD)
                    .input('I', Items.COPPER_INGOT)
                    .input('C', Components.CIRCUIT_BOARD)
                    .input('M', Items.COMPARATOR)
                    .input('L', Items.COMPASS)
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .criterion(hasItem(Components.CIRCUIT_BOARD), conditionsFromItem(Components.CIRCUIT_BOARD))
                    .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                    .criterion(hasItem(Items.COMPASS), conditionsFromItem(Items.COMPASS))
                    .offerTo(exporter, Identifier.of("fobwatch","dir_rem_rec"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.STATTENHEIM_REMOTE, 1)
                    .pattern(" A ")
                    .pattern("LCM")
                    .pattern(" I ")
                    .input('A', Items.AMETHYST_SHARD)
                    .input('I', Items.COPPER_INGOT)
                    .input('C', Components.CIRCUIT_BOARD)
                    .input('M', Items.COMPARATOR)
                    .input('L', Items.RECOVERY_COMPASS)
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .criterion(hasItem(Components.CIRCUIT_BOARD), conditionsFromItem(Components.CIRCUIT_BOARD))
                    .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                    .criterion(hasItem(Items.RECOVERY_COMPASS), conditionsFromItem(Items.RECOVERY_COMPASS))
                    .offerTo(exporter, Identifier.of("fobwatch","stat_rem_rec"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.CHAMELEON_REMOTE, 1)
                    .pattern(" A ")
                    .pattern("LCM")
                    .pattern(" I ")
                    .input('A', Items.AMETHYST_SHARD)
                    .input('I', Items.COPPER_INGOT)
                    .input('C', Components.CIRCUIT_BOARD)
                    .input('M', Items.COMPARATOR)
                    .input('L', Components.CHAMELEON_RECEIVER)
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                    .criterion(hasItem(Components.CIRCUIT_BOARD), conditionsFromItem(Components.CIRCUIT_BOARD))
                    .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                    .criterion(hasItem(Components.CHAMELEON_RECEIVER), conditionsFromItem(Components.CHAMELEON_RECEIVER))
                    .offerTo(exporter, Identifier.of("fobwatch","cham_rem_rec"));

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Components.CHAMELEON_RECEIVER, 1)
                    .pattern("CLC")
                    .pattern("EAR")
                    .pattern("CGC")
                    .input('C', Items.DIAMOND)
                    .input('L', Items.LAPIS_LAZULI)
                    .input('E', Items.EMERALD)
                    .input('R', Items.REDSTONE)
                    .input('G', Items.GOLD_INGOT)
                    .input('A', Items.AMETHYST_SHARD)
                    .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                    .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                    .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                    .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                    .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                    .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                    .offerTo(exporter, Identifier.of("fobwatch","cham_receiver_recipe"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_ONE,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.RED_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver1"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_TWO,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.YELLOW_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver2"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_THREE,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.GRAY_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver3"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_FOUR,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.LIGHT_BLUE_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver4"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_FIVE,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.LIME_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver5"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_SIX,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.BLUE_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver6"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_SEVEN,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.ORANGE_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver7"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_EIGHT,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.WHITE_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver8"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.SCREWDRIVER_MARK_NINE,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.PURPLE_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriver9"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.WAR_SCREWDRIVER,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.BLACK_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriverwar"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Sonic_Screwdriver.DARK_EYES_SCREWDRIVER,1)
                    .input(FobwatchTags.Items.SCREWDRIVERS)
                    .input(Items.BROWN_DYE)
                    .criterion(hasItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE), conditionsFromItem(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE))
                    .offerTo(exporter, Identifier.of(Fobwatch.MOD_ID, "screwdriverdarkeyes"));

        }


        }
    }
