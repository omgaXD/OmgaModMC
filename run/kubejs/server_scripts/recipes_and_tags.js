// priority: 0

settings.logAddedRecipes = true
settings.logRemovedRecipes = true
settings.logSkippedRecipes = false
settings.logErroringRecipes = true


onEvent('recipes', event => {
	// STEEL
	handleMaterial(event, 'omgamod', 'steel')
	event.shaped('omgamod:steel_ingot', ['nnn', 'nIn', 'nnn'], {
		n: '#forge:nuggets/copper',
		I: 'minecraft:iron_ingot'
	})
	event.recipes.create.mixing('2x omgamod:steel_ingot', ['minecraft:iron_ingot', 'minecraft:copper_ingot']).heated()

	// COLORFUL MATERIALS
	handleMaterial(event, 'omgamod', 'redsteel')
	event.shapeless('omgamod:redsteel_ingot', ['omgamod:steel_ingot', '4x minecraft:redstone'])

	handleMaterial(event, 'omgamod', 'woodsteel')
	event.shapeless('omgamod:woodsteel_ingot', 
					['minecraft:birch_sapling', 'omgamod:redsteel_ingot', 'minecraft:acacia_sapling', 
					'omgamod:redsteel_ingot', '#oak_saplings', 'omgamod:redsteel_ingot', 
					'minecraft:jungle_sapling', 'omgamod:redsteel_ingot', 'minecraft:spruce_sapling'])

	handleMaterial(event, 'omgamod', 'goldsteel')
	event.shapeless('omgamod:goldsteel_blend', 
					['minecraft:golden_apple', 'omgamod:woodsteel_plate', 'minecraft:glistering_melon_slice',
					'omgamod:woodsteel_plate', 'minecraft:glow_berries', 'omgamod:woodsteel_plate',
					'minecraft:golden_carrot', 'omgamod:woodsteel_plate', 'minecraft:sunflower'
					]);
	handleMaterial(event, 'omgamod', 'creepersteel')
	handleMaterial(event, 'omgamod', 'prismasteel')
	
	// NETHERITE
	event.recipes.create.pressing('omgamod:netherite_plate', "minecraft:netherite_ingot");
	cast(event, "plate", "tconstruct:molten_netherite", ingot, "omgamod:netherite_plate", 60)

	// FOOD
    event.shaped('omgamod:gshroom', [
    '121', '232', '121'
    ], {
    1: 'minecraft:gold_nugget',
    2: '#shrooms',
    3: '#fungi'
    })
	event.recipes.createMixing(
		['omgamod:saturation_fruit', 'minecraft:glistering_melon_slice'], 
		['minecraft:glistering_melon_slice', 'omgamod:gshroom', '2x minecraft:sugar']
	)

	// COLORFUL STEEL GEAR
	const inter1 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:night_vision_helm'],'minecraft:iron_helmet',[
		event.recipes.createFilling(inter1, [inter1, Fluid.of('omgamod:molten_redsteel', ingot)]),
		event.recipes.createDeploying(inter1, [inter1, 'minecraft:glass']),
		event.recipes.createDeploying(inter1, [inter1, 'minecraft:string']),
		event.recipes.createPressing(inter1, inter1)
	]).transitionalItem(inter1).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter2 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:fertilizer_spray_empty'],'minecraft:glass_bottle',[ 
	event.recipes.createFilling(inter2, [inter2, Fluid.of('omgamod:molten_woodsteel', ingot)]),
	event.recipes.createDeploying(inter2, [inter2, 'create:mechanical_pump']),
	event.recipes.createDeploying(inter2, [inter2, 'minecraft:lever']),
	event.recipes.create.cutting(inter2, inter2)
	]).transitionalItem(inter2).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter3 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:goldsteel_drill'],"create:mechanical_drill",[ 
	event.recipes.createFilling(inter3, [inter3, Fluid.of('omgamod:molten_goldsteel', ingot)]),
	event.recipes.createDeploying(inter3, [inter3, 'minecraft:redstone']),
	event.recipes.createDeploying(inter3, [inter3, "create:cogwheel"]),
	event.recipes.create.pressing(inter3, inter3)
	]).transitionalItem(inter3).loops(4) // set the transitional item and the loops (amount of repetitions)

	const inter4 = 'create:incomplete_precision_mechanism'
	event.recipes.createSequencedAssembly(['omgamod:tnt_cannon'],"create:potato_cannon",[ 
	event.recipes.createFilling(inter4, [inter4, Fluid.of('omgamod:molten_creepersteel', ingot)]),
	event.recipes.createDeploying(inter4, [inter4, 'minecraft:flint_and_steel']),
	event.recipes.createDeploying(inter4, [inter4, "minecraft:dropper"]),
	event.recipes.create.cutting(inter4, inter4)
	]).transitionalItem(inter4).loops(4) // set the transitional item and the loops (amount of repetitions)

	
	


	// MISC

	event.shapeless('omgamod:spectral_catalyst', ['8x omgamod:freed_soul', 'minecraft:lapis_lazuli'])
	event.recipes.create.mixing(Fluid.of('kubejs:fertilizer', 250), ['minecraft:bone_meal', Fluid.of('minecraft:water', 250), '2x ' + "#minecraft:flowers"])
	event.recipes.create.mixing(Fluid.of('kubejs:fertilizer', 500), [Fluid.of('minecraft:water', 250), Fluid.of('omgamod:molten_woodsteel', 250)])

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
	
})

onEvent('item.tags', event => {
	// Get the #forge:cobblestone tag collection and add Diamond Ore to it
	// event.get('forge:cobblestone').add('minecraft:diamond_ore')

	// Get the #forge:cobblestone tag collection and remove Mossy Cobblestone from it
	// event.get('forge:cobblestone').remove('minecraft:mossy_cobblestone')
	handleTags(event, 'omgamod', 'steel')
	handleTags(event, 'omgamod', 'redsteel')
	handleTags(event, 'omgamod', 'woodsteel')
	handleTags(event, 'omgamod', 'goldsteel')
	handleTags(event, 'omgamod', 'creepersteel')
	handleTags(event, 'omgamod', 'prismasteel')


	event.get('shrooms').add(['minecraft:brown_mushroom', 'minecraft:red_mushroom'])
	event.get('fungi').add(['minecraft:warped_fungus', 'minecraft:crimson_fungus'])
	event.get('oak_saplings').add(['minecraft:oak_sapling', 'minecraft:dark_oak_sapling'])
	
})
onEvent('block.tags', event => {
	//event.add('omgamod:steel', "minecraft:")
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

onEvent('fluid.tags', event => {
    event.get("forge:molten_redsteel").add("omgamod:molten_redsteel")
	event.get("forge:molten_woodsteel").add("omgamod:molten_woodsteel")
	event.get("forge:molten_goldsteel").add("omgamod:molten_goldsteel")
	event.get("forge:molten_creepersteel").add("omgamod:molten_creepersteel")
	event.get("forge:molten_prismasteel").add("omgamod:molten_prismasteel")
})
onEvent('block.tags', event => {
    event.get("minecraft:dirt").add("omgamod:woodsteel_block")
})
