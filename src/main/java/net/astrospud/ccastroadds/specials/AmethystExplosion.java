package net.astrospud.ccastroadds.specials;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;


import java.lang.reflect.Field;
import java.util.List;

public class AmethystExplosion extends Explosion {
    private double x;
    private double y;
    private double z;
    private Entity pentity;
    private float power;

    private World privateWorld = null;

    public AmethystExplosion(World world, @Nullable Entity entity, double px, double py, double pz, float ppower) {
        super(world, entity, px, py, pz, ppower);
        privateWorld = world;
        x=px;
        y=py;
        z=pz;
        power = ppower;
        pentity = entity;
    }

    @Override
    public void collectBlocksAndDamageEntities() {

        float q = Math.abs(this.power * 3.0F);
        int k = MathHelper.floor(this.x - (double)q - 1.0);
        int l = MathHelper.floor(this.x + (double)q + 1.0);
        int r = MathHelper.floor(this.y - (double)q - 1.0);
        int s = MathHelper.floor(this.y + (double)q + 1.0);
        int t = MathHelper.floor(this.z - (double)q - 1.0);
        int u = MathHelper.floor(this.z + (double)q + 1.0);

        List<Entity> list = this.privateWorld.getOtherEntities((Entity) this.pentity, new Box((double)k, (double)r, (double)t, (double)l, (double)s, (double)u));
        Vec3d vec3d = new Vec3d(this.x, this.y, this.z);

        for(int v = 0; v < list.size(); ++v) {
            Entity entity = (Entity)list.get(v);
            if (!(entity instanceof ItemEntity) && entity != pentity && !(entity instanceof ExperienceOrbEntity)) {
                double w = Math.sqrt(entity.squaredDistanceTo(vec3d)) / (double)q;
                if (w <= 1.0) {
                    double x = entity.getX() - this.x;
                    double y = (entity instanceof TntEntity ? entity.getY() : entity.getEyeY()) - this.y;
                    double z = entity.getZ() - this.z;
                    double aa = Math.sqrt(x * x + y * y + z * z);
                    if (aa != 0.0) {
                        x /= aa;
                        y /= aa;
                        z /= aa;
                        double ab = (double)getExposure(vec3d, entity);
                        double ac = (1.0 - w) * ab;
                        double ad = ac;
                        if (entity instanceof LivingEntity) {
                            ad = ProtectionEnchantment.transformExplosionKnockback((LivingEntity)entity, ac);
                        }
                        entity.setVelocity(entity.getVelocity().add(x * ad*2, (y * ad / 3)+0.2, z * ad*2));
                        if (entity instanceof PlayerEntity) {
                            PlayerEntity playerEntity = (PlayerEntity)entity;
                            if (!playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.getAbilities().flying)) {
                                getAffectedPlayers().put(playerEntity, new Vec3d(x * ac*2, (y * ac / 3)+0.2, z * ac*2));
                            }
                        }
                    }
                }
            }
        }
    }

   // @Override
  //  public void affectWorld(boolean particles) {
   //     if (pentity == null) { return; }
      //  World pworld = pentity.getWorld();
       // if (pworld.isClient) {
        //    pworld.playSound(this.x, this.y, this.z, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 10.0F, (0.75F + (pworld.random.nextFloat() - pworld.random.nextFloat()) * 0.2F) * 0.7F, false);
       //     pworld.playSound(this.x, this.y, this.z, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 10.0F, (0.75F + (pworld.random.nextFloat() - pworld.random.nextFloat()) * 0.2F) * 0.7F, false);
     //   }
    //}
}
