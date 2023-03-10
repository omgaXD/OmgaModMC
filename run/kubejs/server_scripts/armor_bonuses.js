// priority: 1


function initArmor() {
    addAttribute('diamond_helmet', 'generic.max_health', 4, 'addition')
    addAttribute('diamond_helmet', 'generic.movement_speed', 0.2, 'multiply_base')

    addAttribute('iron_chestplate', 'generic.max_health', 8, 'addition')
    

    addAttribute(['leather_chestplate', 'leather_boots', 'leather_leggings'], 'forge:attack_range', 1, 'addition')
    addAttribute(['leather_chestplate', 'leather_boots', 'leather_leggings'], 'forge:reach_distance', 1, 'addition')
    
    addAttribute(['chainmail_boots', 'chainmail_helmet'], 'generic.movement_speed', 0.3, 'multiply_total')
    addAttribute(['chainmail_boots', 'chainmail_leggings', 'chainmail_chestplate','omgamod:night_vision_helm'], 'generic.movement_speed', 0.5, 'multiply_total')
}






// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //
// ############################ BEHIND THE SCENES ###########################//
// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //



/**
 * 
 * @typedef {number} SetBonusID 
 * SetBonusID is just a number
 * 
 * @typedef {{
    * attribute: Internal.Attribute_, 
    * value: number, 
    * operation: Internal.AttributeModifier$Operation_
 * }} Bonus
 * Bonus is an object holding data in regards to attribute
 * 
 * @typedef {{
    * effect: Internal.MobEffect_
    * amplifier: number
 * }} Effect
 * Effect is an object holding data in regards to MobEffect
 * 
 * @typedef {{
 * fullset_bonuses: Map<Internal.ItemStackJS_, [{set: [Internal.ItemStackJS_], bonuses: [Bonus]}]>
 * ,
 * single_bonuses: Map<Internal.ItemStackJS_, [Bonus]>,
 * ,
 * fullset_effects: Map<Internal.ItemStackJS_, [{set: [Internal.ItemStackJS_], effects: [Effect]}]>
 * ,
 * single_effects: Map<Internal.ItemStackJS_, [Effect]>
 * ,
 * next_index: function() : number,
 * cur_index: function() : number,
 * ,
 * all_items: Set<Internal.ItemStackJS_>
 * }} TooltipInfos
 * @type {TooltipInfos}
 */
let tooltip_infos = {
    fullset_bonuses: new Map(),
    single_bonuses: new Map(),
    fullset_effects: new Map(),
    single_effects: new Map(),
    i: 0,
    next_index: () => ++tooltip_infos.i,
    cur_index: () => tooltip_infos.i,
    all_items: new Set()
}

////////////////////////////////////////////////////////////////////////////
/**
 * @type {[[number, [Internal.ItemStackJS_], function(Internal.PlayerJS): void, ?function(Internal.PlayerJS): void]]}
 */
let bonuses = []
let index = 0


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
    return [head, chest, legs, feet]
}
//#endregion
/**
 * @param {Internal.ArrayOrSelf_<Internal.ItemStackJS_>} itemList
 * @param {function(Internal.PlayerJS): void} action 
 * @param {function(Internal.PlayerJS): void} cancel 
 */
function addAction(itemList, action, cancel) {
    if (!(itemList instanceof Array)) {
        itemList = [itemList]
    } else {
        
    }
    bonuses.push([index++, itemList, action, cancel])
    
}

/**
 * @param {Internal.ArrayOrSelf_<Internal.ItemStackJS_>} itemList
 * @param {Internal.MobEffect_} mobeffect 
 * @param {number} amplifier
 * @param {boolean} showParticles
 */
function addMobEffect(itemList, mobeffect, amplifier, showParticles) {
    var effect = {effect: mobeffect, amplifier: amplifier}
    if (itemList instanceof Array) {
        tooltip_infos.next_index()
        itemList.forEach(item => {
            var effect_info = tooltip_infos.fullset_effects.get(item)
            if (effect_info === undefined) {
                tooltip_infos.fullset_effects.set(item, [{effects: [effect], set: itemList}])
            } else {
                var index = effect_info.findIndex(e => e.set.slice().sort().every((element, index) => itemList.slice().sort()[index] === element))
                if (index == -1) {
                    effect_info.push({set: itemList, effects: [effect]})
                } else {
                    effect_info[index].effects.push(effect)
                }
            }
            tooltip_infos.all_items.add(item)
        })
        
    } else {
        var effect_list = tooltip_infos.single_effects.get(itemList)
        if (effect_list === undefined) {
            tooltip_infos.single_effects.set(itemList, [effect])
        } else {
            effect_list.push(effect)
        }
        tooltip_infos.all_items.add(itemList)
    }
    addAction(itemList, (player) => {player.getPotionEffects().add(mobeffect, 2, amplifier, false, showParticles)}, null)
}
/**
 * @param {Internal.ArrayOrSelf_<Internal.ItemStackJS_>} itemList
 * @param {Internal.Attribute_} attribute 
 * @param {number} value
 * @param {Internal.AttributeModifier$Operation_} operation
 */
function addAttribute(itemList, attribute, value, operation) {
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
    attribute = attribute.replace(/^minecraft:/g, '')
    attribute = attribute.replace(':', '.')
    var bonus = {
        attribute: attribute,
        value: value,
        operation: operation
    }
    if (itemList instanceof Array) {
        tooltip_infos.next_index()
        itemList.forEach(item => {
            var bonus_info = tooltip_infos.fullset_bonuses.get(item)
            if (bonus_info === undefined) {
                tooltip_infos.fullset_bonuses.set(item, [{bonuses: [bonus], set: itemList}])
            } else {
                var index = bonus_info.findIndex(b => b.set.slice().sort().every((element, index) => itemList.slice().sort()[index] === element))
                if (index == -1) {
                    bonus_info.push({set: itemList, bonuses: [bonus]})
                } else {
                    bonus_info[index].bonuses.push(bonus)
                }
            }
            tooltip_infos.all_items.add(item)
        })
        
    } else {
        var bonus_list = tooltip_infos.single_bonuses.get(itemList)
        if (bonus_list === undefined) {
            tooltip_infos.single_bonuses.set(itemList, [bonus])
        } else {
            bonus_list.push(bonus)
        }
        tooltip_infos.all_items.add(itemList)
    }

    addAction(itemList, f, f2)
}
/**
 * 
 * @param {Internal.PlayerJS} player 
 * @param {Internal.ItemStackJS_} item 
 * @returns 
 */
function equippedItem(player, item) {
    return player.getEquipment('head').equals(item) || 
    player.getEquipment('chest').equals(item) || 
    player.getEquipment('legs').equals(item) || 
    player.getEquipment('feet').equals(item)
}

onEvent("server.datapack.low_priority", e => {
    initArmor()
    global.tooltip_infos = tooltip_infos


    /// DEBUG
    /*
    console.log("I'm just a human")
    tooltip_infos.fullset_bonuses.forEach((v, k) => {
        console.log(k.slice() + ": ")
        v.forEach(b => {
            b.bonuses.forEach(bo => {
                if (bo === undefined) {
                    console.log("- FUCK FUCK FUCK")
                } else {
                    console.log("- " + bo.attribute.toString())
                }
            })
        })
    })
    console.log("#### SINGLES ####")
    tooltip_infos.single_bonuses.forEach((v, k) => {
        console.log(k.slice() + ": ")
        v.forEach(bo => {
            if (bo === undefined) {
                console.log("FUCK FUCK FUCK")
            } else {
                console.log("- " + bo.attribute.toString())
            }
        })
    })*/
    global.defined_tooltips = true
})

onEvent("player.tick", e => {
    bonuses.forEach(bonus => {
        let setEquipped = true
        bonus[1].forEach(item => {
            setEquipped = setEquipped && equippedItem(e.player, item)
        })
        if (setEquipped) {
            bonus[2](e.player)
        } else {
            if (bonus[3] !== null) {
                bonus[3](e.player)
            }
        }
    })
})