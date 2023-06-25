

onEvent('entity.loot_tables', (e) => {
    e.modifyEntity('wandering_trader', table =>{
        table.addPool(pool => {
            pool.addItem('wandering_trader_spawn_egg')
        })
    })
    e.modifyEntity('skeleton', table => {
        table.addPool(pool => {
            pool.addItem('omgamod:steel_ingot').randomChance("0.025")
        })
    }) 
    e.modifyEntity('witch', table => {
        table.clearPools()
        table.addPool(pool => {
            pool.addItem('redstone', 2, [1, 6])
            pool.addItem('glowstone', 1, [1, 6])
            pool.addItem('sugar', 1, [1, 6])
        })
        table.addPool(pool => {
            pool.addItem('stick').randomChance(0.5)
        })
    })
    e.modifyEntity('enderman', table => {
        table.addPool((pool) => {
            pool.addCondition({
                "condition": "minecraft:location_check",
                "predicate": {
                    "dimension": "minecraft:the_end"
                }
            })
            pool.addItem('minecraft:end_stone', 1, [3, 5])
        })
        table.addPool((pool) => {
            pool.addCondition({
                "condition": "minecraft:location_check",
                "predicate": {
                    "dimension": "minecraft:the_end"
                }
            })
            pool.addItem('chorus_flower').randomChance(0.1)
        })
    })
})