@TariffManagement @Tariff
Feature: Search a tariff
    In order to find a specific tariff of a product
    as an API consumer
    I must be able to retrieve the information of the tariff

    @HappyPath
    Scenario: Successful tariff search
        Given I already have a created price
        And I fill the search tariff form with valid data
        When I click the SEARCH TARIFF button
        Then I should see OK status code
        And I should see the tariff information
