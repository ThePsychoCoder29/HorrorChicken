package net.mrmisc.horrorchicken.entity.custom.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import net.mrmisc.horrorchicken.entity.custom.ChickMonsterEntity;
import net.mrmisc.horrorchicken.entity.custom.model.ChickModel;
import net.mrmisc.horrorchicken.entity.custom.model.ChickMonsterModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ChickMonsterRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<ChickMonsterEntity, R> {
    public ChickMonsterRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickMonsterModel());
    }
}
