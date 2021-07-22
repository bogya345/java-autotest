Feature: Test first

  Scenario: Checking site
    Then Click selected link
    Then Click advanced search
    Then Click on search settings
#    Then Choose only "615-ПП РФ" in behavior rules
    Then Choose all options in behavior rules
    Then Choose in quick settings "Исключить совместные закупки"
    Then Set until date on "сегодня"
    Then Choose needed region "Алтайский край"
    Then Click on search button
    Then Save the results to a file called "results"