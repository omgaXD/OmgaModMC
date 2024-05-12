// priority: 0

settings.logAddedRecipes = true
settings.logRemovedRecipes = true
settings.logSkippedRecipes = false
settings.logErroringRecipes = true

onEvent('recipes', event => {
	/// STEEL
	event.shapeless('omgamod:steel_ingot', ['minecraft:iron_ingot', '4x #coal'])
	event.recipes.create.mixing('4x omgamod:steel_ingot', ['2x minecraft:iron_ingot', '3x #coal', '1x redstone']).heated()
	event.recipes.create.mixing('2x omgamod:steel_block', ['minecraft:iron_block', '6x #coal', '2x omgamod:arsenicum', '1x redstone']).heated()

	/// COLORFUL MATERIALS
	all_metals.forEach(metal => handleMaterial(event, 'omgamod', metal))

	event.shapeless('omgamod:redsteel_ingot', ['omgamod:steel_ingot', '4x minecraft:redstone'])

	event.shapeless('omgamod:woodsteel_ingot', 
					['minecraft:birch_sapling', 'omgamod:redsteel_ingot', 'minecraft:acacia_sapling', 
					'omgamod:redsteel_ingot', '#oak_saplings', 'omgamod:redsteel_ingot', 
					'minecraft:jungle_sapling', 'omgamod:redsteel_ingot', 'minecraft:spruce_sapling'])

	event.shapeless('omgamod:goldsteel_blend', 
					['minecraft:golden_apple', 'omgamod:woodsteel_plate', 'minecraft:glistering_melon_slice',
					'omgamod:woodsteel_plate', 'minecraft:glow_berries', 'omgamod:woodsteel_plate',
					'minecraft:golden_carrot', 'omgamod:woodsteel_plate', 'minecraft:sunflower'
					]);
	event.shaped('omgamod:creepersteel_block', ['s',
												's'], {s: "omgamod:creepersteel_slab"})
	
	/// NETHERITE
	event.recipes.create.pressing('omgamod:netherite_plate', "minecraft:netherite_ingot");
	cast(event, "plate", "tconstruct:molten_netherite", ingot, "omgamod:netherite_plate", 60)

	/// FOOD
    event.shaped('omgamod:gshroom', [
    '121', '232', '121'
    ], {
    1: 'minecraft:gold_nugget',
    2: '#shrooms',
    3: '#fungi'
    })
	event.recipes.createMixing(
		['omgamod:saturation_fruit'], 
		['minecraft:glistering_melon_slice', 'omgamod:gshroom', '2x minecraft:sugar', "omgamod:goldsteel_nugget"]
	)

	/// COLORFUL STEEL GEAR
	colorfulGear(event) // set the transitional item and the loops (amount of repetitions)

	
	/// DABIUM LINE
	event.recipes.create.mixing(['omgamod:arsenicum', 'wet_sponge'], [Fluid.of('create:potion', 125, {Potion: 'minecraft:poison'}), 'sponge'])
	event.recipes.create.filling('omgamod:omgium', ['minecraft:emerald', Fluid.of('omgamod:molten_raw_omgium', ingot)])

	/// MISC
	event.shapeless('omgamod:spectral_catalyst', ['8x omgamod:freed_soul', 'minecraft:lapis_lazuli'])
	event.recipes.create.mixing(Fluid.of('kubejs:fertilizer', 250), ['minecraft:bone_meal', Fluid.of('minecraft:water', 250), '2x ' + "#minecraft:flowers"])
	event.recipes.create.mixing(Fluid.of('kubejs:fertilizer', 500), [Fluid.of('minecraft:water', 500), Fluid.of('omgamod:molten_woodsteel', 50)])
	// bedrock substitute
	event.recipes.minecraft.crafting_shaped('omgamod:bedrock_substitute', ['lll','lol','lll'], {l: Ingredient.of("lava_bucket"), o: "gravel"})

	/// SPAWN EGGS
	// Skeleton and Wither skelly
	event.recipes.minecraft.crafting_shapeless('skeleton_spawn_egg', ['4x bone', 'egg', '4x bone'])
	event.recipes.minecraft.crafting_shapeless('wither_skeleton_spawn_egg', ['4x #coal', 'skeleton_spawn_egg', '4x blaze_powder'])
	// Slime and Magma cube
	event.recipes.minecraft.crafting_shapeless('slime_spawn_egg', ['4x slime_ball', 'egg', '4x slime_ball'])
	event.recipes.minecraft.crafting_shapeless('magma_cube_spawn_egg', ['4x blaze_powder', 'slime_spawn_egg', '4x blaze_powder'])
	// Blaze
	event.recipes.minecraft.crafting_shapeless('blaze_spawn_egg', ['4x blaze_rod', 'egg', '4x blaze_powder'])
	// Animals
	event.recipes.minecraft.crafting_shapeless('chicken_spawn_egg', ['4x egg', 'egg', '4x feather'])
	event.recipes.minecraft.crafting_shapeless('cow_spawn_egg', ['4x leather', 'egg', '4x leather'])
	event.recipes.minecraft.crafting_shapeless('sheep_spawn_egg', ['4x white_wool', 'egg', '4x white_wool'])
	// Fish
	const fish_obj = {b: 'water_bucket', e: 'egg'}
	event.recipes.minecraft.crafting_shaped('salmon_spawn_egg', ['b b', ' e ', 'b b'], fish_obj)
	event.recipes.minecraft.crafting_shaped('cod_spawn_egg', [' b ', 'beb', ' b '], fish_obj)
	// Guardian
	event.recipes.minecraft.crafting_shapeless('guardian_spawn_egg', ['4x water_bucket', 'egg', '4x water_bucket'])
	event.recipes.minecraft.crafting_shapeless('guardian_spawn_egg', ['4x prismarine_shard', 'egg', '4x prismarine_shard'])
	


	/// RENEWABILITY
	// renewable netherrack and other stones
	event.recipes.create.filling('netherrack', ['#forge:gravel', Fluid.of('lava', 50)])
	event.recipes.create.filling('dripstone_block', ['#forge:gravel', Fluid.of('water', 50)])
	event.recipes.create.filling('calcite', ['#forge:gravel', Fluid.of('milk', 50)])
	// calcite -> bonemeal
	event.recipes.create.crushing([Item.of('sand'), Item.of('bone_meal').withChance(0.5)], 'calcite')
	// dripstone -> pointed dripstone
	event.recipes.minecraft.stonecutting('4x pointed_dripstone', 'dripstone_block')
	// sand
	event.recipes.create.milling('sand', 'gravel')
	// soul sand
	event.recipes.create.milling('soul_sand', 'netherrack')
	event.recipes.create.compacting('soul_soil', '4x soul_sand')
	// tall grass/fern
	event.shaped('tall_grass', ['h','h'], {h: 'grass'})
	event.shaped('large_fern', ['h','h'], {h: 'fern'})
	// coarse dirt
	event.recipes.minecraft.crafting_shapeless('2x coarse_dirt', ['gravel', 'dirt'])
	// crimson and warped nylium
	event.recipes.create.item_application('crimson_nylium', ['netherrack', 'redstone'])
	event.recipes.create.item_application('warped_nylium', ['netherrack', 'emerald'])
	// -- spawner --
	event.recipes.create.mechanical_crafting('spawner', ['bbb', 'bSb', 'bbb'], {b: 'iron_bars', S: 'omgamod:spectral_catalyst'})


	/// CHANGES
	// cogwheels
	event.remove({'output':'create:cogwheel'})
	event.shaped('8x create:cogwheel', ['bbb', 'bsb', 'bbb'], {
		'b': "#minecraft:wooden_buttons",
		's': "create:shaft"
	})

	event.remove({'output':'create:large_cogwheel'})
	event.shaped('4x create:large_cogwheel', ['bPb', 'PsP', 'bPb'], {
		'b': "#minecraft:wooden_buttons",
		'P': "#minecraft:planks",
		's': "create:shaft"
	})

	// remove red sand millstone - only leave red sand crushing wheel (the gold is locked behind blazes)
	event.remove({'output':'minecraft:red_sand', 'type':'create:milling'})
	event.recipes.create.crushing('minecraft:red_sand', 'minecraft:granite')
	event.recipes.create.crushing('3x minecraft:red_sand', 'minecraft:red_sandstone')

	// add certain thresholds to crafts
	// Mech. mixer -> gold
	event.remove({'output':'create:mechanical_mixer'})
	event.recipes.minecraft.crafting_shaped('create:mechanical_mixer', [' c ', 'rCr', ' w '], {c: 'create:cogwheel', r: 'gold_nugget', C: 'create:andesite_casing', w: 'create:whisk'})

	// Windmill bearing, Drill, Sawmill, Comb., Basket, Spout -> redsteel and emeralds
	event.remove({'output':'create:windmill_bearing'}) // Windmill bearing
	event.remove({'output':'create:mechanical_drill'}) // Mech. Drill
	event.remove({'output':'create:mechanical_saw'}) // Mech. Saw
	event.remove({'output':'create:mechanical_harvester'}) // Mech. Harvester
	event.remove({'output':'farmersdelight:basket'}) // Basket
	event.remove({'output': 'create:spout'}) // Spout
	event.recipes.minecraft.crafting_shaped('create:windmill_bearing', [' t ', 'rCr', ' s '], {t: 'create:turntable', r: 'omgamod:redsteel_nugget', C: 'create:andesite_casing', s: 'create:shaft'})
	event.recipes.minecraft.crafting_shaped('create:mechanical_drill', [' a ', 'aRa', ' C '], {a: 'create:andesite_alloy', R: 'omgamod:redsteel_ingot', C: 'create:andesite_casing'})
	event.recipes.minecraft.crafting_shaped('create:mechanical_saw', [' i ', 'iRi', ' C '], {i: 'create:iron_sheet', R: 'omgamod:redsteel_ingot', C: 'create:andesite_casing'})
	event.recipes.minecraft.crafting_shaped('2x create:mechanical_harvester', ['iii', 'aRa', ' C '], {i: 'create:iron_sheet', a: 'create:andesite_alloy', R: 'omgamod:redsteel_ingot', C: 'create:andesite_casing'})
	event.recipes.minecraft.crafting_shaped('farmersdelight:basket', ['b b', 'cec', 'bcb'], {b: 'bamboo', e: 'emerald', c: "farmersdelight:canvas"})
	event.recipes.minecraft.crafting_shaped('create:spout', ['C', 'r', 'k'], {C: 'create:copper_casing', r: 'omgamod:redsteel_nugget', k: 'dried_kelp'})

	// buffed large_water_wheel at cost of having brass in recipe
	event.remove({'output': 'create:large_water_wheel'})
	event.recipes.minecraft.crafting_shaped('create:large_water_wheel', ['bbb','bWb','bbb'], {b:'create:brass_sheet', W:'create:water_wheel'})

	// ease up lava recipe
	event.remove({'output': Fluid.of('lava'), 'mod':'create'})
	event.recipes.create.mixing(Fluid.of("lava", 5), ["#forge:cobblestone"]).heated()

	// buff gravel recipe
	event.remove({'type':'create:splashing', 'input':'gravel'})
	event.recipes.create.splashing([Item.of('iron_nugget').withChance(0.5)], "gravel")

	// smeltery controller locked behind blaze powder
	event.remove({'output':'tconstruct:smeltery_controller'})
	event.recipes.minecraft.crafting_shaped("tconstruct:smeltery_controller", ['ccc','bSb','ccc'], {c: "copper_ingot", b: 'blaze_powder', S: '#tconstruct:seared_bricks'})

	//#region Fertilizer
	Ingredient.of('#minecraft:tall_flowers').itemIds.forEach(element => {
		event.recipes.create.filling('2x ' + element, [element, Fluid.of('kubejs:fertilizer', 25)])
	});
	const fertSize = 64
	const repairAm = 4
	const repairFluidAm = 25
	for (let i = 1; i < fertSize; i++) {
		event.recipes.create.filling
		(
			 Item.of('omgamod:fertilizer_spray', {Damage: Math.max(i - repairAm, 0)}),
			[Item.of('omgamod:fertilizer_spray', {Damage: i}), 
			Fluid.of('kubejs:fertilizer', repairFluidAm)]
		).id("create:filling/fertilizer_spray_" + (fertSize - i))
	}
	event.recipes.create.filling
	(
		Item.of('omgamod:fertilizer_spray', {Damage: fertSize - repairAm}),
		['omgamod:fertilizer_spray_empty',
		Fluid.of('kubejs:fertilizer', repairFluidAm)]
	).id("create:filling/fertilizer_spray_from_empty")
	//#endregion
})

onEvent('item.tags', event => {
	all_metals.forEach(metal => handleTags(event, 'omgamod', metal))

	event.get('shrooms').add(['minecraft:brown_mushroom', 'minecraft:red_mushroom'])
	event.get('fungi').add(['minecraft:warped_fungus', 'minecraft:crimson_fungus'])
	event.get('oak_saplings').add(['minecraft:oak_sapling', 'minecraft:dark_oak_sapling'])
	event.get('coal').add(['coal', 'charcoal'])
})

const has_souls = [
	"minecraft:zombie",
	"minecraft:skeleton",
	"minecraft:creeper",
	"minecraft:villager",
	"minecraft:piglin"
]
const guaranteed_souls = [
	"minecraft:elder_guardian",
	"minecraft:ender_dragon",
	"minecraft:enderman",
	"minecraft:piglin_brute"
]
const three_souls = ["minecraft:wither"]



let colorful_metals = [
	"redsteel",
    "woodsteel",
    "goldsteel",
    "creepersteel",
    "prismasteel",
    "skysteel",
    "endersteel"
]
let all_metals = [
	"steel"
].concat(colorful_metals)

onEvent('tags.fluids', event => {
	colorful_metals.forEach(metal => {
		event.get("forge:molten_" + metal).add("omgamod:molten_" + metal)
		event.get("tconstruct:tooltips/metal").add("#forge:molten_" + metal)
	})

	event.get("omgamod:normal_water").add(["minecraft:water", "minecraft:flowing_water"])
})

onEvent('block.tags', event => {
    event.get("minecraft:dirt").add("omgamod:woodsteel_block")

	event.get("omgamod:bedrock").add(["bedrock", "omgamod:bedrock_substitute"])

	colorful_metals.forEach(metal => {
		event.get("minecraft:beacon_base_blocks").add("omgamod:" + metal + "_block")
	})
})

function colorfulGear(event) {
	const inter1 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:night_vision_helm'], 'minecraft:iron_helmet', [
		event.recipes.createFilling(inter1, [inter1, Fluid.of('omgamod:molten_redsteel', ingot)]),
		event.recipes.createDeploying(inter1, [inter1, 'minecraft:glass']),
		event.recipes.createDeploying(inter1, [inter1, 'minecraft:string']),
		event.recipes.createPressing(inter1, inter1)
	]).transitionalItem(inter1).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter2 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:fertilizer_spray_empty'], 'minecraft:glass_bottle', [
		event.recipes.createFilling(inter2, [inter2, Fluid.of('omgamod:molten_woodsteel', ingot)]),
		event.recipes.createDeploying(inter2, [inter2, 'create:mechanical_pump']),
		event.recipes.createDeploying(inter2, [inter2, 'minecraft:lever']),
		event.recipes.create.cutting(inter2, inter2)
	]).transitionalItem(inter2).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter3 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:goldsteel_drill'], "create:mechanical_drill", [
		event.recipes.createFilling(inter3, [inter3, Fluid.of('omgamod:molten_goldsteel', ingot)]),
		event.recipes.createDeploying(inter3, [inter3, 'minecraft:redstone']),
		event.recipes.createDeploying(inter3, [inter3, "create:cogwheel"]),
		event.recipes.create.pressing(inter3, inter3)
	]).transitionalItem(inter3).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter4 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:tnt_cannon'], "create:potato_cannon", [
		event.recipes.createFilling(inter4, [inter4, Fluid.of('omgamod:molten_creepersteel', ingot)]),
		event.recipes.createDeploying(inter4, [inter4, 'minecraft:flint_and_steel']),
		event.recipes.createDeploying(inter4, [inter4, "minecraft:dropper"]),
		event.recipes.create.cutting(inter4, inter4)
	]).transitionalItem(inter4).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter5 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:prismasteel_flippers'], "diamond_boots", [
		event.recipes.createFilling(inter5, [inter5, Fluid.of('omgamod:molten_prismasteel', ingot)]),
		event.recipes.createDeploying(inter5, [inter5, Item.of('tconstruct:large_plate', '{Material:"tconstruct:cobalt"}')]),
		event.recipes.createDeploying(inter5, [inter5, "minecraft:phantom_membrane"]),
		event.recipes.create.cutting(inter5, inter5)
	]).transitionalItem(inter5).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter6 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:skyseekers'], "elytra", [
		event.recipes.createFilling(inter6, [inter6, Fluid.of('omgamod:molten_skysteel', ingot)]),
		event.recipes.createDeploying(inter6, [inter6, "minecraft:leather_chestplate"]),
		event.recipes.createDeploying(inter6, [inter6, "feather"]),
		event.recipes.create.pressing(inter6, inter6)
	]).transitionalItem(inter6).loops(4)
}
