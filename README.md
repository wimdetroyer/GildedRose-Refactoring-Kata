# Refactoring Kata

## Step 0 - some preliminary things ...

### Setup

- I removed clutter
- We'll work under refactor/gildedRoseRefactor for the refactoring of code.
- For the new functionality (conjured items) we'll work under feature/addConjuredItems.
- Commits will follow following guidelines: https://www.conventionalcommits.org/en/v1.0.0/

### Some thoughts on the existing code
- I will not change the sequence of the code (that is not refactoring - however in this case the functionality would not change, so...) but I must admit it strikes me as odd that the value of sellIn is not IMMEDIATELY decremented instead of in the middle of the method.
- The Item class cannot be changed. That's a shame . 
  - I'm a proponent of Domain Driven Design where possible (<> anemic domain model: https://martinfowler.com/bliki/AnemicDomainModel.html)
    - quality must be >=0 & <= 50 is an example of a business rule inherent to an item
    - Legendary items (like Sulfaras) being immutable with a fixed value is an example of a business rule inherent to an item
  - I suppose that extending the class is allowed (*)
    - I'll do this to atleast not have checks on hardcoded strings but via type checking (with instanceof)
      - It isn't ideal but if its allowed...
      - i wont add getters & setters / methods in the base classes as i feel this is cheating + duplication sometimes

### Some thoughts on the 'conjured item'

- The concept of a 'conjured' item is a bit unclear for me
  - is it a seperate item? (the text fixture seems to indicate this so i'll assume it's this for this exercise!)
  - is it a property which is decorated upon an item? (i.e: Conjured Sulfuras, Conjured Brie, ...) 
    - (*) if this were the case it'd be harder to match this with an inheritance based data model (though at first glance the decorator pattern could come to the rescue.)

## Step 1 - writing the unit tests for the existing code

We assume everything worked beforehand, but in order to be confident in our refactors, we'll have to write tests for all possible scenarios.

Conventions:
 - naming: test<ScenarioUnderTest>_<expectedBehaviour>. Example: testLegendaryItem_doesNotDecreaseInQuality
 - Given-When-Then construction
 - TextTestFixture is not touched (naming is only rectified

After testing all scenarios (11), we get to 100% coverage. We're ready for some refactoring!

## Step 2 - Refactors on the existing code

### Step 2.1 - Extending the item class

Rationale:

 - avoids checking type on a hard coded string
 - fixes the value to 80 for the legendary items
 
Technical comments:

I'm aware a lot of the code refactored here will just dissapear in later steps, but to really drive home the incremental approach i'll do it anyway.

### Step 2.2 - Handling each item in a seperate method

Rationale:
 - reduce cognitive complexity
 - better naming
 - helpful for step 2.3

### Step 2.3 - moving the check on legendary item to the beginning as a guard statement/clause.

Rationale: 
 - See: https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html (cfr Martin Fowler)
 
### Step 2.4 - assure quality is always max 50 & minimum at the END of the method (post-assertion)

Rationale:
 - in order to reduce nested ifs
 - Since we dont process legendary items this will work nicely
 - with DDD, this is also something we could enforce on the item object itself
Drawback:
 - some code needlessly gets executed
