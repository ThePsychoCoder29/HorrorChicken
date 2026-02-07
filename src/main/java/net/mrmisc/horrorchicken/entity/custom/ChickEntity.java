package net.mrmisc.horrorchicken.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.mrmisc.horrorchicken.entity.ModEntities;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.object.LoopType;
import software.bernie.geckolib.animation.object.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChickEntity extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public ChickEntity(EntityType<? extends PathfinderMob> p_480614_, Level p_480320_) {
        super(p_480614_, p_480320_);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if(player.getItemInHand(hand).is(Items.ROTTEN_FLESH) && !level().isClientSide() && isNightTime(level())){
            this.setDeltaMovement(0, 0, 0);
            double x = getX();
            double y = getY();
            double z = getZ();

            float yaw = getYRot();
            float pitch = getXRot();
            float bodyYaw = yBodyRot;
            float headYaw = yHeadRot;

            Vec3 motion = getDeltaMovement();


            ChickMonsterEntity monster =
                    ModEntities.CHICK_MONSTER.get().create(level(), EntitySpawnReason.MOB_SUMMONED);

            if (monster == null) return InteractionResult.FAIL;


            monster.teleportTo((ServerLevel) this.level(), x, y, z, Set.of(),yaw, pitch, false);
            monster.setYBodyRot(bodyYaw);
            monster.setYHeadRot(headYaw);


            monster.setDeltaMovement(motion);


            monster.setHealth(monster.getMaxHealth());


            level().addFreshEntity(monster);
            discard();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0F, (p_481852_) -> p_481852_.is(ItemTags.CHICKEN_FOOD), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, (double)4.0F)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F);
    }


    private boolean isNightTime(Level level){
        long day = level.getDayTime() % 24000L;
        return day >= 13000L && day <= 23000L;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("animCon", 5,animTest -> animTest.setAndContinue(handleWalkAnimation() ? WALK : DefaultAnimations.IDLE))
                .triggerableAnim("chickWalk", WALK));

    }


    private boolean handleWalkAnimation() {
        Vec3 motion = getDeltaMovement();
        return !motion.equals(new Vec3(0, 0, 0));
    }


    protected static final RawAnimation WALK =
            RawAnimation.begin().thenLoop("chickWalk");

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
