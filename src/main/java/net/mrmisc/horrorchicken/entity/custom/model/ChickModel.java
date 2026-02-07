package net.mrmisc.horrorchicken.entity.custom.model;

import net.minecraft.resources.Identifier;
import net.mrmisc.horrorchicken.HorrorChicken;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ChickModel extends GeoModel<ChickEntity> {
    @Override
    public Identifier getModelResource(GeoRenderState geoRenderState) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "geckolib/models/entity/chick.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState geoRenderState) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "textures/entity/chick.png");

    }

    @Override
    public Identifier getAnimationResource(ChickEntity chickEntity) {
        return Identifier.fromNamespaceAndPath(HorrorChicken.MODID, "geckolib/animations/entity/chick.animation.geo.json");
    }
}
