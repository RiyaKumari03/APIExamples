@AddPlace
Feature: Validating Place API's

Scenario Outline: Verify if Place is being Successfully added using AddPlace API
Given Add Place payload with "<name>","<language>","<address>","<phone>" and "<website>"
When User calls "AddPlaceAPI" with "post" Http request
Then The API call should be success with status code 200
And "status" in response body is "OK"
And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
|name           |language |address                  |phone             |website          |
|Frontline house|French-IN|29, side layout, cohen 09|(+91) 983 893 3937|http://google.com|
#|Fsrshfuke house|AsiaSp-IN|28, cyhb layout, ojlkb 10|(+91) 890 893 6767|http://uguugi.com|

@DeletePlace
Scenario: Verify Delete Place API
Given Delete Place Payload
When User calls "DeletePlaceAPI" with post http request
And The API call should be success with status code 200
And "status" in response body is "OK"