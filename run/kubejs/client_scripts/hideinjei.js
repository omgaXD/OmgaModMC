let hiddenIds = []
const hideInJei = (recipe) => {
    hiddenIds.add([recipe.getGroup(), recipe.getId()])
}
onEvent("jei.remove.recipes", event => {
    hiddenIds.forEach(element => {
        event.remove(element[0], element[1])
        console.log(element[0] + " : " + element[1])
    })
})