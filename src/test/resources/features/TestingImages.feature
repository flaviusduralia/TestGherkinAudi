Feature: Testing if images are present and visible

@Runme2
Scenario: Verifying if images are present and visible
	Given I am on audi DE dealer homepage
	Then Some Audi image should be visible
	And Some Audi logo should be visible
	Then I go to IT dealer homepage
	And Logo should be there 
	And Image should be there
	Then I go to FR dealer homepage
	And The logo should be visible
	And The Image should be visible
	Then I go to DE client page
	And Some Audi image should be visible client DE
	And Some Audi logo should be visible client DE
	Then I go to IT client page
	And The logo should be visible client IT
	And The Image should be visible client IT
	Then I go to FR client page
	And The logo should be visible client FR
	And The Image should be visible client FR
	 
	 