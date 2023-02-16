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
})