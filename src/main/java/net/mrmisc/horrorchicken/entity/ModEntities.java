package net.mrmisc.horrorchicken.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.mrmisc.horrorchicken.HorrorChicken;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import net.mrmisc.horrorchicken.entity.custom.ChickMonsterEntity;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(HorrorChicken.MODID);

    public static final Supplier<EntityType<@org.jetbrains.annotations.NotNull ChickEntity>> CHICK =
            ENTITIES.register("chick", ()-> EntityType.Builder.of(ChickEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "chick"))));

    public static final Supplier<EntityType<@org.jetbrains.annotations.NotNull ChickMonsterEntity>> CHICK_MONSTER =
            ENTITIES.register("chick_monster", ()-> EntityType.Builder.of(ChickMonsterEntity::new, MobCategory.MONSTER).sized(0.5f, 0.5f).build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "chick_monster"))));
}
