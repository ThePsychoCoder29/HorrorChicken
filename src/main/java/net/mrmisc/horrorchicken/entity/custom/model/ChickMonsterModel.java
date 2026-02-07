package net.mrmisc.horrorchicken.entity.custom.model;

import net.minecraft.resources.Identifier;
import net.mrmisc.horrorchicken.HorrorChicken;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import net.mrmisc.horrorchicken.entity.custom.ChickMonsterEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ChickMonsterModel extends GeoModel<ChickMonsterEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState geoRenderState) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "geckolib/models/entity/chick_monster.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState geoRenderState) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "textures/entity/chick_monster.png");

    }

    @Override
    public Identifier getAnimationResource(ChickMonsterEntity chickEntity) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "geckolib/animations/entity/chick_monster.animation.geo.json");
    }
}
