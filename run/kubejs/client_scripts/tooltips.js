var tooltip = null;

const addTooltip = (item, str) => {
    tooltip.addAdvanced(item, (item, advanced, text) => {
    if (!tooltip.shift) {
        text.add(1, ('§8Hold [§7Shift§8] for Summary'))
    } else {
        text.add(1, ('§8Hold [§fShift§8] for Summary'))
        text.add(2, '')
        let i = 3
        str.forEach(st => {

            text.add(i, '§4' + st.replace(/{/g, '§c').replace(/}/g, '§4'));
            i++;
        });
    }
    })
}
const addBonusTooltip = (item, str) => {
    tooltip.addAdvanced(item, (item, advanced, text) => {
        text.add(2, '§7When worn:')
        let i = 3
        str.forEach(st => {
            text.add(i, '§9- ' + st.replace(/{/g, '§a').replace(/}/g, '§9'));
            i++;
        });
    })
}
const addFoodTooltip = (item, str) => {
    tooltip.addAdvanced(item, (item, advanced, text) => {
        text.add(2, '§7When eaten:')
        let i = 3
        str.forEach(st => {
            text.add(i, '§9- ' + st.replace(/{/g, '§a').replace(/}/g, '§9'));
            i++;
        });
    })
}
onEvent("item.tooltip", t => {
    tooltip = t;
    //*
    addTooltip('omgamod:redsteel_block', ["Can act as a strong {light source} when lit by redstone."])
    addTooltip('omgamod:woodsteel_block', ["Can be used as a plant base.", "{Saplings} planted on it will {grow faster}."])
    addTooltip('omgamod:goldsteel_block', ["Can be used in alchemy as philosopher's stone:", "Throw it into {any metal} fluid source, get {molten gold}!"])
    addTooltip('omgamod:creepersteel_block', ["Acts like a {powerful} explosive with an {instant} fuse and {repairable} remainder"])
    addTooltip('omgamod:prismasteel_block', ["Acts as an {infinite} water source: just need a bucket to get all that water!"])
    addTooltip('omgamod:skysteel_block', ["When powered, will {stop} the {day cycle}. When turned off, will {resume} the {time as normal}.", "Only the {last} changed skysteel block will affect the day cycle!"])

    
    addTooltip('omgamod:fertilizer_spray', ["Can be used as {bonemeal}. Should be recharged by {spouting} fertilizer fluid on it."])
    addTooltip('omgamod:fertilizer_spray_empty', ["An empty bottle with spray. Supposed to be {filled} with fertilizer fluid"])
    addTooltip('omgamod:tnt_cannon', ["Can {shoot TNT} or {TNT minecarts}!", "(Latter is {NOT} recommended)."])

    addFoodTooltip('golden_apple', ["Gives {Regeneration II (0:05)} effect which restores 4HP", "Gives {Absorbtion I (2:00)} effect which grants additional 4HP"])
    addFoodTooltip('enchanted_golden_apple', ["Gives {Regeneration II (0:20)} effect which restores 16HP", "Gives {Absorbtion IV (2:00)} effect which grants additional 16HP",
                    "Gives {Resistance I (5:00)} effect, which blocks 20% of incoming damage", "Gives {Fire Resistance (5:00)} effect which grants immunity to fire and lava damage"])
    addFoodTooltip('omgamod:saturation_fruit', ["Gives {Saturation I (0:05)} effect which restores most of your hunger"])

    
    addBonusTooltip('omgamod:night_vision_helm', ["Gives {Night Vision} effect"])
    
    //*/
})