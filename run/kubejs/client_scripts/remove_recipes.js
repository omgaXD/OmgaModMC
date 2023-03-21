// priority: 0

onEvent('jei.remove.recipes', event => {
	//console.info(event.categoryIds.toArray().join('\n'))
	
	for (let i = 1; i < 64; i++) {
		if (i == 37 || i == 33) continue;
		event.remove('create:spout_filling','create:filling/fertilizer_spray_' + i)
	}
})