// priority: 0

onEvent('jei.remove.recipes', event => {
	//console.info(event.categoryIds.toArray().join('\n'))
	
	for (let i = 1; i < 64; i++) {
		if (i == 37 || i == 33) continue;
		event.remove('create:spout_filling','create:filling/fertilizer_spray_' + i)
	}
})

onEvent('jei.information', event => {
	event.add('omgamod:goldsteel_blend', ['Dip goldsteel blend into honey, then blazing blood, then molten gold to turn it into goldsteel ingot.'])
	event.add('omgamod:goldsteel_ingot', ['Dip goldsteel blend into honey, then blazing blood, then molten gold to turn it into goldsteel ingot.'])
	
})