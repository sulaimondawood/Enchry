```toml
name = 'Login'
method = 'POST'
url = 'http://localhost:4000/api/auth/login'
sortWeight = 2000000
id = '31a8d279-b4fa-4d7e-98f9-8ee73cd85054'

[body]
type = 'JSON'
raw = '''
{
  email: "te@gmail.com",
  password: "1234567890",
}'''
```
