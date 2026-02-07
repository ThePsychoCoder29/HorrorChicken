package net.mrmisc.horrorchicken.entity.custom.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.mrmisc.horrorchicken.entity.custom.ChickEntity;
import net.mrmisc.horrorchicken.entity.custom.model.ChickModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class ChickRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<ChickEntity, R> {
    public ChickRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickModel());
    }
}
