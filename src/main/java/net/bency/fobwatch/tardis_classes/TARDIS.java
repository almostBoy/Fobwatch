package net.bency.fobwatch.tardis_classes;

import net.bency.fobwatch.Fobwatch;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.stream.Stream;


public class TARDIS extends Block{

    public TARDIS(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(STATE, 1));
        setDefaultState(getDefaultState().with(OPEN, false));
        setDefaultState(getDefaultState().with(Properties.FACING, Direction.NORTH));
    }
    // Streams/variables for all the values I need to easily add more exteriors to this specific class.
    // Conventions are in the extended comments below each stream/variable.
    int amountOfExteriors = 4;
    int[][] directionValues = {{1, 5, 9, 13},{2, 6, 10, 14},{3, 7, 11, 15},{4, 8, 12, 16}};
    /*
    [0] = North Facing
    [1] = East Facing
    [2] = South Facing
    [3] = West Facing
     */
    int[][] exteriorValues = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int finalBlockstate = 3;
    /*
    [0] = Factory
    [1] = Nine
    [2] = Eleven
    [3] = Thirteen
    Highest [n] should be defined in finalBlockstate
     */
    int[][] portalshapeValues = {{1, 2, 3, 4}, {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}};
    /*
    [0] = Factory
    [1] = Policebox
     */

    //The method for registering blocks.
    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(Fobwatch.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }

    //The custom blockstates the TARDIS has
    public static final IntProperty STATE = IntProperty.of("state",1,16);
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    //The Declaration of all the blockstates the TARDIS has
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE, OPEN, Properties.FACING);
    }

    public static final Block TARDIS = register(new TARDIS(AbstractBlock.Settings.create().
                    strength(999999999, 999999999).
                    sounds(BlockSoundGroup.WOOD).
                    pistonBehavior(PistonBehavior.IGNORE).
                    noBlockBreakParticles()),
            "tardis",
            false);


    @Override // Allows the player to interact with the TARDIS through right clicks.
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {



        int tardisState = state.get(STATE);
        boolean doorState = state.get(OPEN);
        // If the player is holding a compass, the TARDIS will rotate.
        if(player.getMainHandStack().getItem() == Components.DIRECTIONAL_REMOTE){
            if(!player.getItemCooldownManager().isCoolingDown(Components.DIRECTIONAL_REMOTE)){
                if(Arrays.stream(directionValues[3]).anyMatch(match -> match == tardisState)){
                    world.setBlockState(pos, state.with(STATE, tardisState-3));
                }else{
                    world.setBlockState(pos, state.with(STATE, tardisState+1));
                }
                world.playSound(player, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS);
                player.getItemCooldownManager().set(Components.DIRECTIONAL_REMOTE, 100);
                return ActionResult.SUCCESS;
            } else {return ActionResult.FAIL;}
            }

        //If holding a breeze rod (placeholder item, going to implement a different one), exterior will cycle.
        else if (player.getMainHandStack().getItem() == Components.CHAMELEON_REMOTE){
            if(!player.getItemCooldownManager().isCoolingDown(Components.CHAMELEON_REMOTE)){
                if(Arrays.stream(exteriorValues[finalBlockstate]).anyMatch(match -> match == tardisState)){
                    world.setBlockState(pos, state.with(STATE, tardisState-((amountOfExteriors-1)*4)));
                }else{
                    world.setBlockState(pos, state.with(STATE, tardisState+4));
                }
                world.playSound(player, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS);
                player.getItemCooldownManager().set(Components.CHAMELEON_REMOTE, 100);
                return ActionResult.SUCCESS;
            } else{return ActionResult.FAIL;}
            }

        // If hand is empty, door open/shut will cycle.
        else {
            world.setBlockState(pos, state.with(OPEN, !doorState));
            if (doorState){
                world.playSound(player, pos, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS);
            }else{
                world.playSound(player, pos, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS);
            }
            return ActionResult.SUCCESS;}
    }
    @Override // Defines the shape of the outline the TARDIS shows when hovered over.
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(-1, -16, -1, 17, 23, 17);
    }
    @Override // The shape with which the player physically interacts
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int tardisState = state.get(STATE);
        boolean doorsOpen = state.get(OPEN);
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
        return Block.createCuboidShape(-1, -16, -1, 17, 23, 17);
    }
    @Override // The shape with which the 3rd person camera physically interacts
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int tardisState = state.get(STATE);
        boolean doorsOpen = state.get(OPEN);
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
        return Block.createCuboidShape(-1, -16, -1, 17, 23, 17);
    }
    @Override // Defines the points at which the player is looking at the TARDIS
    protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return Block.createCuboidShape(-1, -16, -1, 17, 23, 17);
    }

    public static void init() { Components.init(); TARDIS_Spawner.spawnTARDIS(); }


}
