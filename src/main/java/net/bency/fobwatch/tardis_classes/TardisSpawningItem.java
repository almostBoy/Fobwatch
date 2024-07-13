package net.bency.fobwatch.tardis_classes;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.FobwatchSounds;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class TardisSpawningItem extends Item {
    public TardisSpawningItem(Settings settings) {
        super(settings);
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Fobwatch.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static final Item TARDIS_SPAWNER = register
            (new TardisSpawningItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)), "tardis_spawner");


    public static void spawnTARDIS(){

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            if(player.getMainHandStack().getItem() == TARDIS_SPAWNER){
                world.setBlockState(blockPos.up(2), tardisBlock.TARDIS.getDefaultState());
                world.playSound(player, blockPos, FobwatchSounds.CLOISTER_BELL, SoundCategory.BLOCKS);
                player.getMainHandStack().decrement(1);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
    }
}
