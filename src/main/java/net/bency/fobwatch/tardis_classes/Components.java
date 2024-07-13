package net.bency.fobwatch.tardis_classes;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Components {

    // This is the class where the TARDIS component items are created and initialized.
    // The "register" method simply means I don't have to register every item by hand in the "init" method.
    // Every line starting "public static final Item" is implementing a different item with a max stack size of 1 and varying rarities.
    // Everything after the last item is for the creative tab the items are found in (except the "init" method).

    public static Item register(Item item, String id){
        Identifier itemID = Identifier.of(Fobwatch.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static final Item DEMATERIALIZATION_CIRCUIT = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "dematerialization_circuit");
    public static final Item FLUID_LINK = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "fluid_link");
    public static final Item CHAMELEON_CIRCUIT = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "chameleon_circuit");
    public static final Item TELEPATHIC_ENGINE = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "telepathic_engine");
    public static final Item GEOMETRIC_MANIPULATOR = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "geometric_manipulator");
    public static final Item DEFENSIVE_CIRCUIT = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "defensive_circuit");
    public static final Item RIFT_CHARGER = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "rift_charger");
    public static final Item MAGRANOMIC_SENSOR = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)),
            "magranomic_sensor");
    public static final Item TARDIS_ENGINE = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)),
            "tardis_engine");
    public static final Item PLASMIC_SHELL = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)),
            "plasmic_shell");
    public static final Item TARDIS_HEART = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)),
            "tardis_heart");
    public static final Item ARS_EGG = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)),
            "ars_egg");
    public static final Item EYE_OF_HARMONY = register(new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)),
            "eye_of_harmony");
    public static final Item CIRCUIT_BOARD = register(new Item(new Item.Settings().maxCount(1)),
            "circuit_board");
    public static final Item DIRECTIONAL_REMOTE = register(new Item(new Item.Settings().maxCount(1)),
            "directional_remote");
    public static final Item CHAMELEON_REMOTE = register(new Item(new Item.Settings().maxCount(1)),
            "chameleon_remote");
    public static final Item STATTENHEIM_REMOTE = register(new Item(new Item.Settings().maxCount(1)),
            "stattenheim_remote");
    public static final Item CHAMELEON_RECEIVER = register(new Item(new Item.Settings().maxCount(1)),
            "chameleon_receiver");

    public static final RegistryKey<ItemGroup> FOBWATCH_EQUIPMENT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Fobwatch.MOD_ID, "tardis_item_group"));
    public static final ItemGroup FOBWATCH_EQUIPMENT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TardisSpawningItem.TARDIS_SPAWNER))
            .displayName(Text.translatable("itemGroup.fobwatch_equipment"))
            .build();

    public static void init(){
        Registry.register(Registries.ITEM_GROUP, FOBWATCH_EQUIPMENT_GROUP_KEY, FOBWATCH_EQUIPMENT_GROUP);
        ItemGroupEvents.modifyEntriesEvent(FOBWATCH_EQUIPMENT_GROUP_KEY).register(itemGroup ->{
            itemGroup.add(CIRCUIT_BOARD);
            itemGroup.add(DEMATERIALIZATION_CIRCUIT);
            itemGroup.add(FLUID_LINK);
            itemGroup.add(CHAMELEON_CIRCUIT);
            itemGroup.add(TELEPATHIC_ENGINE);
            itemGroup.add(GEOMETRIC_MANIPULATOR);
            itemGroup.add(DEFENSIVE_CIRCUIT);
            itemGroup.add(RIFT_CHARGER);
            itemGroup.add(MAGRANOMIC_SENSOR);
            itemGroup.add(TARDIS_ENGINE);
            itemGroup.add(PLASMIC_SHELL);
            itemGroup.add(TARDIS_HEART);
            itemGroup.add(ARS_EGG);
            itemGroup.add(EYE_OF_HARMONY);
            itemGroup.add(DIRECTIONAL_REMOTE);
            itemGroup.add(CHAMELEON_REMOTE);
            itemGroup.add(STATTENHEIM_REMOTE);
            itemGroup.add(CHAMELEON_RECEIVER);
            itemGroup.add(TardisSpawningItem.TARDIS_SPAWNER);
            itemGroup.add(Sonic_Screwdriver.SCREWDRIVER_MARK_ONE);
        });
    }
}
