# Ticket API Documentation

### Data models

**Form**
```json
{
  "error": false,
  "mesg": "",
  "data": T
}
```
Where `T` is an embedded type.

**Ticket**
```json
{
  "id": 11,
  "title": "My ticket",
  "desc": "This is a exmaple of a ticket.",
  "url": "https://gitlab.com/org/project/-/issues/11",
  "labels": [
    "Mylabel",
    "Otherlabel"
  ]
}
```
Generally only the `id` and `title` fields are required.

**Taglabel**
```json
{
  "id": 40884602,
  "name": "Mylabel",
  "color": "#AABBCC"
}
```
Generally only the `id` and `name` fields are required.

### GET: /ping

**Request body**

**Response body**
```json
{
  "ping": "pong",
  "config": {
    "serverURL": "https://gitlab.com/",
    "projectPath": "group/project",
    "token": "gl-some-token"
  }
}
```

### POST: /api/new

**Request body**
```json
{
  "title" : "Demo ticket",
  "desc": "This is a demo ticket."
}
```
**Response body**
```json
{
  "id": 3,
  "title": "Demo ticket",
  "desc": "This is a demo ticket.",
  "dueDate": "",
  "url": "https://gitlab.com/group/project/-/issues/3",
  "assignees": null
}
```

### GET: /api/request

**Request body**
```json
{
  "id": 3
}
```
**Response body**
```json
{
  "id": 3,
  "title": "Demo ticket",
  "desc": "This is a demo ticket.",
  "dueDate": "",
  "url": "https://gitlab.com/group/project/-/issues/3",
  "assignees": null
}
```
