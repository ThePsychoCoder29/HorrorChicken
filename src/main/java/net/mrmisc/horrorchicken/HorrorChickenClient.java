package net.mrmisc.horrorchicken;

import net.minecraft.client.Minecraft;
import net.mrmisc.horrorchicken.entity.ModEntities;
import net.mrmisc.horrorchicken.entity.custom.renderer.ChickMonsterRenderer;
import net.mrmisc.horrorchicken.entity.custom.renderer.ChickRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = HorrorChicken.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = HorrorChicken.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class HorrorChickenClient {

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CHICK.get(), ChickRenderer::new);
        event.registerEntityRenderer(ModEntities.CHICK_MONSTER.get(), ChickMonsterRenderer::new);
    }
}
