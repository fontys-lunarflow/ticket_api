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

### POST: /api/labels/create

**Request body**
```json
{
  "id": 0,
  "name": "Mylabel",
  "color": "#000000"
}
```

**Response body**
```json
{
  "error": false,
  "mesg": "",
  "data": {
    "id": 0,
    "name": "Mylabel",
    "color": "#000000"
  }
}
```

### POST: /api/labels/delete

**Request body**
```json
{
  "id": 0,
  "name": "Mylabel",
  "color": "#000000"
}
```

**Response body**
```json
{
  "error": false,
  "mesg": "",
  "data": {
    "id": 0,
    "name": "Mylabel",
    "color": "#000000"
  }
}
```

### GET: /api/labels/list

**Response body**
```json
{
  "error": false,
  "mesg": "",
  "data": [
    {
      "id": 0,
      "name": "Mylabel",
      "color": "#000000"
    },
    {
    "id": 1,
    "name": "Otherlabel",
    "color": "#000000"
    }
  ]
}
```

### POST: /api/labels/list/filtered

**Request body**
```json
[
  {
    "id": 0,
    "name": "Mylabel",
    "color": "#000000"
  },
  {
    "id": 1,
    "name": "Otherlabel",
    "color": "#000000"
  },
]
```

**Response body**
```json
{
  "error": false,
  "mesg": "",
  "data": [
    {
      "id": 0,
      "name": "Mylabel",
      "color": "#000000"
    },
    {
      "id": 1,
      "name": "Otherlabel",
      "color": "#000000"
    },
  ]
}
```
