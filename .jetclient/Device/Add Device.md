```toml
name = 'Add Device'
method = 'POST'
url = 'http://localhost:4000/api/devices'
sortWeight = 1000000
id = '91acfeda-2410-405d-8496-000b8271d881'

[[headers]]
key = 'Authorization'
value = 'Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkYXdvb2RAZ21haWwuY29tIiwiZnVsbG5hbWUiOiJEYXdvb2QgRGV2aWNlIiwiaWF0IjoxNzQ4NzAxNzk2LCJleHAiOjE3NDg3ODgxOTZ9.l8UJ3G3g9cVDclr9_synirpJiPY-gK7QZtlDzK-15soxSWjOdlo5S5wB5WSrKPJD'

[auth]
type = 'BEARER'

[body]
type = 'JSON'
raw = '''
{
  deviceName: "King'ss sensor",
  deviceDescription: "Used for scanning both temp and hum.",
  sensorType: "BOTH",
}'''
```
