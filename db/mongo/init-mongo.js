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
        "password": "$2a$10$nSr0.P.5sqs4WKO5/HXfN.O6.muflWnLjwFb8YOWT0UWlI.8.94/q",
        "currency": "ARS",
        "role": 
        {
            "granted_authorities": ["ROLE_USER","ROLE_ADMIN"]
        } 
    },
    { 
        "username": "Don Marin", 
        "email": "don.marin@gmail.com", 
        "enabled": true, 
        "password": "$2a$10$FIa4MhUo40NG6evl1q7Bqee2hX3O1aHTPiV/kZxe7rvVJGNI7woM.",
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
        "password": "$2a$10$luaNjrjqk00XgaI5d5e0j.JDYFzj8PbI0j6.1w5x8uSfl6Vr2WuES",
        "currency": "MXN",
        "role": 
        {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        } 
    }
]);