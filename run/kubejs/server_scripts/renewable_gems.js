onEvent('recipes', event => {

    let renewable = (gemId, inputId, secCatalyst, superHeated) => {
        const rec1 = event.recipes.create.mixing([gemId, 'minecraft:golden_carrot'], [inputId, secCatalyst, 'minecraft:golden_carrot'])
        const rec2 = event.recipes.create.mixing([gemId, 'omgamod:spectral_catalyst', secCatalyst], [inputId, secCatalyst, 'omgamod:spectral_catalyst'])
        if (superHeated) {
            rec1.superheated()
            rec2.superheated()
        }
    }

    // Redstone
    renewable("redstone", "red_dye", "glowstone_dust", false)
    // Lapis lazuli
    renewable("lapis_lazuli", "blue_dye", "experience_bottle", false)
    // Emerald
    renewable("emerald", "lime_dye", "glistering_melon_slice", true)
    // Diamond
    renewable("diamond", "light_blue_dye", "minecraft:dragon_head", true)
})