package com.pandaismyname1.zoomerbows;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Bows {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Zoomer_bows.MODID);

    /*
    Bows to generate:
    - Iron Bow
    - Pyrope Bow
    - Aquite Bow
    - Diopside Bow
    - Diamond Bow
    - Charoite Bow
    - Horizonite Bow
    - Zanite Bow
    - Gravitite Bow
    - Valkyrie Bow
    - Cincinnasite Bow
    - Cincinnasite Diamond Bow
    - Nether Ruby Bow
    - Fire Ruby Bow
    - Netherite Bow
    - Cloggrum Bow
    - Froststeel Bow
    - Utherium Bow
    - Forgotten Bow
    - Aeternium Bow
    - Thallasium Bow
    - Terminite Bow
    - Bone Bow
    - Garnite Bow
    - Ignisithe Bow
    - Fusion Bow
    - Aberiythe Bow
    - Unorithe Bow
    - Phantom Bow
    - Incorynthe Bow
     */

    public static final RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow", () -> new BowItem(new Item.Properties().durability(500)));
    public static final RegistryObject<Item> PYROPE_BOW = ITEMS.register("pyrope_bow", () -> new BowItem(new Item.Properties().durability(600)));
    public static final RegistryObject<Item> AQUITE_BOW = ITEMS.register("aquite_bow", () -> new BowItem(new Item.Properties().durability(700)));
    public static final RegistryObject<Item> DIOPSIDE_BOW = ITEMS.register("diopside_bow", () -> new BowItem(new Item.Properties().durability(800)));
    public static final RegistryObject<Item> DIAMOND_BOW = ITEMS.register("diamond_bow", () -> new BowItem(new Item.Properties().durability(900)));
    public static final RegistryObject<Item> CHAROITE_BOW = ITEMS.register("charoite_bow", () -> new BowItem(new Item.Properties().durability(1000)));
    public static final RegistryObject<Item> HORIZONITE_BOW = ITEMS.register("horizonite_bow", () -> new BowItem(new Item.Properties().durability(1100)));
    public static final RegistryObject<Item> ZANITE_BOW = ITEMS.register("zanite_bow", () -> new BowItem(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> GRAVITITE_BOW = ITEMS.register("gravitite_bow", () -> new BowItem(new Item.Properties().durability(1300)));
    public static final RegistryObject<Item> VALKYRIE_BOW = ITEMS.register("valkyrie_bow", () -> new BowItem(new Item.Properties().durability(1400)));
    public static final RegistryObject<Item> CINCINNASITE_BOW = ITEMS.register("cincinnasite_bow", () -> new BowItem(new Item.Properties().durability(1500)));
    public static final RegistryObject<Item> CINCINNASITE_DIAMOND_BOW = ITEMS.register("cincinnasite_diamond_bow", () -> new BowItem(new Item.Properties().durability(1600)));
    public static final RegistryObject<Item> NETHER_RUBY_BOW = ITEMS.register("nether_ruby_bow", () -> new BowItem(new Item.Properties().durability(1700)));
    public static final RegistryObject<Item> FIRE_RUBY_BOW = ITEMS.register("fire_ruby_bow", () -> new BowItem(new Item.Properties().durability(1800)));
    public static final RegistryObject<Item> NETHERITE_BOW = ITEMS.register("netherite_bow", () -> new BowItem(new Item.Properties().durability(1900)));
    public static final RegistryObject<Item> CLOGGRUM_BOW = ITEMS.register("cloggrum_bow", () -> new BowItem(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> FROSTSTEEL_BOW = ITEMS.register("froststeel_bow", () -> new BowItem(new Item.Properties().durability(2100)));
    public static final RegistryObject<Item> UTHERIUM_BOW = ITEMS.register("utherium_bow", () -> new BowItem(new Item.Properties().durability(2200)));
    public static final RegistryObject<Item> FORGOTTEN_BOW = ITEMS.register("forgotten_bow", () -> new BowItem(new Item.Properties().durability(2300)));
    public static final RegistryObject<Item> AETERNIUM_BOW = ITEMS.register("aeternium_bow", () -> new BowItem(new Item.Properties().durability(2400)));
    public static final RegistryObject<Item> THALLASIUM_BOW = ITEMS.register("thallasium_bow", () -> new BowItem(new Item.Properties().durability(2500)));
    public static final RegistryObject<Item> TERMINITE_BOW = ITEMS.register("terminite_bow", () -> new BowItem(new Item.Properties().durability(2600)));
    public static final RegistryObject<Item> BONE_BOW = ITEMS.register("bone_bow", () -> new BowItem(new Item.Properties().durability(2800)));
    public static final RegistryObject<Item> GARNITE_BOW = ITEMS.register("garnite_bow", () -> new BowItem(new Item.Properties().durability(2900)));
    public static final RegistryObject<Item> IGNISITHE_BOW = ITEMS.register("ignisithe_bow", () -> new BowItem(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> FUSION_BOW = ITEMS.register("fusion_bow", () -> new BowItem(new Item.Properties().durability(3100)));
    public static final RegistryObject<Item> ABERIYTHE_BOW = ITEMS.register("aberythe_bow", () -> new BowItem(new Item.Properties().durability(3200)));
    public static final RegistryObject<Item> UNORITHE_BOW = ITEMS.register("unorithe_bow", () -> new BowItem(new Item.Properties().durability(3300)));
    public static final RegistryObject<Item> PHANTOM_BOW = ITEMS.register("phantom_bow", () -> new BowItem(new Item.Properties().durability(3400)));
    public static final RegistryObject<Item> ICORYTHE_BOW = ITEMS.register("incorythe_bow", () -> new BowItem(new Item.Properties().durability(3500)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
