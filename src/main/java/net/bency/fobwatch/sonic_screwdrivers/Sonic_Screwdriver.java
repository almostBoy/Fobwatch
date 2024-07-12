package net.bency.fobwatch.sonic_screwdrivers;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.FobwatchSounds;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.*;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Arrays;

public class Sonic_Screwdriver extends Item {
    public Sonic_Screwdriver(Settings settings) {
        super(settings);
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

    public static Item register(Item item, String id){
        Identifier itemID = Identifier.of(Fobwatch.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Item screwdriverForCooldown = user.getMainHandStack().getItem();
        if(!user.getItemCooldownManager().isCoolingDown(screwdriverForCooldown)){
        world.playSound(user, user.getBlockPos(),
                FobwatchSounds.SCREWDRIVER_BUZZ, SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set(screwdriverForCooldown, 30);}
        return TypedActionResult.pass(user.getStackInHand(hand));
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

    public static void raycastingTask(){}

    }


