// Install Redis by msi or zip file, add into environment variable, run redis-server, redis-cli
// Install: npm init, npm install express body-parser redis method-override express-handlebars --save

// Open 3 cmd
// npm start     as node app (package.json)
// redis-server or redis-server --service-start
// redis-cli
    // Model: HMSET myhash field1 "Hello" field2 "World"
    // HMSET user01 first_name "John" last_name "Tony" email "DNY@gmail.com" phone "44-012444"
    // HMSET user02 first_name "Tony" last_name "Stack" email "QMT@gmail.com" phone "45-012345"

// How to host??

// bootstrap example: https://getbootstrap.com/docs/5.1/examples/
// Regis: https://redis.io/commands/HMSET

// Ref: https://www.youtube.com/watch?v=9S-mphgE5fA&list=LL&index=3 (Link bootstrap isn't avaiable)

const express = require('express');
const exphbs = require('express-handlebars');
const path = require('path');
const bodyParser = require('body-parser');
const methodOverride = require('method-override');
const redis = require('redis');

// Create Redis Client
let client = redis.createClient();

client.on('connect', function(){
  console.log('Connected to Redis...');
});

// Set Port
const port = 3000;

// Init app
const app = express();

// View Engine\
app.engine('handlebars', exphbs({defaultLayout:'main'}));
app.set('view engine', 'handlebars');

app.use(express.static('public'));
// body-parser
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false}));

// methodOverride
app.use(methodOverride('_method'));

// Search Page
app.get('/', function(req, res, next){
  res.render('login');
});

app.get('/signIn', function(req, res, next){
  res.render('login');
});

// app.get('/user/:id', function(req, res) {
//   res.send('id: ' + req.params.id);
// });

app.get('/user/:id', function(req, res){
  let id = req.params.id
  client.hgetall(id, function(err, obj){
    if(obj){
      obj.id = id
      res.render('user', {
        user: obj
      })
    }
  })
  
})

app.post('/user', function(req, res, next){
  let id = req.body.id;
  let password = req.body.password;
  if(id == '' || password == ''){
    res.render('login', {
      error: 'Your password or id is empty'
    })
  }else if(id == 'admin' && password == 'admin'){
    res.redirect('/admin')

  }else{
    client.hgetall(id, function(err, obj){
      if(!obj){
        res.render('login',{
          error: 'Your password or id is wrong'
        })
      }else{
        if(obj.password == password){
          //how to change man?
          obj.id = id
          res.render('user', {
            user: obj
          })  
           
        }else{
          res.render('login',{
            error: 'Your password or id is wrong'
          })
        }
      }
    })
  }
})



app.get('/signUp', function(req, res, next){
  res.render('signup');
});

// sign up
app.post('/signUp', function(req, res, next){
  let id = req.body.id;
  let password = req.body.password
  let email = req.body.email;
  let phone = req.body.phone;

  client.hgetall(id, function(err, obj){
    if(!obj){
      client.hmset(id, [
        'password', password,
        'email', email,
        'phone', phone
      ], function(err, reply){
        if(err){
          console.log(err);
        }
        console.log(reply);
        res.redirect('/signIn');
      })
    }else{
      res.render('signup', {
        err: 'This username have already existed'
      })
    }
  });
});

// app.get('/user/:id', function(req, res, next){
//   // req.params.id
// })

// // Add User Page
app.get('/user/update', function(req, res, next){
  res.render('update');
}); 

app.get('/user/update/:id', function(req, res, next){
  let id = req.params.id
  client.hgetall(id, function(err, obj){
    if(obj){
      obj.id = id
      res.render('update', {
        user: obj
      })
    }
  })
})

// // Process Add User Page
app.post('/user/update/:id', function(req, res, next){
  let id = req.params.id;
  let password = req.body.password
  let email = req.body.email;
  let phone = req.body.phone;

  client.hmset(id, [
    'password', password,
    'email', email,
    'phone', phone
  ], function(err, reply){
    if(err){
      console.log(err);
    }
    console.log(reply);
    res.redirect('/user/' + id);
  });
});


app.get('/admin', function(req, res, next){
  res.render('searchusers');
});

// Search processing
app.post('/admin/search', function(req, res, next){
  let id = req.body.id;

  client.hgetall(id, function(err, obj){
    if(!obj){
      res.render('searchusers', {
        error: 'User does not exist'
      });
    } else {
      obj.id = id;
      res.render('details', {
        user: obj
      });
    }
  });
});



// Delete User
app.delete('/admin/delete/:id', function(req, res, next){
  client.del(req.params.id);
  res.redirect('/admin');
});



app.listen(port, function(){
  console.log('Server started on port ' + port);
});