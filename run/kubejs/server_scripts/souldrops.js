onEvent('entity.death', soulsLogic) 

let spawnSoulAt = (e, playSound) => {
    console.log("spawning soul at " + e.x + " " + e.y + " " + e.z)
    let itemEntity = e.level.createEntity("item")
    itemEntity.setPosition(e.x, e.y, e.z)
    itemEntity.item = Item.of("omgamod:freed_soul")
    itemEntity.item.count = 1
    itemEntity.noGravity = true
    itemEntity.motionY = 0.12
    itemEntity.pickupDelay = 30
    itemEntity.spawn()
    if (playSound) {
        itemEntity.playSound("entity.blaze.death") 
    }
}

let soulsLogic = (event) => {
    if (!checkSoulDrops(event)) return;
    const e = event.getEntity()
    if (has_souls.includes(e.type)) {
        if (Math.random() <= 0.25) {
            spawnSoulAt(e, true)
        }
    } else if (guaranteed_souls.includes(e.type)) {
        spawnSoulAt(e, true)
    }
    else if (three_souls.includes(e.type)) {
        spawnSoulAt(e, true)
        spawnSoulAt(e, false)
        spawnSoulAt(e, false)
    }
}

let checkSoulDrops = (event) => {
    for (const player of event.level.players) {
        if (player.stages.has("drop_souls")) {
            return true;
        }
    }
    return false;
}

onEvent('player.logged_in', event => {
    if (event.player.stages.has("drop_souls")) {
        for (const player of event.level.players) {
            if (player.stages.has("drop_souls")) continue;
            player.stages.give("drop_souls")
        }
    } else {
        for (const player of event.level.players) {
            if (player.stages.has("drop_souls")) {
                event.player.stages.give("drop_souls")
                break;
            }
        }
    }
})