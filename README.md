# Lantek Coding Challenge

## 1. Instructions
Thank you for taking the time to complete our technical test. It consists of two parts:

- A coding exercise
- A few technical questions

Upload the coding exercise (including the readme file with the answers to the technical questions) to a public Git Repository of your choice and provide us with the URL. 


## 2. Coding Challenge

### 2.1. The Cutting Machines Demo Endpoint
Lantek has a public demo API available at `https://app-academy-neu-codechallenge.azurewebsites.net/api/cut` which returns a list of cutting machines. The endpoint also accepts a parameter to filter machines out by cutting technology:

- Use `/api/cut/2` to fetch cutting machines for 2D technology.

- Use `/api/cut/3` to fetch cutting machines for 3D technology.

This endpoint is protected by basic authentication. You should have received a valid username and password with this assignment.

Please note that this API is running under a serverless compute tier so the first request might be slower than usual while the app becomes online.

Example Request:


```
[GET] https://app-academy-neu-codechallenge.azurewebsites.net/api/cut
```

Headers

```
Authorization 'Basic <Base64 encoded credentials>'
Accept 'application/json'
```


Example Response

```
[
    {
        "id": "8bd1304b-5ac6-49ef-93b4-df0a12b93a3b",
        "name": "GF3015",
        "manufacturer": "HGTech"
    },
    {
        "id": "fffe0fd5-5b1e-49d3-b761-8b9518ee1b72",
        "name": "LT8.10",
        "manufacturer": "BLM Group"
    }
]
```

### 2.2. The Task

The task is to create an application that displays the existing cutting machines filtered by cutting technology (2D/3D). The list of cutting machines must be retrieved by querying the above API. The following information should be displayed for each machine:

- Name
- Manufacturer
- Cutting technology

You can create the application as either a command line application or a web application:

- .NET or JavaScript/TypeScript (you can use Angular/React/Vue.js) for web applications. 
- .NET for command-line applications

Think about the type of work you would like to do at Lantek and choose an appropriate application type and platform.

### 2.3. The Requirements

Feel free to spend as much time on the assignment as you like, as long as the below requirements are met. We would advise you to spend no more than 2-3 hours on this assignment.

We also consider the answers to the technical questions (included in the markdown file) and what you would like to have added if you had more time.

**_Requirements_**

- Complete the user story below.
- Your code should compile and run in one step.
- Feel free to use whatever frameworks/libraries/packages you like.
- You must include tests

**_User Story_**

` As a user running the application, `
` I want to see a list of cutting machines `
` and I want to be able to filter them by technology (2D/3D) `

**_Acceptance criteria_**

A list of cutting machines is returned.
The name, the manufacturer, and the cutting technology are displayed.
The cutting machines can be filtered out by technology.

# 3. Technical Questions

Please answer the following questions in a markdown file called Answers to technical questions.md.

1. How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend much time on the coding test, use this as an opportunity to explain what you would add.

1. What was the most useful feature added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.

1. How would you track down a performance issue in production? Have you ever had to do this?

1. How would you improve the Lantek API that you just used?

Thanks for your time. We look forward to hearing from you! If you have any questions or feedback about this coding exercise, please contact your HR representative.

**The Lantek Tech Team**