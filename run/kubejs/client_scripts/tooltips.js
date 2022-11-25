let tooltip = null;

const addTooltip = (item, str) => {
    tooltip.addAdvanced(item, (item, advanced, text) => {
    if (!tooltip.shift) {
        text.add(1, ('§8Hold [§7Shift§8] for summary'))
    } else {
        text.add(1, ('§8Hold [§fShift§8] for summary'))
        text.add(2, '')
        let i = 3
        str.forEach(st => {

            text.add(i, '§4'+st);
            i++;
        });
    }
    })
}

onEvent("item.tooltip", t => {
    tooltip = t;
    //*
    addTooltip('omgamod:redsteel_block', ["Can act as a strong light source when lit by redstone."])
    addTooltip('omgamod:woodsteel_block', ["Can be used as a plant base.", "Saplings planted on it will grow faster."])
    addTooltip('omgamod:goldsteel_block', ["Can be used in alchemy as philosopher's stone:", "Throw it into any metal fluid source, get molten gold!"])

    addTooltip('omgamod:fertilizer_spray', ["Can be used as bonemeal. Should be recharged by spouting fertilizer fluid on it."])
    addTooltip('omgamod:fertilizer_spray_empty', ["An empty bottle with spray. Supposed to be filled with fertilizer fluid"])
    addTooltip('omgamod:tnt_cannon', ["Can shoot TNT or TNT minecarts!", "(Latter is NOT recommended)."])

    addTooltip('omgamod:saturation_fruit', ["Gives 'Saturation' effect which restores most of your hunger"])

    
    //*/
})