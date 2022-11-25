onEvent("ponder.tag", (event) =>{
    /**
     * "kubejs:getting_started" -> the tag name
     * "minecraft:paper"        -> the icon
     * "Getting Started"        -> the title
     * "This is a description"  -> the description
     * [...items]               -> default items
     */
    event.createTag("kubejs:getting_started", "omgamod:freed_soul", "Omgamod", "Making sense out of tech and magic!", [
        // some default items
        "omgamod:goldsteel_blend",
        "omgamod:creepersteel_block"
    ]);
});

onEvent("ponder.registry", (event) => {
    event.create(["omgamod:goldsteel_blend", "omgamod:goldsteel_ingot"]).scene("goldsteel_ingot_crafting", "Crafting goldsteel ingot", "kubejs:goldsteel_blend", (scene, util) => {
        scene.showStructure();
        scene.scaleSceneView(0.8)
        scene.world.setKineticSpeed([0, 6, 2, 1, 6, 2], -24)
        scene.idle(20)
        scene.showControls(40, [0.5, 6.7, 2.5], "up")
            .withItem("omgamod:goldsteel_blend")


        scene.idle(5)
        scene.showControls(40, [2.8, 6.5, 2.5], "right")
            .withItem("create:honey_bucket")

        scene.idle(5)
        scene.showControls(40, [2.8, 4.5, 2.5], "right")
            .withItem("tconstruct:blazing_blood_bucket")

        scene.idle(5)
        scene.showControls(40, [2.8, 2.5, 2.5], "right")
            .withItem("tconstruct:molten_gold_bucket")
            
        

        scene.idle(40);
        

        scene.idle(10);
        scene.addKeyframe()
        
        const blend = scene.world.createItemEntity([0.5, 7, 2.5], [0, 0, 0], "omgamod:goldsteel_blend");
        scene.idle(10);
        //scene.world.removeEntity(blend);
        scene.world.removeEntity(blend)
        scene.world.createItemOnBelt([0, 6, 2], Direction.DOWN, "omgamod:goldsteel_blend")
        scene.idle(20)
        scene.rotateCameraY(180)
        scene.idle(39)
        scene.world.setBlock([2, 2, 2], 0, true)
        scene.idle(10)
        scene.idle(5)
        scene.showControls(40, [2.5, 6.5, 2.5], "right")
            .withItem("create:honey_bucket")

        scene.idle(5)
        scene.showControls(40, [2.5, 4.5, 2.5], "right")
            .withItem("tconstruct:blazing_blood_bucket")

        scene.idle(5)
        scene.overlay.showOutline(PonderPalette.RED, "no_fluid", [2.5, 2.5, 2.5, 2.5, 2.5, 2.5], 40)
        scene.showControls(40, [2.5, 2.5, 2.5], "right")
            .withItem("bucket")
        scene.text(30, "Consumed.", [2.5, 2.5, 2.5]).colored(PonderPalette.RED)
        scene.idle(5)
        scene.showControls(40, [3.0, 1.85, 2.5], "left")
            .withItem("omgamod:goldsteel_ingot")  
        
        scene.idle(40)
        scene.rotateCameraY(180)
        scene.idle(20)
        scene.addKeyframe()
        scene.world.setKineticSpeed([0, 6, 2, 1, 6, 2], -32)
        // SECOND PART
        scene.world.setBlock([2, 2, 2], "tconstruct:molten_gold_fluid", true)
        //scene.world.modifyBlock([2, 2, 2], () => Block.id("tconstruct:molten_gol"))
        scene.idle(5)
        const blend64 = scene.world.createItemEntity([0.5, 7, 2.5], [0, 0, 0], "64x omgamod:goldsteel_blend")
        scene.idle(10);
        //scene.world.removeEntity(blend);
        scene.world.removeEntity(blend64)
        scene.world.createItemOnBelt([0, 6, 2], Direction.DOWN, "64x omgamod:goldsteel_blend")
        scene.idle(20)
        scene.rotateCameraY(180)
        scene.idle(35)
        scene.world.setBlock([2, 2, 2], 0, true)
        scene.showControls(40, [3.0, 1.85, 2.5], "left")
            .withItem("64x omgamod:goldsteel_ingot")
        scene.text(60, "No matter the amount of goldsteel blend, it'll always successfuly convert into goldsteel ingots.",[3, 1.85, 2.5])
        scene.idle(80)

    })
    event.create(["omgamod:creepersteel_block", "omgamod:goldsteel_block"]).scene("creepersteel_block_craft", "Crafting creepersteel", "kubejs:base_plate", (scene, util) => {
        scene.showBasePlate()
        scene.idle(5)


        scene.world.setBlock([2, 1, 2], Block.id("minecraft:tnt"), true)
        scene.world.showSection([2, 1, 2], Direction.DOWN)

        scene.text(50, "Exactly four goldsteel blocks should be placed in area adjastent to the explosive.", [2.5, 2, 2.5]).placeNearTarget().attachKeyFrame()
        scene.idle(50)

        const id = "omgamod:goldsteel_block"

        scene.world.setBlock([2, 1, 3], Block.id(id), true)
        scene.world.showSection([2, 1, 3], Direction.NORTH)
        scene.idle(5)

        scene.world.setBlock([3, 1, 2], Block.id(id), true)
        scene.world.showSection([3, 1, 2], Direction.WEST)
        scene.idle(5)

        scene.world.setBlock([2, 1, 1], Block.id(id), true)
        scene.world.showSection([2, 1, 1], Direction.SOUTH)
        scene.idle(5)

        scene.world.setBlock([1, 1, 2], Block.id(id), true)
        scene.world.showSection([1, 1, 2], Direction.EAST)
        scene.idle(10)

        scene.text(50, "The explosion will then convert them to creepersteel block.").attachKeyFrame()
        scene.idle(10)
        scene.showControls(30, [2.5, 2, 2.5], "down").rightClick().withItem("minecraft:flint_and_steel")
        scene.world.setBlock([2, 1, 2], 0, false);
        scene.world.createEntity("tnt", [2.5, 1, 2.5])
        scene.idle(80)
        scene.world.setBlocks([1, 1, 1, 3, 1, 3], false, 0)
        scene.particles.simple(1, "minecraft:explosion", [2.5, 1, 2.5])
        scene.world.setBlock([2, 1, 2], Block.id("omgamod:creepersteel_block"), false)
        scene.idle(10)
        scene.text(40, "The creepersteel block spawns right in the origin of explosion.", [2.5, 1.5, 2.5]).attachKeyFrame().placeNearTarget()
        scene.idle(40)
        scene.world.hideSection([1, 1, 1, 3, 1, 3], Direction.UP);
        scene.idle(20)
        scene.world.setBlock([2, 1, 2], Block.id("minecraft:tnt"), false)
        scene.rotateCameraY(90)
        scene.world.showSection([2, 1, 2], Direction.DOWN)
        scene.idle(20)
        scene.world.setBlock([2, 1, 3], Block.id(id), true)
        scene.world.showSection([2, 1, 3], Direction.NORTH)
        scene.idle(5)

        scene.world.setBlock([3, 1, 2], Block.id(id), true)
        scene.world.showSection([3, 1, 2], Direction.WEST)
        scene.idle(5)

        scene.world.setBlock([2, 1, 1], Block.id(id), true)
        scene.world.showSection([2, 1, 1], Direction.SOUTH)
        scene.idle(5)

        scene.world.setBlock([1, 1, 2], Block.id(id), true)
        scene.world.showSection([1, 1, 2], Direction.EAST)
        scene.idle(5)

        scene.world.setBlock([2, 2, 3], Block.id(id), true)
        scene.world.showSection([2, 2, 3], Direction.NORTH)
        scene.idle(5)

        scene.world.setBlock([1, 2, 2], Block.id(id), true)
        scene.world.showSection([1, 2, 2], Direction.EAST)
        scene.idle(10)

        scene.text(50, "If amount of goldsteel blocks isn't four, the conversion won't happen.").attachKeyFrame()
        scene.idle(10)
        scene.showControls(30, [2.5, 2, 2.5], "down").rightClick().withItem("minecraft:flint_and_steel")
        scene.world.setBlock([2, 1, 2], 0, false)
        const ent = scene.world.createEntity("tnt", [2.5, 1, 2.5])
        scene.idle(80)
        scene.world.setBlocks([1, 1, 1, 3, 2, 3], false, 0)
        scene.particles.simple(1, "minecraft:explosion", [2.5, 1, 2.5])
        scene.world.createItemEntity([2.4, 2, 2.8], [0, 0.25, 0], "6x omgamod:goldsteel_block")
    })
})