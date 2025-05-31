```toml
name = 'Register'
method = 'POST'
url = 'http://localhost:4000/api/auth/register'
sortWeight = 1000000
id = '59790816-71ff-47a9-af34-e88e0f9271b8'

[body]
type = 'JSON'
raw = '''
{
  email: "test@gmail.com",
  password: "1234567890",
  fullname: "Dawood Sulaimon"
}'''
```
