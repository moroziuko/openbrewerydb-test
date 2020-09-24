# openbrewerydb-test
Open Brewery DB Testing (POC)

https://www.openbrewerydb.org/

1) Covered "Search Breweries" endpoint with few tests using JUnit, REStAssured, Jackson AssertJ tools
2) "List Breweries" endpoint can be covered using Equivalence partitioning and Boundary values design techniques.
    For each equivalent verification I would create some DataProvider factory to generate data sets for different inputs and/or outputs. 
    Then I would use these data sets for parametrized tests using @MethodSource annotation.
    For example, set of tests parameters will include: 
    - different filter keys and values (valid/invalid/empty etc.)
    - combinations with different page numbers + verify boundary values
    - different number of breweries per page
    - different sorting 
    - combinations with different filters, different page numbers, different numbers per page and different sorting. 
        In such tests the data could be randomised for each executions to avoid long creation and execution of all combinations. 
        In case its not acceptable by business/requirements I would use Pairwise Testing technique. Some testing tools can be used to save time for test design.
    Estimation:
    1-3 working days depends on solution of the last data set
    


