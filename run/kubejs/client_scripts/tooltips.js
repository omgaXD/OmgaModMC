
// Used to initialize the tooltips, one by one.
// Called once by the lang generator.

// TooltipType determines layout of tooltip
// Multiple tooltip types can be used by one item

// Tooltips can be either strings or lists of strings. Separation of lines by \n or by using lists "example1\nexample2" / ["example1", "example2"]

// General function signature:
// add('examplemod:myitem', [['en_us', ['line1', 'line2']], ['ru_ru', ['линия1', 'линия2']]], TooltipType.WhenWorn)

function init() {

    add('omgamod:redsteel_block', [['en_us', "Can act as a strong {light source} when lit by redstone."]])
    add('omgamod:woodsteel_block', [['en_us', ["Can be used as a plant base.", "{Saplings} planted on it will {grow faster}."]]])
    add('omgamod:goldsteel_block', [['en_us', ["Can be used in alchemy as philosopher's stone:", "Throw it into {any metal} fluid source, get {molten gold}!"]]])
    add('omgamod:creepersteel_block', [['en_us', "Acts like a {powerful} explosive with an {instant} fuse and {repairable} remainder"]])
    add('omgamod:prismasteel_block', [['en_us', "Acts as an {infinite} water source: just need a bucket to get all that water!"]])
    add('omgamod:skysteel_block', [['en_us', ["When powered, will {stop} the {day cycle}. When turned off, will {resume} the {time as normal}.", "Only the {last} changed skysteel block will affect the day cycle!"]]])
    
    add('omgamod:fertilizer_spray', [['en_us', "Can be used as {bonemeal}. Should be recharged by {spouting} fertilizer fluid on it."]])
    add('omgamod:fertilizer_spray_empty', [['en_us', "An empty bottle with spray. Supposed to be {filled} with fertilizer fluid"]])
    add('omgamod:tnt_cannon', [['en_us', ["Can {shoot TNT} or {TNT minecarts}!", "(Latter is {NOT} recommended)."]]])

    add('golden_apple', [['en_us', ["Gives {<effect.minecraft.regeneration> II (0:05)} effect which restores 4HP", "Gives {<effect.minecraft.absorption> I (2:00)} effect which grants additional 4HP"]]], TooltipType.WhenEaten)
    add('enchanted_golden_apple', [['en_us', ["Gives {<effect.minecraft.regeneration> II (0:20)} effect which restores 16HP", "Gives {<effect.minecraft.absorption> IV (2:00)} effect which grants additional 16HP",
                    "Gives {<effect.minecraft.resistance> I (5:00)} effect, which blocks 20% of incoming damage", "Gives {<effect.minecraft.fire_resistance> (5:00)} effect which grants immunity to fire and lava damage"]]], TooltipType.WhenEaten)

    add('omgamod:saturation_fruit', [['en_us', "Gives {<effect.minecraft.saturation> I (0:05)} effect which restores most of your hunger"]], TooltipType.WhenEaten)

    
    add('omgamod:night_vision_helm', [['en_us', "Grants {<effect.minecraft.night_vision>} effect"]], TooltipType.WhenWorn)

    add('omgamod:prismasteel_flippers', [['en_us', "{Lightweight} flippers to make your water journey {faster}!"]])
    add('omgamod:prismasteel_flippers', [['en_us', "Swimming speed {+150%}\nThis effect stacks with {<effect.minecraft.dolphins_grace>}"]], TooltipType.WhenWorn)

    add('omgamod:skyseekers', [['en_us', "Very {durable} wings used by builders and factory designers. Let {sky} be your {friend}!"]])
    add('omgamod:skyseekers', [['en_us', "Grants ability to {fly}\nGrants {slow fall}\nHold {Shift} to fall faster\nImmunity to fall damage"]], "worn")

    addBasic('forge.attack')
}





// Copy 'en_us' and change values to the locale you need.
const util_tooltips = {
    'en_us': {
        "item.kubejs.tooltip.hold_shift_off": "§8Hold [§7Shift§8] for Summary",
        "item.kubejs.tooltip.hold_shift_on": "§8Hold [§fShift§8] for Summary",
        "item.kubejs.tooltip.when_worn": "§7When worn:",
        "item.kubejs.tooltip.when_used": "§7When used:",
        "item.kubejs.tooltip.when_eaten": "§7When eaten:",
        "item.kubejs.tooltip.full_set": "§7Full set bonus:"
    },
    'ru_ru': {
        "item.kubejs.tooltip.hold_shift_off": "§8Удерживайте [§7Shift§8], чтобы узнать больше",
        "item.kubejs.tooltip.hold_shift_on": "§8Удерживайте [§fShift§8], чтобы узнать больше",
        "item.kubejs.tooltip.when_worn": "§7Когда надето:",
        "item.kubejs.tooltip.when_used": "§7Когда использовано:",
        "item.kubejs.tooltip.when_eaten": "§7Когда съедено:",
        "item.kubejs.tooltip.full_set": "§7Бонус полного комплекта:"
    }
}

















// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # BEHIND THE SCENES # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //
// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # BEHIND THE SCENES # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //
// # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # BEHIND THE SCENES # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # //
/** 
 * @returns {Internal.Minecraft}
 */
function getMinecraft() {return Minecraft.getInstance()}

const Minecraft = java('net.minecraft.client.Minecraft')

const hold_shift_off = "item.kubejs.tooltip.hold_shift_off";
const hold_shift_on = "item.kubejs.tooltip.hold_shift_on";
const when_worn = "item.kubejs.tooltip.when_worn";
const when_used = "item.kubejs.tooltip.when_used";
const when_eaten = "item.kubejs.tooltip.when_eaten";
const full_set = "item.kubejs.tooltip.full_set";



// enum for different tooltip presets
/**
 * @typedef {"create" | "worn" | "eaten" | "used"} TooltipType
 * @type {TooltipType}
 */
const TooltipType = {
    Create: "create",
    WhenWorn: "worn",
    WhenEaten: "eaten",
    WhenUsed: "used"
}
/**  used for function below it to count and number entires
 * */
let indexCounter = 0

/** initializes a create-style tooltip
 * @param {Internal.Item} item
 * @param {[String, [String]]} lang_entry_pair_array
 * @param {TooltipType} type
 * @returns {[Number, Internal.Item, TooltipType, [[String, [String]]]]}
 */
function add(item, lang_entry_pair_array, type) {
    // null/undefined check for type
    type = type || TooltipType.Create;
    if (!itemHaveTooltipsSet.has(item))
        itemHaveTooltipsSet.add(item)
    const result = [indexCounter++, item, type, lang_entry_pair_array]
    tooltipList.push(result)
    // return is not necessary but I keep it in case I need it in future
    return result
}












/** adds a lang entry
 * @param {String} key
 * @param {[String, [String]]} values
 */
function addBasic(key, values) {
   
}





/** list for all the tooltips
 * @type {[Number, Internal.Item, TooltipType, [[String, [String]]]]}
 */
let tooltipList = []

/** set of all items that have tooltips
 * @type {Set<Internal.Item>}
 */
let itemHaveTooltipsSet = new Set()







// shortcut function for index-langkey generator
let langKey = (i) => {
    return ('item.kubejs.tooltip.tooltip_' + i)
}



let defaultDashEntry = (tt_entry) => tt_entry = '§9- ' + tt_entry.replace(/{/g, '§a').replace(/}/g, '§9').replace(/\n/g, '\n§9- ');




onEvent("client.generate_assets", event => {
    // call the init to generate all needed stuff
    init()

    /**using map to hold every locale's json separately
     * @type {Map<string, Object>}
     */ 
    let lang_jsonMap = new Map()

    // for every single accumulated array object, add the entry
    tooltipList.forEach(obj => {
        let i = obj[0]
        let item = obj[1]
        let tooltipType = obj[2]
        let arr = obj[3]
        arr.forEach(obj2 => {
            let lang = obj2[0]
            /**
             * @type {String}
             */
            let tt_entry = obj2[1]
            if (Array.isArray(tt_entry)) {
                tt_entry = Array.from(tt_entry).join('\n')
            }
           
            // Tooltip entry modification according to type
            switch (tooltipType) {
                case TooltipType.Create: 
                    // parts inside {curly braces} are highlighted
                    tt_entry = '§4' + tt_entry.replace(/{/g, '§c').replace(/}/g, '§4').replace(/\n/g, '\n§4');
                    break;
                case TooltipType.WhenEaten:
                    // add "- " instead of "\n" (as in enumerating properties). Also highlight parts in {curly braces}
                    defaultDashEntry(tt_entry)
                    break;
                case TooltipType.WhenUsed:
                    defaultDashEntry(tt_entry)
                    break;
                case TooltipType.WhenWorn:
                    defaultDashEntry(tt_entry)
                    break;
            }

            if (lang_jsonMap.has(lang)) {
                let jsonEntry =  lang_jsonMap.get(lang);
                jsonEntry[langKey(i)] = tt_entry
                lang_jsonMap.set(lang, jsonEntry)
            } else {
                let jsonEntry = util_tooltips[lang] || jsonEntry['en_us']
                jsonEntry[langKey(i)] = tt_entry;
                lang_jsonMap.set(lang,  jsonEntry)
            }
        })
    })
    lang_jsonMap.forEach((obj, locale) => {
        event.add("kubejs:lang/" + locale, obj)
    })
})


let addShift = (event, item) => {
    event.addAdvanced(item, (_1, _2, component) => {
        if (!event.shift) {
            component.add(1, Component.translate(hold_shift_off))
        } else {
            component.add(1, Component.translate(hold_shift_on))
        }
    })
}











// Event that (I believe) runs when game wants to create a tooltip for an item (or maybe prepare all tooltips for all items?)
onEvent("item.tooltip", event => {

    /**@type {Set<Internal.Item>} */
    itemHaveTooltipsSet.forEach(item => {
       addShift(event, item)
    })
    
    /**@type {Set<Internal.Item>} */
    let whenwornSet = new Set()
    
    tooltipList.slice().reverse().forEach(obj => {
        let i = obj[0]
        let item = obj[1]
        let type = obj[2]
        event.addAdvanced(item, (_1, _2, component) => {
            if (!event.shift) {
            } else {
                let index = 2;
                switch (type) {
                    case (TooltipType.WhenEaten):
                        component.add(index++, Component.translate(when_eaten))
                        break;
                    case (TooltipType.WhenUsed):
                        component.add(index++, Component.translate(when_used))
                        break;
                    case (TooltipType.WhenWorn):
                        component.add(index++, Component.translate(when_worn))
                        whenwornSet.add(item)
                        break;
                }
                Component.translate(langKey(i)).getString().split('\n').reverse().forEach(str => {
                    let str2 = ""
                    let open = -1
                    let i = 0
                    str.split('').forEach(c => {
                        if (c == '<') {
                            open = i
                        } else if (c == '>') {
                            if (open >= 0) {
                                console.log(str.slice(open + 1, i))
                                str2 += Component.translate(str.slice(open + 1, i)).getString()
                                open = -1
                            }
                        } else {
                            if (open == -1) {
                                str2 += c
                            }
                        }
                        
                        i++
                    })
                    
                    component.add(index, defaultDashEntry(str2))
                })
                
            }
        })
        
    })
})
