## Rest API Spring JDBC n-m-relation

## add a Student

POST localhost:8080/api/student

{
    "name": "Test",
    "age": 22,
    "courses": [
        {
            "courseId": 1,
            "description": "English"
        },
        {
            "courseId": 3,
            "description": "Science"
        }
    ]
}

or 

{
    "name": "Test2",
    "age": 22,
    "courses": []
}

or

{
    "name": "Test3",
    "age": 22
}

## get a Student with ID = 1

GET localhost:8080/api/student/1
