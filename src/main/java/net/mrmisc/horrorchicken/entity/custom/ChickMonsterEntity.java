package net.mrmisc.horrorchicken.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.LoopType;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChickMonsterEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public ChickMonsterEntity(EntityType<? extends Monster> p_480614_, Level p_480320_) {
        super(p_480614_, p_480320_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0F));
    }

    @Override
    public void tick() {
        if(!level().isClientSide()){
         handleWalkAnimation();
        }
    }

    private boolean wasWalking = false;

    private void handleWalkAnimation() {
        Vec3 motion = getDeltaMovement();
        boolean isWalking =
                (motion.x * motion.x + motion.z * motion.z) > 0.001;

        if (isWalking && !wasWalking) {
            triggerAnim("controller", "chickWalk");
        }

        wasWalking = isWalking;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, (double)4.0F)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F);
    }



    @Override
    public boolean doHurtTarget(ServerLevel p_376642_, Entity p_21372_) {
        triggerAnim("controller", "attack");
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("controller",animTest -> PlayState.STOP)
                .triggerableAnim("walk", WALK)
                .triggerableAnim("attack", ATTACK));
    }

    protected static final RawAnimation WALK =
            RawAnimation.begin().thenLoop("running");

    protected static final RawAnimation ATTACK =
            RawAnimation.begin().then("attack", LoopType.PLAY_ONCE);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
