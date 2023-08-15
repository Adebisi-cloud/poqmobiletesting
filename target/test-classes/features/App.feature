Feature: CottonTrader App Test
  As a user on CottonTrader App
  I want to be able to add 7th item in the bag
  as well as view the bag and choose from knitwear or any category with 7 items


  Scenario:  Adding Item
    Given I launch the cottonTraderApp
    When I navigate to the shop bottom navigation
    And I select Men
    And I select Clothing
    And I select knitwear category
    And I select the seventh item displayed
    And I select a color if available
    And I add the item to bag
    Then I should see a snackBar
    And I view the bag

