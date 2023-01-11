

/**
 * TC melting
 * @example
 * // melt oak_log into 1 bucket of diamond fluid
 * melt(event, "minecraft:oak_log", "tconstruct:molten_diamond", 900, 200, 10);
 * 
 */
const melt = (event, itemid, fluidOut, outAmount, temp, time) => {
    try {
    event.custom({
        "type": "tconstruct:melting",
        "ingredient": { "item": itemid },
        "result": {
            "fluid": fluidOut,
            "amount": outAmount
        },
        "temperature": temp,
        "time": time
    })
    } catch (e) {
        console.log(e)
    }
}
/**
 * TC melting. It uses tag ID instead of item ID as input.
 * @example
 * // melt any type of wooden doorns into 2 gems of diamond fluid
 * meltTag(event, "minecraft:wooden_doors", "tconstruct:molten_diamond", 200, 1200, 15);
 * 
 */
const meltTag = (event, tagid, fluidOut, outAmount, temp, time) => {
    try {
    event.custom({
        "type": "tconstruct:melting",
        "ingredient": { "tag": tagid },
        "result": {
            "fluid": fluidOut,
            "amount": outAmount
        },
        "temperature": temp,
        "time": time
    })
    } catch (e) {
        console.log(e)
    }
}
/**
 * TC alloying
 * @param {[:{fluidId:any,amount:any}]} inputs
 * @example
 * // alloy 2 golden ingots and 1 chocolate bar into 0.5 honey buckets
 * alloy(event, [{"name": "tconstruct:molten_gold", "amount": 200}, {"name": "create:chocolate", "amount": 250}], "create:honey", 500, 50)
 */
const alloy = (event, inputs, fluidOut, outAmount, temp) => {
    try {
    event.custom({
        "type": "tconstruct:alloy",
        "inputs": inputs,
		"result": {
			"fluid": fluidOut,
			"amount": outAmount
		},
		"temperature": temp
    })
    } catch (e) {
        console.log(e)
    }
}
const cast_block = (event, fluid, item) => {
    try {
        event.custom({
            "type": "tconstruct:casting_basin",
            "fluid": { "name": fluid, "amount": 1296 },
            "result": { "item": item },
            "cooling_time": 150
        })
    } catch (e) {
        console.log(e)
    }
}

const cast = (event, type, fluid, amount, item, time) => {
    try {
        event.custom({
            "type": "tconstruct:casting_table",
            "cast": { "tag": "tconstruct:casts/single_use/" + type },
            "cast_consumed": true,
            "fluid": { "name": fluid, "amount": amount },
            "result": { "item": item },
            "cooling_time": time
        })
        event.custom({
            "type": "tconstruct:casting_table",
            "cast": { "tag": "tconstruct:casts/multi_use/" + type },
            "fluid": { "name": fluid, "amount": amount },
            "result": { "item": item },
            "cooling_time": time
        })
    } catch (e) {
        console.log(e)
    }
}
const castCustom = (event, castedOnItem, fluid, amount, item, time) => {
    try {
        event.custom({
            "type": "tconstruct:casting_table",
            "cast": { "item": castedOnItem },
            "cast_consumed": true,
            "fluid": { "name": fluid, "amount": amount },
            "result": { "item": item },
            "cooling_time": time
        })
        event.custom({
            "type": "tconstruct:casting_table",
            "cast": { "tag": "tconstruct:casts/multi_use/" + type },
            "fluid": { "name": fluid, "amount": amount },
            "result": { "item": item },
            "cooling_time": time
        })
    } catch (e) {
        console.log(e)
    }
}

const ingot = 90;
let handleMaterial = (event, modid, name) => {
    const tag = (kind) => "forge:" + kind + "/" + name;
    const item = (kind) => modid + ":" + name + "_" + kind;
    
    /*
    meltTag(event, tag("ingots"), fluid, ingot, temp, timeMult * 60)
    meltTag(event, tag("plates"), fluid, ingot, temp, timeMult * 60)
    meltTag(event, tag("nuggets"), fluid, ingot / 9, temp, timeMult * 60)
    meltTag(event, tag("storage_blocks"), fluid, ingot * 9, temp, timeMult * 60)
    */
    const ingotExists = (Item.exists(item("ingot")))
    const blockExists = (Item.exists(item("block")))
    const nuggetExists = (Item.exists(item("nugget")))
    const plateExists = (Item.exists(item("plate")))

    if (plateExists && ingotExists) {
        event.recipes.create.pressing(item("plate"), item("ingot")).id("create:pressing/" + name + "_plate")
    }
    if (blockExists && ingotExists) {
        event.shaped(item('block'), [
            'SSS',
            'SSS',
            'SSS'
            ], {
            S: item('ingot')
            }).id(modid + ":" + name + "_block")
        event.shapeless('9x ' + item('ingot'), [item('block')]).id(modid + ":" + name + "_ingot_from_" + name + "_block")
    }
    if (nuggetExists && ingotExists) {
        event.shaped(item('ingot'), [
            'SSS',
            'SSS',
            'SSS'
            ], {
            S: item('nugget')
            }).id(modid + ":" + name + "_ingot_from_nuggets")
        event.shapeless('9x ' + item('nugget'), [item('ingot')]).id(modid + ":" + name + "_nugget")
    }
    
}

let handleTags = (event, modid, name) => {
    const tag = (kind) => "forge:" + kind + "/" + name;
    const forgetag = (kind) => "forge:" + kind;
    const item = (kind) => modid + ":" + name + "_" + kind;
    const ingotExists = (Item.exists(item("ingot")))
    const blockExists = (Item.exists(item("block")))
    const nuggetExists = (Item.exists(item("nugget")))
    const plateExists = (Item.exists(item("plate")))


    if (ingotExists) {
        event.get(tag('ingots')).add(item('ingot'))
        event.get(forgetag('ingots')).add(item('ingot'))
    }
    if (blockExists) {
        event.get(tag('storage_blocks')).add(item('block'))
        event.get(forgetag('storage_blocks')).add(item('block'))
        event.get(tag("minecraft:beacon_base_blocks")).add(item('block'))
    }
    if (nuggetExists) {
        event.get(tag('nuggets')).add(item('nugget'))
        event.get(forgetag('nuggets')).add(item('nugget'))
    }
    if (plateExists) {
        event.get(tag('plates')).add(item('plate'))
        event.get(forgetag('plates')).add(item('plate'))
    }
}