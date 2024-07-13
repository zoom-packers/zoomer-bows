package com.pandaismyname1.zoomerbows;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Zoomer_bows.MODID)
public class Zoomer_bows {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "zoomer_bows";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "zoomer_bows" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(MODID, () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> Bows.IRON_BOW.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
            output.accept(Bows.IRON_BOW.get());
            output.accept(Bows.PYROPE_BOW.get());
            output.accept(Bows.AQUITE_BOW.get());
            output.accept(Bows.DIOPSIDE_BOW.get());
            output.accept(Bows.DIAMOND_BOW.get());
            output.accept(Bows.CHAROITE_BOW.get());
            output.accept(Bows.HORIZONITE_BOW.get());
            output.accept(Bows.ZANITE_BOW.get());
            output.accept(Bows.GRAVITITE_BOW.get());
            output.accept(Bows.VALKYRIE_BOW.get());
            output.accept(Bows.CINCINNASITE_BOW.get());
            output.accept(Bows.CINCINNASITE_DIAMOND_BOW.get());
            output.accept(Bows.NETHER_RUBY_BOW.get());
            output.accept(Bows.FIRE_RUBY_BOW.get());
            output.accept(Bows.NETHERITE_BOW.get());
            output.accept(Bows.CLOGGRUM_BOW.get());
            output.accept(Bows.FROSTSTEEL_BOW.get());
            output.accept(Bows.UTHERIUM_BOW.get());
            output.accept(Bows.FORGOTTEN_BOW.get());
            output.accept(Bows.AETERNIUM_BOW.get());
            output.accept(Bows.THALLASIUM_BOW.get());
            output.accept(Bows.TERMINITE_BOW.get());
            output.accept(Bows.WARDEN_BOW.get());
            output.accept(Bows.BONE_BOW.get());
            output.accept(Bows.GARNITE_BOW.get());
            output.accept(Bows.IGNISITHE_BOW.get());
            output.accept(Bows.FUSION_BOW.get());
            output.accept(Bows.ABERIYTHE_BOW.get());
            output.accept(Bows.UNORITHE_BOW.get());
            output.accept(Bows.PHANTOM_BOW.get());
            output.accept(Bows.ICORYTHE_BOW.get());
            }).build());

    public Zoomer_bows() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        Bows.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            ModItemProperties.addBowProperties();
        }
    }
}
