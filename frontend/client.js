let recipesJsonArray = ""
let recipesCsvArray = ""

let response = fetch("http://localhost:8080/recipes")
    .then(response => response.json())
    .then(
        array => {
            var t = "<tr><th>Name</th><th>Rating</th><th>Ease of preparation</th><th>Type</th><th>Preparation time</th><th>Ingredients</th><th>Calories</th><th>Daily value</th><th>Servings</th><th>Web source</th></tr>"
            var csv = "Name;Rating;Ease of preparation;Type;Preparation time;Ingredients;Calories;Daily value;Servings;Web Source\n"
            for(var i = 0; i < array.length; i++) {
                var tr = "<tr>"
                tr += "<td>" + array[i].name + "</td>"
                tr += "<td>" + array[i].rating + "</td>"
                tr += "<td>" + array[i].easeOfPreparation + "</td>"
                tr += "<td>" + array[i].type + "</td>"
                tr += "<td>" + array[i].preparationTime + "</td>"
                tr += "<td>"
                for(var j = 0; j < array[i].ingredients.length; j++) {
                    tr += array[i].ingredients[j].ingredient
                    tr += " (" + array[i].ingredients[j].amount + ")"
                    if(j != array[i].ingredients.length - 1)
                        tr += ", "
                }
                tr += "</td>"
                tr += "<td>" + array[i].calories + "</td>"
                tr += "<td>" + array[i].dailyValue + "</td>"
                tr += "<td>" + array[i].servings + "</td>"
                tr += "<td>" + array[i].webSource + "</td>"
                tr += "</tr>"
                t += tr
                csv += array[i].name + ";" + array[i].rating + ";" + array[i].easeOfPreparation + ";" + array[i].type + ";" + array[i].preparationTime + ";" + JSON.stringify(array[i].ingredients) + ";" + array[i].calories + ";" + array[i].dailyValue + ";" + array[i].servings + ";" + array[i].webSource + "\n"
            }
            document.getElementById("table").innerHTML = t;
            recipesJsonArray = JSON.stringify(array)
            recipesCsvArray = csv
        }
    )

function getSearchResult() {
    let response = fetch("http://localhost:8080/recipes", {
        method: "post",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ value: document.getElementById("searchValue").value, attribute: document.getElementById("attribute").value })
    })
    .then(response => response.json())
    .then(array => {
            var t = "<tr><th>Name</th><th>Rating</th><th>Ease of preparation</th><th>Type</th><th>Preparation time</th><th>Ingredients</th><th>Calories</th><th>Daily value</th><th>Servings</th><th>Web source</th></tr>"
            var csv = "Name;Rating;Ease of preparation;Type;Preparation time;Ingredients;Calories;Daily value;Servings;Web Source\n"
            for(var i = 0; i < array.length; i++) {
                var tr = "<tr>"
                tr += "<td>" + array[i].name + "</td>"
                tr += "<td>" + array[i].rating + "</td>"
                tr += "<td>" + array[i].easeOfPreparation + "</td>"
                tr += "<td>" + array[i].type + "</td>"
                tr += "<td>" + array[i].preparationTime + "</td>"
                tr += "<td>"
                for(var j = 0; j < array[i].ingredients.length; j++) {
                    tr += array[i].ingredients[j].ingredient
                    tr += " (" + array[i].ingredients[j].amount + ")"
                    if(j != array[i].ingredients.length - 1)
                        tr += ", "
                }
                tr += "</td>"
                tr += "<td>" + array[i].calories + "</td>"
                tr += "<td>" + array[i].dailyValue + "</td>"
                tr += "<td>" + array[i].servings + "</td>"
                tr += "<td>" + array[i].webSource + "</td>"
                tr += "</tr>"
                t += tr
                csv += array[i].name + ";" + array[i].rating + ";" + array[i].easeOfPreparation + ";" + array[i].type + ";" + array[i].preparationTime + ";" + JSON.stringify(array[i].ingredients) + ";" + array[i].calories + ";" + array[i].dailyValue + ";" + array[i].servings + ";" + array[i].webSource + "\n"
            }
            document.getElementById("table").innerHTML = t
            recipesJsonArray = JSON.stringify(array)
            recipesCsvArray = csv
        }
    )
}

function saveJson() {
    var blob = new Blob([recipesJsonArray], { type: "text/plain;charset=utf-8" });
    saveAs(blob, "recipes.json");
}

function saveCsv() {
    var blob = new Blob([recipesCsvArray], { type: "text/plain;charset=utf-8" });
    saveAs(blob, "recipes.csv");
}
