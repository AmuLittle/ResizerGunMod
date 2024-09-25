package com.github.amulittle.resizer_gun.entity;

import java.util.List;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.register.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ResizeFieldInsulatorMarker extends Entity {
    private static String CHILD_DIRECTION_KEY = "ChildDirection";
    private static String SHOULD_KILL_KEY = "ShouldKill";

    private Direction childDirection = Direction.UP;
    private boolean shouldKill = true;
    
    public ResizeFieldInsulatorMarker(EntityType<? extends ResizeFieldInsulatorMarker> entity_type, World world) {
        super(entity_type, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        // nothing
    }

     @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString(CHILD_DIRECTION_KEY, childDirection.getName());
        nbt.putBoolean(SHOULD_KILL_KEY, shouldKill);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains(CHILD_DIRECTION_KEY)) this.childDirection = Direction.byName(nbt.getString(CHILD_DIRECTION_KEY));
        if (nbt.contains(SHOULD_KILL_KEY)) this.shouldKill = nbt.getBoolean(SHOULD_KILL_KEY);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        readCustomDataFromNbt(nbt);
    }

    // if parent entity does not exist then kill
    // if child entity does not exist and there is no ResizeFieldInsulatorBlockerBlock in the way then create a child
    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();
        BlockPos pos = this.getBlockPos();
        BlockPos targetPos;
        switch (childDirection) {
            case UP:
                targetPos = pos.up();
                break;
            case DOWN:
                targetPos = pos.down();
                break;
            case NORTH:
                targetPos = pos.north();
                break;
            case EAST:
                targetPos = pos.east();
                break;
            case SOUTH:
                targetPos = pos.south();
                break;
            case WEST:
                targetPos = pos.west();
                break;
            default:
                targetPos = pos;
        }
        if (world.isInBuildLimit(targetPos)) {
            int chunkX = 0;
            int posX = targetPos.getX();
            if (posX < 0) {
                chunkX = ((int)(posX / 16)) - 1;
            }
            else {
                chunkX = (int)(posX / 16);
            }
            int chunkZ = 0;
            int posZ = targetPos.getZ();
            if (posZ < 0) {
                chunkZ = ((int)(posZ / 16)) - 1;
            }
            else {
                chunkZ = (int)(posZ / 16);
            }
            if (world.isChunkLoaded(chunkX, chunkZ)) {
                Vec3d targetVec = new Vec3d(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                List<ResizeFieldInsulatorMarker> entities = world.getEntitiesByClass(ResizeFieldInsulatorMarker.class, Box.of(targetVec, 1, 1, 1), (entity) -> {
                    return ((ResizeFieldInsulatorMarker)entity).getDirection() == this.childDirection && this.getId() != entity.getId();
                });
                if (entities.size() >= 1) {
                    if (shouldKill) {
                        entities.get(0).setShouldKill(true);
                        this.discard();
                    }
                }
                else if (shouldKill) {
                    this.discard();
                }
                else {
                    ResizeFieldInsulatorMarker marker = new ResizeFieldInsulatorMarker(EntityRegister.RESIZE_FIELD_INSULATOR_MARKER, world);
                    marker.setPos(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                    marker.setShouldKill(false);
                    marker.setDirection(childDirection);
                    world.spawnEntity(marker);
                    ResizerGunMod.LOGGER.info("making new child at " + targetPos.toString());
                }
            }
        }
        else if (shouldKill) {
            this.discard();
        }

    }

    public void setShouldKill(boolean b) {
        this.shouldKill = b;
    }

    public Direction getDirection() {
        return this.childDirection;
    }

    public void setDirection(Direction d) {
        this.childDirection = d;
    }
}
