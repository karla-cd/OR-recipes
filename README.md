# OR-recipes
Repozitorij laboratorijskih vježbi iz kolegija Otvoreno računarstvo

# O repozitoriju
**Autor**: Karla Ćosić-Dragan

**Verzija**: 1.0

**Jezik**: Engleski

# Sadržaj

* `dump` - direktorij s dump-om baze podataka
* `LICENSE` - MIT licencija
* `recipes.csv` - datoteka u `.csv` formatu izvedena iz baze podataka
* `recipes.json` - datoteka u `.json` formatu izvedena iz baze podataka  

# O podacima

**Recipe**

| Atribut             | Opis                                                    | Tip podatka       |
| ------------------- | ------------------------------------------------------- | ----------------- |
| Name                | Ime recepta                                             | string            |
| Rating              | Ocjena recepta                                          | number            |
| Ease of preparation | Jednostavnost pripreme recepta                          | string            |
| Type                | Tip jela na koje se recept odnosi                       | string            |
| Preparation time    | Vrijeme potrebno za pripremu jela po receptu u minutama | number            |
| Ingredients         | Sastojci potrebni za pripremu jela po receptu           | Ingredient objekt |
| Calories            | Broj kalorija jela                                      | number            |
| Daily value         | Postotak dnevnog unosa                                  | string            |
| Servings            | Broj porcija jela                                       | number            |
| Web Source          | Izvor                                                   | string            |

**Ingredient**

| Atribut             | Opis              | Tip podatka |
| ------------------- | ----------------- | ----------- |
| Amount              | Količina sastojka | string      |
| Ingredient          | Ime sastojka      | string      |

# Izvor
Podaci su, uz sitne preinake, preuzeti s web stranice https://www.edamam.com/.

# Licencija
MIT licencija omogućava korištenje repozitorija bez ikakvih restrikcija osim da obavijest o autorskim pravima i dopuštenjima navedenim u licenciji mora biti uključena u sve kopije ili znatne dijelove repozitorija. 
