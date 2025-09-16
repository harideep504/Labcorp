Feature: LabCorp Job Search

  Scenario: Navigate to Careers and verify a job posting
    
    Given I open the LabCorp home page
    
    When I click on the Careers link
    
    And I search for job titled "Software Engineer"
    
    Then I should see the job posting details containing "Software Engineer"
