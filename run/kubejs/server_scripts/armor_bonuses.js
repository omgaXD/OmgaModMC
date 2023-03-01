// priority: 1




function initArmor() {
    addMobEffect(singleItem('diamond_boots', 'feet'), 'speed', 2, true)
    addAttribute(fullSet('iron_helmet', 'iron_chestplate', 'iron_leggings', 'iron_boots'), 'generic.max_health', 4, 'addition')
    addAttribute(singleItem('iron_boots', 'feet'), 'generic.movement_speed', -0.05, 'multiply_total')
}






// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //
// ############################ BEHIND THE SCENES ###########################//
// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //



/**
 * @type {[[number, (function(Internal.PlayerJS): boolean), function(Internal.PlayerJS): void, ?function(Internal.PlayerJS): void]]}
 */
let bonuses = []
let index = 0


//#region PredicateGen
/**
 * 
 * @param {Internal.ItemStackJS_} item 
 * @param {EquipmentSlot_} slot 
 *
 * @returns {function(Internal.PlayerJS): boolean}
*/
function singleItem(item, slot) {
    /**
     * @param {Internal.PlayerJS} player 
     */
    return (player) => {
        return player.getEquipment(slot).equals(item)
    }
}
//#region PredicateGen
/**
 * 
 * @param {Internal.ItemStackJS_} head 
 * @param {Internal.ItemStackJS_} chest 
 * @param {Internal.ItemStackJS_} legs 
 * @param {Internal.ItemStackJS_} feet 
 * @returns {function(Internal.PlayerJS): boolean}
*/
function fullSet(head, chest, legs, feet) {
    /**
     * @param {Internal.PlayerJS} player 
     */
    return (player) => {
        return  player.getEquipment('head').equals(head) &&
                player.getEquipment('chest').equals(chest) &&
                player.getEquipment('legs').equals(legs) &&
                player.getEquipment('feet').equals(feet)
    }
}
//#endregion
/**
 * 
 * @param {function(Internal.PlayerJS): boolean} itemPredicate 
 * @param {function(Internal.PlayerJS): void} action 
 * @param {function(Internal.PlayerJS): void} cancel 
 */
function addAction(itemPredicate, action, cancel) {
    bonuses.push([index++, itemPredicate, action, cancel])
}

/**
 * @param {function(Internal.PlayerJS): boolean} itemPredicate 
 * @param {Internal.MobEffect_} effect 
 * @param {number} amplifier
 * @param {boolean} showParticles
 */
function addMobEffect(itemPredicate, effect, amplifier, showParticles) {
    /**
     * @param {Internal.PlayerJS} player
     */
    
    addAction(itemPredicate, (player) => {player.getPotionEffects().add(effect, 2, amplifier, false, showParticles)}, null)
}
/**
 * @param {function(Internal.PlayerJS): boolean} itemPredicate 
 * @param {Internal.Attribute_} attribute 
 * @param {number} value
 * @param {Internal.AttributeModifier$Operation_} operation
 */
function addAttribute(itemPredicate, attribute, value, operation) {
    let id = 'kubejs:armor_bonus_' + index
    /**
     * @param {Internal.PlayerJS} player
     */
    let f = (player) => {
        player.modifyAttribute(attribute, id, value, operation)
    }
     /**
     * @param {Internal.PlayerJS} player
     */
    let f2 = (player) => {
        player.removeAttribute(attribute, id)
        player.setHealth(Math.min(player.health, player.getMaxHealth()))
    }
    addAction(itemPredicate, f, f2)
}

onEvent("server.datapack.low_priority", e => {
    initArmor()
})

onEvent("player.tick", e => {
    bonuses.forEach(bonus => {
        if (bonus[1](e.player)) {
            bonus[2](e.player)
        } else {
            if (bonus[3] !== null) {
                bonus[3](e.player)
            }
        }
    })
})