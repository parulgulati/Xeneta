@Career
Feature: Verify UI Checks on Career's Page

Background:
	Given User Navigate to Application "careersURL"
	
Scenario Outline: Verify Career's Page is loaded and values are present on the page
	Given Page is Loaded successfully "<pageTitle>"
	Then Verify Xenetas Values "<Values>"
	When Office locations are displayed on the page
	Then Location links given are working as expected "<osloPage>" "<newyork>" "<hamburg>"

Examples:
|pageTitle|Values																				  |osloPage    |newyork              |hamburg|
|Careers  |Xeneta is one,Modernization through data,Variety and Fairness,Transparency builds Trust|Oslo Careers|New York City Careers|Hamburg Careers|


Scenario Outline: Verify Open Roles on Career's Page
	Given Page is Loaded successfully "<pageTitle>"
	When Open Roles section is visible
	Then Verify list of Open roles and description 
    Then Verify Apply link of open role
	
Examples:
|pageTitle|
|Careers  |