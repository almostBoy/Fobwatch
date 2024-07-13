package net.bency.fobwatch.tardis_classes;

import net.bency.fobwatch.Fobwatch;
import net.bency.fobwatch.FobwatchSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.portal.Portal;

import javax.sound.sampled.Port;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Stream;


public class tardisBlock extends Block{
    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Fobwatch.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }
    public static int amountOfExteriors = 7;
    public static int[][] directionValues = {{1, 5, 9, 13, 17, 21, 25},{2, 6, 10, 14, 18, 22, 26},{3, 7, 11, 15, 19, 23, 27},{4, 8, 12, 16, 20, 24, 28}};
    /*
    [0] = North Facing
    [1] = East Facing
    [2] = South Facing
    [3] = West Facing
     */
    public static int[][] exteriorValues = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20},{21, 22, 23, 24},{25, 26, 27, 28}};
    public static int finalBlockstate = amountOfExteriors-1;
    /*
    [0] = Factory
    [1] = Nine
    [2] = Eleven
    [3] = Thirteen
    Highest [n] should be defined in finalBlockstate
     */
    public static int[][] portalshapeValues = {{1, 2, 3, 4}, {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28}};
    /*
    [0] = Factory
    [1] = Policebox
     */

    public static final IntProperty EXTERIORSTATE = IntProperty.of("exteriorstate",1,amountOfExteriors*4);
    public static final IntProperty FLIGHTSTATE = IntProperty.of("flightstate", 1, 3);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(EXTERIORSTATE,FLIGHTSTATE,Properties.OPEN,Properties.FACING);
    }

    public tardisBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(EXTERIORSTATE,1));
        setDefaultState(getDefaultState().with(FLIGHTSTATE,1));
        setDefaultState(getDefaultState().with(Properties.OPEN,false));
        setDefaultState(getDefaultState().with(Properties.FACING, Direction.NORTH));
    }

    public static Block TARDIS = register(new tardisBlock(AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.WOOD)
            .strength(1000000000,1000000000)
            .noBlockBreakParticles()
            .pistonBehavior(PistonBehavior.IGNORE)
            .nonOpaque()),
            "tardis", false
    );

    @Override
    public ActionResult onUse
            (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int exteriorState = state.get(EXTERIORSTATE);
        boolean openQuery = state.get(Properties.OPEN);

        if (state.get(FLIGHTSTATE) == 1) {
            if(openQuery){
                world.setBlockState(pos, state.with(Properties.OPEN, false));
                world.playSound(player, pos, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS);
            }else {
                if (player.getMainHandStack().getItem() == Components.DIRECTIONAL_REMOTE) {
                    if (!player.getItemCooldownManager().isCoolingDown(Components.DIRECTIONAL_REMOTE)) {
                        if (Arrays.stream(directionValues[3]).anyMatch(match -> match == exteriorState)) {
                            world.setBlockState(pos, state.with(EXTERIORSTATE, exteriorState - 3));
                        } else {
                            world.setBlockState(pos, state.with(EXTERIORSTATE, exteriorState + 1));
                        }
                        world.playSound(player, pos, SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.BLOCKS);
                        player.getItemCooldownManager().set(Components.DIRECTIONAL_REMOTE, 20);
                    }
                } else if (player.getMainHandStack().getItem() == Components.CHAMELEON_REMOTE) {
                    if (!player.getItemCooldownManager().isCoolingDown(Components.CHAMELEON_REMOTE)) {
                        if (Arrays.stream(exteriorValues[finalBlockstate]).anyMatch(match -> match == exteriorState)) {
                            world.setBlockState(pos, state.with(EXTERIORSTATE, exteriorState - ((finalBlockstate) * 4)));
                        } else {
                            world.setBlockState(pos, state.with(EXTERIORSTATE, exteriorState + 4));
                        }
                        world.playSound(player, pos, FobwatchSounds.CLOISTER_BELL, SoundCategory.BLOCKS);
                        player.getItemCooldownManager().set(Components.CHAMELEON_REMOTE, 20);
                    }
                }else{
                    world.setBlockState(pos, state.with(Properties.OPEN, true));
                    world.playSound(player, pos, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS);
                }
            }

        }return ActionResult.PASS;
    }

    @Override
    public VoxelShape getCollisionShape
            (BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(FLIGHTSTATE) == 1){
            int tardisState = state.get(EXTERIORSTATE);
            boolean doorsOpen = state.get(Properties.OPEN);
            if (doorsOpen) {
                if (Arrays.stream(directionValues[0]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, 16, 17, 23, 17),
                            Block.createCuboidShape(-1, -16, -2, 0, 23, 16),
                            Block.createCuboidShape(16, -16, -2, 17, 23, 16),
                            Block.createCuboidShape(-1, 23, -2, 17, 24, 16)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[1]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, -1, 0, 23, 17),
                            Block.createCuboidShape(0, -16, -1, 18, 23, 0),
                            Block.createCuboidShape(0, -16, 16, 18, 23, 17),
                            Block.createCuboidShape(0, 23, -1, 18, 24, 17)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[2]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, -1, 17, 23, 0),
                            Block.createCuboidShape(16, -16, 0, 17, 23, 18),
                            Block.createCuboidShape(-1, -16, 0, 0, 23, 18),
                            Block.createCuboidShape(-1, 23, 0, 17, 24, 18)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[3]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(16, -16, -1, 17, 23, 17),
                            Block.createCuboidShape(-2, -16, 16, 16, 23, 17),
                            Block.createCuboidShape(-2, -16, -1, 16, 23, 0),
                            Block.createCuboidShape(-2, 23, -1, 16, 24, 17)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
            }else{
                return Stream.of(
                        Block.createCuboidShape(-1, -16, 16, 17, 23, 17),
                        Block.createCuboidShape(-1, -16, -1, 17, 23, 0),
                        Block.createCuboidShape(16, -16, -1, 17, 23, 17),
                        Block.createCuboidShape(-1, -16, -1, 0, 23, 17),
                        Block.createCuboidShape(0, 22, 0, 16, 24, 16)
                ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        }
    }
        return VoxelShapes.empty();
    }
    @Override
    public VoxelShape getCameraCollisionShape
            (BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(FLIGHTSTATE) == 1){
            int tardisState = state.get(EXTERIORSTATE);
            boolean doorsOpen = state.get(Properties.OPEN);
            if (doorsOpen) {
                if (Arrays.stream(directionValues[0]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, 16, 17, 23, 17),
                            Block.createCuboidShape(-1, -16, -2, 0, 23, 16),
                            Block.createCuboidShape(16, -16, -2, 17, 23, 16),
                            Block.createCuboidShape(-1, 23, -2, 17, 24, 16)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[1]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, -1, 0, 23, 17),
                            Block.createCuboidShape(0, -16, -1, 18, 23, 0),
                            Block.createCuboidShape(0, -16, 16, 18, 23, 17),
                            Block.createCuboidShape(0, 23, -1, 18, 24, 17)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[2]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(-1, -16, -1, 17, 23, 0),
                            Block.createCuboidShape(16, -16, 0, 17, 23, 18),
                            Block.createCuboidShape(-1, -16, 0, 0, 23, 18),
                            Block.createCuboidShape(-1, 23, 0, 17, 24, 18)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
                else if (Arrays.stream(directionValues[3]).anyMatch(match -> match == tardisState)) {
                    return Stream.of(
                            Block.createCuboidShape(16, -16, -1, 17, 23, 17),
                            Block.createCuboidShape(-2, -16, 16, 16, 23, 17),
                            Block.createCuboidShape(-2, -16, -1, 16, 23, 0),
                            Block.createCuboidShape(-2, 23, -1, 16, 24, 17)
                    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
                }
            }else{
                return Stream.of(
                        Block.createCuboidShape(-1, -16, 16, 17, 23, 17),
                        Block.createCuboidShape(-1, -16, -1, 17, 23, 0),
                        Block.createCuboidShape(16, -16, -1, 17, 23, 17),
                        Block.createCuboidShape(-1, -16, -1, 0, 23, 17),
                        Block.createCuboidShape(0, 22, 0, 16, 24, 16)
                ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
            }
        }
        return VoxelShapes.empty();

    }
    @Override
    public VoxelShape getOutlineShape
            (BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(FLIGHTSTATE) == 1) {
            return Block.createCuboidShape(0, -16, -2, 16, 23, 18);
        }else{
            return VoxelShapes.empty();
        }
    }
    @Override
    public VoxelShape getRaycastShape
            (BlockState state, BlockView world, BlockPos pos) {
        if(state.get(FLIGHTSTATE) == 1) {
            return Block.createCuboidShape(0, -16, -2, 16, 23, 18);
        }else{
            return VoxelShapes.empty();
        }
    }

    public static void init(){Components.init(); TardisSpawningItem.spawnTARDIS();}
}



