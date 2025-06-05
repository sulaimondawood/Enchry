```toml
name = 'Create Climate'
method = 'POST'
url = 'http://localhost:4000/api/climate'
sortWeight = 1000000
id = '802cef54-9df1-41bd-ac45-9bd9552c2442'

[auth.bearer]
token = 'eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkYXdvb2RAZ21haWwuY29tIiwiZnVsbG5hbWUiOiJEYXdvb2QgRGV2aWNlIiwiaWF0IjoxNzQ5MTI3MTg3LCJleHAiOjE3NDkyMTM1ODd9.hnR5WHA8MTa3ewANepjUNLgtamCGu8Duef4U8-xMk4RTctkyLyCIRuiN5nBw7xTo'

[body]
type = 'JSON'
raw = '''
{
   "sensoredData": "",
   "nonce": "tgjkfdlvmdvm ",
   "salt": "",
   "time": "3466735",
   "deviceId":"fae2d38d-1da6-4e67-a0ab-0bac32f33b35",
  "timezone": "",
  "longitude": "56",
  "latitude": "65"
}'''
```
