package com.pandaismyname1.zoomerbows;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addBowProperties() {
        makeBow(Bows.IRON_BOW.get());
        makeBow(Bows.PYROPE_BOW.get());
        makeBow(Bows.AQUITE_BOW.get());
        makeBow(Bows.DIOPSIDE_BOW.get());
        makeBow(Bows.DIAMOND_BOW.get());
        makeBow(Bows.CHAROITE_BOW.get());
        makeBow(Bows.HORIZONITE_BOW.get());
        makeBow(Bows.ZANITE_BOW.get());
        makeBow(Bows.GRAVITITE_BOW.get());
        makeBow(Bows.VALKYRIE_BOW.get());
        makeBow(Bows.CINCINNASITE_BOW.get());
        makeBow(Bows.CINCINNASITE_DIAMOND_BOW.get());
        makeBow(Bows.NETHER_RUBY_BOW.get());
        makeBow(Bows.FIRE_RUBY_BOW.get());
        makeBow(Bows.NETHERITE_BOW.get());
        makeBow(Bows.CLOGGRUM_BOW.get());
        makeBow(Bows.FROSTSTEEL_BOW.get());
        makeBow(Bows.UTHERIUM_BOW.get());
        makeBow(Bows.FORGOTTEN_BOW.get());
        makeBow(Bows.AETERNIUM_BOW.get());
        makeBow(Bows.THALLASIUM_BOW.get());
        makeBow(Bows.TERMINITE_BOW.get());
        makeBow(Bows.BONE_BOW.get());
        makeBow(Bows.GARNITE_BOW.get());
        makeBow(Bows.IGNISITHE_BOW.get());
        makeBow(Bows.FUSION_BOW.get());
        makeBow(Bows.ABERYTHE_BOW.get());
        makeBow(Bows.UNORITHE_BOW.get());
        makeBow(Bows.PHANTOM_BOW.get());
        makeBow(Bows.INCORYTHE_BOW.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (stack, world, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, new ResourceLocation("pulling"), (stack, world, entity, i) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
    }
}
