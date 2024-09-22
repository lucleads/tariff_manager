@TariffManagement @Tariff
Feature: Search a tariff
    In order to find a specific tariff of a product
    as an API consumer
    I must be able to retrieve the information of the tariff

    @HappyPath @TestIWouldDo
    Scenario: Successful tariff search
        Given I already have a created price
        And I fill the search tariff form with valid data
        When I click the SEARCH TARIFF button
        Then I should see OK status code
        And I should see the tariff information

    @HappyPath @RequiredTest
    Scenario Outline: Successful search some tariffs
        Given I have prices for <productId> <brandId> "<date>" with information
            | brandId | startDate           | endDate             | priceList | productId | priority | price | curr |
            | 1       | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1         | 35455     | 0        | 35.50 | EUR  |
            | 1       | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2         | 35455     | 1        | 25.45 | EUR  |
            | 1       | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3         | 35455     | 1        | 30.50 | EUR  |
            | 1       | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4         | 35455     | 1        | 38.95 | EUR  |
        And I fill the search tariff form with the info "<brandId>" "<productId>" "<date>"
        When I click the SEARCH TARIFF button
        Then I should see OK status code
        And I should see the tariff information for the tariff <expectedTariff>

        Examples:
            | brandId | productId | date                | expectedTariff |
            | 1       | 35455     | 2020-06-14-10.00.00 | 1              |
            | 1       | 35455     | 2020-06-14-16.00.00 | 2              |
            | 1       | 35455     | 2020-06-14-21.00.00 | 2              |
            | 1       | 35455     | 2020-06-15-10.00.00 | 3              |
            | 1       | 35455     | 2020-06-16-21.00.00 | 4              |