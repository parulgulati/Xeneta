@HomePage
Feature: Navigate to Xeneta's URL, Verify UI checks. 

Background: 
	Given User Navigate to Application "userUrl" 
		
Scenario Outline: Validate the home page and three components of the page
	Given Page is Loaded successfully "<pageTitle>" 
	When User Clicks Xeneta logo it navigates to home Page "<XenetaURL>" "<XenetapageTitle>" 
	Then Verify Top Bar links on the Page "<ListItems>"
	Then Validate home page has main Components "<comp1>" "<comp2>" "<comp3>" 
	When User Click on SignIn Login Page should Open "<loginPageTitle>" 
	
Examples: 
|pageTitle		 |comp1		  |comp2	   |comp3		|ListItems												    |loginPageTitle|XenetaURL |XenetapageTitle|loginPageTitle								  |
|Xeneta in Action|Watch Videos|Schedule Now|Sign up here|Our Customers,Platform,Learn,Company,Sign in,Book a Meeting|Xeneta		   |xeneta.com|Xeneta 		  | Ocean & Air Freight Rate Benchmarking Platform|
		
		
Scenario Outline: Verify that icons and links of the 3 components are working as expected 
	Given Page is Loaded successfully "<pageTitle>" 
	Then Verify links of all components are working as expected "<expPageWatchVideo>" 
	Then Verify icons of all components are working as expected "<expPageWatchVideo>" 
	
Examples: 
|pageTitle		 |expPageWatchVideo|
|Xeneta in Action|Watch Xeneta Videos|