package net.bency.fobwatch;

import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class RaycastingClass {
    public static void registerSonicUseCallback(){
        UseItemCallback.EVENT.register((user, world, hand) -> {
            if (world.isClient){
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
            Item[] screwdriverItems ={
                    Sonic_Screwdriver.SCREWDRIVER_MARK_ONE,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_TWO,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_THREE,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_FOUR,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_FIVE,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_SIX,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_SEVEN,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_EIGHT,
                    Sonic_Screwdriver.SCREWDRIVER_MARK_NINE,
                    Sonic_Screwdriver.DARK_EYES_SCREWDRIVER,
                    Sonic_Screwdriver.WAR_SCREWDRIVER,
            };
            Block[] candleBlocks ={
                    Blocks.CANDLE,
                    Blocks.CYAN_CANDLE,
                    Blocks.BLACK_CANDLE,
                    Blocks.BLUE_CANDLE,
                    Blocks.BROWN_CANDLE,
                    Blocks.GRAY_CANDLE,
                    Blocks.GREEN_CANDLE,
                    Blocks.LIGHT_BLUE_CANDLE,
                    Blocks.LIGHT_GRAY_CANDLE,
                    Blocks.LIME_CANDLE,
                    Blocks.MAGENTA_CANDLE,
                    Blocks.ORANGE_CANDLE,
                    Blocks.PURPLE_CANDLE,
                    Blocks.RED_CANDLE,
                    Blocks.WHITE_CANDLE,
                    Blocks.YELLOW_CANDLE

            };
            Block[] candleCakeBlocks ={
                    Blocks.CANDLE_CAKE,
                    Blocks.CYAN_CANDLE_CAKE,
                    Blocks.BLACK_CANDLE_CAKE,
                    Blocks.BLUE_CANDLE_CAKE,
                    Blocks.BROWN_CANDLE_CAKE,
                    Blocks.GRAY_CANDLE_CAKE,
                    Blocks.GREEN_CANDLE_CAKE,
                    Blocks.LIGHT_BLUE_CANDLE_CAKE,
                    Blocks.LIGHT_GRAY_CANDLE_CAKE,
                    Blocks.LIME_CANDLE_CAKE,
                    Blocks.MAGENTA_CANDLE_CAKE,
                    Blocks.ORANGE_CANDLE_CAKE,
                    Blocks.PURPLE_CANDLE_CAKE,
                    Blocks.RED_CANDLE_CAKE,
                    Blocks.WHITE_CANDLE_CAKE,
                    Blocks.YELLOW_CANDLE_CAKE

            };
            Block[] doorBlocks ={
                    Blocks.OAK_DOOR,
                    Blocks.BIRCH_DOOR,
                    Blocks.SPRUCE_DOOR,
                    Blocks.DARK_OAK_DOOR,
                    Blocks.CHERRY_DOOR,
                    Blocks.MANGROVE_DOOR,
                    Blocks.ACACIA_DOOR,
                    Blocks.BAMBOO_DOOR,
                    Blocks.CRIMSON_DOOR,
                    Blocks.WARPED_DOOR,
                    Blocks.IRON_DOOR,
                    Blocks.COPPER_DOOR,
                    Blocks.EXPOSED_COPPER_DOOR,
                    Blocks.WEATHERED_COPPER_DOOR,
                    Blocks.OXIDIZED_COPPER_DOOR,
                    Blocks.WAXED_COPPER_DOOR,
                    Blocks.WAXED_EXPOSED_COPPER_DOOR,
                    Blocks.WAXED_WEATHERED_COPPER_DOOR,
                    Blocks.WAXED_OXIDIZED_COPPER_DOOR
            };
            Block[] trapDoorBlocks ={
                    Blocks.OAK_TRAPDOOR,
                    Blocks.BIRCH_TRAPDOOR,
                    Blocks.SPRUCE_TRAPDOOR,
                    Blocks.DARK_OAK_TRAPDOOR,
                    Blocks.CHERRY_TRAPDOOR,
                    Blocks.MANGROVE_TRAPDOOR,
                    Blocks.ACACIA_TRAPDOOR,
                    Blocks.BAMBOO_TRAPDOOR,
                    Blocks.CRIMSON_TRAPDOOR,
                    Blocks.WARPED_TRAPDOOR,
                    Blocks.IRON_TRAPDOOR,
                    Blocks.COPPER_TRAPDOOR,
                    Blocks.EXPOSED_COPPER_TRAPDOOR,
                    Blocks.WEATHERED_COPPER_TRAPDOOR,
                    Blocks.OXIDIZED_COPPER_TRAPDOOR,
                    Blocks.WAXED_COPPER_TRAPDOOR,
                    Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR,
                    Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR,
                    Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR
            };
            Block[] copperBulbs = {
                    Blocks.COPPER_BULB,
                    Blocks.EXPOSED_COPPER_BULB,
                    Blocks.WEATHERED_COPPER_BULB,
                    Blocks.OXIDIZED_COPPER_BULB,
                    Blocks.WAXED_COPPER_BULB,
                    Blocks.WAXED_EXPOSED_COPPER_BULB,
                    Blocks.WAXED_WEATHERED_COPPER_BULB,
                    Blocks.WAXED_OXIDIZED_COPPER_BULB
            };

            if(Arrays.stream(screwdriverItems).anyMatch(match -> match == user.getMainHandStack().getItem())){

                Item screwdriverForCooldown = user.getMainHandStack().getItem();

                if (!user.getItemCooldownManager().isCoolingDown(screwdriverForCooldown)) {

                    user.getItemCooldownManager().set(screwdriverForCooldown, 30);
                    int damaged = user.getMainHandStack().getDamage();
                    user.getMainHandStack().setDamage(damaged-1);

                    MinecraftClient client = MinecraftClient.getInstance();
                    HitResult hit = client.cameraEntity.raycast(7, 1.0F, false);


                    switch (hit.getType()) {
                        case MISS -> {
                            return TypedActionResult.fail(user.getStackInHand(hand));
                        }
                        case ENTITY -> {
                            return TypedActionResult.pass(user.getStackInHand(hand));
                        }
                        case BLOCK -> {
                            BlockHitResult blockHit = (BlockHitResult) hit;
                            BlockPos blockPos = blockHit.getBlockPos();
                            int xVal = blockPos.getX();
                            int yVal = blockPos.getY();
                            int zVal = blockPos.getZ();
                            BlockState state = world.getBlockState(blockPos);
                                if (Arrays.stream(doorBlocks).anyMatch(match -> match == state.getBlock())) {
                                    boolean isOpen = state.get(DoorBlock.OPEN);
                                    world.setBlockState(blockPos, state.with(DoorBlock.OPEN, !isOpen));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (Arrays.stream(trapDoorBlocks).anyMatch(match -> match == state.getBlock())) {
                                    boolean isOpen = state.get(TrapdoorBlock.OPEN);
                                    world.setBlockState(blockPos, state.with(TrapdoorBlock.OPEN, !isOpen));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.REDSTONE_LAMP) {
                                    boolean isLit = state.get(RedstoneLampBlock.LIT);
                                    world.setBlockState(blockPos, state.with(RedstoneLampBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (Arrays.stream(copperBulbs).anyMatch(match -> match == state.getBlock())) {
                                    boolean isLit = state.get(BulbBlock.LIT);
                                    world.setBlockState(blockPos, state.with(BulbBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.SCULK_SHRIEKER) {
                                    world.breakBlock(blockPos, false);
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.SCULK_SENSOR) {
                                    world.breakBlock(blockPos, false);
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.CAMPFIRE) {
                                    boolean isLit = state.get(CampfireBlock.LIT);
                                    world.setBlockState(blockPos, state.with(CampfireBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.SOUL_CAMPFIRE) {
                                    boolean isLit = state.get(CampfireBlock.LIT);
                                    world.setBlockState(blockPos, state.with(CampfireBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (Arrays.stream(candleBlocks).anyMatch(match -> match == state.getBlock())) {
                                    boolean isLit = state.get(CandleBlock.LIT);
                                    world.setBlockState(blockPos, state.with(CandleBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (Arrays.stream(candleCakeBlocks).anyMatch(match -> match == state.getBlock())) {
                                    boolean isLit = state.get(CandleCakeBlock.LIT);
                                    world.setBlockState(blockPos, state.with(CandleCakeBlock.LIT, !isLit));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if ((state.getBlock() == Blocks.TNT)) {
                                    world.breakBlock(blockPos, false);
                                    world.createExplosion(user, xVal, yVal, zVal, 4.0f, World.ExplosionSourceType.TNT);
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if ((state.getBlock() == Blocks.STONE_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)) {
                                    boolean powered = state.get(PressurePlateBlock.POWERED);
                                    world.setBlockState(blockPos, state.with(PressurePlateBlock.POWERED, !powered));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if ((state.getBlock() == Blocks.STONE_BUTTON) ||
                                        (state.getBlock() == Blocks.POLISHED_BLACKSTONE_BUTTON)) {
                                    boolean powered = state.get(ButtonBlock.POWERED);
                                    world.setBlockState(blockPos, state.with(ButtonBlock.POWERED, !powered));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if (state.getBlock() == Blocks.TNT) {
                                    world.breakBlock(blockPos, false);
                                    world.createExplosion(user, xVal, yVal, zVal, 4.0f, World.ExplosionSourceType.TNT);
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if ((state.getBlock() == Blocks.STONE_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE) ||
                                        (state.getBlock() == Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)) {
                                    boolean powered = state.get(PressurePlateBlock.POWERED);
                                    world.setBlockState(blockPos, state.with(PressurePlateBlock.POWERED, !powered));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                } else if ((state.getBlock() == Blocks.STONE_BUTTON) ||
                                        (state.getBlock() == Blocks.POLISHED_BLACKSTONE_BUTTON)) {
                                    boolean powered = state.get(ButtonBlock.POWERED);
                                    world.setBlockState(blockPos, state.with(ButtonBlock.POWERED, !powered));
                                    return TypedActionResult.success(user.getStackInHand(hand), true);
                                }
                                return TypedActionResult.fail(user.getStackInHand(hand));
                        }
                    }
                }else{return TypedActionResult.fail(user.getStackInHand(hand));}
            }else{
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
            return TypedActionResult.pass(user.getStackInHand(hand));
        });

    }
}


