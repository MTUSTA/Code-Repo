import json

# some JSON:
x = {
    "firstName": "Jane",
    "lastName": "Doe",
    "hobbies": ["running", "sky diving", "singing"],
    "age": 35,
    "children": [
        {
            "firstName": "Alice",
            "age": 6
        },
        {
            "firstName": "Bob",
            "age": 8
        }
    ]
}

#1 cesit sözlük , str to json json.loads(x) kullan


print(x)
print(type(x["children"][0]["firstName"]))
print(x["children"][0]["firstName"])


# a Python object (dict):
x = {
  "name": "John",
  "age": 30,
  "city": "New York"
}

# convert into JSON string:
y = json.dumps(x)

# the result is a JSON string:
print(y)
