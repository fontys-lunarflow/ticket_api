# Ticket API Documentation

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
