let tooltip = null;

const addTooltip = (item, str) => {
    tooltip.addAdvanced(item, (item, advanced, text) => {
    if (!tooltip.shift) {
        text.add(1, Text.gray('[Shift for more info]').italic())
    } else {
        let i = 1;
        str.forEach(st => {
            text.add(st);
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
    //*/
})