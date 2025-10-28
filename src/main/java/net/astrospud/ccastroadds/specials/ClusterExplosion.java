package net.astrospud.ccastroadds.specials;

import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClusterExplosion extends Explosion {
    private double x;
    private double y;
    private double z;
    private Entity pentity;
    private float power;

    private World privateWorld = null;

    public ClusterExplosion(World world, @Nullable Entity entity, double px, double py, double pz, float ppower) {
        super(world, entity, px, py, pz, ppower, false, DestructionType.DESTROY);
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
                        double ab = (double)getExposure(vec3d, entity);
                        double ac = (1.0 - w) * ab;
                        privateWorld.createExplosion(entity, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), (float)((int)((ac * ac + ac) / 2.0 * (double)q + 1.0)), World.ExplosionSourceType.BLOCK);
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
