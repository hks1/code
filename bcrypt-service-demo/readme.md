```xml
<dependency>      
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-crypto</artifactId>
</dependency>
```

__JBcrypt__ hashes passwords using a version of _Bruce Schneier's Blowfish_ block cipher with modifications designed to raise the cost of off-line password cracking.
The computation cost of the algorithm is parameterized, so it can be increased as computers get faster.

1. Hash password:
```
BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
```

BCrypt.gensalt() is a salt generated randomly.

In case we want to increment the complexity, an optional parameter (`log_rounds`) has to be provided to `BCrypt.gensalt()`, which determines the computational complexity of the hashing. `log_rounds` is exponential (2<sup>_log_rounds_</sup>) and it specifies how many times to run the internal hash function. The default value is 10, and the valid values are between 4 and 31.

The output strings after running `hashpw()` will look like:

`$bcrypt_id$log_rounds$128_bits_salt184_bits_hash`

`hashpw()` is smart enough to extract the salt from the string.

2. Check hashed password:
```
BCrypt.checkpw(unencryptedPassword, hashedPassword);
```

BCrypt is very good hash algorithm for preventing rainbow table attacks by keeping the salt as part of the output from the BCrypt function.
> A Rainbow table is a precomputed table that contains plaintext passwords and their corresponding hash values that can be used to find the text that generates a particular hash. Hackers can use it for cracking hashed passwords stored in a database.

Bcrypt is 10,000 times slower than sha1 to run. If we have a machine that is able to run it in 100ms, this is probably fast enough for login, but it might be too slow if we want to execute Bcrypt against a long list of passwords. In consequence, if a hacker wants to run Bcrypt a billion times by using the same computational power, it will take 27,777 hours.






--user not found
```bash
$ http post localhost:8080/users/login email=user@bitly.com password=topsecret
HTTP/1.1 404
Connection: keep-alive
Content-Length: 14
Content-Type: application/json
Date: Wed, 11 Jan 2023 13:07:27 GMT
Keep-Alive: timeout=60

user not found

```

--create new user

httpie
```bash
http post localhost:8080/users email=user@bitly.com password=topsecret
```
output
```
HTTP/1.1 201
Connection: keep-alive
Content-Length: 0
Date: Wed, 11 Jan 2023 04:06:32 GMT
Keep-Alive: timeout=60

```

curl
```bash
curl -H "Content-Type: application/json" -X POST -d '{"email":"test@test.com","password":"test"}' http://localhost:8080/users
```

--successful login

httpie
```bash
http post localhost:8080/users/login email=user@bitly.com password=topsecret
```
output
```
HTTP/1.1 200
Connection: keep-alive
Content-Length: 22
Content-Type: application/json
Date: Wed, 11 Jan 2023 04:07:47 GMT
Keep-Alive: timeout=60

Welcome user@bitly.com

```

curl
```
curl -H "Content-Type: application/json" -X POST -d '{"email":"test@test.com","password":"test"}' http://localhost:8080/users/login
```
output
```
Welcome test@test.com
```



<!--
Ref:
https://sergiomartinrubio.com/articles/storing-passwords-securely-with-bcrypt-and-java/
https://github.com/smartinrub/bcrypt-service
-->