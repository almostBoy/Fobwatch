package net.bency.fobwatch.sonic_screwdrivers;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.FobwatchSounds;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class Sonic_Screwdriver extends Item {
    public Sonic_Screwdriver(Settings settings) {
        super(settings);
    }


    public static Item register(Item item, String id){
        Identifier itemID = Identifier.of(Fobwatch.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    //Declaring Block Types for call in useOnBlock
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

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //Necessary variables
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        BlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();
        int xVal = blockPos.getX();
        int yVal = blockPos.getY();
        int zVal = blockPos.getZ();
        assert user != null;
        //Play screwdriver buzz
        user.playSound(FobwatchSounds.SCREWDRIVER_BUZZ, 3, 1);
        //Get the type of screwdriver the player is holding
        Item screwdriverForCooldown = user.getMainHandStack().getItem();
        //Check if the screwdriver is on cooldown
        if(!user.getItemCooldownManager().isCoolingDown(screwdriverForCooldown)){
            //Check what block was right-clicked, and adjust its blockstate accordingly, and initiate cooldown
            if(!user.isSneaking()) {
                user.getItemCooldownManager().set(screwdriverForCooldown, 30);
                if (block == Blocks.IRON_DOOR) {
                    boolean isOpen = state.get(DoorBlock.OPEN);
                    world.setBlockState(blockPos, state.with(DoorBlock.OPEN, !isOpen));
                    user.playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN, 1, 1);
                    return ActionResult.success(true);
                } else if (block == Blocks.IRON_TRAPDOOR) {
                    boolean isOpen = state.get(TrapdoorBlock.OPEN);
                    world.setBlockState(blockPos, state.with(TrapdoorBlock.OPEN, !isOpen));
                    user.playSound(SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
                    return ActionResult.success(true);
                } else if (state.getBlock() == Blocks.REDSTONE_LAMP) {
                    boolean isLit = state.get(RedstoneLampBlock.LIT);
                    world.setBlockState(blockPos, state.with(RedstoneLampBlock.LIT, !isLit));
                    user.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1, 1);
                    return ActionResult.success(true);
                } else if (Arrays.stream(copperBulbs).anyMatch(match -> match == state.getBlock())) {
                    boolean isLit = state.get(BulbBlock.LIT);
                    world.setBlockState(blockPos, state.with(BulbBlock.LIT, !isLit));
                    user.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1, 1);
                    return ActionResult.success(true);
                } else if (state.getBlock() == Blocks.CAMPFIRE) {
                    boolean isLit = state.get(CampfireBlock.LIT);
                    world.setBlockState(blockPos, state.with(CampfireBlock.LIT, !isLit));
                    return ActionResult.success(true);
                } else if (state.getBlock() == Blocks.SOUL_CAMPFIRE) {
                    boolean isLit = state.get(CampfireBlock.LIT);
                    world.setBlockState(blockPos, state.with(CampfireBlock.LIT, !isLit));
                    return ActionResult.success(true);
                } else if (Arrays.stream(candleBlocks).anyMatch(match -> match == state.getBlock())) {
                    boolean isLit = state.get(CandleBlock.LIT);
                    world.setBlockState(blockPos, state.with(CandleBlock.LIT, !isLit));
                    return ActionResult.success(true);
                } else if (Arrays.stream(candleCakeBlocks).anyMatch(match -> match == state.getBlock())) {
                    boolean isLit = state.get(CandleCakeBlock.LIT);
                    world.setBlockState(blockPos, state.with(CandleCakeBlock.LIT, !isLit));
                    return ActionResult.success(true);
                } else if (state.getBlock() == Blocks.TNT) {
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                    TntEntity tnt = EntityType.TNT.create(world);
                    assert tnt != null;
                    tnt.updatePosition(xVal+0.5, yVal, zVal+0.5);
                    tnt.setFuse(80);
                    world.spawnEntity(tnt);
                    user.playSound(SoundEvents.ENTITY_TNT_PRIMED, 1, 1);
                    return ActionResult.success(true);
                }
            } else {
                user.getItemCooldownManager().set(screwdriverForCooldown, 100);
                 if (state.getBlock() == Blocks.SCULK_SHRIEKER) {
                    world.breakBlock(blockPos, false);
                    return ActionResult.success(true);
                } else if (state.getBlock() == Blocks.SCULK_SENSOR) {
                    world.breakBlock(blockPos, false);
                    return ActionResult.success(true);
                }
            }
        }
        //If none of the possible conditions were met, default to failing-the player will not interact at all
        return ActionResult.FAIL;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        //Necessary variable
        EntityType<?> entityType = entity.getType();
        //Get what kind of screwdriver the player is holding
        Item screwdriverForCooldown = user.getMainHandStack().getItem();
        //Play screwdriver buzz
        user.playSound(FobwatchSounds.SCREWDRIVER_BUZZ, 1, 1);
        //Check if the screwdriver is on cooldown
if(!user.getItemCooldownManager().isCoolingDown(screwdriverForCooldown)) {
    //Check if a creeper was right-clicked, and explode if so, and initiate cooldown
    user.getItemCooldownManager().set(screwdriverForCooldown, 200);
    if (entityType == EntityType.CREEPER) {
        CreeperEntity creeper = (CreeperEntity) entity;
        creeper.ignite();
    }
}
        //If the entity is not a creeper, default to failing-the player will not interact at all
        return ActionResult.FAIL;
    }

    public static final Item SCREWDRIVER_MARK_ONE = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_one");
        public static final Item SCREWDRIVER_MARK_TWO = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_two");
        public static final Item SCREWDRIVER_MARK_THREE = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_three");
        public static final Item SCREWDRIVER_MARK_FOUR = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_four");
        public static final Item SCREWDRIVER_MARK_FIVE = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_five");
        public static final Item SCREWDRIVER_MARK_SIX = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_six");
        public static final Item SCREWDRIVER_MARK_SEVEN = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_seven");
        public static final Item SCREWDRIVER_MARK_EIGHT = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_eight");
        public static final Item SCREWDRIVER_MARK_NINE = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "screwdriver_mark_nine");
        public static final Item WAR_SCREWDRIVER = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "war_screwdriver");
        public static final Item DARK_EYES_SCREWDRIVER = register(new Sonic_Screwdriver(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
                "dark_eyes_screwdriver");

    public static void init(){}

    }


