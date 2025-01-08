db.createUser({
        user: 'gusta',
        pwd: 'probando2025',
        roles: [
            {
                role: 'readWrite',
                db: 'testDB',
            },
        ],
    });
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    { 
        "username": "Jhon Doe", 
        "email": "Jhon.Doe@gmail.com", 
        "enabled": true, 
        "password": "1234",
        "currency": "ARS",
        "role": 
        {
            "granted_authorities": ["ROLE_ADMIN"]
        } 
    },
    { 
        "username": "Don Marin", 
        "email": "don.marin@gmail.com", 
        "enabled": true, 
        "password": "1234",
        "currency": "USD",
        "role": 
        {
            "granted_authorities": ["ROLE_USER"]
        } 
    },
    { 
        "username": "Don Pingue", 
        "email": "don.pingue@gmail.com", 
        "enabled": true, 
        "password": "1234",
        "currency": "MXN",
        "role": 
        {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        } 
    }
]);