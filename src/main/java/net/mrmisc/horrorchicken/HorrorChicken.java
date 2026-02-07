package net.mrmisc.horrorchicken;

import net.mrmisc.horrorchicken.entity.ModEntities;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import net.mrmisc.horrorchicken.entity.custom.ChickMonsterEntity;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HorrorChicken.MODID)
public class HorrorChicken {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "horrorchicken";

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public HorrorChicken(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);// Note that this is necessary if and only if we want *this* class (HorrorChicken) to respond directly to events.
        ModEntities.ENTITIES.register(modEventBus);
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void registerAtt(EntityAttributeCreationEvent event){
        event.put(ModEntities.CHICK.get(), ChickEntity.createAttributes().build());
        event.put(ModEntities.CHICK_MONSTER.get(), ChickMonsterEntity.createAttributes().build());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
